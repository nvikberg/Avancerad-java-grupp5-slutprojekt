import java.net.URL;
import java.util.ArrayList;

public class Pokemon {




    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public ArrayList<String> getTypeList() {
        return typeList;
    }
    public void setTypeList(ArrayList<String> typeList) {
        this.typeList = typeList;
    }
    public ArrayList<String> getMoveList() {
        return moveList;
    }

    public URL getSpriteURL() {
        return spriteURL;
    }

    public void setSpriteURL(URL spriteURL) {
        this.spriteURL = spriteURL;
    }

    public void setMoveList(ArrayList<String> moveList) {
        this.moveList = moveList;
    }
    private ArrayList<String> typeList;
    private ArrayList<String> moveList;
    private URL spriteURL;
    private String name;
    Pokemon(String name,URL spriteURL,ArrayList<String> moveList,  ArrayList<String> typeList ){
        setName(name);
        setSpriteURL(spriteURL);
        setTypeList(typeList);
        setMoveList(moveList);
    }

    public void pokemonInfo (){
        System.out.println("Pokemon " + getName() + " has " + getMoveList().getFirst() + " and is " + getTypeList());
    }

}
