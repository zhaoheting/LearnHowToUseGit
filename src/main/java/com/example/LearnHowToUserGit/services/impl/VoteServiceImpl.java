package com.example.LearnHowToUserGit.services.impl;

import com.example.LearnHowToUserGit.services.VoteService;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class VoteServiceImpl implements VoteService {
    @Override
    public boolean expireToVote(String articleName) {
        return false;
    }

    @Override
    public boolean hasVotedOrNot(String articleName, String userId) {
        return false;
    }

    @Override
    public void vote(String articleName, String username) {

    }

    @Override
    public void addArticle(Map<String, Object> articleProperties) {

    }
}
