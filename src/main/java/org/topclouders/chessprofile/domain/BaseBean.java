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

import javax.xml.transform.Result;
import java.io.Serializable;

/**
 * Created by kokeny on 29/03/16.
 */
public abstract class BaseBean implements Serializable {


    //TODO implement generic serialization and deserialization here..

    public void serialize() {

    }

    public void deserialize() {

    }

    public Result marshal() {
        return null;
    }

    public <T extends BaseBean> T shallowCopy() {
        return (T) this;
    }

    public boolean equals(Object baseBean) {
        return false;
    }

    public <T extends BaseBean> T deepCopy(T target) {
        return target;
    }

    public <T extends BaseBean> T deepCopy() {
        return (T) this;
    }

    @Override
    public String toString() {
        return "BaseBean{}";
    }
}
