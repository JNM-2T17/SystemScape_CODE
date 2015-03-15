/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.builder;

import java.util.Iterator;
/**
 *
 * @author Laptop
 */
public class ContactFilterQueryBuilder implements FilterQueryBuilder{
    private String select;
	private String from;
	private String where;
	private String groupBy;
	private String having;
	private String orderBy;

	public ContactFilterQueryBuilder() {
		select="";
		from=""; 
		where="WHERE ";
		groupBy="";
		having="";
		orderBy="";
	}

	public void addColumn(String column) {
		if(select.length()==0)
			select= "SELECT \""+column+"\", ";
		else
			select = select + "\"" + column + "\", ";
	}

	public void addTable(String table) {
            if(from.length()==0)
                from= "FROM \""+table+"\", ";
            else
                from = from + "\"" + table + "\", ";
	}
        
        public void addLeftJoin(String table){
            from = from +"LEFT JOIN "+ table+" ";
        }
        
        public void addOn(String condition){
            from = from+"ON "+condition+" ";
        }

        public void addCondition(Iterator conditions) {
            String temp;
            temp= (String) conditions.next();
            if(!temp.equals(""))
                where = where + "ii.itemdata = \"" + temp +"\"&&";
            temp= (String) conditions.next();
            if(!temp.equals(""))
                where = where + "ii.classification = \"" + temp +"\"&&";
            temp= (String) conditions.next();
            if(!temp.equals(""))
                where = where + "ii.location = \"" + temp +"\"&&";
            temp= (String) conditions.next();
            if(!temp.equals(""))
                where = where + "it.assetTag = \"" + temp +"\"&&";
            temp= (String) conditions.next();
            if(!temp.equals(""))
                where = where + "it.serviceTag = \"" + temp +"\"&&";
            temp= (String) conditions.next();
            if(!temp.equals(""))
                where = where + "e.name = \"" + temp +"\"&&";
            temp= (String) conditions.next();
            if(!temp.equals(""))
                where = where + "pi.quantityReceived = \"" + temp +"\"&&";
	}

	public void addGrouping(String group) {
		if(groupBy.length()==0)
			groupBy = "GROUP BY \""+group+"\", ";
		else
			groupBy = groupBy + "\""+ group +"\", ";
	}

	public void addGroupingCondition(String groupCondition) {
            if(having.length()==0)
                having = "HAVING \""+ groupCondition +"\", ";
            else
                having = having +" \""+groupCondition+"\", ";
	}

	public void addOrderCriteria(String orderCriteria) {
            if(orderBy.length()==0)
                orderBy= "ORDER BY \""+ orderCriteria+"\", ";
            else		
                orderBy = orderBy + "\""+ orderCriteria +"\", ";
	}

	public String getQuery(Iterator conditions) {
            addColumn("ii.ID");
            addColumn("ii.itemdata");
            addColumn("id.description");
            addColumn("ii.classification");
            addColumn("pi.quantityReceived");
            addColumn("ii.location");
            addColumn("ii.invoiceNo");
            addColumn("it.assetTag");
            addColumn("it.serviceTag");
            addColumn("e.name");
            addColumn("it.deliveryDate");
            addColumn("c.endDate");
            addColumn("w.endDate");
            
            addTable("inventoryitem ii");
            addLeftJoin("poitem pi");
            addOn("ii.itemdata = pi.itemname");
            addLeftJoin("assetassignment aa");
            addOn("aa.ID = ii.ID");
            addLeftJoin("itasset it");
            addOn("it.ID = ii.ID");
            addLeftJoin("contract c");
            addOn("ii.ID = c.hardware");
            addLeftJoin("warranty w");
            addOn("ii.ID = w.hardware");
            addLeftJoin("softwareassignment sa");
            addOn("sa.ID = ii.ID");
            addLeftJoin("employee e");
            addOn("(e.ID = aa.employee OR e.ID = sa.employee)");
            
            
            addCondition(conditions);
            
            addGrouping("ii.ID");
	
            return select.substring(0,select.length()-1) + " " +from.substring(0,from.length()-1) + " " + where.substring(0,where.length()-2)+" "+groupBy.substring(0,groupBy.length()-1)+" "+having.substring(0,having.length()-1);
	}
}
