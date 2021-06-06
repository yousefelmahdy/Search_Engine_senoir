<<<<<<< HEAD

use Project;
=======
CREATE DATABASE `Project`; 
USE `Project`;

>>>>>>> 0b543b6190cabf0b7776a311992cd3b3240f2c9c
create table URLs
(
noOfDocument int,
URL varchar(1000) not null,
title varchar(1000) not null,
<<<<<<< HEAD
description varchar(8000),

primary key (noOfDocument)
=======
descriptions varchar(10000),

primary key (noOfDocument)



>>>>>>> 0b543b6190cabf0b7776a311992cd3b3240f2c9c
);


create table Frequencies
(
word varchar(25),
<<<<<<< HEAD
noOfDocument int,
TF int not null,
=======
noOfDocument int(10),
TF int(10) not null,
>>>>>>> 0b543b6190cabf0b7776a311992cd3b3240f2c9c

primary key (word,noOfDocument),
foreign key (noOfDocument) references URLs(noOfDocument) ON DELETE CASCADE

);
<<<<<<< HEAD
=======
ALTER USER 'root'@'localhost' IDENTIFIED WITH mysql_native_password BY '1234';
-- insert into URLs values(1,'https://stackoverflow.com/questions/28991391','no title','no discription');
-- insert into URLs values(2,'https://stackoverflow.com','no title','no discription');

-- insert into Frequencies values('run',1,10);
-- insert into Frequencies values('run',2,5);
>>>>>>> 0b543b6190cabf0b7776a311992cd3b3240f2c9c


