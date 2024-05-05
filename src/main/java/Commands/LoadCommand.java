package Commands;

import Logic.Manager;

import java.io.IOException;

public class LoadCommand implements Command {
    private final Manager manager;
    private final String fileName;

    public LoadCommand(Manager manager, String fileName) {
        this.manager = manager;
        this.fileName = fileName;
    }


    @Override
    public void execute() {
        try {
            manager.load(fileName);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
