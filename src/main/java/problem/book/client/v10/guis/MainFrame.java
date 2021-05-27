package problem.book.client.v10.guis;

import org.springframework.beans.factory.parsing.Problem;
import problem.book.client.v10.ProblemBookApp;
import problem.book.client.v10.dtos.*;
import problem.book.client.v10.guis.components.TextInputComponent;
import problem.book.client.v10.guis.utilities.DatabaseLinker;
import problem.book.client.v10.guis.utilities.ReturnCode;
import problem.book.client.v10.guis.utilities.Utility;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MainFrame extends JFrame {
    // Components used by both the Login GUI and the Registration GUI
    private final JLabel title = new JLabel("", SwingConstants.CENTER);
    private final TextInputComponent name = new TextInputComponent(" Name ");
    private final TextInputComponent specialField = new TextInputComponent(" Special field ");
    private final TextInputComponent password = new TextInputComponent(" Password ", new JPasswordField());
    private final JLabel errorLabel = new JLabel("", SwingConstants.CENTER);

    // Components used only by the Login GUI
    private final JButton login = new JButton("   Login   ");
    private final JPanel loginPanel = new JPanel();

    private final JButton registerStudent = new JButton(" Register as student ");
    private final JButton registerTeacher = new JButton(" Register as teacher ");
    private final FlowLayout registerLayout = new FlowLayout();
    private final JPanel registerPanel = new JPanel(registerLayout);

    // Components used only by the Registration GUI
    private final TextInputComponent passwordConfirm = new TextInputComponent(" Confirm password ", new JPasswordField());

    private final JLabel avatarLabel = new JLabel(" Avatar ");
    private final JComboBox<String> avatarBox = new JComboBox<>();
    private final JPanel avatarPanel = new JPanel();

    private final JButton register = new JButton("   Register   ");
    private final JButton returnToLogin = new JButton(" Return to login ");

    private final FlowLayout buttonsPanelLayout = new FlowLayout();
    private final JPanel buttonsPanel = new JPanel(buttonsPanelLayout);

    public MainFrame() {
        super("Problem Book");

        // Make this window visible
        setVisible(true);

        // Make this window non resizeable
        setResizable(false);

        // Stop the execution of the program when closing this window
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setupMutualComponents();
        setupLoginComponents();
        setupRegisterComponents();

        // By default, draw the login interface first
        drawLogin();
    }

    public void drawLogin() {
        errorLabel.setText("");
        name.getInput().setText("");
        password.getInput().setText("");
        specialField.getInput().setText("");

        specialField.getLabelText().setText(" Special field ");

        getContentPane().removeAll();
        setLayout(new GridLayout(7, 1));

        title.setText("Welcome! Please log in or register!");

        add(title, BorderLayout.PAGE_START);
        add(name, BorderLayout.CENTER);
        add(specialField, BorderLayout.CENTER);
        add(password, BorderLayout.CENTER);
        add(loginPanel, BorderLayout.CENTER);
        add(registerPanel, BorderLayout.CENTER);
        add(errorLabel, BorderLayout.CENTER);

        // Prepare everything for display
        pack();
        repaint();

        // Center this window
        setLocationRelativeTo(null);
    }

    public void drawRegister(boolean forStudents) {
        errorLabel.setText("");
        name.getInput().setText("");
        password.getInput().setText("");

        getContentPane().removeAll();
        setLayout(new GridLayout(8, 1));

        if (forStudents) {
            title.setText("Register as Student");
            specialField.getLabelText().setText("Reg. Number");
            register.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    registerStudentAction();
                }
            });
        }
        else {
            title.setText("Register as Teacher");
            specialField.getLabelText().setText("Email");
            register.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    registerTeacherAction();
                }
            });
        }

        add(title, BorderLayout.PAGE_START);
        add(name, BorderLayout.CENTER);
        add(specialField, BorderLayout.CENTER);
        add(password, BorderLayout.CENTER);
        add(passwordConfirm, BorderLayout.CENTER);
        add(avatarPanel, BorderLayout.CENTER);
        add(buttonsPanel, BorderLayout.CENTER);
        add(errorLabel, BorderLayout.CENTER);

        // Prepare everything for display
        pack();
        repaint();

        // Center this window
        setLocationRelativeTo(null);
    }

    private void setupMutualComponents() {
        // Title setup
        title.setFont(Utility.getFontText());
        title.setBorder(new EmptyBorder(30, 30, 15, 30));

        // Initialize the Error Label
        errorLabel.setFont(Utility.getFontError());
    }

    private void setupLoginComponents() {
        // Login button setup
        login.setFont(Utility.getFontButton());
        login.setBorder(Utility.getButtonBorder());
        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoggedInDTO loggedInDTO = verifyCredentials(name.getInputText(), password.getInputText());
                if (loggedInDTO != null) {
                    errorLabel.setText("Authenticated successfully!");

                    ProblemBookApp.setLoggedInDTO(loggedInDTO);
                    ProblemBookApp.setIsStudent(verifyRegistrationNumber(specialField.getInputText()));
                    dispose();
                }
                else {
                    errorLabel.setText("Error! Credentials are incorrect!");
                }

            }
        });

        // Login panel setup
        loginPanel.setBorder(new EmptyBorder(10, 0, 10, 0));
        loginPanel.add(login);

        // Register student button setup
        registerStudent.setFont(Utility.getFontButton());
        registerStudent.setBorder(Utility.getButtonBorder());
        registerStudent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                drawRegister(true);
            }
        });

        // Register teacher button setup
        registerTeacher.setFont(Utility.getFontButton());
        registerTeacher.setBorder(Utility.getButtonBorder());
        registerTeacher.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                drawRegister(false);
            }
        });

        // Layout setup
        registerLayout.setHgap(80);

        // Register panel setup
        registerPanel.setBorder(new EmptyBorder(0, 20, 20, 20));
        registerPanel.add(registerStudent);
        registerPanel.add(registerTeacher);
    }

    private void setupRegisterComponents() {
        // Avatar selection
        avatarLabel.setFont(Utility.getFontText());
        avatarLabel.setBorder(Utility.getButtonBorder());

        // Avatar ComboBox setup
        avatarBox.setPreferredSize(Utility.getComboBoxDimension());
        avatarBox.setFont(Utility.getComboBoxFont());
        avatarBox.addItem("Blank");
        avatarBox.addItem("Smiley face");

        // Avatar Panel setup
        avatarPanel.add(avatarLabel);
        avatarPanel.add(avatarBox);

        // Register button setup
        register.setFont(Utility.getFontButton());
        register.setBorder(Utility.getButtonBorder());

        // Return button
        returnToLogin.setFont(Utility.getFontButton());
        returnToLogin.setBorder(Utility.getButtonBorder());
        returnToLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                drawLogin();
            }
        });

        // Register button panel setup
        buttonsPanelLayout.setHgap(50);
        buttonsPanel.setBorder(new EmptyBorder(10, 0, 10, 0));
        buttonsPanel.add(register);
        buttonsPanel.add(returnToLogin);

    }

    private void registerStudentAction() {
        // Verify that all fields are written
        if (name.getInputText().equals("") || specialField.getInputText().equals("") || password.getInputText().equals("") || passwordConfirm.getInputText().equals("")) {
            errorLabel.setText("Please fill out every field!");
            return;
        }

        // Verify that the name contains at least 3 characters
        if (name.getInputText().length() < 3) {
            errorLabel.setText("The name should be at least 3 characters long!");
            return;
        }

        // Verify that the name is written ok
        if (!verifyName(name.getInputText())) {
            errorLabel.setText("The name should only contain letters!");
            return;
        }

        // Verify that the register number is ok
        if (!verifyRegistrationNumber(specialField.getInputText())) {
            errorLabel.setText("The registration number is incorrect!");
            return;
        }

        // Verify that password is at least 5 characters long
        if (password.getInputText().length() < 5) {
            errorLabel.setText("The password should be at least 5 characters long.");
            return;
        }

        // verify that the password is at most 30 characters long
        if (password.getInputText().length() > 30) {
            errorLabel.setText("The password should not exceed 30 characters.");
            return;
        }

        // Verify that passwords match
        if (!verifyPasswordMatch(password.getInputText(), passwordConfirm.getInputText())) {
            errorLabel.setText("Passwords do not match!");
            return;
        }

        errorLabel.setText("One moment...");

        // Register the student
        RegisterDTO registerDTO = new RegisterDTO(name.getInputText(),
                specialField.getInputText(),
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

    private void registerTeacherAction() {
        // Verify that all fields are written
        if (name.getInputText().equals("") || specialField.getInputText().equals("") || password.getInputText().equals("") || passwordConfirm.getInputText().equals("")) {
            errorLabel.setText("Please fill out every field!");
            return;
        }

        // Verify that the name contains at least 3 characters
        if (name.getInputText().length() < 3) {
            errorLabel.setText("The name should be at least 3 characters long!");
            return;
        }

        // Verify that the name is written ok
        if (!verifyName(name.getInputText())) {
            errorLabel.setText("The name should only contain letters!");
            return;
        }

        // Verify that the email is ok
        if (!verifyEmail(specialField.getInputText())) {
            errorLabel.setText("The email is incorrect!");
            return;
        }

        // Verify that password is at least 5 characters long
        if (password.getInputText().length() < 5) {
            errorLabel.setText("The password should be at least 5 characters long.");
            return;
        }

        // verify that the password is at most 30 characters long
        if (password.getInputText().length() > 30) {
            errorLabel.setText("The password should not exceed 30 characters.");
            return;
        }

        // Verify that passwords match
        if (!verifyPasswordMatch(password.getInputText(), passwordConfirm.getInputText())) {
            errorLabel.setText("Passwords do not match!");
            return;
        }

        errorLabel.setText("One moment...");

        // Register the teacher
        RegisterDTO registerDTO = new RegisterDTO(name.getInputText(),
                specialField.getInputText(),
                hashPassword(password.getInputText()),
                avatarBox.getSelectedIndex() + 1);


        Integer returnCode = DatabaseLinker.getInstance().addTeacher(registerDTO);
        if (returnCode == null) {
            errorLabel.setText("Fatal error occurred, please try again later!");
            return;
        }

        if (returnCode == ReturnCode.teacherNameExists) {
            errorLabel.setText("The name already exists!");
            return;
        }

        errorLabel.setText("Registration successful!");
    }

    /**
     * Verifies that the given name and password do exist in the database. Searches either for students (if a valid RN was provided) or for teachers (if a valid email was provided).
     * @param name the name used to log in
     * @param password the password used to log in
     * @return the student/teacher from the database or null if none found
     */
    private LoggedInDTO verifyCredentials(String name, String password) {
        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setName(name);
        loginDTO.setHashPassword(hashPassword(password));
        if (verifyRegistrationNumber(specialField.getInputText())) {
            return DatabaseLinker.getInstance().loginStudent(loginDTO);
        }
        return DatabaseLinker.getInstance().loginTeacher(loginDTO);
    }

    /**
     * Verifies that thw passwords match.
     * @param pass1 the first password
     * @param pass2 the second password
     * @return true if they match, false otherwise
     */
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

    private boolean verifyEmail(String email) {
        return email.matches("[a-zA-Z0-9]+@[a-zA-Z0-9]+.[a-zA-Z0-9]+");
    }

    private String hashPassword(String password) {
        try {
            MessageDigest hasher =  MessageDigest.getInstance("SHA-256");
            byte[] encodedPassword = hasher.digest(password.getBytes(StandardCharsets.UTF_8));
            return bytesToHex(encodedPassword);
        } catch (NoSuchAlgorithmException e) {
            return "no-such-algorithm";
        }
    }

    private static String bytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder(2 * hash.length);
        for (byte b : hash) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }
}
