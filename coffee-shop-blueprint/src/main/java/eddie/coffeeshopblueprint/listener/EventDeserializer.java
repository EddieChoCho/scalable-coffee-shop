package eddie.coffeeshopblueprint.listener;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import eddie.coffeeshopblueprint.events.CoffeeEvent;
import org.springframework.stereotype.Component;

import static eddie.coffeeshopblueprint.listener.EventSerializer.CLASSNAME;

@Component
public class EventDeserializer {

    private Gson gson = new Gson();

    public CoffeeEvent deserialize(String input) throws ClassNotFoundException {
        JsonObject json = gson.fromJson(input, JsonObject.class);
        Class<? extends CoffeeEvent> eventClass = this.getEventClass(json);
        CoffeeEvent event = gson.fromJson(input, eventClass);
        return event;
    }

    public Class<? extends CoffeeEvent> getEventClass(JsonObject json) throws ClassNotFoundException {
        String className = json.get(CLASSNAME).getAsString();
        json.remove(CLASSNAME);
        return (Class<? extends CoffeeEvent>) Class.forName(className);
    }
}
