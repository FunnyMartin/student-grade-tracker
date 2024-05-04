package Commands;

import Logic.Manager;

import java.io.IOException;

public class SaveCommand implements Command {
    private Manager manager;
    private String fileName;

    public SaveCommand(Manager manager, String fileName){
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
