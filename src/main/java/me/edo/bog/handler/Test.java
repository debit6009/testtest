package me.edo.bog.handler;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import me.edo.bog.Main;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPreLoginEvent;

public class Test implements Listener {

    @EventHandler
    public void preLogin(PlayerPreLoginEvent event) {
        DBCollection table = Main.getInstance().getMongoDB().getDatabase().getCollection("users");
        BasicDBObject searchQuery = new BasicDBObject();
        searchQuery.put("uuid", event.getUniqueId());
        DBCursor result = table.find(searchQuery);

        if(result.hasNext()) {
            BasicDBObject updated = new BasicDBObject();
            updated.putAll(result.next());
            updated.remove("ign");
            updated.put("ign", event.getName());

            BasicDBObject fUpdate = new BasicDBObject();
            fUpdate.put("$put", updated);

            table.update(searchQuery, fUpdate);
        }

        if(!result.hasNext()) {
            BasicDBObject newUser = new BasicDBObject();

            newUser.put("ign", event.getName());
            newUser.put("uuid", event.getUniqueId());
            table.insert(newUser);
        }
    }
}
