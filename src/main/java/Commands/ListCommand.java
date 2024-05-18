package Commands;

import Logic.*;

public class ListCommand implements Command {
    private final Manager manager;

    public ListCommand(Manager manager) {
        this.manager = manager;
    }

    @Override
    public void execute() {
        manager.list();
    }
}
