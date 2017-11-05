var secretary = angular.module('secretaryController',
    [
        'ngRoute',
        'angularUtils.directives.dirPagination',
        'daypilot'
    ])

secretary.controller('secretaryCtrlDoctor', ['$scope', 'secretaryFactory', '$rootScope', '$location', '$window', '$timeout',
    function ($scope, secretaryFactory, $rootScope, $location, $window, $timeout) {

        $scope.select = {};
        $scope.select.username = null;
        $scope.select.name = null;
        $scope.select.checked =null;
        $scope.select.alert = null;

        $scope.selectedNewStart = null;
        $scope.selectedNewEnd = null;

        $scope.patientFilter = "";
        $scope.doctorSelected = null;

        $scope.filterEvents = null;

        $scope.consultTriggered = false;

        $scope.consultations= {};



        //TABLE SCRIPT//////////////////////////////////////////////////
        //TABLE SCRIPT//////////////////////////////////////////////////
        //TABLE SCRIPT//////////////////////////////////////////////////

        $scope.events = [];


        $scope.eventText =null;
        $scope.eventConsultID =null;
        $scope.eventStartDate =null;
        $scope.eventEndDate =null;
        $scope.eventStartDateFull =null;
        $scope.eventEndDateFull =null;
        $scope.doctorSelectedName =null;
        $scope.eventDate = null;


        $scope.dayConfig = {
            viewType: "Day",
            theme: "calendar_green",
            onEventMove: function (args) {
                $scope.updateConsult(args);
            },
            onEventResize: function (args) {
                $scope.updateConsult(args);
            },
            onEventClick: function (args) {
                $scope.eventText =args.e.data.text;
                $scope.eventConsultID =args.e.data.id;
                $scope.eventDate=args.e.data.start.value.substring(0,10);
                $scope.eventStartDate =args.e.data.start.value.substring(11,19);
                $scope.eventEndDate =args.e.data.end.value.substring(11,19);
                $scope.eventStartDateFull =args.e.data.start.value;
                $scope.eventEndDateFull =args.e.data.end.value;
                $('#eventModal').modal('show');
                var counter =null;
                for ( i in $scope.consultations){
                    if($scope.consultations[i].id === args.e.data.id){
                        counter = i;
                    }
                }
                if(counter!==null){
                    if($scope.consultations[counter].checked === "true"){
                        var enabler = document.getElementById("announceDoctorId");
                        enabler.disabled = true;
                        sweetAlert('Warning', 'Consultation already made', "warning");
                    }
                }
            },
            onTimeRangeSelected: function (args) {
                $scope.selectedNewStart = args.start;
                $scope.selectedNewEnd = args.end;
            }
        };
        $scope.weekConfig = {
            viewType: "Week",
            theme: "calendar_green",
            onEventMove: function (args) {
                $scope.updateConsult(args);
            },
            onEventResize: function (args) {
                $scope.updateConsult(args);
            },
            onEventClick: function (args) {
                $scope.eventText =args.e.data.text;
                $scope.eventConsultID =args.e.data.id;
                $scope.eventDate=args.e.data.start.value.substring(0,10);
                $scope.eventStartDate =args.e.data.start.value.substring(11,19);
                $scope.eventEndDate =args.e.data.end.value.substring(11,19);
                $scope.eventStartDateFull =args.e.data.start.value;
                $scope.eventEndDateFull =args.e.data.end.value;
                $('#eventModal').modal('show');
                var counter =null;
                for ( i in $scope.consultations){
                    if($scope.consultations[i].id === args.e.data.id){
                        counter = i;
                    }
                }
                if(counter!==null){
                    if($scope.consultations[counter].checked === "true"){
                        var enabler = document.getElementById("announceDoctorId");
                        enabler.disabled = true;
                        sweetAlert('Warning', 'Consultation already made', "warning");
                    }else {
                        var enabler = document.getElementById("announceDoctorId");
                        enabler.disabled = false;
                    }
                }

            },
            onTimeRangeSelected: function (args) {
                $scope.selectedNewStart = args.start.value;
                $scope.selectedNewEnd = args.end.value;
                $scope.$apply();
            },
        };

        $scope.updateConsult = function(args){
            var counter = null;
            for(i in $scope.consultations){
                if($scope.consultations[i].id === args.e.data.id){
                    counter = i;
                }
            }
            var pat_id =  $scope.getIdOfPatient(args.e.data.text);
            var newData = {
                "id": args.e.data.id,
                "patient_id":  pat_id,
                "startDate":  args.newStart,
                "endDate": args.newEnd,
                "details": $scope.consultations[counter].details,
                "checked": $scope.consultations[counter].checked,
                "alert": $scope.consultations[counter].alert

            };
            secretaryFactory.updateConsultation(newData);
        };

        $scope.getIdOfPatient = function(id){
            for(i in $scope.patients){
                if($scope.patients[i].name === id){
                    return $scope.patients[i].pnc;
                }
            }
        };


        $scope.navigatorConfig = {
            selectMode: "day",
            showMonths: 3,
            skipMonths: 3,
            theme: "navigator_green",
            onTimeRangeSelected: function (args) {
                $scope.weekConfig.startDate = args.day;
                $scope.dayConfig.startDate = args.day;
                loadEvents();
            }
        };

        function loadEvents() {
            // using $timeout to make sure all changes are applied before reading visibleStart() and visibleEnd()
            $timeout(function () {
                var params = {
                    start: $scope.week.visibleStart().toString(),
                    end: $scope.week.visibleEnd().toString()
                }
                /*  $http.post("backend_events.php", params).success(function(data) {
                 $scope.events = data;
                 });*/
            });
        }

        $scope.dayConfig.visible = false;
        $scope.weekConfig.visible = true;
        $scope.showDay = function () {
            $scope.dayConfig.visible = true;
            $scope.weekConfig.visible = false;
        };
        $scope.showWeek = function () {
            $scope.dayConfig.visible = false;
            $scope.weekConfig.visible = true;
        };


        //SORT SCRIPT//////////////////////////////////////////////////
        //SORT SCRIPT//////////////////////////////////////////////////
        //SORT SCRIPT//////////////////////////////////////////////////

        var vm = this;
        $scope.pageno = 1; // initialize page no to 1
        $scope.total_count = 0;
        $scope.itemsPerPage = 5;
        $scope.getData = function (pageno) { // This would fetch the data on page change.
            //In practice this should be in a factory.
            $scope.doctors = [];
            secretaryFactory.getAllDoctors().then(function (response) {
                //ajax request to fetch data into vm.data
                $scope.doctors = response.data;  // data to be displayed on current page.
                $scope.total_count = response.total_count; // total data count.
            });
        };
        $scope.getData($scope.pageno); // Call the function to fetch initial data on page load.


        $scope.setSelectedDoctor = function (name,nume) {
            $scope.events.splice(0);
            $scope.doctorSelected = name;
            $scope.doctorSelectedName = nume;
            var email = {
                "msg": $scope.doctorSelected
            };
            secretaryFactory.getConsultationOfDoctor(email).then(function(d){

                $scope.consultations = d.data;
                for(i in  $scope.consultations){

                    if($scope.consultations[i].checked === "true") {
                        var data = {
                            "id": $scope.consultations[i].id,
                            "text": $scope.consultations[i].patient.name,
                            "start": $scope.consultations[i].startDate,
                            "end": $scope.consultations[i].endDate,
                            "backColor" : "#660a16"
                        };
                    } else {
                        var data = {
                            "id": $scope.consultations[i].id,
                            "text": $scope.consultations[i].patient.name,
                            "start": $scope.consultations[i].startDate,
                            "end": $scope.consultations[i].endDate
                        };
                    }
                    if($scope.filterEvents){
                        var string = $scope.consultations[i].patient.name;
                        var substring = $scope.filterEvents;
                        if(string.indexOf(substring) !== -1){
                            $scope.addEvent(data);
                        }
                    } else {
                        $scope.addEvent(data);
                    }

                }

            })
        };

        $scope.searchPatient = function(){
            $scope.setSelectedDoctor($scope.doctorSelected);
        };

        $scope.addEvent = function(event){
            var testDuplicate = false;
            for(i in $scope.events){
                if($scope.events[i].id === event.id){
                    testDuplicate = true;
                }
            }
            if(!testDuplicate){
                $scope.events.push(event);
            }
        };

        $scope.setSelectedPatient = function (name) {

            $scope.patientSelected = name;
        };

        $scope.addConsult = function(){
            $scope.consultTriggered = true;
            var validTest = true;
           if(!$scope.doctorSelected ||  !$scope.patientSelected ||   !$scope.selectedNewStart  || !$scope.selectedNewEnd ){
               validTest = false;
           }


            var patientBody;

            for (i in $scope.patients) {
                if ($scope.patients[i].name === $scope.patientSelected) {
                    patientBody = $scope.patients[i];
                }
            }

           if(validTest){
               var consult = {
                   "id": "12",
                   "patient": patientBody,
                   "startDate": $scope.selectedNewStart,
                   "endDate": $scope.selectedNewEnd,
                   "doctorUsername": $scope.doctorSelected,
                   "details" : "Created",
                   "checked" : "false",
                   "alert" : "false"
               };


               secretaryFactory.addNewConsultation(consult).then(function () {
                   sweetAlert('Success', 'Consultation added', "success");
                   $scope.setSelectedDoctor($scope.doctorSelected);
               });
           } else {
               sweetAlert("Data invalid", "Please fill in all fields", "error");
           }
        };

        $scope.deleteConsultation = function(){
            for(i in $scope.events){
                if($scope.events[i].id === $scope.eventConsultID){
                    $scope.counter = $scope.events[i];
                }
            }
            swal({
                    title: "Are you sure?",
                    text: "You will not be able to recover this consultation!",
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
                        $scope.events.splice($scope.counter, 1);
                        $scope.clust = {};
                        secretaryFactory.deleteConsultation($scope.eventConsultID).then(function(){
                            swal("Deleted!", "This consultation has been deleted.", "success");
                            $scope.setSelectedDoctor($scope.doctorSelected);
                        });

                    } else {
                        swal("Cancelled", "This consultation is safe :)", "error");
                    }
                });
        };

        $scope.announceDoctor = function(){
            var pat_id =  $scope.getIdOfPatient($scope.eventText);
            var newData = {
                "id":  $scope.eventConsultID,
                "patient_id":  pat_id,
                "startDate":  $scope.eventStartDateFull,
                "endDate":  $scope.eventEndDateFull,
                "details": "Created - ",
                "checked": "false",
                "alert": "true"
            };
            var counter =null;
            for(i in $scope.consultations){
                if($scope.consultations[i].id === $scope.eventConsultID){
                    counter = i;
                }
            }
            if(counter !== null){
                console.log($scope.consultations[counter].alert);
                if($scope.consultations[counter].alert !== "true"){
                    secretaryFactory.updateConsultation(newData);
                    var email = {
                        "msg": $scope.doctorSelected
                    };
                    secretaryFactory.getConsultationOfDoctor(email).then(function(d){
                        $scope.consultations = d.data;
                    });
                    $('#eventModal').modal('hide');
                } else {
                    sweetAlert("Fail", "Doctor already announced", "warning");
                    $('#eventModal').modal('hide');
                }
            }

        };

        
    }]
);

secretary.controller('secretaryCtrl', ['$scope', 'secretaryFactory', '$rootScope', '$location', '$window', '$timeout',
    function ($scope, secretaryFactory, $rootScope, $location, $window, $timeout) {
        $scope.patients = null;

        var vm = this;
        $scope.pageno = 1; // initialize page no to 1
        $scope.total_count = 0;
        $scope.itemsPerPage = 13;
        $scope.getData = function (pageno) { // This would fetch the data on page change.
            //In practice this should be in a factory.
            $scope.patients = [];
            secretaryFactory.getAllPatients().then(function (response) {
                //ajax request to fetch data into vm.data
                $scope.patients = response.data;  // data to be displayed on current page.
                $scope.total_count = response.total_count; // total data count.
            });
        };
        $scope.getData($scope.pageno); // Call the function to fetch initial data on page load.

        secretaryFactory.getAllPatients().then(function (d) { //2. so you can use .then()
            $scope.patients = d.data;
            $scope.list = $scope.patients;
        });

        $scope.select = {};
        $scope.select.pnc = null;
        $scope.select.idcard = null;
        $scope.select.name = null;
        $scope.select.dateOfBirth = null;
        $scope.select.address = null;
        
        $scope.form_pnc =null;
        $scope.form_idcard =null;
        $scope.form_name =null;
        $scope.form_date =null;
        $scope.form_address =null;

        $scope.passwordRegex = /^[a-zA-Z0-9]*$/i;
        $scope.pncPattern = /^(1|2|5|6)(0[0-9]|1[0-7]|[3-9][0-9])(0[1-9]|[10]|[11]|[12])([0-2][1-9]|[20]|3[0-1])\d{6}$/i;
        $scope.idcardPattern = /^[A-Za-z][A-Za-z][0-9][0-9][0-9][0-9][0-9][0-9]$/i;
        $scope.dateOfBirthPattern = /^(([2][0][0-1][0-9])|([1][9][2-9][0-9]))[\-](([0][1-9])|([1][0-2]))[\-](([0][1-9])|(([1][0-9])|([2][0-9])|([3][0-1])))$/i;

        $scope.sort = function (keyname) {
            $scope.sortKey = keyname;   //set the sortKey to the param passed
            $scope.reverse = !$scope.reverse; //if true make it false and vice versa
        }

        $scope.setSelectedPatient = function (pnc) {
            $scope.PNCSselected = pnc;

            var enabler = document.getElementById("updatePatientM");
            enabler.disabled = false;
            enabler = document.getElementById("removePatientM");
            enabler.disabled = false;
        };
        
        $scope.setToNull = function () {
            $scope.form_pnc =null;
            $scope.form_idcard =null;
            $scope.form_name =null;
            $scope.form_date =null;
            $scope.form_address =null;
        }


        $scope.currentPage = 1;
        $scope.numPerPage = 10;
        $scope.paginate = function (value) {
            var begin, end, index;
            begin = ($scope.currentPage - 1) * $scope.numPerPage;
            end = begin + $scope.numPerPage;
            index = $scope.patients.indexOf(value);
            return (begin <= index && index < end);
        };


        $scope.addPatient = function (isValid) {
            if (isValid) {
                var testForDuplicate = false;
                for (i in $scope.patients) {

                    if ($scope.patients[i].pnc === $scope.form_pnc) {

                        testForDuplicate = true;
                    }
                }

                
                if (testForDuplicate) {
                    sweetAlert("Fail", "Personal numerical code should be unique", "warning");
                } else {

                    var data = {
                        pnc: $scope.form_pnc,
                        idcard: $scope.form_idcard,
                        name: $scope.form_name,
                        dateOfBirth: $scope.form_date,
                        address: $scope.form_address
                    };

                    secretaryFactory.addNewPatient(data).then(function () {
                        sweetAlert('Success', 'Patient added', "success");
                    });


                    $scope.patients.push({
                        pnc: $scope.form_pnc,
                        idcard: $scope.form_idcard,
                        name: $scope.form_name,
                        dateOfBirth: $scope.form_date,
                        address: $scope.form_address
                    });

                    $scope.setToNull();
                }
            } else {
                sweetAlert("Data invalid", "Please fill in all fields", "error");
            }
        }

        $scope.updatePatient = function (isValid) {
            if (isValid) {

                var data = {
                    pnc: $scope.select.pnc,
                    idcard: $scope.select.idcard,
                    name: $scope.select.name,
                    dateOfBirth: $scope.select.dateOfBirth,
                    address: $scope.select.address
                };

                secretaryFactory.updatePatient(data).then(function () {
                    sweetAlert('Success', 'Patient info updated', "success");
                });

                for (i in $scope.patients) {
                    if ($scope.patients[i].pnc === $scope.select.pnc) {
                        $scope.patients[i].idcard = $scope.select.idcard;
                        $scope.patients[i].name = $scope.select.name;
                        $scope.patients[i].dateOfBirth = $scope.select.dateOfBirth;
                        $scope.patients[i].address = $scope.select.address;
                    }
                }

            } else {
                sweetAlert("Data invalid", "Please fill in all fields", "error");
            }

        }

        $scope.deletePatient = function () {
            //search cluster with given id and delete it
            $scope.counter = 0;
            for (i in $scope.patients) {
                if ($scope.patients[i].pnc === $scope.select.pnc) {

                    $scope.counter = i;
                    swal({
                            title: "Are you sure?",
                            text: "You will not be able to recover this patient account!",
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
                                $scope.patients.splice($scope.counter, 1);
                                $scope.clust = {};
                                secretaryFactory.deletePatientByPNC($scope.select.pnc).then(function () {
                                    swal("Deleted!", "This patient has been deleted.", "success");
                                });

                            } else {
                                swal("Cancelled", "This patient is safe :)", "error");
                            }
                        });

                }
            }
        }
        
    }
]);

