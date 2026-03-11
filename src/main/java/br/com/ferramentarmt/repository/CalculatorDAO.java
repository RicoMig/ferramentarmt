package br.com.ferramentarmt.repository;

import br.com.ferramentarmt.config.MongoJDBC;
import br.com.ferramentarmt.model.GameConversion;
import org.bson.Document;
import static com.mongodb.client.model.Filters.eq;

/**
 * Specialized DAO for Calculator operations.
 */
public class CalculatorDAO {
    private static final String COLLECTION_NAME = "game-Param";

    /**
     * Retrieves game conversion parameters from MongoDB based on game code.
     * @param gameCode The acronym for the game (e.g., 'WOW', 'tba')
     * @return A GameConversion object or null if not found.
     */
    public GameConversion getGameParams(String gameCode) {
        Document doc = MongoJDBC.conect().getCollection(COLLECTION_NAME).find(eq("gameCode", gameCode)).first();
        
        if (doc == null) {
            return null;
        }

        GameConversion gc = new GameConversion();
        gc.setGameCode(doc.getString("gameCode"));
        gc.setGameName(doc.getString("gameName") != null ? doc.getString("gameName") : doc.getString("gameCode")); 
        gc.setPremiumToGlobalRate(doc.getDouble("premiumToGlobalRate"));
        gc.setInGameToPremiumRate(doc.getDouble("inGameToPremiumRate"));
        
        return gc;
    }
}
