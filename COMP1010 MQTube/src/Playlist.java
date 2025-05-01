package src;
import java.util.ArrayList;

public class Playlist {
    String playListName;
    ArrayList<Video> videos;
    User owner;

    public Playlist(String playListName, User owner) {
        this.playListName = playListName;
        this.owner = owner;
        this.videos = new ArrayList<>();
    }
}

