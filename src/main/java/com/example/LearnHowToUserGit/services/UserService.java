package com.example.LearnHowToUserGit.services;

import com.example.LearnHowToUserGit.entity.User;

public interface UserService {

    User addUser(User user);

    User selectUser(String userName, String password);
}
