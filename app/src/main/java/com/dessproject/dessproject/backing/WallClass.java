package com.dessproject.dessproject.backing;

import java.util.List;

/**
 * Created by Eric on 11/12/2017.
 */

public class WallClass {
    public int userId;
    public List<PostClass> posts;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public List<PostClass> getPosts() {
        return posts;
    }

    public void setPosts(List<PostClass> posts) {
        this.posts = posts;
    }


}
