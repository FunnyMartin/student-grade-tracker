package Commands;

import Logic.*;

public class AddSubjectCommand implements Command {
    private final Manager manager;
    private final String subjectName;

    public AddSubjectCommand(Manager manager, String subjectName) {
        this.manager = manager;
        this.subjectName = subjectName;
    }

    @Override
    public void execute() {
        manager.addSubject(subjectName);
    }
}