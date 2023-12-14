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
    JLabel labelWrongAnswer, labelCorrectAnswer, name;
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

        labelWrongAnswer = new JLabel("Evil insults/correct answer here");
        labelWrongAnswer.setFont(new Font("Verdana", Font.BOLD, 18));
        labelWrongAnswer.setBounds(230, 25, 700, 80);
        labelWrongAnswer.setBackground(new Color(25, 25, 25));
        labelWrongAnswer.setForeground(new Color(250, 250, 250));

        labelCorrectAnswer = new JLabel("You were correct!");
        labelCorrectAnswer.setFont(new Font("Verdana", Font.BOLD, 18));
        labelCorrectAnswer.setBounds(300, 25, 700, 80);
        labelCorrectAnswer.setBackground(new Color(25, 25, 25));
        labelCorrectAnswer.setHorizontalTextPosition(SwingConstants.CENTER);

        textField = new TextField(); //this can hold quiz question
        textField.setBounds(25, 100, 650, 50);
        textField.setBackground(new Color(25, 25, 25));
        textField.setForeground(new Color(250, 250, 250));
        //textField.setEditable(false);
        textField.setText("Question");

        name = new JLabel("Pokemon Name Here");
        name.setFont(new Font("Verdana", Font.BOLD, 18));
        name.setBounds(230, 180, 300, 80);
        name.setBackground(new Color(25, 25, 25));
        name.setForeground(new Color(250, 250, 250));

        /*textArea = new JTextArea(); //maybe this can hold both name and photo from API.. not sure how it will work yet
        textArea.setBounds(250, 250, 200, 200);
        textArea.setBackground(new Color(25, 25, 25));
        textArea.setForeground(new Color(250, 250, 250));
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setText("API INFO");*/



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

        frame.add(textField);
        /*frame.add(textArea);*/
        frame.add(buttonTrue);
        frame.add(buttonFalse);
        frame.add(labelWrongAnswer);
        //frame.add(labelCorrectAnswer);
        frame.add(name);

        new Pokemon();

        try {
            URL imageURL = Pokemon.findSprite();
            BufferedImage img = ImageIO.read(imageURL);
            ImageIcon icon = new ImageIcon(img);
            JLabel sprite = new JLabel();
            sprite.setIcon(icon);

            sprite.setBounds(300, 250, 700, 200);

            frame.add(sprite);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        frame.setVisible(true);
        //questions();
    }
}
