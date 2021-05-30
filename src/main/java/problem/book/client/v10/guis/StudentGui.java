package problem.book.client.v10.guis;

import problem.book.client.v10.ProblemBookApp;
import problem.book.client.v10.dtos.LoggedInDTO;
import problem.book.client.v10.dtos.ProblemDTO;
import problem.book.client.v10.guis.utilities.DatabaseLinker;
import problem.book.client.v10.guis.utilities.Utility;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.imageio.ImageIO;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Properties;

public class StudentGui extends JFrame {

    private final JTextArea consoleLog = new JTextArea();
    private final LoggedInDTO student = ProblemBookApp.getLoggedInDTO();

    private final JTextArea textField = new JTextArea();
    private ProblemDTO problemDTO = DatabaseLinker.getInstance().getNextProblem(0);

    public StudentGui() {
        // Set the name of tha application
        setTitle("Problem Book - Student");

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

        BufferedImage bufferedImage = ImageIO.read(new File(DatabaseLinker.getInstance().getAvatarForId(student.getAvatarId()).getUrl()));
        JLabel image = new JLabel(new ImageIcon(bufferedImage));
        image.setBorder(BorderFactory.createLineBorder(Color.black, 5));

        JComboBox<String> avatarId = new JComboBox<>();
        Utility.addAvatarItems(avatarId);
        avatarId.setFont(new Font("Arial", Font.PLAIN, 25));
        avatarId.setSelectedIndex(student.getAvatarId() - 1);
        avatarId.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int position = avatarId.getSelectedIndex() + 1;
                DatabaseLinker.getInstance().updateAvatar(true, student.getId(), position);
            }
        });


        imagePanel.add(image);
        imagePanel.add(avatarId);

        JLabel name = new JLabel("Name: " + student.getName());
        name.setFont(new Font("Arial", Font.PLAIN, 35));
        name.setBorder(Utility.getTextBorder());

        JLabel email = new JLabel("Email: " + student.getEmail());
        email.setFont(new Font("Arial", Font.PLAIN, 35));
        email.setBorder(Utility.getTextBorder());

        textPanel.add(name);
        textPanel.add(email);

        JButton sendEmail = new JButton("Send email");
        sendEmail.setFont(new Font("Arial", Font.BOLD, 50));
        sendEmail.setBorder(BorderFactory.createLineBorder(Color.black, 5));
        sendEmail.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setMultiSelectionEnabled(false);
                int result = fileChooser.showOpenDialog(null);

                if (result == JFileChooser.APPROVE_OPTION) {
                    File file = fileChooser.getSelectedFile();

                    sendEmail(problemDTO.getTeacherEmail(),
                            student.getEmail(),
                            "Problem " + problemDTO.getId(),
                            "My name is " + student.getName() + " and I resolved the problem " + problemDTO.getId() + ".",
                            file);
                }
                else {
                    appendConsoleLog("File selection aborted!");
                }
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
        textField.setEditable(false);
        textField.setText(problemDTO.getContent());
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

        JLabel problemId = new JLabel("Problem ID: " + problemDTO.getId());
        problemId.setFont(new Font("Arial", Font.PLAIN, 40));
        problemId.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 100));

        JLabel author = new JLabel("Author's ID: " + problemDTO.getTeacherId());
        author.setFont(new Font("Arial", Font.PLAIN, 40));
        author.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 100));

        JPanel informationPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        informationPanel.add(problemId);
        informationPanel.add(author);

        JButton displayProblem = new JButton("  Display problem  ");
        displayProblem.setFont(new Font("Arial", Font.BOLD, 40));
        displayProblem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textField.setText(problemDTO.getContent());
            }
        });
        // displayProblem.setBorder(BorderFactory.createLineBorder(Color.red, 5));

        JButton displayHint1 = new JButton("  Display first hint  ");
        displayHint1.setFont(new Font("Arial", Font.BOLD, 40));
        displayHint1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textField.setText(problemDTO.getHint1());
            }
        });
        // displayHint1.setBorder(BorderFactory.createLineBorder(Color.green, 5));

        JButton displayHint2 = new JButton("  Display second hint  ");
        displayHint2.setFont(new Font("Arial", Font.BOLD, 40));
        displayHint2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textField.setText(problemDTO.getHint2());
            }
        });
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
        next.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                problemDTO = DatabaseLinker.getInstance().getNextProblem(problemDTO.getId());
                textField.setText(problemDTO.getContent());
                problemId.setText("Problem ID: " + problemDTO.getId());
                author.setText("Author's ID: " + problemDTO.getTeacherId());
                appendConsoleLog("Problem with ID " + problemDTO.getId() + " received with success!");
            }
        });

        JButton previous = new JButton("Previous");
        previous.setFont(new Font("Arial", Font.PLAIN, 30));
        previous.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                problemDTO = DatabaseLinker.getInstance().getPreviousProblem(problemDTO.getId());
                textField.setText(problemDTO.getContent());
                problemId.setText("Problem ID: " + problemDTO.getId());
                author.setText("Author's ID: " + problemDTO.getTeacherId());
                appendConsoleLog("Problem with ID " + problemDTO.getId() + " received with success!");
            }
        });

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.add(previous);
        buttonsPanel.add(next);

        JLabel searchLabel = new JLabel("Search by id:");
        searchLabel.setFont(new Font("Arial", Font.PLAIN, 30));

        JTextField searchTextField = new JTextField();
        searchTextField.setFont(new Font("Arial", Font.PLAIN, 30));
        searchTextField.setPreferredSize(new Dimension(150, 40));

        JButton searchButton = new JButton("Search");
        searchButton.setFont(new Font("Arial", Font.PLAIN, 30));
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id;
                try {
                    id = Integer.parseInt(searchTextField.getText());
                }
                catch (NumberFormatException exception) {
                    appendConsoleLog("Error! Invalid ID");
                    return;
                }
                ProblemDTO newProblem = DatabaseLinker.getInstance().getProblem(id);
                if (newProblem == null) {
                    appendConsoleLog("Could not find the problem with ID " + id + "!");
                    return;
                }
                problemDTO = newProblem;
                textField.setText(problemDTO.getContent());
                problemId.setText("Problem ID: " + problemDTO.getId());
                author.setText("Author's ID: " + problemDTO.getTeacherId());
                appendConsoleLog("Problem with ID " + id + " received with success!");
            }
        });

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
        appendConsoleLog("[START] Console logs:");

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

    private void sendEmail(String to, String from, String subject, String content, File attachment) {
        Properties properties = System.getProperties();

        // Setup mail server
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.socketFactory.port", "465");
        properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

        // Get the Session object.// and pass username and password
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {

            protected PasswordAuthentication getPasswordAuthentication() {
                // problembookjava@gmail.com
                return new PasswordAuthentication("problembookjava", "version1.0");

            }

        });

        try {
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            // Set Subject: header field
            message.setSubject(subject);

            // Create the message part
            BodyPart messageBodyPart = new MimeBodyPart();

            // Fill the message
            messageBodyPart.setText(content);

            // Create a multipart message
            Multipart multipart = new MimeMultipart();

            // Set text message part
            multipart.addBodyPart(messageBodyPart);

            // Part two is attachment
            messageBodyPart = new MimeBodyPart();
            DataSource source = new FileDataSource(attachment);
            messageBodyPart.setDataHandler(new DataHandler(source));
            messageBodyPart.setFileName("problem");
            multipart.addBodyPart(messageBodyPart);

            // Send the complete message parts
            message.setContent(multipart);

            // Send message
            Transport.send(message);
            appendConsoleLog("Email sent successfully!");
        } catch (MessagingException mex) {
            mex.printStackTrace();
            appendConsoleLog("Error! Could not send email!");
        }
    }

    private void appendConsoleLog(String message) {
        consoleLog.setText(consoleLog.getText() + ">>>" + message + "\n");
    }
}
