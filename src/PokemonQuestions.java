import java.util.ArrayList;
import java.util.Random;

public class PokemonQuestions {
    private ArrayList<Pokemon> pokemons;
    private Pokemon truePokemon;
    private Pokemon falsePokemon;
    ///private Random rand;
    private Boolean trueOrFalse; //statement true or false
    private Random rand = new Random();

    /* PokemonQuestions class is to ask true or false questions about Pokemon attributes
     *
     * */
    private ArrayList<Pokemon> getPokemons() {
        return pokemons;
    }

    private void setPokemons(ArrayList<Pokemon> pokemons) {
        this.pokemons = pokemons;
    }

    public Pokemon getTruePokemon() {
        return this.truePokemon;
    }

    private void setTruePokemon(Pokemon truePokemon) {
        this.truePokemon = truePokemon;
    }

    public Pokemon getFalsePokemon() {
        return falsePokemon;
    }

    public void setFalsePokemon(Pokemon falsePokemon) {
        this.falsePokemon = falsePokemon;
    }
    public Random getRand() {
        return rand;
    }

    public Boolean getTrueOrFalse() {
        return trueOrFalse;
    }

    public void setTrueOrFalse(Boolean trueOrFalse) {
        this.trueOrFalse = trueOrFalse;
    }

    public ArrayList<String> getQuestionList() {
        return questionList;
    }

    ArrayList<String> questionList = new ArrayList<>();

    PokemonQuestions(ArrayList<Pokemon> pokemons) {
        //Saves pokemon list
        setPokemons(pokemons);
        //saves the pokemon that the user will see
        setTruePokemon(getPokemons().getFirst());
        //saves the pokemon that will be used to lie to the user
        setFalsePokemon(getPokemons().getLast());
        //saves a random number between 1 and 0
        int randomNumb = getRand().nextInt(2);
        System.out.println("RANDOM " + randomNumb);
        //if the number is 1 then it is a true statement and
        // the questions will be based on the pokemon that the users see
        //otherwise it will be false and the questions will be based around the other pokemon
        if (randomNumb ==1){

            generateQuestion(getTruePokemon());
            setTrueOrFalse(true);
        }

        else
        {
            generateQuestion(getFalsePokemon());
            setTrueOrFalse(false);
        }

    }
    //method to get random question from true question/false question methods
    //issue because url and name dont follow random so question is only correct sometimes, actually using this as the game now
    public void generateQuestion(Pokemon pokemon) {
        //randomize a number using the type size same for moveNumber
        int typeNumber = getRand().nextInt(pokemon.getTypeList().size());
        int moveNumber = getRand().nextInt(pokemon.getMoveList().size());
        //Always saves the pokemon name that the user will see,
        String name = getTruePokemon().getName();
        //gets a type using the number from typeNumber. Same for move
        String type =pokemon.getTypeList().get(typeNumber);
        String move = pokemon.getMoveList().get(moveNumber);
        //questions using the strings above
        String moveQuestion = (name + " can use  " +  move + " move");
        String typeQuestion = (name + " is a " + type + " type");
        //saves the questions in a list
        getQuestionList().add(moveQuestion);
        getQuestionList().add(typeQuestion);

        System.out.println(getQuestionList());
        pokemon.pokemonInfo();

    }

    public String randomQuestion(){
        int questionNumber = getRand().nextInt(getQuestionList().size());
        return getQuestionList().get(questionNumber);
    }

}
