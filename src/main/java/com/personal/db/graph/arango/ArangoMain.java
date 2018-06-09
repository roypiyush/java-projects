package com.personal.db.graph.arango;

import com.arangodb.ArangoDB;
import com.arangodb.ArangoDBException;
import com.arangodb.entity.BaseDocument;
import com.arangodb.entity.CollectionEntity;

public class ArangoMain {
    public static void main(String[] args) {

        final ArangoDB arangoDB = new ArangoDB.Builder().user("root").password("root").build();
        final String dbName = "local";
        String collectionName = "firstCollection";

        try {
            arangoDB.createDatabase(dbName);
            System.out.println("Database created: " + dbName);

            CollectionEntity myArangoCollection = arangoDB.db(dbName).createCollection(collectionName);
            System.out.println("Collection created: " + myArangoCollection.getName());

            BaseDocument myObject = new BaseDocument();
            myObject.setKey("myKey");
            myObject.addAttribute("a", "Foo");
            myObject.addAttribute("b", 42);

            arangoDB.db(dbName).collection(collectionName).insertDocument(myObject);
            System.out.println("Document created");

            BaseDocument myDocument = arangoDB.db(dbName).collection(collectionName).getDocument("myKey",
                    BaseDocument.class);
            System.out.println("Get and Print Document\n" + "Key: "
                    + myDocument.getKey() + "Attribute a: "
                    + myDocument.getAttribute("a")
                    + "Attribute b: " + myDocument.getAttribute("b"));
            boolean isDropped = arangoDB.db(dbName).drop();
            System.out.println("DB dropped " + isDropped);
            arangoDB.shutdown();
        } catch (final ArangoDBException e) {
            e.printStackTrace();
        }
    }
}
