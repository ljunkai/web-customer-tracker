
create table bugReportComment (

	id INT PRIMARY KEY IDENTITY(1,1),
	comment varchar(200),
	bugReportId INT,

	FOREIGN KEY (bugReportId) REFERENCES bugReport(id)
)