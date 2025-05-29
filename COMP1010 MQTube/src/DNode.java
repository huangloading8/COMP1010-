package src;

/*
 * This is a DNode class that will be used to create LinkList
 * The Playlist will be a double LinkList containing Video objects
 */

public class DNode {
    public Video video;
	public DNode previous, next;

    /*
     * Constructs a new DNode containing the given Video.
     * Initializes previous and next
     *
     * @param video The Video object to store in this node
     */
	public DNode(Video video) {
		this.video = video;
		this.previous = null;
        this.next = null;
	}

    public Video getVideo() {
        return video;
    }

    public DNode getNext() {
        return next;
    }

    public void setNext(DNode next) {
        this.next = next;
    }

    public DNode getPrevious() {
        return previous;
    }

    public void setPrevious(DNode previous) {
        this.previous = previous;
    }
}