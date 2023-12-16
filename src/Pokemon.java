/**
 * @authors Sam Danielsson & Nikolina Vikberg
 * @see https://github.com/nvikberg/SlutProjekt_PokemonQuiz.git
 **/

import com.eclipsesource.json.JsonArray;
import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.JsonValue;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
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
        ArrayList<String> moveArrayList = new ArrayList<>();
        JsonArray moveArray = getJsObject().get("moves").asArray(); //retriving object to array

        for (JsonValue moveValue:moveArray){
            JsonObject typeObject = moveValue.asObject().get("move").asObject();
            String move = (typeObject.get("name").asString());
            moveArrayList.add(move);
        }

        System.out.println(moveArrayList + " MOVES ");
        
    }


    //makes a jsonvalue for each index of the typeArray.
    // Then that jsonvalue is turned into an object. Inside that object is the type,
    // so we extract the type and then find its name and put it in a string,
    // that String is then put into a list
        public void findType() { //method for finding "types" in pokemon from json
            ArrayList<String> typeArrayList = new ArrayList<>();
            JsonArray typeArray = getJsObject().get("types").asArray(); //retriving object to array

                for (JsonValue typeValue:typeArray){
                    JsonObject typeObject = typeValue.asObject().get("type").asObject();
                    String type = (typeObject.get("name").asString());
                    typeArrayList.add(type);
                    System.out.println(typeArrayList);
                }
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
