package src;
public class Comment {
    private String commentID;
    private String username;
    private String email;
    private String content;
    private int dateUploaded;
    private Video video;

    public Comment(String commentID, String username, String email, String content, int dateUploaded, Video video) {
        this.commentID = commentID;
        this.username = username;
        this.email = email;
        this.content = content;
        this.dateUploaded = dateUploaded;
        this.video = video;
    }

    public String getContent() {
        return content;
    }
}
