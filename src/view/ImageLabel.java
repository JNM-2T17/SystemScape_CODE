package view;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

public class ImageLabel extends JLabel {
	private ImageLabel(ImageBuilder specs) {
		super();
		
		
		this.setText("");
		this.setIcon(specs.img);
		if (specs.clr != null)
			this.setBackground(specs.clr);
		if(specs.img!=null){
			this.setPreferredSize(new Dimension(this.getIcon().getIconWidth()+10, this.getIcon().getIconHeight()));
			
		}
	}

	public static class ImageBuilder {
		private ImageIcon img;
		private Color clr;

		public ImageBuilder() {
			clr = null;
			img = null;
		}

		public ImageBuilder img(String path, int height, int width) {
			img = new ImageIcon(path);
			Image image = img.getImage();
			Image newimg = image.getScaledInstance(width, height,
					java.awt.Image.SCALE_SMOOTH);
			img = new ImageIcon(newimg);
			return this;
		}

		public ImageBuilder img(String path, double d) {
			img = new ImageIcon(path);
			Image image = img.getImage();
			Image newimg = image.getScaledInstance(
					(int) (img.getIconWidth() * d),
					(int) (img.getIconHeight() * d),
					java.awt.Image.SCALE_SMOOTH);
			img = new ImageIcon(newimg);
			return this;
		}

		public ImageLabel build() {
			return new ImageLabel(this);
		}
	}

}
