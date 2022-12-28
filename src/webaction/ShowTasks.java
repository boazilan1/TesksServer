package webaction;

import java.util.Map.Entry;

import pagegen.BasicParts;
import super_simple_web_server.SuperSimpleWebServer.Request;
import tasks.MutableState;
import tasks.Task;
import tasks.TaskBundle;
import tasks.TasksBundleOmMemory;

public class ShowTasks implements WebAction {
	@Override
	public String doAction(Request request, final String untrust_remainingUriParams, final TaskBundle tasks) {
		return BasicParts.tasksHeader(tasks) + BasicParts.tasksList(tasks);
		
	}
}
