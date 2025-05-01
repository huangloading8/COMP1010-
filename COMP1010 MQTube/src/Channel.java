package src;
import java.util.ArrayList;

public class Channel {
    String channelName;
    User owner;
    String channelDescription;
    ArrayList<Video> videos;
    ArrayList<Playlist> playlist;

    public Channel(String channelName, User owner, String channelDescription) {
        this.channelName = channelName;
        this.owner = owner;
        this.channelDescription = channelDescription;
        this.videos = new ArrayList<>();
        this.playlist = new ArrayList<>();
    }
}

