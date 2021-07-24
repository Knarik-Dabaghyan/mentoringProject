package project.model;

public class User {
    private  String userEmail;
    private  String userPassword;

    public User(UserBuilder builder) {
        this.userEmail = builder.userEmail;
        this.userPassword = builder.password;
    }

    public String getUserEmail(){
        return userEmail;
    }

    public String getUserPassword(){
        return userPassword;
    }

    @Override
    public String toString() {
        return "UserEmail: " +this.userEmail+"UserPassword"+this.getUserPassword();
    }
    public static class UserBuilder {
        private String userEmail;
        private String password;

        public UserBuilder userEmail(String userEmail) {
            this.userEmail = userEmail;
            return this;
        }

        public UserBuilder password(String password) {
            this.password = password;
            return this;
        }
        public User build() {
            return new User(this);
        }

    }
}

