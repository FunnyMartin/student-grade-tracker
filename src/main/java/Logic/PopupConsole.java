package Logic;

import Commands.*;

import javax.swing.*;
import java.awt.*;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;

public class PopupConsole implements Runnable {
    private final JTextArea consoleTextArea;
    private final JTextField inputField;
    private final CommandParser parser;

    //THIS METHOD WAS CREATED USING CHATGPT
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

    /**
     * Sets the popup console theme based on users input
     *
     * @param theme is the color
     */
    public void setTheme(String theme) {
        theme = theme.toLowerCase();
        Color color = null;
        String[] themesArray = {"black", "white", "red", "green", "blue", "gray", "yellow", "orange", "magenta", "cyan", "pink"};
        switch (theme) {
            case "black":
                consoleTextArea.setBackground(Color.BLACK);
                inputField.setBackground(Color.BLACK);
                color = Color.WHITE;
                break;
            case "white":
                consoleTextArea.setBackground(Color.WHITE);
                inputField.setBackground(Color.WHITE);
                color = Color.BLACK;
                break;
            case "red":
                color = Color.RED;
                break;
            case "green":
                color = Color.GREEN;
                break;
            case "blue":
                color = Color.BLUE;
                break;
            case "gray":
                color = Color.GRAY;
                break;
            case "yellow":
                color = Color.YELLOW;
                break;
            case "orange":
                color = Color.ORANGE;
                break;
            case "magenta":
                color = Color.MAGENTA;
                break;
            case "cyan":
                color = Color.CYAN;
                break;
            case "pink":
                color = Color.PINK;
                break;
            default:
                System.out.println("Invalid theme");
                System.out.println("Please choose from: " + Arrays.toString(themesArray));
                break;
        }

        if (color != null) {
            consoleTextArea.setForeground(color);
            inputField.setForeground(color);
            inputField.setCaretColor(color);
        }
    }

    @Override
    public void run() {
    }
}