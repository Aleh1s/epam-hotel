package ua.aleh1s.hotelepam.controller.command;

import ua.aleh1s.hotelepam.appcontext.ResourcesManager;

public class CommandException extends RuntimeException {

    private final String path;

    public CommandException() {
        super("Oops... something went wrong!");
        this.path = ResourcesManager.getInstance().getValue("path.page.error");
    }

    public CommandException(String message) {
        super(message);
        this.path = ResourcesManager.getInstance().getValue("path.page.error");
    }

    public CommandException(String message, String path) {
        super(message);
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
