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

package org.topclouders.chessprofile;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.topclouders.chessprofile.config.AppConfig;
import org.topclouders.chessprofile.domain.Profile;
import org.topclouders.chessprofile.repository.ProfileRepository;

/**
 * Created by kokeny on 29/03/16.
 */
public class Main implements ApplicationContextAware, Runnable {

    @Autowired
    private ProfileRepository profileRepository;

    private ConfigurableApplicationContext applicationContext;

    public static void main(String[] args) {

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);

        Main main = applicationContext.getBean(Main.class);

        Profile profile = main.profileRepository.findProfileByLoginId("alma");

        System.out.println(profile);
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "  - Shutdown hook invoked");
        this.applicationContext.close();
        System.out.println(Thread.currentThread().getName() + "  - Application context has been closed.");

    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = (ConfigurableApplicationContext) applicationContext;
    }
}
