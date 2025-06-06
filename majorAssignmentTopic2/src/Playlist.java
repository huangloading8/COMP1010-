/*
 * This class represents a playlist consisting of videos linked in a doubly linked list.
 * It contains methods to edit playlists, such as removing or adding video to playlists.
 */

public class Playlist {
    private String playlistName;
    private DNode start;
    private DNode end;
    private User owner;

    // This is the Playlist constructor with all of the required elements for a playlist
    public Playlist(String playlistName, User owner) {
        this.playlistName = playlistName;
        this.owner = owner;
        this.start = null;
        this.end = null;
    }

    public String getPlaylistName() {
        return playlistName;
    }

    // Method for adding video to playlist
    public void addVideoToPlaylist(Video video) {
        DNode newNode = new DNode(video);
        if (start == null) {
            // When the playlist is empty, the first video (start) is also the last video (end)
            start = newNode; 
            end = newNode;
        } else {
            // Append to the end and update links
            end.setNext(newNode);
            newNode.setPrevious(end);
            end = newNode;
        }
    }

    /*
     * Removes the specified node from the playlist.
     * Updates the links between other nodes so the list stays connected.
     *
     * @param dnode the node to remove; if null, no action is taken
     */
    public void removeVideoFromPlaylist(DNode dnode) {
        if (dnode == null) return;
    
        
        // Update start or end of the linked list if necessary
        if (dnode == start) start = dnode.getNext();
    
        if (dnode == end) end = dnode.getPrevious();
        
        
        // Link the nodes before and after dnode to each other
        if (dnode.getPrevious() != null) dnode.getPrevious().setNext(dnode.getNext());
    
        if (dnode.getNext() != null) dnode.getNext().setPrevious(dnode.getPrevious());

        
        // Reset the removed node’s previous and next pointers
        dnode.setPrevious(null);
        dnode.setNext(null);
    }

    // Recursive method to count the number of videos in the playlist
    public int countPlaylistVideos(DNode node) {
        if (node == null) {
            return 0;
        } else { 
            return 1 + countPlaylistVideos(node.getNext());
        }
    } 

    public boolean removeVideoById(int videoId) {
        DNode toRemove = findVideo(videoId);
        if (toRemove != null) {
            removeVideoFromPlaylist(toRemove);
            return true;
        }
        return false;
    }
    
    // Method for getting the starting video from the playlist
    public DNode getStart() {
        return start;
    }

    public Video searchVideoByTitle(DNode current, String title) {
        if (current == null) return null;
        if (current.getVideo().getTitle().equalsIgnoreCase(title)) {
            return current.getVideo();
        }
        return searchVideoByTitle(current.getNext(), title);
    }

    public DNode findVideo(int videoId) {
        DNode current = start;
        while (current != null) {
            if (current.getVideo().getId() == videoId) {
                return current;
            }
            current = current.getNext();
        }
        return null;
    }

    public boolean containsVideo(int videoId) {
        return findVideo(videoId) != null;
    }

    public User getOwner() {
        return owner;
    }
}

