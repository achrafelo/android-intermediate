package com.possiblelabs.serverspringtest;

/**
 * Created by possiblelabs on 7/30/15.
 */
public class Post {
    private String userId;
    private String id;
    private String title;
    private String body;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Post{");
        sb.append("userId='").append(userId).append('\'');
        sb.append(", id='").append(id).append('\'');
        sb.append(", title='").append(title).append('\'');
        sb.append(", body='").append(body).append('\'');
        sb.append('}');
        return sb.toString();
    }

    private boolean isNull(String s) {
        return s == null || s.isEmpty();
    }

    public void updatePost(Post post) {
        if (!isNull(post.userId))
            this.userId = post.userId;

        if (!isNull(post.id))
            this.id = post.id;

        if (!isNull(post.title))
            this.title = post.title;

        if (!isNull(post.body))
            this.body = post.body;
    }

    public void modifyPost(Post post) {
        if (!isNull(this.userId) && !isNull(post.userId))
            this.userId = post.userId;

        if (!isNull(this.id) && !isNull(post.id))
            this.id = post.id;

        if (!isNull(this.title) && !isNull(post.title))
            this.title = post.title;

        if (!isNull(this.body) && !isNull(post.body))
            this.body = post.body;
    }
}
