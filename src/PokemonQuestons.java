import java.util.ArrayList;
import java.util.Scanner;

public class PokemonQuestons {
    private ArrayList<Pokemon> getPokemons() {
        return pokemons;
    }
    private void setPokemons(ArrayList<Pokemon> pokemons) {
        this.pokemons = pokemons;
    }
    private Pokemon getTruePokemon(){
        return this.truePokemon;
    }
    private void setTruePokemon(Pokemon truePokemon){
        this.truePokemon =truePokemon;
    }
    public Pokemon getFalsePokemon() {
        return falsePokemon;
    }
    public void setFalsePokemon(Pokemon falsePokemon) {
        this.falsePokemon = falsePokemon;
    }
    private Pokemon truePokemon;
    private Pokemon falsePokemon;
    private ArrayList<Pokemon> pokemons;
    PokemonQuestons(ArrayList<Pokemon> pokemons){
        setPokemons(pokemons);
        setTruePokemon(getPokemons().getLast());
        setFalsePokemon(getPokemons().getFirst());
    }

    //method for asking questions about their moves , not implemented with AL on buttons in GUI yet, only scanner input
    //there are only correct moves as the moment also
    public void moveQuestion () {
        String move =getTruePokemon().getMoveList().getFirst(); //getting first move from list

        System.out.println(getTruePokemon().getName() +"have this move? " + move + " type y/n for answer");
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
        return userResponse.equals("y") && getTruePokemon().getMoveList().contains(actualMove);
    }
}
