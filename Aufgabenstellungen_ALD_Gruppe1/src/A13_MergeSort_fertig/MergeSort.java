package A13_MergeSort_fertig;

import java.util.Arrays;


public class MergeSort implements PersonenSort {
	
	/**
	 * Sortier-Funktion
	 */
	public void sort(Person[] personen) {
		// Start des rekursiven Aufrufs für das gesamte Array
		sort(personen, 0, personen.length-1);
	}

	/**
	 * Rekursive Funktion zum Sortieren eines Teils des Arrays
	 * @param personen Zu sortierendes Array
	 * @param start    Startpunkt im Array
	 * @param end      Endpunkt im Array
	 */
	public void sort(Person[] personen, int start, int end)
	{
		//Nur mehr 1 Element bei diesem Aufruf. Teile das Array nicht weiter sondern brich ab, damit im übergeordneten Aufruf
		//die Elemente in der richtigen Reihenfolge getauscht werden können.
		
		if((end - start) < 1) 
			return; 
		
		
		int mitte = start + (end - start)/2;
		
		//Array immer in der Mitte teilen, bis nur noch 1 Element übrig ist. 
		sort(personen, start, mitte); 
		sort(personen, mitte + 1, end); 
		
		// Für Merge: Hälften in eigene Arrays kopieren
		// Hinweis: bei copyOfRange ist Obergrenze exklusiv, deshalb "+ 1"
		Person[] teil1 = Arrays.copyOfRange(personen, start, mitte + 1);
		Person[] teil2 = Arrays.copyOfRange(personen, mitte + 1, end+1);
		
		// Die beiden extrahierten Hälften sind für sich schon sortiert. Nun müssen
		//aus den beiden Teilen die einzelnen Elemente in der richtigen Reihenfolge im gesamten Array eingefügt werden. 
		merge(teil1, teil2, personen, start);
	}

	/**
	 * Merge zweier Arrays in ein Ergebnis-Array
	 * @param pers1 Erstes Array
	 * @param pers2 Zweites Array
	 * @param result Ergebnisarray
	 * @param start  Position für Speicherung in Ergebnisarray
	 */
	public void merge(Person[] pers1, Person[] pers2, Person[] result, int start) {

		int indexP1 = 0; 
		int indexP2 = 0; 
		
		int i = 0; 
		
		while(indexP1 < pers1.length || indexP2 < pers2.length) //so lange ausführen, bis beide Teile einsortiert sind. 
		{
			Person p1 = null; 
			
			//Bestimme Person aus dem Teil 1, bis dieses vollständig abgearbeitet wurde
			if(indexP1 < pers1.length) 
				p1 = pers1[indexP1];
			
			Person p2 = null; 
			
			//Bestimme Person aus dem Teil 2, bis dieses vollständig abgearbeitet wurde
			if(indexP2 < pers2.length) 
				p2 = pers2[indexP2];
			
			//Schon alle Personen aus Teil 1 abgearbeitet. Füge nur noch Personen aus Teil 2 ins Gesamtarray. 
			if(p1 == null) 
			{
				result[start + i] = p2; 
				indexP2++; 
			}
			//Schon alle Personen aus Teil 2 abgearbeitet. Füge nur noch Personen aus Teil 1 ins Gesamtarray. 
			else if(p2 == null)
			{
				result[start + i] = p1; 
				indexP1++; 
			}
			
			//Personen aus beiden Teilen müssen noch eingefügt werden. Füge jene Person ein, die im Ergebnisarray vorher vorhanden sein muss. 
			else //compare the 2 persons. 
			{
				int cmpVal = p1.compareTo(p2); 
				
				if(cmpVal < 0) //Negativer Wert. P1 ist vor P2 -> Füge P1 ein.  
				{
					result[start + i] = p1; 
					indexP1++; 
				}
				else if(cmpVal > 0) //Positiver Wert. P1 ist nach P2 -> Füge P2 ein.  
				{
					result[start + i] = p2; 
					indexP2++; 
				}
			}
			
			i++; 
		}
	}

}
