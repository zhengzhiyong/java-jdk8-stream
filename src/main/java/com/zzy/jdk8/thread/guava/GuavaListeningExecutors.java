package com.zzy.jdk8.thread.guava;

import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import static java.util.concurrent.Executors.newFixedThreadPool;

public class GuavaListeningExecutors {

    public ListeningExecutorService createListeningExecutorService(){
        return MoreExecutors.listeningDecorator(newFixedThreadPool(4));
    }
}
