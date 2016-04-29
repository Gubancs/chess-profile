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
/// <reference path="../dts/jquery.d.ts" />


'use strict';
var chessprofile = angular.module("chessprofile", ['ngRoute']);

// configure our routes
chessprofile.config(function ($routeProvider) {
    $routeProvider

    // route for the home page
        .when('/', {
            redirectTo: "/welcome"
        })

        .when('/welcome', {
            templateUrl: 'templates/welcome.html',
            controller: 'WelcomeController'
        })
});
