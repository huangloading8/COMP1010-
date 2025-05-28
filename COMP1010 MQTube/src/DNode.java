package src;

public class DNode {
    public Video video;
	public DNode previous, next;

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