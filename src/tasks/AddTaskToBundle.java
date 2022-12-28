package tasks;

import org.junit.jupiter.api.function.Executable;

public class AddTaskToBundle implements Executable {
	TaskBundle m_tasksBundle;
	Task m_task;

	AddTaskToBundle(final TaskBundle tasksBundle, final Task task) {
		m_tasksBundle = tasksBundle;
		m_task = task;
	}

	@Override
	public void execute() throws Throwable {
		m_tasksBundle.add(m_task);
	}
}
