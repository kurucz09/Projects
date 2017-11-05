var accountsController = angular.module('accountsController', ['ngRoute'])

accountsController.controller('accountsToCtrl',
    function ($scope, accountsFactory, reportService,clientsFactory, regUserValidation, $rootScope, $location) {
        $scope.clients = [];
        $scope.accounts = [];
        $scope.allAccounts = [];
        $scope.currency = [
            {cur: 'RON'},
            {cur: 'EUR'},
            {cur: 'USD'}
        ];
        $scope.selectedCurrency = $scope.currency[0];
        $scope.selectedAllAccounts = $scope.allAccounts['accountNumber'];
        $scope.selectedClient = $scope.clients[0];
        $scope.showCancel = false;

        $scope.select = {};
        $scope.select.accountNumber = null;
        $scope.select.cnp = null;
        $scope.select.dateCreated = null;
        $scope.select.credit = null;
        $scope.select.addButtonLabel = 'Add Account';
        $scope.select.updateButtonLabel = 'Update Account';
        $scope.select.accountEnteredManually = null;
        $scope.creditToCompute = null;
        $scope.accountToMakeTransfers = null;
        $scope.accountToMakeTransfersCredit = null;
        $scope.ammountToMakeTransfersCredit = null;

        var promise = clientsFactory.getAllClients();
        promise.then(function (response) {
            $scope.clients = response.data;
        }, function errorCallback() {
            sweetAlert("ERROR", "DATA NOT VISIBLE ERROR:101", "error");
        });

        var promise = clientsFactory.getAccountsOfClients($rootScope.accountsId);
        promise.then(function (response) {
            $scope.accounts = response.data;
        }, function errorCallback() {
            sweetAlert("ERROR", "DATA NOT VISIBLE ERROR:102", "error");
        });

        var promise = accountsFactory.getAllAccounts();
        promise.then(function (response) {
            $scope.allAccounts = response.data;
        }, function errorCallback() {
            sweetAlert("ERROR", "DATA NOT VISIBLE ERROR:103", "error");
        });

        $scope.setSelectedAccount = function (iban, credit, currenc) {
            $scope.selectedRowAccount = iban;
            $scope.selectedRowCredit = credit;
            $scope.selectedRowCurrency = currenc;
        };

        $scope.changedCurrency = function (item) {
            $scope.selectedCurrency = item.cur;

        }

        $scope.changedAccount = function (item) {
            var res = [];
            for (var x in item) {
                item.hasOwnProperty(x) && res.push(item[x])
            }
            $scope.selectedAllAccounts = res[1];
        }

        $scope.addNewAccount = function () {
            var isNewAccountWellFormatted = true;

            var ibanVal = $scope.select.accountNumber;
            var ibanTest = regUserValidation.checkForIban(ibanVal);
            if (!ibanTest) {
                $scope.select.accountNumber = null;
                var d = document.getElementById("iban_input");
                d.classList.add("alert-danger");
                isNewAccountWellFormatted = false;
            } else {
                var d = document.getElementById("iban_input");
                d.classList.remove("alert-danger");
            }

            var dateVal = $scope.select.dateCreated;
            var dateTest = regUserValidation.checkForDate(dateVal);
            if (!dateTest) {
                $scope.select.dateCreated = null;
                var d = document.getElementById("accounts_dateCreated_input");
                d.classList.add("alert-danger");
                isNewAccountWellFormatted = false;
            } else {
                var d = document.getElementById("accounts_dateCreated_input");
                d.classList.remove("alert-danger");
            }

            var creditVal = $scope.select.credit;
            var creditTest = regUserValidation.checkForCredit(creditVal);
            if (!creditTest) {
                $scope.select.phone = null;
                var d = document.getElementById("accounts_credit_input");
                d.classList.add("alert-danger");
                isNewAccountWellFormatted = false;
            } else {
                var d = document.getElementById("accounts_credit_input");
                d.classList.remove("alert-danger");
            }

            $scope.changedUsertype;
            if (isNewAccountWellFormatted) {
                var promise = accountsFactory.getAccount($scope.select.accountNumber);
                promise.then(function (response) {
                    if (response.data != "") {
                        sweetAlert("Ooops", 'Account already on database', "warning");
                    } else {
                        var data = {
                            clientCNP: $rootScope.accountsId,
                            accountNumber: $scope.select.accountNumber,
                            currency: $scope.selectedCurrency,
                            dateCreated: $scope.select.dateCreated,
                            credit: $scope.select.credit
                        };

                        var promise = accountsFactory.addNewAccount(data);
                        promise.then(function () {
                            sweetAlert("SUCCESS", 'INSERTED', "success");
                        }, function errorCallback(data) {
                            sweetAlert("ERROR", data, "error");
                        });
                        $scope.accounts.push({
                            accountNumber: $scope.select.accountNumber,
                            currency: $scope.selectedCurrency,
                            dateCreated: $scope.select.dateCreated,
                            credit: $scope.select.credit
                        });

                        var usr = $rootScope.usernameLogged;
                        var activity = "Added a new account : "+"(AccNR)-"+$scope.select.accountNumber
                            +" (Currency)-"+$scope.selectedCurrency +" (Credit)-"+$scope.select.credit
                            +" For (CNP)-"+$rootScope.accountsId;
                        reportService.addReportItem(usr,activity);
                    }
                }, function errorCallback(data) {
                    console.log("ERROR", data, "error");
                });

            }
        }

        $scope.openEditAccount = function (selCurrency) {
            if (selCurrency == "RON") {
                $scope.selectedCurrency = $scope.currency[0];
            } else if (selCurrency == "EUR") {
                $scope.selectedCurrency = $scope.currency[1];
            } else if (selCurrency == "USD") {
                $scope.selectedCurrency = $scope.currency[2];
            }
            var btnEdit = document.getElementById("updateButtonAccount");
            btnEdit.disabled = false;

            var ibanField = document.getElementById("iban_input");
            ibanField.disabled = true;

            var addMoneyField = document.getElementById("addMoney");
            addMoneyField.disabled = false;

            var subtractMoneyField = document.getElementById("subtractMoney");
            subtractMoneyField.disabled = false;

            var processField = document.getElementById("processTransaction_id");
            processField.disabled = false;

            $scope.accountToMakeTransfers = $scope.selectedRowAccount;
            $scope.accountToMakeTransfersCredit = $scope.selectedRowCredit;
        }

        $scope.updateAccount = function () {
            $scope.accountToEdit = null;
            for (i in $scope.accounts) {
                if ($scope.accounts[i].accountNumber == $scope.selectedRowAccount) {
                    $scope.accounts[i].accountNumber = $scope.select.accountNumber;
                    $scope.accounts[i].currency = $scope.selectedCurrency;
                    $scope.accounts[i].dateCreated = $scope.select.dateCreated;
                    $scope.accounts[i].credit = $scope.select.credit;
                    $scope.accountToEdit = $scope.accounts[i];

                }
            }

            var isUpdateWellFormatted = true;

            var dateVal = $scope.accountToEdit.dateCreated;
            var dateTest = regUserValidation.checkForDate(dateVal);
            if (!dateTest) {
                $scope.select.dateCreated = null;
                var d = document.getElementById("accounts_dateCreated_input");
                d.classList.add("alert-danger");
                isUpdateWellFormatted = false;
            } else {
                var d = document.getElementById("accounts_dateCreated_input");
                d.classList.remove("alert-danger");
            }

            var creditVal = $scope.accountToEdit.credit;
            var creditTest = regUserValidation.checkForCredit(creditVal);
            if (!creditTest) {
                $scope.select.phone = null;
                var d = document.getElementById("accounts_credit_input");
                d.classList.add("alert-danger");
                isUpdateWellFormatted = false;
            } else {
                var d = document.getElementById("accounts_credit_input");
                d.classList.remove("alert-danger");
            }

            if ($scope.accountToEdit != null && isUpdateWellFormatted) {
                var data = {
                    clientCNP: $rootScope.accountsId,
                    accountNumber: $scope.accountToEdit.accountNumber,
                    currency: $scope.selectedCurrency,
                    dateCreated: $scope.accountToEdit.dateCreated,
                    credit: $scope.accountToEdit.credit
                };

                var promise = accountsFactory.updateAccount(data);
                promise.then(function () {
                    sweetAlert("SUCCESS", "Account updated", "success");
                }, function errorCallback(data) {
                    sweetAlert("ERROR", "Error on updating" + data, "error");
                });

                var usr = $rootScope.usernameLogged;
                var activity = "Updated an account : "+"(AccNR)-"+$scope.accountToEdit.accountNumber
                    +" (Currency)-"+$scope.selectedCurrency +" (Credit)-"+$scope.accountToEdit.credit
                    +" For (CNP)-"+$rootScope.accountsId;
                reportService.addReportItem(usr,activity);

                $scope.select.accountNumber = null;
                $scope.select.currency = null;
                $scope.select.dateCreated = null;
                $scope.select.credit = null;
            } else {
                sweetAlert("FAILED", "Data format not accepted", "warning");
            }

            var btnEdit = document.getElementById("updateButtonAccount");
            btnEdit.disabled = true;
            var ibanField = document.getElementById("iban_input");
            ibanField.disabled = false;
            var ibanField = document.getElementById("addMoney");
            ibanField.disabled = true;

            var ibanField = document.getElementById("subtractMoney");
            ibanField.disabled = true;
        }

        $scope.deleteAccount = function (iban) {
            //search cluster with given id and delete it
            for (i in $scope.accounts) {
                if ($scope.accounts[i].accountNumber == iban) {
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
                                swal("Deleted!", "Your account has been deleted.", "success");
                                $scope.accounts.splice(i, 1);
                                $scope.clust = {};
                                var promise = accountsFactory.deleteOneAccount(iban);
                                promise.then(function (data) {
                                    var usr = $rootScope.usernameLogged;
                                    var activity = "Deleted an account : "+"(AccNR)-"+iban;
                                    reportService.addReportItem(usr,activity);

                                }), function (data) {
                                    sweetAlert("ERROR", "Error on deletion" + data, "error");
                                };
                            } else {
                                swal("Cancelled", "Your account is safe :)", "error");
                            }
                        });
                }
            }
        }

        $scope.addMoney = function (credit) {
            var creditIsWellFormatted = true;
            var creditVal = credit;
            var creditTest = regUserValidation.checkForCredit(creditVal);
            if (!creditTest) {
                $scope.select.creditToCompute = null;
                var d = document.getElementById("accounts_edit_credit");
                d.classList.add("alert-danger");
                creditIsWellFormatted = false;
            } else {
                var d = document.getElementById("accounts_edit_credit");
                d.classList.remove("alert-danger");
            }

            if (creditIsWellFormatted) {
                var res = [];
                for (var x in $scope.selectedCurrency) {
                    $scope.selectedCurrency.hasOwnProperty(x) && res.push($scope.selectedCurrency[x])
                }
                var sele = res[0];

                var data = {
                    "account": {
                        clientCNP: $rootScope.accountsId,
                        accountNumber: $scope.select.accountNumber,
                        currency: sele,
                        dateCreated: $scope.select.dateCreated,
                        credit: $scope.select.credit
                    },
                    "sum": credit
                };
                var promise = accountsFactory.addMoney(data);
                promise.then(function () {
                    sweetAlert("SUCCESS", "Account updated", "success");
                }, function errorCallback(data) {
                    sweetAlert("ERROR", "Error on updating" + data, "error");
                });

                //set in table
                var sum = parseFloat(credit) + parseFloat($scope.selectedRowCredit);
                for (i in $scope.accounts) {
                    if ($scope.accounts[i].accountNumber == $scope.selectedRowAccount) {
                        $scope.accounts[i].credit = sum.toFixed(2);
                    }
                }

                var usr = $rootScope.usernameLogged;
                var activity = "Added credit in the : "+"(AccNR)-"+$scope.select.accountNumber
                    +"For (CNP)-"+$rootScope.accountsId +"(Sum)-"+credit;
                reportService.addReportItem(usr,activity);
            }
            $scope.select.creditToCompute = null;
            $scope.select.accountNumber = null;
            $scope.select.currency = null;
            $scope.select.dateCreated = null;
            $scope.select.credit = null;
        }

        $scope.subtractMoney = function (credit) {
            var creditIsWellFormatted = true;
            var creditVal = credit;
            var creditTest = regUserValidation.checkForCredit(creditVal);
            if (!creditTest) {
                $scope.select.creditToCompute = null;
                var d = document.getElementById("accounts_edit_credit");
                d.classList.add("alert-danger");
                creditIsWellFormatted = false;
            } else {
                var d = document.getElementById("accounts_edit_credit");
                d.classList.remove("alert-danger");
            }

            if (creditIsWellFormatted) {
                var cred = parseFloat(credit);
                var hadCredit = parseFloat($scope.select.credit);
                var subtractIsWellFormatted = true;
                if (parseFloat(cred.toFixed(2)) > parseFloat(hadCredit.toFixed(2))) {
                    subtractIsWellFormatted = false;
                }
                if (subtractIsWellFormatted) {
                    var res = [];
                    for (var x in $scope.selectedCurrency) {
                        $scope.selectedCurrency.hasOwnProperty(x) && res.push($scope.selectedCurrency[x])
                    }
                    var sele = res[0];

                    var data = {
                        "account": {
                            clientCNP: $rootScope.accountsId,
                            accountNumber: $scope.select.accountNumber,
                            currency: sele,
                            dateCreated: $scope.select.dateCreated,
                            credit: $scope.select.credit
                        },
                        "sum": "-" + credit
                    }
                    var promise = accountsFactory.addMoney(data);
                    promise.then(function () {
                        sweetAlert("SUCCESS", "Account updated", "success");
                    }, function errorCallback(data) {
                        sweetAlert("ERRORR", "Error on updating" + data, "error");
                    });

                    var sum = parseFloat($scope.selectedRowCredit) - parseFloat(credit);
                    for (i in $scope.accounts) {
                        if ($scope.accounts[i].accountNumber == $scope.selectedRowAccount) {
                            $scope.accounts[i].credit = sum.toFixed(2);
                        }
                    }
                    var usr = $rootScope.usernameLogged;
                    var activity = "Subtracted credit in the : "+"(AccNR)-"+$scope.select.accountNumber
                        +"For (CNP)-"+$rootScope.accountsId +"(Sum)-"+credit;
                    reportService.addReportItem(usr,activity);
                } else {
                    sweetAlert("WARNING", "Cannot subtract more than the client has", "warning");
                }
            } else {
                sweetAlert("WARNING", "Amount of money cannot in other form than XX.XX, only decimals", "warning");
            }
            $scope.select.creditToCompute = null;
            $scope.select.accountNumber = null;
            $scope.select.currency = null;
            $scope.select.dateCreated = null;
            $scope.select.credit = null;
            var ibanField = document.getElementById("iban_input");
            ibanField.disabled = false;
            var ibanField = document.getElementById("addMoney");
            ibanField.disabled = true;
            var ibanField = document.getElementById("subtractMoney");
            ibanField.disabled = true;
            var creditField = document.getElementById("accounts_edit_credit");
            creditField.textContent = "";

        }

        $scope.processTransaction = function () {
            var credit = $scope.ammountToMakeTransfersCredit;
            var sendToList = $scope.selectedAllAccounts;
            var sendToInput = $scope.select.accountEnteredManually;
            $scope.sendTo = null;
            var ibanTest = regUserValidation.checkForIban(sendToInput);

            console.log(sendToList);
            console.log(sendToInput);

            if (sendToInput != null && ibanTest) {
                $scope.sendTo = sendToInput;
            } else if (sendToList != null) {
                $scope.sendTo = sendToList;
            } else {
                sweetAlert("WARNING", "Insert an account", "warning");
            }
            if($scope.sendTo != null){
                var creditTest = regUserValidation.checkForCredit(credit);
                if ($scope.sendTo == $scope.selectedRowAccount) {
                    sweetAlert("WARNING", "Cannot send to itself", "warning");
                } else if (creditTest) {
                    var promise = accountsFactory.getAccount($scope.sendTo);
                    promise.then(function (response) {
                        if (response.data != "") {
                            //account in database
                            if (parseFloat(parseFloat(credit).toFixed(2)) <= parseFloat(parseFloat($scope.selectedRowCredit).toFixed(2))) {
                                var res = [];
                                for (var x in $scope.selectedCurrency) {
                                    $scope.selectedCurrency.hasOwnProperty(x) && res.push($scope.selectedCurrency[x])
                                }
                                var sele = res[0];
                                var data = {
                                    "fromAccount": {
                                        clientCNP: $rootScope.accountsId,
                                        accountNumber: $scope.select.accountNumber,
                                        currency: sele,
                                        dateCreated: $scope.select.dateCreated,
                                        credit: $scope.select.credit
                                    },
                                    "toAccount": {
                                        clientCNP: response.data['clientCNP'],
                                        accountNumber: response.data['accountNumber'],
                                        currency: response.data['currency'],
                                        dateCreated: response.data['dateCreated'],
                                        credit: response.data['credit']
                                    },
                                    "sum": credit
                                }
                                var promise = accountsFactory.makeTransaction(data);
                                promise.then(function () {
                                    sweetAlert("SUCCESS", "Account updated", "success");
                                }, function errorCallback(response) {
                                    sweetAlert("ERROR", "Error on updating" + response + response.data, "error");
                                });

                                var processField = document.getElementById("processTransaction_id");
                                processField.disabled = false;
                                var ibanField = document.getElementById("iban_input");
                                ibanField.disabled = false;

                                var usr = $rootScope.usernameLogged;
                                var activity = "Transaction : "+"From (AccNR)-"+$scope.select.accountNumber
                                    +" Of (CNP)-"+$rootScope.accountsId +" To (AccNR)-"+response.data['accountNumber']
                                    +" Of (CNP)-"+response.data['clientCNP']+ " of : (Credit)"+credit
                                    + "(CUR) "+ sele + " -> "+response.data['currency'];
                                reportService.addReportItem(usr,activity);

                                $scope.select.creditToCompute = null;
                                $scope.select.accountNumber = null;
                                $scope.select.currency = null;
                                $scope.select.dateCreated = null;
                                $scope.select.credit = null;

                            } else {
                                sweetAlert("WARNING", "Cannot send more than the client has", "warning");
                            }
                        } else {
                            //account not in database
                            if (parseFloat(parseFloat(credit).toFixed(2)) <= parseFloat(parseFloat($scope.selectedRowCredit).toFixed(2))) {
                                var res = [];
                                for (var x in $scope.selectedCurrency) {
                                    $scope.selectedCurrency.hasOwnProperty(x) && res.push($scope.selectedCurrency[x])
                                }
                                var sele = res[0];
                                var data = {
                                    "account": {
                                        clientCNP: $rootScope.accountsId,
                                        accountNumber: $scope.select.accountNumber,
                                        currency: sele,
                                        dateCreated: $scope.select.dateCreated,
                                        credit: $scope.select.credit
                                    },
                                    "sum": credit
                                }
                                var promise = accountsFactory.addMoney(data);
                                promise.then(function () {
                                    sweetAlert("SUCCESS", "Account updated", "success");
                                }, function errorCallback(data) {
                                    sweetAlert("ERROR", "Error on updating" + data, "error");
                                });

                                var processField = document.getElementById("processTransaction_id");
                                processField.disabled = false;
                                var ibanField = document.getElementById("iban_input");
                                ibanField.disabled = false;

                                var usr = $rootScope.usernameLogged;
                                var activity = "Transaction : "+"From (AccNR)-"+$scope.select.accountNumber
                                    +" Of (CNP)-"+$rootScope.accountsId +" To (AccNR)-"+ $scope.sendTo
                                    + " of : (Credit)"+credit;
                                reportService.addReportItem(usr,activity);

                                $scope.select.creditToCompute = null;
                                $scope.select.accountNumber = null;
                                $scope.select.currency = null;
                                $scope.select.dateCreated = null;
                                $scope.select.credit = null;

                            } else {
                                sweetAlert("WARNING", "Cannot send more than the client has", "warning");
                            }
                        }


                    }, function errorCallback(data) {
                        console.log(data);
                    });
                } else {
                    sweetAlert("WARNING", "Ammount should be a number", "warning");
                }
            }




        }

        $scope.refreshAccountsTable = function () {
            var promise = clientsFactory.getAccountsOfClients($rootScope.accountsId);
            promise.then(function (response) {
                $scope.accounts = response.data;
            }, function errorCallback() {
                sweetAlert("ERROR", "DATA NOT VISIBLE ERROR:102", "error");
            });
        }

        $scope.processBills = function () {
            $location.path('/bills');
        }

        $scope.return = function(){
            $location.path('/');
        }


    })

