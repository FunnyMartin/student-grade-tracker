package Commands;

import Logic.FileManager;

import java.io.IOException;

public class SaveCommand implements Command {
    private final FileManager manager;
    private final String fileName;

    public SaveCommand(FileManager manager, String fileName) {
        this.manager = manager;
        this.fileName = fileName;
    }

    @Override
    public void execute() {
        try {
            manager.save(fileName);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
