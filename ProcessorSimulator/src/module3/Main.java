package module3;
import java.util.*;

public class Main {
	public static void main(String[] args) {
		Random rand = new Random();
		final int QUANTUM = 5;
		SimProcessor processor = new SimProcessor();
		ArrayList<ProcessControlBlock> ready = new ArrayList<ProcessControlBlock>();
		int readyUpTo = 0;
		ArrayList<ProcessControlBlock> blocked = new ArrayList<ProcessControlBlock>();
		int blockedUpTo = 0;
		ready.add(new ProcessControlBlock(new SimProcess(1, "Process 1", 100)));
		ready.add(new ProcessControlBlock(new SimProcess(2, "Process 2", 150)));
		ready.add(new ProcessControlBlock(new SimProcess(3, "Process 3", 265)));
		ready.add(new ProcessControlBlock(new SimProcess(4, "Process 4", 318)));
		ready.add(new ProcessControlBlock(new SimProcess(5, "Process 5", 150)));
		ready.add(new ProcessControlBlock(new SimProcess(6, "Process 6", 250)));
		ready.add(new ProcessControlBlock(new SimProcess(7, "Process 7", 319)));
		ready.add(new ProcessControlBlock(new SimProcess(8, "Process 8", 125)));
		ready.add(new ProcessControlBlock(new SimProcess(9, "Process 9", 167)));
		ready.add(new ProcessControlBlock(new SimProcess(10, "Process 10", 100)));
		ProcessState state;
		int timesThisProcess = 0;
		boolean contextSwitch = false;
		
		System.out.print("Restoring Process: " + ready.get(0).getProcess().getPid());
		processor.setCurrInstruction(ready.get(0).getCurrInstruction());
		System.out.print(" Instruction: " + processor.getCurrInstruction() + " - ");
		processor.setRegister1Value(ready.get(0).getRegister1Value());
		System.out.print("R1: " + processor.getRegister1Value() + ", ");
		processor.setRegister2Value(ready.get(0).getRegister2Value());
		System.out.print("R2: " + processor.getRegister2Value() + ", ");
		processor.setRegister3Value(ready.get(0).getRegister3Value());
		System.out.print("R3: " + processor.getRegister3Value() + ", ");
		processor.setRegister4Value(ready.get(0).getRegister4Value());
		System.out.println("R4: " + processor.getRegister4Value());
		processor.setCurrProcess(ready.get(0).getProcess());
		timesThisProcess = 0;
		
		for(int i=0; i<3000; i++) {
			System.out.print("Step " + (i+1) + ": ");
			if(contextSwitch) {
				System.out.println("Context Switch:");
				if(ready.size() == 1) {
					if(blocked.size() == 0) {
						System.out.println("All the processes are complete.");
						break;
					}
					System.out.println("The processor is idling.");
				}
				else {
					System.out.print("Saving Process: " + ready.get(0).getProcess().getPid());
					ready.get(readyUpTo).setCurrInstruction(processor.getCurrInstruction());
					System.out.print(" Instruction: " + processor.getCurrInstruction() + " - ");
					ready.get(readyUpTo).setRegister1Value(processor.getRegister1Value());
					System.out.print("R1: " + processor.getRegister1Value() + ", ");
					ready.get(readyUpTo).setRegister2Value(processor.getRegister2Value());
					System.out.print("R2: " + processor.getRegister2Value() + ", ");
					ready.get(readyUpTo).setRegister3Value(processor.getRegister3Value());
					System.out.print("R3: " + processor.getRegister3Value() + ", ");
					ready.get(readyUpTo).setRegister4Value(processor.getRegister4Value());
					System.out.println("R4: " + processor.getRegister4Value());
					
					ready.remove(0);
					
					System.out.print("Restoring Process: " + ready.get(0).getProcess().getPid());
					processor.setCurrInstruction(ready.get(0).getCurrInstruction());
					System.out.print(" Instruction: " + processor.getCurrInstruction() + " - ");
					processor.setRegister1Value(ready.get(0).getRegister1Value());
					System.out.print("R1: " + processor.getRegister1Value() + ", ");
					processor.setRegister2Value(ready.get(0).getRegister2Value());
					System.out.print("R2: " + processor.getRegister2Value() + ", ");
					processor.setRegister3Value(ready.get(0).getRegister3Value());
					System.out.print("R3: " + processor.getRegister3Value() + ", ");
					processor.setRegister4Value(ready.get(0).getRegister4Value());
					System.out.println("R4: " + processor.getRegister4Value());
					processor.setCurrProcess(ready.get(0).getProcess());
					timesThisProcess = 0;
					
					contextSwitch = false;
				}
			}
			else {//execute instruction
				System.out.print("Processing: Process: " + ready.get(0).getProcess().getProcName());
				System.out.print(" PID: " + ready.get(0).getProcess().getPid());
				System.out.print(" Executing Instruction: " + processor.getCurrInstruction());
				state = processor.executeNextInstruction();
				timesThisProcess++;
				if(state == ProcessState.BLOCKED) {
					System.out.println("*** Process Blocked ***");
					blocked.add(ready.get(readyUpTo));
					blockedUpTo++;
					contextSwitch = true;
				}
				else if(state == ProcessState.FINISHED) {
					System.out.println("*** Process Finished ***");
					contextSwitch = true;
				}
				else if(timesThisProcess == QUANTUM) {
					System.out.println("*** Quantum Expired ***");
					ready.add(ready.get(0));
					contextSwitch = true;
				}
			}
			for(int j=0; j<blockedUpTo; j++) {
				if(rand.nextInt(100) < 30) {
					ready.add(blocked.get(j));
					blocked.remove(j);
					blockedUpTo--;
				}
			}
		}
	}
}
