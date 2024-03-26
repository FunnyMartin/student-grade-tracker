public class ListCommand implements Command {
    private Manager manager;

    public ListCommand(Manager manager){
        this.manager = manager;
    }


    @Override
    public void execute() {
        manager.list();
    }
}
