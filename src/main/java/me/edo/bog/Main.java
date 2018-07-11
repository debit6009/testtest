package me.edo.bog;

import lombok.Getter;
import me.edo.bog.handler.Test;
import me.edo.bog.mongo.MongoDB;
import me.edo.bog.mongo.MongoDBD;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    @Getter public static Main instance;
    @Getter private static MongoDB mongoDB;

    @Override
    public void onEnable() {
        instance = this;

        MongoDB mdb = new MongoDB(MongoDBD.username, MongoDBD.password, MongoDBD.database, MongoDBD.host, MongoDBD.port);
        mdb.setDatabase(MongoDBD.database);
        mongoDB = mdb;

        Bukkit.getServer().getPluginManager().registerEvents(new Test(), this);
    }

    @Override
    public void onDisable() {
        getMongoDB().closeConnection();
    }


}
