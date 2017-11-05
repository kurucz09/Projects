var admin = angular.module('adminController', ['ngRoute'])

admin.controller('adminCtrlUsers', ['$scope', 'userFactory', '$rootScope', '$location', '$window',
        function ($scope, userFactory, $rootScope, $location, $window) {

            $scope.users = null;
            userFactory.getAllUsers().then(function (d) { //2. so you can use .then()
                $scope.users = d.data;
            });

            $scope.accountType = [
                {type: 'regularUser'},
                {type: 'administrator'}
            ];

            $scope.selectedType = $scope.accountType[0];
            $scope.select = {};
            $scope.select.id = null;
            $scope.select.username = null;
            $scope.select.password = null;
            $scope.select.userType = null;

            $scope.select.tester_uniqueness = null;
            $scope.usernameLoggedin = $window.sessionStorage.usernameLogged;

            $scope.usernamePattern = /^([a-zA-Z]|[0-9]|[_])+$/i;
            $scope.passwordPattern = /^\S{6,18}$/i;


            $scope.setSelected = function (index) {
                if (index === "administrator") {
                    $scope.selectedType = $scope.accountType[1];
                } else if (index === "regularUser") {
                    $scope.selectedType = $scope.accountType[0];
                }
                var enabler = document.getElementById("updateButton");
                enabler.disabled = false;
                enabler = document.getElementById("deleteButton");
                enabler.disabled = false;
            };

            $scope.changedUsertype = function (item) {
                $scope.selectedType = item;
            }

            $scope.addUser = function (isValid) {
                if (isValid) {
                    var newID = $scope.generateID();
                    var testForDupplicate = false;
                    for (i in $scope.users) {
                        if ($scope.users[i].username === $scope.select.username) {
                            testForDupplicate = true;
                        }
                    }
                    if (testForDupplicate) {
                        sweetAlert("Fail", "Username should be unique", "warning");
                    } else {
                        var data = {
                            id: newID,
                            username: $scope.select.username,
                            password: $scope.select.password,
                            userType: $scope.selectedType.type
                        };

                        userFactory.addNewUser(data).then(function () {
                            sweetAlert('Success', 'INSERTED', "success");
                        });


                        $scope.users.push({
                            id: newID,
                            username: $scope.select.username,
                            password: $scope.select.password,
                            userType: $scope.selectedType.type
                        });

                        $scope.select.username = null;
                        $scope.select.password = null;
                        $scope.select.id = null;
                    }
                } else {
                    sweetAlert("Data invalid", "Please fill in username and password", "error");
                }
            }

            $scope.deleteUser = function () {
                //search cluster with given id and delete it
                $scope.counter = 0;
                for (i in $scope.users) {
                    if ($scope.users[i].username === $scope.select.username) {
                        if ($scope.select.username === $window.sessionStorage.usernameLogged) {
                            sweetAlert("ERROR", "Cannot delete your own account. In order to delete your account from database, please contact other administrator", "error")
                        } else {
                            $scope.counter = i;
                            swal({
                                    title: "Are you sure?",
                                    text: "You will not be able to recover this user!",
                                    type: "warning",
                                    showCancelButton: true,
                                    confirmButtonColor: "#DD6B55",
                                    confirmButtonText: "Yes, delete it!",
                                    cancelButtonText: "No, cancel!",
                                    closeOnConfirm: false,
                                    closeOnCancel: false
                                },
                                function (isConfirm) {
                                    if (isConfirm) {
                                        $scope.users.splice($scope.counter, 1);
                                        $scope.clust = {};
                                        userFactory.deleteUserByUsername($scope.select.username).then(function () {
                                            swal("Deleted!", "This client has been deleted.", "success");
                                        });

                                    } else {
                                        swal("Cancelled", "This client is safe :)", "error");
                                    }
                                });
                        }
                    }
                }


            };

            $scope.updateUser = function () {
                userFactory.getUserByUsername($scope.select.username).then(function (response) {
                    if (response.data != "" && response.data.username != $scope.select.tester_uniqueness) {
                        sweetAlert("ERROR", "There already exists an account with this username", "error");
                    } else {
                        for (i in $scope.users) {
                            if ($scope.users[i].id === $scope.select.id) {
                                $scope.users[i].username = $scope.select.username;
                                $scope.users[i].password = $scope.select.password;
                                $scope.users[i].userType = $scope.selectedType.type;
                            }
                        }
                        var data = {
                            id: $scope.select.id,
                            username: $scope.select.username,
                            password: $scope.select.password,
                            userType: $scope.selectedType.type
                        };

                        userFactory.updateUser(data).then(function () {
                            sweetAlert("Success", "user updated", "success");
                        });
                    }

                });
            }

            $scope.switchAdminPage = function (place) {
                if (place === 'users') {
                    $location.path('/goToAdministratorUsers');
                }
                if (place === 'books') {
                    $location.path('/goToAdministratorBooks');
                }
                if (place === 'report') {
                    $location.path('/goToAdministratorReports');
                }

            }

            $scope.generateID = function () {
                var maxid = 0;
                for (i in $scope.users) {
                    if (parseInt($scope.users[i].id) > parseInt(maxid)) {
                        maxid = parseInt($scope.users[i].id);
                    }
                }
                return parseInt(maxid) + parseInt(1);
            }

            $scope.logOut = function () {
                $location.path('/');
            }
        }
    ]
)

admin.controller('adminCtrlBooks', ['$scope', 'bookFactory', '$rootScope', '$location', '$window',
        function ($scope, bookFactory, $rootScope, $location, $window) {

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


            $scope.isbnSelected = null;
            $scope.select.tester_uniqueness = null;
            $scope.usernameLoggedin = $window.sessionStorage.usernameLogged;

            $scope.isbnPattern = /^[0-9]{1}[-]{1}[0-9]{3}[-]{1}[0-9]{5}[-]{1}[0-9]{0,1}$/i;
            $scope.authorsPattern = /^[a-zA-Z]+(([\'\,\.\- ][a-zA-Z ])?[a-zA-Z]*)*$/i;
            $scope.yearPattern = /^([1]?|[2]?)[0-9]?[0-9]?[0-9]$/i;
            $scope.imagePattern = /^[h][t][t][p][s]?[:][\/][\/]([a-zA-Z]|[0-9]?)+[.][\S+]+[\/][\S+]+[.](([j][p][e][g])|([j][p][g])|([p][n][g])|([g][i][f])|([b][m][p])|([t][i][f][f]))$/i;
            $scope.pricePattern = /^([1-9]+)([0-9]+)?$/i;

            $scope.sortType = $scope.select.title; // set the default sort type
            $scope.sortReverse = false;  // set the default sort order
            $scope.searchedFilter = "";

            $scope.getNumberFull = function (num) {
                return new Array(num);
            }

            $scope.getNumberEmpty = function (num) {
                var newNum = parseInt(5) - parseInt(num);
                return new Array(newNum);
            }

            $scope.setToNull = function () {
                $scope.select.isbn = null;
                $scope.select.title = null;
                $scope.select.authors = null;
                $scope.select.year = null;
                $scope.select.genres = null;
                $scope.select.image = null;
                $scope.select.review = null;
                $scope.select.price = null;
                $scope.select.nrOfItems = null;
            }

            $scope.initUpdate = function () {
                var enabler = document.getElementById("update_isbn_input");
                enabler.disabled = true;
            }

            $scope.setSelectedBook = function (isbn, index) {
                $scope.isbnSelected = isbn;

                var opts = $scope.genreType.length;
                for (var i = 0; i < opts; i++) {
                    if ($scope.genreType[i].type === index.toString()) {
                        $scope.selectedType = $scope.genreType[i];
                        break;
                    }
                }


                var enabler = document.getElementById("updateBook");
                enabler.disabled = false;
                enabler = document.getElementById("removeBook");
                enabler.disabled = false;
            };

            $scope.changedGenreType = function (item) {
                $scope.selectedType = item.type;
            }

            $scope.addBook = function (isValid) {

                if (isValid) {
                    var testForDupplicate = false;

                    for (i in $scope.books) {
                        if ($scope.books[i].isbn === $scope.select.isbn) {
                            testForDupplicate = true;
                        }
                    }

                    if (testForDupplicate) {
                        sweetAlert("Fail", "ISBN should be unique", "warning");
                    } else {

                        var nrItemsInitial = 0;
                        var initialReview = 5;
                        var arrayGenres = ( $scope.selectedType ).split(",");
                        var data = {
                            title: $scope.select.title,
                            authors: $scope.select.authors,
                            year: $scope.select.year,
                            genres: arrayGenres,
                            image: $scope.select.image,
                            review: initialReview,
                            nrOfItems: nrItemsInitial,
                            price: $scope.select.price,
                            isbn: $scope.select.isbn
                        };

                        bookFactory.addNewBook(data).then(function () {
                            sweetAlert('Success', 'INSERTED', "success");
                        });


                        $scope.books.push({
                            title: $scope.select.title,
                            authors: $scope.select.authors,
                            year: $scope.select.year,
                            genres: arrayGenres,
                            image: $scope.select.image,
                            review: initialReview,
                            nrOfItems: nrItemsInitial,
                            price: $scope.select.price,
                            isbn: $scope.select.isbn
                        });

                        $scope.select.isbn = null;
                        $scope.select.title = null;
                        $scope.select.authors = null;
                        $scope.select.year = null;
                        $scope.select.genres = null;
                        $scope.select.image = null;
                        $scope.select.review = null;
                        $scope.select.price = null;
                        $scope.select.nrOfItems = null;
                    }
                } else {
                    sweetAlert("Data invalid", "Please fill in all fields", "error");
                }
            }

            $scope.deleteBook = function () {
                //search cluster with given id and delete it
                $scope.counter = 0;
                for (i in $scope.books) {
                    if ($scope.books[i].isbn === $scope.select.isbn) {

                        $scope.counter = i;
                        swal({
                                title: "Are you sure?",
                                text: "You will not be able to recover this book!",
                                type: "warning",
                                showCancelButton: true,
                                confirmButtonColor: "#DD6B55",
                                confirmButtonText: "Yes, delete it!",
                                cancelButtonText: "No, cancel!",
                                closeOnConfirm: false,
                                closeOnCancel: false
                            },
                            function (isConfirm) {
                                if (isConfirm) {
                                    $scope.books.splice($scope.counter, 1);
                                    $scope.clust = {};
                                    bookFactory.deleteBookByISBN($scope.select.isbn).then(function () {
                                        swal("Deleted!", "This client has been deleted.", "success");
                                    });

                                } else {
                                    swal("Cancelled", "This client is safe :)", "error");
                                }
                            });

                    }
                }


            };

            $scope.updateBook = function (isValid) {
                if (isValid) {
                    var arrayGenres = $scope.selectedType.type.split(",");
                    var data = {
                        title: $scope.select.title,
                        authors: $scope.select.authors,
                        year: $scope.select.year,
                        genres: arrayGenres,
                        image: $scope.select.image,
                        review: $scope.select.review,
                        nrOfItems: $scope.select.nrOfItems,
                        price: $scope.select.price,
                        isbn: $scope.select.isbn
                    };

                    bookFactory.updateBook(data).then(function () {
                        sweetAlert('Success', 'Book updated', "success");
                    });

                    for (i in $scope.books) {
                        if ($scope.books[i].isbn === $scope.select.isbn) {
                            $scope.books[i].title = $scope.select.title;
                            $scope.books[i].authors = $scope.select.authors;
                            $scope.books[i].genres = arrayGenres;
                            $scope.books[i].image = $scope.select.image;
                            $scope.books[i].year = $scope.select.year;
                            $scope.books[i].price = $scope.select.price;
                        }
                    }

                } else {
                    sweetAlert("Data invalid", "Please fill in all fields", "error");
                }

            }

            $scope.switchAdminPage = function (place) {
                if (place === 'users') {
                    $location.path('/goToAdministratorUsers');
                }
                if (place === 'books') {
                    $location.path('/goToAdministratorBooks');
                }
                if (place === 'report') {
                    $location.path('/goToAdministratorReports');
                }
            }

            $scope.increaseStock = function (isbn) {
                bookFactory.increaseStock(isbn).then(function () {
                    for (i in $scope.books) {
                        if ($scope.books[i].isbn === isbn) {
                            $scope.books[i].nrOfItems = parseInt($scope.books[i].nrOfItems) + parseInt(1);
                        }
                    }
                });
            }

            $scope.dereaseStock = function (isbn) {
                bookFactory.decreaseStock(isbn).then(function (response) {
                    if (response.data['message'] === 'success') {
                        for (i in $scope.books) {
                            if ($scope.books[i].isbn === isbn) {
                                $scope.books[i].nrOfItems = parseInt($scope.books[i].nrOfItems) - parseInt(1);
                            }
                        }
                    } else if (response.data['message'] === 'failed') {
                        sweetAlert("Error", "Cannot have negative stock", "error");
                    }
                });
            }


            $scope.logOut = function () {
                $location.path('/');
            }
        }

    ]
)

admin.controller('adminReportCtrl', ['$scope', 'sellFactory', 'bookFactory', '$rootScope', '$location', '$window',
        function ($scope, sellFactory, bookFactory, $rootScope, $location, $window) {

            $scope.sells = null;
            sellFactory.getAllSells().then(function (d) {
                $scope.sells = d.data;
            });

            $scope.select = {};
            $scope.select.book = null;
            $scope.select.date = null;
            $scope.select.user = null;
            $scope.select.quantity = null;

            $scope.usernameLoggedin = $window.sessionStorage.usernameLogged;

            $scope.switchAdminPage = function (place) {
                if (place === 'users') {
                    $location.path('/goToAdministratorUsers');
                }
                if (place === 'books') {
                    $location.path('/goToAdministratorBooks');
                }
                if (place === 'report') {
                    $location.path('/goToAdministratorReports');
                }
            }

            $scope.logOut = function () {
                $location.path('/');
            }

            $scope.downloadReport = function (downCase) {
                var d = new Date();
                var data_now = d.getFullYear() + '-' + (parseInt(d.getMonth()) + parseInt(1)).toString() + '-' + d.getDay();


                $scope.outOfStock = null;
                bookFactory.getBooksOutOfStock().then(function (d) {
                    $scope.outOfStock = d.data;

                    if (downCase === "pdf") {
                        var logList = "";
                        for (i in $scope.outOfStock) {
                            logList += "\n" +
                                "TITLE: " + $scope.outOfStock[i].title + '\n' +
                                "ISBN: " + $scope.outOfStock[i].isbn + '\n' +
                                "AUTHOR: " + $scope.outOfStock[i].authors + '\n' +
                                "YEAR: " + $scope.outOfStock[i].year + '\n\n';
                        }
                        var docDefinition = {
                            content: [
                                {text: 'Out of stock report created on ' + data_now, fontSize: 15},
                                {text: logList, fontSize: 10}

                            ]
                        };
                        pdfMake.createPdf(docDefinition).download("REPORT_" + data_now + '.pdf');
                    } else if (downCase === "csv") {


                        downloadCSV(d);


                    }
                });


            }

            function downloadCSV(args) {
                var data, filename, link;
                var csv = convertArrayOfObjectsToCSV(args);
                if (csv == null) return;

                filename = args.filename || 'export.csv';

                if (!csv.match(/^data:text\/csv/i)) {
                    csv = 'data:text/csv;charset=utf-8,' + csv;
                }
                data = encodeURI(csv);

                link = document.createElement('a');
                link.setAttribute('href', data);
                link.setAttribute('download', filename);
                link.click();
            }

            function convertArrayOfObjectsToCSV(args) {
                var result, ctr, keys, columnDelimiter, lineDelimiter, data;

                data = args.data || null;
                if (data == null || !data.length) {
                    return null;
                }

                columnDelimiter = args.columnDelimiter || ',';
                lineDelimiter = args.lineDelimiter || '\n';

                keys = Object.keys(data[0]);

                result = '';
                result += keys.join(columnDelimiter);
                result += lineDelimiter;

                data.forEach(function (item) {

                    ctr = 0;
                    keys.forEach(function (key) {
                        if (ctr > 0) {
                            result += columnDelimiter;
                        }

                        if (item[key].length > 1) {
                            var newStr = item[key].toString().replace(/,/g,' ');
                            result += newStr;
                        } else {
                            result += item[key];
                        }
                        ctr++;
                    });

                    result += lineDelimiter;
                });

                return result;
            }

        }

    ]
)
