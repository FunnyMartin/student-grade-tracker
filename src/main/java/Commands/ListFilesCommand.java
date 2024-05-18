package Commands;

import Logic.FileManager;

public class ListFilesCommand implements Command {
    private final FileManager manager;

    public ListFilesCommand(FileManager manager) {
        this.manager = manager;
    }

    @Override
    public void execute() {
        manager.listFiles();
    }
}
