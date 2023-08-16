CREATE TABLE Employee_Table (
    Employee_ID int not null,
    Department_ID int not null,
    Project_ID int not null,
    Employee_Name varchar(255),
    Employee_Email varchar(255),
    PRIMARY KEY(Employee_ID)
);
insert into employee_table
values
(1,1,1,'Gayatri','gp'),
(2,1,1,'bena','bp'),
(3,1,2,'rina','rp'),
(4,2,2,'kajal','kp'),
(5,3,3,'vandu','vp');