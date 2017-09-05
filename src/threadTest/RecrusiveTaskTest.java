package threadTest;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
    
    
public class RecrusiveTaskTest extends RecursiveTask<Long> {

    private long workLoad = 0;

    public RecrusiveTaskTest(long workLoad) {
        this.workLoad = workLoad;
    }
    
    
    
    public static void main(String[] args) {
		ForkJoinPool pool=ForkJoinPool.commonPool();
		RecursiveTask< Long> task=new RecrusiveTaskTest(100);
		long mergedResult=pool.invoke(task);
		System.out.println("mergedResult:"+mergedResult);
	}

    protected Long compute() {

        //if work is above threshold, break tasks up into smaller tasks
        if(this.workLoad > 16) {
            System.out.println("Splitting workLoad : " + this.workLoad);

            List<RecrusiveTaskTest> subtasks =
                new ArrayList<RecrusiveTaskTest>();
            subtasks.addAll(createSubtasks());

            for(RecrusiveTaskTest subtask : subtasks){
                subtask.fork();
            }

            long result = 0;
            for(RecrusiveTaskTest subtask : subtasks) {
                result += subtask.join();
            }
            return result;

        } else {
            System.out.println("Doing workLoad myself: " + this.workLoad);
            return workLoad * 3;
        }
    }

    private List<RecrusiveTaskTest> createSubtasks() {
        List<RecrusiveTaskTest> subtasks =
        new ArrayList<RecrusiveTaskTest>();

        RecrusiveTaskTest subtask1 = new RecrusiveTaskTest(this.workLoad / 2);
        RecrusiveTaskTest subtask2 = new RecrusiveTaskTest(this.workLoad / 2);

        subtasks.add(subtask1);
        subtasks.add(subtask2);

        return subtasks;
    }
}