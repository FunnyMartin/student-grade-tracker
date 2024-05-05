package Commands;

import Logic.Manager;

public class ListFilesCommand implements Command {
    private final Manager manager;

    public ListFilesCommand(Manager manager) {
        this.manager = manager;
    }

    @Override
    public void execute() {
        manager.listFiles();
    }
}