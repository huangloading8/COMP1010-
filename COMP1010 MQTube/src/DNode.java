package src;

public class DNode {
    public int data;
	public DNode previous, next;
	public DNode(int data, DNode previous, DNode next) {
		this.data = data;
		this.previous = previous;
		if (previous != null) { previous.next = this; }
		if (next != null) { next.previous = this; }
	}
}
