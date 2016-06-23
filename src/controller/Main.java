package controller;
import view.Gui;
import view.Login;


public class Main {
	public static void main(String[] args){
		Gui gui=new Gui();
		//System.out.println("hello");
		gui.register(new Login(gui), "login");
		
		gui.setPanel("login");
		//System.out.println("hi");
	}
}
