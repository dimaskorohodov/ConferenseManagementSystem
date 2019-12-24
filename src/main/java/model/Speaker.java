package model;

import model.enums.UserRole;

public class Speaker extends User {
    private int rating;

    public Speaker(String login, String password, UserRole userRole, int rating) {
        super(login, password, userRole);
        this.rating = rating;
    }

    public Speaker() {
        super();
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
