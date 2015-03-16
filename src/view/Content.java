package view;
import javax.swing.JPanel;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Font;
import java.awt.Dimension;

public class Content extends JPanel {
	private JPanel panTop, panCaption, panOption;
	private JButton btnBack, btnAdd, btnFilter, btnExport, btnDelete;
	private JLabel lblCaption;
	private JPanel panContent;

	private Content(ContentBuilder cb) {

		setLayout(new BorderLayout(0, 0));
		setBackground(Color.WHITE);
		
		panTop = new JPanel();
		panTop.setBackground(Color.WHITE);
		add(panTop, BorderLayout.NORTH);
		panTop.setLayout(new BorderLayout(0, 0));

		panCaption = new JPanel();
		panCaption.setBackground(Color.WHITE);
		panTop.add(panCaption, BorderLayout.WEST);

		btnBack = new Button.ButtonBuilder().img("src/assets/Round/In.png", 35,
				35).build();
		btnBack.setVisible(cb.back);
		btnBack.setActionCommand("back");
		panCaption.add(btnBack);

		JLabel lblCaption = new JLabel(cb.caption);
		lblCaption.setPreferredSize(new Dimension(500, 35));
		lblCaption.setOpaque(true);
		lblCaption.setFont(new Font("Arial", Font.PLAIN, 14));
		lblCaption.setBackground(new Color(246, 246, 246));
		panCaption.add(lblCaption);

		panOption = new JPanel();
		panOption.setBackground(Color.WHITE);
		panTop.add(panOption, BorderLayout.EAST);

		JLabel lblDelete = new JLabel("Delete");
		lblDelete.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDelete.setForeground(new Color(139, 0, 0));
		lblDelete.setVisible(cb.delete);
		panOption.add(lblDelete);

		btnDelete = new Button.ButtonBuilder().img(
				"src/assets/Round/Delete.png", 35, 35).build();
		btnDelete.setVisible(cb.delete);
		btnDelete.setActionCommand("delete");
		panOption.add(btnDelete);

		btnAdd = new Button.ButtonBuilder().img("src/assets/Round/Add.png", 35,35).build();
		btnAdd.setVisible(cb.add);
		btnAdd.setActionCommand("add");
		panOption.add(btnAdd);

		btnFilter = new Button.ButtonBuilder().img("src/assets/Round/Find.png",35, 35).build();
		btnFilter.setVisible(cb.filter);
		btnFilter.setActionCommand("filter");
		panOption.add(btnFilter);

		btnExport = new Button.ButtonBuilder().img("src/assets/Round/Export.png", 35, 35).build();
		btnExport.setVisible(cb.export);
		btnExport.setActionCommand("export");
		panOption.add(btnExport);

		panContent = cb.content;
		add(panContent, BorderLayout.CENTER);
	}
	
        public JPanel getContent(){
            return panContent;
        }
	

	public JButton getBtnBack() {
		return btnBack;
	}



	public JButton getBtnAdd() {
		return btnAdd;
	}



	public JButton getBtnFilter() {
		return btnFilter;
	}



	public JButton getBtnExport() {
		return btnExport;
	}



	public JButton getBtnDelete() {
		return btnDelete;
	}



	public static class ContentBuilder {
		private String caption;
		private boolean back;
		private boolean export;
		private boolean filter;
		private boolean add;
		private boolean delete;
		private JPanel content;

		public ContentBuilder() {
			caption = "Rissa Rocks";
			delete = false;
			back = false;
			export = false;
			add = false;
			filter = false;
			content = new JPanel();
		}

		public ContentBuilder back(boolean stat) {
			back = stat;
			return this;
		}

		public ContentBuilder delete(boolean stat) {
			delete = stat;
			return this;
		}

		public ContentBuilder export(boolean stat) {
			export = stat;
			return this;
		}

		public ContentBuilder filter(boolean stat) {
			filter = stat;
			return this;
		}

		public ContentBuilder add(boolean stat) {
			add = stat;
			return this;
		}

		public ContentBuilder content(JPanel cont) {
			content = cont;
			return this;
		}

		public ContentBuilder caption(String text) {
			caption = text;
			return this;
		}

		public Content build() {
			return new Content(this);
		}
	}

}
