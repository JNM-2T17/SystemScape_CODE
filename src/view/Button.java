package view;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class Button extends JButton {
	private Button(ButtonBuilder specs) {
		super();
		
		
		this.setText(specs.text);
		this.setIcon(specs.img);
		if (specs.clr != null)
			this.setBackground(specs.clr);
		this.setContentAreaFilled(false);
		this.setBorderPainted(false);
		if(specs.text.equals("")&&specs.img!=null){
			this.setPreferredSize(new Dimension(this.getIcon().getIconWidth()+10, this.getIcon().getIconHeight()));
			
		}
	}

	public static class ButtonBuilder {
		private ImageIcon img;
		private String text;
		private Color clr;

		public ButtonBuilder() {
			text="";
			clr = null;
			img = null;
		}

		public ButtonBuilder img(String path, int height, int width) {
			img = new ImageIcon(path);
			Image image = img.getImage();
			Image newimg = image.getScaledInstance(width, height,
					java.awt.Image.SCALE_SMOOTH);
			img = new ImageIcon(newimg);
			return this;
		}

		public ButtonBuilder img(String path, double d) {
			img = new ImageIcon(path);
			Image image = img.getImage();
			Image newimg = image.getScaledInstance(
					(int) (img.getIconWidth() * d),
					(int) (img.getIconHeight() * d),
					java.awt.Image.SCALE_SMOOTH);
			img = new ImageIcon(newimg);
			return this;
		}

		public ButtonBuilder text(String text) {
			this.text = text;
			return this;
		}

		public Button build() {
			return new Button(this);
		}
	}

}
//