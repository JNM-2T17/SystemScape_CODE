/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model.builder;

import java.util.Date;
import java.util.Iterator;

/**
 *
 * @author Christian Gabriel
 */
public class ProjectFilterQueryBuilder implements FilterQueryBuilder{
    private String select;
	private String from;
	private String where;
	private String groupBy;
	private String having;
	private String orderBy;

	public ProjectFilterQueryBuilder() {
		select="";
		from=""; 
		where="WHERE ";
		groupBy="";
		having="";
		orderBy="";
	}

	public void addColumn(String column) {
		if(select.length()==0)
			select= "SELECT "+column+", ";
		else
			select = select + "" + column + ", ";
	}

	public void addTable(String table) {
            if(from.length()==0)
                from= "FROM "+table+", ";
            else
                from = from + "" + table + ", ";
	}

        public void addCondition(Iterator conditions) {
            String temp;
            temp= (String) conditions.next();
            if(!temp.equals(""))
                where = where + "p.name LIKE \"%" + temp +"%\" OR ";
            temp= ((Date) conditions.next()).toString();
            if(!temp.equals(""))
                where = where + "p.startDate LIKE \"%" + temp +"%\" OR ";
            temp= ((Date) conditions.next()).toString();
            if(!temp.equals(""))
                where = where + "p.endDate LIKE \"%" + temp +"%\" OR ";
	}

	public void addGrouping(String group) {
		if(groupBy.length()==0)
			groupBy = "GROUP BY "+group+", ";
		else
			groupBy = groupBy + ""+ group +", ";
	}

	public void addGroupingCondition(String groupCondition) {
            if(having.length()==0)
                having = "HAVING "+ groupCondition +", ";
            else
                having = having +" "+groupCondition+", ";
	}

	public void addOrderCriteria(String orderCriteria) {
            if(orderBy.length()==0)
                orderBy= "ORDER BY "+ orderCriteria+", ";
            else		
                orderBy = orderBy + ""+ orderCriteria +", ";
	}

	public String getQuery(Iterator conditions) {
            addColumn("p.name");
            addColumn("p.startDate");
            addColumn("p.endDate");
            addColumn("p.employee");
            addTable("project p");
            addCondition(conditions);
            return select.substring(0,(select.length()-2)) + " " +from.substring(0,from.length()-2) + " " + where.substring(0,where.length()-3);
	}
}
