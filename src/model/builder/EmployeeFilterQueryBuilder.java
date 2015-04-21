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
        select = "";
        from = "";
        where = "WHERE ";
        groupBy = "";
        having = "";
        orderBy = "";
    }

    public void addColumn(String column) {
        if (select.length() == 0) {
            select = "SELECT " + column + ", ";
        } else {
            select = select + "" + column + ", ";
        }
    }

    public void addTable(String table) {
        if (from.length() == 0) {
            from = "FROM " + table + ", ";
        } else {
            from = from + "" + table + ", ";
        }
    }

    public void addCondition(Iterator conditions) {
        if (conditions != null) {
            String temp;
            temp = (String) conditions.next();
            int check = 0;
            if (!temp.equals("")) {
                where = where + "e.name LIKE \"%" + temp + "%\" && ";
            }
            temp = conditions.next().toString();
            if (!temp.equals("")) {
                where = where + "e.status LIKE \"%" + temp + "%\" && ";
            }
            temp = conditions.next().toString();
            if (!temp.equals("")) {
                where = where + "p.name LIKE \"%" + temp + "%\" && ";
            }

            where = where.substring(0, where.length() - 3);

        } else {
            where = "";
        }
    }

    public void addGrouping(String group) {
        if (groupBy.length() == 0) {
            groupBy = "GROUP BY " + group + ", ";
        } else {
            groupBy = groupBy + "" + group + ", ";
        }
    }

    public void addGroupingCondition(String groupCondition) {
        if (having.length() == 0) {
            having = "HAVING " + groupCondition + ", ";
        } else {
            having = having + " " + groupCondition + ", ";
        }
    }

    public void addOrderCriteria(String orderCriteria) {
        if (orderBy.length() == 0) {
            orderBy = "ORDER BY " + orderCriteria + ", ";
        } else {
            orderBy = orderBy + "" + orderCriteria + ", ";
        }
    }

    public void addLeftJoin(String table) {
        from = from + "LEFT JOIN " + table + " ";
    }

    public void addOn(String condition) {
        from = from + "ON " + condition + " ";
    }

    public String getQuery(Iterator conditions) {
        addColumn("e.ID");
        addColumn("e.name");
        addColumn("e.status");
        addColumn("e.type");
        addColumn("p.name");
        addColumn("p.startDate");
        addColumn("p.endDate");
        addTable("employee e");
        from = from.substring(0, from.length() - 2) + " ";
        addLeftJoin("projectassignment pa");
        addOn("pa.employeeID = e.ID");
        addLeftJoin("project p");
        addOn("pa.project = p.name");
        addCondition(conditions);
        System.out.println(select.substring(0, (select.length() - 2)) + " " + from.substring(0, from.length() - 1) + " " + where);
        return select.substring(0, (select.length() - 2)) + " " + from.substring(0, from.length() - 1) + " " + where;
    }

}
