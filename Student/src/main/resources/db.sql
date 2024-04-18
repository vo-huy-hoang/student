create database student_manager;
use student_manager;


create table user (
   id int primary key auto_increment,
   username varchar(100) not null unique,
   password varchar(100) not null
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

create table clazz (
    id int primary key auto_increment,
    name varchar(100)
);

create table student (
    id int primary key auto_increment,
    name varchar(100),
    score double
);

alter table student add clazz_id int references clazz(id);

insert into clazz(name)
values  ('12A'),
        ('12B'),
        ('12C');

insert into student(name, score)
values ('Nguyễn Văn A', 9.2),
       ('Nguyễn Văn B', 8.4);

insert into user(username, password)
values ('VHH', 'VHH');

select id, name, score from student where id = ?;
insert into student (name, score) values (?, ?);

select s.id, s.name as name, s.score, c.name as clazz_name from student s inner join clazz c on s.clazz_id = c.id
where s.name like concat('%', ?, '%') and (s.score between ? and ?);
select id, name from clazz;
# insert into user(username, password) values ('VHH', '$2a$12$05awLpawJIrjt7927dKqxumfu6jjjKqUziXEJOHGCP8qFXLtooeb');
insert into user(id, username, password) values (2, 'user', '$2a$12$6eUmNqs2KvBmhKiKfZMK/OyVDwjgQ7Qs/MFagJJY2uNaxFao3TjyO');
insert into user(id, username, password) values (3, 'admin', '$2a$12$4Wy/lH8hzpG6z2auop2DK.8ym8jXcVhu5TTy6CnsnQlvsCqlHL9ta');


insert into role (id, name)
values (1, 'user'),
       (2, 'admin');

insert into user_role (user_id, role_id) values (1, 1);
insert into user_role (user_id, role_id) values (1, 2);
insert into user_role (user_id, role_id) values (2, 1);
insert into user_role (user_id, role_id) values (3, 2);

select r.name as role from user as u
join user_role as ur on u.id = ur.user_id
join role as r on ur.role_id = r.id where u.username = ?;

select * from user where username = ? and password = ?;
