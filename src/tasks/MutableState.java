package tasks;

public class MutableState {
	private boolean m_isCompleted = false;
	
	
	public MutableState(String isCompleted) {
		m_isCompleted = isCompleted.equals("true") ? true : false;
	}

	public MutableState() {
		// TODO Auto-generated constructor stub
	}

	public final boolean isCompleted() {
		return m_isCompleted;
	}
	
	public final void setCompleted(boolean isCompleted) {
		m_isCompleted = isCompleted;
	}
}
