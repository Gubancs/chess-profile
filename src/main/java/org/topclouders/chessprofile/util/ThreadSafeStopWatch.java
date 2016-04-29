/*
 * Copyright 2016 TopClouders Hungary Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.topclouders.chessprofile.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.concurrent.ThreadSafe;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.text.NumberFormat;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by kokeny on 01/04/16.
 */
@ThreadSafe
public class ThreadSafeStopWatch implements StopWatch {

    private static final Logger LOG = LoggerFactory.getLogger(ThreadSafeStopWatch.class);

    private static final String DEFAULT_TASK_NAME = "task";

    private final Map<String, Task> queuedTasks = Collections.synchronizedMap(new HashMap<>());

    private final List<Task> completedTasks = Collections.synchronizedList(new ArrayList<>());

    private final AtomicLong totalTimeInMillis = new AtomicLong(0);

    @Override
    public StopWatch start() {
        this.startTask(DEFAULT_TASK_NAME);
        return this;
    }

    @Override
    public StopWatch stop() {

        if (LOG.isDebugEnabled()) {
            LOG.debug("The task '" + Thread.currentThread().getId() + "." + Thread.currentThread().getName() + "' has been stopped at " + GregorianCalendar.getInstance().getTime());
        }

        final String threadKey = prepareThreadKey();

        if (this.queuedTasks.containsKey(threadKey) == false) {
            throw new IllegalStateException("Can\'t stop StopWatch: it\'s not running");
        }

        final Task finishedTask = this.queuedTasks.remove(threadKey);
        finishedTask.stop();

        completedTasks.add(finishedTask);

        totalTimeInMillis.addAndGet(finishedTask.getRunningTimeInMillis());
        return this;
    }

    @Override
    public StopWatch startTask(String taskName) {
        final String threadKey = prepareThreadKey();
        Task currentTask = this.queuedTasks.get(threadKey);

        if (currentTask != null) {
            throw new IllegalStateException("You have to stop current task before start new one.");
        }

        final Task newTask = new Task(Thread.currentThread(), taskName);
        this.queuedTasks.put(threadKey, newTask);

        if (LOG.isDebugEnabled()) {
            LOG.debug("The task '" + newTask.getTaskId() + "' has been started at " + GregorianCalendar.getInstance().getTime());
        }

        return this;
    }

    @Override
    public String prettyPrint() {

        if (queuedTasks.size() != 0) {
            throw new IllegalStateException("You have to stop all tasks before invoke prettyPrint(), " +
                    "please use CountDownLatch to wait until\n" +
                    " * a set of tasks being performed in other threads completes");
        }

        final StringBuffer sb = new StringBuffer();

        final NumberFormat nf = NumberFormat.getNumberInstance();
        nf.setMinimumIntegerDigits(5);
        nf.setGroupingUsed(false);

        sb.append("---------------------------------------------------------------\n");
        sb.append("|  ms     %     Task name\n");
        sb.append("---------------------------------------------------------------\n");
        if (completedTasks.size() > 0) {
            for (Task task : completedTasks) {
                sb.append("|  ").append(nf.format(task.getRunningTimeInMillis())).append("  ");
                long percent = Math.round(100.0D * task.getRunningTimeInMillis() / this.totalTimeInMillis.get());
                sb.append(String.format("%02d", percent)).append("%").append("  ");
                sb.append(task.getTaskName()).append("\n");
            }
        } else {
            sb.append("| no task info kept\n");
        }
        sb.append("---------------------------------------------------------------\n");
        sb.append("|  No. tasks        : " + this.completedTasks.size() + "\n");
        sb.append("|  Total time       : " + this.totalTimeInMillis.get() + " ms\n");
        sb.append("---------------------------------------------------------------\n");

        return sb.toString();
    }


    @Override
    public void prettyPrint(OutputStream outputStream) throws IOException {
        outputStream.write(prettyPrint().getBytes());
        outputStream.flush();
    }

    protected String prepareThreadKey() {
        return Thread.currentThread().getId() + "." + Thread.currentThread().getName();
    }

    private static final class Task implements Serializable {

        private transient final Thread thread;

        private final String taskName;

        private final long startTimeInMillis;

        private final String taskId;

        private final long threadId;

        private final String threadName;

        private long finishTimeInMillis = -1;

        private long runningTimeInMillis = -1;

        private boolean completed = false;

        private Task(Thread thread, String taskName) {
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

        public boolean isCompleted() {
            return completed;
        }
    }

    public List<Task> getCompletedTasks() {
        return Collections.unmodifiableList(this.completedTasks);
    }

    public List<Task> getQueuedTasks() {
        return Collections.unmodifiableList(new ArrayList<>(this.queuedTasks.values()));
    }
}
