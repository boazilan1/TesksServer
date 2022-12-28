package tasks;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Iterator;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.jupiter.api.Test;

import filehandel.AddTasksFile;
import uriparse.TaskParser;

abstract class TasksBundleTest {
	private static final Logger logger = Logger.getLogger(TasksBundleTest.class.getCanonicalName());

	public abstract TaskBundle createTasksBundle(); // factory method

	@Test
	void addOneTask() {
		TaskBundle tasksBundle = createTasksBundle();
		Task task1 = TaskParser.parseTask("go to sleep", "20220612", "1200");
		tasksBundle.add(task1);
		assertEquals(tasksBundle.size(), 1);
	}

	@Test
	void addMultioulTask() {
		TaskBundle tasksBundle = createTasksBundle();
		Task task1 = TaskParser.parseTask("go to sleep", "20220612", "1200");
		Task task2 = TaskParser.parseTask("wake up", "20220612", "0800");
		tasksBundle.add(task1);
		tasksBundle.add(task2);
		assertEquals(tasksBundle.size(), 2);
	}

	@Test
	void addDupictionTask() {
		TaskBundle tasksBundle = createTasksBundle();
		Task task1 = TaskParser.parseTask("go to sleep", "20220612", "1200");
		Task task2 = TaskParser.parseTask("go to sleep", "20220612", "1200");

		tasksBundle.add(task1);
		AddTaskToBundle addTaskToBundle = new AddTaskToBundle(tasksBundle, task2);
		assertThrowsExactly(TaskAlreadyExistsException.class, addTaskToBundle);
		assertEquals(tasksBundle.size(), 1);
	}

	@Test
	void addNull() {
		TaskBundle tasksBundle = createTasksBundle();
		AddTaskToBundle addTaskToBundle = new AddTaskToBundle(tasksBundle, null);
		//assertThrowsExactly(NullPointerException.class, () -> tasksBundle.add(null));
		//assertEquals(tasksBundle.size(), 0); // need to fix
	}
	
	@Test
	void getMutableStateFromTask() {
		TaskBundle tasksBundle = createTasksBundle();
		Task task1 = TaskParser.parseTask("go to sleep", "20220612", "1200");
		tasksBundle.add(task1);
		assertEquals(tasksBundle.getState(task1).isCompleted(),false);
	}
	@Test
	void moltiTasksItartor() {
		TaskBundle tasksBundle = createTasksBundle();
		Task task1 = TaskParser.parseTask("go to sleep", "20220612", "1200");
		Task task2 = TaskParser.parseTask("wake up", "20220612", "0800");
		Task task3 = TaskParser.parseTask("chill", "20220612", "0800");
		tasksBundle.add(task1);
		tasksBundle.add(task2);
		tasksBundle.add(task3);
		//Iterator itr = tasksBundle.iterator();	
		for(Entry<Task, MutableState> o : tasksBundle) {
			logger.log(Level.INFO, o.getKey().getName().toString());
		}
	}
	

}
