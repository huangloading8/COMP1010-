package src;
import java.util.ArrayList;

public class Video {
    private int videoID;
    private String title;
    private String description;
    private int viewCount;
    private int likeCount;
    private ArrayList<Comment> comments;
    private int duration; // in seconds
    private int dateUploaded;
    private Channel channel;

    public Video(int videoID, String title, String description, int duration, int dateUploaded, Channel channel) {
        this.videoID = videoID;
        this.title = title;
        this.description = description;
        this.duration = duration;
        this.dateUploaded = dateUploaded;
        this.channel = channel;
        this.viewCount = 0;
        this.likeCount = 0;
        this.comments = new ArrayList<>();
    }

    public void addComment(Comment comment) {
        comments.add(comment);
    }

    public void incrementViewCount() {
        viewCount++;
    }

    public void likeVideo() {
        likeCount++;
    }

    public String getTitle() {
        return title;
    }
}
