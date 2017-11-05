(function () {
    var doctorServices = angular.module('doctorServices', []);

    doctorServices.factory('doctorFactory', ['$http', 'API_URL',
        function ($http, API_URL) {

            return {
                getAllDoctors: function () {
                    return $http.get(API_URL + '/secretary/get');
                },

                getAllPatients: function(){
                    return $http.get(API_URL + '/secretary/all');
                },

                addNewPatient: function (patient) {
                    return $http.post(API_URL + '/secretary/', JSON.stringify(patient));
                },

                addNewConsultation: function (consult) {
                    return $http.post(API_URL + '/secretary/consult', JSON.stringify(consult));
                },

                getConsultationOfDoctor: function(name){
                    return $http.put(API_URL + '/secretary/doctor', JSON.stringify(name));
                },

                updateConsultation: function (consultation) {
                    return $http.put(API_URL + '/secretary/consultation', JSON.stringify(consultation));
                },

                updatePatient: function (patient) {
                    return $http.put(API_URL + '/secretary', JSON.stringify(patient));
                },

                getUserByUsername: function (username) {
                    return $http.get(API_URL + '/users/' + username);
                },

                deletePatientByPNC: function (pnc) {
                    return $http.delete(API_URL + '/secretary/' + pnc);
                },

                deleteConsultation: function(id){
                    return $http.delete(API_URL + '/secretary/consultation/' + id);
                },

                getConsultationOfDoctorPatient: function(doctor,patient){
                    return $http.get(API_URL + '/secretary/doctor/'+doctor +'/patient/'+patient);
                },

                decode: function(){
                    var token = localStorage.auth_token;
                    var decoded = jwt_decode(token);
                    return decoded.iss;
                }

            };
        }
    ]);



})();