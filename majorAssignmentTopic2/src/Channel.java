import java.util.ArrayList;

/*
 *This class represents a channel owned by a user.
 *It contans methods for uploading videos and managing playlists.
 */

public class Channel {
    private String channelName;
    private User owner;
    private String channelDescription;
    private ArrayList<Video> videos;
    private ArrayList<Playlist> playlists;

    // This is a Channel constructor with all required information about a channel
    public Channel(String channelName, User owner, String channelDescription) {
        this.channelName = channelName;
        this.owner = owner;
        this.channelDescription = channelDescription;
        this.videos = new ArrayList<>();
        this.playlists = new ArrayList<>();
    }

    // Method for adding a video to a channel and prints a confirmation message.
    public void uploadVideo(Video video) {
        videos.add(video);
    }

    // Method for creating a playlist for a channel (limiting to 5 playlists per channel)
    public Playlist createPlaylist(String name) {
        if (playlists.size() >= 5) {
            System.out.println("Cannot create more than 5 playlists.");
            return null;
        }
       Playlist newPlaylist = new Playlist(name, owner);
        playlists.add(newPlaylist);
        return newPlaylist;
    }

    // Method for delete a playlist from a channel
    public boolean removePlaylist(String name) {
        Playlist playlist = getPlaylistByName(name);
        if (playlist != null) {
            playlists.remove(playlist);
            System.out.println("Playlist \"" + name + "\" removed.");
            return true;
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

    // Method for removing a video from a channel, using the video ID
    public boolean removeVideoFromChannel(int videoID) {
        Video videoToRemove = getVideoById(videoID);
        if (videoToRemove != null) {
            videos.remove(videoToRemove);  
            
            for (Playlist playlist : playlists) {
                playlist.removeVideoById(videoID);
            }
            
            CSVUtils.removeVideoFromCSV(videoToRemove); 
           
            return true;
        }
       
        return false;
    }
    
    // Method to get a video by searching through the video ID
    public Video getVideoById(int id) {
        for (Video v : videos) {
            if (v.getVideoID() == id) {
                return v;
            }
        }
        return null;
    }

    public Playlist getPlaylistByName(String name) {
        for (Playlist p : playlists) {
            if (p.getPlaylistName().equals(name)) {
                return p;
            }
        }
        return null;
    }
}
