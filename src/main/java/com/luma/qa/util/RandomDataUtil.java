package com.luma.qa.util;

import com.github.javafaker.Faker;
import com.luma.qa.pojo.UserData;

public class RandomDataUtil {
    public UserData createRandomUser() {
        UserData user = new UserData();
        Faker faker = new Faker();
        user.firstName = faker.name().firstName();
        user.lastName = faker.name().lastName();
        user.emailId = faker.internet().emailAddress();
        user.password = faker.internet().password(8,    10,true,true);
        return user;
    }
}
