import javax.swing.*;
import java.awt.*;
import java.io.PrintStream;
import java.util.ArrayList;

public class PopupConsole {
    private JTextArea consoleTextArea;
    private JTextField inputField;
    private CommandParser parser;

    public PopupConsole() {
        try {
            // Set custom look and feel
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        // Custom colors
        Color backgroundColor = Color.BLACK;
        Color textColor = new Color(0, 255, 0); // Green color

        JFrame frame = new JFrame("Grade Tracker");
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        // Set icon for the JFrame
        ImageIcon icon = new ImageIcon("src/Other/logo.png");
        frame.setIconImage(icon.getImage());

        JPanel contentPane = new JPanel(new BorderLayout());
        contentPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        contentPane.setBackground(backgroundColor);

        consoleTextArea = new JTextArea();
        consoleTextArea.setEditable(false);
        consoleTextArea.setFont(new Font("Consolas", Font.PLAIN, 14));
        consoleTextArea.setForeground(textColor); // Set text color
        consoleTextArea.setBackground(backgroundColor); // Set background color
        JScrollPane scrollPane = new JScrollPane(consoleTextArea);
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

        inputField = new JTextField();
        inputField.setFont(new Font("Consolas", Font.PLAIN, 14));
        inputField.setForeground(textColor); // Set text color
        inputField.setBackground(backgroundColor); // Set background color
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

        // Redirect System.out to consoleTextArea
        PrintStream printStream = new PrintStream(new TextAreaOutputStream(consoleTextArea));
        System.setOut(printStream);
        System.setErr(printStream);

        ArrayList<Subject> subjects = new ArrayList<>();
        Manager manager = new Manager(subjects);
        parser = new CommandParser(manager);
    }

    private void processInput(String input) {
        Command command = parser.parse(input);
        if (command != null) {
            command.execute();
        } else {
            consoleTextArea.append("Invalid command\n");
        }
    }
}
