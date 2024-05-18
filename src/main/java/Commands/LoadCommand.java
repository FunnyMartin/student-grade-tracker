package Commands;

import Logic.FileManager;

import java.io.IOException;

public class LoadCommand implements Command {
    private final FileManager manager;
    private final String fileName;

    public LoadCommand(FileManager manager, String fileName) {
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
