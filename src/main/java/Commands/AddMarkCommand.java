package Commands;

import Logic.*;


public class AddMarkCommand implements Command {
    private final Manager manager;
    private final String subjectName;
    private final int grade, weight;

    public AddMarkCommand(Manager manager, String subjectName, int grade, int weight) {
        this.manager = manager;
        this.subjectName = subjectName;
        this.grade = grade;
        this.weight = weight;
    }

    @Override
    public void execute() {
        manager.addMark(subjectName, grade, weight);
    }
}
