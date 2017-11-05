var index = angular.module('mainController', ['ngRoute'])

index.controller('mainCtrl', ['$scope', 'userFactory', '$rootScope', '$location', 
    function ($scope, userFactory, $rootScope, $location) {

        $rootScope.login = "Login";
        $rootScope.showSecretary = false;

        $scope.isLoggedIn = function(){
            return(!!(localStorage.getItem('auth_token')));
        }

        $scope.logout = function(){
            localStorage.removeItem('auth_token');
            $rootScope.showSecretary = false;
            $location.path('/login');
            window.location.reload(false);
        }

        $scope.goPatients = function () {
            $location.path('/secretary');
        }

        $scope.goConsult = function () {
            $location.path('/secretary_consult');
        }

    }]
);


