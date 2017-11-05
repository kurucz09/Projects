----------------------------------------------------------------------------------------------
1. Using BankWebApplication online - needs an internet connection and may be used only as a front  desk user. It is necessarry to have an account created a priori.
Access the link bank.dezbateri.ro.
----------------------------------------------------------------------------------------------
2. Using BankWebApplication on the dekstop machine
For using bank web application on your comouter, the following steps should be followed:

Backend and Frontend components
a. Clone git repository: $git clone https://github.com/SD-PS2017/30432-a1-bogdanstupariu.git

b. Download and install the latest version of the Java Platform, Standard Edition Development Kit (Java SE 6 Update 27). Note the installation directory for later—probably something like C:\Program Files\Java\jdk1.6.0_27\bin.

c. To make sure that Windows can find the Java compiler and interpreter:
Select Start -> Computer -> System Properties -> Advanced system settings -> Environment Variables -> System variables -> PATH.

[ In Vista, select Start -> My Computer -> Properties -> Advanced -> Environment Variables -> System variables -> PATH. ]

[ In Windows XP, Select Start -> Control Panel -> System -> Advanced -> Environment Variables -> System variables -> PATH. ]

Prepend C:\Program Files\Java\jdk1.6.0_27\bin; to the beginning of the PATH variable.
Click OK three times.

d. Launch the command prompt via All Programs -> Accessories -> Command Prompt.
From the Command Prompt, navigate to the directory containing your .java files, say C:\introcs\hello, by typing the cd command below.
C:\Users\username>cd c:\30432-a1-bogdanstupariu\Bank_Backend\
C:\30432-a1-bogdanstupariu\Bank_Backend\>
Assuming the file, Main.java, is in the current working directory, type the javac command in boldface below to compile it.
C:\30432-a1-bogdanstupariu\Bank_Backend\>javac \src\main\java\com\stupariu
From the Command Prompt, type the java command below.
C:\30432-a1-bogdanstupariu\Bank_Backend\>java \src\main\java\com\stupariu

/////////////////////////
Install tomcat

/////////////////////////
Database component
a. Install MySQL workspace
b. Setup the localhost on port 3306
c. From the downloaded git repository, import the scripts from the sql folder in a new mysql schema called bank

-------------------------------------------------------------------------------------------
3. Using BankWebApplication as a developer - install all components from scretch
a. Clone git repository: $git clone https://github.com/SD-PS2017/30432-a1-bogdanstupariu.git
b. Install MySQL workspace
. Setup the localhost on port 3306
. From the downloaded git repository, import the scripts from the sql folder in a new mysql schema called bank 
c. Install an IDE for the backend (recommended Eclipse EE or IntelliJ)
d. Install maven framework
e. Create a new Meven project and Get spring boot framework
f. Create a new SpringBoot project inside and import the project from the Bank_Backend folder.
g. Update the maven project and Run as SpringBoot project
h. Install (reommended but others may be just perfect) WebStorm IDE
i. Import the project from the Bank_Front folder in repository
j. Run index.html

-------------------------------------------------------------------------------------------
-------------------------------------------------------------------------------------------
----------------------------------HOW TO USE-----------------------------------------------
-------------------------------------------------------------------------------------------
-------------------------------------------------------------------------------------------

1. LOGIN PAGE:
Insert the account (username and password) provided by the developer/administrator and login in the application. In case of success, you will be redirected to the page for which you have the rights (administrator or regular). In case of failuri, a message will be displayed on the screen with the error reason (invalid account, no username, no password). In case the invallid account message persists and the data is well inserted, please contact the administrator.
2. ADMINISTRATOR PAGE:
After logging in with an administrator account, a page with all the users will be displayed. The users are organized in a table and the information about them is displayed. The administrator may perform the following actions on the users:
- Add user - createing a new user by inserting all the necessarry data in the input area and with the right format, and by choosing the proper user type. The user is created after triggering the add user button.
- Update user - updating the information about a user is done by firstly selecting which user to be edited by selecting the edit option in the table in the row corresponding with the desired user. When this action is triggered, the input fields will be filled with the selected user data and the update user button will be activated. Editing data is done by changing the content in the input fields and triggering the update user button.
- Delete user - deleting a user from the database by triggering the delete button in the row corresponsing with the desired to delete user in table. A request of confirmation message will be displayed. After confirm, the user will be deleted
- View reports - viewing all the activity a user has done. Redirecting to the page:
REPORTS - the page will initially display all the log activity data from the database. There are other options on this page:
	- Generating reports for specific date interval - filling the inputs (starting date and eding date) and pressing generate will update the shown data with the desired one. The table will display the activities done by the selected user in the interval specified.
	- Download report - by triggering this option, a report containing the data in the table will be downloaded on the dektop computer.
3. REGULAR USER PAGE:
After logging in with an regular useer account, a page with all the client  of the bank will be displayed. The clients are organized in a table with all the information about them displayed. The regular user may perform the following aconts on the clients:
- Add client - createing a new client by inserting all the necessarry data in the input area and with the right format. The client is created after triggering the add client button.
- Update client - updating the information about a client  is done by firstly selecting which client  to be edited by selecting the edit option in the table in the row corresponding with the desired client . When this action is triggered, the input fields will be filled with the selected client  data and the update client  button will be activated. Editing data is done by changing the content in the input fields and triggering the update client  button.
- Delete client  - deleting a client  from the database by triggering the delete button in the row corresponsing with the desired to delete client  in table. A request of confirmation message will be displayed. After confirm, the client will be deleted.
- Accounts - by pressing the Accounts option, a new page will be opened with all the information contained in the database about the accounts of the selected clients
ACCOUNTS - The page will display all the accounts the selected clients has. There are other options on this page:
	- Add account - works similarily with the add client action
	- Update account - works similarily with the update client action
	- Delete account - works similarily with the delete client action
	- Operations on credit - adds or subtracts credit from a preselected account. THis action may be done by selecting an acount to be edited, filling the sum in the amount area, an pressing the + or - buttons for the desired action. Instatly the information on table will be updated.
	- Transactions - Transacts money between accounts. First of all, an account should be selected by pressing edit option - this account will be the source in the transaction. Another account will be selected from one of two options - choose receiver account from dropdown list or insert the account manually. After setting the accounts, the amount to be trasferred will be filled in. Attention to not send an amount bigger than the existing one in the sedining account - the application will give an error message pointing this problem.	
	- Process bills - trigerring this action will redirect the user to the bills page:
		- bills page shows all the bills the client requested to be paid. Basically, when scanning a bill, a request for the bank to make a transaction in made. The bank user will see the paid bills and the requested to be paid one in a table. Before the table, a list of the client's accounts and the amount in ther is shown. The user can pay the bills from the account from which the client requested so.


-------------------------------------------------------------------------------------------
-------------------------------------------------------------------------------------------
------------------------------------CREDITS------------------------------------------------
-------------------------------------------------------------------------------------------
-------------------------------------------------------------------------------------------

Created by Bogdan Stupariu, 2017
bogdan.stupariu0@gmail.com
