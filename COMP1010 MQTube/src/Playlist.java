package src;

public class Playlist {
    private String playlistName;
    private User owner;
    private DNode start;
    private DNode end;

    public Playlist(String playlistName, User owner) {
        this.playlistName = playlistName;
        this.owner = owner;
        this.start = null;
        this.end = null;
    }

    public String getPlaylistName (Playlist playlist){
        return playlist.playlistName;
    }
    public String getPlaylistName() {
        return playlistName;
    }

    public void addVideoToPlaylist(Video video) {
        DNode newNode = new DNode(video);
        if (start == null) {
            start = newNode;
        } else {
            end.setNext(newNode);
            newNode.setPrevious(end);
            end = newNode;
        }
    }

    public void removeVideoFromPlaylist(DNode dnode) {
    if (dnode == null) {
        return;
    }
    if (dnode.getPrevious() != null) {
        dnode.getPrevious().setNext(dnode.getNext());
    }
    if (dnode.getNext() != null) {
        dnode.getNext().setPrevious(dnode.getPrevious());
    }
    dnode.setPrevious(null);
    dnode.setNext(null);
    }
    
    public DNode getStart() {
    return start;
}

}

