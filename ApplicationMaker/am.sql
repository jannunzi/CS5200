create table developer (
  id serial primary key,
  username varchar(30),
  password varchar(30),
  email varchar(30)
);

create table application (
  id serial primary key,
  name varchar(30),
  description varchar(200),
  developerId int references developer(id)
);

create table page (
  id serial primary key,
  name varchar(30),
  applicationId int references application(id)
);

select * from developer;

select * from application;