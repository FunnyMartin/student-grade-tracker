package Commands;

import Logic.Manager;

public class DeleteFileCommand implements Command {
    private final Manager manager;
    private final String fileName;

    public DeleteFileCommand(Manager manager, String fileName) {
        this.manager = manager;
        this.fileName = fileName;
    }


    @Override
    public void execute() {
        manager.deleteFile(fileName);
    }
}
