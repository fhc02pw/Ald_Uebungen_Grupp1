package A06_Tiefensuche_fertig;

import java.util.LinkedList;
import java.util.List;

import A05_Breitensuche_fertig.BaseTree;
import A05_Breitensuche_fertig.Node;

public class Tiefensuche extends BaseTree<Film> {

	@Override
	/**
	 * Sortierkriterium im Baum: L�nge des Films
	 */
	protected int compare(Film a, Film b) {

		return Double.compare(a.getL�nge(), b.getL�nge()); 
	}

	/**
	 * Retourniert die Titelliste der Film-Knoten des Teilbaums in symmetrischer Folge (engl. in-order, d.h. links-Knoten-rechts)
	 * @param node Wurzelknoten des Teilbaums
	 * @return Liste der Titel in symmetrischer Reihenfolge
	 */
	public List<String> getNodesInOrder(Node<Film> node) {

		List<String> filmsInOrder = new LinkedList<>(); 
		addNodesInOrder(node, filmsInOrder); //starte rekursiven Aufruf um Filme aus Teilbaum des �bergabefilms zu retournieren
		return filmsInOrder; 
	}
	
	public void addNodesInOrder(Node<Film> node, List<String> filmsInOrder) {

		if(node == null) //Brich ab, wenn kein Film gefunden
			return; 
		
		//Die Reihenfolge der aufgerufenen Methoden entspricht 'in-order' (links - Node - rechts)
		addNodesInOrder(node.getLeft(), filmsInOrder);  	//Suche (REKURSION) zuerst im linken Teilbaum und f�ge dort alle Elemente ein
		filmsInOrder.add(node.getValue().getTitel()); 		//f�ge aktuelles Element ein
		addNodesInOrder(node.getRight(), filmsInOrder); 	//Suche (REKURSION) im rechten Teilbaum und f�ge von dort alle Elemente ein 
	}
	
	/**
	 * Retourniert Titelliste jener Filme, deren L�nge zwischen min und max liegt, in Hauptreihenfolge (engl. pre-order, d.h. Knoten-links-rechts)
	 * @param min Minimale L�nge des Spielfilms
	 * @param max Maximale L�nge des Spielfilms
	 * @return Liste der Filmtitel in Hauptreihenfolge
	 */
	public List<String> getMinMaxPreOrder(double min, double max) {

		List<String> minMaxPreOrder = new LinkedList<>(); 
		addMinMaxPreOrder(this.getRoot(), min, max, minMaxPreOrder); //Starte rekursiven Aufruf
		return minMaxPreOrder; 
	}

	private void addMinMaxPreOrder(Node<Film> node, double min, double max, List<String> minMaxPreOrder) {
		
		if(node == null) //Brich ab, wenn kein Node
			return; 
		
		//Hole die L�nge des Filmtitels (Es werden ja nur Filme retouniert, welche innerhalb von min und max liegen)
		double laenge = node.getValue().getL�nge(); 
		
		//Ist der aktuelle Filmtitel innerhalb von min und max, so f�ge diesen dem Titel hinzu
		if(laenge <= max && laenge >= min)
			minMaxPreOrder.add(node.getValue().getTitel()); 
		
		//Wenn die aktuelle L�nge noch gr��er ist, als min, dann Suche im linken Teilbaum weiter, bis min unterschritten wurde
		if(min <= laenge)
			addMinMaxPreOrder(node.getLeft(), min, max, minMaxPreOrder); 
		
		//Wenn die aktuelle L�nge noch kleiner ist, als max, dann Suche im rechten Teilbaum weiter, bis max �berschritten wurde
		if(max >= laenge) 
			addMinMaxPreOrder(node.getRight(), min, max, minMaxPreOrder); 
	}

}
