package ua.aleh1s.hotelepam.controller.command;

public class Result {

    private final String page;
    private final boolean isRedirect;

    private Result(String page, boolean isRedirect) {
        this.page = page;
        this.isRedirect = isRedirect;
    }

    public static Result of(String page, boolean isRedirect) {
        return new Result(page, isRedirect);
    }

    public String getPage() {
        return page;
    }

    public boolean isRedirect() {
        return isRedirect;
    }
}
