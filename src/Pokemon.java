import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

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
    Pokemon(String name,URL spriteURL,ArrayList<String> moveList,  ArrayList<String> typeList ) {
        setName(name);
        setSpriteURL(spriteURL);
        setTypeList(typeList);
        setMoveList(moveList);

        moveQuestion();


    }


    //method for gathering pokemons info thru getting - getName, getMove (only first one from list) and getType
    public void pokemonInfo (){
        System.out.println( getName() + " has this move " + getMoveList().getFirst() + " and is this type " + getTypeList());
    }

    //method for asking questions about their moves , not implemented with AL on buttons in GUI yet, only scanner input
    //there are only correct moves as the moment also
    public void moveQuestion () {
        String move = getMoveList().getFirst(); //getting first move from list
        System.out.println("does " + getName() +"have this move? " + move + " type y/n for answer");
        Scanner scanner = new Scanner(System.in);
        String userResponse =scanner.next(); //scanner reads user input for now but this will be deleted once implemented with buttons
        boolean isCorrect = isMoveCorrect(move, userResponse); // calling in boolean method

        if(isCorrect){
            System.out.println("correct");
        } else{
            System.out.println("incorrect");
        }


    }

    //checks if user response is correct and if the move exists in the move list
    private boolean isMoveCorrect(String actualMove, String userResponse) {
        return userResponse.equals("y") && getMoveList().contains(actualMove);
    }

}
