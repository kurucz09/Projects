var clientsController = angular.module('clientsController', ['ngRoute'])

clientsController.controller('clientsToCtrl',
    function ($scope, clientsFactory,reportService, regUserValidation, $rootScope, $location) {


        $scope.clients = [];

        var promise = clientsFactory.getAllClients();
        promise.then(function (response) {
            $scope.clients = response.data;
        }), function () {
            sweetAlert("ERROR", "DATA NOT VISIBLE", "error");
        };


        $scope.select = {};
        $scope.select.name = null;
        $scope.select.idClient = null;
        $scope.select.cnp = null;
        $scope.select.address = null;
        $scope.select.phone = null;
        $scope.select.addButtonLabel = 'Add Client';
        $scope.select.updateButtonLabel = 'Update Client';

        $scope.setSelected = function (index) {
            $scope.selectedRow = index;

        };

        $scope.deleteClient = function (cnp) {
            //search cluster with given id and delete it
            for (i in $scope.clients) {
                if ($scope.clients[i].cnp == cnp) {
                    swal({
                            title: "Are you sure?",
                            text: "You will not be able to recover this client!",
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
                                $scope.clients.splice(i, 1);
                                $scope.clust = {};
                                var promise = clientsFactory.deleteClient(cnp);
                                promise.then(function (data) {
                                    var usr = $rootScope.usernameLogged;
                                    var activity = "Deleted a client : "+"(CNP)-"+$scope.select.cnp;
                                    reportService.addReportItem(usr,activity);
                                }), function (data, status) {
                                    sweetAlert("ERROR", "Error on deletion" + data, "error");
                                };
                            } else {
                                swal("Cancelled", "This client is safe :)", "error");
                            }
                        });

                }
            }


        };

        $scope.addClient = function () {
            var isWellFormatted = true;

            var nameVal = $scope.select.name;
            var nameTest = regUserValidation.checkForName(nameVal);
            if (!nameTest) {
                isWellFormatted = false;
                $scope.select.name = null;
                var d = document.getElementById("clientName_input");
                d.classList.add("alert-danger");
            } else {
                var d = document.getElementById("clientName_input");
                d.classList.remove("alert-danger");
            }

            var idClientVal = $scope.select.idClient;
            var idClientTest = regUserValidation.checkForIdClient(idClientVal);
            if (!idClientTest) {
                isWellFormatted = false;
                $scope.select.idClient = null;
                var d = document.getElementById("idCard_input");
                d.classList.add("alert-danger");
            } else {
                var d = document.getElementById("idCard_input");
                d.classList.remove("alert-danger");
            }

            var cnpVal = $scope.select.cnp;
            var cnpTest = regUserValidation.checkForCNP(cnpVal);
            if (!cnpTest) {
                isWellFormatted = false;
                $scope.select.cnp = null;
                var d = document.getElementById("cnp_input");
                d.classList.add("alert-danger");
            } else {
                var d = document.getElementById("cnp_input");
                d.classList.remove("alert-danger");
            }

            //Phone number validation
            var phoneNo = $scope.select.phone;
            var phoneTest = regUserValidation.checkForPhone(phoneNo);
            if (!phoneTest) {
                isWellFormatted = false;
                $scope.select.phone = null;
                var d = document.getElementById("phone_input");
                d.classList.add("alert-danger");
            } else {
                var d = document.getElementById("phone_input");
                d.classList.remove("alert-danger");
            }

            //Address
            if ($scope.select.address == null || $scope.select.address == "") {
                isWellFormatted = false;
                var d = document.getElementById("address_input");
                d.classList.add("alert-danger");
            } else {
                var d = document.getElementById("address_input");
                d.classList.remove("alert-danger");
            }

            if (isWellFormatted) {
                var promise = clientsFactory.getClientByCNP($scope.select.cnp);
                promise.then(function (response) {
                    if (response.data != "") {
                        sweetAlert("Ooops", 'Client already on database', 'warning');
                    } else {

                        var data = {
                            name: $scope.select.name,
                            idClient: $scope.select.idClient,
                            cnp: $scope.select.cnp,
                            address: $scope.select.address,
                            phone: $scope.select.phone
                        };

                        var promise = clientsFactory.addClient(data);
                        promise.then(function () {
                            sweetAlert('Success', 'INSERTED', "success");
                        }, function errorCallback(data) {
                            sweetAlert("ERROR", data, "error");
                        });

                        $scope.clients.push({
                            name: $scope.select.name,
                            idClient: $scope.select.idClient,
                            cnp: $scope.select.cnp,
                            address: $scope.select.address,
                            phone: $scope.select.phone
                        });

                        var usr = $rootScope.usernameLogged;
                        var activity = "Added a new client : "+"(CNP)-"+$scope.select.cnp+" (NAME)-"+$scope.select.name;
                        reportService.addReportItem(usr,activity);
                    }
                }, function errorCallback(data) {
                    console.log(data);
                });
            }


        }

        $scope.editClient = function () {
            var btnEdit = document.getElementById("updateButton");
            btnEdit.disabled = false;
        }

        $scope.updateClient = function () {
            $scope.clientToEdit = null;
            for (i in $scope.clients) {
                if ($scope.clients[i].cnp == $scope.selectedRow) {
                    if ($scope.select.cnp == $scope.clients[i].cnp) {
                        $scope.clients[i].name = $scope.select.name;
                        $scope.clients[i].idClient = $scope.select.idClient;
                        $scope.clients[i].cnp = $scope.select.cnp;
                        $scope.clients[i].address = $scope.select.address;
                        $scope.clients[i].phone = $scope.select.phone;
                        $scope.clientToEdit = $scope.clients[i];
                    } else {
                        sweetAlert("ERROR", "Personal numerical code cannot be changed!", "error");
                    }
                }
            }

            if ($scope.select.cnp == $scope.clientToEdit.cnp) {
                var data = {
                    name: $scope.select.name,
                    idClient: $scope.select.idClient,
                    cnp: $scope.select.cnp,
                    address: $scope.select.address,
                    phone: $scope.select.phone
                };

                var promise = clientsFactory.updateClient(data);
                promise.then(function () {
                    sweetAlert("Success", "Client updated", "success");
                }, function errorCallback(data) {
                    sweetAlert("ERROR", "Error on deletion" + data, "error");
                });

                var usr = $rootScope.usernameLogged;
                var activity = "Updated a client : "+"(CNP)-"+$scope.select.cnp+" (NAME)-"+$scope.select.name
                    +" (IDClient)-"+$scope.select.idClient+" (Address)-"+$scope.select.address+" (Phone)-"+$scope.select.phone;
                reportService.addReportItem(usr,activity);
            }

            $scope.select.name = null;
            $scope.select.idClient = null;
            $scope.select.cnp = null;
            $scope.select.address = null;
            $scope.select.phone = null;

            var btnEdit = document.getElementById("updateButton");
            btnEdit.disabled = true;
        }

        $scope.accountsClient = function (cnp, clientName) {
            $rootScope.accountsId = cnp;
            $rootScope.accountsName = clientName;
            $location.path('/accounts');
        }

        $scope.return = function(){
            $location.path('/');
        }
    }
)

