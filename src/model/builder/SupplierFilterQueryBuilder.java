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
public class SupplierFilterQueryBuilder implements FilterQueryBuilder {

    private String select;
    private String from;
    private String where;
    private String groupBy;
    private String having;
    private String orderBy;

    public SupplierFilterQueryBuilder() {
        select = "";
        from = "";
        where = "WHERE ";
        groupBy = "";
        having = "";
        orderBy = "";
    }

    public void addColumn(String column) {
        if (select.length() == 0) {
            select = "SELECT " + column + ",";
        } else {
            select = select + " " + column + ",";
        }
    }

    public void addTable(String table) {
        if (from.length() == 0) {
            from = "FROM " + table + ",";
        } else {
            from = from + " " + table + ",";
        }
    }

    public void addCondition(Iterator conditions) {
        if (conditions != null) {
            String temp;
            temp = (String) conditions.next();
            if (!temp.equals("")) {
                where = where + "name LIKE \"%" + temp + "%\"&& ";
            }
            temp = (String) conditions.next();
            if (!temp.equals("")) {
                where = where + "country LIKE \"%" + temp + "%\"&& ";
            }
            temp = (String) conditions.next();
            if (!temp.equals("")) {
                where = where + "state LIKE \"%" + temp + "%\"&& ";
            }
            temp = (String) conditions.next();
            if (!temp.equals("")) {
                where = where + "city LIKE \"%" + temp + "%\"&& ";
            }
            temp = (String) conditions.next();
            if (!temp.equals("")) {
                String temp2 = (String) conditions.next();
                where = where + "sc.supplier IN (SELECT supplier FROM suppliercontact WHERE value LIKE \"%" + temp + "%\" && type LIKE \"%" + temp2 + "%\")&&";
            }
            where = where + "s.name=sc.supplier &&";
            where = where.substring(0, where.length() - 2);
        }else{
            where = "";
        }
    }

    public void addGrouping(String group) {
        if (groupBy.length() == 0) {
            groupBy = "GROUP BY " + group + ", ";
        } else {
            groupBy = groupBy + " " + group + ",";
        }
    }

    public void addGroupingCondition(String groupCondition) {
        if (having.length() == 0) {
            having = "HAVING \"" + groupCondition + "\", ";
        } else {
            having = having + " \"" + groupCondition + "\", ";
        }
    }

    public void addOrderCriteria(String orderCriteria) {
        if (orderBy.length() == 0) {
            orderBy = "ORDER BY " + orderCriteria + ", ";
        } else {
            orderBy = orderBy + " " + orderCriteria + ",";
        }
    }

    public String getQuery(Iterator conditions) {
        addColumn("s.name");
        addColumn("s.country");
        addColumn("s.state");
        addColumn("s.city");
        addColumn("sc.type");
        addColumn("sc.value");
        addTable("supplier s");
        addTable("suppliercontact sc");
        addCondition(conditions);
        addGrouping("s.name");

        if (groupBy.length() > 0) {
            groupBy = groupBy.substring(0, groupBy.length() - 1);
        }

        //System.out.println(select.substring(0, select.length() - 1) + " " + from.substring(0, from.length() - 1) + " " + where.substring(0, where.length() - 2) + " " + groupBy.substring(0, groupBy.length() - 1));
        return select.substring(0, select.length() - 1) + " " + from.substring(0, from.length() - 1) + " " + where + " " + groupBy.substring(0, groupBy.length() - 1);
    }
}
