package Commands;

import Logic.Manager;

import java.io.IOException;

public class CreateFileCommand implements Command {
    private final Manager manager;
    private final String fileName;

    public CreateFileCommand(Manager manager, String fileName) {
        this.manager = manager;
        this.fileName = fileName;
    }


    @Override
    public void execute() {
        try {
            manager.createFile(fileName);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
