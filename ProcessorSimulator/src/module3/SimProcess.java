package module3;
import java.util.Random;

public class SimProcess {
	private int pid;
	private String procName;
	private int totalInstructions;
	Random rand = new Random();
	
	public SimProcess(int pid, String procName, int totalInstructions) {
		this.pid = pid;
		this.procName = procName;
		this.totalInstructions = totalInstructions;
	}
	
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public String getProcName() {
		return procName;
	}
	public void setProcName(String procName) {
		this.procName = procName;
	}
	public int getTotalInstructions() {
		return totalInstructions;
	}
	public void setTotalInstructions(int totalInstructions) {
		this.totalInstructions = totalInstructions;
	}

	public ProcessState execute(int i) {
		System.out.println("PID: " + pid + " Process: " + procName + " Executing: " + i);
		if(i >= totalInstructions) {
			return ProcessState.FINISHED;
		}
		
		else if(rand.nextInt(100) < 15) {
			return ProcessState.BLOCKED;
		}
		else {
			return ProcessState.READY;
		}
	}
}
