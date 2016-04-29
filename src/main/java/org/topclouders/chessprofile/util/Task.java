package org.topclouders.chessprofile.util;

import java.io.Serializable;

/**
 * Created by kokeny on 29/04/16.
 */
public final class Task implements Serializable {

    private transient final Thread thread;

    private final String taskName;

    private final long startTimeInMillis;

    private final String taskId;

    private final long threadId;

    private final String threadName;

    private long finishTimeInMillis = -1;

    private long runningTimeInMillis = -1;

    private boolean completed = false;

    Task(Thread thread, String taskName) {
        this.thread = thread;
        this.taskName = taskName;
        this.threadId = thread.getId();
        this.threadName = thread.getName();
        this.taskId = getThreadId() + "." + getThreadName() + "." + getTaskName();
        this.startTimeInMillis = System.currentTimeMillis();
    }

    public String getTaskName() {
        return taskName;
    }

    public Thread getThread() {
        return thread;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    public Object getThreadName() {
        return this.thread.getName();
    }

    public long getThreadId() {
        return this.thread.getId();
    }

    public String getTaskId() {
        return this.taskId;
    }

    public void stop() {
        if (this.completed) {
            throw new IllegalArgumentException("Cannot stop task, because the task +" + this.taskName + " is already completed");
        }
        this.finishTimeInMillis = System.currentTimeMillis();
        this.runningTimeInMillis = this.finishTimeInMillis - this.startTimeInMillis;
        this.completed = true;
    }

    public long getRunningTimeInMillis() {
        return runningTimeInMillis;
    }

    boolean isCompleted() {
        return completed;
    }
}