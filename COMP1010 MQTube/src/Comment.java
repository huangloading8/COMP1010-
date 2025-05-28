package src;

public class Comment {
    private String commentID;
    private String username;
    private String email;
    private String content;
    private int datePosted;
    private Video video;

    public Comment(String commentID, String username, String email, String content, int datePosted, Video video) {
        this.commentID = commentID;
        this.username = username;
        this.email = email;
        this.content = content;
        this.datePosted = datePosted;
        this.video = video;
    }

    public String getCommentID() {
        return commentID;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getContent() {
        return content;
    }

    public int getDatePosted() {
        return datePosted;
    }

    public Video getVideo() {
        return video;
    }
}
