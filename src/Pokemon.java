import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

public class Pokemon {

/*
this class respresents the actual pokemons with their name, sprite(photo), list of moves and list of types. We are
using getter and setters to retrive the values. so this class encapsulates the data related to the pokemon and
info about the pokemon can be used by pokemon info method
*/

    private ArrayList<String> typeList; //list of pokemon types
    private ArrayList<String> moveList; //list of pokemon moves
    private URL spriteURL; //pokemons photo
    private String name; //pokemons name

    public String getName() {
        return name;
    }
    private void setName(String name) {
        this.name = name;
    }
    public ArrayList<String> getTypeList() {
        return typeList;
    }
    private void setTypeList(ArrayList<String> typeList) {
        this.typeList = typeList;
    }
    public ArrayList<String> getMoveList() {
        return moveList;
    }

    public URL getSpriteURL() {
        return spriteURL;
    }

    private void setSpriteURL(URL spriteURL) {
        this.spriteURL = spriteURL;
    }

    private void setMoveList(ArrayList<String> moveList) {
        this.moveList = moveList;
    }

    //constructor to initialise Pokemon object with the provided values for pokemon attributes
    Pokemon(String name,URL spriteURL,ArrayList<String> moveList,  ArrayList<String> typeList ) {
        setName(name);
        setSpriteURL(spriteURL);
        ArrayList<String> f = new ArrayList<>();
        setTypeList(typeList);
        setMoveList(moveList);
    }


    //method for gathering pokemons info thru getting - getName, getMove (only first one from list) and getType
    public void pokemonInfo (){
        System.out.println( getName() + " has this move " + getMoveList().getFirst() + " and is this type " + getTypeList());
    }



}
