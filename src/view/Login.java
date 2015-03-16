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
import javax.swing.border.LineBorder;
import model.User;

import view.employee.TabEmployees;
import view.inventory.TabInventory;
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

    public Login(Gui parent) {
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
        lblLogIn.setFont(new Font("Tahoma", Font.PLAIN, 24));
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
        txtUsername.setText("Username");
        txtUsername.setBounds(21, 25, 320, 30);
        panContent.add(txtUsername);
        txtUsername.setColumns(10);

        pwdPassword = new JPasswordField();
        pwdPassword.setText("Password");
        pwdPassword.setBounds(21, 66, 320, 30);
        panContent.add(pwdPassword);

        btnLogin = new JButton("Login");
        btnLogin.setForeground(Color.WHITE);
        btnLogin.setFont(new Font("Tahoma", Font.PLAIN, 18));
        btnLogin.setBackground(new Color(32, 130, 213));
        btnLogin.setBorder(null);
        btnLogin.setBounds(21, 112, 320, 30);
        btnLogin.addActionListener(this);
        panContent.add(btnLogin);

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
