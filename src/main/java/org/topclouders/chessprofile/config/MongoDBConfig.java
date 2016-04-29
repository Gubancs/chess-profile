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

package org.topclouders.chessprofile.config;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.MongoException;
import com.mongodb.ServerAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.topclouders.chessprofile.component.MongoDBProperties;

import java.net.UnknownHostException;
import java.util.Collections;

/**
 * Created by kokeny on 29/03/16.
 */
@Configuration
@ComponentScan(basePackages = "org.topclouders.chessprofile.component")
public class MongoDBConfig {

    @Autowired
    private MongoDBProperties properties;

    @Bean
    public MongoClient mongoClient() throws UnknownHostException {
        final ServerAddress serverAddress = new ServerAddress(properties.getHost(), properties.getPort());

        MongoCredential credential = MongoCredential.createCredential(properties.getUser(), properties.getDatabase(), properties.getPassword());
        return new MongoClient(serverAddress, Collections.singletonList(credential));
    }

    @Bean(name = "mongoTemplate")
    public MongoOperations createMongoTemplate(MongoClient mongoClient) throws UnknownHostException, MongoException {
        MongoDbFactory factory = new SimpleMongoDbFactory(mongoClient, properties.getDatabase());
        return new MongoTemplate(factory);
    }
}
