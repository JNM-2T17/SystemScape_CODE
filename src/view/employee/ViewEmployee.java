package view.employee;

import view.ViewTemplate;

public class ViewEmployee extends ViewTemplate{

	@Override
	public void initialize() {
		// TODO Auto-generated method stub
		setColCount(5);
		String headers[]={"Name", "Status", "Projects", "Project Date", ""};
		getModel().setColumnIdentifiers(headers);
		setColWidth(0, 500);
		setColWidth(1, 220);
		setColWidth(2, 15);
		setColRendEdit(4);
	}

	@Override
	public void refresh() {
		// TODO Auto-generated method stub
		
	}

}
