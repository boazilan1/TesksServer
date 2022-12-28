package tasks;

public class TaskIsNotOnDiskException extends RuntimeException {

	public TaskIsNotOnDiskException(String message){
		super(message);
	}
}
