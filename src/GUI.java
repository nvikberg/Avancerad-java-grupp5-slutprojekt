import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;


public class GUI {
    static TextField textField;
    static JTextArea textArea;
    JButton buttonTrue, buttonFalse;
    JLabel labelWrongAnswer, labelCorrectAnswer, name, labelQuestion;
    static String databaseUrl = "https://pokeapi.co/api/v2/berry/1/";

    GUI() {
        // super();

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //fonstret stangs
        frame.setTitle("Pokemon Quiz");
        frame.setLayout(null);
        frame.setSize(700, 700);
        frame.getContentPane().setBackground(new Color(5, 42, 68));
        frame.setLocation(300, 150);
        frame.setResizable(false);

        labelQuestion = new JLabel("QUIZ QUESTION"); //this can hold quiz question
        labelQuestion.setFont(new Font("Sans", Font.BOLD, 18));
        labelQuestion.setBounds(0, 25, 700, 80);
        labelQuestion.setBackground(new Color(25, 25, 25));
        labelQuestion.setForeground(new Color(222, 194, 24));
        labelQuestion.setHorizontalAlignment(SwingConstants.CENTER);

        labelWrongAnswer = new JLabel("Evil insults/correct answer here");
        labelWrongAnswer.setFont(new Font("Verdana", Font.PLAIN, 18));
        labelWrongAnswer.setBounds(200, 100, 700, 80);
        labelWrongAnswer.setBackground(new Color(25, 25, 25));
        labelWrongAnswer.setForeground(new Color(211, 106, 19));
        labelWrongAnswer.setHorizontalTextPosition(SwingConstants.CENTER);

        labelCorrectAnswer = new JLabel("You were correct!");
        labelCorrectAnswer.setFont(new Font("Verdana", Font.BOLD, 18));
        labelCorrectAnswer.setBounds(0, 100, 700, 80);
        labelCorrectAnswer.setBackground(new Color(25, 25, 25));
        labelCorrectAnswer.setForeground(new Color(211, 106, 19));
        labelCorrectAnswer.setHorizontalTextPosition(SwingConstants.CENTER);

        name = new JLabel("Pokemon name goes here");
        name.setFont(new Font("Verdana", Font.PLAIN, 18));
        name.setBounds(0, 180, 700, 80);
        name.setBackground(new Color(25, 25, 25));
        name.setForeground(new Color(250, 250, 250));
        name.setHorizontalAlignment(SwingConstants.CENTER);

        buttonTrue = new JButton();
        buttonTrue.setBounds(230, 500, 100, 80);
        buttonTrue.setFocusable(false);
        buttonTrue.setFont(new Font("Verdana", Font.BOLD, 20));
        buttonTrue.setText("True");

        buttonFalse = new JButton();
        buttonFalse.setBounds(360, 500, 100, 80);
        buttonFalse.setFocusable(false);
        buttonFalse.setFont(new Font("Verdana", Font.BOLD, 18));
        buttonFalse.setText("False");

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

        frame.add(labelQuestion);
        /*frame.add(textArea);*/
        frame.add(buttonTrue);
        frame.add(buttonFalse);
        frame.add(labelWrongAnswer);
        //frame.add(labelCorrectAnswer);
        frame.add(name);

        new Pokemon();


        //adding name and type for random pokemon in GUI label
        name.setText(Pokemon.getName().toUpperCase());

        try {// access the Pokemon sprite image from pokemon class
            URL imageURL = Pokemon.findSprite();
            BufferedImage img = ImageIO.read(imageURL);
            ImageIcon icon = new ImageIcon(img);
            JLabel sprite = new JLabel();
            sprite.setIcon(icon);
            int width = 200;
            int height = 200;
            sprite.setBounds(300,250, width, height);

           // sprite.setBounds(300, 250, 700, 200);

            frame.add(sprite);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        frame.setVisible(true);
        //questions();
    }
}
