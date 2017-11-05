(function () {
    var userServices = angular.module('bookServices', []);

    userServices.factory('bookFactory', ['$http', 'API_URL',
        function ($http, API_URL) {

            return {
                getAllBooks: function () {
                    return $http.get(API_URL + '/books');
                },

                updateBook: function (book) {
                    return $http.put(API_URL + '/books/', JSON.stringify(book));
                },

                deleteBookByISBN: function (isbn) {
                    return $http.delete(API_URL + '/books/' + isbn);
                },

                addNewBook: function (book) {
                    return $http.post(API_URL + '/books/', JSON.stringify(book));
                },

                increaseStock: function(isbn){
                    return $http.get(API_URL + '/books/increase/' + isbn);
                },

                decreaseStock: function(isbn){
                    return $http.get(API_URL + '/books/decrease/' + isbn);
                },

                getBooksOutOfStock: function(){
                    return $http.get(API_URL + '/books/report');
                }

            };
        }]);

})();