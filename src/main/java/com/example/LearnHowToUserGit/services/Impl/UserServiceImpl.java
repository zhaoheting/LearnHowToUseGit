package com.example.LearnHowToUserGit.services.Impl;

import com.example.LearnHowToUserGit.dao.UserMapper;
import com.example.LearnHowToUserGit.entity.User;
import com.example.LearnHowToUserGit.services.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    UserMapper userMapper;

    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public User addUser(User user) {
        return userMapper.insert(user);
    }

    @Override
    public User selectUser(String userName, String password) {
        return userMapper.queryByNameAndPwd(userName, password);
    }
}
