package filehandel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.DirectoryIteratorException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import tasks.Task;
import tasks.TasksBundle;
import uriparse.TaskEncoder;
import uriparse.TaskParser;
public class AddTasksFile {

	public static void addTaskFile(final Task task)  {
		String taskString = TaskEncoder.encode(task);		
		try {
			taskString = java.net.URLDecoder.decode(taskString, StandardCharsets.UTF_8.name());
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		String[] fullUri = taskString.split("/");		
		try {
			Files.write(Paths.get("tasks/"+fullUri[0]), taskString.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void addTasksToBundeleFromFile(final TasksBundle tasksBundle, String fileName) throws IOException {
		final Path fullPath = Paths.get(fileName);						
		List<Path> result = new ArrayList<>();
		
      try (DirectoryStream<Path> stream = Files.newDirectoryStream(fullPath, "*")) {
          for (Path entry: stream) {
              result.add(entry);
          }
      } catch (DirectoryIteratorException ex) {
          throw ex.getCause();
      } catch (IOException e) {
			e.printStackTrace();
		}
      
      for(Path p :result) {
      	try (BufferedReader reader = Files.newBufferedReader(p, Charset.forName("UTF-8"))) {
   			String currentLine = null;
   			while ((currentLine = reader.readLine()) != null) {
   				String[] taskParm = currentLine.split("/");
   				Task task = TaskParser.parseTask(taskParm[0],taskParm[1],taskParm[2]);
   				tasksBundle.add(task);
   			}
   		} catch (IOException ex) {
   			ex.printStackTrace(); // handle an exception here
   		}
      }      
      
      
      
      
      
      
      
		//tasksBundle.add(new Task("Buy milk", LocalDateTime.of(LocalDate.now(), LocalTime.of(12, 15))));
	}
}
