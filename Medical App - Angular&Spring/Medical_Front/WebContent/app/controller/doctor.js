var doctor = angular.module('doctorController', ['ngRoute'])

doctor.controller('doctorCtrl', ['$scope', '$http','$timeout','doctorFactory', '$rootScope', '$location', '$window',
    function ($scope, $http,$timeout,doctorFactory, $rootScope, $location,$window) {


        $scope.select = {};
        $scope.select.username = null;
        $scope.select.name = null;
        $scope.select.checked = null;
        $scope.select.alert = null;
        $scope.patientFilter = "";
        $scope.patients = null;
        $scope.consultations = null;
        $scope.patientSelected = "emptySel23";
        $scope.consDetails = null;

        $scope.memoryPatient = null;
        $scope.memoryCons = null;
        $scope.endConsultationVar = false;

        doctorFactory.getAllPatients().then(function (d) { //2. so you can use .then()
            $scope.patients = d.data;
        });


        $scope.doctorLogged = doctorFactory.decode();

        doctorFactory.getConsultationOfDoctorPatient($scope.doctorLogged,$scope.patientSelected).then(function(d){
           $scope.consultations = d.data;
        });

        $scope.setSelectedPatient = function (pnc) {
            if(pnc!==null){
                $scope.consDetails = "";
                $scope.patientSelected = pnc;
                doctorFactory.getConsultationOfDoctorPatient($scope.doctorLogged,$scope.patientSelected).then(function(d){
                    $scope.consultations = d.data;
                });
            }
        };

        $scope.setSelectedCons = function(cons){
            if(cons!==null){
                $scope.consSelected = cons;
                $scope.consDetails = cons.details;
                var enabler = document.getElementById("consultUpdateBtn");
                enabler.disabled = false;
            }
        };

        $scope.setSelectedConsById = function (id){
            for(i in $scope.consultations){
                if($scope.consultations[i].id === id){
                    $scope.setSelectedCons($scope.consultations[i]);
                }
            }
        };


        $scope.viewAllConsult = function(){
            $scope.patientFilter = "";
            $scope.setSelectedPatient("emptySel23");
            var enabler = document.getElementById("consultUpdateBtn");
            enabler.disabled = true;
            $scope.consSelected = null;

        };

        $scope.addConsultDetails = function(){

            var newData = {
                "id": $scope.consSelected.id,
                "patient_id":  $scope.patientSelected,
                "startDate":  $scope.consSelected.startDate,
                "endDate": $scope.consSelected.endDate,
                "details": $scope.consDetails,
                "checked": $scope.consSelected.checked,
                "alert": $scope.consSelected.alert
            };

            for(i in $scope.consultations){
                if($scope.consultations[i].id === $scope.consSelected.id){
                    $scope.consultations[i].details = $scope.consDetails;
                }
            }
            $scope.consDetails = null;
            doctorFactory.updateConsultation(newData);

        };

        $scope.sort = function (keyname) {
            $scope.sortKey = keyname;   //set the sortKey to the param passed
            $scope.reverse = !$scope.reverse; //if true make it false and vice versa
        };


        $scope.endConsultation = function() {
            $scope.endConsultationVar = false;
            $scope.memoryPatient = null;
            $scope.memoryCons = null;
            $scope.viewAllConsult();
        };

        var loadTime = 3000, //Load the data every second
            errorCount = 0, //Counter for the server errors
            loadPromise; //Pointer to the promise created by the Angular $timout service

        var getData = function() {
            var email = {
                "msg": $scope.doctorLogged
            };
            doctorFactory.getConsultationOfDoctor(email)
                .then(function(d){

                    $scope.consultationss = d.data;
                    errorCount = 0;
                    for(i in $scope.consultationss){
                        if($scope.consultationss[i].alert === "true"){
                            swal({
                                    title: "Patient arrived",
                                    text: "Name: "+$scope.consultationss[i].patient.name,
                                    type: "info",
                                    closeOnConfirm: false,
                                    showLoaderOnConfirm: true
                                },
                                function(){
                                    setTimeout(function(){
                                        swal("Patient invited");
                                    }, 1000);

                                });
                            var newData = {
                                "id": $scope.consultationss[i].id,
                                "patient_id":  $scope.consultationss[i].patient.pnc,
                                "startDate":  $scope.consultationss[i].startDate,
                                "endDate": $scope.consultationss[i].endDate,
                                "details": $scope.consultationss[i].details,
                                "alert": "false",
                                "checked": "true"
                            };
                            doctorFactory.updateConsultation(newData);
                            $scope.memoryPatient = $scope.consultationss[i].patient.pnc;
                            $scope.memoryCons = $scope.consultationss[i].id;

                            if(!$scope.endConsultationVar){
                                $scope.setSelectedPatient($scope.memoryPatient);
                                $scope.setSelectedConsById($scope.memoryCons);
                                $scope.endConsultationVar = true;
                            }
                        }
                    }



                    nextLoad();
                })
                .catch(function(res) {
                    nextLoad(++errorCount * 2 * loadTime);
                });
        };

        var cancelNextLoad = function() {
            $timeout.cancel(loadPromise);
        };

        var nextLoad = function(mill) {
            mill = mill || loadTime;

            //Always make sure the last timeout is cleared before starting a new one
            cancelNextLoad();
            loadPromise = $timeout(getData, mill);
        };


        //Start polling the data from the server
        getData();


        //Always clear the timeout when the view is destroyed, otherwise it will keep polling
        $scope.$on('$destroy', function() {
            cancelNextLoad();
        });

        $scope.data = 'Loading...';


    }]
);
