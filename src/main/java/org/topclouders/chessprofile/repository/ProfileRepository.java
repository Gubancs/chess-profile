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

package org.topclouders.chessprofile.repository;

import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.topclouders.chessprofile.domain.Profile;
import org.topclouders.chessprofile.domain.QProfile;

/**
 * Created by kokeny on 29/03/16.
 */
@Repository
public interface ProfileRepository extends CrudRepository<Profile, Long>, QueryDslPredicateExecutor<Profile> {

    default Profile findProfileByEmail(String email) {
        return findOne(QProfile.profile.email.eq(email));
    }

    default Profile findProfileByLoginId(String loginId) {
        return findOne(QProfile.profile.loginId.eq(loginId));
    }
}
