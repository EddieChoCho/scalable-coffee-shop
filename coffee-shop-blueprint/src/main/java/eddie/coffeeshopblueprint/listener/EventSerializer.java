package eddie.coffeeshopblueprint.listener;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import eddie.coffeeshopblueprint.events.CoffeeEvent;
import org.springframework.stereotype.Component;

@Component
public class EventSerializer {

    public static final String CLASSNAME = "classname";
    private Gson gson = new Gson();

    public String serialize(CoffeeEvent event){
        String jsonString = gson.toJson(event);
        JsonObject json = gson.fromJson(jsonString, JsonObject.class);
        json.addProperty(CLASSNAME, event.getClass().getName());
        return gson.toJson(json);
    }
}
