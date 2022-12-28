package tasksaction;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import tasks.TaskBundle;
import tasks.TasksBundleOmMemory;

class CompletionActionTest {

	@Test
	void testConstractor() {
		CompletionAction completionAction = new CompletionAction();
	}
	
	private TaskBundle addCompletionAction(TaskBundle tasks ,List<String> input,String addToInput) {		
		AddTaskAction act = new AddTaskAction();
		Map<String, Object> params = act.validate(input);
		tasks = act.doAction(tasks, params);
		
		List<String> arraylist = new ArrayList<>(input);
		arraylist.add(addToInput);
		
		CompletionAction completionAction = new CompletionAction();
		Map<String, Object> params1 = completionAction.validate(arraylist);
		
		return completionAction.doAction(tasks, params1);
	}
	
	@Test
	void testdoAction() {		
		TaskBundle tasks = new TasksBundleOmMemory();
		assertEquals(0, tasks.size());

		List<String> input = Arrays.asList("MyTask", "20221225", "1251");
		tasks = addCompletionAction(tasks,input,"false");
		assertEquals(1, tasks.size());
		
	}

}
