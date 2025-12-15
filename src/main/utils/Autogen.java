package main.utils;

import java.util.concurrent.atomic.AtomicInteger;

public class Autogen {
    private static AtomicInteger projectID = new AtomicInteger(0);
    private static AtomicInteger taskID = new AtomicInteger(0);

    public static String addProject(){
        projectID.getAndIncrement();
        return String.format("P%03d",projectID.get());
    }

    public static String addTask(){
        taskID.getAndIncrement();
        return String.format("T%03d", taskID.get());

    }
}
