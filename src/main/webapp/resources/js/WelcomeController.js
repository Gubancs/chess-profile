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
/// <reference path="../dts/angular.d.ts" />
/// <reference path="../dts/chessboardjs.d.ts" />
/// <reference path="../dts/chessprofile.d.ts" />
/// <reference path="../dts/jquery.d.ts" />
var chessprofile = angular.module("chessprofile");
/**
 * Welcome screen controller
 *
 * @author Gabor Kokeny - mail: kokeny19@gmail.com
 * @version 1.0.0
 */
chessprofile.controller("WelcomeController", function ($scope, $controller, $http, $location, $interval) {
    //Invoke super constructor
    $controller('ViewController', { $scope: $scope });
    /**
     * Ajax success handler for the preload request
     * @param preloadReply The preload reply from the server
     */
    $scope.onPreloadSuccess = function (preloadReply) {
        console.debug("WelcomeController:onPreloadSuccess ", { preloadReply: preloadReply });
        $scope.preloadReply = preloadReply;
    };
});
//# sourceMappingURL=WelcomeController.js.map