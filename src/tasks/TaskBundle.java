package tasks;

import java.util.Iterator;
import java.util.Map.Entry;

public interface TaskBundle  extends Iterable<Entry<Task, MutableState>>{
	
	public  void add(final Task task);
	public  Iterator<Entry<Task, MutableState>> iterator();
	public  boolean isEmpty();
	public  int size();
	public  MutableState getState(Task task);
	public  void deletAllTesks();
}
