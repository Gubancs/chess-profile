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
/**
 * State Manager able to save and retrieve state of
 * JSON objects into the local storage of the browser.
 *
 * @author Gabor Kokeny - mail: kokeny19@gmail.com
 * @version 1.0.0
 */
var StateManager = (function () {
    function StateManager() {
        if (window.localStorage == null) {
            throw "Failed to initialize the StateManager, because your browser doesn't supports the HTML5 local storage.";
        }
        this.storage = window.localStorage;
    }
    /**
     * Retrieve state of an object by key
     *
     * @param {string} key
     * @return {T} Return the last state
     */
    StateManager.prototype.getState = function (key) {
        var stringState = this.storage.getItem(StateManager.STATE_PREFIX + key);
        var state = JSON.parse(stringState);
        console.debug("Get state from local storage", { key: key, state: state });
        return state;
    };
    /**
     * Check the local storage contains a key
     * @param key The key to be check in the local storage
     * @returns {boolean} Return true if the local storage contains a key otherwise return false.
     */
    StateManager.prototype.containsKey = function (key) {
        return this.getState(key) != null;
    };
    /**
     * Save state of an object
     *
     * @param {string} key A unique key for this state
     * @param {T} state The state that should be save
     */
    StateManager.prototype.saveState = function (key, state) {
        console.debug("Save object state into local storage", { key: key, object: state });
        this.storage.setItem(StateManager.STATE_PREFIX + key, JSON.stringify(state));
    };
    StateManager.STATE_PREFIX = "chessprofile.state:";
    return StateManager;
})();
//# sourceMappingURL=StateManager.js.map