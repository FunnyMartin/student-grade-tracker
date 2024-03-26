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
                if(parts.length == 3 && parts[1].equals("add")){
                    return new AddSubjectCommand(manager, parts[2]);
                }
                break;

            case "help":
                return new HelpCommand(manager);

            case "list":
                return new ListCommand(manager);
            case "mark":
        }

        // Add more commands as needed

        return null; // Or throw an exception for unknown commands
    }
}
