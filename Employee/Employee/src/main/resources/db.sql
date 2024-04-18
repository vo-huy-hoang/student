create database employee_manager;
use employee_manager;

create table user (
    id int primary key auto_increment,
    username varchar(50) not null unique,
    password varchar(100)
);

create table role (
    id int primary key auto_increment,
    name varchar(100) not null
);

create table user_role (
    id int primary key auto_increment,
    user_id int,
    role_id int,
    foreign key (user_id) references user(id),
    foreign key (role_id) references role(id)
);

create table department (
    id int primary key auto_increment,
    name varchar(100) not null
);

create table employee (
    id int primary key auto_increment,
    name varchar(100) not null,
    birthDay date,
    gender boolean,
    salary double,
    phone_number varchar(50) not null unique,
    department_id int,
    foreign key (department_id) references department(id)
);
insert into department (id, name)
values (1, 'Quản Lý'),
       (2, 'Kế Toán'),
       (3, 'Sale-Marketing'),
       (4, 'Sản Xuất');

insert into employee(name, birthDay, gender, salary, phone_number, department_id)
values  ('Nguyễn Văn A', '1990-01-15', true, 15000000.0, '0935271790', 1),
        ('Nguyễn Văn B', '1991-02-16', false, 16100000.0, '0935271791', 2),
        ('Nguyễn Văn C', '1992-03-17', true, 12100000.0,'0935271792', 3),
        ('Nguyễn Văn D', '1993-04-18', false, 13400000.0, '0935271793', 4),
        ('Nguyễn Văn E', '1993-04-18', true, 13400000.0, '0935271794', 4);

insert into user(username, password) values ('VHH', '$2a$12$05awLpawJIrjt7927dKqxumfu6jjjKqUziXEJOHGCP8qFXLtooeb');
insert into user(id, username, password) values (2, 'user', '$2a$12$wQFgfHG97sA9X3DaQ6qUneK7BAZEEHg8oJgZB4bNAv3qGFpvjiY5i');
insert into user(id, username, password) values (3, 'admin', '$2a$12$iab6gj3zZHzCbeJ8AcQ9jeiM6gSakTFgDD6UqOyfkX7KFUTASk9hi');


insert into role (id, name)
values (1, 'user'),
       (2, 'admin');

insert into user_role (user_id, role_id) values (1, 1);
insert into user_role (user_id, role_id) values (1, 2);
insert into user_role (user_id, role_id) values (2, 1);
insert into user_role (user_id, role_id) values (3, 2);
select e.id, e.name, e.birthDay, e.salary, e.phone_number, e.department_id, d.name from employee as e left join department d on e.department_id = d.id;

select r.name as role from user as u
join user_role as ur on u.id = ur.user_id
join role as r on ur.role_id = r.id where u.username = ?;