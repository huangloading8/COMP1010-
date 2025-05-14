package src;
import java.util.ArrayList;

public class Playlist {
    private String playlistName;
    private ArrayList<Video> videos;
    private User owner;

    public Playlist(String playlistName, User owner) {
        this.playlistName = playlistName;
        this.owner = owner;
        this.videos = new ArrayList<>();
    }

    public void addVideo(Video video) {
        videos.add(video);
    }

    public ArrayList<Video> getVideos() {
        return videos;
    }

    public String getPlaylistName() {
        return playlistName;
    }

    public User getOwner() {
        return owner;
} 
}
