(function () {
    var reportService = angular.module('reportsServices', []);

    reportService.factory('reportService', ['$http', 'API_URL',
        function ($http, API_URL) {

            var addReportItem = function (loggedUser,activity) {
                var d = new Date();
                var date = d.getFullYear() + "-" + (d.getMonth()+1)+"-"+d.getDate();
                var log = {
                    username: loggedUser,
                    activity: activity,
                    dateCreated: date
                };
                return $http.post(API_URL + '/reports', JSON.stringify(log));
            }

            var getAllReportsOfUsername = function(username){
                return $http.get(API_URL + '/reports/'+username);
            }

            var getAllReportsOfUsernameInInterval = function(username,dateStart,dateEnd){
                var path = username+"/"+dateStart+"/"+dateEnd;
                return $http.get(API_URL + '/reports/'+path);
            }


            return {
                addReportItem: function (loggedUser,activity) {
                    return addReportItem(loggedUser,activity);
                },
                getAllReportsOfUsername: function (username) {
                    return getAllReportsOfUsername(username);
                },
                getAllReportsOfUsernameInInterval: function (username,dateStart,dateEnd) {
                    return getAllReportsOfUsernameInInterval(username,dateStart,dateEnd);
                }

            };
        }]);

})();