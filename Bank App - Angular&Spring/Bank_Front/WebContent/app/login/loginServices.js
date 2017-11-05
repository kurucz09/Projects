(function () {
    var userServices = angular.module('loginServices', []);

    userServices.factory('loginFactory', ['$http', 'API_URL',
        function ($http, API_URL) {
            var getAllUsersFunction = function () {
                return $http.get(API_URL + '/users');
            }

            var updateUser = function (user) {
                return $http.put(API_URL + '/users/', JSON.stringify(user));
            }

            var deleteUser = function(id){
                return $http.delete(API_URL + '/users/' + id);
            }

            var checkUserAndPassword = function (username, password) {
                return $http.get(API_URL + '/users/check/' + username + '/' + password);
            }

            var addNewUser = function(user){
                return $http.post(API_URL + '/users/', JSON.stringify(user));
            }


            return {
                findAllUsers: function () {
                    return getAllUsersFunction();
                },

                updateUser: function(user){
                    return updateUser(user);
                },

                deleteUser: function(id){
                    return deleteUser(id);
                },

                checkUserPassword: function (username, password) {
                    return checkUserAndPassword(username, password);
                },

                addNewUser: function(user){
                    return addNewUser(user);
                }

            };
        }]);

})();