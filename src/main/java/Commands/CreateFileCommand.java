package Commands;

import Logic.FileManager;

import java.io.IOException;

public class CreateFileCommand implements Command {
    private final FileManager manager;
    private final String fileName;

    public CreateFileCommand(FileManager manager, String fileName) {
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
