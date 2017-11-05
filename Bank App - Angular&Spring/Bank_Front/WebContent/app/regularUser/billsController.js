var billsController = angular.module('billsController', ['ngRoute'])


billsController.controller('billsController',
    function ($scope, $http, $location,reportService, $rootScope, billFactory,accountsFactory,clientsFactory) {

        $scope.allBills = [];
        $scope.allAccounts = [];

        $scope.select = {};
        $scope.select.billId = null;
        $scope.select.cnp = null;
        $scope.select.senderAccount = null;
        $scope.select.receiverAccount = null;
        $scope.select.state = null;
        $scope.select.dateCreated = null;
        $scope.select.ammound = null;
        $scope.sumInAccount = null;
        $scope.accountNumberBills = null;


        var promise = clientsFactory.getAccountsOfClients($rootScope.accountsId);
        promise.then(function (response) {
            $scope.allAccounts = response.data;
        }, function errorCallback() {
            sweetAlert("ERROR", "DATA NOT VISIBLE ERROR:101", "error");
        });

        var promise = billFactory.getAllBills($rootScope.accountsId);
        promise.then(function (response) {
            $scope.allBills = response.data;
        }, function errorCallback() {
            sweetAlert("ERROR", "DATA NOT VISIBLE ERROR:101", "error");
        });

        $scope.chengeBillState = function (id,fromAccount,state) {
            if(state == "solved"){
                sweetAlert("WARNING","This bill is already paid","warning");
            } else {
                var sumAccount = null;
                var sumToPay = null;
                var haveEnoughMoney = true;
                for(i in $scope.allAccounts){
                    if($scope.allAccounts[i].accountNumber == fromAccount){
                        sumAccount = parseFloat($scope.allAccounts[i].credit);
                    }
                }
                for(i in $scope.allBills){
                    if ($scope.allBills[i].billId == id){
                        sumToPay = $scope.allBills[i].ammound;
                    }
                }

                if (parseFloat(parseFloat(sumToPay).toFixed(2)) > parseFloat(parseFloat(sumAccount).toFixed(2))) {
                    haveEnoughMoney = false;
                }

                if(!haveEnoughMoney){
                    sweetAlert("WARNING","Not enough money in the account","warning");
                } else {
                    var promise = billFactory.chengeBillState(id);
                    promise.then(function () {
                        sweetAlert("SUCCESS", "Transfer made for bill", "success");
                    }, function errorCallback() {
                        sweetAlert("ERROR", "DATA NOT VISIBLE ERROR:101", "error");
                    });

                    var toPay = null;
                    for(i in $scope.allBills){
                        if ($scope.allBills[i].billId == id){
                            $scope.allBills[i].state = "solved";
                            toPay = $scope.allBills[i].ammound;
                        }
                    }

                    var selectedAccount = null;
                    for(i in $scope.allAccounts){
                        console.log($scope.allAccounts[i].accountNumber + "   and    "+fromAccount);
                        if($scope.allAccounts[i].accountNumber == fromAccount){
                            var sum = parseFloat($scope.allAccounts[i].credit) - parseFloat(toPay);
                            $scope.allAccounts[i].ammound = sum;
                            selectedAccount = $scope.allAccounts[i];
                        }
                    }

                    console.log(selectedAccount);
                    var data = {
                        "account": {
                            clientCNP: $rootScope.accountsId,
                            accountNumber: selectedAccount.accountNumber,
                            currency: selectedAccount.currency,
                            dateCreated: selectedAccount.dateCreated,
                            credit: selectedAccount.credit
                        },
                        "sum": "-" + toPay
                    }
                    var promise = accountsFactory.addMoney(data);
                    promise.then(function () {
                    }, function errorCallback(data) {
                        sweetAlert("ERRORR", "Error on updating" + data, "error");
                    });

                    var usr = $rootScope.usernameLogged;
                    var activity = "Bill paid : " +" For (CNP)-"+$rootScope.accountsId
                        +" with (AccNR)-" +selectedAccount.accountNumber
                        +" To (Receiver)-"+$scope.select.receiverAccount
                        +" Of (Sum)-"+toPay;
                    reportService.addReportItem(usr,activity);


                }
            }
        }

        $scope.return = function(){
            $location.path('/');
        }
    });

