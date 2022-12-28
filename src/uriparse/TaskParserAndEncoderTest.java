package uriparse;

import static org.junit.jupiter.api.Assertions.*;

import java.time.DateTimeException;

import org.junit.jupiter.api.Test;
import uriparse.TaskParser;
import tasks.Task;
import tasks.TaskAlreadyExistsException;

class TaskParserAndEncoderTest {

	@Test
	void parseOneTaskpe() {
		Task task = TaskParser.parseTask("rilax","20221211","1015");
		
	}
	
	@Test
	void parseTaskpeNullName() {
		assertThrowsExactly(NullPointerException.class,
				() -> TaskParser.parseTask(null,"20021200","2011"));
		
//		assertThrowsExactly(NullPointerException.class,		
//				() -> TaskParser.parseTask("rilax",null,"2011"));
		
		assertThrowsExactly(DateTimeException.class,
				() -> TaskParser.parseTask("rilax",null,"2011"));
		
		assertThrowsExactly(DateTimeException.class,
				() -> TaskParser.parseTask("rilax","20021200",null));
	}	
}
