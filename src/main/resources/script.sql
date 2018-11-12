create database db_example; -- Create the new database
create user 'springuser'@'localhost' identified by 'ThePassword'; -- Creates the user
grant all on db_example.* to 'springuser'@'localhost'; -- Gives all the privileges to the new user on the newly created database



insert into student
values(1,'Ravi', 'A1234568');

insert into student
values(2,'Hugo', 'E1234567');

select * from student

SHOW VARIABLES LIKE "%version%";