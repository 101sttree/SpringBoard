create table usertb
(
	uno int auto_increment,
	udate date,
	id varchar(50),
	pw varchar(50),
	name varchar(20),
	primary key(uno)
);
create databases springBoard;
create table boardtb
(
	bno int auto_increment,
	uno int,
	writer varchar(50),
	bdate date,
	hit int,
	title varchar(100),
	btext text,
	primary key(bno),
	foreign key (uno) references usertb (uno)
);

create table commenttb
(
	cno int auto_increment,
	bno int,
	uno int,
	cwriter varchar(50),
	cdate date,
	ctext text,
	primary key(cno),
	foreign key (bno) references boardtb (bno),
	foreign key (uno) references usertb (uno)
);

create table filetb
(
	fno int auto_increment,
	bno int,
	uno int,
	fname varchar(500),
	ex varchar(10),
	path varchar(500),
	fsize int,
	primary key(fno),
	foreign key (bno) references boardtb (bno),
	foreign key (uno) references usertb (uno)
);