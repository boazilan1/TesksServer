package tasks;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TaskBundleOnFileTest extends TasksBundleTest{
	//public TaskBundle m_taskBundleOnFile ;
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		//m_taskBundleOnFile.deletAllTesks();
	}

	@AfterEach
	void tearDown() throws Exception {
		TaskBundle taskBundleOnFile = new TaskBundleOnFile();
		taskBundleOnFile.deletAllTesks();
	}

		
	@Override
	public TaskBundle createTasksBundle() {
		TaskBundle taskBundleOnFile = new TaskBundleOnFile();
		//taskBundleOnFile.deletAllTesks();
		return taskBundleOnFile;
	}

}
