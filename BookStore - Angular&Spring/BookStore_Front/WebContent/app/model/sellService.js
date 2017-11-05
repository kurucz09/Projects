(function () {
    var sellServices = angular.module('sellServices', []);

    sellServices.factory('sellFactory', ['$http', 'API_URL',
        function ($http, API_URL) {

            return {
                getAllSells: function () {
                    return $http.get(API_URL + '/sells');
                },

                addNewSell: function (sell) {
                    return $http.post(API_URL + '/sells', JSON.stringify(sell));
                }

            };
        }]);

})();