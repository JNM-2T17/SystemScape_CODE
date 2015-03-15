/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.ArrayList;
import java.util.Iterator;
import model.Contract;
import model.database.DAO;
import view.Observer;
import view.Subject;

/**
 *
 * @author Laptop
 */
public class ContractController implements Subject{

    private static ContractController instance;
    private DAO dao;
    private Contract contract;
    private ArrayList<Observer> observerList;

    public ContractController() {
        dao = DAO.getInstance();
        contract = new Contract();
        observerList = new ArrayList();
    }
    
    public static ContractController getInstance() {
        if (instance == null) {
            instance = new ContractController();
        }
        return instance;
    }
    
     public Iterator search(String searchStr){
        return dao.search("Contract", searchStr);
    }
    

    public Contract getWarranty() {
        return contract;
    }
    
    public Object getObject(String key){
        return dao.get("contract", key);
    }

    @Override
    public void registerObserver(Observer o) {
        o.update();
        observerList.add(o);
    }

    @Override
    public void unregisterObserver(Observer o) {
        // TODO Auto-generated method stub
        observerList.remove(o);
    }

    @Override
    public void notifyObserver() {
        // TODO Auto-generated method stub
        for (Observer o : observerList) {
            o.update();
        }
    }
    
    public Iterator getAll(){
        return dao.get("Contract");
    }
    
    public void addContract(Contract contract) {
		// TODO Auto-generated method stub
        // Supplier supplier = (Supplier) dao.get("Supplier", supplierName);
        this.contract.setHardware(contract.getHardware());
        this.contract.setStartDate(contract.getStartDate());
        this.contract.setEndDate(contract.getEndDate());

        dao.add("Contract", this.contract);
    }

    
    public void editContract(Contract contract) {
		// TODO Auto-generated method stub

    }
}
