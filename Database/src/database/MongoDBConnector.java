package database;

import com.mongodb.client.*;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import java.util.ArrayList;
import java.util.List;

public class MongoDBConnector {
    private static final String CONNECTION_STRING = "mongodb://localhost:27017";
    private static final String DATABASE_NAME = "accountdb";
    private static final String COLLECTION_NAME = "accounts";
    
    private MongoClient mongoClient;
    private MongoDatabase database;
    private MongoCollection<Document> collection;
    
    public MongoDBConnector() {
        try {
            mongoClient = MongoClients.create(CONNECTION_STRING);
            database = mongoClient.getDatabase(DATABASE_NAME);
            collection = database.getCollection(COLLECTION_NAME);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void saveAccount(String id, String money, double interestRate, 
                          String openDate, String firstName, String lastName, 
                          String birthDate, int age) {
        Document document = new Document()
                .append("_id", id)
                .append("money", money)
                .append("interestRate", interestRate)
                .append("openDate", openDate)
                .append("firstName", firstName)
                .append("lastName", lastName)
                .append("birthDate", birthDate)
                .append("age", age);
                
        try {
            collection.insertOne(document);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
    
    public List<Document> getAllAccounts() {
        List<Document> accounts = new ArrayList<>();
        try {
            FindIterable<Document> documents = collection.find();
            for (Document doc : documents) {
                accounts.add(doc);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return accounts;
    }
    
    public Document getAccountById(String id) {
        return collection.find(Filters.eq("_id", id)).first();
    }
    
    public void close() {
        if (mongoClient != null) {
            mongoClient.close();
        }
    }
}