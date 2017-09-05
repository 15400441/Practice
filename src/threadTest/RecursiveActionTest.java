package threadTest;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveAction;

import javax.annotation.processing.ProcessingEnvironment;

public class RecursiveActionTest extends RecursiveAction {
	
	 private String workload = "";
	 private static final int THRESHOLD = 4;
	 
	 public RecursiveActionTest(String workload) {
	        this.workload = workload;
	 }
	 
	 
	 public static void main(String[] args) {
		System.out.println("starting running...");
		ForkJoinPool forkJoinPool=ForkJoinPool.commonPool();
	    RecursiveActionTest task=new RecursiveActionTest("adbkksaglajD");
	    forkJoinPool.invoke(task);
	   
	}
	 

	@Override
	protected void compute() {	
		if(workload.length()>THRESHOLD){
			ForkJoinTask.invokeAll(createSubtasks());
		}else{
			processing(workload);
		}
	}
	
	private List<RecursiveActionTest> createSubtasks() {
        List<RecursiveActionTest> subtasks = new ArrayList<>();
 
        String partOne = workload.substring(0, workload.length() / 2);
        String partTwo = workload.substring(workload.length() / 2, workload.length());
 
        subtasks.add(new RecursiveActionTest(partOne));
        subtasks.add(new RecursiveActionTest(partTwo));
 
        return subtasks;
    }
	
	private void processing(String work){
		String result=work.toUpperCase();
		System.out.println("reuslt:"+result+" was processed by "+Thread.currentThread().getName());
	}
	
	
	

}
