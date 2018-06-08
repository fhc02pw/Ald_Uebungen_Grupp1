package A04_TraverseTree_fertig;

import java.util.HashSet;
import java.util.Set;


public class W�rterbuch {

	/**
	 * Wurzel des Baums (Startknoten)
	 */
	private Wort root;
	
	public Wort getRoot() {
		return root;
	}

	/**
	 * Z�hlt alle W�rter des Teilbaums ab einem bestimmten Wort
	 * @param w Wort
	 * @return Zahl der W�rter (=Anzahl der Elemente)
	 */
	public int countWordsInSubTree(Wort w) {
		if(w == null) //Wort gibt es im Baum nicht. 
			return 0; 
		
		int currentWordCount = 1; 							 	//�bergebenes Element selbst. 
		currentWordCount += countWordsInSubTree(w.getLeft());   //Rekursiver Aufruf, damit die Anzahl unter dem linken Teilbaum, berechnet wird
		currentWordCount += countWordsInSubTree(w.getRight());  //Rekursiver Aufruf, damit die Anzahl unter dem rechten Teilbaum, berechnet wird
		return currentWordCount; 
	}
	
	/**
	 * Liefert die Menge aller W�rter retour, die ein spezifisches Pr�fix haben.
	 * @param prefix W�rter m�ssen diesen Pr�fix haben
	 * @return Menge aller zutreffenden W�rter
	 */
	public Set<String> getWordsWithPrefix(String prefix) {
		
		Set<String> foundWords = new HashSet<>(); 
		addWordsWithPrefix(getRoot(), prefix, foundWords); //Startet die rekursive Suche im Baum f�r den gegebenen Prefix. 
		return foundWords;
		
	}
	
	private void addWordsWithPrefix(Wort w, String prefix, Set<String> set)
	{
		if(w == null) //Brich ab, wenn das Wort null ist
			return; 
		
		//Aus aktuellem Wort muss der Prefix extrahiert werden, damit der Vergleich funktioniert. 
		String prefixOfCurrentWord = w.getWort().substring(0, prefix.length());
		int cmpVal = prefix.compareTo(prefixOfCurrentWord);
		
		//Wenn die Prefixes �bereinstimmen, wird es dem Set hinzugef�gt. 
		if(cmpVal == 0)
			set.add(w.getWort());
		
		//Rekursiver Aufruf, damit die linken und rechten Teilb�ume ebenfalls durchsucht werden. 
		addWordsWithPrefix(w.getLeft(), prefix, set); 
		addWordsWithPrefix(w.getRight(), prefix, set); 
	}
	

	/**
	 * Neues Wort hinzuf�gen
	 * @param wort Hinzuzuf�gendes Wort
	 */
	public void add(String wort) {
		Wort neu = new Wort(wort);
		if (root == null) {			// Fall 1: Baum ist leer
			root = neu;
			return;
		}
		Wort w = root;				// Fall 2: Baum ist nicht leer
		while (true) {
			int vgl = wort.compareTo(w.getWort());
			if (vgl < 0) {			// Neues Wort ist lexikographisch kleiner
				if (w.getLeft() == null) {
					w.setLeft(neu);
					neu.setParent(w);
					return;
				}
				w = w.getLeft();
			}
			else if (vgl > 0) {		// Neues Wort ist lexikographisch gr��er
				if (w.getRight() == null) {
					w.setRight(neu);
					neu.setParent(w);
					return;
				}
				w = w.getRight();
			}
			else {					// Neues Wort ist lexikographisch gleich
				return;
			}
		}
	}

	public Wort find(String s) {
		return find(root, s);
	}
	
	private Wort find(Wort current, String s) {
		if (current == null) {
			return null;
		}
		int vgl = s.compareTo(current.getWort());
		if (vgl == 0) {		// Gefunden
			return current;
		}
		else if (vgl < 0) {	// Links
			return find(current.getLeft(), s);
		}
		else {				// Rechts
			return find(current.getRight(), s);
		}
	}
	
}
