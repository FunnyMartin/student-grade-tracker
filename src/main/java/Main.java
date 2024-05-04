import Commands.*;
import Logic.*;
import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        ArrayList<Subject> subjects = new ArrayList<>();
        Manager manager = new Manager(subjects);
        CommandParser parser = new CommandParser(manager);
        Scanner scan = new Scanner(System.in);
        String input;
        SwingUtilities.invokeLater(PopupConsole::new);

        while(true){
            input = scan.nextLine();
            Command command = parser.parse(input);
            if(command != null){
                command.execute();
            } else{
                System.out.println("invalid command");
            }
        }
    }
}