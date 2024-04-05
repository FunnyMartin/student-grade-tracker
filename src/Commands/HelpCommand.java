package Commands;
import Logic.*;

public class HelpCommand implements Command{
    private Manager manager;

    public HelpCommand(Manager manager){
        this.manager = manager;
    }

    @Override
    public void execute() {
        manager.help();
    }
}
