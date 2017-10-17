package com.bravo.easyhttp;

import android.util.Log;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2017/10/17.
 */

public class ThreadPoolManager {
    private static final String TAG = "bravo";
    private static ThreadPoolManager instance = new ThreadPoolManager();
    LinkedBlockingQueue<FutureTask<?>> linkedBlockingQueue = new LinkedBlockingQueue<>();
    private final ThreadPoolExecutor threadPoolExecutor;

    public ThreadPoolManager() {
        int num = Runtime.getRuntime().availableProcessors();
        threadPoolExecutor = new ThreadPoolExecutor(num,num*2,num*2, TimeUnit.SECONDS,new ArrayBlockingQueue<Runnable>(num),handler);
        threadPoolExecutor.execute(command);
    }

    public static ThreadPoolManager getInstance(){
        return instance;
    }

    public<T> void excute(FutureTask<T> futureTask) throws InterruptedException {
        linkedBlockingQueue.put(futureTask);
    }

  private   RejectedExecutionHandler handler = new RejectedExecutionHandler() {
      @Override
      public void rejectedExecution(Runnable runnable, ThreadPoolExecutor threadPoolExecutor) {
          try {
              linkedBlockingQueue.put(new FutureTask<Object>(runnable,null) {
              });
          } catch (InterruptedException e) {
              e.printStackTrace();
          }
      }
  };

    Runnable command = new Runnable() {
        @Override
        public void run() {
            while (true){
                FutureTask<?> futureTask = null;
                try {
                    futureTask = linkedBlockingQueue.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if (futureTask != null){
                    Log.e(TAG,"执行一个任务。");
                    threadPoolExecutor.execute(futureTask);
                }

                Log.e(TAG,"线程池大小      "+threadPoolExecutor.getPoolSize());
            }
        }
    };

}
