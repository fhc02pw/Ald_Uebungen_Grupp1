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
			dist[i] = 9999; // Summen im Graph d�rfen nie mehr ergeben
		}
	}

	/**
	 * Berechnet *alle* k�rzesten Wege ausgehend vom Startknoten Setzt dist[]-
	 * und pred[]-Arrays, kein R�ckgabewert
	 * 
	 * @param from
	 *            Startknoten
	 */
	protected boolean calculatePath(int from, int to) {

		VertexHeap heap = new VertexHeap(graph.numVertices()); 
		
		//initialisiert die Vorg�nger und den Heap
		initPredecessorAndHeap(heap);
		
		//from ist unser startknoten und hat keine Kosten (Entfernung = 0). 
		heap.setCost(from, 0); //innerhalb von setCost wird ein swim durchgef�hrt, wodurch der Startknoten ganz nach oben wandert
		dist[from] = 0; 
		
		while(!heap.isEmpty()) //Algorithmus so lange wiederholen, bis der Heap leer ist (und somit alle Knoten abgearbeitet worden sind). 
		{
			//holt n�chsten Knoten aus dem Heap.
			Vertex currentV = heap.remove();
			
			//F�r jede Kante aus dem aktuellen Knoten muss f�r den Zielknoten die Entfernung vom Start aktualisiert werden (in dist und heap), 
			//wenn der neue Weg k�rzer ist als der alte. 
			List<WeightedEdge> edges = graph.getEdges(currentV.vertex);
			for(WeightedEdge we : edges)
			{
				int totalDistance = dist[currentV.vertex] + we.weight; 
				
				//Weg nur aktualisieren, wenn der neue k�rzer ist, als der alte. 
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

	/*
	 * Initialisiert die Vorg�nger pred[] auf -1 (Wir wissen aktuell nicht, von welchem Knoten wir zu welchem Knoten gelangen). Das 
	 * berechnet Dijkstra. 
	 * Weiters werden alle Knoten in den Heap eingef�gt (Im Heap befinden sich alle Knoten mit der aktuellen Distanz vom Start entfernt). 
	 * */
	private void initPredecessorAndHeap(VertexHeap heap) {
		pred = new int[graph.numVertices()];
		for(int i = 0; i < graph.numVertices(); i++)
		{
			pred[i] = -1; 
			heap.insert(new Vertex(i, dist[i]));
		}
	}
}
