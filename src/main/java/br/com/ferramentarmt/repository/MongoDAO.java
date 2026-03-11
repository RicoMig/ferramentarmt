package br.com.ferramentarmt.repository;

import br.com.ferramentarmt.config.MongoJDBC;
import com.mongodb.MongoWriteException;
import org.bson.Document;

import java.util.LinkedList;
import java.util.List;

public class MongoDAO {
    public static void insert (String collection, Document document){
        try{
            MongoJDBC.conect().getCollection(collection).insertOne(document);
        }catch(MongoWriteException me){
            if (me.getError().getCode() == 11000) { // Código de erro para índice único duplicado
                System.err.println("Este Documento já existe dentro da Coleção.");
            } else {
                System.err.println("Erro ao criar documento: " + me.getMessage());
            }

        }
    }

    public static void viewAll(String collection){
        for(Document document:  MongoJDBC.conect().getCollection(collection).find()){
            System.out.println(document.toString());
        }
    }

    public static List<Document> search(String collection, Document query){
        List<Document> documentsFound = new LinkedList<>();
        for(Document doc: MongoJDBC.conect().getCollection(collection).find(query)){
            documentsFound.add(doc);
        }
        return documentsFound;
    }

    public static void main(String[] args) {

    }
}
