package problem.book.client.v10.guis;

import jdk.jshell.execution.Util;
import problem.book.client.v10.ProblemBookApp;
import problem.book.client.v10.guis.utilities.DatabaseLinker;
import problem.book.client.v10.guis.utilities.Utility;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.Flow;

public class StudentGui extends JFrame {

    private final JTextArea consoleLog = new JTextArea();

    public StudentGui() {
        // Set the name of tha application
        setTitle("Problem Book");

        // Make this window visible
        setVisible(true);

        // Make this window non resizeable
        setResizable(false);

        // Stop the execution of the program when closing this window
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        initGui();

        pack();
        // Make the application fit the screen
        setExtendedState(JFrame.MAXIMIZED_BOTH);
    }

    private void initGui() {
        GridLayout appLayout = new GridLayout(1, 3);
        setLayout(appLayout);

        try {
            displayLeftPanel();
            displayCentralPanel();
            displayRightPanel();
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Private method for drawing the left panel of the application.
     * @since version 1.0
     */
    private void displayLeftPanel() throws IOException {
        GridLayout layout = new GridLayout(3, 1);
        layout.setVgap(100);

        JPanel panelLeft = new JPanel(layout);
        JPanel imagePanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 1000, 5));
        JPanel textPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel buttonPanel = new JPanel(new GridLayout(1, 2));

        BufferedImage bufferedImage = ImageIO.read(new File("C:\\Users\\Adrian\\Desktop\\1.png"));
        JLabel image = new JLabel(new ImageIcon(bufferedImage));
        image.setBorder(BorderFactory.createLineBorder(Color.black, 5));

        JComboBox<String> avatarId = new JComboBox<>();
        addAvatarItems(avatarId);
        avatarId.setFont(new Font("Arial", Font.PLAIN, 25));

        imagePanel.add(image);
        imagePanel.add(avatarId);

        JLabel name = new JLabel("Name: test");
        name.setFont(new Font("Arial", Font.PLAIN, 35));
        name.setBorder(Utility.getTextBorder());

        JLabel registrationNumber = new JLabel("Reg. Number: 1234ABCDEF#0#" );
        registrationNumber.setFont(new Font("Arial", Font.PLAIN, 35));
        registrationNumber.setBorder(Utility.getTextBorder());

        textPanel.add(name);
        textPanel.add(registrationNumber);

        JButton sendEmail = new JButton("Send email");
        sendEmail.setFont(new Font("Arial", Font.BOLD, 50));
        sendEmail.setBorder(BorderFactory.createLineBorder(Color.black, 5));

        JButton logout = new JButton("Log out");
        logout.setFont(new Font("Arial", Font.BOLD, 50));
        logout.setBorder(BorderFactory.createLineBorder(Color.black, 5));

        buttonPanel.add(sendEmail);
        buttonPanel.add(logout);

        textPanel.setBorder(BorderFactory.createLineBorder(Color.black, 5));
        buttonPanel.setBorder(BorderFactory.createLineBorder(Color.black, 5));

        panelLeft.add(imagePanel);
        panelLeft.add(textPanel);
        panelLeft.add(buttonPanel);

        add(panelLeft);
    }

    /**
     * Private method for drawing the central panel of the application.
     * @since version 1.0
     */
    private void displayCentralPanel() {
        JTextArea textField = new JTextArea();

        textField.setEditable(false);
        textField.setText(Utility.getDummyLongText());
        textField.setLineWrap(true);

        textField.setFont(new Font("Arial", Font.PLAIN, 20));
        textField.setBackground(new Color(230, 230, 230));
        textField.setBorder(new EmptyBorder(45, 15, 15, 15));

        JScrollPane scroll = new JScrollPane(textField);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        add(scroll);
    }

    /**
     * Private method for drawing the right side of the main panel.
     * @since version 1.0
     */
    private void displayRightPanel() {
        GridLayout layout = new GridLayout(4, 1);
        layout.setVgap(20);
        JPanel rightPanel = new JPanel(layout);

        JLabel problemId = new JLabel("Problem ID: 0000");
        problemId.setFont(new Font("Arial", Font.PLAIN, 40));
        problemId.setBorder(Utility.getTextBorder());

        JLabel author = new JLabel("Author: Professor X");
        author.setFont(new Font("Arial", Font.PLAIN, 40));
        author.setBorder(Utility.getTextBorder());

        JPanel informationPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        informationPanel.add(problemId);
        informationPanel.add(author);

        JButton displayProblem = new JButton("  Display problem  ");
        displayProblem.setFont(new Font("Arial", Font.BOLD, 40));
        // displayProblem.setBorder(BorderFactory.createLineBorder(Color.red, 5));

        JButton displayHint1 = new JButton("  Display first hint  ");
        displayHint1.setFont(new Font("Arial", Font.BOLD, 40));
        // displayHint1.setBorder(BorderFactory.createLineBorder(Color.green, 5));

        JButton displayHint2 = new JButton("  Display second hint  ");
        displayHint2.setFont(new Font("Arial", Font.BOLD, 40));
        // displayHint2.setBorder(BorderFactory.createLineBorder(Color.green, 5));

        FlowLayout displayLayout = new FlowLayout(FlowLayout.CENTER);
        displayLayout.setVgap(15);
        JPanel displayPanel = new JPanel(displayLayout);
        displayPanel.add(displayProblem);
        displayPanel.add(displayHint1);
        displayPanel.add(displayHint2);

        JLabel searchText = new JLabel("Looking for a specific problem?");
        searchText.setFont(new Font("Arial", Font.PLAIN, 30));

        JButton next = new JButton("Next");
        next.setFont(new Font("Arial", Font.PLAIN, 30));

        JButton previous = new JButton("Previous");
        previous.setFont(new Font("Arial", Font.PLAIN, 30));

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.add(next);
        buttonsPanel.add(previous);

        JLabel searchLabel = new JLabel("Search by id:");
        searchLabel.setFont(new Font("Arial", Font.PLAIN, 30));

        JTextField searchTextField = new JTextField();
        searchTextField.setFont(new Font("Arial", Font.PLAIN, 30));
        searchTextField.setPreferredSize(new Dimension(150, 40));

        JButton searchButton = new JButton("Search");
        searchButton.setFont(new Font("Arial", Font.PLAIN, 30));

        JPanel searchPanel = new JPanel();
        searchPanel.add(searchLabel);
        searchPanel.add(searchTextField);
        searchPanel.add(searchButton);
        // searchPanel.setBorder(BorderFactory.createLineBorder(Color.black, 10));

        FlowLayout searchSettingLayout = new FlowLayout(FlowLayout.CENTER);
        searchSettingLayout.setVgap(20);
        JPanel searchSettingsPanel = new JPanel(searchSettingLayout);
        searchSettingsPanel.add(searchText);
        searchSettingsPanel.add(searchPanel);
        searchSettingsPanel.add(buttonsPanel);

        consoleLog.setEditable(false);
        consoleLog.setFont(new Font("Arial", Font.PLAIN, 20));
        consoleLog.setLineWrap(true);
        consoleLog.setText("Console logs:\n");
        consoleLog.setText(consoleLog.getText() + Utility.getDummyLongText());

        JScrollPane consolePane = new JScrollPane(consoleLog);
        consolePane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        informationPanel.setBorder(BorderFactory.createLineBorder(Color.black, 3));
        displayPanel.setBorder(BorderFactory.createLineBorder(Color.black, 3));
        searchSettingsPanel.setBorder(BorderFactory.createLineBorder(Color.black, 3));
        consolePane.setBorder(BorderFactory.createLineBorder(Color.black, 3));

        rightPanel.add(informationPanel);
        rightPanel.add(displayPanel);
        rightPanel.add(searchSettingsPanel);
        rightPanel.add(consolePane);

        add(rightPanel);
    }

    /**
     * Private method which constructs the avatar ID combo box.
     * @param avatarId the combo box to be constructed
     * @since version 1.0
     */
    private void addAvatarItems(JComboBox<String> avatarId) {
        avatarId.addItem("Image 1");
        avatarId.addItem("Image 2");
        avatarId.addItem("Image 3");
        avatarId.addItem("Image 4");
    }
}
