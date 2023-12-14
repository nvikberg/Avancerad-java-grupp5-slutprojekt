import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.JsonValue;

import java.net.MalformedURLException;
import java.net.URL;
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
    public JsonValue getJsValue() {
        return jsValue;
    }
    public void setJsValue(JsonValue information) {
        this.jsValue = information;
    }

    public static JsonObject getJsObject() {
        return jsObject;
    }

    public void setJsObject(JsonObject jsObject) {
        this.jsObject = jsObject;
    }

    private static JsonObject jsObject;
    private final Random RAND = new Random();
    private String name;
    private JsonValue jsValue;
     static URL imageURL;


    Pokemon() {
        // exempel på hur man kan använda Jsonvalue nu med id
        JsonValue jv = PokeAPI.getRequest(randomId());
        //sparar jsvalue för framtid manipulation, samma med jsonObject
        setJsValue(jv);
        setJsObject(getJsValue().asObject());

        JsonObject jo = getJsObject().get("species").asObject();
        String name = jo.get("name").asString();
        setName(name);
        System.out.println(getName());



    }

    public static URL findSprite(){
        JsonObject sprite = getJsObject().get("sprites").asObject();
        String spriteURL = sprite.get("front_default").asString();

        try {
            imageURL= new URL(spriteURL);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        System.out.println(imageURL);
        return imageURL;
    }

    public int randomId() {

        int id = getRand().nextInt(1000);
        id++;
        return id;

    }

}
