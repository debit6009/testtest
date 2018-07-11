package me.edo.bog.mongo;

import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;

import java.util.Arrays;

public class MongoDB {

    private MongoClient mongo = null;
    private DB database = null;

    public MongoClient getMongo() {
        if(mongo == null) {
            MongoCredential credential = MongoCredential.createCredential(MongoDBD.username, MongoDBD.database, MongoDBD.password.toCharArray());
            mongo = new MongoClient(new ServerAddress(MongoDBD.host, MongoDBD.port), Arrays.asList(credential));
        }
        return mongo;
    }

    public MongoDB(String username, String password, String database2, String host, int port) {
        MongoCredential credential = MongoCredential.createCredential(username, database2, password.toCharArray());
        mongo = new MongoClient(new ServerAddress(host, port), Arrays.asList(credential));
    }

    public DB getDatabase() {
        if (database == null) {
            database = getMongo().getDB(MongoDBD.database);
        }
        return database;
    }

    public void setDatabase(String db) {
        database = getMongo().getDB(db);
    }

    public void closeConnection() {
        if(mongo != null) {
            mongo.close();
        }
    }
}
