package tasks;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MutableStateTest {
	
	private MutableState m_mutableState;
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		m_mutableState = new MutableState();
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void constructor() {			
		assertEquals(m_mutableState.isCompleted(),false);
	}
	
	@Test
	void changeState() {
		m_mutableState.setCompleted(true);
		assertEquals(m_mutableState.isCompleted(),true);
	}
	
	@Test
	void changeStates() {
		m_mutableState.setCompleted(true);
		assertEquals(m_mutableState.isCompleted(),true);
		m_mutableState.setCompleted(false);
		assertEquals(m_mutableState.isCompleted(),false);
	}

}
