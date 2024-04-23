package Commands;
import Logic.*;

public class CommandParser {
    private Manager manager;

    public CommandParser(Manager manager) {
        this.manager = manager;
    }

    public Command parse(String input){
        String[] parts = input.split(" ");
        String commandName = parts[0].toLowerCase();

        switch(commandName){
            case "subject":
                if(parts.length == 3 && parts[1].equalsIgnoreCase("add")){
                    return new AddSubjectCommand(manager, parts[2]);
                } else if (parts.length == 3 && parts[1].equalsIgnoreCase("remove")) {
                    return new RemoveSubjectCommand(manager, parts[2]);
                } else if (parts.length == 6 && parts[2].equalsIgnoreCase("mark") && parts[3].equalsIgnoreCase("add")) {
                    return new AddMarkCommand(manager, parts[1], Integer.valueOf(parts[4]), Integer.valueOf(parts[5]));
                } else if (parts.length == 5 && parts[2].equalsIgnoreCase("mark") && parts[3].equalsIgnoreCase("remove")){
                    return new RemoveMarkCommand(manager, parts[1], Integer.valueOf(parts[4]));
                } else if (parts.length == 3 && parts[2].equalsIgnoreCase("graph")){
                    return new GraphCommand(manager, parts[1]);
                }
                break;

            case "help":
                return new HelpCommand(manager);
            case "list":
                return new ListCommand(manager);
            case "settings":
            case "save":
            case "load":
        }

        return null; // Or throw an exception for unknown commands
    }
}
