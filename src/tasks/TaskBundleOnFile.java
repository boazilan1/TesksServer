package tasks;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Stream;

import filehandel.AddTasksFile;

public class TaskBundleOnFile implements TaskBundle {
	private final Map<Task, MutableState> m_tasks = new HashMap<>();
	private String m_dirName = "tasks";
	private Path m_pathDirName = Paths.get(m_dirName);

	@Override
	public void add(Task task) {
		AddTasksFile.addTaskFile(task, new MutableState("false"));
	}

	@Override
	public final Iterator<Entry<Task, MutableState>> iterator() {
		TasksBundleOmMemory tasksBundleOmMemory = AddTasksFile.tasksBundleOmMemoryFromDisk(m_pathDirName);
		
		return tasksBundleOmMemory.iterator();
	}

	@Override
	public final boolean isEmpty() {
		return size() == 0 ? true : false;
	}

	@Override
	public final int size() {
		try (Stream<Path> files = Files.list(m_pathDirName)) {
			long count = files.count();
			return (int) count;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return 1;
	}

	@Override
	public final MutableState getState(Task task) {
		try {
			MutableState mutableState = AddTasksFile.getState(task, m_pathDirName);
			return mutableState;
		} catch (NoSuchFileException e) {
			e.printStackTrace();
		}
		throw new IllegalArgumentException("task not found: " + task.toString());
	}

	@Override
	public void deletAllTesks() {
		AddTasksFile.deletAllTesksOnFile(m_pathDirName);
	}
}
