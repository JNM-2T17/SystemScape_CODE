/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model.builder;

import java.util.Iterator;

/**
 *
 * @author Christian Gabriel
 */
public class POFilterQueryBuilder implements FilterQueryBuilder{
    private String select;
	private String from;
	private String where;
	private String groupBy;
	private String having;
	private String orderBy;

	public POFilterQueryBuilder() {
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
                where = where + "po.type = " + temp +" && ";
            temp= (String) conditions.next();
            if(!temp.equals(""))
                where = where + "po.supplier = " + temp +" && ";
            temp= (String) conditions.next();
            if(!temp.equals(""))
                where = where + "po.date = " + temp +" && ";
            temp= (String) conditions.next();
            if(!temp.equals(""))
                where = where + "pi.no = " + temp +" && ";
            temp= (String) conditions.next();
            if(!temp.equals(""))
                addGroupingCondition("Sum"+temp);
            where = where + "po.no = pi.no && po.type = pi.type && pi.itemname = id.name && ";
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
            addColumn("po.no");
            addColumn("po.type");
            addColumn("po.supplier");
            addColumn("po.date");
            addColumn("SUM(pi.quantityOrdered*id.unitPrice) AS Sum");
            addTable("purchaseorder po");
            addTable("poitem pi");
            addTable("itemdata id");
            addGrouping("po.no");
            addGrouping("po.type");
            addCondition(conditions);
            return select.substring(0,(select.length()-2)) + " " +from.substring(0,from.length()-2) + " " + where.substring(0,where.length()-3)+" "+groupBy.substring(0,groupBy.length()-2)+" "+having.substring(0,having.length()-2);
	}
}
