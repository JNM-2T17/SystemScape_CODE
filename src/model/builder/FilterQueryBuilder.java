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
public interface FilterQueryBuilder {

    public void addColumn(String column);

    public void addTable(String table);

    public void addCondition(Iterator conditions);

    public void addGrouping(String group);

    public void addGroupingCondition(String groupCondition);

    public void addOrderCriteria(String orderCriteria);

    public String getQuery(Iterator conditions);
    
}
