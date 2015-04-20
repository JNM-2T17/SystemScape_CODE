/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import model.Warranty;

/**
 *
 * @author Laptop
 */
public class WarrantyDAO implements IDBCUD {

    public Iterator get() {
        Connection con = DBConnection.getConnection();
        ArrayList<Warranty> warranties = new ArrayList<Warranty>();
        Date startDate, endDate;
        try {
            String query = "SELECT * FROM warranty";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                startDate = new java.util.Date(resultSet.getDate("startDate").getTime());
                endDate = new java.util.Date(resultSet.getDate("endDate").getTime());
                Warranty warranty = new Warranty(resultSet.getInt("hardware"), startDate, endDate);
                warranties.add(warranty);
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException sqlee) {
                sqlee.printStackTrace();
            }
        }

        return warranties.iterator();
    }

    public Object get(String key) {
        Connection con = DBConnection.getConnection();
        try {

            String query = "SELECT * FROM warranty where hardware =  ? ORDER  BY 1";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setInt(1, Integer.parseInt(key));
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Warranty warranty = new Warranty(resultSet.getInt("hardware"), resultSet.getDate("startDate"), resultSet.getDate("endDate"));

                try {
                    if(con!=null)
                    con.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

                return warranty;
            }

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException sqlee) {
                sqlee.printStackTrace();
            }
        }

        return null;
    }

    public Iterator search(String searchStr) {
        Connection con = DBConnection.getConnection();
        String strings[] = searchStr.split(" "); //assuming string format is number space period of time e.g. "10 days"
        ArrayList<Warranty> warranties = new ArrayList<Warranty>();
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        java.sql.Date date = new java.sql.Date(gregorianCalendar.getTimeInMillis());
        String dateNow = date.toString();
        Date startDate, endDate;
        int day = 0;
        if(strings[1].equalsIgnoreCase("weeks")){
            day = gregorianCalendar.get(GregorianCalendar.DAY_OF_MONTH) + (Integer.parseInt(strings[0])*7);
        }else if (strings[1].equalsIgnoreCase("months")) {//assumes 1 month == 30 days
            day = gregorianCalendar.get(GregorianCalendar.DAY_OF_MONTH) + (Integer.parseInt(strings[0])*30);
        }else if (strings[1].equalsIgnoreCase("days")) {
            day = gregorianCalendar.get(GregorianCalendar.DAY_OF_MONTH) + (Integer.parseInt(strings[0]));
        }
        
        gregorianCalendar.set(GregorianCalendar.DAY_OF_MONTH, day);
        date = new java.sql.Date(gregorianCalendar.getTimeInMillis());
        searchStr = date.toString();

        try {
            String query = "SELECT * FROM warranty WHERE endDate <= \'" + searchStr + "\' AND endDate >= \'" + dateNow + "\' ORDER BY 1";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                startDate = new java.util.Date(resultSet.getDate("startDate").getTime());
                endDate = new java.util.Date(resultSet.getDate("endDate").getTime());
                Warranty warranty = new Warranty(resultSet.getInt("hardware"), startDate, endDate);
                warranties.add(warranty);
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException sqlee) {
                sqlee.printStackTrace();
            }
        }
        return warranties.iterator();
    }

    public void add(Object object) {
        Connection con = DBConnection.getConnection();
        Warranty warranty = (Warranty) object;
        try {

            String query = "INSERT INTO warranty VALUES(?,?,?);";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setInt(1, warranty.getHardware());
            preparedStatement.setDate(2, new java.sql.Date(warranty.getStartDate().getTime()));
            preparedStatement.setDate(3, new java.sql.Date(warranty.getEndDate().getTime()));
            preparedStatement.execute();

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException sqlee) {
                sqlee.printStackTrace();
            }
        }

    }

    public void update(Object object, String origKey) {
        Connection con = DBConnection.getConnection();
        Warranty warranty = (Warranty) object;
        try {
            String query = "UPDATE warranty SET hardware = ?,startDate = ?, "
                    + "endDate= ?";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setInt(1, warranty.getHardware());
            preparedStatement.setDate(2, new java.sql.Date(warranty.getStartDate().getTime()));
            preparedStatement.setDate(3, new java.sql.Date(warranty.getEndDate().getTime()));
            preparedStatement.execute();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException sqlee) {
                sqlee.printStackTrace();
            }
        }
    }

    public void delete(Object object) {
        Connection con = DBConnection.getConnection();
        Warranty warranty = (Warranty) object;
        try {
            String query = "DELETE FROM warranty WHERE ID = ?;";
            PreparedStatement preparedStatement = con
                    .prepareStatement(query);
            preparedStatement.setInt(1, warranty.getHardware());
            preparedStatement.execute();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException sqlee) {
                sqlee.printStackTrace();
            }
        }
    }

}
