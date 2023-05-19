/*show tables;*/

use healthforecast;

create table category(
id bigint auto_increment primary key,
begin_range decimal(8,3),
end_range decimal(8,3),
name varchar(255)
);

CREATE TABLE health_condition(
id bigint auto_increment primary key,
name varchar(20) not null,
description varchar(100) not null
);

create table forecast(
id bigint primary key auto_increment,
category_name varchar(255),
category_value int,
date datetime,
name varchar(255),
text varchar(255),
value decimal(19,2),
category_id bigint,
constraint category_foreign_key FOREIGN KEY(category_id) references category(id)
);


create table roles(
role_id bigint primary key auto_increment,
name varchar(255)
);

create table symptom(
id bigint primary key auto_increment,
description varchar(255),
name varchar(255),
healthcondition_id bigint,
constraint health_condition_foreign_key FOREIGN KEY(healthcondition_id) references health_condition(id)
);

create table user(
id bigint primary key auto_increment,
birth_date datetime,
email varchar(255),
enabled bit(1),
first_name varchar(255) NOT NULL,
last_name varchar(255) NOT NULL,
password varchar(255) NOT NULL
);

create table user_health_condition(
id bigint primary key auto_increment,
healthcondition_id bigint,
user_id bigint,
constraint user_health_condition_foreign_key FOREIGN KEY(healthcondition_id) references health_condition(id),
constraint user_foreign_key FOREIGN KEY(user_id) references user(id) 
);

create table user_health_condition_symptom(
id bigint primary key auto_increment,
healthcondition_id bigint,
user_id bigint,
constraint user_health_condition_symptom_foreign_key FOREIGN KEY(user_id) references user(id),
constraint health_condition_symptom_foreign_key FOREIGN KEY(healthcondition_id) references health_condition(id) 
);

/*create table users_roles(
user_id bigint,
role_id bigint,
primary key(user_id,role_id)
);*/

/*alter table users_roles add constraint users_roles_foreign_key foreign key(user_id) references user(id);
alter table users_roles add constraint users_roles_second_foreign_key foreign key(role_id) references roles(role_id);*/


create table users_roles(
user_id bigint,
role_id bigint,
primary key(user_id,role_id),
constraint users_roles_foreign_key FOREIGN KEY(user_id) references user(id),
constraint roles_foreign_key FOREIGN KEY(role_id) references roles(role_id)
);


create table verification_token(
id bigint primary key auto_increment,
expiry_date datetime,
token varchar(255),
user_id bigint,
constraint user_verification_token_foreign_key FOREIGN KEY(user_id) references user(id) 
);











