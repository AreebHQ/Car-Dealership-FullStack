/*DROP DATABASE IF EXISTS dealership;
CREATE DATABASE dealership;*/
USE dealership;

select * from user join role on user.role = role.role_id;
update vehicle set type = "New" where vehicle_id = 5;
delete from vehicle where vehicle_id = 5;
SELECT * FROM vehicle WHERE mileage = 0 AND sold = false;
SELECT md.name, m.name, v.* FROM Vehicle v join make m on m.make_id = v.make join
 model md on md.model_id = v.model WHERE v.mileage = 0 
AND year BETWEEN 0 AND 9999 AND sale_price BETWEEN 0 AND 999999 AND m.name = "supra"  OR md.name =  "supra" AND v.sold = false;

select * from message;
select * from user;
select * from vehicle;
select * from make;
select * from model;
select * from role;
select * from body;
select * from purchase;
select * from purchase_type;
select * from specials;
select * from color;
...;
/*DROP TABLE IF EXISTS vehicle;*/
CREATE TABLE vehicle (
vehicle_id int primary key auto_increment,
make int,
model int,
type varchar(10),
body int,
year varchar(10),
transmission varchar(15),
body_color int,
interior_color int,
mileage int,
vin_number varchar(20),
mrsp_price int,
sale_price int,
description varchar(500),
image varchar(50),
featured boolean,
sold boolean,
foreign key (make) references make(make_id),
foreign key (model) references model(model_id),
foreign key (body) references body(body_id),
foreign key (body_color) references color(color_id),
foreign key (interior_color) references color(color_id)
);

select * from vehicle;
select DISTINCT year from vehicle;
alter table vehicle modify column type varchar(15);


update vehicle set description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor gestas. Hac habitasse platea dictumst quisque sagittis. Dui nunc mattis enim ut tellus elementum sagittis. Nam aliquam sem et tortor consequat id. Lacus vel facilisis volutpat est velit egestas dui id. Consequat interdum varius sit amet mattis vulputate enim nulla. Felis donec et odio pellentesque. Lorem mollis aliquam ut porttitor leo a. Sodales ut et"
 where vehicle_id = 8;



update vehicle set image = "../images/car3.jpg" where vehicle_id = 2;
update vehicle set image = "../images/car2.jpg" where vehicle_id = 3;
update vehicle set image = "../images/car4.jpg" where vehicle_id = 4;
update vehicle set image = "../images/car5.jpg" where vehicle_id = 6;
update vehicle set image = "../images/car6.jpg" where vehicle_id = 7;
update vehicle set image = "../images/car7.jpg" where vehicle_id = 8;
update vehicle set image = "../images/car8.jpg" where vehicle_id = 9;
update vehicle set image = "../images/car9.jpg" where vehicle_id = 10;




INSERT INTO vehicle(make,model,type,body,year,transmission,body_color,interior_color,mileage,vin_number,mrsp_price,
sale_price,description,image,featured,sold)
VALUES (1,1,1,1,"2020","Automatic",1,2,21000,"4Y1SL65848Z411439",36000,34000,"A very good car","",false,false);

INSERT INTO vehicle(make,model,type,body,year,transmission,body_color,interior_color,mileage,vin_number,mrsp_price,
sale_price,description,image,featured,sold)
VALUES (2,2,2,2,"2021","Manual",3,4,31000,"1ZPSL65848Z418888",32000,30000,"An okay car","",false,false);

INSERT INTO vehicle(make,model,type,body,year,transmission,body_color,interior_color,mileage,vin_number,mrsp_price,
sale_price,description,image,featured,sold)
VALUES (3,3,1,3,"2022","Automatic",5,6,5000,"9JKSL65848Z418777",33000,29000,"Another good car","",false,false);

INSERT INTO vehicle(make,model,type,body,year,transmission,body_color,interior_color,mileage,vin_number,mrsp_price,
sale_price,description,image,featured,sold)
VALUES (4,5,1,2,"2022","Manual",3,1,0,"ZZOSL65848Z418999",38000,34000,"Another great car","",false,false);

INSERT INTO vehicle(make,model,type,body,year,transmission,body_color,interior_color,mileage,vin_number,mrsp_price,
sale_price,description,image,featured,sold)
VALUES (2,3,1,1,"2018","Manual",3,1,0,"ZZOSL65848Z44451",18000,16000,"Another great car","",false,false);

INSERT INTO vehicle(make,model,type,body,year,transmission,body_color,interior_color,mileage,vin_number,mrsp_price,
sale_price,description,image,featured,sold)
VALUES (3,1,2,2,"2014","Automatic",1,1,0,"ZZOSL65848Z44441",14000,11000,"Another great car","",false,false);


INSERT INTO vehicle(make,model,type,body,year,transmission,body_color,interior_color,mileage,vin_number,mrsp_price,
sale_price,description,image,featured,sold)
VALUES (2,1,2,3,"2008","Automatic",1,1,0,"ZZOSL65848Z44477",22000,19000,"Another great car","",false,false);

INSERT INTO vehicle(make,model,type,body,year,transmission,body_color,interior_color,mileage,vin_number,mrsp_price,
sale_price,description,image,featured,sold)
VALUES (4,4,1,3,"2019","Automatic",1,1,0,"ZZOSL65848Z44499",15000,10000,"Another great car","",false,false);

INSERT INTO vehicle(make,model,type,body,year,transmission,body_color,interior_color,mileage,vin_number,mrsp_price,
sale_price,description,image,featured,sold)
VALUES (1,3,1,2,"2021","Automatic",1,1,0,"ZZOSL65848Z44411",18000,17000,"Another great car","",false,false);


DROP TABLE IF EXISTS role;
CREATE TABLE role (
role_id int primary key auto_increment,
name varchar(10)
);
select * from role;
Update role set name = "Sales" where role_id = 1;
INSERT INTO role(name) VALUES("Sales");
INSERT INTO role(name) VALUES("Admin");
INSERT INTO role(name) VALUES("Disabled");

DROP TABLE IF EXISTS user;
CREATE TABLE user (
user_id int primary key auto_increment,
first_name varchar(50),
last_name varchar(50),
email varchar(50) NOT NULL,
role int NOT NULL,
password varchar(50) NOT NULL,
foreign key (role) references role(role_id)
);

select * from user;
INSERT INTO user(first_name,last_name,email,role,password) VALUES("Adam", "Smith","Adam@gmail.com",2,"Adam123");
INSERT INTO user(first_name,last_name,email,role,password) VALUES("Test", "TestLast","test@gmail.com","Admin","test123");
INSERT INTO user(first_name,last_name,email,role,password) VALUES("Jennifer", "Lopez","Jennifer@gmail.com",1,"Jennifer123");

DROP TABLE IF EXISTS make;
CREATE TABLE make (
make_id int primary key auto_increment,
name varchar(50),
date varchar(10),
user_email varchar(50)
);
select * from make;
INSERT INTO make(name,date,user_email) VALUES ("Toyota", "04/10/2021","Adam@gmail.com");
INSERT INTO make(name,date,user_email) VALUES ("BMW", "01/15/2021","Cena@gmail.com");
INSERT INTO make(name,date,user_email) VALUES ("Honda", "08/14/2021","Adam@gmail.com");
INSERT INTO make(name,date,user_email) VALUES ("Nissan", "06/22/2021","Cena@gmail.com");


DROP TABLE IF EXISTS model;
CREATE TABLE model (
model_id int primary key auto_increment,
make_id int,
name varchar(20),
date varchar(10),
user_email varchar(50),
foreign key (make_id) references make(make_id)
);
select * from model;
INSERT INTO model(make_id,name,date,user_email) VALUES (1,"Prius", "04/12/2021","Adam@gmail.com");
INSERT INTO model(make_id,name,date,user_email) VALUES (1,"Avalon", "06/18/2021","Adam@gmail.com");
INSERT INTO model(make_id,name,date,user_email) VALUES (1,"Supra", "07/10/2021","Cena@gmail.com");
INSERT INTO model(make_id,name,date,user_email) VALUES (2,"M3", "02/10/2021","Cena@gmail.com");
INSERT INTO model(make_id,name,date,user_email) VALUES (2,"M4", "03/15/2021","Cena@gmail.com");
INSERT INTO model(make_id,name,date,user_email) VALUES (3,"Accord", "08/16/2021","Adam@gmail.com");
INSERT INTO model(make_id,name,date,user_email) VALUES (3,"Civic", "09/01/2021","Cena@gmail.com");
INSERT INTO model(make_id,name,date,user_email) VALUES (4,"Altima", "09/22/2021","Cena@gmail.com");
INSERT INTO model(make_id,name,date,user_email) VALUES (4,"Maxima", "10/12/2021","Adam@gmail.com");

DROP TABLE IF EXISTS color;
CREATE TABLE color (
color_id int primary key auto_increment,
name varchar(15)
);
select * from color;
INSERT INTO color(name) VALUES ("Orange");
INSERT INTO color(name) VALUES ("Black");
INSERT INTO color(name) VALUES ("White");
INSERT INTO color(name) VALUES ("Grey");
INSERT INTO color(name) VALUES ("Blue");
INSERT INTO color(name) VALUES ("Red");
INSERT INTO color(name) VALUES ("Green");

DROP TABLE IF EXISTS body;
CREATE TABLE body (
body_id int primary key auto_increment,
name varchar(50)
);
select * from body;
INSERT INTO body(name) VALUES ("SUV");
INSERT INTO body(name) VALUES ("Sedan");
INSERT INTO body(name) VALUES ("Cope");



DROP TABLE IF EXISTS purchase;
CREATE TABLE purchase (
purchase_id int primary key auto_increment,
vehicle_id int,
name varchar(50),
email varchar(50),
street varchar(50),
city varchar(50),
state varchar(50),
zip varchar(5),
price int,
purchase_type varchar(20),
user_id int,
foreign key (vehicle_id) references vehicle (vehicle_id),
foreign key (user_id) references user(user_id)
);

alter table purchase drop foreign key purchase_type;

select * from vehicle;

INSERT INTO purchase(vehicle_id,name,email,street,city,state,zip,price,purchase_type,user_id)
 VALUES (5,"Morgan Freeman","Freeman@gmail.com","22 Best Street", "Best City","New York","11220",32000,1,1);
 
INSERT INTO purchase(vehicle_id,name,email,street,city,state,zip,price,purchase_type,user_id)
 VALUES (2,"Smith Johnson","Smith@gmail.com","66 Street", "Another City","New Jersey","99999",28000,2,2);
 
INSERT INTO purchase(vehicle_id,name,email,street,city,state,zip,price,purchase_type,user_id)
 VALUES (3,"Johnny Bravo","Bravo@gmail.com","90 Street", "New City","Texas","22222",25000,3,1);
 
INSERT INTO purchase(vehicle_id,name,email,street,city,state,zip,price,purchase_type,user_id)
 VALUES (4,"Khan Jack","Khan@gmail.com","115 Street", "Lost City","Florida","66666",18000,1,2);
 
 INSERT INTO purchase(vehicle_id,name,email,street,city,state,zip,price,purchase_type,user_id)
 VALUES (6,"Jack Sparrow","Sparrow@gmail.com","404 Street", "Found City","Arizona","77777",17500,2,1);
 
 INSERT INTO purchase(vehicle_id,name,email,street,city,state,zip,price,purchase_type,user_id)
 VALUES (2,"James Bond","Bond@gmail.com","Secret Street", "Suspecious City","New York","88888",35600,3,2);

update purchase set vehicle_id = 4 where purchase_id = 8;


DROP TABLE IF EXISTS sales;
CREATE TABLE sales (
sales_id int primary key auto_increment,
user_id int,
sale_amount double,
vechicle int,
foreign key (user_id) references user(user_id)
);

DROP TABLE IF EXISTS inventory;
CREATE TABLE inventory (
inventory_id int primary key auto_increment,
year varchar(10),
make_id int,
model_id int,
count int,
stock_value double,
type varchar(10),
foreign key (make_id) references make(make_id),
foreign key (model_id) references model(model_id)
);


DROP TABLE IF EXISTS specials;
CREATE TABLE specials (
special_id int primary key auto_increment,
title varchar(50),
description varchar(500)
);
select * from special;
INSERT INTO special(title,description) VALUES ("Deal One","Spring Car Sale - Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum. ");
INSERT INTO special(title,description) VALUES ("Deal Two","Winter Car Sale - Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum. ");
INSERT INTO special(title,description) VALUES ("Deal Three","Summer Car Sale - Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum. ");
INSERT INTO special(title,description) VALUES ("Deal Four","Autumn Car Sale - Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum. ");


DROP TABLE IF EXISTS message;
CREATE TABLE message(
message_id int primary key auto_increment,
name varchar(50),
email varchar(50),
phone varchar(15),
subject varchar(100),
message_body varchar(5000)

);


DROP TABLE IF EXISTS purchase_type;
CREATE TABLE purchase_type (
type_id int primary key auto_increment,
name varchar(50)
);

select * from purchase_type;
INSERT INTO purchase_type(name) VALUES ("Bank Finance");
INSERT INTO purchase_type(name) VALUES ("Cash");
INSERT INTO purchase_type(name) VALUES ("Dealer Finance");

