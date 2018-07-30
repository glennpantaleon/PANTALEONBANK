--Creates Registered User Account Table
create table RegiUsers(userid number(20) primary key,
fullname varchar2(60),
username varchar2(60),
userpassword varchar2(50),
superuser number(1));

--Creates Bank Account Table
create table RegiBankAccnt(userid number(10),
bankaccntid number(10) primary key,
accntbalan double precision);

alter table RegiBankAccnt add constraint FK_USERID foreign key (userid) references RegiUsers(userid);



alter table RegiBankAccnt drop constraint FK_USERID;
alter table TransactRecord drop constraint FK_BANKACCNTID;
--drop table RegiUsers;
--drop table RegiBankAccnt;





--Creates Transaction Record Table
create table TransactRecord(transid number(10) primary key,
bankaccntid number (10),
transtype varchar2(200),
oldaccntbalan double precision,
newaccntbalan double precision
);

alter table TransactRecord add constraint FK_BANKACCNTID foreign key (bankaccntid) references RegiBankAccnt(bankaccntid);



--Generate Registered User ID
create sequence regiuserid_gen
minvalue 0
start with 1
increment by 1 ;
--Generate Registered Bank Account ID
create sequence regibankid_gen
minvalue 0
start with 1
increment by 1 ;

--Adds Registered User Id to record
create or replace procedure add_user(fullname varchar2,username varchar2,
userpassword varchar2,superuser number)
is begin
insert into RegiUsers values(regiuserid_gen.nextval,fullname,username,userpassword,superuser);
end;

--Adds Registered Bank Account Id to record
create or replace procedure add_bank(userid number,accntbalan number)
is begin
insert into REGIBANKACCNT values(regibankid_gen.nextval,userid,accntbalan);
end;


create or replace function withdraw(accntbalan number,oldaccntbalan number)return number is newaccntbalan number(10);
begin
newaccntbalan := accntbalan - oldaccntbalan;
return newaccntbalan;
end;

create or replace function deposit(accntbalan number,oldaccntbalan number)return number is newaccntbalan number(10);
begin
newaccntbalan := accntbalan + oldaccntbalan;
return newaccntbalan;
end;

select * from RegiUsers;
select * from RegiBankAccnt;

commit;
rollback;