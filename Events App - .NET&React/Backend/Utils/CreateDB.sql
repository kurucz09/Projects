USE master
IF EXISTS(select * from sys.databases where name='Evenimente')
DROP DATABASE Evenimente
go
DROP TABLE IF EXISTS Ratings;
DROP TABLE IF EXISTS Comments;
DROP TABLE IF EXISTS Tickets;
DROP TABLE IF EXISTS Pictures;
DROP TABLE IF EXISTS [Events];
DROP TABLE IF EXISTS Users;
DROP TABLE IF EXISTS Categories;
DROP TABLE IF EXISTS Roles;
go
CREATE DATABASE Evenimente;
go
use [Evenimente];
go



create table Categories
(
	Id int primary key identity(1000,1),
	[Name] nvarchar(30),
	PictureURL nvarchar(4000)

)
go
create table Roles
(
	Id int primary key identity(1000,1),
	[Name] nvarchar(10)
)
go

create table Users
(
	 Id int  primary key identity(1000,1),
	 [Name] nvarchar(200),
	 Username nvarchar(50),
	 [Password] nvarchar(90),
	 Email nvarchar(600),
	 DateBirth nvarchar(12),
	 PictureURL nvarchar(MAX),
	 RoleId int FOREIGN KEY REFERENCES Roles(Id) 
)
go

create table [Events]
(
	Id int primary key identity(1000,1),
	[Name] nvarchar(30),
	[Description] nvarchar(4000),
	Place nvarchar(300),
	[Hour] nvarchar(10),
	BeginDate nvarchar(12),
	EndDate nvarchar(12),
	MinDateBirth nvarchar(12),
	NumberOfTickets int,
	Price int,
	[Status] nvarchar(10),
	UserId int FOREIGN KEY REFERENCES Users(Id),
	CategoryId int FOREIGN KEY REFERENCES Categories(Id)

)
go

create table Pictures
(
	Id int primary key identity(1000,1),
	PictureURL nvarchar(MAX),
	EventId int FOREIGN KEY REFERENCES [Events](Id)
	
)
go

create table Tickets
(
	 Id int primary key identity(1000,1),
	 EventId int FOREIGN KEY REFERENCES [Events](Id),
	 UserId int FOREIGN KEY REFERENCES Users(Id),
	 BuyedTickets int,
	 [Status] nvarchar(20)
)
go

create table Comments
(
	Id int  primary key identity(1000,1),
	[Description] nvarchar(MAX),
	[Date] nvarchar(15),
	EventId int FOREIGN KEY REFERENCES [Events](Id),
	UserId int FOREIGN KEY REFERENCES Users(Id)
)
go

create table Ratings 
(
	Id int  primary key identity(1000,1),
	EventId int FOREIGN KEY REFERENCES [Events](Id),
	UserId int FOREIGN KEY REFERENCES Users(Id),
	Points int
)
go
