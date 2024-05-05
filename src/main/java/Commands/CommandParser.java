package Commands;

import Logic.*;

public class CommandParser {
    private final Manager manager;

    public CommandParser(Manager manager) {
        this.manager = manager;
    }

    /**
     * Parses command input and if invalid then returns null
     *
     * @param input is users input
     * @return command based on users input
     */
    public Command parse(String input) {
        String[] parts = input.split(" ");
        String commandName = parts[0].toLowerCase();

        switch (commandName) {
            case "subject":
                if (parts.length == 3 && parts[1].equalsIgnoreCase("add")) {
                    return new AddSubjectCommand(manager, parts[2]);
                } else if (parts.length == 3 && parts[1].equalsIgnoreCase("remove")) {
                    return new RemoveSubjectCommand(manager, parts[2]);
                } else if (parts.length == 6 && parts[2].equalsIgnoreCase("mark") && parts[3].equalsIgnoreCase("add")) {
                    return new AddMarkCommand(manager, parts[1], Integer.parseInt(parts[4]), Integer.parseInt(parts[5]));
                } else if (parts.length == 5 && parts[2].equalsIgnoreCase("mark") && parts[3].equalsIgnoreCase("remove")) {
                    return new RemoveMarkCommand(manager, parts[1], Integer.parseInt(parts[4]));
                } else if (parts.length == 3 && parts[2].equalsIgnoreCase("graph")) {
                    return new GraphCommand(manager, parts[1]);
                }
                break;

            case "help":
                return new HelpCommand(manager);
            case "list":
                if (parts.length == 1) {
                    return new ListCommand(manager);
                } else if (parts.length == 2 && parts[1].equalsIgnoreCase("files")) {
                    return new ListFilesCommand(manager);
                }
                break;

            case "save":
                if (parts.length == 2) {
                    return new SaveCommand(manager, parts[1]);
                }
            case "load":
                if (parts.length == 2) {
                    return new LoadCommand(manager, parts[1]);
                }
            case "create":
                if (parts.length == 2) {
                    return new CreateFileCommand(manager, parts[1]);
                }
            case "delete":
                if (parts.length == 2) {
                    return new DeleteFileCommand(manager, parts[1]);
                }
            case "quit":
                return new QuitCommand(manager);
        }

        return null; // Or throw an exception for unknown commands
    }
}
