package com.dessproject.dessproject.backing;

import java.util.*;
import java.io.Serializable;
/**
 * Created by Eric on 11/12/2017.
 */

public class PostClass implements Serializable{
    public int postId;
    public String content;
    public List<Integer> attatchedTags = new ArrayList<Integer>();
    public int authorId;
    public List<Integer> attatchedUsers = new ArrayList<Integer>();

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

    public List<Integer> getAttatchedTags() {
        return attatchedTags;
    }

    public void setAttatchedTags(List<Integer> attatchedTags) {
        this.attatchedTags = attatchedTags;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public List<Integer> getAttatchedUsers() {
        return attatchedUsers;
    }

    public void setAttatchedUsers(List<Integer> attatchedUsers) {
        this.attatchedUsers = attatchedUsers;
    }


}
