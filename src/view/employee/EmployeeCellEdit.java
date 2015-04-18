/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.employee;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.Employee;
import view.CellEdit;

/**
 *
 * @author Laptop
 */
class EmployeeCellEdit extends CellEdit implements ActionListener{

    private TabEmployees tab;
    private Employee employee;
    
    public EmployeeCellEdit(Employee employee, TabEmployees tab){
		super();
		this.tab=tab;
		this.employee=employee;
		
		
		if(tab==null) System.out.println("TAB NUUUUL");
		else System.out.println("WUUUUUUUUUUUUUT");
		
		if(employee==null) System.out.println("NUOOOO");
		else{
			System.out.println("YEEEEEY");
		}
		
		if(employee==null){
			clear();
		}
	}
	
	public Object get(){
		return employee;
	}
        
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==getBtnEdit()){
			if(tab==null) System.out.println("ACT TAB NUUUUL");
			tab.setEdit(employee);
		}
		else if(e.getSource()==getBtnView()){
			/*if(tab==null) System.out.println("ACT TAB NUUUUL");*/
			tab.setView(employee);
		}
		
	}
    
}
