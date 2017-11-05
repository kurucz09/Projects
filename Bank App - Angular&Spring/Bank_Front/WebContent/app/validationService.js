(function () {
    var validationServices = angular.module('validationService', []);

    validationServices.factory('regUserValidation', ['$http', 'API_URL',
        function ($http, API_URL) {
            var checkForName = function (myVal) {
                var val = myVal;
                var pattern = /^[a-zA-Z]+([ ][a-zA-Z]+)?$/im;
                if ((pattern.test(val))&& val!=null) {
                    return true;
                } else {
                    return false;
                }

            }

            var checkForIdClient = function (myVal) {
                var val = myVal;
                var pattern = /^[A-Za-z][A-Za-z][0-9][0-9][0-9][0-9][0-9][0-9]$/im;
                if ((pattern.test(val))) {
                    return true;
                } else {
                    return false;
                }

            }

            var checkForCNP = function (myVal) {
                var val = myVal;
                var pattern = /^(1|2|5|6)(0[0-9]|1[0-7]|[3-9][0-9])(0[1-9]|[10]|[11]|[12])([0-2][1-9]|[20]|3[0-1])\d{6}$/im;
                if ((pattern.test(val))) {
                    return true;
                } else {
                    return false;
                }

            }

            var checkForPhone = function (myVal) {
                var val = myVal;
                var phonePattern1 = /^[0][7][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]$/im;
                var phonePattern2 = /^[0][7][0-9][0-9][.][0-9][0-9][0-9][.][0-9][0-9][0-9]$/im;
                var phonePattern3 = /^[0][7][0-9][0-9] [0-9][0-9][0-9] [0-9][0-9][0-9]$/im;
                if ((phonePattern1.test(val))|| (phonePattern2.test(val)) || (phonePattern3.test(val)) ) {
                        return true;
                    } else {
                        return false;
                    }
            }

            var checkForIban = function (myVal) {
                var val = myVal;
                var pattern = /^([R][O]|[E][U]|[U][S])[0-9][0-9] [S][B][B][K] ([0-9]|[A-Z]){4} [0-9]{4} [0-9]{4} [0-9]{4}$/im;
                if ((pattern.test(val))) {
                    return true;
                } else {
                    return false;
                }
            }

            var checkForDate = function (myVal) {
                var val = myVal;
                var pattern = /^20[0-1][0-9]\-((0[1-9])|(1[0-2]))\-((0[1-9])|((1[0-9])|(2[0-9])|(3[0-1])))$/im;
                if ((pattern.test(val))) {
                    return true;
                } else {
                    return false;
                }
            }

            var checkForCredit = function (myVal) {
                var val = myVal;
                var pattern = /^[0-9]+.[0-9]+$/im;
                if ((pattern.test(val))) {
                    return true;
                } else {
                    return false;
                }
            }

            var checkForUsern = function (myVal) {
                var val = myVal;
                var pattern = /^[a-zA-Z]+$/im;
                if ((pattern.test(val))&& val!=null) {
                    return true;
                } else {
                    return false;
                }
            }

            var checkForPassword = function (myVal) {
                var val = myVal;
                var pattern = /^([a-zA-Z]|[0-9])+$/im;
                if ((pattern.test(val)) && val!=null) {
                    return true;
                } else {
                    return false;
                }
            }

            var checkForEmail = function (myVal) {
                var val = myVal;
                var pattern = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/im;
                if ((pattern.test(val))) {
                    return true;
                } else {
                    return false;
                }
            }

            var checkForYear = function (myVal) {
                var val = myVal;
                var pattern = /^[2][0]([0]|[1])[0-9]$/im;
                if ((pattern.test(val))&& val!=null) {
                    return true;
                } else {
                    return false;
                }
            }

            var checkForMonth = function (myVal) {
                var val = myVal;
                var pattern = /^([0][1-9])|([1][0-2])$/im;
                if ((pattern.test(val))&& val!=null) {
                    return true;
                } else {
                    return false;
                }
            }

            var checkForDay = function (myVal) {
                var val = myVal;
                var pattern = /^([0][1-9])|([1-2][0-9])|([3][0-1])$/im;
                if ((pattern.test(val))&& val!=null) {
                    return true;
                } else {
                    return false;
                }
            }

            return {
                checkForName: function (myVal) {
                    return checkForName(myVal);
                },

                checkForIdClient: function (myVal) {
                    return checkForIdClient(myVal);
                },
                checkForCNP: function (myVal) {
                    return checkForCNP(myVal);
                },
                checkForPhone: function (myVal) {
                    return checkForPhone(myVal);
                },
                checkForIban: function (myVal) {
                    return checkForIban(myVal);
                },
                checkForDate: function (myVal) {
                    return checkForDate(myVal);
                },
                checkForCredit: function (myVal) {
                    return checkForCredit(myVal);
                },

                checkForUsern: function (myVal) {
                    return checkForUsern(myVal);
                },

                checkForPassword: function (myVal) {
                    return checkForPassword(myVal);
                },

                checkForEmail: function (myVal) {
                    return checkForEmail(myVal);
                },

                checkForYear: function (myVal) {
                    return checkForYear(myVal);
                },
                checkForMonth: function (myVal) {
                    return checkForMonth(myVal);
                },
                checkForDay: function (myVal) {
                    return checkForDay(myVal);
                }


            };
        }]);

})();