package Commands;

import Logic.*;

public class CommandParser {
    private final Manager manager;
    private final FileManager fileManager;
    private final PopupConsole popupConsole;

    public CommandParser(Manager manager, FileManager fileManager, PopupConsole popupConsole) {
        this.manager = manager;
        this.fileManager = fileManager;
        this.popupConsole = popupConsole;
    }

    /**
     * Parses command input and if invalid then returns null
     *
     * @param input is users input
     * @return command based on users input
     */
    public Command parse(String input) {
        String[] parts = input.split(" ");
        if (parts.length == 0) {
            return null;
        }
        String commandName = parts[0].toLowerCase();

        try {
            switch (commandName) {
                case "subject":
                    if (parts.length == 3 && parts[1].equalsIgnoreCase("add")) {
                        return new AddSubjectCommand(manager, parts[2]);
                    } else if (parts.length == 3 && parts[1].equalsIgnoreCase("remove")) {
                        return new RemoveSubjectCommand(manager, parts[2].toLowerCase());
                    } else if (parts.length == 6 && parts[2].equalsIgnoreCase("mark") && parts[3].equalsIgnoreCase("add")) {
                        return new AddMarkCommand(manager, parts[1].toLowerCase(), Integer.parseInt(parts[4]), Integer.parseInt(parts[5]));
                    } else if (parts.length == 5 && parts[2].equalsIgnoreCase("mark") && parts[3].equalsIgnoreCase("remove")) {
                        return new RemoveMarkCommand(manager, parts[1].toLowerCase(), Integer.parseInt(parts[4]));
                    } else if (parts.length == 3 && parts[2].equalsIgnoreCase("graph")) {
                        return new GraphCommand(manager, parts[1].toLowerCase());
                    }
                    break;

                case "help":
                    if (parts.length == 1) {
                        return new HelpCommand(manager);
                    }
                    break;
                case "list":
                    if (parts.length == 1) {
                        return new ListCommand(manager);
                    } else if (parts.length == 2 && parts[1].equalsIgnoreCase("files")) {
                        return new ListFilesCommand(fileManager);
                    }
                    break;

                case "save":
                    if (parts.length == 2) {
                        return new SaveCommand(fileManager, parts[1]);
                    }
                    break;
                case "load":
                    if (parts.length == 2) {
                        return new LoadCommand(fileManager, parts[1]);
                    }
                    break;
                case "create":
                    if (parts.length == 2) {
                        if (parts[1].matches("[a-zA-Z0-9]+")) {
                            return new CreateFileCommand(fileManager, parts[1]);
                        }
                    }
                    break;
                case "delete":
                    if (parts.length == 2) {
                        return new DeleteFileCommand(fileManager, parts[1]);
                    }
                    break;
                case "theme":
                    if(parts.length == 2){
                        return new ThemeCommand(manager, popupConsole, parts[1]);
                    }
                    break;
                case "calculate":
                    if (parts.length == 3 && Double.parseDouble(parts[2]) <= 5 && Double.parseDouble(parts[2]) >= 1){
                        return new CalculateCommand(manager, parts[1], Double.parseDouble(parts[2]));
                    }
                    break;
                case "quit":
                    return new QuitCommand(manager);
            }
            return null;
        } catch (NumberFormatException ignored) {

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
