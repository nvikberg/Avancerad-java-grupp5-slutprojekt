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
import java.security.KeyStore;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;


public class GUI implements ActionListener {

    JButton buttonTrue, buttonFalse, buttonEndGame;
    JLabel labelCorrectAnswer, name, type, labelQuestion, labelHeader, labelScore, labelTrueOrFalse;
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
    JFrame frame = new JFrame();


    private JLabel spriteLabel = new JLabel();
    Pokemon currentPokemon;
    PokemonQuestions question;

    GUI() {

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

        labelScore = new JLabel();
        labelScore.setBounds(0, 370, 700, 100);
        labelScore.setBackground(new Color(0, 0, 0));
        labelScore.setForeground(new Color(250, 250, 250));
        labelScore.setFont(new Font("Verdana", Font.PLAIN, 15));
        labelScore.setHorizontalAlignment(SwingConstants.CENTER);

        labelTrueOrFalse = new JLabel("True or False");
        labelTrueOrFalse.setFont(new Font("Verdana", Font.PLAIN, 20));
        labelTrueOrFalse.setBounds(0, 120, 700, 80);
        labelTrueOrFalse.setBackground(new Color(25, 25, 25));
        labelTrueOrFalse.setForeground(new Color(211, 106, 19));
        labelTrueOrFalse.setHorizontalAlignment(SwingConstants.CENTER);

        labelQuestion = new JLabel(""); //this can hold quiz question
        labelQuestion.setFont(new Font("Sans", Font.BOLD, 18));
        labelQuestion.setBounds(0, 160, 700, 80);
        labelQuestion.setBackground(new Color(25, 25, 25));
        labelQuestion.setForeground(new Color(222, 194, 24));
        labelQuestion.setHorizontalAlignment(SwingConstants.CENTER);

        labelCorrectAnswer = new JLabel();
        labelCorrectAnswer.setFont(new Font("Verdana", Font.PLAIN, 16));
        labelCorrectAnswer.setBounds(0, 200, 700, 80);
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

        frame.add(labelScore);
        frame.add(labelHeader);
        frame.add(labelTrueOrFalse);

        // frame.add(labelSeconds);
        // frame.add(labelTime);

        frame.add(buttonTrue);
        frame.add(buttonFalse);
        frame.add(buttonEndGame);
        frame.add(labelCorrectAnswer);

        newPokemon(); //new pokemon call
        frame.add(getSpriteLabel());
        frame.add(labelQuestion);
        frame.setVisible(true);

    }

    //generating new pokemon and pokemon question
    public void newPokemon() {
        new GeneratePokemon();
        question = new PokemonQuestions(GeneratePokemon.getPokemons());
        currentPokemon = question.getTruePokemon();
        setSpriteURL(GeneratePokemon.getPokemons().getFirst().getSpriteURL());

        labelQuestion.setText(question.randomQuestion());
        labelScore.setText("Points " + correct_guesses + " / " + total_questions);

        pokemonSprite();

    }

    public void pokemonSprite() {

        try {// access the Pokemon sprite image from GeneratePokemon class in pokemons list

            BufferedImage img = ImageIO.read(getSpriteURL());
            ImageIcon icon = new ImageIcon(img);

            getSpriteLabel().setIcon(icon);
            getSpriteLabel().setBounds(300, 250, 700, 200);

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    //implementing action listneres for buttons
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton clickedButton = (JButton) e.getSource();

        if (clickedButton == buttonTrue) {
            buttonPress(true);

        } else if (clickedButton == buttonFalse) {
            buttonPress(false);

        } else if (clickedButton == buttonEndGame) {
            buttonTrue.setEnabled(false);
            buttonFalse.setEnabled(false);
            results();
        }
    }

    //buttons changing colors when pressed by user, green for correct and red for wrong
    //compares user answer with the correct answer from questions.getTrueOrFalse
    //
    private void buttonPress(boolean userAnswer) {
        //userAnswered=true; //flagging this to use in timer

        if (question.getTrueOrFalse() == userAnswer) { //get true or false method from PQ class
           if (userAnswer) {
               buttonTrue.setBackground(new Color(0, 200, 0));
           } else {
               buttonFalse.setBackground(new Color(0, 200, 0));
           }
            // clickedButton.setBackground(new Color(0, 200, 0));
            correct_guesses++;
            //labelCorrectAnswer.setText("You were correct!");

        } else {
            if (userAnswer) {
                buttonTrue.setBackground(new Color(250, 0, 0));
            } else {
                buttonFalse.setBackground(new Color(250, 0, 0));
            }

           // labelCorrectAnswer.setText("That's wrong!");
        }

        total_questions++;  //counter for total guesses

        // Disable buttons
        buttonTrue.setEnabled(false);
        buttonFalse.setEnabled(false);

        pause.start(); //starting timer for showing correct answer
    }
/*
    //method for marking answers when timer is up without counting guesses
    private void fakeButtonPress() {
//if user doiesnt answer in time, computer shows answer
        boolean correctAnswer = question.getTrueOrFalse();
        if (correctAnswer) {
            buttonTrue.setBackground(new Color(0, 200, 0));
        } else {
            buttonFalse.setBackground(new Color(250, 0, 0));
        }
    }*/


    //delay the colors of the buttons until next question
    Timer pause = new Timer(200, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

            labelCorrectAnswer.setText("");

            reset(); //next question
            pause.stop(); //stop timer on label
        }
    });

    //reset method, new pokemon (question and photo with its attributes)
    //buttons go back to normal color and buttons get enabled
    public void reset() {
        newPokemon();
        buttonTrue.setBackground(new Color(30, 29, 29, 255));
        buttonFalse.setBackground(new Color(30, 29, 29, 255));

        buttonTrue.setEnabled(true);
        buttonFalse.setEnabled(true);
    }

    //method for displaying result in the end
    public void results() {

       labelScore.setText("Score " + correct_guesses + " /" + total_questions + " correct guesses");
       labelScore.setFont(new Font("Verdana", Font.PLAIN, 25));

    }

}

//OPTION TO PLAY AGAIN
//ADD SO YOU CAN SEE THE SCORE ALL THE TIME

//HALF PAGE PA ROSITSAS
//OUR RAPPORT

