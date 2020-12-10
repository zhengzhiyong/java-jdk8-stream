package com.zzy.jdk8.thread.guava;

import org.junit.jupiter.api.Test;
import java.util.concurrent.*;

public class GuavaTest {

    private GuavaService guavaService = new GuavaService();

    @Test
    public void test1() throws InterruptedException {
        long start = System.currentTimeMillis();
        Long userTime = guavaService.getUserList();
        Long orderTime = guavaService.getOrderList();
        Long messageTime = guavaService.getMessageList();
        Long time = userTime + orderTime + messageTime;
        long end = System.currentTimeMillis();
        System.out.println("计算结果："+ time + "秒，共耗时：" + (end -start)+"毫秒");
    }

    @Test
    public void test2() throws InterruptedException, ExecutionException {
        long start = System.currentTimeMillis();
        ExecutorService service = Executors.newFixedThreadPool(3);

        Future userFuture =  service.submit(()->{
            return guavaService.getUserList() ;
        });
        Future orderFuture = service.submit(()-> {
            return guavaService.getOrderList();
        });
        Future messageFuture = service.submit(()-> {
            return guavaService.getMessageList();
        });

        Long userTime = (Long) userFuture.get();
        Long orderTime = (Long) orderFuture.get();
        Long messageTime = (Long) messageFuture.get();
        Long time = userTime + orderTime + messageTime;
        long end = System.currentTimeMillis();
        System.out.println("执行结果为："+time+"秒，共耗时：" + (end -start)+"毫秒");
    }
}
