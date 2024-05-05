package Commands;

import Logic.*;

public class RemoveSubjectCommand implements Command {

    private final Manager manager;
    private final String subjectName;

    public RemoveSubjectCommand(Manager manager, String subjectName) {
        this.manager = manager;
        this.subjectName = subjectName;
    }

    @Override
    public void execute() {
        manager.removeSubject(subjectName);
    }
}
