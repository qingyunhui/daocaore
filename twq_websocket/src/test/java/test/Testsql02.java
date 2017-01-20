package test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;

import com.tw.jdbc.SqlserverJdbc;

public class Testsql02 {

	public static void main(String[] args) throws Exception{
		SqlserverJdbc db = temPlan("1");

		/*StringBuilder sb = new StringBuilder();
		sb.append("[").append("{field : 'FItemID',title : '序号',width : 100,rowspan:2}").append(",")
		.append("{field : 'FNumber',title : '物料编号',width : 100,rowspan:2}").append(",")
		.append("{field : 'FName',title : '物料名称',width : 100,rowspan:2}").append(",")
		.append("{field : 'FModel',title : '物料规格',width : 100,rowspan:2}").append(",")
		.append("{field : 'CLQty',title : '材料仓库存',width : 100,rowspan:2}").append(",")
		.append("{field : 'DJQty',title : '待检仓库存',width : 100,rowspan:2}").append(",")
		.append("{field : 'TLFQty',title : '在制需求量',width : 100,rowspan:2}").append(",")
		.append("{field : '',title : '总计',colspan:3}").append(",");
		
		
		ResultSet rs = db.ExecuteQuery("select distinct PlanDate from #TmpScheduling order by PlanDate");
		try {
			while (rs.next()) {
				sb.append("{field : '',title : '"+rs.getString(1)+"',colspan:4}").append(",");
				ResultSet rs01 = db.ExecuteQuery("select ErpNo from #TmpScheduling where PlanDate='"+rs.getString(1)+"' order by schedulingSeq");
				while (rs01.next()) {
					sb.append("{field : '',title : '"+rs01.getString(1)+"',colspan:2}").append(",");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		sb.deleteCharAt(sb.length()-1);
		sb.append("],[").append("{field : 'PlanNum',title : '总计划入库数',width : 100}").append(",")
		.append("{field : 'TXqty',title : '总需求数量',width : 100}").append(",")
		.append("{field : 'TSqty',title : '总剩余数量',width : 100}").append(",");
		
		ResultSet rs01 = db.ExecuteQuery("select distinct PlanDate from #TmpScheduling order by PlanDate");
		try {
			while (rs01.next()) {
				sb.append("{field : 'D-").append(rs01.getString(1)).append("-1',title : '计划入库数',width : 100}").append(",")
				.append("{field : 'D-").append(rs01.getString(1)).append("-2',title : '可用数量',width : 100}").append(",")
				.append("{field : 'D-").append(rs01.getString(1)).append("-3',title : '需求数量',width : 100}").append(",")
				.append("{field : 'D-").append(rs01.getString(1)).append("-4',title : '剩余数量',width : 100}").append(",");
				ResultSet rs02 = db.ExecuteQuery("select ErpNo from #TmpScheduling where PlanDate='"+rs01.getString(1)+"' order by schedulingSeq");
				while (rs02.next()) {
					sb.append("{field : 'E-").append(rs02.getString(1)).append("-1',title : '需求数量',width : 100}").append(",")
					.append("{field : 'E-").append(rs02.getString(1)).append("-2',title : '剩余数量',width : 100}").append(",");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		sb.deleteCharAt(sb.length()-1).append("]");
		System.out.println(sb.toString());*/
		
//		db.Close();
		
		
		mtlist(db);
		
		db.Close();
		System.out.println(111111);
	}

	private static void mtlist(SqlserverJdbc db2) {
		String sql = "";
        try{
            sql = " select a.FItemID, a.FNumber,a.TypeFlag, a.FName, a.FModel, cast(a.FQty as numeric(10,2)) as FQty, "
                + " cast(isnull(b.CLQty,0) as numeric(10,2)) as CLQty, cast(isnull(b.DJQty,0) as numeric(10,2)) as DJQty, "
                + " cast(isnull(c.FNeedQty,0) as numeric(10,2)) as TLFQty, cast(isnull(d.PlanNum,0) as numeric(10,2)) as PlanNum  "
                + " into #TmpPlanMrp from( "
                + "         select FItemID, FNumber, FName, FModel,TypeFlag, sum(FQty) as FQty "
                + "         from #TmpErpMtInfo "
                + "         group by FItemID, FNumber, FName, FModel,TypeFlag "
                + " ) a "
                + " left join #TmpStockInfo b on a.FItemID=b.FItemID "
                + " left join V_UM_MRP_PPBomEntry c on a.FItemID=c.FItemID "
                + " left join ( "
                + "     select FItemID, FNumber, sum(a.PlanNum) as PlanNum "
                + "     from #TmpDatePlanInPut a "
                + "     join ( "
                + "         select distinct PlanDate from #TmpScheduling "
                + "     ) b on a.DateStr=b.PlanDate "
                + "     group by FItemID, FNumber "
                + " ) d on a.FItemID=d.FitemID "
                + " order by a.FNumber ";
            db2.Execute(sql);
            
            db2.Execute("select * into tmp_PlanMrp  from  #TmpPlanMrp");
//            db2.Execute("insert into tmp_PlanMrp select * from #TmpPlanMrp");
            
            
//            sql = " select b.FItemID, c.FNumber, b.des, b.Data_1, b.Data_2, a.DateSeq, a.ErpSeq, b.Data_seq "
//                    + " into #TmpPlanMrpData from( "
//                    + "         select b.ErpNo as des, a.seq as DateSeq, b.seq as ErpSeq "
//                    + "         from #tmpDateSeq a "
//                    + "         join #tmpErpSeq b on a.DateStr=b.DateStr "
//                    + "         union all "
//                    + "         select DateStr as des, seq as DateSeq, 0 as ErpSeq "
//                    + "         from #tmpDateSeq "
//                    + " ) a  "
//                    + " join( "
//                    + "         select DateStr as des, FItemID, '\"D-'+DateStr+'-1\":\"'+cast(PlanNum as varchar(20))+'\"' as Data_1,'\"D-'+DateStr+'-2\":\"'+cast(KFQty as varchar(20))+'\"' as Data_2, 1 as Data_seq from #tmpDateMt_2 "
//                    + "         union all "
//                    + "         select DateStr as des, FItemID, '\"D-'+DateStr+'-3\":\"'+cast(FQty as varchar(20))+'\"' as Data_1,'\"D-'+DateStr+'-4\":\"'+cast(SFQty as varchar(20))+'\"' as Data_2, 1 as Data_seq from #tmpDateMt_2 "
//                    + "         union all "
//                    + "         select ErpNo as des, FItemID, '\"E-'+ErpNo+'-1\":\"'+cast(FQty as varchar(20))+'\"' as Data_1, '\"E-'+ErpNo+'-2\":\"'+cast(SFQty as varchar(20))+'\"' as Data_2, 1 as Data_seq from #tmpErpMt_2 "
//                    + " ) b on a.des=b.des "
//                    + " join( "
//                    + "         select distinct FItemID, FNumber from #TmpErpMtInfo "
//                    + " ) c on b.FItemID=c.FItemID "
//                    + " order by c.FNumber, b.FItemID, a.DateSeq, a.ErpSeq, b.Data_seq ";
//            
//            db2.Execute(sql);
//            
//            db2.Execute("select * into tmp_PlanMrpData  from  #TmpPlanMrpData");
            
        }catch(Exception e){
            e.printStackTrace();
        }
	}

	private static SqlserverJdbc temPlan(String typeFlag) throws Exception{
		String condition = "";
		if ("1".equals(typeFlag)) {
			condition = " and (b.FName like '%贴片%' or b.FName in('PCB-主板','PCB-WIFI小板','射频滤波器')) ";
		} else if ("2".equals(typeFlag)) {
			condition = " and b.Fname not like '%贴片%' and ( "
					+ "         (b.Fnumber like 'A.C%' "
					+ "         or b.Fnumber like 'A.R%' "
					+ "         or b.Fnumber like 'A.L%' "
					+ "         or b.Fnumber like 'A.D%' "
					+ "         or b.Fnumber like 'A.Q%' "
					+ "         or b.Fnumber like 'A.E%' "
					+ "         or b.Fnumber like 'A.X%' "
					+ "         or b.Fnumber like 'A.S%' "
					+ " ) "
					+ " or (b.Fnumber like 'A.P%' and b.FName not in('PCB-主板','PCB-WIFI小板')) "
					+ " or b.FName in('陶瓷滤波器','插件晶体','插件晶振','高频变压器','调协器','插件IC','普通排线') "
					+ ") ";
		} else if ("3".equals(typeFlag)) {
			condition = " and ( "
					+ " b.Fnumber like 'A.H%' and b.Fname<>'普通排线' "
					+ " or b.Fnumber like 'A.T%' and b.Fname<>'高频变压器' "
					+ " or b.Fnumber like 'A.B%' "
					+ " or b.Fnumber like 'A.F%' "
					+ " or b.Fnumber like 'A.K%' "
					+ " or b.Fnumber like 'A.M%' "
					+ " or b.Fname in('喇叭','蜂鸣器','电视机','功能模块','IC卡') " + ")  ";
		}

		SqlserverJdbc db = new SqlserverJdbc();
		String sql = "";
		
		
		sql = " if exists (select * from tempdb.dbo.sysobjects where id = object_id(N'tempdb..#TmpScheduling') and type='U') drop table #TmpScheduling ";
        db.Execute(sql);
        sql = " if exists (select * from tempdb.dbo.sysobjects where id = object_id(N'tempdb..#TmpErpMtInfo') and type='U') drop table #TmpErpMtInfo ";
        db.Execute(sql);
        sql = " if exists (select * from tempdb.dbo.sysobjects where id = object_id(N'tempdb..#TmpStockInfo') and type='U') drop table #TmpStockInfo ";
        db.Execute(sql);
        sql = " if exists (select * from tempdb.dbo.sysobjects where id = object_id(N'tempdb..#TmpDateSeq') and type='U') drop table #TmpDateSeq ";
        db.Execute(sql);
        sql = " if exists (select * from tempdb.dbo.sysobjects where id = object_id(N'tempdb..#TmpErpSeq') and type='U') drop table #TmpErpSeq ";
        db.Execute(sql);
        sql = " if exists (select * from tempdb.dbo.sysobjects where id = object_id(N'tempdb..#TmpDatePlanInPut') and type='U') drop table #TmpDatePlanInPut ";
        db.Execute(sql);
        sql = " if exists (select * from tempdb.dbo.sysobjects where id = object_id(N'tempdb..#TmpDatePlanInPut_1') and type='U') drop table #TmpDatePlanInPut_1 ";
        db.Execute(sql);
        sql = " if exists (select * from tempdb.dbo.sysobjects where id = object_id(N'tempdb..#TmpErpNeedInPut') and type='U') drop table #TmpErpNeedInPut ";
        db.Execute(sql);
        
        
        //排程表信息
        sql = " select ErpNo, CustName, ProNo, OrderNum, OrderDate, PlanDate, PlanNum, TypeFlag, SchedulingSeq "
            + " into #TmpScheduling "
            + " from( "
            + "         select ErpNoSplit as ErpNo, CustName, ProNo, OrderNum, "
            + "         CONVERT(varchar(10), OrderDate, 23) as OrderDate, "
            + "         CONVERT(varchar(10), PatchPlanDate, 23) as PlanDate, "
            + "         PatchPlanNum as PlanNum, 1 as TypeFlag, SchedulingSeq "
            + "         from V_UM_MRP_Scheduling "
            + "         where PatchPlanDate is not null and isnull(PatchPlanNum,0)<>0 "
            + "         union all "
            + "         select ErpNoSplit as ErpNo, CustName, ProNo, OrderNum, "
            + "         CONVERT(varchar(10), OrderDate, 23) as OrderDate, "
            + "         CONVERT(varchar(10), PluginPlanDate, 23) as PlanDate, "
            + "         PluginPlanNum as PlanNum, 2 as TypeFlag, SchedulingSeq "
            + "         from V_UM_MRP_Scheduling "
            + "         where PluginPlanDate is not null and isnull(PluginPlanNum,0)<>0 "
            + "         union all "
            + "         select ErpNoSplit as ErpNo, CustName, ProNo, OrderNum, "
            + "         CONVERT(varchar(10), OrderDate, 23) as OrderDate, "
            + "         CONVERT(varchar(10), PackPlanDate, 23) as PlanDate, "
            + "         PackPlanNum as PlanNum, 3 as TypeFlag, SchedulingSeq "
            + "         from V_UM_MRP_Scheduling "
            + "         where PackPlanDate is not null and isnull(PackPlanNum,0)<>0 "
            + " )a "
            + " where TypeFlag = '"+typeFlag+"' ";
        db.Execute(sql);
        
        //销售订单物料需求信息
        sql = " select a.ErpNo, a.FInterID, a.FBOMNumber, a.FItemID, b.FName, b.FNumber, b.FModel, a.FAuxQty, a.FQty,a.TypeFlag "
            + " into #TmpErpMtInfo "
            + " from( "
            + "         select b.ErpNo, a.FBOMNumber, a.FInterID, a.FItemID,b.TypeFlag, "
            + "         sum(a.FAuxQty*b.PlanNum) as FAuxQty, sum(a.FQty*b.PlanNum) as FQty "
            + "         from UM_MRP_BOMdetail a "
            + "         join #TmpScheduling b on a.FBOMNumber=b.ProNo "
            + "         group by b.ErpNo, a.FBOMNumber, a.FInterID, a.FItemID,b.TypeFlag "
            + " )a "
            + " join t_ICItem b on a.FItemID=b.FItemID "
            + " where 1=1 "+condition
            + " order by a.ErpNo, a.FItemID ";
        db.Execute(sql);
        
        //插入物料需求里可互替的XXX或XXR物料而又不在需求里的XXX或XXR物料
        sql = " insert into #TmpErpMtInfo(ErpNo, FInterID, FBOMNumber, FItemID, FName, FNumber, FModel, FAuxQty, FQty,TypeFlag) "
            + " select b.ErpNo, b.FInterID, b.FBOMNumber, a.FItemID, a.FName, a.FNumber, a.FModel, 0 as FAuxQty, 0 as FQty,b.TypeFlag "
            + " from( "
            + "         select a.* "
            + "         from( "
            + "                 select distinct FItemID, FNumber, FName, FModel from #TmpErpMtInfo "
            + "                 union "
            + "                 select distinct b.FItemID_R, b.FNumber_R, b.FName_R, b.FModel_R from #TmpErpMtInfo a "
            + "                 join V_UM_MRP_t_icitem b on a.FItemID=b.FItemID "
            + "                 union "
            + "                 select distinct b.FItemID_X, b.FNumber_X, b.FName_X, b.FModel_X from #TmpErpMtInfo a "
            + "                 join V_UM_MRP_t_icitem b on a.FItemID=b.FItemID "
            + "         ) a "
            + "         left join #TmpErpMtInfo b on a.FItemID=b.FItemID "
            + "         where b.FItemID is null "
            + " ) a "
            + " join( "
            + "         select top 1 * from #TmpErpMtInfo "
            + " ) b on 1=1 ";
        db.Execute(sql);
        //插入替代物料
        sql = "insert into #TmpErpMtInfo(ErpNo, FInterID, FBOMNumber, FItemID, FName, FNumber, FModel, FAuxQty, FQty,TypeFlag) "
        		+ "select a.ErpStr, 0, '',b.FItemID, b.FName, b.FNumber, b.FModel, a.NeedNum as FAuxQty, a.NeedNum as  FQty,a.MtType "
        		+ " from UM_MRP_SaveInfo_Dtl_ErpNeed a left join t_ICItem b on a.FNumber=b.FNumber "
        		+ "where a.FNumber not in (SELECT fnumber FROM #TmpErpMtInfo) "+condition;
        db.Execute(sql);
        
        //获取物料库存（材料仓和待检仓）
        sql = " select FItemID, CLQty, DJQty, CLQty+DJQty as StockQty "
            + " into #TmpStockInfo "
            + " from( "
            + "         select FItemID, "
            + "         sum(case when FstockID in(152,16477,29005) then FQty else 0 end) as CLQty, "
            + "         sum(case when FstockID=151 then FQty else 0 end) as DJQty "
            + "         from ICInventory "
            + "         group by FItemID "
            + " ) a ";
        db.Execute(sql);
        //排程的日期排序
        sql = " select DateStr, ROW_NUMBER() OVER(ORDER BY DateStr) as seq "
            + " into #tmpDateSeq "
            + " from( "
            + "         select distinct PlanDate as DateStr from #TmpScheduling "
            + " )a ";
        db.Execute(sql);
        //排程的销售订单排序
        sql = " select PlanDate as DateStr, ErpNo, ROW_NUMBER() OVER(PARTITION BY PlanDate ORDER BY SchedulingSeq) as seq "
            + " into #tmpErpSeq "
            + " from #TmpScheduling ";
        db.Execute(sql);
        
        sql = "select a.DateStr, c.FItemID, c.FNumber, a.PlanNum "
        		+ "into #TmpDatePlanInPut "
        		+ "from UM_MRP_SaveInfo_Dtl_DatePlan a "
        		+ "join (select FNumber,max(versionDate) versionDate from UM_MRP_SaveInfo_Dtl_DatePlan  group by FNumber) b "
        		+ "on a.FNumber = b.FNumber and a.versionDate=b.versionDate "
        		+ "join t_icitem c on b.FNumber=c.FNumber where a.MtType='"+typeFlag+"'";
        db.Execute(sql);
        //最新操作日期的需求数量
        
        sql = "select a.ErpStr as ErpNo, c.FItemID, c.FNumber, a.NeedNum "
        		+ "into #TmpErpNeedInPut "
        		+ "from UM_MRP_SaveInfo_Dtl_ErpNeed a "
        		+ "join (select ErpStr, FNumber,max(versionDate) versionDate "
        		+ "from UM_MRP_SaveInfo_Dtl_ErpNeed  group by ErpStr, FNumber) b "
        		+ "on a.ErpStr = b.ErpStr and a.FNumber = b.FNumber and a.versionDate=b.versionDate "
        		+ "join t_icitem c on b.FNumber=c.FNumber where a.MtType='"+typeFlag+"'";
        db.Execute(sql);
        //每个计划日期物料的计划数
        sql = " select a.PlanDate, a.FItemID, isnull(b.PlanNum, 0) as PlanNum "
            + " into #TmpDatePlanInPut_1 "
            + " from( "
            + "         select a.PlanDate, b.FItemID "
            + "         from( "
            + "                 select distinct PlanDate from #TmpScheduling "
            + "         ) a "
            + "         join ( "
            + "                 select distinct FItemID from #TmpDatePlanInPut "
            + "         ) b on 1=1 "
            + " ) a "
            + " left join #TmpDatePlanInPut b on a.PlanDate=b.DateStr and a.FItemID=b.FItemID ";
        db.Execute(sql);
        
        
        sql = " if exists (select * from tempdb.dbo.sysobjects where id = object_id(N'tempdb..#TmpDateMt') and type='U') drop table #TmpDateMt ";
        db.Execute(sql);
        sql = " if exists (select * from tempdb.dbo.sysobjects where id = object_id(N'tempdb..#tmpDateMt_1') and type='U') drop table #TmpDateMt_1 ";
        db.Execute(sql);
        sql = " if exists (select * from tempdb.dbo.sysobjects where id = object_id(N'tempdb..#tmpDateMt_2') and type='U') drop table #TmpDateMt_2 ";
        db.Execute(sql);
        //每个计划日期的物料库存 需求 计划数
        sql = " select a.DateStr, a.FItemID, isnull(c.StockQty,0) as StockQty, "
            + " isnull(b.FQty,0) as FQty, isnull(d.PlanNum,0) as PlanNum "
            + " into #TmpDateMt "
            + " from( "
            + "         select DateStr, FItemID "
            + "         from( "
            + "                 select distinct PlanDate as DateStr from #TmpScheduling "
            + "         ) a "
            + "         join( "
            + "                 select distinct FItemID from #TmpErpMtInfo "
            + "         ) b on 1=1 "
            + " ) a "
            + " left join ( "
            + "         select b.PlanDate as DateStr, a.FItemID, sum(FQty) as FQty "
            + "         from #TmpErpMtInfo a "
            + "         join #TmpScheduling b on a.ErpNo=b.ErpNo "
            + "         group by b.PlanDate, a.FItemID "
            + " ) b on a.DateStr=b.DateStr and a.FItemID=b.FItemID "
            + " left join #TmpStockInfo c on a.FItemID=c.FItemID "
            + " left join #TmpDatePlanInPut d on a.DateStr=d.DateStr and a.FItemID=d.FItemID ";
        db.Execute(sql);
        sql = " select a.DateStr, a.FItemID, a.StockQty, a.PlanNum, a.FQty, "
            + " sum(b.FQty) as TFQty, a.StockQty+sum(b.PlanNum)-sum(b.FQty) as SFQty, "
            + " a.StockQty+sum(b.PlanNum)-sum(b.FQty)+a.FQty as KFQty "
            + " into #tmpDateMt_1 "
            + " from #tmpDateMt a "
            + " join #tmpDateMt b on a.FItemID=b.FItemID and a.DateStr>=b.DateStr "
            + " group by a.DateStr, a.FItemID, a.StockQty, a.PlanNum, a.FQty "
            + " order by a.FItemID, a.DateStr ";
        db.Execute(sql);
        
        sql = " select a.DateStr, a.FItemID, a.StockQty, a.PlanNum, a.FQty,  "
            + " a.TFQty, a.SFQty+isnull(b.FNeedQty,0) as SFQty, "
            + " a.KFQty+isnull(b.FNeedQty,0) as KFQty "
            + " into #tmpDateMt_2 "
            + " from #tmpDateMt_1 a "
            + " left join V_UM_MRP_PPBomEntry b on a.FItemID=b.FItemID ";
        db.Execute(sql);
        
        sql = " if exists (select * from tempdb.dbo.sysobjects where id = object_id(N'tempdb..#TmpErpMt') and type='U') drop table #TmpErpMt ";
        db.Execute(sql);
        sql = " if exists (select * from tempdb.dbo.sysobjects where id = object_id(N'tempdb..#TmpErpMt_1') and type='U') drop table #TmpErpMt_1 ";
        db.Execute(sql);
        sql = " if exists (select * from tempdb.dbo.sysobjects where id = object_id(N'tempdb..#TmpErpMt_2') and type='U') drop table #TmpErpMt_2 ";
        db.Execute(sql);
        sql = " select a.ErpNo, a.PlanDate, a.seq, a.FItemID, isnull(StockQty,0) as StockQty, isnull(e.PlanNum,0) as PlanNum,  "
            + " isnull(f.NeedNum, isnull(b.FQty,0)) as FQty "
            + " into #TmpErpMt "
            + " from( "
            + "         select a.ErpNo, a.PlanDate, a.seq, b.FItemID "
            + "         from( "
            + "                 select distinct ErpNo, PlanDate, ROW_NUMBER() OVER(ORDER BY PlanDate, SchedulingSeq) as seq "
            + "                 from #TmpScheduling "
            + "         )a "
            + "         join( "
            + "                 select distinct FItemID from #TmpErpMtInfo "
            + "         )b on 1=1 "
            + " ) a "
            + " left join #TmpErpMtInfo b on a.ErpNo=b.ErpNo and a.FItemID=b.FItemID "
            + " left join #TmpStockInfo c on a.FItemID=c.FItemID "
            + " left join ( "
            + "     select a.PlanDate, a.FItemID, sum(b.PlanNum) as PlanNum "
            + "     from #TmpDatePlanInPut_1 a "
            + "     join #TmpDatePlanInPut_1 b on a.FItemID=b.FItemID and a.PlanDate>=b.PlanDate "
            + "     group by a.PlanDate, a.FItemID "
            + " ) e on a.PlanDate=e.PlanDate and a.FItemID=e.FItemID "
            + " left join #TmpErpNeedInPut f on a.ErpNo=f.ErpNo and a.FItemID=F.FItemID "
            + " order by a.FItemID, a.seq ";
        db.Execute(sql);
        sql = " select a.PlanDate, a.FItemID, a.StockQty, a.ErpNo, a.FQty, a.seq, "
            + " sum(b.FQty) as TFQty, a.StockQty+a.PlanNum-sum(b.FQty) as SFQty "
            + " into #tmpErpMt_1 "
            + " from #tmpErpMt a "
            + " join #tmpErpMt b on a.FItemID=b.FItemID and a.seq>=b.seq "
            + " group by a.PlanDate,a.FItemID, a.StockQty, a.ErpNo, a.FQty, a.seq, a.PlanNum "
            + " order by a.FItemID, a.seq ";
        db.Execute(sql);
        
        sql = " select a.FItemID, a.StockQty, a.ErpNo, a.FQty, a.seq, "
            + " a.TFQty, a.SFQty+isnull(b.FNeedQty,0) as SFQty "
            + " into #tmpErpMt_2 "
            + " from #tmpErpMt_1 a "
            + " left join V_UM_MRP_PPBomEntry b on a.FItemID=b.FItemID ";
        db.Execute(sql);
		return db;
	}

}
