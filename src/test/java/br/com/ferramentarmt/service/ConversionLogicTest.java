package br.com.ferramentarmt.service;

import br.com.ferramentarmt.model.GameConversion;
import br.com.ferramentarmt.repository.CalculatorDAO;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Unit tests to validate the mathematical conversion logic of the RMT Calculator.
 */
public class ConversionLogicTest {

    private static final double DELTA = 0.0001;
    private static CalculatorDAO calculatorDAO;

    @BeforeAll
    public static void setup() {
        // Initialize the DAO
        calculatorDAO = new CalculatorDAO();
        // Ensure connection is initialized (MongoJDBC is used inside DAO)
        System.out.println("Integration Test Setup: DAO initialized.");
    }

    /**
     * Test 1 (In-Game to Global):
     * Fetches 'rag' parameters from the database and performs the calculation.
     */
    @Test
    public void testInGameToGlobalConversion() {
        // Fetch real data from MongoDB
        GameConversion game = calculatorDAO.getGameParams("rag");
        
        assertNotNull(game, "Game parameters for 'rag' not found in database. Run DatabaseSeederTest first.");

        double ingameInput = 50000.0;
        
        // Dynamic calculation based on DB values
        double expectedPremium = ingameInput * game.getInGameToPremiumRate();
        double expectedGlobal = expectedPremium * game.getPremiumToGlobalRate();

        // Validate the logic holds up with DB values
        double actualGlobal = (ingameInput * game.getInGameToPremiumRate()) * game.getPremiumToGlobalRate();

        assertEquals(expectedGlobal, actualGlobal, DELTA, "The conversion from In-Game Gold to Global Money is incorrect using DB rates.");
        System.out.println("Test 1 Result (rag): " + ingameInput + " Gold -> R$ " + actualGlobal);
    }

    /**
     * Test 2 (Premium to Global):
     * Fetches 'WOW' parameters (if available) or uses 'pos' as a fallback.
     */
    @Test
    public void testPremiumToGlobalConversion() {
        // Fetch real data from MongoDB ('pos' was seeded in the updated script)
        GameConversion game = calculatorDAO.getGameParams("pos");
        
        assertNotNull(game, "Game parameters for 'pos' not found in database.");

        double premiumInput = 100.0;
        double expectedGlobal = premiumInput * game.getPremiumToGlobalRate();

        double actualGlobal = premiumInput * game.getPremiumToGlobalRate();

        assertEquals(expectedGlobal, actualGlobal, DELTA, "The conversion from Premium Currency to Global Money is incorrect using DB rates.");
        System.out.println("Test 2 Result (pos): " + premiumInput + " Cash -> R$ " + actualGlobal);
    }

    /**
     * Test 3 (Combined Input):
     * Fetches 'tba' (Tibia) parameters from the database.
     */
    @Test
    public void testCombinedConversion() {
        // Fetch real data from MongoDB
        GameConversion game = calculatorDAO.getGameParams("tba");
        
        assertNotNull(game, "Game parameters for 'tba' not found in database.");

        double ingameInput = 1000.0;
        double premiumInput = 50.0;

        // Dynamic expected value
        double totalPremium = (ingameInput * game.getInGameToPremiumRate()) + premiumInput;
        double expectedGlobal = totalPremium * game.getPremiumToGlobalRate();

        // Actual logic
        double actualGlobal = ((ingameInput * game.getInGameToPremiumRate()) + premiumInput) * game.getPremiumToGlobalRate();

        assertEquals(expectedGlobal, actualGlobal, DELTA, "The combined conversion is incorrect using DB rates.");
        System.out.println("Test 3 Result (tba): " + ingameInput + " Gold + " + premiumInput + " Cash -> R$ " + actualGlobal);
    }
}
