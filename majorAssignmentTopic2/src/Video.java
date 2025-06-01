/*
 * This class represents a video uploaded to a channel
 * It contains methods to operate on video objects in a channel
 */

public class Video {
    private static int nextId = 1; // Static counter for generating unique video IDs
    private int videoID;
    private String title;
    private String description;
    private int duration;
    private int dateUploaded;
    private Channel channel;

    // This is the Video constructor with all of the required information about a video
    public Video(String title, String description, int duration, int dateUploaded, Channel channel) {
        this.videoID = nextId++; // Assign and increment unique ID
        this.title = title;
        this.description = description;
        this.duration = duration;
        this.dateUploaded = dateUploaded;
        this.channel = channel;
     
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


    // This method returns the information about a video
    public String getVideoInfo() {
        return "Video ID: " + videoID +
                "\nTitle: " + title +
                "\nDescription: " + description +
                "\nDuration: " + duration + " seconds" +
                "\nUploaded on: " + dateUploaded +
                "\nChannel: " + channel.getChannelName();
    }
    /*
     * This method manually sets the video ID.
     * It also updates the static ID counter if necessary to avoid duplicates.
     *
     * @param id the new video ID
     */
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
