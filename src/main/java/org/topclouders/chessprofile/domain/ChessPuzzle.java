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

/**
 * Created by kokeny on 29/03/16.
 */
@org.springframework.data.mongodb.core.mapping.Document
public class ChessPuzzle extends Document<Long> {

    private Profile author;

    private int difficulty;

    private int level;

    private int numberOfSolutions;

    private int numberOfExperiments;

    private Rating averageRating;

    private long averageTime;

    private String description;

    private String title;

    @QueryEmbedded
    private Pgn pgn;
}
