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

package org.topclouders.chessprofile.domain;

import com.mysema.query.annotations.QueryEmbedded;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

/**
 * Created by kokeny on 29/03/16.
 */
@org.springframework.data.mongodb.core.mapping.Document
public final class Profile extends Document<Long> {

    /**
     * A string literal that contains the userName of a register chess player.
     */
    @Field
    private String loginId;

    @Field
    private String email;

    @QueryEmbedded
    private Password password;

    @QueryEmbedded
    private Rating rating;

    /**
     * The unique identity of a chess player provided by http://www.fide.com
     */
    @Field
    private String fideId;

    @Field
    private String facebookId;

    @Field
    private Date registrationDate;

    @Field
    private Date birthDate;

    @Field
    private Date lastLoginDate;


    public Profile() {
        super();
    }
}
