package model.builder;

import java.util.Date;
import java.util.Iterator;

public class EmployeeFilterQueryBuilder implements FilterQueryBuilder {
	
	private String select;
	private String from;
	private String where;
	private String groupBy;
	private String having;
	private String orderBy;

	public EmployeeFilterQueryBuilder() {
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
                where = where + "e.name LIKE \"%" + temp +"%\" OR ";
            temp= conditions.next().toString();
            if(!temp.equals(""))
                where = where + "e.status LIKE \"%" + temp +"%\" OR ";
            temp= conditions.next().toString();
            if(!temp.equals(""))
                where = where + "p.name LIKE \"%" + temp +"%\" OR ";
            temp= conditions.next().toString();
            if (!temp.equals("")) 
                where = where + "p.startDate LIKE \"%" + temp + "%\" OR ";
            temp= conditions.next().toString();
            if (!temp.equals("")) 
                where = where + "p.endDate LIKE \"%" + temp + "%\" ";
                
            where = where + "&& pa.project = p.name && pa.employeeID = e.ID";
            System.out.println("WHERE na you"+ where);
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
            //addColumn("e.id");
            addColumn("e.name");
            addColumn("e.status");
            addColumn("p.name");
            addColumn("p.startDate");
            addColumn("p.endDate");
            addTable("employee e");
            addTable("project p");
            addTable("projectassignment pa");
            addCondition(conditions);
            System.out.println(select.substring(0,(select.length()-2)) + " " +from.substring(0,from.length()-2) + " " + where.substring(0,where.length()-3));
            return select.substring(0,(select.length()-2)) + " " +from.substring(0,from.length()-2) + " " + where.substring(0,where.length());
	}

}
