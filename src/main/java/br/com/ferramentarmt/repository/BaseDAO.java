package br.com.ferramentarmt.repository;

import com.mongodb.client.MongoCollection;
import br.com.ferramentarmt.config.MongoJDBC;
import org.bson.Document;

/**
 * Base generic DAO class providing common MongoDB collection access.
 */
public abstract class BaseDAO<T> {

    //protected final MongoCollection<Document> collection;

    protected BaseDAO(String collectionName) {
        //this.collection = MongoJDBC.getDatabase().getCollection(collectionName);
    }
    
    // Future generic operations like findById, save, delete can be added here
}
