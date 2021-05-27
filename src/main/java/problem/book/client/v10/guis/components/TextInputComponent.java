package problem.book.client.v10.guis.components;

import problem.book.client.v10.guis.utilities.Utility;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class TextInputComponent extends JPanel {
    
    JLabel text;
    JTextField input;
    
    public TextInputComponent(String text) {
        this.text = new JLabel(text, SwingConstants.CENTER);
        input = new JTextField();

        init();
    }

    public TextInputComponent(String text, JTextField fieldType) {
        this.text = new JLabel(text, SwingConstants.CENTER);
        input = fieldType;
        init();
    }

    private void init() {

        // Set the preferences
        setLayout(new GridLayout(1, 2));

        setBorder(new EmptyBorder(25, 0, 25, 20));

        this.text.setFont(Utility.getFontText());

        input.setFont(Utility.getFontText());
        input.setPreferredSize(new Dimension(300, 50));


        // Add all the components
        add(this.text);
        add(input);
    }

    public JTextField getInput() {return input;}

    public String getInputText() {
        return input.getText();
    }

    public JLabel getLabelText() {
        return text;
    }
}
