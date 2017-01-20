select
distinct CONVERT(varchar(100), PatchPlanDate, 23)
from UM_MRP_Scheduling_Dtl
where VersionID=(select max(VersionID) as VersionID from UM_MRP_Scheduling_Main)
and PatchPlanDate is not null
and isnull(PatchPlanNum,0)<>0
select
b.ErpNo from
(
   select
   max(VersionID) as VersionID
   from UM_MRP_Scheduling_Main
)
a join UM_MRP_Scheduling_Dtl b on a.VersionID=b.VersionID
where PatchPlanDate='2015-04-03'
and isnull(PatchPlanNum,0)<>0
select
*
from UM_MRP_Scheduling_Dtl


select
*
from UM_MRP_BaseDateInfo
where datestr between
(
   SELECT
   min(a.planDate)
   FROM UM_MRP_SCHEDULING_DAYPLAN a join
   (
      SELECT
      FNumber,max(version) as version
      FROM UM_MRP_SCHEDULING
      where username='weir'
      group by FNumber
   )
   b on a.FNumber=b.FNumber
   and a.version=b.version
   where a.username='weir'
)
and
(
   SELECT
   max(a.planDate)
   FROM UM_MRP_SCHEDULING_DAYPLAN a join
   (
      SELECT
      FNumber,max(version) as version
      FROM UM_MRP_SCHEDULING
      where username='weir'
      group by FNumber
   )
   b on a.FNumber=b.FNumber
   and a.version=b.version
   where a.username='weir'
)
;

select * from UM_MRP_SCHEDULING_DAYPLAN