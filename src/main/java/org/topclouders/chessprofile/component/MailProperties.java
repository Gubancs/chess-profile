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

package org.topclouders.chessprofile.component;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * Created by kokeny on 03/04/16.
 */
@Component
public class MailProperties implements Serializable {

    @Value("${mail.smtp.user}")
    private String userName;

    @Value("${mail.smtp.password}")
    private char[] password;

    @Value("${mail.smtp.url}")
    private String url;

    @Value("${mail.smtp.port}")
    private int port;

    public MailProperties(){
        super();
    }

    public String getUserName() {
        return userName;
    }

    public char[] getPassword() {
        return password;
    }

    public String getUrl() {
        return url;
    }

    public int getPort() {
        return port;
    }
}
