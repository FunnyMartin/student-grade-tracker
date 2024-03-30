public class RemoveSubjectCommand implements Command{

    private Manager manager;
    private String subjectName;

    public RemoveSubjectCommand(Manager manager, String subjectName){
        this.manager = manager;
        this.subjectName = subjectName;
    }

    @Override
    public void execute() {
        manager.removeSubject(subjectName);
    }
}
