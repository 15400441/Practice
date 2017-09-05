package threadTest;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class ExecutorServiceTest {
	public static void main(String[] args) throws InterruptedException {
		Callable<String> callbackTask=()->{TimeUnit.SECONDS.sleep(2);
		return "taskFinish by "+Thread.currentThread().getName();
		};
		
		
		Callable<String> callbackTask2=()->{TimeUnit.SECONDS.sleep(5);
		return " 5 seconds taskFinish by "+Thread.currentThread().getName();
		};
		
		List<Callable<String>> tasks=new ArrayList<>();
		tasks.add(callbackTask2);
		tasks.add(callbackTask);
		tasks.add(callbackTask);
		System.out.println("start runing...");
		ExecutorService executorService=Executors.newFixedThreadPool(5);
		List<Future<String>> futures=executorService.invokeAll(tasks);
		
			futures.stream().forEach((f)->{
				try {
					System.out.println(f.get());
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ExecutionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});
		}
	

}
