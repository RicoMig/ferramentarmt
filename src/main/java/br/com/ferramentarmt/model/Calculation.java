package br.com.ferramentarmt.model;

import java.util.Date;

/**
 * Model class for Calculation data.
 */
public class Calculation {
    private String id;
    private String gameName;
    private Double inGameAmount;
    private Double premiumAmount;
    private Date timestamp;

    public Calculation() {}

    public Calculation(String gameName, Double inGameAmount, Double premiumAmount) {
        this.gameName = gameName;
        this.inGameAmount = inGameAmount;
        this.premiumAmount = premiumAmount;
        this.timestamp = new Date();
    }

    // Getters and Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getGameName() { return gameName; }
    public void setGameName(String gameName) { this.gameName = gameName; }

    public Double getInGameAmount() { return inGameAmount; }
    public void setInGameAmount(Double inGameAmount) { this.inGameAmount = inGameAmount; }

    public Double getPremiumAmount() { return premiumAmount; }
    public void setPremiumAmount(Double premiumAmount) { this.premiumAmount = premiumAmount; }

    public Date getTimestamp() { return timestamp; }
    public void setTimestamp(Date timestamp) { this.timestamp = timestamp; }
}
