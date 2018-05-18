package A09_ZyklenTiefensuche_fertig;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

import basisAlgorithmen.Graph;
import basisAlgorithmen.Vertex;
import basisAlgorithmen.WeightedEdge;

public class Zyklen {

	private Graph g;
	
	public Zyklen(Graph g) {
		this.g = g;
	}
	
	/**
	 * Retourniert einen Zyklus eines Graphen, sofern einer existiert
	 * @param g zu prüfender Graph
	 * @return Anzahl der Komponenten
	 */
	public List<Integer> getCycle() {

		boolean[] visited = new boolean[g.numVertices()]; 
		Integer nodeToVisit = 0; 
		
		do
		{
			List<Integer> checkCycleResult = checkCycle(visited, nodeToVisit, new LinkedList<Integer>());
			
			if(checkCycleResult != null) //if cycle is found, return it. Otherwise continue with not visited nodes
				return checkCycleResult; 
			
		}while((nodeToVisit = getNotVisitedNode(visited)) != null); 
		
		return null;
	}
	
	private List<Integer> checkCycle(boolean[] visited, Integer vertice, List<Integer> previousNodes)
	{
		if(visited[vertice])
		{
			if(previousNodes.contains(vertice)) //current node already found in path before.
			{
				//if graph is not directed, the second to last node is the current one, because the edges exist doubled
				if(!g.isDirected() && previousNodes.get(previousNodes.size() - 2) == vertice) 
					return null; 
				
				return getCycleResult(previousNodes, vertice); //error. Cycle found
			}
				
			return null; //node already visited. no need to check. 
		}
		else
		{
			visited[vertice] = true; 
			previousNodes.add(vertice); 
			
			for(WeightedEdge edge : g.getEdges(vertice))
			{
				//try to find cycle in subpaths. 
				List<Integer> checkCycleResult = checkCycle(visited, edge.to_vertex, previousNodes); 
				if(checkCycleResult != null)
					return checkCycleResult; 
			}
			
			previousNodes.remove(vertice); //no error found in subpaths. so remove the node from the path. 
		}
		
		return null; 
	}
	
	
	private List<Integer> getCycleResult(List<Integer> previousNodes, Integer vertice) {
		//will return the sub list of previous nodes. 
		
		List<Integer> result = new LinkedList<>(); 
		boolean verticeFound = false; 
		for(Integer node : previousNodes)
		{
			if(node.equals(vertice))
				verticeFound = true;
			if(verticeFound)
				result.add(node); 
		}
		
		result.add(vertice); //to show full cycle.
		return result;
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
