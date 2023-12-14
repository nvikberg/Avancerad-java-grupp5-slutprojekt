import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.JsonValue;

import java.util.Random;

public class Pokemon {
    public Random getRand() {
        return RAND;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public JsonValue getJSInformation() {
        return jsInformation;
    }
    public void setJSInformation(JsonValue information) {
        this.jsInformation = information;
    }

    public JsonObject getJsObject() {
        return jsObject;
    }

    public void setJsObject(JsonObject jsObject) {
        this.jsObject = jsObject;
    }

    private JsonObject jsObject;
    private final Random RAND = new Random();
    private String name;
    private JsonValue jsInformation;


    Pokemon() {
        // exempel på hur man kan använda Jsonvalue nu med id
        JsonValue jv = PokeAPI.getRequest(randomId());
        //sparar jsvalue för framtid manipulation, samma med jsonObject
        setJSInformation(jv);
        setJsObject(getJSInformation().asObject());

        JsonObject jo = getJsObject().get("species").asObject();
        String name = jo.get("name").asString();
        setName(name);
        System.out.println(getName());

    }

    public int randomId() {

        int id = getRand().nextInt(1292);
        id++;
        return id;

    }

}
