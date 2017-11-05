# Medical Application 
Created by Bogdan Stupariu


The application is created for the third assignment of Software Designs' laboratory and uses the following technologies:
  - Database: MySQL Workbanch
  - Backend: Java 1.8, Spring Boot, Maven, JDBC
  - Frontend: Javascript, AngularJS (1.7), Bootstrap

> This is a client-server application for managing the consultations of doctors in a clinic. The application has three types of users: the clinic secretary, the doctors and an administrator.


## How to run the application


##### 1. Run the application online. 
This feature is possible by uploading both the back and front side on a server (or two). 
- The backend should be run on a virtual server with the port setted to localhost address port 8080.
- The database should be imported in an online database context (eighter on a cloud service, web hosting or other possible ways) alongside with changing the access settings from the application properties in the backend side. The address and port should be changed in order to match with the new setted ones.
- The frontend should be uploaded on a server. In order to make the requests to the backend work, the address should be updated to the new context. The settings are defined in the app.js file, under the API_URL constant as an address link.

**The backend is created in Java, so be sure to find a web hosting / cloud service that supports Java Hosting!**

##### 2. Run the application on a local machine

**Backend and Frontend components**
Clone git repository: 
```sh
$git clone https://github.com/SD-PS2017/30432-a3-bogdanstupariu.git
```

 Download and install the latest version of the Java Platform, Standard Edition Development Kit (Java SE 6 Update 27). Note the installation directory for later—probably something like 
 ```sh
 C:\Program Files\Java\jdk1.6.0_27\bin.
```
To make sure that Windows can find the Java compiler and interpreter:
Select Start -> Computer -> System Properties -> Advanced system settings -> Environment Variables -> System variables -> PATH.
- [ In Vista, select Start -> My Computer -> Properties -> Advanced -> Environment Variables -> System variables -> PATH. ]
- [ In Windows XP, Select Start -> Control Panel -> System -> Advanced -> Environment Variables -> System variables -> PATH. ]

Prepend 
```sh
C:\Program Files\Java\jdk1.6.0_27\bin; 
```
to the beginning of the PATH variable.
Click OK three times.

Launch the command prompt via All Programs -> Accessories -> Command Prompt.
From the Command Prompt, navigate to the directory containing your .java files, say
```sh
C:\introcs\hello, by typing the cd command below.
C:\Users\username>cd c:\30432-a3-bogdanstupariu\medapp_back\
C:\30432-a3-bogdanstupariu\medapp_back\>
```
Assuming the file, Main.java, is in the current working directory, type the javac command in boldface below to compile it.
```sh
C:\30432-a3-bogdanstupariu\medapp_back\>javac \src\main\java\com\stupariu
```
From the Command Prompt, type the java command below
```sh
C:\30432-a3-bogdanstupariu\medapp_back\>java \src\main\java\com\stupariu
```

**Install tomcat**

Follow the installation guide provided on the official website
-> [tomcat] installation guide

**Database component**
- Install MySQL workspace
-> [MySQL] installation guide
- Setup the localhost on port 3306
- From the downloaded git repository, import the scripts from the sql folder in a new mysql schema called bank
```sh
https://github.com/SD-PS2017/30432-a3-bogdanstupariu/blob/master/sql_query.sql
```

##### 3. Using BankWebApplication as a developer - install all components from scretch
Clone git repository: 
```
$git clone https://github.com/SD-PS2017/30432-a3-bogdanstupariu.git
```
1. Install MySQL workspace
1.1 Setup the localhost on port 3306
1.2 From the downloaded git repository, import the scripts from the sql folder in a new mysql schema called bank 
```sh
https://github.com/SD-PS2017/30432-a3-bogdanstupariu/blob/master/sql_query.sql
```
2. Install an IDE for the backend (recommended Eclipse EE or IntelliJ)
 [Eclipse] download
[IntelliJ] download
3. Install maven framework
4. Create a new Meven project and Get spring boot framework
5. Create a new SpringBoot project inside and import the project from the Bank_Backend folder.
6. Update the maven project and Run as SpringBoot project
7. Install (reommended but others may be just perfect) WebStorm IDE
[WebStorm] download
8. Import the project from the Bank_Front folder in repository
9. Run index.html

## How to use the application


**1. LOGIN PAGE:**
Insert the account (username and password) provided by the developer/administrator and login in the application. In case of success, you will be redirected to the page for which you have the rights (administrator or regular). In case of failure, a message will be displayed on the screen with the error reason (invalid account, no username, no password). In case the invallid account message persists and the data is well inserted, please contact the administrator.
![N|Solid](https://github.com/SD-PS2017/30432-a3-bogdanstupariu/blob/master/ScreenShots/Login/Screenshot%202017-06-01%2023.27.04.png?raw=true)
![N|Solid](https://github.com/SD-PS2017/30432-a3-bogdanstupariu/blob/master/ScreenShots/Login/Screenshot%202017-06-01%2023.27.28.png?raw=true)
![N|Solid](https://github.com/SD-PS2017/30432-a3-bogdanstupariu/blob/master/ScreenShots/Login/Screenshot%202017-06-01%2023.27.36.png?raw=true)

**2. ADMINISTRATOR PAGE:**
After logging in with an administrator account, a page with all the users will be displayed. The users are organized in a table and the information about them is displayed. The administrator may perform the following actions on the users:
![N|Solid](https://github.com/SD-PS2017/30432-a3-bogdanstupariu/blob/master/ScreenShots/Admin/Home.png?raw=true)
- Add user - createing a new user by inserting all the necessarry data in the input area and with the right format, and by choosing the proper user type. The user is created after triggering the add user button.
![N|Solid](https://github.com/SD-PS2017/30432-a3-bogdanstupariu/blob/master/ScreenShots/Admin/Add.png?raw=true)
- Update user - updating the information about a user is done by firstly selecting which user to be edited by selecting the edit option in the table in the row corresponding with the desired user. When this action is triggered, the input fields will be filled with the selected user data and the update user button will be activated. Editing data is done by changing the content in the input fields and triggering the update user button.
![N|Solid](https://github.com/SD-PS2017/30432-a3-bogdanstupariu/blob/master/ScreenShots/Admin/Remove.png?raw=true)
- Delete user - deleting a user from the database by triggering the delete button in the row corresponsing with the desired to delete user in table. A request of confirmation message will be displayed. After confirm, the user will be deleted
![N|Solid](https://github.com/SD-PS2017/30432-a3-bogdanstupariu/blob/master/ScreenShots/Admin/Delete.png?raw=true)
- Search users by email, name or type
![N|Solid](https://github.com/SD-PS2017/30432-a3-bogdanstupariu/blob/master/ScreenShots/Admin/Search.png?raw=true)
- Show password - protection for sensible data
![N|Solid](https://github.com/SD-PS2017/30432-a3-bogdanstupariu/blob/master/ScreenShots/Admin/ShowPasswordSelect.png?raw=true)

	
**3. SECRETARY PAGE**
After logging in with an secretary account, a page with all the patients will be displayed. The patients are organized in a table with all the information about them displayed. The secretary may perform the following aconts on the patients:
**3.1. The patients page**
![N|Solid](https://github.com/SD-PS2017/30432-a3-bogdanstupariu/blob/master/ScreenShots/Secretary/patient.png?raw=true)
- Add patient - createing a new patient by inserting all the necessarry data in the input area and with the right format. patient user is created after triggering the add user button.
![N|Solid](https://github.com/SD-PS2017/30432-a3-bogdanstupariu/blob/master/ScreenShots/Secretary/add.png?raw=true)
- Update patient - updating the information about a patient is done by firstly selecting which patient to be edited and by triggering the update button. When this action is triggered, the input fields will be filled with the selected patients' data and the update patient button will be activated. Editing data is done by changing the content in the input fields and triggering the update user button in the poped-up window.
![N|Solid](https://github.com/SD-PS2017/30432-a3-bogdanstupariu/blob/master/ScreenShots/Secretary/update.png?raw=true)
- Delete patient - deleting a patient from the database by selecting a patient and then triggering the delete button. A request of confirmation message will be displayed. After confirm, the user will be deleted
![N|Solid](https://github.com/SD-PS2017/30432-a3-bogdanstupariu/blob/master/ScreenShots/Secretary/remove.png?raw=true)
- Search patient name, personal numerical code, id card, address or birthdate
![N|Solid](https://github.com/SD-PS2017/30432-a3-bogdanstupariu/blob/master/ScreenShots/Secretary/search.png?raw=true)

**3.2. The Consultations page**
After triggering the Consultations page from the nav bar, the page with consultations will be opened. Here, the secretary has the following options:
![N|Solid](https://github.com/SD-PS2017/30432-a3-bogdanstupariu/blob/master/ScreenShots/Secretary/consultations.png?raw=true)
- Search for a doctor
![N|Solid](https://github.com/SD-PS2017/30432-a3-bogdanstupariu/blob/master/ScreenShots/Secretary/searchDoctor.png?raw=true)
- Search for a patient
- See consultations for a week
![N|Solid](https://github.com/SD-PS2017/30432-a3-bogdanstupariu/blob/master/ScreenShots/Secretary/consultations.png?raw=true)
- See consultations for a specific day
![N|Solid](https://github.com/SD-PS2017/30432-a3-bogdanstupariu/blob/master/ScreenShots/Secretary/dayconsults.png?raw=true)
- Search for specific consultation
![N|Solid](https://github.com/SD-PS2017/30432-a3-bogdanstupariu/blob/master/ScreenShots/Secretary/searchinday.png?raw=true)
- Add consultation
![N|Solid](https://github.com/SD-PS2017/30432-a3-bogdanstupariu/blob/master/ScreenShots/Secretary/addConsultbef.png)
![N|Solid](https://github.com/SD-PS2017/30432-a3-bogdanstupariu/blob/master/ScreenShots/Secretary/addconsult.png?raw=true)
- Edit consultation details - Done by moving or resizing the event in the calendar for changing the programming details.
![N|Solid](https://github.com/SD-PS2017/30432-a3-bogdanstupariu/blob/master/ScreenShots/Secretary/consultations.png?raw=true)
- Delete consultation
- Announce Doctor that the patient arrived for the consutlation
![N|Solid](https://github.com/SD-PS2017/30432-a3-bogdanstupariu/blob/master/ScreenShots/Secretary/announcedoctor.png?raw=true)

**4. SECRETARY PAGE**
After logging in with an doctor account, a page with all the patients and consutlations will be displayed. The data is organized in lists with all the information about them displayed. The Doctor may perform the following aconts on the patients:
![N|Solid](https://github.com/SD-PS2017/30432-a3-bogdanstupariu/blob/master/ScreenShots/Doctor/allConsultsOfDoctor.png?raw=true)
- Search patient
![N|Solid](https://github.com/SD-PS2017/30432-a3-bogdanstupariu/blob/master/ScreenShots/Doctor/SearchPatient.png?raw=true)
- View Consults of a patient
![N|Solid](https://github.com/SD-PS2017/30432-a3-bogdanstupariu/blob/master/ScreenShots/Doctor/ViewConsultsOfPatient.png?raw=true)
- If a patient arrives and the secretary triggers the button announcing that, the following message will pop-up
![N|Solid](https://github.com/SD-PS2017/30432-a3-bogdanstupariu/blob/master/ScreenShots/Doctor/PatientArived.png?raw=true)
After this, if the patient is welcomed, by clicking "ok", the following message appears:
![N|Solid](https://github.com/SD-PS2017/30432-a3-bogdanstupariu/blob/master/ScreenShots/Doctor/patient%20invited.png?raw=true)
This will change pe page to a page setted for the specific consutlation, with the following options:
Add consultation deatails and end consultation
![N|Solid](https://github.com/SD-PS2017/30432-a3-bogdanstupariu/blob/master/ScreenShots/Doctor/consultationinside.png?raw=true)


**Free Software, Hell Yeah!**

   [dill]: <https://github.com/joemccann/dillinger>
   [git-repo-url]: <https://github.com/joemccann/dillinger.git>
   [john gruber]: <http://daringfireball.net>
   [df1]: <http://daringfireball.net/projects/markdown/>
   [markdown-it]: <https://github.com/markdown-it/markdown-it>
   [Ace Editor]: <http://ace.ajax.org>
   [node.js]: <http://nodejs.org>
   [Twitter Bootstrap]: <http://twitter.github.com/bootstrap/>
   [jQuery]: <http://jquery.com>
   [@tjholowaychuk]: <http://twitter.com/tjholowaychuk>
   [express]: <http://expressjs.com>
   [AngularJS]: <http://angularjs.org>
   [Gulp]: <http://gulpjs.com>
   [tomcat]: <https://tomcat.apache.org/tomcat-7.0-doc/appdev/installation.html>
   [MySQL]: <https://dev.mysql.com/downloads/workbench/>
   [Eclipse]: <http://www.eclipse.org/downloads/download.php?file=/technology/epp/downloads/release/neon/3/eclipse-jee-neon-3-win32-x86_64.zip>
   [IntelliJ]: <https://www.jetbrains.com/idea/download/download-thanks.html?platform=windows>
   [WebStorm]: <https://www.jetbrains.com/webstorm/download/download-thanks.html?platform=windows>

   [PlDb]: <https://github.com/joemccann/dillinger/tree/master/plugins/dropbox/README.md>
   [PlGh]: <https://github.com/joemccann/dillinger/tree/master/plugins/github/README.md>
   [PlGd]: <https://github.com/joemccann/dillinger/tree/master/plugins/googledrive/README.md>
   [PlOd]: <https://github.com/joemccann/dillinger/tree/master/plugins/onedrive/README.md>
   [PlMe]: <https://github.com/joemccann/dillinger/tree/master/plugins/medium/README.md>
   [PlGa]: <https://github.com/RahulHP/dillinger/blob/master/plugins/googleanalytics/README.md>
