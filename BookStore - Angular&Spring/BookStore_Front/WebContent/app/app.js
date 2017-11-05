var app = angular.module("booksApp", [
    'ngRoute',
    'loginController',
    'userServices',
    'adminController',
    'bookServices',
    'userController',
    'sellServices'
    /*  'userController',
     'validationServices',*/
])

app.constant('API_URL', 'http://localhost:8080/');

app.config(function ($routeProvider) {
    $routeProvider
        .when('/', {
            templateUrl: 'app/view/login.html'
        })
        .when('/regularUser', {
            resolve: {
                "check": function ($location, $window) {
                    if ($window.sessionStorage.givernRights!=="regularUser") {
                        $location.path('/');
                    }
                }
            },
            templateUrl: 'app/view/user.html'
        })

        .when('/administratorUsers', {
            resolve: {
                "check": function ($location, $window) {
                    if ($window.sessionStorage.givernRights!=="administrator") {
                        $location.path('/');
                    }
                }
            },
            templateUrl: 'app/view/administratorUsers.html'
        })
        .when('/administratorBooks', {
            resolve: {
                "check": function ($location, $window) {
                    if ($window.sessionStorage.givernRights!=="administrator") {
                        $location.path('/');
                    }
                }
            },
            templateUrl: 'app/view/administratorBooks.html'
        })
        .when('/goToAdministratorUsers',
            {
                resolve: {
                    "check": function ($location, $window) {
                        if ($window.sessionStorage.givernRights!=="administrator") {
                            $location.path('/');
                        }
                    }
                },
                templateUrl: 'app/view/administratorUsers.html'
            })

        .when('/goToAdministratorBooks',
            {
                resolve: {
                    "check": function ($location, $window) {
                        if ($window.sessionStorage.givernRights!=="administrator") {
                            $location.path('/');
                        }
                    }
                },
                templateUrl: 'app/view/administratorBooks.html'
            })

        .when('/goToAdministratorReports',
            {
                resolve: {
                    "check": function ($location, $window) {
                        if ($window.sessionStorage.givernRights!=="administrator") {
                            $location.path('/');
                        }
                    }
                },
                templateUrl: 'app/view/administratorReport.html'
            })

});