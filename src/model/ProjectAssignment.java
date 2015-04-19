package model;

public class ProjectAssignment {
	
	private String project;
	private int employeeID;
	
	public ProjectAssignment(String project, int employeeID){
		setProject(project);
		setEmployeeID(employeeID);
	}
	
	public void setProject(String project){
		this.project = project;
	}
	
	public void setEmployeeID(int employeeID){
		this.employeeID = employeeID;
	}
	
	public int getEmployeeID(){
		return employeeID;
	}
	
	public String getProject(){
		return project;
	}
	

}
