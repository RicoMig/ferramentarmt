package br.com.ferramentarmt.controller;

import br.com.ferramentarmt.model.GameConversion;
import br.com.ferramentarmt.repository.MongoDAO;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import br.com.ferramentarmt.config.MongoJDBC;
import org.bson.Document;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/api/calculate")
public class CalculatorServlet extends HttpServlet {

    private static final Map<String, Double> EXCHANGE_RATES = new HashMap<>();

    static {
        EXCHANGE_RATES.put("BRL", 1.0);  // Base Currency is now BRL
        EXCHANGE_RATES.put("USD", 0.20); // 1 BRL ≈ 0.20 USD
        EXCHANGE_RATES.put("EUR", 0.18); // 1 BRL ≈ 0.18 EUR
    }

    @Override
    public void init() throws ServletException {
        super.init();
        try {
            MongoJDBC.conect();
        } catch (Exception e) {
            System.err.println("Failed to initialize MongoDB connection: " + e.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        StringBuilder sb = new StringBuilder();
        String line;
        try (BufferedReader reader = req.getReader()) {
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        }

        JsonObject responseJson = new JsonObject();
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        try {
            String jsonString = sb.toString();
            JsonObject jsonObject = JsonParser.parseString(jsonString).getAsJsonObject();

            String gameCode = jsonObject.get("game").getAsString();
            double ingameAmount = jsonObject.get("ingameAmount").getAsDouble();
            double premiumAmount = jsonObject.get("premiumAmount").getAsDouble();
            String targetCurrency = jsonObject.get("targetCurrency").getAsString();
            
            // Optional: Custom Market Price override
            double customMarketPrice = jsonObject.has("customMarketPrice") ? jsonObject.get("customMarketPrice").getAsDouble() : 0;

            // 1. Fetch GameConversion from MongoDB
            Document query = new Document("gameCode", gameCode);
            List<Document> results = MongoDAO.search("game-Param", query);

            if (results.isEmpty()) {
                responseJson.addProperty("success", false);
                responseJson.addProperty("message", "Game not found in database: " + gameCode);
            } else {
                Document doc = results.get(0);
                
                // Logic: Use custom market price if provided (> 0), else fallback to DB rate
                double inGameToPremiumRate;
                if (customMarketPrice > 0) {
                    inGameToPremiumRate = 1.0 / customMarketPrice;
                } else {
                    inGameToPremiumRate = doc.getDouble("inGameToPremiumRate");
                }
                
                double premiumToGlobalRate = doc.getDouble("premiumToGlobalRate"); // DB Rate is now treated as BRL-based

                // 2. Math Implementation
                double totalPremiumValue = premiumAmount + (ingameAmount * inGameToPremiumRate);
                double baseGlobalValueBRL = totalPremiumValue * premiumToGlobalRate;

                // 3. Currency Exchange Implementation
                double exchangeRate = EXCHANGE_RATES.getOrDefault(targetCurrency, 1.0);
                double finalValue = baseGlobalValueBRL * exchangeRate;

                // 4. Response Payload
                responseJson.addProperty("success", true);
                responseJson.addProperty("finalValue", finalValue);
                responseJson.addProperty("currencyCode", targetCurrency);
                responseJson.addProperty("premiumEquivalent", totalPremiumValue);
            }

        } catch (Exception e) {
            responseJson.addProperty("success", false);
            responseJson.addProperty("message", "Error during calculation: " + e.getMessage());
            e.printStackTrace();
        }

        try (PrintWriter out = resp.getWriter()) {
            out.print(responseJson.toString());
            out.flush();
        }
    }
}
