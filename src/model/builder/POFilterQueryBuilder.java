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
public class POFilterQueryBuilder implements FilterQueryBuilder {

    private String select;
    private String from;
    private String where;
    private String groupBy;
    private String having;
    private String orderBy;

    public POFilterQueryBuilder() {
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
            String operator = "";
            temp = (String) conditions.next();
            if (!temp.equals("")) {
                where = where + "po.type LIKE \"%" + temp + "%\" && ";
            }
            temp = (String) conditions.next();
            if (!temp.equals("")) {
                where = where + "po.supplier LIKE \"%" + temp + "%\" && ";
            }
            temp = (String) conditions.next();
            if (!temp.equals("")) {
                where = where + "po.date LIKE \"%" + temp + "%\" && ";
            }
            temp = (String) conditions.next();
            if (!temp.equals("")) {
                operator = temp;
            }
            temp = (String) conditions.next();
            if (!temp.equals("")) {
                where = where + "po.currency LIKE \"%" + temp + "%\" && ";
            }
            temp = (String) conditions.next();
            if (!temp.equals("")) {
                if (operator.equals("")) {
                    addGroupingCondition("Sum = " + temp);
                } else {
                    addGroupingCondition("Sum " + operator + " " + temp);
                }
                having = having.substring(0, having.length() - 2);
            }
            temp = (String) conditions.next();
            if (!temp.equals("")) {
                where = where + "po.invoiceNo LIKE \"%" + temp + "%\" && ";
            }
            where = where + "po.no = pi.no && po.type = pi.type && pi.itemname = id.name && ";
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

    public String getQuery(Iterator conditions) {
        addColumn("po.no");
        addColumn("po.type");
        addColumn("po.invoiceNo");
        addColumn("po.supplier");
        addColumn("po.date");
        addColumn("SUM(pi.quantityOrdered*id.unitPrice) AS Sum");
        addTable("purchaseorder po");
        addTable("poitem pi");
        addTable("itemdata id");
        addGrouping("po.no");
        addGrouping("po.type");
        addCondition(conditions);
        System.out.println(select.substring(0, select.length() - 1) + " " + from.substring(0, from.length() - 1) + " " + where + " " + groupBy.substring(0, groupBy.length() - 1));
        return select.substring(0, (select.length() - 2)) + " " + from.substring(0, from.length() - 2) + " " + where + " " + groupBy.substring(0, groupBy.length() - 2) + " " + having;
    }
}
/*
        
 SELECT po.no, po.type, po.supplier, po.date, SUM(pi.quantityOrdered*id.unitPrice) AS Sum
 FROM purchaseorder po, poitem pi, itemdata id
 WHERE po.type="Hard" && po.supplier=”Shayane” && po.no=pi.no && pi.itemname=id.name && po.date="2015-03-04"
 GROUP BY  po.no
 HAVING Sum=1400
 */
