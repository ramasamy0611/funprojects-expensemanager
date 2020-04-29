package com.ram.projects.expensemanager.db;

import org.springframework.scheduling.concurrent.ForkJoinPoolFactoryBean;
import org.springframework.scheduling.concurrent.ThreadPoolExecutorFactoryBean;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

public class RepositoryUtil {
    private Executor executorService;
    //TODO: fix
    /*public static  Executor executor(){
        new ThreadPoolExecutor();
    }*/
}
