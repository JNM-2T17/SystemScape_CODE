package view.employee;

import view.PanelCell;
import view.ViewTemplate;

public class ViewEmployee extends ViewTemplate{

	@Override
	public void initialize() {
		// TODO Auto-generated method stub
		setColCount(5);
		String headers[]={"Name", "Status", "Projects", "Project Date", ""};
		getModel().setColumnIdentifiers(headers);
		setColRendEdit(new PanelCell(), new PanelCell());
	}

	@Override
	public void refresh() {
		// TODO Auto-generated method stub
		
	}

}
