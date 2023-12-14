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
    private static String ability;
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
    public static String getAbility(){
        return ability;
    }
    public void setAbility(String ability) {
        this.ability = ability;
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
         findAbility();

    }

    public void findAbility() { //method for finding Abilites in pokemon from json
        JsonArray abilityArray = getJsObject().get("abilities").asArray();//retrives the array value from jsonObject, now abilityArray holds the array of the json value "abilities"
        if (!abilityArray.isEmpty()) { //as long as it's not empty it will run the for loop
            StringBuilder abilityBuilder = new StringBuilder(); //to be able to modify the strings directly
            for (JsonValue abilityValue : abilityArray) { //iterates over each Value in the Array
                JsonObject abilityObject = abilityValue.asObject().get("ability").asObject(); //extracts the value to an object
                String pokemonAbility = abilityObject.get("name").asString(); //extracts "name" and save data to pokemon as a string
                abilityBuilder.append(pokemonAbility).append(" ");
            }
            setAbility(abilityBuilder.toString().trim());
            System.out.println(getAbility());
        } else {
            System.out.println("No abilities found for this Pokemon.");
        }
    }
        public void findType() { //method for finding "types" in pokemon from json
        JsonArray typesArray = getJsObject().get("types").asArray();//retrives the array value from jsonObject, now typesArray holds the array of the json value "types"
        if (!typesArray.isEmpty()) { //as long as it's not empty it will run the for loop
            StringBuilder typeBuilder = new StringBuilder(); //to be able to modify the strings directly
            for (JsonValue typeValue : typesArray) { //iterates over each typevValue in the typeArray
                JsonObject typeObject = typeValue.asObject().get("type").asObject(); //extracts the value "type" to object
                String pokemonType = typeObject.get("name").asString(); //extracts "name" and save to pokemon type as string
                typeBuilder.append(pokemonType).append(" "); //building strings from pokemonTypes + append (space between)
            }
                setType(typeBuilder.toString().trim()); //converts the data from typebuilder to a string and sets it to setType
                System.out.println(getType()); //call getType method to print out in console
            } else {
                    System.out.println("No types found for this Pokemon.");
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
