package A13_MergeSort_fertig;

public class Person {
	
	private final String nachname;
	
	private final String vorname;

	public Person(String vorname, String nachname) {
		this.nachname = nachname;
		this.vorname = vorname;
	}

	public String getNachname() {
		return nachname;
	}
	
	public String getVorname() {
		return vorname;
	}
	
	/**
	 * Vergleicht zwei Personen miteinander
	 * @return <0, wenn a<b || =0, wenn a=b || >0, wenn a>b
	 */
	public int compareTo(Person p) {
		int cmpVal = this.getNachname().compareTo(p.getNachname()); 
		
		if(cmpVal != 0)
			return cmpVal; 
		
		return this.getVorname().compareTo(p.getVorname()); 
	}
}
