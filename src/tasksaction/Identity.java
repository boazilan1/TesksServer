package tasksaction;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import tasks.TaskBundle;
import tasks.TasksBundleOmMemory;

public class Identity implements TasksAction {
	@Override
	public TaskBundle doAction(TaskBundle tasks, Map<String, Object> params) {
		return tasks;
	}

	@Override
	public Map<String, Object> validate(List<String> untrust_params) {
		return new HashMap<>();
	}

}
