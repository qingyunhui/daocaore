package test03;

import com.tw.jdbc.SqlserverJdbc;

public class T02 {

	public static void main(String[] args) {
		SqlserverJdbc db = new SqlserverJdbc();
		try {
			db.Execute("delete from tmp_mrp_plan");
			
			String sql = " if exists (select * from tempdb.dbo.sysobjects where id = object_id(N'tempdb..#tmp_mrp_plan01') and type='U') drop table #tmp_mrp_plan01 ";
			db.Execute(sql);
			sql = " if exists (select * from tempdb.dbo.sysobjects where id = object_id(N'tempdb..#tmp_mrp_plan02') and type='U') drop table #tmp_mrp_plan02";
			db.Execute(sql);
			sql = " if exists (select * from tempdb.dbo.sysobjects where id = object_id(N'tempdb..#tmp_mrp_plan03') and type='U') drop table #tmp_mrp_plan03";
			db.Execute(sql);
			
			sql = "select a.TypeFlag,a.FItemID, b.FName, b.FNumber, b.FModel,sum(a.FQty) as FQty "
					+ "into #tmp_mrp_plan01 from( select b.ErpNo, a.FBOMNumber, a.FInterID, a.FItemID,"
					+ "b.TypeFlag, sum(a.FAuxQty*b.PlanNum) as FAuxQty, sum(a.FQty*b.PlanNum) as FQty "
					+ " from UM_MRP_BOMdetail a join (select ErpNoSplit as ErpNo, CustName, ProNo,"
					+ " OrderNum, CONVERT(varchar(10), OrderDate, 23) as OrderDate, "
					+ "CONVERT(varchar(10), PatchPlanDate, 23) as PlanDate, PatchPlanNum as PlanNum,"
					+ " 1 as TypeFlag, SchedulingSeq from V_UM_MRP_Scheduling "
					+ "where PatchPlanDate is not null and isnull(PatchPlanNum,0)<>0 ) b "
					+ "on a.FBOMNumber=b.ProNo group by b.ErpNo, a.FBOMNumber, a.FInterID, "
					+ "a.FItemID,b.TypeFlag )a join t_ICItem b on a.FItemID=b.FItemID "
					+ "where 1=1 and (b.FName like '%贴片%' or b.FName "
					+ "in('PCB-主板','PCB-WIFI小板','射频滤波器')) group by a.FItemID,b.FNumber,"
					+ "b.FName, b.FModel,a.TypeFlag order by a.FItemID ";
			db.Execute(sql);
			db.Execute("insert into tmp_mrp_plan select * from #tmp_mrp_plan01");
			
			sql = "select a.TypeFlag,a.FItemID, b.FName, b.FNumber, b.FModel, sum(a.FQty) as FQty "
					+ "into #tmp_mrp_plan02 from(select b.ErpNo, a.FBOMNumber, a.FInterID, a.FItemID,b.TypeFlag,"
					+ "sum(a.FAuxQty*b.PlanNum) as FAuxQty, sum(a.FQty*b.PlanNum) as FQty "
					+ "from UM_MRP_BOMdetail a join (select ErpNoSplit as ErpNo, CustName, ProNo, OrderNum,"
					+ "CONVERT(varchar(10), OrderDate, 23) as OrderDate,CONVERT(varchar(10), PluginPlanDate, 23) as PlanDate,"
					+ "PluginPlanNum as PlanNum, 2 as TypeFlag, SchedulingSeq from V_UM_MRP_Scheduling "
					+ "where PluginPlanDate is not null and isnull(PluginPlanNum,0)<>0) b on a.FBOMNumber=b.ProNo "
					+ "group by b.ErpNo, a.FBOMNumber, a.FInterID, a.FItemID,b.TypeFlag)a "
					+ "join t_ICItem b on a.FItemID=b.FItemID where 1=1 and b.Fname not like '%贴片%' and ( "
					+ "(b.Fnumber like 'A.C%' "
					+ "or b.Fnumber like 'A.R%' "
					+ "or b.Fnumber like 'A.L%' "
					+ "or b.Fnumber like 'A.D%' "
					+ "or b.Fnumber like 'A.Q%' "
					+ "or b.Fnumber like 'A.E%' "
					+ "or b.Fnumber like 'A.X%' "
					+ "or b.Fnumber like 'A.S%' ) or (b.Fnumber like 'A.P%' and b.FName not in('PCB-主板','PCB-WIFI小板')) "
					+ "or b.FName in('陶瓷滤波器','插件晶体','插件晶振','高频变压器','调协器','插件IC','普通排线')) "
					+ "group by a.FItemID,b.FNumber,b.FName, b.FModel,a.TypeFlag order by a.FItemID ";
			db.Execute(sql);
			db.Execute("insert into tmp_mrp_plan select * from #tmp_mrp_plan02");
			
			sql = "select a.TypeFlag,a.FItemID, b.FName, b.FNumber, b.FModel, sum(a.FQty) as FQty "
					+ "into #tmp_mrp_plan03 from( select b.ErpNo, a.FBOMNumber, a.FInterID, a.FItemID,b.TypeFlag,"
					+ "sum(a.FAuxQty*b.PlanNum) as FAuxQty, sum(a.FQty*b.PlanNum) as FQty "
					+ "from UM_MRP_BOMdetail a join (select ErpNoSplit as ErpNo, CustName, ProNo, OrderNum, "
					+ "CONVERT(varchar(10), OrderDate, 23) as OrderDate, CONVERT(varchar(10), PackPlanDate, 23) as PlanDate,"
					+ "PackPlanNum as PlanNum, 3 as TypeFlag, SchedulingSeq from V_UM_MRP_Scheduling "
					+ "where PackPlanDate is not null and isnull(PackPlanNum,0)<>0) b on a.FBOMNumber=b.ProNo "
					+ "group by b.ErpNo, a.FBOMNumber, a.FInterID, a.FItemID,b.TypeFlag )a "
					+ "join t_ICItem b on a.FItemID=b.FItemID where 1=1 and ( b.Fnumber like 'A.H%' and b.Fname<>'普通排线' "
					+ "or b.Fnumber like 'A.T%' and b.Fname<>'高频变压器' "
					+ "or b.Fnumber like 'A.B%' "
					+ "or b.Fnumber like 'A.F%' "
					+ "or b.Fnumber like 'A.K%' "
					+ "or b.Fnumber like 'A.M%' or b.Fname in('喇叭','蜂鸣器','电视机','功能模块','IC卡')) "
					+ "group by a.FItemID,b.FNumber,b.FName, b.FModel,a.TypeFlag order by a.FItemID ";
			db.Execute(sql);
			db.Execute("insert into tmp_mrp_plan select * from #tmp_mrp_plan03");
			
			db.Execute("delete from tmp_mrp_plan_date");
			
			sql = " if exists (select * from tempdb.dbo.sysobjects where id = object_id(N'tempdb..#tmp_mrp_plan_date01') and type='U') drop table #tmp_mrp_plan_date01";
			db.Execute(sql);
			sql = " if exists (select * from tempdb.dbo.sysobjects where id = object_id(N'tempdb..#tmp_mrp_plan_date02') and type='U') drop table #tmp_mrp_plan_date02";
			db.Execute(sql);
			sql = " if exists (select * from tempdb.dbo.sysobjects where id = object_id(N'tempdb..#tmp_mrp_plan_date03') and type='U') drop table #tmp_mrp_plan_date03";
			db.Execute(sql);
			
			sql = "select b.PlanDate as DateStr, a.FItemID, sum(FQty) as FQty,0 as PlanNum "
					+ "into #tmp_mrp_plan_date01 from (select TOP 100 PERCENT a.ErpNo, a.FInterID, a.FBOMNumber, a.FItemID, b.FName, b.FNumber, b.FModel, "
					+ "a.FAuxQty, a.FQty,a.TypeFlag from( select b.ErpNo, a.FBOMNumber, a.FInterID, a.FItemID,b.TypeFlag, "
					+ "sum(a.FAuxQty*b.PlanNum) as FAuxQty, sum(a.FQty*b.PlanNum) as FQty from UM_MRP_BOMdetail a "
					+ "join (select ErpNoSplit as ErpNo, CustName, ProNo, OrderNum, CONVERT(varchar(10), OrderDate, 23) as OrderDate,"
					+ "CONVERT(varchar(10), PatchPlanDate, 23) as PlanDate, PatchPlanNum as PlanNum, 1 as TypeFlag, SchedulingSeq "
					+ "from V_UM_MRP_Scheduling where PatchPlanDate is not null and isnull(PatchPlanNum,0)<>0 ) b "
					+ "on a.FBOMNumber=b.ProNo group by b.ErpNo, a.FBOMNumber, a.FInterID, a.FItemID,b.TypeFlag )a "
					+ "join t_ICItem b on a.FItemID=b.FItemID where 1=1 and "
					+ "(b.FName like '%贴片%' or b.FName in('PCB-主板','PCB-WIFI小板','射频滤波器')) order by a.FItemID ) a "
					+ "join (select ErpNoSplit as ErpNo, CustName, ProNo, OrderNum, CONVERT(varchar(10), OrderDate, 23) as OrderDate, "
					+ "CONVERT(varchar(10), PatchPlanDate, 23) as PlanDate, PatchPlanNum as PlanNum, 1 as TypeFlag, SchedulingSeq "
					+ "from V_UM_MRP_Scheduling where PatchPlanDate is not null and isnull(PatchPlanNum,0)<>0 ) b on a.ErpNo=b.ErpNo "
					+ "group by b.PlanDate, a.FItemID";
			db.Execute(sql);
			db.Execute("insert into tmp_mrp_plan_date select * from #tmp_mrp_plan_date01");
			
			sql = "select b.PlanDate as DateStr, a.FItemID, sum(FQty) as FQty,0 as PlanNum "
					+ "into #tmp_mrp_plan_date02 from (select TOP 100 PERCENT a.ErpNo, a.FInterID, a.FBOMNumber, a.FItemID, b.FName, b.FNumber, b.FModel, "
					+ "a.FAuxQty, a.FQty,a.TypeFlag from(  select b.ErpNo, a.FBOMNumber, a.FInterID, a.FItemID,b.TypeFlag, "
					+ "sum(a.FAuxQty*b.PlanNum) as FAuxQty, sum(a.FQty*b.PlanNum) as FQty from UM_MRP_BOMdetail a "
					+ "join (select ErpNoSplit as ErpNo, CustName, ProNo, OrderNum, CONVERT(varchar(10), OrderDate, 23) as OrderDate, "
					+ "CONVERT(varchar(10), PluginPlanDate, 23) as PlanDate, PluginPlanNum as PlanNum, 2 as TypeFlag, SchedulingSeq "
					+ "from V_UM_MRP_Scheduling where PluginPlanDate is not null and isnull(PluginPlanNum,0)<>0 ) b "
					+ "on a.FBOMNumber=b.ProNo group by b.ErpNo, a.FBOMNumber, a.FInterID, a.FItemID,b.TypeFlag )a "
					+ "join t_ICItem b on a.FItemID=b.FItemID where 1=1 and b.Fname not like '%贴片%' and ( "
					+ "(b.Fnumber like 'A.C%' "
					+ "or b.Fnumber like 'A.R%' "
					+ "or b.Fnumber like 'A.L%' "
					+ "or b.Fnumber like 'A.D%' "
					+ "or b.Fnumber like 'A.Q%' "
					+ "or b.Fnumber like 'A.E%' "
					+ "or b.Fnumber like 'A.X%' "
					+ "or b.Fnumber like 'A.S%' ) "
					+ "or (b.Fnumber like 'A.P%' and b.FName not in('PCB-主板','PCB-WIFI小板')) "
					+ "or b.FName in('陶瓷滤波器','插件晶体','插件晶振','高频变压器','调协器','插件IC','普通排线')) order by a.FItemID ) a "
					+ "join (select ErpNoSplit as ErpNo, CustName, ProNo, OrderNum, CONVERT(varchar(10), OrderDate, 23) as OrderDate, "
					+ "CONVERT(varchar(10), PluginPlanDate, 23) as PlanDate, PluginPlanNum as PlanNum, 2 as TypeFlag, SchedulingSeq "
					+ "from V_UM_MRP_Scheduling where PluginPlanDate is not null and isnull(PluginPlanNum,0)<>0 ) b on a.ErpNo=b.ErpNo "
					+ "group by b.PlanDate, a.FItemID";
			db.Execute(sql);
			db.Execute("insert into tmp_mrp_plan_date select * from #tmp_mrp_plan_date02");
			
			sql = "select b.PlanDate as DateStr, a.FItemID, sum(FQty) as FQty,0 as PlanNum "
					+ "into #tmp_mrp_plan_date03 from (select TOP 100 PERCENT a.ErpNo, a.FInterID, a.FBOMNumber, a.FItemID, b.FName, b.FNumber, b.FModel, "
					+ "a.FAuxQty, a.FQty,a.TypeFlag from(select b.ErpNo, a.FBOMNumber, a.FInterID, a.FItemID,b.TypeFlag,"
					+ "sum(a.FAuxQty*b.PlanNum) as FAuxQty, sum(a.FQty*b.PlanNum) as FQty from UM_MRP_BOMdetail a "
					+ "join (select ErpNoSplit as ErpNo, CustName, ProNo, OrderNum, CONVERT(varchar(10), OrderDate, 23) as OrderDate,"
					+ "CONVERT(varchar(10), PackPlanDate, 23) as PlanDate,PackPlanNum as PlanNum, 3 as TypeFlag, SchedulingSeq "
					+ "from V_UM_MRP_Scheduling where PackPlanDate is not null and isnull(PackPlanNum,0)<>0 ) b on a.FBOMNumber=b.ProNo "
					+ "group by b.ErpNo, a.FBOMNumber, a.FInterID, a.FItemID,b.TypeFlag )a join t_ICItem b on a.FItemID=b.FItemID "
					+ "where 1=1 and (b.Fnumber like 'A.H%' and b.Fname<>'普通排线' or b.Fnumber like 'A.T%' and b.Fname<>'高频变压器' "
					+ "or b.Fnumber like 'A.B%' or b.Fnumber like 'A.F%' or b.Fnumber like 'A.K%' or b.Fnumber like 'A.M%' "
					+ "or b.Fname in('喇叭','蜂鸣器','电视机','功能模块','IC卡')) order by a.FItemID ) a "
					+ "join (select ErpNoSplit as ErpNo, CustName, ProNo, OrderNum, CONVERT(varchar(10), OrderDate, 23) as OrderDate, "
					+ "CONVERT(varchar(10), PackPlanDate, 23) as PlanDate, PackPlanNum as PlanNum, 3 as TypeFlag, SchedulingSeq "
					+ "from V_UM_MRP_Scheduling where PackPlanDate is not null and isnull(PackPlanNum,0)<>0 ) b on a.ErpNo=b.ErpNo "
					+ "group by b.PlanDate, a.FItemID";
			db.Execute(sql);
			db.Execute("insert into tmp_mrp_plan_date select * from #tmp_mrp_plan_date03");
			
			
			db.Execute("delete from tmp_mrp_plan_date_need");
			
			sql = " if exists (select * from tempdb.dbo.sysobjects where id = object_id(N'tempdb..#tmp_mrp_plan_date_need01') and type='U') drop table #tmp_mrp_plan_date_need01";
			db.Execute(sql);
			sql = " if exists (select * from tempdb.dbo.sysobjects where id = object_id(N'tempdb..#tmp_mrp_plan_date_need02') and type='U') drop table #tmp_mrp_plan_date_need02";
			db.Execute(sql);
			sql = " if exists (select * from tempdb.dbo.sysobjects where id = object_id(N'tempdb..#tmp_mrp_plan_date_need03') and type='U') drop table #tmp_mrp_plan_date_need03";
			db.Execute(sql);
			
			sql = "select a.*,isnull(b.FQty,0) as FQty into #tmp_mrp_plan_date_need01 from (select a.ErpNo, a.PlanDate, a.seq, b.FItemID "
					+ "from( "
					+ "select distinct ErpNo, PlanDate, ROW_NUMBER() OVER(ORDER BY PlanDate, SchedulingSeq) as seq "
					+ "from (select ErpNoSplit as ErpNo, CustName, ProNo, OrderNum, "
					+ "CONVERT(varchar(10), OrderDate, 23) as OrderDate, "
					+ "CONVERT(varchar(10), PatchPlanDate, 23) as PlanDate,"
					+ "PatchPlanNum as PlanNum, 1 as TypeFlag, SchedulingSeq"
					+ " from V_UM_MRP_Scheduling "
					+ "where PatchPlanDate is not null and isnull(PatchPlanNum,0)<>0) a"
					+ ")a "
					+ "join( "
					+ "       select distinct FItemID from (select TOP 100 PERCENT a.ErpNo, a.FInterID, "
					+ "a.FBOMNumber, a.FItemID, b.FName, b.FNumber, b.FModel, a.FAuxQty, a.FQty,a.TypeFlag "
					+ "from( "
					+ " select b.ErpNo, a.FBOMNumber, a.FInterID, a.FItemID,b.TypeFlag, "
					+ " sum(a.FAuxQty*b.PlanNum) as FAuxQty, sum(a.FQty*b.PlanNum) as FQty "
					+ " from UM_MRP_BOMdetail a "
					+ " join (select ErpNoSplit as ErpNo, CustName, ProNo, OrderNum, "
					+ "CONVERT(varchar(10), OrderDate, 23) as OrderDate, "
					+ "CONVERT(varchar(10), PatchPlanDate, 23) as PlanDate, "
					+ "PatchPlanNum as PlanNum, 1 as TypeFlag, SchedulingSeq "
					+ "from V_UM_MRP_Scheduling "
					+ "where PatchPlanDate is not null and isnull(PatchPlanNum,0)<>0) b on a.FBOMNumber=b.ProNo "
					+ " group by b.ErpNo, a.FBOMNumber, a.FInterID, a.FItemID,b.TypeFlag "
					+ ")a "
					+ "join t_ICItem b on a.FItemID=b.FItemID "
					+ "where 1=1 and (b.FName like '%贴片%' or b.FName in('PCB-主板','PCB-WIFI小板','射频滤波器')) "
					+ " order by a.ErpNo, a.FItemID ) a "
					+ "     )b on 1=1 ) a "
					+ "left join (select TOP 100 PERCENT a.ErpNo, a.FInterID, a.FBOMNumber, a.FItemID, b.FName, "
					+ "b.FNumber, b.FModel, a.FAuxQty, a.FQty,a.TypeFlag "
					+ "from( "
					+ "  select b.ErpNo, a.FBOMNumber, a.FInterID, a.FItemID,b.TypeFlag, "
					+ " sum(a.FAuxQty*b.PlanNum) as FAuxQty, sum(a.FQty*b.PlanNum) as FQty "
					+ " from UM_MRP_BOMdetail a "
					+ " join (select ErpNoSplit as ErpNo, CustName, ProNo, OrderNum, "
					+ "CONVERT(varchar(10), OrderDate, 23) as OrderDate, "
					+ " CONVERT(varchar(10), PatchPlanDate, 23) as PlanDate, "
					+ "PatchPlanNum as PlanNum, 1 as TypeFlag, SchedulingSeq "
					+ "from V_UM_MRP_Scheduling "
					+ "where PatchPlanDate is not null and isnull(PatchPlanNum,0)<>0) b on a.FBOMNumber=b.ProNo "
					+ " group by b.ErpNo, a.FBOMNumber, a.FInterID, a.FItemID,b.TypeFlag "
					+ ")a "
					+ "join t_ICItem b on a.FItemID=b.FItemID "
					+ "where 1=1 and (b.FName like '%贴片%' or b.FName in('PCB-主板','PCB-WIFI小板','射频滤波器')) "
					+ "order by a.ErpNo, a.FItemID ) b on a.ErpNo=b.ErpNo and a.FItemID=b.FItemID";
			db.Execute(sql);
			db.Execute("insert into tmp_mrp_plan_date_need select * from  #tmp_mrp_plan_date_need01");
			
			sql = "select a.*,isnull(b.FQty,0) as FQty into #tmp_mrp_plan_date_need02 from (select a.ErpNo, a.PlanDate, a.seq, b.FItemID "
					+ "        from( "
					+ "               select distinct ErpNo, PlanDate, ROW_NUMBER() OVER(ORDER BY PlanDate, SchedulingSeq) as seq "
					+ "              from (select ErpNoSplit as ErpNo, CustName, ProNo, OrderNum, "
					+ "      CONVERT(varchar(10), OrderDate, 23) as OrderDate, "
					+ "      CONVERT(varchar(10), PluginPlanDate, 23) as PlanDate, "
					+ "   PluginPlanNum as PlanNum, 2 as TypeFlag, SchedulingSeq "
					+ "  from V_UM_MRP_Scheduling "
					+ "  where PluginPlanDate is not null and isnull(PluginPlanNum,0)<>0 ) a"
					+ "  )a "
					+ " join( "
					+ "       select distinct FItemID from (select TOP 100 PERCENT a.ErpNo, a.FInterID, a.FBOMNumber,"
					+ " a.FItemID, b.FName, b.FNumber, b.FModel, a.FAuxQty, a.FQty,a.TypeFlag "
					+ " from( "
					+ "       select b.ErpNo, a.FBOMNumber, a.FInterID, a.FItemID,b.TypeFlag, "
					+ "     sum(a.FAuxQty*b.PlanNum) as FAuxQty, sum(a.FQty*b.PlanNum) as FQty "
					+ "   from UM_MRP_BOMdetail a "
					+ "   join (select ErpNoSplit as ErpNo, CustName, ProNo, OrderNum, "
					+ "  CONVERT(varchar(10), OrderDate, 23) as OrderDate, "
					+ "  CONVERT(varchar(10), PluginPlanDate, 23) as PlanDate, "
					+ " PluginPlanNum as PlanNum, 2 as TypeFlag, SchedulingSeq "
					+ " from V_UM_MRP_Scheduling "
					+ " where PluginPlanDate is not null and isnull(PluginPlanNum,0)<>0 ) b on a.FBOMNumber=b.ProNo "
					+ "  group by b.ErpNo, a.FBOMNumber, a.FInterID, a.FItemID,b.TypeFlag "
					+ " )a "
					+ " join t_ICItem b on a.FItemID=b.FItemID "
					+ " where 1=1 and b.Fname not like '%贴片%' and ( "
					+ "       (b.Fnumber like 'A.C%' "
					+ "     or b.Fnumber like 'A.R%' "
					+ "   or b.Fnumber like 'A.L%' "
					+ " or b.Fnumber like 'A.D%' "
					+ "  or b.Fnumber like 'A.Q%' "
					+ " or b.Fnumber like 'A.E%'  "
					+ "  or b.Fnumber like 'A.X%' "
					+ "  or b.Fnumber like 'A.S%' "
					+ ") "
					+ "or (b.Fnumber like 'A.P%' and b.FName not in('PCB-主板','PCB-WIFI小板')) "
					+ "or b.FName in('陶瓷滤波器','插件晶体','插件晶振','高频变压器','调协器','插件IC','普通排线')) "
					+ "order by a.ErpNo, a.FItemID ) a "
					+ "      )b on 1=1 ) a "
					+ "left join (select TOP 100 PERCENT a.ErpNo, a.FInterID, a.FBOMNumber, a.FItemID, b.FName, b.FNumber, b.FModel, "
					+ "a.FAuxQty, a.FQty,a.TypeFlag "
					+ "from( "
					+ "      select b.ErpNo, a.FBOMNumber, a.FInterID, a.FItemID,b.TypeFlag, "
					+ "    sum(a.FAuxQty*b.PlanNum) as FAuxQty, sum(a.FQty*b.PlanNum) as FQty "
					+ "   from UM_MRP_BOMdetail a "
					+ "  join (select ErpNoSplit as ErpNo, CustName, ProNo, OrderNum, "
					+ "  CONVERT(varchar(10), OrderDate, 23) as OrderDate, "
					+ "  CONVERT(varchar(10), PluginPlanDate, 23) as PlanDate, "
					+ "  PluginPlanNum as PlanNum, 2 as TypeFlag, SchedulingSeq "
					+ "  from V_UM_MRP_Scheduling "
					+ "  where PluginPlanDate is not null and isnull(PluginPlanNum,0)<>0 ) b on a.FBOMNumber=b.ProNo "
					+ "   group by b.ErpNo, a.FBOMNumber, a.FInterID, a.FItemID,b.TypeFlag "
					+ ")a "
					+ "join t_ICItem b on a.FItemID=b.FItemID "
					+ "where 1=1 and b.Fname not like '%贴片%' and ( "
					+ "      (b.Fnumber like 'A.C%' "
					+ "    or b.Fnumber like 'A.R%' "
					+ "     or b.Fnumber like 'A.L%' "
					+ "     or b.Fnumber like 'A.D%' "
					+ "     or b.Fnumber like 'A.Q%' "
					+ "     or b.Fnumber like 'A.E%'  "
					+ "     or b.Fnumber like 'A.X%' "
					+ "    or b.Fnumber like 'A.S%' "
					+ ") "
					+ "or (b.Fnumber like 'A.P%' and b.FName not in('PCB-主板','PCB-WIFI小板')) "
					+ "or b.FName in('陶瓷滤波器','插件晶体','插件晶振','高频变压器','调协器','插件IC','普通排线')) "
					+ "order by a.ErpNo, a.FItemID ) b on a.ErpNo=b.ErpNo and a.FItemID=b.FItemID";
			db.Execute(sql);
			db.Execute("insert into tmp_mrp_plan_date_need select * from  #tmp_mrp_plan_date_need02");
			
			sql = "select a.*,isnull(b.FQty,0) as FQty into #tmp_mrp_plan_date_need03 from (select a.ErpNo, a.PlanDate, a.seq, b.FItemID "
					+ "   from( "
					+ "          select distinct ErpNo, PlanDate, ROW_NUMBER() OVER(ORDER BY PlanDate, SchedulingSeq) as seq "
					+ "       from (select ErpNoSplit as ErpNo, CustName, ProNo, OrderNum, "
					+ "     CONVERT(varchar(10), OrderDate, 23) as OrderDate, "
					+ "     CONVERT(varchar(10), PackPlanDate, 23) as PlanDate, "
					+ "    PackPlanNum as PlanNum, 3 as TypeFlag, SchedulingSeq "
					+ "   from V_UM_MRP_Scheduling "
					+ "   where PackPlanDate is not null and isnull(PackPlanNum,0)<>0 ) a "
					+ "  )a "
					+ "  join( "
					+ "        select distinct FItemID from (select TOP 100 PERCENT a.ErpNo, a.FInterID, a.FBOMNumber, "
					+ "a.FItemID, b.FName, b.FNumber, b.FModel, a.FAuxQty, a.FQty,a.TypeFlag "
					+ " from( "
					+ "      select b.ErpNo, a.FBOMNumber, a.FInterID, a.FItemID,b.TypeFlag, "
					+ "   sum(a.FAuxQty*b.PlanNum) as FAuxQty, sum(a.FQty*b.PlanNum) as FQty "
					+ "  from UM_MRP_BOMdetail a "
					+ "  join (select ErpNoSplit as ErpNo, CustName, ProNo, OrderNum, "
					+ " CONVERT(varchar(10), OrderDate, 23) as OrderDate, "
					+ "   CONVERT(varchar(10), PackPlanDate, 23) as PlanDate,"
					+ "  PackPlanNum as PlanNum, 3 as TypeFlag, SchedulingSeq "
					+ "  from V_UM_MRP_Scheduling "
					+ "  where PackPlanDate is not null and isnull(PackPlanNum,0)<>0 ) b on a.FBOMNumber=b.ProNo "
					+ "   group by b.ErpNo, a.FBOMNumber, a.FInterID, a.FItemID,b.TypeFlag "
					+ " )a "
					+ " join t_ICItem b on a.FItemID=b.FItemID "
					+ " where 1=1 and ( "
					+ " b.Fnumber like 'A.H%' and b.Fname<>'普通排线' "
					+ " or b.Fnumber like 'A.T%' and b.Fname<>'高频变压器' "
					+ " or b.Fnumber like 'A.B%' "
					+ " or b.Fnumber like 'A.F%' "
					+ " or b.Fnumber like 'A.K%' "
					+ " or b.Fnumber like 'A.M%' "
					+ " or b.Fname in('喇叭','蜂鸣器','电视机','功能模块','IC卡')) "
					+ " order by a.ErpNo, a.FItemID ) a "
					+ "       )b on 1=1 ) a "
					+ "left join (select TOP 100 PERCENT a.ErpNo, a.FInterID, a.FBOMNumber, a.FItemID, b.FName, b.FNumber,"
					+ " b.FModel, a.FAuxQty, a.FQty,a.TypeFlag "
					+ " from( "
					+ "         select b.ErpNo, a.FBOMNumber, a.FInterID, a.FItemID,b.TypeFlag, "
					+ "       sum(a.FAuxQty*b.PlanNum) as FAuxQty, sum(a.FQty*b.PlanNum) as FQty "
					+ "     from UM_MRP_BOMdetail a "
					+ "    join (select ErpNoSplit as ErpNo, CustName, ProNo, OrderNum, "
					+ "  CONVERT(varchar(10), OrderDate, 23) as OrderDate, "
					+ "  CONVERT(varchar(10), PackPlanDate, 23) as PlanDate, "
					+ "  PackPlanNum as PlanNum, 3 as TypeFlag, SchedulingSeq "
					+ "  from V_UM_MRP_Scheduling "
					+ "  where PackPlanDate is not null and isnull(PackPlanNum,0)<>0 ) b on a.FBOMNumber=b.ProNo "
					+ "  group by b.ErpNo, a.FBOMNumber, a.FInterID, a.FItemID,b.TypeFlag "
					+ " )a "
					+ " join t_ICItem b on a.FItemID=b.FItemID "
					+ " where 1=1 and ( "
					+ " b.Fnumber like 'A.H%' and b.Fname<>'普通排线' "
					+ " or b.Fnumber like 'A.T%' and b.Fname<>'高频变压器' "
					+ " or b.Fnumber like 'A.B%' "
					+ " or b.Fnumber like 'A.F%' "
					+ " or b.Fnumber like 'A.K%' "
					+ " or b.Fnumber like 'A.M%' "
					+ " or b.Fname in('喇叭','蜂鸣器','电视机','功能模块','IC卡')) "
					+ " order by a.ErpNo, a.FItemID ) b on a.ErpNo=b.ErpNo and a.FItemID=b.FItemID";
			db.Execute(sql);
			db.Execute("insert into tmp_mrp_plan_date_need select * from  #tmp_mrp_plan_date_need03");
		} catch (Exception e) {
//			db.Close();
			e.printStackTrace();
		}
//		db.Close();
		System.out.println(111111);
	}

}
