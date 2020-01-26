package com.example.LearnHowToUserGit.services;

import java.util.Map;

public interface VoteService {
    //判断是否超出可投票时间
    public boolean expireToVote(String articleName);

    //判断是否投过票
    public boolean hasVotedOrNot(String articleName, String userId);

    //投票（修改score数据，添加名字到本文章的投票名单里）
    public void vote(String articleName,String username);

    //发布一个文章（将文章添加到hash里面，将发布者名单添加到已投票用户名单里面）
    public void addArticle(Map<String, Object> articleProperties);


}
