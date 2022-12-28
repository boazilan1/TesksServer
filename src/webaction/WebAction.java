package webaction;

import super_simple_web_server.SuperSimpleWebServer.Request;
import tasks.TaskBundle;


public interface WebAction {
	String doAction(final Request request, final String untrust_remainingUriParams, final TaskBundle tasks);
}
