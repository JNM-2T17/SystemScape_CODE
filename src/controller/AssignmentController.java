/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.ArrayList;
import java.util.Iterator;
import model.Employee;
import model.ITAsset;
import model.Supplier;
import model.SupplierContact;
import model.database.DAO;
import view.Observer;
import view.Subject;

/**
 *
 * @author Christian Gabriel
 */
public class AssignmentController implements Subject {

    private static AssignmentController instance;
    private DAO dao;
    private ArrayList<Observer> observerList;

    public AssignmentController() {
        dao = DAO.getInstance();
        observerList = new ArrayList();
    }
    
    
    public static AssignmentController getInstance() {
        if (instance == null) {
            instance = new AssignmentController();
        }
        return instance;
    }

    public Iterator filter(Iterator conditions) {
        return dao.filter("ITAsset", conditions);
    }
    
    public Iterator getDistinct(String string){
        return dao.getDistinct("ITAsset", string);
    }

    public void addITAsset(ITAsset itAsset) {
        // TODO Auto-generated method stub

        dao.add("ITAsset", itAsset);
        notifyObserver();
    }

    public Object getObject(String key) {
        return dao.get("assignment", key);
    }

    public Iterator getAll() {
        return dao.get("assignment");
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
