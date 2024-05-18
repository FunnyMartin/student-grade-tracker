package Commands;

import Logic.FileManager;

public class DeleteFileCommand implements Command {
    private final FileManager manager;
    private final String fileName;

    public DeleteFileCommand(FileManager manager, String fileName) {
        this.manager = manager;
        this.fileName = fileName;
    }

    @Override
    public void execute() {
        manager.deleteFile(fileName);
    }
}
