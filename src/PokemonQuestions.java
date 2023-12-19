import java.util.ArrayList;
import java.util.Random;

public class PokemonQuestions {
    private ArrayList<Pokemon> pokemons;
    private Pokemon truePokemon;
    private Pokemon falsePokemon;
    ///private Random rand;
    private Boolean trueOrFalse; //statement true or false
    private String currentQuestion;
    private boolean correctAnswer; //to store the correct answer
    private Random rand;

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
    public boolean getCorrectAnswer(){
        return correctAnswer;
    }

    PokemonQuestions(ArrayList<Pokemon> pokemons) {
        setPokemons(pokemons);
        setTruePokemon(getPokemons().get(0)); //sets true pokemon as first one in the list
        setFalsePokemon(getPokemons().get(getPokemons().size() - 1)); //sets false pokemon as last one in the list
        rand = new Random(); //initiallize rand object
        int i = 0;

        randomQuestion();
    }
    //method to get random question from true question/false question methods
    //issue because url and name dont follow random so question is only correct sometimes, actually using this as the game now
    public String randomQuestion() {
        Random rand = getRand();
        int randomNumb = rand.nextInt(getPokemons().size());
        Pokemon randomPokemon = getPokemons().get(randomNumb);

        // Use randomPokemon to generate your question
        if (rand.nextBoolean()) {
            return trueQuestions(randomPokemon);
        } else {
            return falseQuestions(randomPokemon);
        }
    }

    public boolean isAnswerCorrect() {
        return correctAnswer;
    }
    public String getCurrentQuestion(){
        return currentQuestion;
    }


/*
        Random rand = getRand();
        int randomNumb = rand.nextInt(2); // 0 is true and 1 is false
        if (randomNumb == 0) {
            return trueQuestions(); //if random generates 0 it returns trueQuestion
        } else
            return falseQuestions();//if random generates 1 it returns falsequestion
*/

      /* *//**//* public String randomQuestion() {
            Random rand = getRand();
            int randomNumb = rand.nextInt(2); // 0 is true and 1 is false
            if (randomNumb == 0) {
                return trueQuestions(); //if random generates 0 it returns trueQuestion
            } else
                return falseQuestions();//if random generates 1 it returns falsequestion
*/

    //method for asking questions about their moves , not implemented with AL on buttons in GUI yet, only scanner input
    private String trueQuestions (Pokemon pokemon) {
        int random = getRand().nextInt(getTruePokemon().getTypeList().size());
        String type = getTruePokemon().getTypeList().getFirst().toUpperCase();
       //String move = getTruePokemon().getMoveList().get(randomNumb).toUpperCase();
        correctAnswer = true;
        return "Is this " + getTruePokemon().getName() + " and is he a " + type + " type?";
     }

    private String falseQuestions (Pokemon pokemon) {
        int random = getRand().nextInt(getFalsePokemon().getTypeList().size());
        String type = getFalsePokemon().getTypeList().getLast().toUpperCase();
       // String move = getFalsePokemon().getMoveList().get(randomNumb).toUpperCase();
        correctAnswer = false;
        return "Is this " + getFalsePokemon().getName() + " and is he a " + type + " type?";
    }

        //checks if user response is correct and if the move exists in the move list
    //private boolean isMoveCorrect(String actualMove, String userResponse) {
    //return userResponse.equals("y") && getTruePokemon().getMoveList().contains(actualMove);
    // }
}
