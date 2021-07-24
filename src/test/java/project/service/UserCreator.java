package project.service;

import project.model.User;

public class UserCreator {

    public static final String TESTDATA_USER_NAME = "testdata.user.name";
    public static final String TESTDATA_USER_PASSWORD = "testdata.user.password";

    public static User getCredentialsFromProperty() {
       User testUser = new User.UserBuilder()
                .userEmail(TestDataReader.getTestData(TESTDATA_USER_NAME))
                .password(TestDataReader.getTestData(TESTDATA_USER_PASSWORD))
                .build();
       return testUser;

    }
}