/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.ArrayList;
import java.util.Iterator;
import model.Employee;
import model.Supplier;
import model.SupplierContact;
import model.database.DAO;
import view.Observer;
import view.Subject;

/**
 *
 * @author Christian Gabriel
 */
public class EmployeeController implements Subject {

    private static EmployeeController instance;
    private DAO dao;
    private Employee employee;
    private ArrayList<Observer> observerList;

    public EmployeeController() {
        dao = DAO.getInstance();
        observerList = new ArrayList();
    }
    
    public static EmployeeController getInstance() {
        if (instance == null) {
            instance = new EmployeeController();
        }
        return instance;
    }

    public Iterator filter(Iterator conditions) {
        return dao.filter("Employee", conditions);
    }
    
    public Iterator getDistinct(String string){
        return dao.getDistinct("Employee", string);
    }

    public void addEmployee(Employee emp) {
        // TODO Auto-generated method stub
        employee.setName(emp.getName());
        employee.setID(emp.getID());

        dao.add("Employee", employee);
        notifyObserver();
    }

    public Object getObject(String key) {
        return dao.get("Employee", key);
    }

    public Iterator getAll() {
        return dao.get("Employee");
    }

    @Override
    public void registerObserver(Observer o) {
        o.update();
        observerList.add(o);
    }

    @Override
    public void unregisterObserver(Observer o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void notifyObserver() {
        for (Observer o : observerList) {
            o.update();
        }
    }

}
