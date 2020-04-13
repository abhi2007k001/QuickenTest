package com.dlc.constants;

public class dbConstants {

	public static final String query = "SELECT COUNT(*) FROM " + "table";

	public static final String query01 = "SELECT COUNT(DISTINCT(s.individual_id)) from screening_history s inner join individual i on s.individual_id=i.id  inner join subcenter sc on s.subcenter_id=sc.id inner join phc ph on sc.phc_id=ph.id where ph.id=1210093 and s.workflow_action_id in (3204,3252,3222,3223,3224,3225,3232,3242,3243) and i.ind_state=0 ";
	public static final String phcquery = "SELECT COUNT(DISTINCT(s.individual_id)) from screening_history s inner join individual i on s.individual_id=i.id  inner join subcenter sc on s.subcenter_id=sc.id inner join phc ph on sc.phc_id=ph.id where ph.id=#phcId and s.workflow_action_id in (3204,3252,3222,3223,3224,3225,3232,3242,3243) and i.ind_state=#stateId ";

	public static final String query02 = "SELECT phc_screened from database_goi_kpi_aggregator.hierarchy_kpi WHERE location_type=7004 and id=1210093 and filter1=9999";
	public static final String kpiQuery = "SELECT phc_screened from database_goi_kpi_aggregator.hierarchy_kpi WHERE location_type=#locationType and id=#locationId and filter1=#filterId";

	public static final String query03 = "select * from database_goi_kpi_aggregator.hierarchy_kpi hk ";
}
