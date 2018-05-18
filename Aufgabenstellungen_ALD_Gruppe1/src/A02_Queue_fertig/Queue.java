package A02_Queue_fertig;

public class Queue<T>
{
    private Node<T> first;
    
    private Node<T> last;
    /**
     * Das vorderste (=erste) Element aus der Queue entfernen und zurückliefern.
     * Existiert kein Element, wird eine Exception ausgelöst.
     * @throws QueueEmptyException 
     */
    public T dequeue() throws QueueEmptyException {

    	if(first == null)
    		throw new QueueEmptyException(); 
    	
    	T val = first.getData(); 
    	first = first.getNext(); 
    	return val; 
    }
    
    
    
    /**
     * Übergebenen Integer am Ende der Queue anhängen.
     * @param i Zahl
     */
    public void enqueue(T i) {
    	Node<T> node = new Node(i); 
    	
    	if(last != null) //only set pointer of last if last isn´t null
    		last.setNext(node);
    	if(first == null) //if queue is empty set first node
    		first = node; 
    	
    	last = node; 
    }
    
    /**
     * Liefert die Anzahl der Elemente im Stack
     * @return
     */
    public int getCount() {
    	int count = 0; 
    	Node<T> node = first; 
    	
    	while(node != null)
    	{
    		count++; 
    		node = node.getNext(); 
    	}
    	
    	return count; 
    }
}
