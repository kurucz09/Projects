var login = angular.module('loginController', ['ngRoute'])

login.controller('loginCtrl', ['$scope', 'userFactory', '$rootScope', '$location', '$window',
    function ($scope, userFactory, $rootScope, $location,$window) {
        $scope.username = null;
        $scope.password = null;
        $rootScope.loggedIn = false;

        var d = document.getElementById("alert-login");
        d.style.display = 'none';

        $scope.submitLogin = function () {

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

            if (username_length === 0 && password_length === 0) {
                $scope.mymessage = 'Login with username and password';
                d.style.display = 'block';
            } else if (username_length === 0) {
                $scope.mymessage = 'Please provide a username';
                d.style.display = 'block';
            } else if (password_length === 0) {
                $scope.mymessage = 'Please provide a password';
                d.style.display = 'block';
            } else {

                var promise = userFactory.checkUserPassword($scope.username, $scope.password);
                promise.then(function successCallback(response) {
                    $rootScope.loggedState = response.data['userType'];
                    if ($rootScope.loggedState === 'notFound') {
                        $scope.mymessage = "Invalid account [ERROR 122]";
                        d.style.display = 'block';
                    } else if ($rootScope.loggedState === 'administrator') {
                        $rootScope.loggedIn = true;
                        $window.sessionStorage.usernameLogged = response.data['username'];
                        $window.sessionStorage.givernRights = "administrator";
                        $location.path('/goToAdministratorUsers');
                    } else if ($rootScope.loggedState === 'regularUser') {
                        $rootScope.loggedIn = true;
                        $window.sessionStorage.usernameLogged = response.data['username'];
                        $window.sessionStorage.givernRights = "regularUser";
                        $location.path('/regularUser');
                    } else {
                        $scope.mymessage = "Invalid account [ERROR 123]";
                        d.style.display = 'block';
                    }
                }, function errorCallback(data) {
                    $scope.mymessage = "Invalid account [ERROR 124]";
                    d.style.display = 'block';
                });
            }
        }
    }]
);

$(function(){
    $("[data-hide]").on("click", function(){
        $(this).closest("." + $(this).attr("data-hide")).hide();
    });
});
