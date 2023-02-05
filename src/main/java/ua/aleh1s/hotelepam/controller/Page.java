package ua.aleh1s.hotelepam.controller;

public enum Page {

    ERROR_PAGE("error.jsp"),
    SIGNUP("signup.jsp"),
    LOGIN("login.jsp"),
    APPLICATION("application.jsp");

    private final String path;

    Page(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
    @Override
    public String toString() {
        return path;
    }
}
