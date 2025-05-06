package src;
import java.util.ArrayList;

public class Channel {
    private String channelName;
    private User owner;
    private String channelDescription;
    private ArrayList<Video> videos;
    private ArrayList<Playlist> playlists;

    public Channel(String channelName, User owner, String channelDescription) {
        this.channelName = channelName;
        this.owner = owner;
        this.channelDescription = channelDescription;
        this.videos = new ArrayList<>();
        this.playlists = new ArrayList<>();
    }

    public void uploadVideo(Video video) {
        videos.add(video);
    }

    public void createPlaylist(String name) {
        playlists.add(new Playlist(name, owner));
    }

    public ArrayList<Video> getVideos() {
        return videos;
    }

    public ArrayList<Playlist> getPlaylists() {
        return playlists;
    }

    public String getChannelName() {
        return channelName;
    }
}
