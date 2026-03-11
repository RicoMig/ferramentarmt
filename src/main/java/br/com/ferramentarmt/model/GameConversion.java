package br.com.ferramentarmt.model;

/**
 * Model class to handle game conversion data between currencies.
 * Maps with MongoDB's 'game-Param' collection.
 */
public class GameConversion {
    private String id; // Maps with MongoDB's _id
    private String gameCode; // 3-letter acronym (e.g., 'WOW', 'RAG')
    private String gameName; // Full name of the game
    private Double premiumToGlobalRate; // Premium Currency (Cash) to Global Money (R$ or US$)
    private Double inGameToPremiumRate; // In-Game Gold/Silver to Premium Currency (Cash)

    // Empty constructor
    public GameConversion() {
    }

    // All-arguments constructor
    public GameConversion(String id, String gameCode, String gameName, Double premiumToGlobalRate, Double inGameToPremiumRate) {
        this.id = id;
        this.gameCode = gameCode;
        this.gameName = gameName;
        this.premiumToGlobalRate = premiumToGlobalRate;
        this.inGameToPremiumRate = inGameToPremiumRate;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGameCode() {
        return gameCode;
    }

    public void setGameCode(String gameCode) {
        this.gameCode = gameCode;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String game) {
        String gameName = null;
        switch(game){
            case "tba": gameName = "Tibia"; break;
            case "tbm": gameName = "Tibiame"; break;
            case "tof": gameName = "Tales of fearless"; break;
            case "kkl": gameName = "Kakele"; break;
            case "rag": gameName = "Ragnarok Latam"; break;
            case "rat": gameName = "RagnaTrue"; break;
            case "pxg": gameName = "PokeXGame"; break;
            case "pos": gameName = "Pokestone"; break;
        }
        this.gameName = gameName;
    }


    public Double getPremiumToGlobalRate() {
        return premiumToGlobalRate;
    }

    public void setPremiumToGlobalRate(Double premiumToGlobalRate) {
        this.premiumToGlobalRate = premiumToGlobalRate;
    }

    public Double getInGameToPremiumRate() {
        return inGameToPremiumRate;
    }

    public void setInGameToPremiumRate(Double inGameToPremiumRate) {
        this.inGameToPremiumRate = inGameToPremiumRate;
    }

    @Override
    public String toString() {
        return "GameConversion{" +
                "id='" + id + '\'' +
                ", gameCode='" + gameCode + '\'' +
                ", gameName='" + gameName + '\'' +
                ", premiumToGlobalRate=" + premiumToGlobalRate +
                ", inGameToPremiumRate=" + inGameToPremiumRate +
                '}';
    }
}
