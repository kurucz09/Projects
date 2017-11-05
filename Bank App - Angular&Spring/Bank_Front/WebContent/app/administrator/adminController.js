var admin = angular.module('adminController', ['ngRoute'])

admin.controller('adminCtrl',
    function ($scope, loginFactory, regUserValidation, $rootScope, $location) {


        $scope.users = [];

        var promise = loginFactory.findAllUsers();
        promise.then(function (response) {
            $scope.users = response.data;
        }), function () {
            sweetAlert("ERROR", "DATA NOT VISIBLE", "error");
        };
        $scope.accountType = [
            {type: 'RegularUser'},
            {type: 'Administrator'}
        ];

        $scope.selectedType = $scope.accountType[0];
        $scope.select = {};
        $scope.select.id = null;
        $scope.select.name = null;
        $scope.select.username = null;
        $scope.select.password = null;
        $scope.select.email = null;
        $scope.select.logginState = null;

        $scope.setSelected = function (index) {
            $scope.selectedRow = index;
        };

        $scope.changedUsertype = function (item) {
            $scope.selectedType = item;
        }

        $scope.deleteUser = function (username) {
            //search cluster with given id and delete it

            for (i in $scope.users) {
                console.log($scope.users[i].username + " , " + username);
                if ($scope.users[i].username == username) {
                    if (username == $rootScope.usernameLogged) {
                        sweetAlert("ERROR", "Cannot delete your own account. In order to delete your account from database, please contact other administrator", "error")
                    } else {
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
                                    swal("Deleted!", "This client has been deleted.", "success");
                                    $scope.users.splice(i, 1);
                                    $scope.clust = {};
                                    var promise = loginFactory.deleteUser(username);
                                    promise.then(function (data) {

                                    }), function (data, status) {
                                        sweetAlert("ERROR", "Error on deletion" + data, "error");
                                    };
                                } else {
                                    swal("Cancelled", "This client is safe :)", "error");
                                }
                            });
                    }
                }
            }


        };

        $scope.addUser = function () {
            var isWellFormatted = false;

            var nameVal = $scope.select.name;
            var nameTest = regUserValidation.checkForName(nameVal);
            if (!nameTest) {
                $scope.select.name = null;
                var d = document.getElementById("nameUser_input");
                d.classList.add("alert-danger");
            } else {
                var d = document.getElementById("nameUser_input");
                d.classList.remove("alert-danger");
            }

            var usernameVal = $scope.select.username;
            var usernameTest = regUserValidation.checkForUsern(usernameVal);
            if (!usernameTest) {
                $scope.select.username = null;
                var d1 = document.getElementById("username_input");
                d1.classList.add("alert-danger");

            } else {
                var d2 = document.getElementById("username_input");
                d2.classList.remove("alert-danger");

            }

            var passVal = $scope.select.password;
            var passTest = regUserValidation.checkForPassword(passVal);
            if (!passTest) {
                $scope.select.password = null;
                var f = document.getElementById("password_input");
                f.classList.add("alert-danger");
            } else {
                var f2 = document.getElementById("password_input");
                f2.classList.remove("alert-danger");
            }

            //Phone number validation
            var emailVal = $scope.select.email;
            var emailTest = regUserValidation.checkForEmail(emailVal);
            if (!emailTest) {
                $scope.select.email = null;
                var d = document.getElementById("email_input");
                d.classList.add("alert-danger");
            } else {
                var d = document.getElementById("email_input");
                d.classList.remove("alert-danger");
            }

            if (emailTest && passTest && usernameTest && nameTest) {
                isWellFormatted = true;
            }


            if (isWellFormatted) {
                var testForDupplicate = false;
                for (i in $scope.users) {
                    if ($scope.users[i].username == $scope.select.username) {
                        testForDupplicate = true;
                    }

                }
                if (!testForDupplicate) {
                    var data = {
                        id: $scope.select.id,
                        name: $scope.select.name,
                        username: $scope.select.username,
                        email: $scope.select.email,
                        password: $scope.select.password,
                        logginState: $scope.selectedType.type
                    };

                    var promise = loginFactory.addNewUser(data);
                    promise.then(function () {
                        sweetAlert('Success', 'INSERTED', "success");
                    }, function errorCallback(data) {
                        sweetAlert("ERROR", data, "error");
                    });

                    $scope.users.push({
                        id: $scope.select.id,
                        name: $scope.select.name,
                        username: $scope.select.username,
                        email: $scope.select.email,
                        password: $scope.select.password,
                        logginState: $scope.selectedType.type
                    });

                    $scope.select.name = null;
                    $scope.select.username = null;
                    $scope.select.password = null;
                    $scope.select.email = null;
                }
                else {
                    sweetAlert("Ooops", 'User already on database', 'warning');
                }
            } else {
                sweetAlert("ERROR", 'Data input not correct', 'warning');
            }

        }


        $scope.editUser = function (selType) {
            console.log(selType);
            if (selType == "RegularUser") {
                $scope.selectedType = $scope.accountType[0];
            } else if (selType == "Administrator") {
                $scope.selectedType = $scope.accountType[1];
            }

            var btnEdit = document.getElementById("updateButton");
            btnEdit.disabled = false;
        }

        $scope.updateUser = function () {
            $scope.userToEdit = null;
            for (i in $scope.users) {
                if ($scope.users[i].username == $scope.selectedRow) {
                    if ($scope.select.username == $scope.users[i].username) {
                        $scope.users[i].name = $scope.select.name;
                        $scope.users[i].username = $scope.select.username;
                        $scope.users[i].password = $scope.select.password;
                        $scope.users[i].email = $scope.select.email;
                        $scope.users[i].logginState = $scope.selectedType;
                        $scope.userToEdit = $scope.users[i];
                    } else {
                        sweetAlert("ERROR", "Some error!", "error");
                    }
                }
            }

            var data = {

                id: $scope.select.id,
                name: $scope.select.name,
                username: $scope.select.username,
                email: $scope.select.email,
                password: $scope.select.password,
                logginState: $scope.selectedType.type
            };

            console.log(data);

            var promise = loginFactory.updateUser(data);
            promise.then(function () {
                sweetAlert("Success", "user updated", "success");
            }, function errorCallback(data) {
                sweetAlert("ERROR", "Error on update" + data, "error");
            });

            $scope.select.name = null;
            $scope.select.username = null;
            $scope.select.password = null;
            $scope.select.email = null;

            var btnEdit = document.getElementById("updateButton");
            btnEdit.disabled = true;
        }

        $scope.refreshUsersTable = function () {
            var promise = loginFactory.findAllUsers();
            promise.then(function (response) {
                $scope.users = response.data;
            }), function () {
                sweetAlert("ERROR", "DATA NOT VISIBLE", "error");
            };
        }

        $scope.getReports = function (username) {
            $rootScope.usernameReports = username;
            $location.path('/reports');
        }

        $scope.return = function(){
            $location.path('/');
        }

    }
)

admin.controller('reportsCtrl',
    function ($scope, loginFactory, reportService, regUserValidation, $rootScope, $location) {
        $scope.fromYear = null;
        $scope.fromMonth = null;
        $scope.fromDay = null;
        $scope.endYear = null;
        $scope.endMonth = null;
        $scope.endDay = null;
        $scope.fromDate = null;
        $scope.endDate = null;
        $scope.isWellFormatted = true;

        $scope.allLogs = [];


        var promise = reportService.getAllReportsOfUsername($rootScope.usernameReports);
        promise.then(function (response) {
            $scope.allLogs = response.data;
        }), function () {
            sweetAlert("ERROR", "DATA NOT VISIBLE", "error");
        };
        $scope.select = {};
        $scope.select.dateCreated = null;
        $scope.select.activity = null;

        $scope.generateReport = function () {
            var fromYear = $scope.fromYear;
            var passTest = regUserValidation.checkForYear(fromYear);
            if (!passTest) {
                $scope.fromYear = null;
                var f = document.getElementById("starting_year");
                f.classList.add("alert-danger");
                $scope.isWellFormatted = false;
            } else {

                var f2 = document.getElementById("starting_year");
                f2.classList.remove("alert-danger");
            }
            var endYear = $scope.endYear;
            var passTest = regUserValidation.checkForYear(endYear);
            if (!passTest) {
                $scope.endYear = null;
                $scope.isWellFormatted = false;
                var f = document.getElementById("ending_year");
                f.classList.add("alert-danger");
            } else {
                var f2 = document.getElementById("ending_year");
                f2.classList.remove("alert-danger");
            }
            var fromYear = $scope.fromMonth;
            var passTest = regUserValidation.checkForMonth(fromYear);
            if (!passTest) {
                $scope.fromMonth = null;
                $scope.isWellFormatted = false;
                var f = document.getElementById("starting_month");
                f.classList.add("alert-danger");
            } else {
                var f2 = document.getElementById("starting_month");
                f2.classList.remove("alert-danger");
            }
            var fromYear = $scope.endMonth;
            var passTest = regUserValidation.checkForMonth(fromYear);
            if (!passTest) {
                $scope.endMonth = null;
                $scope.isWellFormatted = false;
                var f = document.getElementById("ending_month");
                f.classList.add("alert-danger");
            } else {
                var f2 = document.getElementById("ending_month");
                f2.classList.remove("alert-danger");
            }
            var fromYear = $scope.fromDay;
            var passTest = regUserValidation.checkForDay(fromYear);
            if (!passTest) {
                $scope.fromDay = null;
                $scope.isWellFormatted = false;
                var f = document.getElementById("starting_day");
                f.classList.add("alert-danger");
            } else {
                var f2 = document.getElementById("starting_day");
                f2.classList.remove("alert-danger");
            }
            var fromYear = $scope.endDay;
            var passTest = regUserValidation.checkForDay(fromYear);
            if (!passTest) {
                $scope.endDay = null;
                $scope.isWellFormatted = false;
                var f = document.getElementById("ending_day");
                f.classList.add("alert-danger");
            } else {
                var f2 = document.getElementById("ending_day");
                f2.classList.remove("alert-danger");
            }

            if ($scope.isWellFormatted) {
                var dateGreat = false;
                if (parseFloat($scope.fromYear) < parseFloat($scope.endYear)) {
                    dateGreat = true;
                } else if (parseFloat($scope.fromYear) == parseFloat($scope.endYear)) {
                    if (parseFloat($scope.fromMonth) < parseFloat($scope.endMonth)) {
                        dateGreat = true;
                    } else if (parseFloat($scope.fromMonth) == parseFloat($scope.endMonth)) {
                        if (parseFloat($scope.fromDay) < parseFloat($scope.endDay)) {
                            dateGreat = true;
                        }

                    }
                }
                if (dateGreat) {
                    $scope.fromDate = $scope.fromYear + "-" + $scope.fromMonth + "-" + $scope.fromDay;
                    $scope.endDate = $scope.endYear + "-" + $scope.endMonth + "-" + $scope.endDay;

                    var promise = reportService.getAllReportsOfUsernameInInterval($rootScope.usernameReports, $scope.fromDate, $scope.endDate);
                    promise.then(function (response) {
                        $scope.allLogs = response.data;
                        if (response.data == "") {
                            sweetAlert("NOT FOUND", "No actions were made in this interval", "warning");
                        }
                    }), function () {
                        sweetAlert("ERROR", "DATA NOT VISIBLE", "error");
                    };


                } else {
                    sweetAlert("warning", "Starting date should be before Ending date", "warning");
                }
            } else {
                sweetAlert("warning", "Date not in accepted format", "warning");
            }
        }

        $scope.downloadReport = function () {
            if ($scope.endYear != null && $scope.endMonth != null && $scope.endDay != null
                && $scope.fromYear != null && $scope.fromMonth != null && $scope.fromDay != null) {
                $scope.generateReport();
            }
            var logList = "";
            for (i in $scope.allLogs) {
                logList += "\n" + $scope.allLogs[i].dateCreated + "   :   " + $scope.allLogs[i].activity + '\n';
            }
            var docDefinition = {
                content: [
                    {text: 'Activity report for ' + $rootScope.usernameReports, fontSize: 15},
                    {text: logList}

                ]
            };
            pdfMake.createPdf(docDefinition).download("REPORT_" + $rootScope.usernameReports + '.pdf');
        }

        $scope.return = function(){
            $location.path('/');
        }
    }
)