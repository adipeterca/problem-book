package problem.book.client.v10.guis;

import problem.book.client.v10.dtos.RegisterDTO;
import problem.book.client.v10.guis.components.TextInputComponent;
import problem.book.client.v10.guis.utilities.DatabaseLinker;
import problem.book.client.v10.guis.utilities.ReturnCode;
import problem.book.client.v10.guis.utilities.Utility;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URISyntaxException;

public class MainFrame extends JFrame {
    private JLabel errorLabel;

    public MainFrame() {
        super("Problem Book");
        // Make this window visible
        setVisible(true);

        // Make this window non resizeable
        setResizable(false);

        // Stop the execution of the program when closing this window
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // By default, draw the login interface first
        drawLogin();

        // Center this window
        setLocationRelativeTo(null);
    }

    /**
     * Public method for drawing the login GUI.
     */
    public void drawLogin() {
        getContentPane().removeAll();
        setLayout(new GridLayout(6, 1));

        // Title setup
        JLabel title = new JLabel("Welcome! Please log in or register!", SwingConstants.CENTER);
        title.setFont(Utility.getFontText());
        add(title, BorderLayout.PAGE_START);
        title.setBorder(new EmptyBorder(30, 30, 15, 30));

        // Name setup
        TextInputComponent name = new TextInputComponent("Name");
        add(name, BorderLayout.CENTER);

        // Password setup
        TextInputComponent password = new TextInputComponent("Password", new JPasswordField());
        add(password, BorderLayout.CENTER);

        // Login button setup
        JButton login = new JButton("   Login   ");
        login.setFont(Utility.getFontButton());
        login.setBorder(Utility.getButtonBorder());
        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (verifyCredentials(name.getInputText(), password.getInputText())) {
                    errorLabel.setText("Authenticated successfully!");
                }
                else {
                    errorLabel.setText("Error! Credentials are incorrect!");
                }

            }
        });

        JPanel loginPanel = new JPanel();
        loginPanel.setBorder(new EmptyBorder(10, 0, 10, 0));
        loginPanel.add(login);
        add(loginPanel, BorderLayout.CENTER);

        // Register student button setup
        JButton registerStudent = new JButton(" Register as student ");
        registerStudent.setFont(Utility.getFontButton());
        registerStudent.setBorder(Utility.getButtonBorder());
        registerStudent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                drawRegisterStudent();
            }
        });

        // Register teacher button setup
        JButton registerTeacher = new JButton(" Register as teacher ");
        registerTeacher.setFont(Utility.getFontButton());
        registerTeacher.setBorder(Utility.getButtonBorder());

        FlowLayout registerLayout = new FlowLayout();
        registerLayout.setHgap(80);
        JPanel registerPanel = new JPanel();
        registerPanel.setLayout(registerLayout);
        registerPanel.setBorder(new EmptyBorder(0, 20, 20, 20));
        registerPanel.add(registerStudent);
        registerPanel.add(registerTeacher);

        add(registerPanel, BorderLayout.CENTER);

        // Error label setup
        errorLabel = new JLabel("", SwingConstants.CENTER);
        errorLabel.setFont(Utility.getFontError());
        add(errorLabel, BorderLayout.CENTER);

        // Prepare everything for display
        pack();
        repaint();
    }

    public void drawRegisterStudent() {
        getContentPane().removeAll();
        setLayout(new GridLayout(8, 1));

        // Title setup
        JLabel title = new JLabel("Student credentials", SwingConstants.CENTER);
        title.setFont(Utility.getFontText());
        add(title, BorderLayout.PAGE_START);
        title.setBorder(new EmptyBorder(30, 30, 15, 30));

        // Name setup
        TextInputComponent name = new TextInputComponent(" Name ");
        add(name, BorderLayout.CENTER);

        // Registration number setup
        TextInputComponent regNumber = new TextInputComponent(" Reg. Number ");
        add(regNumber, BorderLayout.CENTER);

        // Password setup
        TextInputComponent password = new TextInputComponent(" Password ", new JPasswordField());
        add(password, BorderLayout.CENTER);

        // Password confirmation setup
        TextInputComponent passwordConfirm = new TextInputComponent(" Confirm password ", new JPasswordField());
        add(passwordConfirm, BorderLayout.CENTER);

        // Avatar selection
        JLabel avatarLabel = new JLabel("Avatar");
        avatarLabel.setFont(Utility.getFontText());
        avatarLabel.setBorder(Utility.getButtonBorder());

        JComboBox<String> avatarBox = new JComboBox<>();
        avatarBox.addItem("Blank");
        avatarBox.addItem("Smiley face");

        JPanel avatarPanel = new JPanel();
        avatarPanel.add(avatarLabel);
        avatarPanel.add(avatarBox);

        add(avatarPanel);

        // Register button setup
        JButton register = new JButton("   Register   ");
        register.setFont(Utility.getFontButton());
        register.setBorder(Utility.getButtonBorder());
        register.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Verify that the name is written ok
                if (!verifyName(name.getInputText())) {
                    errorLabel.setText("The name should only contain letters!");
                    return;
                }

                // Verify that the register number is ok
                if (!verifyRegistrationNumber(regNumber.getInputText())) {
                    errorLabel.setText("The registration number is incorrect!");
                    return;
                }

                // Verify that password is at least 5 characters long
                if (password.getInputText().length() < 5) {
                    errorLabel.setText("The password should be at least 5 characters long.");
                    return;
                }

                // Verify that passwords match
                if (!verifyPasswordMatch(password.getInputText(), passwordConfirm.getInputText())) {
                    errorLabel.setText("Passwords do not match. Try again!");
                    return;
                }

                // Register the student
                RegisterDTO registerDTO = new RegisterDTO(name.getInputText(),
                        regNumber.getInputText(),
                        hashPassword(password.getInputText()),
                        avatarBox.getSelectedIndex() + 1);


                Integer returnCode = DatabaseLinker.getInstance().addStudent(registerDTO);
                if (returnCode == null) {
                    errorLabel.setText("Fatal error occurred, please try again later!");
                    return;
                }
                if (returnCode == ReturnCode.studentNameExists) {
                    errorLabel.setText("The name already exists!");
                    return;
                }
                if (returnCode == ReturnCode.studentRegistrationNumberExists) {
                    errorLabel.setText("The registration number already exists!");
                    return;
                }

                errorLabel.setText("Registration successful!");

            }
        });

        JPanel registerPanel = new JPanel();
        registerPanel.setBorder(new EmptyBorder(10, 0, 10, 0));
        registerPanel.add(register);
        add(registerPanel, BorderLayout.CENTER);

        // Error label setup
        errorLabel = new JLabel("", SwingConstants.CENTER);
        errorLabel.setFont(Utility.getFontError());
        add(errorLabel, BorderLayout.CENTER);

        // Prepare everything for display
        pack();
        repaint();
    }

    private boolean verifyCredentials(String name, String password) {
        return name.equals("dummy") && password.equals("dummy");
    }

    private boolean verifyPasswordMatch(String pass1, String pass2) {
        return pass1.equals(pass2);
    }

    /**
     * Verifies that a given registration number is correct.
     * For the sake of simplicity, a correct registration number should have:
     * - a length of 13 characters
     * - the first 4 characters should be digits
     * - the next 6 should be uppercase letters
     * - the last 3 characters should be, in this order, '#0#'
     * @param regNumber the registration number to be verified
     * @return true if it is correct, false otherwise
     */
    private boolean verifyRegistrationNumber(String regNumber) {
        return regNumber.matches("[0-9][0-9][0-9][0-9][A-Z][A-Z][A-Z][A-Z][A-Z][A-Z]#0#");
    }

    /**
     * Verifies that the name contains only letters.
     * @param name the name to be verified
     * @return true if it only contains letters, false otherwise
     */
    private boolean verifyName(String name) {
        return name.matches("[a-zA-Z]+");
    }

    private String hashPassword(String password) {
        return password;
    }
}
