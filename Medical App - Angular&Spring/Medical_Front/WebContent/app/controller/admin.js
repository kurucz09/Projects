var admin = angular.module('adminController', ['ngRoute','angularUtils.directives.dirPagination'])

admin.controller('adminCtrl', ['$scope', 'userFactory', '$rootScope', '$location', '$window',
    function ($scope, userFactory, $rootScope, $location,$window) {
        $scope.accounts = null;

        var vm = this;
        $scope.pageno = 1; // initialize page no to 1
        $scope.total_count = 0;
        $scope.itemsPerPage = 13;
        $scope.getData = function(pageno){ // This would fetch the data on page change.
            //In practice this should be in a factory.
            $scope.accounts = [];
            userFactory.getAllAccounts().then(function(response){
                //ajax request to fetch data into vm.data
                $scope.accounts = response.data;  // data to be displayed on current page.
                $scope.total_count = response.total_count; // total data count.
            });
        };
        $scope.getData($scope.pageno); // Call the function to fetch initial data on page load.

        userFactory.getAllAccounts().then(function (d) { //2. so you can use .then()
            $scope.accounts = d.data;
            $scope.list = $scope.accounts;
        });

        $scope.accountsType = [
            {type: 'Administrator'},
            {type: 'Secretary'},
            {type: 'Doctor'}
        ];

        $scope.selectedType = $scope.accountsType[0];
        $scope.select = {};
        $scope.select.username = null;
        $scope.select.password = null;
        $scope.select.name = null;
        $scope.select.accountType = null;

        $scope.passwordRegex = /^[a-zA-Z0-9]*$/i;

        $scope.sort = function(keyname){
            $scope.sortKey = keyname;   //set the sortKey to the param passed
            $scope.reverse = !$scope.reverse; //if true make it false and vice versa
        }



        $scope.setSelectedAccount = function (email, type) {
            $scope.emailSelected = email;

            var opts = $scope.accountsType.length;
            for (var i = 0; i < opts; i++) {
                if ($scope.accountsType[i].type === type.toString()) {
                    $scope.selectedType = $scope.accountsType[i];
                    break;
                }
            }


            var enabler = document.getElementById("updateUserM");
            enabler.disabled = false;
            enabler = document.getElementById("removeUserM");
            enabler.disabled = false;
        };

        $scope.changedAccountType = function (item) {
            $scope.selectedType = item;
        }

        $scope.setToNull = function(){
            $scope.account.email = null;
            $scope.account.password = null;
            $scope.account.name = null;
        }



        $scope.currentPage = 1;
        $scope.numPerPage = 10;
        $scope.paginate = function(value) {
            var begin, end, index;
            begin = ($scope.currentPage - 1) * $scope.numPerPage;
            end = begin + $scope.numPerPage;
            index = $scope.accounts.indexOf(value);
            return (begin <= index && index < end);
        };



        $scope.addAccount = function (isValid) {
            if (isValid) {
                var testForDupplicate = false;
                for (i in $scope.accounts) {
                    if ($scope.accounts[i].username === $scope.account.email) {
                        testForDupplicate = true;
                    }
                }

                if (testForDupplicate) {
                    sweetAlert("Fail", "E-mail should be unique", "warning");
                } else {

                    var data = {
                        username : $scope.account.email ,
                        password : $scope.account.password ,
                        name : $scope.account.name ,
                        accountType :  $scope.selectedType.type
                    };

                    userFactory.addNewUser(data).then(function () {
                        sweetAlert('Success', 'Account added', "success");
                    });


                    $scope.accounts.push({
                        username : $scope.account.email ,
                        password : $scope.account.password ,
                        name : $scope.account.name ,
                        accountType :  $scope.selectedType.type
                    });

                   $scope.setToNull();
                }
            } else {
                sweetAlert("Data invalid", "Please fill in all fields", "error");
            }
        }

        $scope.updateAccount = function (isValid) {
            if (isValid) {

                var data = {
                    username : $scope.select.username ,
                    password : $scope.select.password ,
                    name : $scope.select.name ,
                    accountType :  $scope.selectedType.type
                };

                userFactory.updateUser(data).then(function () {
                    sweetAlert('Success', 'Account updated', "success");
                });

                for (i in $scope.accounts) {
                    if ($scope.accounts[i].username === $scope.select.username) {
                        $scope.accounts[i].name = $scope.select.name;
                        $scope.accounts[i].password = $scope.select.password;
                        $scope.accounts[i].accountType = $scope.selectedType.type;
                    }
                }

            } else {
                sweetAlert("Data invalid", "Please fill in all fields", "error");
            }

        }

        $scope.deleteAccount = function () {
            //search cluster with given id and delete it
            $scope.counter = 0;
            for (i in $scope.accounts) {
                if ($scope.accounts[i].username === $scope.select.username) {

                    $scope.counter = i;
                    swal({
                            title: "Are you sure?",
                            text: "You will not be able to recover this account!",
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
                                $scope.accounts.splice($scope.counter, 1);
                                $scope.clust = {};
                                userFactory.deleteUserByUsername($scope.select.username).then(function () {
                                    swal("Deleted!", "This account has been deleted.", "success");
                                });

                            } else {
                                swal("Cancelled", "This account is safe :)", "error");
                            }
                        });

                }
            }
        }

    }]
);


