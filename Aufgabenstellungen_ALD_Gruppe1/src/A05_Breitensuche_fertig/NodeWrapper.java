package A05_Breitensuche_fertig;

public class NodeWrapper {
	private Node<Integer> node;
	private int level; 
	
	
	
	
	public NodeWrapper(Node<Integer> node, int level) {
		super();
		this.node = node;
		this.level = level;
	}
	
	public int getLevel() {
		return level;
	}
	public Node<Integer> getNode() {
		return node;
	} 
	
	
	
}
