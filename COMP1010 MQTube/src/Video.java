package src;
import java.util.ArrayList;

public class Video {
    int videoID;
    String title;
    String description;
    int viewCount;
    int likeCount;
    ArrayList<Comment> comment;
    int duration;
    int dateUploaded;
    Channel channel;

    public Video(int videoID, String title, String description, int duration, int dateUploaded, Channel channel) {
        this.videoID = videoID;
        this.title = title;
        this.description = description;
        this.duration = duration;
        this.dateUploaded = dateUploaded;
        this.channel = channel;
        this.viewCount = 0;
        this.likeCount = 0;
        this.comment = new ArrayList<>();
    }
}

