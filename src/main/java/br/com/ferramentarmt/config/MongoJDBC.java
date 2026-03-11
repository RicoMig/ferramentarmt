/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ferramentarmt.config;

import com.mongodb.MongoClientSettings;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import java.util.Arrays;

/**
 *
 * @author ricomig
 */
public class MongoJDBC {
    private static MongoDatabase trabalho() {
        MongoDatabase database = null;

        MongoCredential credential = MongoCredential.createCredential("EnricoMigliorini", "enrico", new String("EM200300").toCharArray());

        MongoClientSettings settings = MongoClientSettings.builder().applyToClusterSettings(builder -> builder.hosts(Arrays.asList(new ServerAddress("172.20.7.146", 27017))))
                .credential(credential).build();

        try {

            MongoClient mongoClient = MongoClients.create(settings);
            return database = mongoClient.getDatabase("enrico");

        } catch (Exception e) {

            e.printStackTrace();
            return null;
        }

    }
    private static MongoDatabase notebook() {
        MongoDatabase database = null;
        try {
            MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017/");
            database = mongoClient.getDatabase("DespesasDB");

            System.out.println("Conexão ao MongoDB estabelecida com sucesso!");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return database;
    }
    public static MongoDatabase conect() {
        String username = System.getProperty("user.name");
        switch (username) {
            case "naquta":
                return notebook();
            case "migri":
                return trabalho();
            default:
                return null;

        }
    }

    public static void main(String[] args) {
        System.out.println(System.getProperty("user.name"));
    }
}