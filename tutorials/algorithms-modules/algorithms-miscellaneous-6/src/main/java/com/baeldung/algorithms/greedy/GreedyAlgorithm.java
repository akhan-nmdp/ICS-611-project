package com.baeldung.algorithms.greedy;

import java.util.List;

public class GreedyAlgorithm {

    int currentLevel = 0;
    final int maxLevel = 3; 
    SocialConnector sc;
    FollowersPath fp;
    
    public GreedyAlgorithm(SocialConnector sc) {
        super();
        this.sc = sc;
        this.fp = new FollowersPath();
    }
    
    public long findMostFollowersPath(String account) {
        long max = 0;
        SocialUser toFollow = null;
        
        List<SocialUser> followers = sc.getFollowers(account);
        for (SocialUser el : followers) {
            long followersCount = el.getFollowersCount();
            if (followersCount > max) {
                toFollow = el;
                max = followersCount;
            }
        }
        
        if (currentLevel < maxLevel - 1) {
            currentLevel++;
            max += findMostFollowersPath(toFollow.getUsername());
            return max;
        } else {
            return max;
        }            
    }
    
    public FollowersPath getFollowers() {
        return fp;
    }
}
