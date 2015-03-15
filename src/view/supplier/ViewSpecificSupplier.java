package view.supplier;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

public class ViewSpecificSupplier extends JPanel{
	
	private JPanel panContent, panWest, panEast, panSupplier, panCenterInfo;
	private JLabel lblSupplier, lblSupplierValue, lblAddressValue;
	private JPanel panTable;
	private JLabel lblAddress;
	private JLabel lblCountry;
	private JLabel lblCountryValue;
	private JLabel lblState;
	private JLabel lblStateValue;
	private JLabel lblCity;
	private JLabel lblCityValue;
	private JLabel lblContacts;
	private JPanel panContacts;
	
	public ViewSpecificSupplier() {
		setLayout(new BorderLayout(0, 0));
		
		panContent = new JPanel();
		add(panContent);
		panContent.setLayout(new BorderLayout(0, 0));
		
		panSupplier = new JPanel();
		panContent.add(panSupplier, BorderLayout.NORTH);
		panSupplier.setLayout(new BorderLayout(0, 0));
		
		lblSupplier = new JLabel("Supplier :");
		lblSupplier.setFont(new Font("Arial", Font.PLAIN, 22));
		panSupplier.add(lblSupplier, BorderLayout.WEST);
		
		lblSupplierValue = new JLabel("random ");
		lblSupplierValue.setFont(new Font("Arial", Font.PLAIN, 22));
		panSupplier.add(lblSupplierValue, BorderLayout.CENTER);
		
		panCenterInfo = new JPanel();
		panContent.add(panCenterInfo, BorderLayout.SOUTH);
		panCenterInfo.setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("120px"),
				ColumnSpec.decode("135px:grow"),
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				ColumnSpec.decode("10px"),},
			new RowSpec[] {
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("52px"),
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),}));
		
		lblAddress = new JLabel("Address :");
		lblAddress.setFont(new Font("Arial", Font.PLAIN, 22));
		panCenterInfo.add(lblAddress, "1, 2");
		
		lblCountry = new JLabel("Country :");
		lblCountry.setFont(new Font("Arial", Font.PLAIN, 22));
		panCenterInfo.add(lblCountry, "2, 4");
		
		lblCountryValue = new JLabel("Random");
		lblCountryValue.setFont(new Font("Arial", Font.PLAIN, 22));
		panCenterInfo.add(lblCountryValue, "4, 4, center, default");
		
		lblState = new JLabel("State :");
		lblState.setFont(new Font("Arial", Font.PLAIN, 22));
		panCenterInfo.add(lblState, "2, 6");
		
		lblStateValue = new JLabel("Random");
		lblStateValue.setFont(new Font("Arial", Font.PLAIN, 22));
		panCenterInfo.add(lblStateValue, "4, 6, center, default");
		
		lblCity = new JLabel("City :");
		lblCity.setFont(new Font("Arial", Font.PLAIN, 22));
		panCenterInfo.add(lblCity, "2, 8");
		
		lblCityValue = new JLabel("Random");
		lblCityValue.setFont(new Font("Arial", Font.PLAIN, 22));
		panCenterInfo.add(lblCityValue, "4, 8, center, default");
		
		lblContacts = new JLabel("Contacts :");
		lblContacts.setFont(new Font("Arial", Font.PLAIN, 22));
		panCenterInfo.add(lblContacts, "1, 10");
		
		
		
		
		
		panWest = new JPanel();
		add(panWest, BorderLayout.WEST);
		
		panEast = new JPanel();
		add(panEast, BorderLayout.EAST);
		//panContacts.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
	}

		public void setSupplierContacts(JPanel contactsTablePanel)
		{
			panContacts = contactsTablePanel;
			panContacts.setBackground(Color.CYAN);
			panCenterInfo.add(panContacts, "2, 12, 3, 1, fill, fill");
		}
}
