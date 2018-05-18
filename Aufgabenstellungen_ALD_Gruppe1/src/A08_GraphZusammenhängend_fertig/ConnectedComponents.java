package A08_GraphZusammenhängend_fertig;

import basisAlgorithmen.Graph;
import basisAlgorithmen.WeightedEdge;

public class ConnectedComponents {
	
	/**
	 * Retourniert die Anzahl der zusammenhängenden Komponenten eines Graphen
	 * @param g zu prüfender Graph
	 * @return Anzahl der Komponenten
	 */
	public int getNumberOfComponents(Graph g) {
		int numOfComponents = 0; 
		Integer nodeToVisit = 0; 
		
		boolean[] visited = new boolean[g.numVertices()];  
		do
		{
			numOfComponents++; 
			visitNodes(g, nodeToVisit, visited); 
		}
		while((nodeToVisit = getNotVisitedNode(visited)) != null); 
		
		return numOfComponents;
	}

	private void visitNodes(Graph g, int vertice, boolean[] visited) {
		if(visited[vertice])
			return; 
		
		visited[vertice] = true; 
		for(WeightedEdge edge : g.getEdges(vertice))
		{
			visitNodes(g, edge.to_vertex, visited); 
		}
	}
	
	private Integer getNotVisitedNode(boolean[] visited)
	{
		for(int i = 0; i < visited.length; i++)
		{
			if(!visited[i])
				return i; 
		}
		
		return null; 
	}

}
