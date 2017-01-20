CREATE TABLE [dbo].[UM_MRP_PLAN] ( 
    [TypeFlag]	int NOT NULL,
    [FItemID] 	int NOT NULL,
    [FName]   	varchar(80) NOT NULL,
    [FNumber] 	varchar(80) NULL,
    [FModel]  	varchar(255) NULL,
    [FQty]    	decimal(38,9) NULL 
    )
ON [PRIMARY]
GO

CREATE TABLE [dbo].[UM_MRP_PLAN_DATE] ( 
    [DateStr]	varchar(10) NULL,
    [FItemID]	int NOT NULL,
    [FQty]   	decimal(38,9) NULL,
    [PlanNum]	numeric(12,2) NULL 
    )
ON [PRIMARY]
GO

CREATE TABLE [dbo].[UM_MRP_PLAN_DATE_NEED] ( 
    [ErpNo]   	varchar(50) NOT NULL,
    [PlanDate]	varchar(10) NULL,
    [seq]     	bigint NULL,
    [FItemID] 	int NOT NULL,
    [FQty]    	decimal(38,9) NULL 
    )
ON [PRIMARY]
GO


CREATE TABLE [dbo].[UM_MRP_SCHEDULING] ( 
    [username]  	varchar(50) NOT NULL,
    [FNumber]   	varchar(80) NULL,
    [FName]     	varchar(80) NOT NULL,
    [FModel]    	varchar(255) NULL,
    [FQty]      	numeric(38,2) NULL,
    [CLQty]     	decimal(38,10) NULL,
    [DJQty]     	decimal(38,10) NULL,
    [TLFQty]    	decimal(38,10) NULL,
    [PlanNum]   	numeric(12,2) NOT NULL,
    [version]   	int NULL,
    [createDate]	datetime NULL,
    [ID]        	int IDENTITY(1,1) NOT FOR REPLICATION NOT NULL,
    CONSTRAINT [ID] PRIMARY KEY([ID])
	WITH (PAD_INDEX = ON)
    )
ON [PRIMARY]
GO

CREATE TABLE [dbo].[UM_MRP_SCHEDULING_DAYPLAN] ( 
    [username]	varchar(50) NOT NULL,
    [planDate]	varchar(10) NOT NULL,
    [PlanNum] 	numeric(12,2) NOT NULL,
    [FNumber] 	varchar(80) NOT NULL,
    [version] 	int NULL,
    [ID]      	int IDENTITY(1,1) NOT NULL,
    CONSTRAINT [plan_id] PRIMARY KEY([ID])
)
ON [PRIMARY]
GO


CREATE TABLE [dbo].[UM_MRP_SCHEDULING_DAYNEED] ( 
    [username]	varchar(50) NOT NULL,
    [planDate]	varchar(10) NOT NULL,
    [FQty]    	numeric(38,2) NULL,
    [FNumber] 	varchar(80) NOT NULL,
    [version] 	int NULL,
    [ID]      	int IDENTITY(1,1) NOT NULL,
    CONSTRAINT [need_id] PRIMARY KEY([ID])
)
ON [PRIMARY]
GO


CREATE TABLE [dbo].[UM_MRP_PLAN_PO] ( 
    [ID] 	int NOT NULL,
    [DateStr]  	varchar(50) NOT NULL,
    [FNumber]  	varchar(50) NOT NULL,
    [username] 	varchar(50) NULL,
    CONSTRAINT [um_mrp_plan_poId] PRIMARY KEY([ID])
)


CREATE TABLE [dbo].[UM_MRP_PLAN_PO_ORDER] ( 
    [ID] 	int IDENTITY(1,1) NOT NULL,
    [PID] 	int NOT NULL,
    [DateStr]  	varchar(50) NOT NULL,
    [FNumber]  	varchar(50) NOT NULL,
    [POOrderID]	int NOT NULL,
    [FSupplyID] int NOT NULL,
    [FSupplyName]   varchar(80) NULL,
    [username] 	varchar(50) NULL,
    CONSTRAINT [um_mrp_plan_poOrderId] PRIMARY KEY([ID])
)