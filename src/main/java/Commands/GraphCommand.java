package Commands;

import Logic.Manager;

public class GraphCommand implements Command{
    private Manager manager;
    private String subjectName;

    public GraphCommand(Manager manager, String subjectName){
        this.manager = manager;
        this.subjectName = subjectName;
    }

    @Override
    public void execute() {
        manager.graph(subjectName);
    }
}
