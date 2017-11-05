var user = angular.module('userController', ['ngRoute'])

user.controller('userCtrl', ['$scope', 'sellFactory','userFactory' ,'bookFactory', '$rootScope', '$location', '$window',
        function ($scope, sellFactory,userFactory, bookFactory, $rootScope, $location, $window) {

            $scope.books = null;
            bookFactory.getAllBooks().then(function (d) {
                $scope.books = d.data;
            });


            $scope.genreType = [
                {type: 'ScienceFiction'},
                {type: 'Drama'},
                {type: 'Adventure'},
                {type: 'Romance'},
                {type: 'Mystery'},
                {type: 'ScienceFiction,Drama'},
                {type: 'ScienceFiction,Adventure'},
                {type: 'ScienceFiction,Romance'},
                {type: 'ScienceFiction,Mystery'},
                {type: 'Drama,Adventure'},
                {type: 'Drama,Romance'},
                {type: 'Drama,Mystery'},
                {type: 'Adventure,Romance'},
                {type: 'Adventure,Mystery'},
                {type: 'Romance,Mystery'}
            ];

            $scope.selectedType = $scope.genreType[0];
            $scope.select = {};
            $scope.select.isbn = null;
            $scope.select.title = null;
            $scope.select.authors = null;
            $scope.select.year = null;
            $scope.select.genres = null;
            $scope.select.image = null;
            $scope.select.review = null;
            $scope.select.price = null;
            $scope.select.nrOfItems = null;

            $scope.toBeSold = null;

            $scope.usernameLoggedin = $window.sessionStorage.usernameLogged;

            $scope.numberPattern = /^([1-9]+)([0-9]+)?$/i;


            $scope.setSelectedBook = function (isbn, index) {
                $scope.isbnSelected = isbn;

                var opts = $scope.genreType.length;
                for (var i = 0; i < opts; i++) {
                    if ($scope.genreType[i].type === index.toString()) {
                        $scope.selectedType = $scope.genreType[i];
                        break;
                    }
                }


                var enabler = document.getElementById("sellBooks");
                enabler.disabled = false;

            };

            $scope.getNumberFullUser = function (num) {
                return new Array(num);
            };

            $scope.getNumberEmptyUser = function (num) {
                var newNum = parseInt(5) - parseInt(num);
                return new Array(newNum);
            };


            $scope.sellBook = function (isValid) {
                if (isValid) {
                    if ($scope.select.nrOfItems < $scope.toBeSold) {
                        sweetAlert("Not enough", "items on stock", "error");
                    } else {
                        var book_selected = null;
                        for (i in $scope.books) {
                            if ($scope.books[i].isbn === $scope.select.isbn) {
                                book_selected = $scope.books[i];
                            }
                        }
                        var d = new Date();
                        var data_now = d.getFullYear() + '-' + d.getMonth() + '-' + d.getDay();

                        var data = {
                            book: book_selected,
                            quantity: $scope.toBeSold,
                            date: data_now,
                            user: $scope.usernameLoggedin
                        };

                        console.log(data);
                        var isbn = $scope.select.isbn;
                        sellFactory.addNewSell(data).then(function (response) {
                            console.log(response);
                            for (i in $scope.books) {
                                if ($scope.books[i].isbn === isbn) {
                                    $scope.books[i].nrOfItems = parseInt($scope.books[i].nrOfItems) - parseInt($scope.toBeSold);
                                }
                            }
                        });
                    }

                } else {
                    sweetAlert("Data invalid", "Please insert a valid number", "error");
                }
            }


            $scope.logOut = function () {
                $location.path('/');
            }
        }

    ]
)