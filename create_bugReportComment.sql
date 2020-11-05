
create table bugReport (

	id INT PRIMARY KEY IDENTITY(1,1),
	title varchar (200),
	description varchar (500),
	status varchar(40),
	reportedBy varchar(50),
	assignee varchar(50)

)

create table bugReportComment (

	id INT PRIMARY KEY IDENTITY(1,1),
	comment varchar(200),
	bugReportId INT,

)

