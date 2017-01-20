package test03;

import com.tw.jdbc.SqlserverJdbc;

public class T01 {

	public static void main(String[] args) throws Exception{
		SqlserverJdbc db = new SqlserverJdbc();
		
		String sql = null;
		
		sql = "select distinct a.DateStr,a.FItemID,a.FQty,a.PlanNum,isnull(b.StockQty,0) as StockQty "
				+ "into #TmpDateMt from tmp_mrp_plan_date a "
				+ "join tmp_mrp_plan c on a.FItemID=c.FItemID and c.TypeFlag=1 "
				+ "left join ("
				+ "select FItemID, "
				+ "sum(case when FstockID in(152,16477,29005) then FQty else 0 end)+"
				+ "sum(case when FstockID=151 then FQty else 0 end) as StockQty "
				+ "from ICInventory "
				+ "group by FItemID) b on a.FItemID=b.FItemID ";
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
		
		
		/*sql = "select a.ErpNo, a.PlanDate, a.seq, a.FItemID,isnull(a.FQty,0) as FQty,isnull(b.StockQty,0) as StockQty,0 as PlanNum "
				+ "into #TmpErpMt from tmp_mrp_plan_date_need a "
				+ "join tmp_mrp_plan c on a.FItemID=c.FItemID and c.TypeFlag=1 "
				+ "left join ("
				+ "select FItemID,"
				+ "sum(case when FstockID in(152,16477,29005) then FQty else 0 end)+"
				+ "sum(case when FstockID=151 then FQty else 0 end) as StockQty "
				+ "from ICInventory group by FItemID) b on a.FItemID=b.FItemID "
				+ "order by a.FItemID, a.seq";
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
		db.Execute(sql);*/
		
//		db.Execute("select * into tmp_DateMt from #TmpDateMt");
//		db.Execute("select * into tmp_DateMt_01 from #TmpDateMt_1");
		db.Execute("select * into tmp_DateMt_02 from #TmpDateMt_2");
//		db.Execute("select * into tmp_ErpMt from #TmpErpMt");
//		db.Execute("select * into tmp_ErpMt_01 from #TmpErpMt_1");
//		db.Execute("select * into tmp_ErpMt_02 from #TmpErpMt_2");	
		
//		db.Close();
		System.out.println(1111);
	}

}
