package Commands;

import Logic.*;

public class HelpCommand implements Command {
    private final Manager manager;

    public HelpCommand(Manager manager) {
        this.manager = manager;
    }

    @Override
    public void execute() {
        manager.help();
    }
}
