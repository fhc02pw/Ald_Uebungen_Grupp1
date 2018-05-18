package A10_DijkstraPQShortestPath_fertig;

import java.util.List;

public class DijkstraPQShortestPath extends FindWay {
	private int[] dist;

	public DijkstraPQShortestPath(Graph graph) {
		super(graph);
	}

	/**
	 * Startentfernung initialisieren
	 * 
	 * @param from
	 *            Startknoten
	 */
	protected void initPathSearch() {
		int numv = graph.numVertices();
		dist = new int[numv];
		for (int i = 0; i < numv; i++) {
			dist[i] = 9999; // Summen im Graph dürfen nie mehr ergeben
		}
	}

	/**
	 * Berechnet *alle* kürzesten Wege ausgehend vom Startknoten Setzt dist[]-
	 * und pred[]-Arrays, kein Rückgabewert
	 * 
	 * @param from
	 *            Startknoten
	 */
	protected boolean calculatePath(int from, int to) {

		VertexHeap heap = new VertexHeap(graph.numVertices()); 
		
		//vorgänger und heap initialisieren
		initPredecessorAndHeap(heap);
		
		//from ist unser startknoten und hat keine Kosten
		heap.setCost(from, 0);
		dist[from] = 0; 
		
		while(!heap.isEmpty())
		{
			Vertex currentV = heap.remove();
			List<WeightedEdge> edges = graph.getEdges(currentV.vertex);
			
			for(WeightedEdge we : edges)
			{
				int totalDistance = dist[currentV.vertex] + we.weight; 
				
				if(totalDistance < dist[we.to_vertex])
				{
					dist[we.to_vertex] = totalDistance;
					pred[we.to_vertex] = currentV.vertex;
					heap.setCost(we.to_vertex, totalDistance);
				}
			}
		}
		
		return true; 
		
	}

	private void initPredecessorAndHeap(VertexHeap heap) {
		pred = new int[graph.numVertices()];
		for(int i = 0; i < graph.numVertices(); i++)
		{
			pred[i] = -1; 
			heap.insert(new Vertex(i, dist[i]));
		}
	}
}
