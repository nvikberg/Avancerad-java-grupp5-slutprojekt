import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.lang.reflect.Array;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;


public class GUI implements ActionListener {
    JButton buttonTrue, buttonFalse, buttonEndGame;
    JLabel labelWrongAnswer, labelCorrectAnswer, name, type, labelQuestion, labelHeader, labelTime,
            labelSeconds, labelScore, labelSecondsLeft;
    static String databaseUrl = "https://pokeapi.co/api/v2/berry/1/";
    ImageIcon frameIcon = new ImageIcon("frameIcon.png");

    public URL getSpriteURL() {
        return spriteURL;
    }

    public void setSpriteURL(URL spriteURL) {
        this.spriteURL = spriteURL;
    }
    public JLabel getSpriteLabel() {
        return spriteLabel;
    }

    private URL spriteURL;

    int correct_guesses = 0;
    int total_questions = 0;
    int result;
    int seconds = 10;
    String answer;
    JFrame frame = new JFrame();


    private JLabel spriteLabel = new JLabel();
    Pokemon currentPokemon;
    PokemonQuestions question;
    //private PokemonQuestions pokemonQuestions;

   /* Timer pause = new Timer(1000, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            seconds--;//after each second its subracting 1
            labelSeconds.setText(String.valueOf(seconds)); //display
            if (seconds <= 0) { //if timer runs out display will
                results();

            }
        }
    });*/


    GUI() {
        // super();


        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //fonstret stangs
        frame.setTitle("Pokemon Quiz");
        frame.setLayout(null);
        frame.setSize(700, 700);
        frame.getContentPane().setBackground(new Color(5, 42, 68));
        frame.setLocation(300, 150);
        frame.setResizable(false);
        frame.setIconImage(frameIcon.getImage());

        labelHeader = new JLabel("Pokemon Quiz"); //this can hold quiz question
        labelHeader.setFont(new Font("Sans", Font.BOLD, 30));
        labelHeader.setBounds(0, 15, 700, 80);
        labelHeader.setHorizontalAlignment(SwingConstants.CENTER);
        labelHeader.setForeground(new Color(194, 170, 13));

        labelSeconds = new JLabel();
        labelSeconds.setBounds(500, 470, 100, 100);
        labelSeconds.setForeground(new Color(125, 25, 25));
        labelSeconds.setFont(new Font("Verdana", Font.PLAIN, 25));
        // labelSeconds.setOpaque(true);
        labelSeconds.setText(String.valueOf(seconds));
        labelSeconds.setHorizontalAlignment(SwingConstants.CENTER);


        labelTime = new JLabel();
        labelTime.setBounds(500, 420, 100, 100);
        labelTime.setForeground(new Color(205, 25, 25));
        labelTime.setFont(new Font("Verdana", Font.PLAIN, 25));
        labelTime.setText("TIMER");
        labelTime.setHorizontalAlignment(SwingConstants.CENTER);

        labelScore = new JLabel();
        labelScore.setBounds(0, 200, 700, 100);
        labelScore.setBackground(new Color(0, 0, 0));
        labelScore.setForeground(new Color(250, 250, 250));
        labelScore.setFont(new Font("Verdana", Font.PLAIN, 25));
        labelScore.setHorizontalAlignment(SwingConstants.CENTER);

        labelQuestion = new JLabel(""); //this can hold quiz question
        labelQuestion.setFont(new Font("Sans", Font.BOLD, 18));
        labelQuestion.setBounds(0, 100, 700, 80);
        labelQuestion.setBackground(new Color(25, 25, 25));
        labelQuestion.setForeground(new Color(222, 194, 24));
        labelQuestion.setHorizontalAlignment(SwingConstants.CENTER);

        labelCorrectAnswer = new JLabel();
        labelCorrectAnswer.setFont(new Font("Verdana", Font.PLAIN, 16));
        labelCorrectAnswer.setBounds(0, 160, 700, 80);
        labelCorrectAnswer.setBackground(new Color(25, 25, 25));
        labelCorrectAnswer.setForeground(new Color(211, 106, 19));
        labelCorrectAnswer.setHorizontalAlignment(SwingConstants.CENTER);

        name = new JLabel("Pokemon name goes here");
        name.setFont(new Font("Verdana", Font.PLAIN, 18));
        name.setBounds(0, 180, 700, 80);
        name.setBackground(new Color(25, 25, 25));
        name.setForeground(new Color(250, 250, 250));
        name.setHorizontalAlignment(SwingConstants.CENTER);

        type = new JLabel();
        type.setFont(new Font("Verdana", Font.PLAIN, 12));
        type.setBounds(0, 200, 700, 80);
        type.setBackground(new Color(25, 25, 25));
        type.setForeground(new Color(250, 250, 250));
        type.setHorizontalAlignment(SwingConstants.CENTER);

        buttonTrue = new JButton();
        buttonTrue.setBounds(230, 460, 100, 80);
        buttonTrue.setFocusable(false);
        buttonTrue.setFont(new Font("Verdana", Font.BOLD, 18));
        buttonTrue.setText("True");
        buttonTrue.setForeground(new Color(255, 255, 255));
        buttonTrue.setBackground(new Color(30, 29, 29, 255));
        buttonTrue.setBorder(new EtchedBorder());
        buttonTrue.addActionListener(this);

        buttonFalse = new JButton();
        buttonFalse.setBounds(360, 460, 100, 80);
        buttonFalse.setFocusable(false);
        buttonFalse.setFont(new Font("Verdana", Font.BOLD, 18));
        buttonFalse.setText("False");
        buttonFalse.setForeground(new Color(255, 255, 255));
        buttonFalse.setBackground(new Color(30, 29, 29, 255));
        buttonFalse.setBorder(new EtchedBorder());
        buttonFalse.addActionListener(this);

        buttonEndGame = new JButton();
        buttonEndGame.setBounds(300, 560, 100, 50);
        buttonEndGame.setFocusable(false);
        buttonEndGame.setFont(new Font("Verdana", Font.BOLD, 14));
        buttonEndGame.setText("End Game");
        buttonEndGame.setForeground(new Color(255, 255, 255));
        buttonEndGame.setBackground(new Color(30, 29, 29, 255));
        buttonEndGame.setBorder(new EtchedBorder());
        buttonEndGame.addActionListener(this);

/*
        textField.setBackground(new Color(10,100,0));
        JButton button = new JButton("Add text to Fire Base");
        button.addActionListener(e -> {
            try {
                putRequest(textField.getText());
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });
*/
        frame.add(labelScore);
        frame.add(labelHeader);
        frame.add(labelSeconds);
        frame.add(labelTime);

        frame.add(buttonTrue);
        frame.add(buttonFalse);
        frame.add(buttonEndGame);
        frame.add(labelCorrectAnswer);



        new GeneratePokemon();
        PokemonQuestions question = new PokemonQuestions(GeneratePokemon.getPokemons());
        currentPokemon = question.getTruePokemon();
        setSpriteURL(GeneratePokemon.getPokemons().getFirst().getSpriteURL());





        // name.setText(pokemonName);




        newPokemon();
        frame.add(getSpriteLabel());
        frame.add(labelQuestion);
        frame.setVisible(true);
        Scanner scan = new Scanner(System.in);
        System.out.println(GeneratePokemon.getPokemons().toString());
        String u = scan.nextLine();
        reset();
        //nextQuestion(); // Set up the initial question
        //pause.start(); // Start the timer for question change
        // frame.add(name);

    }

    public void newPokemon(){
        new GeneratePokemon();
          question = new PokemonQuestions(GeneratePokemon.getPokemons());
        currentPokemon = question.getTruePokemon();
        setSpriteURL(GeneratePokemon.getPokemons().getFirst().getSpriteURL());

        labelQuestion.setText("True or false! " + question.randomQuestion());
        pokemonSpirte();
    }

    public void pokemonSpirte(){

        try {// access the Pokemon sprite image from GeneratePokemon class in pokemons list

            BufferedImage img = ImageIO.read(getSpriteURL());
            ImageIcon icon = new ImageIcon(img);

            getSpriteLabel().setIcon(icon);
            getSpriteLabel().setBounds(300, 250, 200, 200);

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void reset(){
        newPokemon();
        frame.setVisible(true);
        labelCorrectAnswer.setText("");
    }

    //method for next question //NOT FUNCTIONING CORRECT
    public void nextQuestion() {
        /*Pokemon currentPokemon = pokemonQuestions.getTruePokemon();*/
        // GeneratePokemon.getPokemons().getFirst();
        // String question = pokemonQuestions.randomQuestion();
        // labelQuestion.setText(question);
        // pause.start();
        /*updateSprite(currentPokemon);


        System.out.println(currentPokemon);*/
        // name.setText(currentPokemon.getName());
        // type.setText(currentPokemon.getTypeList().toString());

    }

    //to update sprite photo for each question //NOT FUNCTIONING CORRECT
    private void updateSprite(Pokemon pokemon) {
        try {
            BufferedImage img = ImageIO.read(pokemon.getSpriteURL());
            ImageIcon icon = new ImageIcon(img);

            //sprite.setIcon(icon);

            System.out.println(pokemon.getSpriteURL());

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    //implementing action listneres for buttons, having issues reaching false button
    @Override
    public void actionPerformed(ActionEvent e) {
        // buttonTrue.setEnabled(false);
        //buttonFalse.setEnabled(false);

        JButton clickedButton = (JButton) e.getSource();

        if (clickedButton == buttonTrue) {
            buttonPress(true);


        } else if (clickedButton == buttonFalse) {
            buttonPress(false);

        } else if (clickedButton == buttonEndGame) {
            results();
        }

        //nextQuestion(); //call method for new question

    }

    //timer to change questions
   /* Timer pause = new Timer(2000, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            seconds = 10;
            labelSeconds.setText(String.valueOf(seconds));

            buttonTrue.setBackground(new Color(30, 29, 29, 255));
            buttonFalse.setBackground(new Color(30, 29, 29, 255));

            buttonTrue.setEnabled(true);
            buttonFalse.setEnabled(true);

            nextQuestion(); //call method for new question
        }
    });*/

    // pause.setRepeats(false); //timer only goes once


    //for changing colors when pressed, green correct and red wrong
    private void buttonPress(boolean userAnswer) {

        if (question.getTrueOrFalse() == userAnswer) { //get true or false method from PQ class
            // buttonTrue.setBackground(new Color(0, 200, 0));
            //pause.stop();
            correct_guesses++;
            labelCorrectAnswer.setText("You were correct!");
        } else {
            //  buttonFalse.setBackground(new Color(250, 0, 0));
            labelCorrectAnswer.setText("That's wrong!");
            //pause.stop();

        }
        total_questions++;  //counter for total guesses
    }

    //method for displaying result in the end
    public void results() {
        labelScore.setText("Score " + correct_guesses + " /" + total_questions + " correct guesses");
        //pause.stop();
    }

}




