package module3;

public class ProcessControlBlock {
	private SimProcess process;
	private int currInstruction;
	private int register1;
	private int register2;
	private int register3;
	private int register4;

	public ProcessControlBlock(SimProcess process) {
		super();
		this.process = process;
		this.currInstruction = 1;
	}
	
	public SimProcess getProcess() {
		return process;
	}
	public int getCurrInstruction() {
		return currInstruction;
	}
	public void setCurrInstruction(int currInstruction) {
		this.currInstruction = currInstruction;
	}
	public int getRegister1Value() {
		return register1;
	}
	public void setRegister1Value(int register1) {
		this.register1 = register1;
	}
	public int getRegister2Value() {
		return register2;
	}
	public void setRegister2Value(int register2) {
		this.register2 = register2;
	}
	public int getRegister3Value() {
		return register3;
	}
	public void setRegister3Value(int register3) {
		this.register3 = register3;
	}
	public int getRegister4Value() {
		return register4;
	}
	public void setRegister4Value(int register4) {
		this.register4 = register4;
	}
}
