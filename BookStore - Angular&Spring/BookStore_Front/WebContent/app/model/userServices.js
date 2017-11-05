(function () {
    var userServices = angular.module('userServices', []);

    userServices.factory('userFactory', ['$http', 'API_URL',
        function ($http, API_URL) {

            return {
                getAllUsers: function () {
                    return $http.get(API_URL + '/users');
                },

                updateUser: function (user) {
                    return $http.put(API_URL + '/users/', JSON.stringify(user));
                },

                getUserByUsername: function (username) {
                    return $http.get(API_URL + '/users/' + username);
                },

                deleteUserByUsername: function (username) {
                    return $http.delete(API_URL + '/users/' + username);
                },

                checkUserPassword: function (username, password) {
                    return $http.get(API_URL + '/users/check/' + username + '/' + password);
                },

                addNewUser: function (user) {
                    return $http.post(API_URL + '/users/', JSON.stringify(user));
                }

            };
        }]);

})();