package ua.aleh1s.hotelepam.exception;

import ua.aleh1s.hotelepam.appcontext.ResourcesManager;

public class ControllerException extends ApplicationException {

    private final String path;

    public ControllerException() {
        super("Oops... something went wrong!");
        this.path = ResourcesManager.getInstance().getValue("path.page.error");
    }

    public ControllerException(String message) {
        super(message);
        this.path = ResourcesManager.getInstance().getValue("path.page.error");
    }

    public ControllerException(String message, String path) {
        super(message);
        this.path = path;
    }

    public ControllerException(Throwable cause, String message, String path) {
        super(message, cause);
        this.path = path;
    }

    public String getPath() {
        return path;
    }

}
