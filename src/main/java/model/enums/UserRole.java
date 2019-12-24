package model.enums;

public enum UserRole {
    ADMIN("ROLE_ADMIN"),
    MODERATOR("ROLE_MODERATOR"),
    SPEAKER("ROLE_SPEAKER"),
    USER("ROLE_USER");
    private String role;

    UserRole(String role) {
        this.role = role;
    }

    public static final UserRole getRole(String role) {
        switch (role) {
            case "USER":
                return USER;
            case "ADMIN":
                return ADMIN;
            case "MODERATOR":
                return MODERATOR;
            case "SPEAKER":
                return SPEAKER;
        }
        return null;
    }
}
