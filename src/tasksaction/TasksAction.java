package tasksaction;

import java.util.List;
import java.util.Map;

import super_simple_web_server.SuperSimpleWebServer.Request;
import tasks.TaskBundle;
import tasks.TasksBundleOmMemory;

public interface TasksAction {
	TaskBundle doAction(final TaskBundle tasks, final Map<String, Object> params);
	Map<String, Object> validate(final List<String> untrust_params);
}
