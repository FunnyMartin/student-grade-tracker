package Commands;

import Logic.Manager;
import Logic.PopupConsole;

public class ThemeCommand implements Command{
    private final Manager manager;
    private final PopupConsole popupConsole;
    private final String theme;

    public ThemeCommand(Manager manager, PopupConsole popupConsole, String theme) {
        this.manager = manager;
        this.popupConsole = popupConsole;
        this.theme = theme;
    }

    @Override
    public void execute() {
        manager.changeTheme(popupConsole, theme);
    }
}
