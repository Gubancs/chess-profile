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

/// <reference path="StateManager.ts" />

var chessprofile = angular.module("chessprofile");
/**
 * The abstract implementation of a View controller
 *
 * @author gubancs
 * @version 1.0.0
 */
chessprofile.controller("ViewController", function ($scope, $http, $location) {

    /**
     * This object will be contains response of the preload request.
     *
     * @type {PreloadReply}
     */
    $scope.preloadReply = {};

    /**
     * This flag means the preloadData successfully loaded.
     * It is false when any errors occured at the pre load Ajax request.
     * @type {boolean}
     */
    $scope.loading = false;

    /**
     * The Remote preload URL of this ViewController.
     * for example: /welcome/preload
     * @type {string}
     */
    $scope.preloadUrl = $location.path().substring(1, $location.path().length) + "/preload";

    $scope.stateManager = new StateManager();

    $scope.model = {};

    /**
     * Initialize function of the Database controller
     */
    $scope.init = function () {
        console.debug("ViewController:init invoked with arguments : ", {preloadUrl: $scope.preloadUrl});

        // call to api, test
        $http({method: 'POST', url: $scope.preloadUrl})
            .success($scope.onPreloadSuccess).error($scope.onPreloadFailure);
    }

    /**
     * Ajax success handler for the preload request
     * @param preloadReply The preload reply from the server
     */
    $scope.onPreloadSuccess = function (preloadReply:PreloadReply):void {
        console.debug("ViewController:onPreloadSuccess ", {preloadReply: preloadReply});

        $scope.preloadReply = preloadReply;
        $scope.preloadSuccess = true;

    }

    /**
     * Ajax failure handler for the preload request
     * @param status The HTTP status
     */
    $scope.onPreloadFailure = function (status:number):void {
        console.error("ViewController:onPreloadFailure", arguments);

        //TODO should be handle preload failure here.
    }

    /**
     * Helper function for check a viewLocation is equals with value of the $location.path();
     *
     * @param viewLocation The string location to check
     * @returns {boolean} Return true if the viewLocation equals with $location.path() otherwise return false.
     */
    $scope.isActive = function (viewLocation:string) {
        return viewLocation === $location.path();
    }

});