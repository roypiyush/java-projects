package com.personal.db.graph.arango;

import com.arangodb.ArangoDB;
import com.arangodb.ArangoDBException;
import com.arangodb.entity.BaseDocument;
import com.arangodb.entity.CollectionEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ArangoMain {
    private static final Logger LOGGER = LoggerFactory.getLogger(ArangoMain.class);

    public static void main(String[] args) {

        final ArangoDB arangoDB = new ArangoDB.Builder().user("root").password("root").build();
        final String dbName = "local";
        String collectionName = "firstCollection";

        try {
            arangoDB.createDatabase(dbName);
            LOGGER.info("Database Created {}", dbName);

            CollectionEntity myArangoCollection = arangoDB.db(dbName).createCollection(collectionName);
            LOGGER.info("Collection Created {}", myArangoCollection.getName());

            BaseDocument myObject = new BaseDocument();
            myObject.setKey("myKey");
            myObject.addAttribute("a", "Foo");
            myObject.addAttribute("b", 42);

            arangoDB.db(dbName).collection(collectionName).insertDocument(myObject);
            LOGGER.info("Document created {}", myObject.toString());

            BaseDocument myDocument = arangoDB.db(dbName).collection(collectionName).getDocument("myKey",
                    BaseDocument.class);
            LOGGER.info("Get and Print Document {}", myDocument.toString());
            boolean isDropped = arangoDB.db(dbName).drop();
            LOGGER.info("DB dropped " + isDropped);
            arangoDB.shutdown();
        } catch (final ArangoDBException e) {
            e.printStackTrace();
        }
    }
}
