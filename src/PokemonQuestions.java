import java.util.ArrayList;
import java.util.Random;

public class PokemonQuestions {
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

    public Random getRand() {
        return rand;
    }


    public Boolean getTrueOrFalse() {
        return trueOrFalse;
    }

    public void setTrueOrFalse(Boolean trueOrFalse) {
        this.trueOrFalse = trueOrFalse;
    }

    Boolean trueOrFalse;

    private Random rand;
    PokemonQuestions (ArrayList<Pokemon> pokemons){
        setPokemons(pokemons);
        setTruePokemon(getPokemons().getFirst());
        setFalsePokemon(getPokemons().getLast());
        int i= 0;

        if (i==1){
            trueQuestions();
        }
        else {
            falseQuestions();
        }
        return trueOrFalse;
    }

    //method for asking questions about their moves , not implemented with AL on buttons in GUI yet, only scanner input
    //there are only correct moves as the moment also
    public String trueQuestions () {
        String move = getTruePokemon().getMoveList().getFirst().toUpperCase(); //getting first move from list


        String questionMove = ("True or False, does " + getTruePokemon().getName() +" have this move: " + move + " ?");

       // Scanner scanner = new Scanner(System.in);

       String userResponse = ""; //scanner reads user input for now but this will be deleted once implemented with buttons
       
        boolean isCorrect = isMoveCorrect(move, userResponse); // calling in boolean method


        if(isCorrect){
            System.out.println("correct");
        } else{
            System.out.println("incorrect");
        }


        //return move;
        return questionMove;
    }

    public boolean falseQuestions () {

        int randomNumb = getRand().nextInt(getFalsePokemon().getMoveList().size());
        String type = getFalsePokemon().getTypeList().getFirst().toUpperCase();
        String move = getFalsePokemon().getMoveList().get(randomNumb).toUpperCase();
        String falseType = (getFalsePokemon().getName().toUpperCase() + " is a " + type + " type");
        String falseMove = (getFalsePokemon().getName().toUpperCase() + " has " + move  + " type");
        return false;
    }

    //checks if user response is correct and if the move exists in the move list
    private boolean isMoveCorrect(String actualMove, String userResponse) {
        return userResponse.equals("y") && getTruePokemon().getMoveList().contains(actualMove);
    }
}
