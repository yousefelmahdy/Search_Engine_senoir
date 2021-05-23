CREATE DATABASE `Project`; 
USE `Project`;

create table URLs
(
noOfDocument int(10),
URL varchar(1000) not null,
title varchar(1000) not null,
description varchar(10000),

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


