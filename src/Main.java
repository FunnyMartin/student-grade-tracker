import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ArrayList<Subject> subjects = new ArrayList<>();
        Manager manager = new Manager(subjects);
        CommandParser parser = new CommandParser(manager);
        Scanner scan = new Scanner(System.in);
        String input;

        System.out.println("use help for commands");

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