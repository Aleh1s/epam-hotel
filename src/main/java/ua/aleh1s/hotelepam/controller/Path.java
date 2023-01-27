package ua.aleh1s.hotelepam.controller;

public enum Path {

    ERROR_PAGE("error.jsp"),
    REGISTRATION_PAGE("registration.jsp");

    private final String path;

    Path(String path) {
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
