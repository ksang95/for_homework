drop table savingaccount;
drop table loanaccount;

create table savingaccount(
	name varchar2(50),
	term number(10),
	monthly number(10),
	interest number(10),
	rate number(3,1),
	accountnum varchar2(10) primary key,
	total number(10),
	refund number(10)
);

create table loanaccount(
	name varchar2(50),
	term number(10),
	loan number(10),
	interest number(10),
	rate number(3,1),
	accountnum varchar2(10) primary key,
	monthly number(10)
);