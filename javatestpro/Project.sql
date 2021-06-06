
use Project;
create table URLs
(
noOfDocument int,
URL varchar(1000) not null,
title varchar(1000) not null,
description varchar(8000),

primary key (noOfDocument)
);


create table Frequencies
(
word varchar(25),
noOfDocument int,
TF int not null,

primary key (word,noOfDocument),
foreign key (noOfDocument) references URLs(noOfDocument) ON DELETE CASCADE

);


