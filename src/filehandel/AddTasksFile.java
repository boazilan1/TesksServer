package filehandel;

import java.io.BufferedReader;
import uriparse.TaskParser;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.DirectoryIteratorException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;

import tasks.MutableState;
import tasks.Task;
import tasks.TaskAlreadyExistsException;
import tasks.TaskBundle;
import tasks.TaskBundleOnFile;
import tasks.TaskIsNotOnDiskException;
import tasks.TasksBundleOmMemory;
import uriparse.TaskEncoder;
import uriparse.TaskParser;


public class AddTasksFile {
	private static final Logger logger = Logger.getLogger(AddTasksFile.class.getCanonicalName());

	public static void addTaskFile(final Task task, MutableState mutableState) {
		if(isTaskContainInFile(task,Paths.get("tasks"))) {
			throw new TaskAlreadyExistsException(task);
		}
		String taskString = TaskEncoder.encode(task);
		taskString += mutableState.isCompleted() ? "/true" : "/false";
		try {
			taskString = java.net.URLDecoder.decode(taskString, StandardCharsets.UTF_8.name());
		} 
		catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		String[] fullUri = taskString.split("/");
		try {
			Files.write(Paths.get("tasks/" + fullUri[0]), taskString.getBytes());
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void addTasksToBundeleFromFile(final TaskBundle tasksBundle, String fileName) throws IOException {
		final Path fullPath = Paths.get(fileName);
		List<Path> result = new ArrayList<>();

		try (DirectoryStream<Path> stream = Files.newDirectoryStream(fullPath, "*")) {
			for (Path entry : stream) {
				System.out.println(entry.toString());
				result.add(entry);
			}
		} catch (DirectoryIteratorException ex) {
			throw ex.getCause();
		} catch (IOException e) {
			e.printStackTrace();
		}

		for (Path p : result) {
			try (BufferedReader reader = Files.newBufferedReader(p, Charset.forName("UTF-8"))) {
				String currentLine = null;
				while ((currentLine = reader.readLine()) != null) {
					String[] taskParm = currentLine.split("/");
					Task task = TaskParser.parseTask(taskParm[0], taskParm[1], taskParm[2]);
					tasksBundle.add(task);
				}
			} 
			catch (IOException ex) {
				ex.printStackTrace(); // handle an exception here
			}
		}
	}

	public static void deletAllTesksOnFile(Path m_pathDirName) {
		try (DirectoryStream<Path> entries = Files.newDirectoryStream(m_pathDirName, "*")) {
			for (Path entry : entries) {
				Files.delete(entry);
			}
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static MutableState getState(Task task, Path m_pathDirName) throws NoSuchFileException {
		if (isTaskContainInFile(task, m_pathDirName)) {
			Path pathOndisk = taskPathOnDisk(task, m_pathDirName);

			try (BufferedReader reader = Files.newBufferedReader(pathOndisk, Charset.forName("UTF-8"))) {
				String currentLine = null;
				while ((currentLine = reader.readLine()) != null) {
					String[] taskParm = currentLine.split("/");
					return new MutableState(taskParm[3]);
				}
			} 
			catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		throw new TaskIsNotOnDiskException(task.getName() + "is not on disk");
	}

	private static boolean isTaskContainInFile(Task task, Path m_pathDirName) {
		try (DirectoryStream<Path> entries = Files.newDirectoryStream(m_pathDirName, "*")) {
			for (Path entry : entries) {
				if (entry.toString().endsWith("/" + task.getName())) {
					return true;
				}
			}
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	private static Path taskPathOnDisk(Task task, Path m_pathDirName) {
		try (DirectoryStream<Path> entries = Files.newDirectoryStream(m_pathDirName)) {
			for (Path entry : entries) {
				if (entry.toString().endsWith("/" + task.getName())) {
					return entry;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		throw new TaskIsNotOnDiskException(task.getName() + "is not on disk");
	}

	public static TasksBundleOmMemory tasksBundleOmMemoryFromDisk(Path m_pathDirName) {
		TasksBundleOmMemory tasksBundleOmMemory= new TasksBundleOmMemory();
		try (DirectoryStream<Path> entries = Files.newDirectoryStream(m_pathDirName)) {
			for (Path entry : entries) {
				try (BufferedReader reader = Files.newBufferedReader(entry, Charset.forName("UTF-8"))) {
					String currentLine = "";
					while ((currentLine = reader.readLine()) != null) {
						String[] taskParm = currentLine.split("/");						
						//logger.log(Level.INFO,taskParm[0]+taskParm[1]+taskParm[2]); 
						Task task = TaskParser.parseTask(taskParm[0],taskParm[1],taskParm[2]);
						tasksBundleOmMemory.add(task);
					}
				} 
				catch (IOException ex) {
					ex.printStackTrace();
				}
			}
		} 
		catch (IOException ex) {
			ex.printStackTrace();
		}
		return tasksBundleOmMemory;
	}
}
