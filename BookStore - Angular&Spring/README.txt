----------------------------------------------------------------------------------------------
1. Using BookStoreApplication online - needs an internet connection and may be used only as a front  desk user. It is necessarry to have an account created a priori.
Access the link SBookStore.com .
----------------------------------------------------------------------------------------------
2. Using BookStoreApplication on the dekstop machine
For using bank web application on your comouter, the following steps should be followed:

Backend and Frontend components
a. Clone git repository: $git clone https://github.com/SD-PS2017/30432-a2-bogdanstupariu.git

b. Download and install the latest version of the Java Platform, Standard Edition Development Kit (Java SE 6 Update 27). Note the installation directory for later—probably something like C:\Program Files\Java\jdk1.6.0_27\bin.

c. To make sure that Windows can find the Java compiler and interpreter:
Select Start -> Computer -> System Properties -> Advanced system settings -> Environment Variables -> System variables -> PATH.

[ In Vista, select Start -> My Computer -> Properties -> Advanced -> Environment Variables -> System variables -> PATH. ]

[ In Windows XP, Select Start -> Control Panel -> System -> Advanced -> Environment Variables -> System variables -> PATH. ]

Prepend C:\Program Files\Java\jdk1.6.0_27\bin; to the beginning of the PATH variable.
Click OK three times.

d. Launch the command prompt via All Programs -> Accessories -> Command Prompt.
From the Command Prompt, navigate to the directory containing your .java files, say C:\introcs\hello, by typing the cd command below.
C:\Users\username>cd c:\30432-a2-bogdanstupariu\bookstore\
C:\30432-a2-bogdanstupariu\bookstore\>
Assuming the file, app.java, is in the current working directory, type the javac command in boldface below to compile it.
C:\30432-a2-bogdanstupariu\bookstore\>javac \src\main\java\com\stupariu
From the Command Prompt, type the java command below.
C:\30432-a2-bogdanstupariu\bookstore\>java \src\main\java\com\stupariu

/////////////////////////
Install tomcat

/////////////////////////
In case the browser blocks using the frontend side because of cross origin permissions, create a server and run the frontend on a virtual server.

-------------------------------------------------------------------------------------------
3. Using BookStoreApplication as a developer - install all components from scretch
a. Clone git repository: $git clone https://github.com/SD-PS2017/30432-a2-bogdanstupariu.git
b. Install an IDE for the backend (recommended Eclipse EE or IntelliJ)
c. Install maven framework
d. Create a new Meven project and Get spring boot framework
e. Create a new SpringBoot project inside and import the project from the boostore folder.
f. Add the jaxb framework
g. Update the maven project and Run as SpringBoot project
h. Install (reommended but others may be just perfect) WebStorm IDE
i. Import the project from the BookStore_Front folder in repository
j. Run index.html

-------------------------------------------------------------------------------------------
-------------------------------------------------------------------------------------------
----------------------------------HOW TO USE-----------------------------------------------
-------------------------------------------------------------------------------------------
-------------------------------------------------------------------------------------------

1. LOGIN PAGE:
Insert the account (username and password) provided by the developer/administrator and login in the application. In case of success, you will be redirected to the page for which you have the rights (administrator or regular). In case of failure, a message will be displayed on the screen with the error reason (invalid account, no username, no password). In case the invallid account message persists and the data is well inserted, please contact the administrator.
2. ADMINISTRATOR PAGE:
After logging in with an administrator account, a page with all the users will be displayed. The users are organized in a table and the information about them is displayed. The administrator may perform the following actions on the users:
- Add user - createing a new user by inserting all the necessarry data in the input area and with the right format, and by choosing the proper user type. The user is created after triggering the add user button.
- Update user - updating the information about a user is done by firstly selecting which user to be edited by selecting the edit option in the table in the row corresponding with the desired user. When this action is triggered, the input fields will be filled with the selected user data and the update user button will be activated. Editing data is done by changing the content in the input fields and triggering the update user button.
- Delete user - deleting a user from the database by triggering the delete button in the row corresponsing with the desired to delete user in table. A request of confirmation message will be displayed. After confirm, the user will be deleted

The administrator may switch to the Books page -> all the books in the database will be shown. The following operations may be done:
- Add Book - create a new book by inserting all the necessary data in the right format. If the format is wrong, a live message will be shown. If not all the filds are completed, a message will be shown. No action may be done until all conditions are aquired.
- Update Book - Select a book from the table, trigger update button, change values (correct format), triggeer update button.
- Delete Book- Select a book from table, trigger delete button, confirm or dismisss action.
- Search in table - fill in the search form for filtering the table
- Increase/Decrease stock - at each book, trigger the + or - buttons in order to increase or decrease the number of that specific book from the stock.

The administrator may switch to the reports page -> all the actions ( sells) done by the regular users are shown in a table. Two options available:
- generate PDF report - generates a report with all out of stock books in pdf format
- generate CSV report - generates a report with all out of stock books in csv format

3. REGULAR USER PAGE:
After logging in with an regular useer account, a page with all the books in the database is shown. The following actions may be taken:
- Sell book - Select a book and fill in the quantity field. Press sell button.
- Search by genre
- Search by title
- Search by author

-------------------------------------------------------------------------------------------
-------------------------------------------------------------------------------------------
------------------------------------CREDITS------------------------------------------------
-------------------------------------------------------------------------------------------
-------------------------------------------------------------------------------------------

Created by Bogdan Stupariu, 2017
bogdan.stupariu0@gmail.com
