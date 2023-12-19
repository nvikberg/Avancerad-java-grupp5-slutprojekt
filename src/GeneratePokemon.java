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

public class GeneratePokemon {

    private static JsonObject jsObject;
    private Random RAND = new Random();
    private JsonValue jsValue;
    static URL imageURL;

    public Random getRand() {
        return RAND;
    }

    private JsonValue getJsValue() {
        return jsValue;
    }
    private void setJsValue(JsonValue information) {
        this.jsValue = information;
    }

    private static JsonObject getJsObject() {
        return jsObject;
    }

    private void setJsObject(JsonObject jsObject) {
        this.jsObject = jsObject;
    }

    public static ArrayList<Pokemon> getPokemons() {
        return pokemons;
    }

    public void setPokemons(ArrayList<Pokemon> pokemons) {
        this.pokemons = pokemons;
    }

    public static ArrayList<Pokemon> pokemons;

    GeneratePokemon() {
        // exempel på hur man kan använda Jsonvalue nu med id

        //sparar jsvalue för framtid manipulation, samma med jsonObject


        setPokemons(new ArrayList<>()); //create new arraylist to store pokemons

        //for loop to get out 2 random pokemons with their sprite, move and type
        for (int i = 0; i < 2; i++) {
            JsonValue jv = PokeAPI.getRequest(randomId());
            setJsValue(jv);
            setJsObject(getJsValue().asObject());
            JsonObject jo = getJsObject().get("species").asObject();
            String name = jo.get("name").asString();
            //new pokemon egg with info on name, sprite, move and type
            Pokemon egg = new Pokemon(name, findSprite(), findMove(), findType());
            getPokemons().add(egg); // adding created pokemon egg to pokemons list

            //Checks the pokemon list size
            if (getPokemons().size()==2){
                // goes through the first pokemon types
                for (String type:getPokemons().getFirst().getTypeList()){
                    //the type is removed from the last pokemons type list
                    getPokemons().getLast().getTypeList().remove(type);

                }
                //if the last pokemon type list is empty,removes that pokemon and starts over the loop
                if (getPokemons().getLast().getTypeList().isEmpty()){

                    getPokemons().remove(getPokemons().getLast());
                    i = 0;
                    continue;
                }
                //goes through the first pokemon moves
                for (String move:getPokemons().getFirst().getMoveList()){
                    //removes that move if it's in the last pokemon move list
                    getPokemons().getLast().getMoveList().remove(move);
                   // System.out.println(move + " was removed");
                    //System.out.println(getPokemons().getFirst().getMoveList().toString());
                }
                //if the last pokemon move list is empty,removes that pokemon and starts over the loop
                if (getPokemons().getLast().getMoveList().isEmpty()){
                    getPokemons().remove(getPokemons().getLast());
                    i = 0;
                }


            }
        }
        getPokemons().getFirst().pokemonInfo();
        getPokemons().getLast().pokemonInfo();

        //getPokemons().getLast().pokemonInfo();


       //question.moveQuestion();

    }

    //makes a jsonvalue for each index of the typeArray.
    // Then that jsonvalue is turned into an object. Inside that object is the type,
    // so we extract the type and then find its name and put it in a string,
    // that String is then put into a list
    public ArrayList<String> findMove() { //method for finding Abilites in pokemon from json
        //makes a new empty String ArrayList
        ArrayList<String> moveArrayList = new ArrayList<>();
        JsonArray moveArray = getJsObject().get("moves").asArray(); //retriving object to array

        for (JsonValue moveValue:moveArray){
            JsonObject typeObject = moveValue.asObject().get("move").asObject();
            String move = (typeObject.get("name").asString());
            moveArrayList.add(move);
        }

        return moveArrayList;

    }


    //makes a jsonvalue for each index of the typeArray.
    // Then that jsonvalue is turned into an object. Inside that object is the type,
    // so we extract the type and then find its name and put it in a string,
    // that String is then put into a list
        public ArrayList<String> findType() { //method for finding "types" in pokemon from json
            ArrayList<String> typeArrayList = new ArrayList<>();
            JsonArray typeArray = getJsObject().get("types").asArray(); //retriving object to array

                for (JsonValue typeValue:typeArray){
                    JsonObject typeObject = typeValue.asObject().get("type").asObject();
                    String type = (typeObject.get("name").asString());


                    typeArrayList.add(type);
                    /*System.out.println(typeArrayList);*/
                }
                return typeArrayList;
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
