package com.baeldung.mongo.collectionexistence;

import java.util.ArrayList;

import org.bson.Document;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class CollectionExistence {

    private static MongoClient mongoClient;

    private static String testCollectionName;
    private static String databaseName;

    public static void setUp() {
        if (mongoClient == null) {
            mongoClient = MongoClients.create("mongodb://localhost:27017");
        }
        databaseName = "baeldung";
        testCollectionName = "student";
    }

    public static void collectionExistsSolution() {

        MongoDatabase db = mongoClient.getDatabase(databaseName);

        System.out.println("collectionName " + testCollectionName + db.listCollectionNames().into(new ArrayList<>()).contains(testCollectionName));

    }

    public static void createCollectionSolution() {

        MongoDatabase database = mongoClient.getDatabase(databaseName);

        try {
            database.createCollection(testCollectionName);

        } catch (Exception exception) {
            System.err.println("Collection already Exists");
        }

    }

    public static void listCollectionNamesSolution() {

        MongoDatabase database = mongoClient.getDatabase(databaseName);
        boolean collectionExists = database.listCollectionNames()
            .into(new ArrayList<String>())
            .contains(testCollectionName);

        System.out.println("collectionExists:- " + collectionExists);

    }

    public static void countSolution() {

        MongoDatabase database = mongoClient.getDatabase(databaseName);

        MongoCollection<Document> collection = database.getCollection(testCollectionName);

        System.out.println(collection.countDocuments());

    }

    public static void main(String args[]) {

        //
        // Connect to cluster (default is localhost:27017)
        //
        setUp();

        //
        // Check the db existence using DB class's method
        //
        collectionExistsSolution();

        //
        // Check the db existence using the createCollection method of MongoDatabase class
        //
        createCollectionSolution();

        //
        // Check the db existence using the listCollectionNames method of MongoDatabase class
        //
        listCollectionNamesSolution();

        //
        // Check the db existence using the count method of MongoDatabase class
        //
        countSolution();

    }

}


