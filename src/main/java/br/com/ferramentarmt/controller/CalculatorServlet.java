package br.com.ferramentarmt.controller;

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

@WebServlet("/api/calculate")
public class CalculatorServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
        super.init();
        try {
            // Trigger connection initialization
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

        String jsonString = sb.toString();
        JsonObject jsonObject = JsonParser.parseString(jsonString).getAsJsonObject();

        double ingameAmount = jsonObject.get("ingameAmount").getAsDouble();
        double premiumAmount = jsonObject.get("premiumAmount").getAsDouble();



        // Debugging to IDE console
        System.out.println("--- Calculation Request Received ---");
        System.out.println("Game: " + gameName(jsonObject.get("game").getAsString().toLowerCase()));
        System.out.println("In-Game Amount: " + ingameAmount);
        System.out.println("Premium Amount: " + premiumAmount);
        System.out.println("------------------------------------");

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        try (PrintWriter out = resp.getWriter()) {
            out.print("{\"status\": \"success\", \"message\": \"Data received by back-end\"}");
            out.flush();
        }
    }

    private String gameName(String game){
        switch(game){
            case "tba": return "Tibia";
            case "tbm": return "Tibiame";
            case "tof": return "Tales of fearless";
            case "kkl": return "Kakele";
            case "rag": return "Ragnarok Latam";
            case "rat": return "RagnaTrue";
            case "pxg": return "PokeXGame";
            case "pos": return "Pokestone";
        }
        return null;
    }


}
