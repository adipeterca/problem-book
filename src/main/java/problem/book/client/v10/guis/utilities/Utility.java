package problem.book.client.v10.guis.utilities;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Utility {
    private final static Font fontText = new Font("Arial", Font.PLAIN, 48);
    private final static Font fontButton = new Font("Arial", Font.PLAIN, 30);
    private final static Font fontError = new Font("Arial", Font.BOLD, 20);

    private static final EmptyBorder buttonBorder = new EmptyBorder(10, 10, 10, 10);
    private static final EmptyBorder textBorder = new EmptyBorder(20, 10, 20, 10);

    private static final Dimension comboBoxDimension = new Dimension(200, 50);
    private static final Font comboBoxFont = new Font("Arial", Font.PLAIN, 30);

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

    public static Dimension getComboBoxDimension() {
        return comboBoxDimension;
    }

    public static Font getComboBoxFont() {
        return comboBoxFont;
    }

    public static EmptyBorder getTextBorder() {
        return textBorder;
    }

    public static String getDummyLongText() {
        return "Lorem ipsum dolor sit amet,\n\n consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretium quis, sem. Nulla consequat massa quis enim. Donec pede justo, fringilla vel, aliquet nec, vulputate eget, arcu. In enim justo, rhoncus ut, imperdiet a, venenatis vitae, justo. Nullam dictum felis eu pede mollis pretium. Integer tincidunt. Cras dapibus. Vivamus elementum semper nisi. Aenean vulputate eleifend tellus. Aenean leo ligula, porttitor eu, consequat vitae, eleifend ac, enim. Aliquam lorem ante, dapibus in, viverra quis, feugiat a, tellus. Phasellus viverra nulla ut metus varius laoreet. Quisque rutrum. Aenean imperdiet. Etiam ultricies nisi vel augue. Curabitur ullamcorper ultricies nisi. Nam eget dui. Etiam rhoncus. Maecenas tempus, tellus eget condimentum rhoncus, sem quam semper libero, sit amet adipiscing sem neque sed ipsum. Nam quam nunc, blandit vel, luctus pulvinar, hendrerit id, lorem. Maecenas nec odio et ante tincidunt tempus. Donec vitae sapien ut libero venenatis faucibus. Nullam quis ante. Etiam sit amet orci eget eros faucibus tincidunt. Duis leo. Sed fringilla mauris sit amet nibh. Donec sodales sagittis magna. Sed consequat, leo eget bibendum sodales, augue velit cursus nunc, Lorem ipsum dolor sit amet,\n\n consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretium quis, sem. Nulla consequat massa quis enim. Donec pede justo, fringilla vel, aliquet nec, vulputate eget, arcu. In enim justo, rhoncus ut, imperdiet a, venenatis vitae, justo. Nullam dictum felis eu pede mollis pretium. Integer tincidunt. Cras dapibus. Vivamus elementum semper nisi. Aenean vulputate eleifend tellus. Aenean leo ligula, porttitor eu, consequat vitae, eleifend ac, enim. Aliquam lorem ante, dapibus in, viverra quis, feugiat a, tellus. Phasellus viverra nulla ut metus varius laoreet. Quisque rutrum. Aenean imperdiet. Etiam ultricies nisi vel augue. Curabitur ullamcorper ultricies nisi. Nam eget dui. Etiam rhoncus. Maecenas tempus, tellus eget condimentum rhoncus, sem quam semper libero, sit amet adipiscing sem neque sed ipsum. Nam quam nunc, blandit vel, luctus pulvinar, hendrerit id, lorem. Maecenas nec odio et ante tincidunt tempus. Donec vitae sapien ut libero venenatis faucibus. Nullam quis ante. Etiam sit amet orci eget eros faucibus tincidunt. Duis leo. Sed fringilla mauris sit amet nibh. Donec sodales sagittis magna. Sed consequat, leo eget bibendum sodales, augue velit cursus nunc,";
    }

    /**
     * Private method which constructs the avatar ID combo box.
     * @param avatarId the combo box to be constructed
     * @since version 1.0
     */
    public static void addAvatarItems(JComboBox<String> avatarId) {
        avatarId.addItem("Image 1");
        avatarId.addItem("Image 2");
        avatarId.addItem("Image 3");
        avatarId.addItem("Image 4");
    }
}
