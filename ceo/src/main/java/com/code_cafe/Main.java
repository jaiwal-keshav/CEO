package com.code_cafe;

import com.code_cafe.HomePage.Home;
import com.code_cafe.MentorsListPage.UserList;
import com.code_cafe.MessagePage.Message;
import com.code_cafe.ProfilePage.Profile;

import javafx.application.Application;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        //Application.launch(Home.class, args);
        //Application.launch(Profile.class, args);
        //Application.launch(Message.class, args);
        Application.launch(UserList.class, args);

    }

}
    