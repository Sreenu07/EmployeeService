DBScripts:
==========
create database organisationdb;

use organisationdb;

create table department(ID int primary key auto_increment , NAME varchar(40) NOT NULL);

create table employee(ID int primary key auto_increment,UUID varchar(20) NOT NULL UNIQUE KEY,  EMAIL varchar(255) NOT NULL UNIQUE KEY,FIRST_NAME varchar(20) NOT NULL, LAST_NAME varchar(20) NOT NULL,
DATE_OF_BIRTH date NOT NULL, DEPARTMENT_ID int NOT NULL, CREATION_TIME datetime NOT NULL,foreign key (DEPARTMENT_ID) references department(ID));

Authentication credentials
==========================
1.username: user
2.password: password

RabbitMQ Configurations
=======================

1.exchange name:employee.events
2.Key routing name:event1
3.Queue name:queue1

Sample Request Body for REST endPoints
=======================================
1.POST http://localhost:8081/employees/departments
{ 
 "name": "DEVELOPMENT"
}

2.GET http://localhost:8081/employees/departments


3.POST http://localhost:8081/employees
{
	"firstName": "Talapa",
    "lastName": "srinivasulu",
    "email": "srinivasulu.talapa@gmail.com",
    "dateOfBirth": "1989-11-07",
	"department": {
        "id": 1    
    }
}

4.GET http://localhost:8081/employees/{uuid}

5.PUT http://localhost:8081/employees/{uuid}
{
"firstName": "Talapa",
"lastName": "srini",
"email": "srini.talapa@gmail.com"
}

6.DELETE http://localhost:8081/employees/{uuid}

BUILD and RUN
=============
1.Touch the pom.xml and it will build automatically.
2.run as Spring Boot App.

Swagger UI URLS
================
1.http://localhost:8081/swagger-ui.html
