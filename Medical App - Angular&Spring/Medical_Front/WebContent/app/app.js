var app = angular.module("medicalApp", [
    'ngRoute',
    'ngCookies',
    'ngResource',
    'ngSanitize',
    'loginController',
    'userServices',
    'doctorServices',
    'secretaryServices',
    'adminController',
    'secretaryController',
    'doctorController',
    'mainController'
   // 'bookServices',
   // 'userController',
   // 'sellServices'
  /*  'userController',
   'validationServices',*/
]);

app.constant('API_URL', 'http://localhost:8080/');

app.factory('authInterceptor', function($q,$location){
    return{
        request: function(config){
            config.headers = config.headers || {};

            if(localStorage.auth_token){
                config.headers.token = localStorage.auth_token;
            }
            return config;
        },
        responseError: function(response){
            if(response.status === 401){
                localStorage.removeItem('auth_token');
                $location.path('/login');
            }
            return $q.reject(response);
        }
    }
})


app.config(function($httpProvider){
    $httpProvider.interceptors.push('authInterceptor');
})

app.config(function ($routeProvider) {
    $routeProvider
        .when('/', {
            templateUrl: 'app/view/login.html',
            controller: 'loginCtrl'
        })
        .when('/login',{
            templateUrl: 'app/view/login.html',
            controller: 'loginCtrl'
        })
        .when('/secretary',{
            resolve: {
                "check": function ($location,userFactory,$rootScope) {
                    var data = userFactory.decode();
                    if(data !== 'Secretary'){
                        $location.path('/');
                    } else {
                        $rootScope.showSecretary = true;
                    }

                }
            },
            templateUrl: 'app/view/secretary.html',
            controller: 'secretaryCtrl'
        })
        .when('/secretary_consult',{
            resolve: {
                "check": function ($location,userFactory,$rootScope) {
                    var data = userFactory.decode();
                    if(data !== 'Secretary'){
                        $location.path('/');
                    } else {
                        $rootScope.showSecretary = true;
                    }

                }
            },
            templateUrl: 'app/view/secretary_consultations.html',
            controller: 'secretaryCtrl'
        })
        .when('/doctor',{
            resolve: {
                "check": function ($location,userFactory) {
                    var data = userFactory.decode();
                    if(data !== 'Doctor'){
                        $location.path('/');
                    }

                }
            },
            templateUrl: 'app/view/doctor.html',
            controller: 'doctorCtrl'
        })
        .when('/admin',{
            resolve: {
                "check": function ($location,userFactory) {
                    var data = userFactory.decode();
                    if(data !== 'Administrator'){
                        $location.path('/login');
                    }

                }
            },
            templateUrl: 'app/view/admin.html',
            controller: 'adminCtrl'
        })
        .when('/log',{
            resolve: {
                "check": function ($location,userFactory) {
                   var data = userFactory.decode();
                    if(data === 'Secretary'){
                        $location.path('/secretary');
                    } else
                    if(data === 'Doctor'){
                        $location.path('/doctor');
                    } else
                    if(data === 'Administrator'){
                        $location.path('/admin');
                    }

                }
            },
            templateUrl : 'app/view/login.html',
            controller: 'loginCtrl'
        })
        .otherwise({
            redirectTo: '/'
        })

});