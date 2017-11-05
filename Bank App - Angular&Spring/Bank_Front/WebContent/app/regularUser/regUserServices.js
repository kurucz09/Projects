(function () {
    var userServices = angular.module('regUserServices', []);

    userServices.factory('clientsFactory', ['$http', 'API_URL',
        function ($http, API_URL) {
            var getClientsFunction = function () {
                return $http.get(API_URL + '/clients');
            }

            var deleteOneClient = function (id) {
                return $http.delete(API_URL + '/clients/' + id);
            }

            var getClientByCNP = function (cnp) {
                return $http.get(API_URL + '/clients/' + cnp);
            }

            var addClient = function (client) {
                return $http.post(API_URL + '/clients/', JSON.stringify(client));
            }

            var updateClient = function (client) {
                return $http.put(API_URL + '/clients/', JSON.stringify(client));
            }

            var getAccountsOfClients = function (cnp) {
                return $http.get(API_URL + '/accounts/client/' + cnp);
            }


            return {
                getAllClients: function () {
                    return getClientsFunction();
                },

                deleteClient: function (id) {
                    return deleteOneClient(id);
                },

                getClientByCNP: function (id) {
                    return getClientByCNP(id);
                },

                getAccountsOfClients: function (cnp) {
                    return getAccountsOfClients(cnp);
                },

                addClient: function (client) {
                    return addClient(client);
                },

                updateClient: function (client) {
                    return updateClient(client);
                },


            };
        }]);


    userServices.factory('accountsFactory', ['$http', 'API_URL',
        function ($http, API_URL) {
            var getAllAccounts = function () {
                return $http.get(API_URL + '/accounts');
            }

            var getAccount = function (accountNumber) {
                return $http.get(API_URL + '/accounts/account/' + accountNumber);
            }

            var addNewAccount = function (account) {
                return $http.post(API_URL + '/accounts/', JSON.stringify(account));
            }

            var updateAccount = function (account) {
                return $http.put(API_URL + '/accounts/', JSON.stringify(account));
            }

            var deleteOneAccount = function (id) {
                return $http.delete(API_URL + '/accounts/' + id);
            }

            var addMoney = function (data) {
                return $http.put(API_URL + '/accounts/addMoney', JSON.stringify(data));
            }

            var makeTransaction = function (data) {
                return $http.put(API_URL + '/accounts/transaction', JSON.stringify(data));
            }
            return {
                getAllAccounts: function () {
                    return getAllAccounts();
                },

                getAccount: function (accountNumber) {
                    return getAccount(accountNumber);
                },

                addNewAccount: function (account) {
                    return addNewAccount(account);
                },

                updateAccount: function (account) {
                    return updateAccount(account);
                },

                deleteOneAccount: function (iban) {
                    return deleteOneAccount(iban);
                },

                addMoney: function (data) {
                    return addMoney(data);
                },

                makeTransaction: function (data) {
                    return makeTransaction(data);
                }
            }
        }]);

    userServices.factory('billFactory', ['$http', 'API_URL',
        function ($http, API_URL) {
            var getAllBills = function (id) {
                return $http.get(API_URL + '/bills/' + id);
            }

            var chengeBillState = function (id) {
                return $http.get(API_URL + '/bills/update/' +id);
            }
            return {
                getAllBills: function (id) {
                    return getAllBills(id);
                },
                chengeBillState: function (id) {
                    return chengeBillState(id);
                }

            }

        }]);
})();