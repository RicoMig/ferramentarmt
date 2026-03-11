package br.com.ferramentarmt;

import br.com.ferramentarmt.config.MongoJDBC;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import static com.mongodb.client.model.Filters.eq;

/**
 * Sandbox script to verify the conversion logic and database connection.
 * This script runs independently using a main method.
 */
public class ConversionSandboxTest {

    public static void main(String[] args) {
        System.out.println("========================================");
        System.out.println("   RMT CALCULATOR - SANDBOX VERIFIER    ");
        System.out.println("========================================\n");

        try {
            // 1. Fetch Data: Connect to the rmt_tool_db
            MongoDatabase database = MongoJDBC.conect();
            if (database == null) {
                System.err.println("[ERROR] Failed to connect to MongoDB. Check if service is running.");
                return;
            }

            // Target: 'KAK' (Kakele)
            String targetGameCode = "KAK";
            Document gameDoc = database.getCollection("game-Param")
                    .find(eq("gameCode", targetGameCode))
                    .first();

            if (gameDoc == null) {
                System.err.println("[ERROR] Game '" + targetGameCode + "' not found in 'game-Param'. Run DatabaseSeederTest first.");
                return;
            }

            // Extract rates
            String gameName = gameDoc.getString("gameName");
            double goldRate = gameDoc.getDouble("inGameToPremiumRate");
            double cashRate = gameDoc.getDouble("premiumToGlobalRate");

            // 2. Simulate User Input (Frontend Mock)
            double userInGameInput = 1000000; // 1 Million Gold
            double userPremiumInput = 500;    // 500 Premium Coins

            // 3. Calculation Logic
            double inGameEquivalentToPremium = userInGameInput * goldRate;
            double totalPremiumAmount = inGameEquivalentToPremium + userPremiumInput;
            double totalGlobalValue = totalPremiumAmount * cashRate;

            // 4. Console Output: Clean Report
            System.out.println(">>> GAME PARAMETERS FETCHED <<<");
            System.out.println("Game: " + gameName + " (" + targetGameCode + ")");
            System.out.println("In-Game to Premium Rate: " + goldRate);
            System.out.println("Premium to Global Rate:  " + cashRate + " (R$)");
            System.out.println("----------------------------------------");

            System.out.println(">>> SIMULATED USER INPUTS <<<");
            System.out.println("User Input (Gold):   " + String.format("%,.2f", userInGameInput));
            System.out.println("User Input (Cash):   " + String.format("%,.2f", userPremiumInput));
            System.out.println("----------------------------------------");

            System.out.println(">>> STEP-BY-STEP CALCULATION <<<");
            System.out.println("[Step 1] " + String.format("%,.2f", userInGameInput) + " Gold * " + goldRate + " = " + inGameEquivalentToPremium + " Premium Coins");
            System.out.println("[Step 2] " + inGameEquivalentToPremium + " (from gold) + " + userPremiumInput + " (direct cash) = " + totalPremiumAmount + " Total Premium");
            System.out.println("[Step 3] " + totalPremiumAmount + " Total Premium * " + cashRate + " rate = R$ " + String.format("%.2f", totalGlobalValue));
            System.out.println("----------------------------------------");

            System.out.println("[FINAL RESULT] The total value in Global Money is: R$ " + String.format("%.2f", totalGlobalValue));
            System.out.println("\n========================================");

        } catch (Exception e) {
            System.err.println("[CRITICAL ERROR] Failed to execute sandbox test:");
            e.printStackTrace();
        }
    }
}
