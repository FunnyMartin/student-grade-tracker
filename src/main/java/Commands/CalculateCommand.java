package Commands;

import Logic.Manager;

public class CalculateCommand implements Command{
    private final Manager manager;
    private final String subjectName;
    private final double desiredAverage;

    public CalculateCommand(Manager manager, String subjectName, double desiredAverage) {
        this.manager = manager;
        this.subjectName = subjectName;
        this.desiredAverage = desiredAverage;
    }

    @Override
    public void execute() {
        manager.calculate(subjectName, desiredAverage);
    }
}
