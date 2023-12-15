/**
 * @authors Sam Danielsson & Nikolina Vikberg
 * @see https://github.com/nvikberg/SlutProjekt_PokemonQuiz.git
 **/

import com.eclipsesource.json.JsonArray;
import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.JsonValue;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Random;

public class Pokemon {

    private static JsonObject jsObject;
    private final Random RAND = new Random();
    private static String name;
    private static String type;
    private static String move;
    private JsonValue jsValue;
    static URL imageURL;

    public Random getRand() {
        return RAND;
    }

    public static String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;

    }

    public static String getMove() {
        return move;
    }

    public void setMove(String move) {
        this.move = move;
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

        findType();
        findMove();

    }
/* //alriks kod
JsonValue jv = Json.parse(response.toString());
JsomObject jo = jv.asObject();
JsonArray ja = jo.get("abilities").asArray();
JsonObject inne = ja.get(0).asObject();
String name = inne.getString("name", "no ability");
sout (name)
 */


    //need to find index in the array to only retrieve the first one
    public void findMove() { //method for finding Abilites in pokemon from json
        JsonArray moveArray = getJsObject().get("moves").asArray(); //retriving object to array
        JsonObject moveObject = moveArray.get(0).asObject().get("move").asObject(); //get index 0 from array
        setMove(moveObject.get("name").asString()); //gettar "name" from "move"
        System.out.println(getMove());
    }


        public void findType() { //method for finding "types" in pokemon from json
            JsonArray typeArray = getJsObject().get("types").asArray(); //retriving object to array
            System.out.println(typeArray.size());
            JsonObject typeObject = typeArray.get(0).asObject().get("type").asObject(); //get index 0 from array
            setType(typeObject.get("type").asString()); //gettar "name" from "move"
           System.out.println(getType());
            }

    // takes the URL for the specific pokemon and returns that URL
    public static URL findSprite(){
        JsonObject sprite = getJsObject().get("sprites").asObject();
        String spriteURL = sprite.get("front_default").asString();

        try {
            imageURL= new URL(spriteURL);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        return imageURL;
    }


    //Randomizes a number between 0 and 1000 and uses that number as
    // an id for the pokemon API so that it can find that pokemon
    //each time user starts the program, a new pokemon gets randomized
    public int randomId() {

        int id = getRand().nextInt(1000);
        id++;
        return id;
    }
}
