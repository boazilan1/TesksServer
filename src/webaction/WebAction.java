package webaction;

import super_simple_web_server.SuperSimpleWebServer.Request;
import tasks.TasksBundle;

public interface WebAction {
	String doAction(final Request request, final String untrust_remainingUriParams, final TasksBundle tasks);
}
