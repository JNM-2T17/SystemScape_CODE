package view;

import controller.UserController;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;

import model.User;
import view.employee.TabEmployees;
import view.inventory.TabInventory;
import view.project.TabProject;
import view.purchaseOrder.TabPO;
import view.supplier.TabSupplier;

public class Login extends JPanel implements ActionListener {

    private JPanel panMain;
    private JPanel panClose;
    private JButton close;
    private JPanel panLogin;
    private JPanel panHeader;
    private JPanel panCenter;
    private JLabel lblLogIn;
    private JPanel panContent;
    private JTextField txtUsername;
    private JPasswordField pwdPassword;
    private JButton btnLogin;
    private Gui parent;
    private JPanel panBottom;
    private JPanel panLeft;
    private JPanel panRight;
    private UserController userController;
    private JLabel lblUsername;
    private JLabel lblPassword;

    public Login(Gui parent) {
    	
    

    	UIManager.put("Button.font", new Font("Arial",Font.PLAIN,14)/* font of your liking */);
    	UIManager.put("ToggleButton.font", new Font("Arial",Font.PLAIN,14)/* font of your liking */);
    	UIManager.put("RadioButton.font", new Font("Arial",Font.PLAIN,14)/* font of your liking */);
    	UIManager.put("CheckBox.font", new Font("Arial",Font.PLAIN,14)/* font of your liking */);
    	UIManager.put("ColorChooser.font", new Font("Arial",Font.PLAIN,14)/* font of your liking */);
    	UIManager.put("ComboBox.font", new Font("Arial",Font.PLAIN,14)/* font of your liking */);
    	UIManager.put("Label.font", new Font("Arial",Font.PLAIN,14)/* font of your liking */);
    	UIManager.put("List.font", new Font("Arial",Font.PLAIN,14)/* font of your liking */);
    	UIManager.put("MenuBar.font", new Font("Arial",Font.PLAIN,14)/* font of your liking */);
    	UIManager.put("MenuItem.font", new Font("Arial",Font.PLAIN,14)/* font of your liking */);
    	UIManager.put("RadioButtonMenuItem.font", new Font("Arial",Font.PLAIN,14)/* font of your liking */);
    	UIManager.put("CheckBoxMenuItem.font", new Font("Arial",Font.PLAIN,14)/* font of your liking */);
    	UIManager.put("Menu.font", new Font("Arial",Font.PLAIN,14)/* font of your liking */);
    	UIManager.put("PopupMenu.font", new Font("Arial",Font.PLAIN,14)/* font of your liking */);
    	UIManager.put("OptionPane.font", new Font("Arial",Font.PLAIN,14)/* font of your liking */);
    	UIManager.put("Panel.font", new Font("Arial",Font.PLAIN,14)/* font of your liking */);
    	UIManager.put("ProgressBar.font", new Font("Arial",Font.PLAIN,14)/* font of your liking */);
    	UIManager.put("ScrollPane.font", new Font("Arial",Font.PLAIN,14)/* font of your liking */);
    	UIManager.put("Viewport.font", new Font("Arial",Font.PLAIN,14)/* font of your liking */);
    	UIManager.put("TabbedPane.font", new Font("Arial",Font.PLAIN,14)/* font of your liking */);
    	UIManager.put("Table.font", new Font("Arial",Font.PLAIN,14)/* font of your liking */);
    	UIManager.put("TableHeader.font", new Font("Arial",Font.PLAIN,14)/* font of your liking */);
    	UIManager.put("TextField.font", new Font("Arial",Font.PLAIN,14)/* font of your liking */);
    	UIManager.put("PasswordField.font", new Font("Arial",Font.PLAIN,14)/* font of your liking */);
    	UIManager.put("TextArea.font", new Font("Arial",Font.PLAIN,14)/* font of your liking */);
    	UIManager.put("TextPane.font", new Font("Arial",Font.PLAIN,14)/* font of your liking */);
    	UIManager.put("EditorPane.font", new Font("Arial",Font.PLAIN,14)/* font of your liking */);
    	UIManager.put("TitledBorder.font", new Font("Arial",Font.PLAIN,14)/* font of your liking */);
    	UIManager.put("ToolBar.font", new Font("Arial",Font.PLAIN,14)/* font of your liking */);
    	UIManager.put("ToolTip.font", new Font("Arial",Font.PLAIN,14)/* font of your liking */);
    	UIManager.put("Tree.font", new Font("Arial",Font.PLAIN,14)/* font of your liking */);
    	
    	
        setPreferredSize(new Dimension(1500, 1000));
        this.parent = parent;
        // try {
        // for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
        // if ("Nimbus".equals(info.getName())) {
        // UIManager.setLookAndFeel(info.getClassName());
        // break;
        // }
        // }
        // } catch (Exception e) {
        // e.printStackTrace();
        // }

        this.setLayout(new BorderLayout());

        panMain = new JPanel();
        panMain.setBackground(new Color(32, 130, 213));
        this.add(panMain, BorderLayout.NORTH);
        panMain.setLayout(new BorderLayout(0, 0));

        JLabel logo = new JLabel(" ");
        ImageIcon ii = new ImageIcon("src/assets/logo.png");
        Image img = ii.getImage();
        Image newimg = img.getScaledInstance((int) (ii.getIconWidth() * 0.5),
                (int) (ii.getIconHeight() * 0.5), java.awt.Image.SCALE_SMOOTH);
        ii = new ImageIcon(newimg);
        logo.setIcon(ii);

        panMain.add(logo, BorderLayout.WEST);

        panClose = new JPanel();
        panClose.setBackground(new Color(32, 130, 213));
        panMain.add(panClose, BorderLayout.EAST);

        close = new Button.ButtonBuilder().img("src/assets/Metro/Delete.png",
                30, 30).build();
        close.addActionListener(this);
        panClose.add(close);

        panCenter = new JPanel();
        panCenter.setBackground(Color.WHITE);
        this.add(panCenter, BorderLayout.CENTER);
        GridBagLayout gbl_panCenter = new GridBagLayout();
        panCenter.setLayout(gbl_panCenter);

        panLogin = new JPanel();
        panLogin.setBorder(new LineBorder(new Color(32, 130, 213), 1, true));
        panLogin.setPreferredSize(new Dimension(370, 200));
        GridBagConstraints gbc_panLogin = new GridBagConstraints();
        gbc_panLogin.gridx = 0;
        panCenter.add(panLogin, gbc_panLogin);
        panLogin.setLayout(new BorderLayout(0, 0));

        panHeader = new JPanel();
        panHeader.setPreferredSize(new Dimension(10, 40));
        panHeader.setBackground(new Color(32, 130, 213));
        panLogin.add(panHeader, BorderLayout.NORTH);
        GridBagLayout gbl_panHeader = new GridBagLayout();
        gbl_panHeader.columnWeights = new double[]{1.0};
        panHeader.setLayout(gbl_panHeader);

        lblLogIn = new JLabel("Log In");
        lblLogIn.setForeground(Color.WHITE);
        lblLogIn.setFont(new Font("Arial", Font.PLAIN, 20));
        GridBagConstraints gbc_lblLogIn = new GridBagConstraints();
        gbc_lblLogIn.insets = new Insets(0, 0, 5, 0);
        gbc_lblLogIn.gridx = 0;
        gbc_lblLogIn.gridy = 0;
        panHeader.add(lblLogIn, gbc_lblLogIn);

        panContent = new JPanel();
        panContent.setBackground(Color.WHITE);
        panLogin.add(panContent, BorderLayout.CENTER);
        panContent.setLayout(null);

        txtUsername = new JTextField();
        txtUsername.setBounds(110, 25, 237, 30);
        panContent.add(txtUsername);
        txtUsername.setColumns(10);

        pwdPassword = new JPasswordField();
        pwdPassword.setBounds(110, 66, 237, 30);
        panContent.add(pwdPassword);

        btnLogin = new JButton("Login");
        btnLogin.setForeground(Color.WHITE);
        btnLogin.setFont(new Font("Arial", Font.PLAIN, 16));
        btnLogin.setBackground(new Color(32, 130, 213));
        btnLogin.setBorder(null);
        btnLogin.setBounds(21, 112, 326, 30);
        btnLogin.addActionListener(this);
        panContent.add(btnLogin);
        
        lblUsername = new JLabel("Username:");
        lblUsername.setBounds(21, 33, 79, 14);
        panContent.add(lblUsername);
        
        lblPassword = new JLabel("Password:");
        lblPassword.setBounds(21, 74, 79, 14);
        panContent.add(lblPassword);

        panBottom = new JPanel();
        panBottom.setBackground(new Color(32, 130, 213));
        add(panBottom, BorderLayout.SOUTH);

        panLeft = new JPanel();
        panLeft.setBackground(new Color(32, 130, 213));
        add(panLeft, BorderLayout.WEST);

        panRight = new JPanel();
        panRight.setBackground(new Color(32, 130, 213));
        add(panRight, BorderLayout.EAST);

        userController = UserController.getInstance();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnLogin) {
            User user = (User) userController.getUser(txtUsername.getText());
  
            if (user != null) {
                if (txtUsername.getText().equals(user.getUsername()) && new String(pwdPassword.getPassword()).equals(user.getPassword())) {
                    MainPanel mp = new MainPanel(parent);
                    parent.register(mp, "main");
                    if (user.isManager()) {
                        mp.addTab(new TabPO(parent), "Purchase Orders");
                        mp.addTab(new TabSupplier(parent), "Suppliers");
                        mp.addTab(new TabInventory(parent), "Inventory");
                        mp.addTab(new TabEmployees(parent), "Employees");
                        mp.addTab(new TabProject(parent), "Projects");
                    } else {
                        mp.addTab(new TabInventory(parent), "Inventory");
                        mp.enableNotif(false);
                    }
                    parent.setPanel("main");
                } else {
                    Message msg = new Message(parent, Message.ERROR, "Username and password don't match!");
                }
            } else {
                Message msg = new Message(parent, Message.ERROR, "User doesn't exist!");
            }
        } else if (e.getSource() == close) {
            System.exit(0);
        }
    }
}
