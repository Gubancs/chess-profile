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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Currency;

/**
 * Created by kokeny on 03/04/16.
 */
@Component
public class AppProperties implements Serializable {

    @Value("${app.locale}")
    private String locale;

    @Value("${app.currency}")
    private Currency currency;

    @Value("${app.encoding}")
    private String encoding;

    @Autowired
    private MongoDBProperties db;

    @Autowired
    private MailProperties mail;

    public MongoDBProperties db() {
        return db;
    }

    public MailProperties mail() {
        return mail;
    }


    public Currency getCurrency() {
        return currency;
    }

    public String getEncoding() {
        return encoding;
    }

    public String getLocale() {
        return locale;
    }
}