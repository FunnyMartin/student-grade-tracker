import Commands.*;
import Logic.*;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ArrayList<Subject> subjects = new ArrayList<>();
        Manager manager = new Manager(subjects);
        FileManager fileManager = new FileManager(manager);
        Scanner scan = new Scanner(System.in);
        PopupConsole popupConsole = new PopupConsole();
        SwingUtilities.invokeLater(popupConsole);
        CommandParser parser = new CommandParser(manager, fileManager, popupConsole);

        while (true) {
            String input = scan.nextLine();
            Command command = parser.parse(input);
            if (command != null) {
                command.execute();
            } else {
                System.out.println("invalid command");
            }
        }
    }
}