package com.dessproject.dessproject.backing;

import java.util.*;
/**
 * Created by Eric on 11/12/2017.
 */

public class PostClass {
    public int postId;
    public String content;
    public List<TagClass> attatchedTags;
    public int authorId;
    public List<UserClass> attatchedUsers;

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<TagClass> getAttatchedTags() {
        return attatchedTags;
    }

    public void setAttatchedTags(List<TagClass> attatchedTags) {
        this.attatchedTags = attatchedTags;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public List<UserClass> getAttatchedUsers() {
        return attatchedUsers;
    }

    public void setAttatchedUsers(List<UserClass> attatchedUsers) {
        this.attatchedUsers = attatchedUsers;
    }


}
