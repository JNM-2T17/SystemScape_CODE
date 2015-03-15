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
public class QueryFilterDirector {

    private FilterQueryBuilder builder;

    public QueryFilterDirector(FilterQueryBuilder builder) {
        this.builder = builder;
    }

    public String getQuery(Iterator conditions) {
        return builder.getQuery(conditions);
    }
}
