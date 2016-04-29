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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterGroups;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.topclouders.chessprofile.util.StopWatch;
import org.topclouders.chessprofile.util.ThreadSafeStopWatch;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by kokeny on 01/04/16.
 */
public class ThreadSafeStopWatchTest {

    private static final Logger LOG = LoggerFactory.getLogger(ThreadSafeStopWatchTest.class);

    private static final int NUMBER_OF_TASKS = 6;

    private static final int THREAD_POOL_SIZE = 4;

    private StopWatch stopWatch;

    private AtomicInteger taskIdSequence;


    @BeforeTest
    @BeforeGroups({"t1", "t2", "t3", "t4"})
    public void beforeTest() {
        LOG.debug("ThreadSafeStopWatchTest has been started.");

        stopWatch = new ThreadSafeStopWatch();
        taskIdSequence = new AtomicInteger(0);
    }

    @Test(threadPoolSize = THREAD_POOL_SIZE, invocationCount = 0, groups = "t3")
    public void testPrettyPrintWithEmptyTasks() {
    }


    @Test(threadPoolSize = THREAD_POOL_SIZE, invocationCount = NUMBER_OF_TASKS, groups = "t2")
    public void testParallelStopWatchExecution() {

        LOG.debug("thread-" + Thread.currentThread().getName() + ":pool-" + Thread.currentThread().getId());

        stopWatch.startTask("task_" + taskIdSequence.incrementAndGet());

        try {
            Thread.sleep(new Random().nextInt(200) + 100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        stopWatch.stop();
    }

    @Test(threadPoolSize = THREAD_POOL_SIZE, invocationCount = 12, groups = "t4")
    public void testParallelStopWatchExecution2() {
        LOG.debug("thread-" + Thread.currentThread().getName() + ":pool-" + Thread.currentThread().getId());
        stopWatch.startTask("task_" + taskIdSequence.incrementAndGet());
        stopWatch.stop();
    }

    @Test(threadPoolSize = 1, invocationCount = NUMBER_OF_TASKS, groups = "t1")
    public void testSingleThreadPoolSize() {

        LOG.debug("thread-" + Thread.currentThread().getName() + ":pool-" + Thread.currentThread().getId());

        stopWatch.startTask("task_" + taskIdSequence.incrementAndGet());


        stopWatch.stop();
    }

    @Test(expectedExceptions = IllegalStateException.class)
    public void testIllegalStateException() {
        stopWatch.stop();
    }


    @Test(expectedExceptions = IllegalStateException.class)
    public void testIllegalStateException2() {
        stopWatch.startTask("task1");
        stopWatch.prettyPrint();
    }

    @Test(expectedExceptions = IllegalStateException.class)
    public void testIllegalStateException3() {
        stopWatch.start();
        stopWatch.startTask("task2");
    }

    @Test(expectedExceptions = IllegalStateException.class)
    public void testIllegalStateException4() {
        stopWatch.start();
        stopWatch.start();
    }


    @Test(expectedExceptions = IllegalStateException.class)
    public void testIllegalStateException5() {
        stopWatch.start();
        stopWatch.prettyPrint();
    }

    @AfterGroups({"t1", "t2", "t3", "t4"})
    public void afterTest() throws IOException {
        LOG.debug(stopWatch.prettyPrint());

        LOG.debug("ThreadSafeStopWatchTest has been finished.");
    }
}

