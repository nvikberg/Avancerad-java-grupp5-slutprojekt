import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

public class Pokemon {




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
    private ArrayList<String> typeList;
    private ArrayList<String> moveList;
    private URL spriteURL;
    private String name;

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
