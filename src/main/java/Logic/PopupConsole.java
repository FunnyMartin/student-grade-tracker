package Logic;

import Commands.*;

import javax.swing.*;
import java.awt.*;
import java.io.PrintStream;
import java.util.ArrayList;

public class PopupConsole implements Runnable {
    private final JTextArea consoleTextArea;
    private final JTextField inputField;
    private final CommandParser parser;

    /**
     * Sets the popup console theme based on users input
     *
     * @param theme is the color
     */
    public void setTheme(String theme) {
        theme = theme.toLowerCase();
        switch (theme) {
            case "black":
                consoleTextArea.setBackground(Color.BLACK);
                inputField.setBackground(Color.BLACK);
                consoleTextArea.setForeground(Color.WHITE);
                inputField.setForeground(Color.WHITE);
                inputField.setCaretColor(Color.WHITE);
                break;
            case "white":
                consoleTextArea.setBackground(Color.WHITE);
                inputField.setBackground(Color.WHITE);
                consoleTextArea.setForeground(Color.BLACK);
                inputField.setForeground(Color.BLACK);
                inputField.setCaretColor(Color.BLACK);
                break;
            case "red":
                consoleTextArea.setForeground(Color.RED);
                inputField.setForeground(Color.RED);
                inputField.setCaretColor(Color.RED);
                break;
            case "green":
                consoleTextArea.setForeground(Color.GREEN);
                inputField.setForeground(Color.GREEN);
                inputField.setCaretColor(Color.GREEN);
                break;
            case "blue":
                consoleTextArea.setForeground(Color.BLUE);
                inputField.setForeground(Color.BLUE);
                inputField.setCaretColor(Color.BLUE);
                break;
            default:
                System.out.println("Please choose from: black, white, red, green, blue");
                break;
        }
    }

    /**
     * Creates the popup window, sets icon and redirects text output stream to the window
     */
    public PopupConsole() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        Color backgroundColor = Color.BLACK;
        Color textColor = new Color(255, 255, 255, 255);

        JFrame frame = new JFrame("Grade Tracker");
        frame.setSize(800, 600);
        frame.setMinimumSize(new Dimension(275, 275));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        ImageIcon icon = new ImageIcon("src/main/java/Other/logo.png");
        frame.setIconImage(icon.getImage());

        JPanel contentPane = new JPanel(new BorderLayout());
        contentPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        contentPane.setBackground(backgroundColor);

        consoleTextArea = new JTextArea();
        consoleTextArea.setEditable(false);
        consoleTextArea.setFont(new Font("Consolas", Font.PLAIN, 14));
        consoleTextArea.setForeground(textColor);
        consoleTextArea.setBackground(backgroundColor);

        JScrollPane scrollPane = new JScrollPane(consoleTextArea);
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

        inputField = new JTextField();
        inputField.setCaretColor(Color.WHITE);
        inputField.setFont(new Font("Consolas", Font.PLAIN, 14));
        inputField.setForeground(textColor);
        inputField.setBackground(backgroundColor);

        inputField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.LIGHT_GRAY),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));

        inputField.addActionListener(e -> {
            String input = inputField.getText();
            consoleTextArea.append("> " + input + "\n");
            processInput(input);
            inputField.setText("");
        });

        contentPane.add(scrollPane, BorderLayout.CENTER);
        contentPane.add(inputField, BorderLayout.SOUTH);

        frame.setContentPane(contentPane);
        frame.setVisible(true);

        PrintStream printStream = new PrintStream(new TextAreaOutputStream(consoleTextArea));
        System.setOut(printStream);
        System.setErr(printStream);

        ArrayList<Subject> subjects = new ArrayList<>();
        Manager manager = new Manager(subjects);
        FileManager fileManager = new FileManager(manager);
        parser = new CommandParser(manager, fileManager, this);
    }

    /**
     * Processes users input into a command and executes it
     *
     * @param input is the users input
     */
    private void processInput(String input) {
        Command command = parser.parse(input);
        if (command != null) {
            command.execute();
        } else {
            consoleTextArea.append("Invalid command\n");
        }
    }

    @Override
    public void run() {

    }
}
