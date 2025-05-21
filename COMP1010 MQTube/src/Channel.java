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
        System.out.println("Video \"" + video.getTitle() + "\" uploaded to channel: " + channelName); // upload successful message
    }

    public void createPlaylist(String name) {
        playlists.add(new Playlist(name, owner));
        System.out.println("Playlist \"" + name + "\" created.");
    }

    public boolean removePlaylist(String name) {
        for (int i = 0; i < playlists.size(); i++) {
            if (playlists.get(i).getPlaylistName().equals(name)) {  
                playlists.remove(i);
                System.out.println("Playlist \"" + name + "\" removed.");
                return true;
            }
        }
        System.out.println("Playlist \"" + name + "\" not found.");
        return false;
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

    public User getOwner() {
        return owner;
    }

    public String getChannelDescription() {
        return channelDescription;
    }
    
    public void setChannelName(String newName) {
        this.channelName = newName;
    }
    
    public void setDescription(String newDescription) {
        this.channelDescription = newDescription;
    }
    

    public boolean removeVideo(int videoID) {
        for (int i = 0; i < videos.size(); i++) {
            if (videos.get(i).getVideoID() == videoID) {
                Video videoToRemove = videos.get(i);
                videos.remove(i);                    
                CSVUtils.removeVideoFromCSV(videoToRemove); 
                System.out.println("Video ID " + videoID + " has been removed.");
                return true;
            }
        }
        System.out.println("Video ID " + videoID + " not found.");
        return false;
    }
    

    

}
