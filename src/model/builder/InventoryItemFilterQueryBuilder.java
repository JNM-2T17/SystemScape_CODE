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
public class InventoryItemFilterQueryBuilder implements FilterQueryBuilder{
    private String select;
	private String from;
	private String where;
	private String groupBy;
	private String having;
	private String orderBy;

	public InventoryItemFilterQueryBuilder() {
		select="";
		from=""; 
		where="";
		groupBy="";
		having="";
		orderBy="";
	}

	public void addColumn(String column) {
		if(select.length()==0)
			select= "SELECT "+column+", ";
		else
			select = select + column + ", ";
	}

	public void addTable(String table) {
            if(from.length()==0)
                from= "FROM "+table+", ";
            else
                from = from + table + ", ";
	}
        
        public void addLeftJoin(String table){
            from = from +"LEFT JOIN "+ table+" ";
        }
        
        public void addOn(String condition){
            from = from+"ON "+condition+" ";
        }

        public void addCondition(Iterator conditions) {
            where = "WHERE ";
            String temp, temp2;
            temp= (String) conditions.next();
            if(!temp.equals(""))
                where = where + "ii.itemdata LIKE \"%" + temp +"%\"&& ";
            temp= (String) conditions.next();
            if(!temp.equals(""))
                where = where + "ii.classification = \"" + temp +"\"&& ";
            temp= (String) conditions.next();
            if(!temp.equals(""))
                where = where + "ii.location LIKE \"%" + temp +"%\"&& ";
            temp= (String) conditions.next();
            if(!temp.equals(""))
                where = where + "it.assetTag LIKE \"%" + temp +"%\"&& ";
            temp= (String) conditions.next();
            if(!temp.equals(""))
                where = where + "it.serviceTag LIKE \"%" + temp +"%\"&& ";
            temp= (String) conditions.next();
            if(!temp.equals(""))
                where = where + "e.name LIKE \"%" + temp +"%\"&& ";
            temp= (String) conditions.next();
            temp2 = (String) conditions.next();
            if(!temp2.equals("") && !temp.equals(""))
                    where = where + "pi.quantityReceived " + temp + " " + temp2 + " && ";
            temp= (String) conditions.next();
            if(!temp.equals(""))
                where = where + "ii.invoiceNo LIKE \"%" + temp +"%\"&& ";
	}

	public void addGrouping(String group) {
		if(groupBy.length()==0)
			groupBy = "GROUP BY "+group+", ";
		else
			groupBy = groupBy + group +", ";
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
            addColumn("ii.ID");
            
            addTable("inventoryitem ii");
            from = from.substring(0,from.length()-2) + " ";
            addLeftJoin("poitem pi");
            addOn("ii.itemdata = pi.itemname");
            addLeftJoin("itemassignment ia");
            addOn("ia.itemID = ii.ID");
            addLeftJoin("itasset it");
            addOn("it.ID = ii.ID");
            addLeftJoin("contract c");
            addOn("ii.ID = c.hardware");
            addLeftJoin("warranty w");
            addOn("ii.ID = w.hardware");
            addLeftJoin("employee e");
            addOn("e.ID = ia.employeeID");
            
            
            addCondition(conditions);
            
            addGrouping("ii.ID");
            System.out.println("huwaw\n" +select.substring(0,select.length()-2) + " " +from.substring(0,from.length()-1) + " " + where.substring(0,where.length()-3)+" "+groupBy.substring(0,groupBy.length()-2));
            return select.substring(0,select.length()-2) + " " +from.substring(0,from.length()-1) + " " + where.substring(0,where.length()-3)+" "+groupBy.substring(0,groupBy.length()-2);
	}
}
