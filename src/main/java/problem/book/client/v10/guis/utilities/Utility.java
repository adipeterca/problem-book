package problem.book.client.v10.guis.utilities;

import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Utility {
    private final static Font fontText = new Font("Arial", Font.PLAIN, 48);
    private final static Font fontButton = new Font("Arial", Font.PLAIN, 30);
    private final static Font fontError = new Font("Arial", Font.BOLD, 20);

    private static final EmptyBorder buttonBorder = new EmptyBorder(10, 10, 10, 10);

    public static Font getFontText() {
        return fontText;
    }

    public static Font getFontButton() {
        return fontButton;
    }

    public static EmptyBorder getButtonBorder() {
        return buttonBorder;
    }

    public static Font getFontError() {
        return fontError;
    }
}
