package A05_Breitensuche_fertig;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Breitensuche extends BaseTree<Integer> {

	@Override
	protected int compare(Integer a, Integer b) {
		return a.compareTo(b);
	}

	/**
	 * Liefert Knoten des Baums ausgehend von Start in Reihenfolge der Breitensuche zurück
	 * @param start Startknoten für Teilbaum
	 * @return Liste der Knoten in Breitenfolge
	 */
	public List<Integer> getBreadthFirstOrder(Node<Integer> start) {
		Queue<Node<Integer>> nodesToSearch = new LinkedList<>(); 
		nodesToSearch.add(start); 
		List<Integer> results = new LinkedList<>(); 
		addResults(nodesToSearch, results); 
		
		return results; 
	}

	private void addResults(Queue<Node<Integer>> nodesToSearch, List<Integer> results) {
		
		if(nodesToSearch.isEmpty())
			return; 
		
		Node<Integer> nextNodeToSearch = nodesToSearch.poll(); 
		
		if(nextNodeToSearch != null)
		{
			results.add(nextNodeToSearch.getValue()); 
			nodesToSearch.add(nextNodeToSearch.getLeft()); 
			nodesToSearch.add(nextNodeToSearch.getRight()); 
		}
		
		addResults(nodesToSearch, results); 
	}

	/**
	 * Liefert Knoten des Baums ausgehend von Start in Reihenfolge der Breitensuche zurück,
	 * allerdings nur jene Knoten, die in der angegebenen Ebene liegen (Start hat Ebene=1)
	 * @param start Startknoten für Teilbaum
	 * @param level Nur Knoten dieser Ebene ausgeben
	 * @return Liste aller Knoten
	 */
	public List<Integer> getBreadthFirstOrderForLevel(Node<Integer> start, int level) {

		List<Integer> results = new LinkedList<>(); 
		Queue<NodeWrapper> nodesToSearch = new LinkedList<>();
		nodesToSearch.add(new NodeWrapper(start, 1)); 
		addNodesForLevel(nodesToSearch, results, level); 
		return results; 
	}
	
	private void addNodesForLevel(Queue<NodeWrapper> nodesToSearch, List<Integer> results, int level)
	{
		if(nodesToSearch.isEmpty())
			return; 
		
		NodeWrapper currentNodeWrapper = nodesToSearch.poll(); 
		Node<Integer> currentNode = currentNodeWrapper.getNode(); 
		
		if(currentNode != null)
		{			
			int currentLevel = currentNodeWrapper.getLevel(); 
			
			if(currentLevel == level)
			{
				results.add(currentNode.getValue());
			}
			else
			{
				nodesToSearch.add(new NodeWrapper(currentNode.getLeft(), currentLevel + 1));
				nodesToSearch.add(new NodeWrapper(currentNode.getRight(), currentLevel + 1));
			}
		}
		
		
		addNodesForLevel(nodesToSearch, results, level);
	}

}
