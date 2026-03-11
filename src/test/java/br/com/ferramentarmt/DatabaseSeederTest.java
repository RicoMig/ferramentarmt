package br.com.ferramentarmt;

import br.com.ferramentarmt.config.MongoJDBC;
import br.com.ferramentarmt.repository.MongoDAO;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class DatabaseSeederTest {

    public static void main(String[] args) {
        System.out.println("Starting Synchronized Database Seeding...");

        try {
            // Get database connection from MongoJDBC
            MongoDatabase database = MongoJDBC.conect();

            if (database == null) {
                System.err.println("Failed to connect to MongoDB. Ensure the service is running.");
                return;
            }

            System.out.println("Connected to database: " + database.getName());

            // Collection name
            String collectionName = "game-Param";
            MongoCollection<Document> collection = database.getCollection(collectionName);

            // Drop/clear existing collection
            System.out.println("Dropping existing collection: " + collectionName);
            collection.drop();

            // Seed Data synchronized with calculator.jsp
            List<Document> seeds = new ArrayList<>();

            // 1. Tibia
            seeds.add(new Document()
                    .append("gameCode", "tba")
                    .append("gameName", "Tibia")
                    .append("premiumToGlobalRate", 0.15)
                    .append("inGameToPremiumRate", 0.02));

            // 2. Tibiame
            seeds.add(new Document()
                    .append("gameCode", "tbm")
                    .append("gameName", "Tibiame")
                    .append("premiumToGlobalRate", 0.10)
                    .append("inGameToPremiumRate", 0.015));

            // 3. Tales of Fearless
            seeds.add(new Document()
                    .append("gameCode", "tof")
                    .append("gameName", "Tales of Fearless")
                    .append("premiumToGlobalRate", 0.20)
                    .append("inGameToPremiumRate", 0.005));

            // 4. Kakele
            seeds.add(new Document()
                    .append("gameCode", "kkl")
                    .append("gameName", "Kakele")
                    .append("premiumToGlobalRate", 0.044)
                    .append("inGameToPremiumRate", 0.01));

            // 5. Ragnarok Latam
            seeds.add(new Document()
                    .append("gameCode", "rag")
                    .append("gameName", "Ragnarok Latam")
                    .append("premiumToGlobalRate", 0.05)
                    .append("inGameToPremiumRate", 0.001));

            // 6. RagnaTrue
            seeds.add(new Document()
                    .append("gameCode", "rat")
                    .append("gameName", "RagnaTrue")
                    .append("premiumToGlobalRate", 0.08)
                    .append("inGameToPremiumRate", 0.002));

            // 7. PokeXGame
            seeds.add(new Document()
                    .append("gameCode", "pxg")
                    .append("gameName", "PokeXGame")
                    .append("premiumToGlobalRate", 0.30)
                    .append("inGameToPremiumRate", 0.0005));

            // 8. Pokestone
            seeds.add(new Document()
                    .append("gameCode", "pos")
                    .append("gameName", "Pokestone")
                    .append("premiumToGlobalRate", 0.25)
                    .append("inGameToPremiumRate", 0.0008));

            // Insert records
            for (Document doc : seeds) {
                collection.insertOne(doc);
                System.out.println(
                        "Successfully inserted: " + doc.getString("gameName") + " (" + doc.getString("gameCode") + ")");
            }

            System.out.println("Database seeding completed successfully for " + seeds.size() + " games!");
            MongoDAO.viewAll(collectionName);

        } catch (Exception e) {
            System.err.println("An error occurred during seeding: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
