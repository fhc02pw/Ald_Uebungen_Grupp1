package A12_DijkstraLand;

import java.util.ArrayList;
import java.util.List;

public class Dijkstra {

	public static List<Integer> dijkstra(Graph g, int von, int nach) {
		
		int[] pred = new int[g.numVertices()];
		int[] dist = new int[g.numVertices()];
		
		VertexHeap heap = new VertexHeap(g.numVertices()); 
		
		//initialisierung von Vorgängern & Distanz sowie initialisieren des Heaps
		for(int i = 0; i < g.numVertices(); i++)
		{
			pred[i] = -1; 
			dist[i] = 9999;
			heap.insert(new WeightedEdge(i, dist[i])); 
		}
		
		//von hat distanz 0
		dist[von] = 0; 
		heap.setPriority(von, 0);
		
		while(!heap.isEmpty())
		{
			//Kante zu einem Zielknoten
			WeightedEdge currentEdge = heap.remove(); 
			int currentVertex = currentEdge.vertex; 
			
			for(WeightedEdge we : g.getEdges(currentVertex))
			{
				//kante zum Zielknoten. Wenn der Zielknoten nicht mehr im Heap ist, haben wir dorthin schon den kürzesten weg. Mache mit nächsten Knoten weiter
				if(!heap.contains(we)) continue; 
				
				
				int totalDistance = dist[currentVertex] + we.weight; 
				
				String fromLand = g.getLand(currentEdge.vertex); 
				String toLand = g.getLand(we.vertex); 
				
				if(!fromLand.equals(toLand))
					totalDistance += 1; 
				
				if(totalDistance < dist[we.vertex])
				{
					dist[we.vertex] = totalDistance; 
					pred[we.vertex] = currentVertex; 
					heap.setPriority(we.vertex, totalDistance); //setze priorität des nächsten Knotens 
				}
			}
			
			
		}
		
		
		// pred ausgeben
		for(int i=0; i<pred.length; i++) {
			System.out.println(i + " über " + pred[i]);
		}
		
		
		// Way ausgeben
		System.out.println();
		ArrayList<Integer> way = predToWay(pred, von, nach);
		return way;
	}
	
	
	
	private static ArrayList<Integer> predToWay(int[] pred, int from, int to) {
		
		ArrayList<Integer> way = new ArrayList<Integer>(); 
		
		int current = to; 
		while(current >= 0)
		{
			way.add(0, current); 
			current = pred[current]; 
		}
		
		return way;
	}
	

}
