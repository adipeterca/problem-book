package problem.book.client.v10.guis;

import problem.book.client.v10.ProblemBookApp;
import problem.book.client.v10.dtos.LoggedInDTO;
import problem.book.client.v10.dtos.ProblemDTO;
import problem.book.client.v10.guis.utilities.DatabaseLinker;
import problem.book.client.v10.guis.utilities.Utility;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class TeacherGui extends JFrame {
    private final JTextArea consoleLog = new JTextArea();
    private final LoggedInDTO teacher = ProblemBookApp.getLoggedInDTO();

    private final JTextArea textArea = new JTextArea();
    private final JTextArea hint1Text = new JTextArea();
    private final JTextArea hint2Text = new JTextArea();

    public TeacherGui() {
        // Set the name of tha application
        setTitle("Problem Book - Teacher");

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

    private void displayLeftPanel() throws IOException {
        GridLayout layout = new GridLayout(3, 1);
        layout.setVgap(100);

        JPanel panelLeft = new JPanel(layout);
        JPanel imagePanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 1000, 5));
        JPanel textPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel buttonPanel = new JPanel(new GridLayout(1, 2));

        BufferedImage bufferedImage = ImageIO.read(new File("C:\\Users\\Adrian\\Desktop\\" + teacher.getAvatarId() + ".png"));
        JLabel image = new JLabel(new ImageIcon(bufferedImage));
        image.setBorder(BorderFactory.createLineBorder(Color.black, 5));

        JComboBox<String> avatarId = new JComboBox<>();
        Utility.addAvatarItems(avatarId);
        avatarId.setFont(new Font("Arial", Font.PLAIN, 25));
        avatarId.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int position = avatarId.getSelectedIndex() + 1;
                DatabaseLinker.getInstance().updateAvatar(true, teacher.getId(), position);
            }
        });

        imagePanel.add(image);
        imagePanel.add(avatarId);

        JLabel name = new JLabel("Name: teacher test");
        name.setFont(new Font("Arial", Font.PLAIN, 35));
        name.setBorder(Utility.getTextBorder());

        JLabel registrationNumber = new JLabel("Email: teacher@teacher.com" );
        registrationNumber.setFont(new Font("Arial", Font.PLAIN, 35));
        registrationNumber.setBorder(Utility.getTextBorder());

        textPanel.add(name);
        textPanel.add(registrationNumber);

        JButton addProblem = new JButton("Add");
        addProblem.setFont(new Font("Arial", Font.BOLD, 50));
        addProblem.setBorder(BorderFactory.createLineBorder(Color.black, 5));
        addProblem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (textArea.getText().length() < 10) {
                    appendConsoleLog("Error! The problem needs \nto have at least 10 characters.");
                    return;
                }
                if (hint1Text.getText().length() == 0) {
                    appendConsoleLog("Error! Hint 1 cannot be empty!");
                    return;
                }
                if (hint2Text.getText().length() == 0) {
                    appendConsoleLog("Error! Hint 2 cannot be empty!");
                    return;
                }

                ProblemDTO problemDTO = new ProblemDTO(teacher.getId(), teacher.getEmail(), textArea.getText(), hint1Text.getText(), hint2Text.getText());
                int id = DatabaseLinker.getInstance().addProblem(problemDTO);
                appendConsoleLog("Added problem with ID " + id);
            }
        });

        JButton logout = new JButton("Log out");
        logout.setFont(new Font("Arial", Font.BOLD, 50));
        logout.setBorder(BorderFactory.createLineBorder(Color.black, 5));
        logout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ProblemBookApp.setLoggedInDTO(null);
                dispose();
            }
        });

        buttonPanel.add(addProblem);
        buttonPanel.add(logout);

        textPanel.setBorder(BorderFactory.createLineBorder(Color.black, 5));
        buttonPanel.setBorder(BorderFactory.createLineBorder(Color.black, 5));

        panelLeft.add(imagePanel);
        panelLeft.add(textPanel);
        panelLeft.add(buttonPanel);

        add(panelLeft);
    }

    private void displayCentralPanel() {
        JLabel title = new JLabel("Enter the problem's text:");
        title.setBorder(new EmptyBorder(20, 10, 20, 10));
        title.setFont(Utility.getFontText());

        textArea.setLineWrap(true);
        textArea.setBorder(new EmptyBorder(45, 15, 15, 15));
        textArea.setFont(new Font("Arial", Font.PLAIN, 20));

        JScrollPane scroll = new JScrollPane(textArea);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        JPanel centralPanel = new JPanel(new BorderLayout());
        centralPanel.add(title, BorderLayout.PAGE_START);
        centralPanel.add(scroll);
        centralPanel.setBorder(BorderFactory.createLineBorder(Color.black, 3));

        add(centralPanel);
    }

    private void displayRightPanel() {
        JLabel hint1Label = new JLabel("Enter the first hint:");
        hint1Label.setBorder(new EmptyBorder(50, 40, 50, 40));
        hint1Label.setFont(Utility.getFontText());

        hint1Text.setFont(new Font("Arial", Font.PLAIN, 25));
        hint1Text.setBorder(Utility.getTextBorder());
        hint1Text.setLineWrap(true);

        JScrollPane hint1Scroll = new JScrollPane(hint1Text);
        hint1Scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        JPanel hint1Panel = new JPanel(new GridLayout(2, 1));
        hint1Panel.add(hint1Label);
        hint1Panel.add(hint1Scroll);

        JLabel hint2Label = new JLabel("Enter the second hint:");
        hint2Label.setBorder(new EmptyBorder(50, 40, 50, 40));
        hint2Label.setFont(Utility.getFontText());

        hint2Text.setFont(new Font("Arial", Font.PLAIN, 25));
        hint2Text.setBorder(Utility.getTextBorder());
        hint2Text.setLineWrap(true);

        JScrollPane hint2Scroll = new JScrollPane(hint2Text);
        hint2Scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        JPanel hint2Panel = new JPanel(new GridLayout(2, 1));
        hint2Panel.add(hint2Label);
        hint2Panel.add(hint2Scroll);

        JTextArea warning = new JTextArea();
        warning.setText("Warning! The hints must be below 100 characters, \notherwise they will be trimmed!");
        warning.setFont(new Font("Arial", Font.PLAIN, 25));
        warning.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        warning.setBackground(new Color(240, 240, 240));
        warning.setLineWrap(true);

        consoleLog.setEditable(false);
        consoleLog.setFont(new Font("Arial", Font.PLAIN, 20));
        consoleLog.setLineWrap(true);
        consoleLog.setText("Console logs:\n");
        consoleLog.setText(consoleLog.getText() + Utility.getDummyLongText());

        JScrollPane consolePane = new JScrollPane(consoleLog);
        consolePane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        JPanel rightPanel = new JPanel(new GridLayout(4, 1));

        rightPanel.add(hint1Panel);
        rightPanel.add(hint2Panel);
        rightPanel.add(warning);
        rightPanel.add(consolePane);
        add(rightPanel);
    }

    private void appendConsoleLog(String message) {
        consoleLog.setText(consoleLog.getText() + ">>>" + message + "\n");
    }
}
