ALTER TABLE [dbo].[bugReportComment]  WITH CHECK ADD  CONSTRAINT [FK_bugReportComment] FOREIGN KEY([bugReportId])
REFERENCES [dbo].[bugReport] ([id])
ON UPDATE CASCADE
ON DELETE CASCADE
GO