import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;


public class GUI {
    JButton buttonTrue, buttonFalse;
    JLabel labelWrongAnswer, labelCorrectAnswer, name, type, labelQuestion, labelHeader;
    static String databaseUrl = "https://pokeapi.co/api/v2/berry/1/";
    ImageIcon frameIcon = new ImageIcon("frameIcon.png");

    public URL getSpriteURL() {
        return spriteURL;
    }

    public void setSpriteURL(URL spriteURL) {
        this.spriteURL = spriteURL;
    }

    private URL spriteURL;
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
        frame.setIconImage(frameIcon.getImage());

        labelHeader = new JLabel("Pokemon Quiz"); //this can hold quiz question
        labelHeader.setFont(new Font("Sans", Font.BOLD, 30));
        labelHeader.setBounds(0, 15, 700, 80);
        labelHeader.setHorizontalAlignment(SwingConstants.CENTER);
        labelHeader.setForeground(new Color(194, 170, 13));

        labelQuestion = new JLabel("Quiz questions will pop up here"); //this can hold quiz question
        labelQuestion.setFont(new Font("Sans", Font.BOLD, 18));
        labelQuestion.setBounds(0, 100, 700, 80);
        labelQuestion.setBackground(new Color(25, 25, 25));
        labelQuestion.setForeground(new Color(222, 194, 24));
        labelQuestion.setHorizontalAlignment(SwingConstants.CENTER);

        labelWrongAnswer = new JLabel("Evil insults/correct answer here");
        labelWrongAnswer.setFont(new Font("Verdana", Font.PLAIN, 16));
        labelWrongAnswer.setBounds(0, 130, 700, 80);
        labelWrongAnswer.setBackground(new Color(25, 25, 25));
        labelWrongAnswer.setForeground(new Color(211, 106, 19));
        labelWrongAnswer.setHorizontalAlignment(SwingConstants.CENTER);

        labelCorrectAnswer = new JLabel("You were correct!");
        labelCorrectAnswer.setFont(new Font("Verdana", Font.PLAIN, 16));
        labelCorrectAnswer.setBounds(0, 160, 700, 80);
        labelCorrectAnswer.setBackground(new Color(25, 25, 25));
        labelCorrectAnswer.setForeground(new Color(211, 106, 19));
        labelCorrectAnswer.setHorizontalAlignment(SwingConstants.CENTER);;

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

        buttonFalse = new JButton();
        buttonFalse.setBounds(360, 460, 100, 80);
        buttonFalse.setFocusable(false);
        buttonFalse.setFont(new Font("Verdana", Font.BOLD, 18));
        buttonFalse.setText("False");
        buttonFalse.setForeground(new Color(255, 255, 255));
        buttonFalse.setBackground(new Color(30, 29, 29, 255));
        buttonFalse.setBorder(new EtchedBorder());

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
        frame.add(labelHeader);
        frame.add(labelQuestion);
        frame.add(buttonTrue);
        frame.add(buttonFalse);
        frame.add(labelWrongAnswer);
        //frame.add(labelCorrectAnswer);


        new GeneratePokemon();
        setSpriteURL(GeneratePokemon.getPokemons().getLast().getSpriteURL());
        name.setText(GeneratePokemon.getPokemons().getLast().getName());


        //calling for name and type for random pokemon from the Pokemon class into GUI labels
        /*name.setText(GeneratePokemon.getName().toUpperCase());
        type.setText(GeneratePokemon.getType());*/

        try {// access the Pokemon sprite image from pokemon class

            BufferedImage img = ImageIO.read(getSpriteURL());
            ImageIcon icon = new ImageIcon(img);
            JLabel sprite = new JLabel();
            sprite.setIcon(icon);
            sprite.setBounds(300,250, 200, 200);
            frame.add(sprite);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        frame.add(name);
        frame.add(type);
        frame.setVisible(true);
    }
}
