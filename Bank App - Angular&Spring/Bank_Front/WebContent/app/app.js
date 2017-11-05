var app = angular.module("bankApp", [
    'ngRoute',
    'login',
    'accountsController',
    'clientsController',
    'billsController',
    'loginServices',
    'regUserServices',
    'validationService',
    'reportsServices',
    'adminController'
])

app.constant('API_URL', 'http://localhost:8080/');

app.config(function ($routeProvider) {
    $routeProvider
        .when('/', {
            templateUrl: 'app/login/login.html'
        })
        .when('/clients', {
            resolve: {
                "check": function ($location, $rootScope) {
                    if (!$rootScope.loggedIn) {
                        $location.path('/');
                    }
                }
            },
            templateUrl: 'app/regularUser/clients.html'
        })
        .when('/administrator', {
            resolve: {
                "check": function ($location, $rootScope) {
                    if (!$rootScope.loggedIn) {
                        $location.path('/');
                    }
                }
            },
            templateUrl: 'app/administrator/administrator.html'
        })
        .when('/accounts', {
            resolve: {
                "check": function ($location, $rootScope) {
                    if (!$rootScope.loggedIn) {
                        $location.path('/');
                    }
                }
            },
            templateUrl: 'app/regularUser/accounts.html'
        })
        .when('/bills', {
            resolve: {
                "check": function ($location, $rootScope) {
                    if (!$rootScope.loggedIn) {
                        $location.path('/');
                    }
                }
            },
            templateUrl: 'app/regularUser/bills.html'
        })
        .when('/reports', {
            resolve: {
                "check": function ($location, $rootScope) {
                    if (!$rootScope.loggedIn) {
                        $location.path('/');
                    }
                }
            },
            templateUrl: 'app/administrator/reports.html'
        })
});