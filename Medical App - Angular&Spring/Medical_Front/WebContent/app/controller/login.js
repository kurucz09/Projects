var login = angular.module('loginController', ['ngRoute'])

login.controller('loginCtrl', ['$scope', 'userFactory', '$rootScope', '$location', '$window', '$http',
    function ($scope, userFactory, $rootScope, $location,$window, $http) {

        $scope.user = null;

        $scope.wrongCredentialsVar = false;


        $scope.login = function(){
            var data = {
                "username": $scope.user.email,
                "password": $scope.user.password
            };
            if($scope.loginForm.$valid){

               var promise = userFactory.login(data);
               promise.then(success,error);
            } else {

            }
        }

        var success = function(response) {
            $scope.wrongCredentialsVar = false;
            localStorage.setItem('auth_token', response.data['msg']);
            $location.path('/log');
        };

        var error = function() {
            $scope.wrongCredentialsVar = true;
            $scope.user.password = "";
        };

        $scope.wrongCredentials = function () {
            return $scope.wrongCredentialsVar;
        };

    }]
);


