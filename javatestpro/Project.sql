CREATE DATABASE `Project`; 
USE `Project`;

create table URLs
(
noOfDocument int,
URL varchar(1000) not null,
title varchar(1000) not null,
descriptions varchar(10000),

primary key (noOfDocument)



);


create table Frequencies
(
word varchar(25),
noOfDocument int(10),
TF int(10) not null,

primary key (word,noOfDocument),
foreign key (noOfDocument) references URLs(noOfDocument) ON DELETE CASCADE

);
ALTER USER 'root'@'localhost' IDENTIFIED WITH mysql_native_password BY '1234';
-- insert into URLs values(1,'https://stackoverflow.com/questions/28991391','no title','no discription');
-- insert into URLs values(2,'https://stackoverflow.com','no title','no discription');

-- insert into Frequencies values('run',1,10);
-- insert into Frequencies values('run',2,5);


