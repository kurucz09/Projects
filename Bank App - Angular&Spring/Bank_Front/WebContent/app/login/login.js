var login = angular.module('login', ['ngRoute'])

login.controller('loginCtrl',
    function ($scope, loginFactory, $rootScope, $location) {
        $scope.users = [];
        var promise = loginFactory.findAllUsers();
        promise.then(function (data) {
            $scope.users = data;
        }), function (data, status) {
            alert(status);
        };
        console.log($scope.users);

        $scope.submitLogin = function () {
            console.log("PRESSED");

            var username_length = 0;
            for (var key in $scope.username) {
                if ($scope.username.hasOwnProperty(key)) {
                    username_length++;
                }
            }
            var password_length = 0;
            for (var key2 in $scope.password) {
                if ($scope.password.hasOwnProperty(key2)) {
                    password_length++;
                }
            }

            if (username_length == 0 && password_length == 0) {
                $scope.mymessage = 'Login with username and password';
            } else if (username_length == 0) {
                $scope.mymessage = 'Please provide a username';
            } else if (password_length == 0) {
                $scope.mymessage = 'Please provide a password';
            } else {

                var promise = loginFactory.checkUserPassword($scope.username, $scope.password);
                promise.then(function successCallback(response) {
                    $rootScope.loggedState = response.data['logginState'];
                    if ($rootScope.loggedState == 'notLogged') {
                        $scope.mymessage = "Invalid account [ERROR 122]";
                    } else if ($rootScope.loggedState == 'Administrator') {
                        $rootScope.loggedIn = true;
                        $rootScope.usernameLogged = response.data['username'];
                        $rootScope.emailLogged = response.data['email'];
                        $rootScope.nameLogged = response.data['name'];
                        $location.path('/administrator');
                    } else if ($rootScope.loggedState == 'RegularUser') {
                        $rootScope.loggedIn = true;
                        $rootScope.usernameLogged = response.data['username'];
                        $rootScope.emailLogged = response.data['email'];
                        $rootScope.nameLogged = response.data['name'];
                        $location.path('/clients');
                    } else {
                        $scope.mymessage = "Invalid account [ERROR 123]";
                    }
                }, function errorCallback(data) {
                    $scope.mymessage = "Invalid account [ERROR 124]";
                });
            }
        }
    }
);