package src;

import java.util.ArrayList;

public class Video {
    private static int nextId = 1; // Static counter for generating unique video IDs

    private int videoID;
    private String title;
    private String description;
    private int viewCount;
    private int likeCount;
    private int duration;
    private int dateUploaded;
    private Channel channel;
    private ArrayList<Comment> comments;

    // Constructor 
    public Video(String title, String description, int duration, int dateUploaded, Channel channel) {
        this.videoID = nextId++; // Assign and increment unique ID
        this.title = title;
        this.description = description;
        this.duration = duration;
        this.dateUploaded = dateUploaded;
        this.channel = channel;
        this.viewCount = 0;
        this.likeCount = 0;
        this.comments = new ArrayList<>();
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getDuration() {
        return duration;
    }

    public int getDateUploaded() {
        return dateUploaded;
    }

    public int getVideoID() {
        return videoID;
    }

    public Channel getChannel() {
        return channel;
    }

    public int getViewCount() {
        return viewCount;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public void addComment(Comment comment) {
        comments.add(comment);
    }

    public ArrayList<Comment> getComments() {
        return comments;
    }

    public String getVideoInfo() {
        return "Video ID: " + videoID +
                "\nTitle: " + title +
                "\nDescription: " + description +
                "\nDuration: " + duration + " seconds" +
                "\nUploaded on: " + dateUploaded +
                "\nChannel: " + channel.getChannelName() +
                "\nViews: " + viewCount +
                "\nLikes: " + likeCount +
                "\nComments: " + comments.size();
    }

    public void setVideoID(int id) {
        this.videoID = id;
        // Also update nextId if necessary to avoid duplicates on future videos:
        if (id >= nextId) {
            nextId = id + 1;
        }
    }

    

    public int getId() {
        return this.videoID;
    }

}
