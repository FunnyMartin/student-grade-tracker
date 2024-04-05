package Commands;
import Logic.*;

public class RemoveMarkCommand implements Command{
    private Manager manager;
    private String subjectName;
    private int index;

    public RemoveMarkCommand(Manager manager, String subjectName, int index){
        this.manager = manager;
        this.subjectName = subjectName;
        this.index = index;
    }

    @Override
    public void execute() {
        manager.removeMark(subjectName, index);
    }
}
