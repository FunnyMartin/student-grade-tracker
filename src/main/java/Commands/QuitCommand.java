package Commands;

import Logic.Manager;

public class QuitCommand implements Command {
    private final Manager manager;

    public QuitCommand(Manager manager) {
        this.manager = manager;
    }

    @Override
    public void execute() {
        manager.quit();
    }
}
