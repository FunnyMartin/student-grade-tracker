package Commands;

import Logic.*;

public class RemoveMarkCommand implements Command {
    private final Manager manager;
    private final String subjectName;
    private final int index;

    public RemoveMarkCommand(Manager manager, String subjectName, int index) {
        this.manager = manager;
        this.subjectName = subjectName;
        this.index = index;
    }

    @Override
    public void execute() {
        manager.removeMark(subjectName, index);
    }
}
