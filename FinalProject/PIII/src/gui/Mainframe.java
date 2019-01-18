package gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import api.Category;
import api.Product;
import api.Project;
import javax.swing.JTextArea;
import java.awt.Panel;
import javax.swing.JEditorPane;

/**
 * Mainframe displays all the other codes here.
 * 
 * @author Derek
 * @version 1.0
 *
 */

public class Mainframe {

	private JFrame frmDemoProgram;
	private final ActionListener action = new ButtonHandler();
	protected Project project;
	private JPasswordField passwordField;
	private String password;
	protected String userid;
	protected Integer sessionID;
	private JTextField textField;
	private AddCategory cat;
	private AddProduct prod;
	private JTextArea textArea;
	protected JTextPane textPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Project p = new Project();
					Mainframe window = new Mainframe();
					window.frmDemoProgram.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application of the mainframe.
	 * 
	 * @throws BadLocationException
	 */
	public Mainframe() throws BadLocationException {
		project = new Project();
		initialize();
		sessionID = 0;
		updateprod();
		updatecat();
	}

	protected void updatecat() {
		String cresult = "";

		for (Category c : project.back.listofCategory) {

			cresult = cresult + c.toString() + "\n";

		}

		textArea.setText(cresult);

	}

	protected void updateprod() throws BadLocationException {
		StyledDocument doc = (StyledDocument) textPane.getDocument();
		for (Product p : project.back.listofProduct) {
			Style style = doc.addStyle("style", null);
			StyleConstants.setIcon(style, new ImageIcon(p.getImagePath()));
			doc.insertString(doc.getLength(), "ignored", style);
			SimpleAttributeSet set = new SimpleAttributeSet();
			doc.insertString(doc.getLength(), p.toString(), set);

		}

	}

	/**
	 * Initialize the contents of the frame.
	 * 
	 * @throws BadLocationException
	 */
	private void initialize() {
		frmDemoProgram = new JFrame();
		frmDemoProgram.setTitle("Demo Program");
		frmDemoProgram.setBounds(100, 100, 900, 770);
		frmDemoProgram.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmDemoProgram.getContentPane().setLayout(null);

		JLabel lblUserId = new JLabel("User ID");
		lblUserId.setBounds(70, 10, 50, 20);
		frmDemoProgram.getContentPane().add(lblUserId);

		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(70, 40, 60, 20);
		frmDemoProgram.getContentPane().add(lblPassword);

		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(action);
		btnLogin.setBounds(300, 10, 120, 30);
		frmDemoProgram.getContentPane().add(btnLogin);

		JButton btnLogout = new JButton("Logout");
		btnLogout.addActionListener(action);
		btnLogout.setBounds(300, 40, 120, 30);
		frmDemoProgram.getContentPane().add(btnLogout);

		JLabel lblNotAUser = new JLabel("Not a User?");
		lblNotAUser.setBounds(120, 70, 80, 20);
		frmDemoProgram.getContentPane().add(lblNotAUser);

		JButton btnReigister = new JButton("Register");
		btnReigister.addActionListener(action);
		btnReigister.setBounds(210, 65, 120, 30);
		frmDemoProgram.getContentPane().add(btnReigister);

		JButton btnAddCategory = new JButton("Add Category");
		btnAddCategory.addActionListener(action);
		btnAddCategory.setBounds(40, 480, 117, 29);
		frmDemoProgram.getContentPane().add(btnAddCategory);

		JButton btnAddProduct = new JButton("Add Product");
		btnAddProduct.addActionListener(action);
		btnAddProduct.setBounds(40, 525, 120, 30);
		frmDemoProgram.getContentPane().add(btnAddProduct);

		JButton btnSeeInvoices = new JButton("See Invoices");
		btnSeeInvoices.addActionListener(action);
		btnSeeInvoices.setBounds(250, 525, 120, 30);
		frmDemoProgram.getContentPane().add(btnSeeInvoices);

		JButton btnSeeShoppingCart = new JButton("See Shopping Cart");
		btnSeeShoppingCart.addActionListener(action);
		btnSeeShoppingCart.setBounds(250, 480, 160, 30);
		frmDemoProgram.getContentPane().add(btnSeeShoppingCart);

		JButton btnAddDistributionCentre = new JButton("Add Distribution Centre");
		btnAddDistributionCentre.addActionListener(action);
		btnAddDistributionCentre.setBounds(40, 570, 185, 30);
		frmDemoProgram.getContentPane().add(btnAddDistributionCentre);

		JButton btnMaintainQuantity = new JButton("Maintain Quantity");
		btnMaintainQuantity.addActionListener(action);
		btnMaintainQuantity.setBounds(40, 690, 155, 30);
		frmDemoProgram.getContentPane().add(btnMaintainQuantity);

		JButton btnAddRoute = new JButton("Add Route");
		btnAddRoute.addActionListener(action);
		btnAddRoute.setBounds(40, 610, 120, 30);
		frmDemoProgram.getContentPane().add(btnAddRoute);

		JButton btnProduce = new JButton("Produce Sales Report");
		btnProduce.addActionListener(action);
		btnProduce.setBounds(40, 650, 185, 30);
		frmDemoProgram.getContentPane().add(btnProduce);

		JLabel lblIfYouAre = new JLabel("If you are an admin");
		lblIfYouAre.setBounds(40, 450, 130, 20);
		frmDemoProgram.getContentPane().add(lblIfYouAre);

		JLabel lblIfYouAre_1 = new JLabel("If you are a shopper");
		lblIfYouAre_1.setBounds(250, 450, 130, 20);
		frmDemoProgram.getContentPane().add(lblIfYouAre_1);

		passwordField = new JPasswordField();
		passwordField.setBounds(140, 40, 130, 25);
		frmDemoProgram.getContentPane().add(passwordField);

		JLabel lblDesignedBy = new JLabel("Designed by Yang Wang,Xiao Wang,Jiayu Li,Yufeng Li");
		lblDesignedBy.setForeground(Color.RED);
		lblDesignedBy.setFont(new Font("Papyrus", Font.ITALIC, 10));
		lblDesignedBy.setBounds(90, 730, 355, 20);
		frmDemoProgram.getContentPane().add(lblDesignedBy);

		textField = new JTextField();
		textField.setBounds(140, 10, 130, 25);
		frmDemoProgram.getContentPane().add(textField);
		textField.setColumns(10);

		JButton btnBuyProduct = new JButton("Manage Product In Shopping Cart");
		btnBuyProduct.setBounds(250, 570, 250, 30);
		btnBuyProduct.addActionListener(action);
		frmDemoProgram.getContentPane().add(btnBuyProduct);

		JLabel lblProducts1 = new JLabel("Products");
		lblProducts1.setBounds(460, 15, 60, 15);
		frmDemoProgram.getContentPane().add(lblProducts1);

		JLabel lblCategories = new JLabel("Categories");
		lblCategories.setBounds(59, 99, 71, 16);
		frmDemoProgram.getContentPane().add(lblCategories);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(40, 120, 355, 280);
		frmDemoProgram.getContentPane().add(scrollPane);

		textArea = new JTextArea();
		scrollPane.setViewportView(textArea);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane_1.setBounds(500, 40, 380, 700);
		frmDemoProgram.getContentPane().add(scrollPane_1);

		textPane = new JTextPane();
		textPane.setEditable(false);
		scrollPane_1.setViewportView(textPane);

	}

	/**
	 * The ActionListener of this button of the frame.
	 */
	private class ButtonHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if (e.getActionCommand().equals("Register")) {
				Register dataScreen = new Register(project);
				dataScreen.pack();
				dataScreen.setVisible(true);
			}
			if (e.getActionCommand().equals("Add Category")) {
				if (sessionID != 0) {
					cat = new AddCategory(project, sessionID, Mainframe.this);
					cat.pack();
					cat.setVisible(true);
				} else {
					JFrame j = new JFrame();
					JOptionPane.showMessageDialog(j, "Sorry, please login");
				}
			}
			if (e.getActionCommand().equals("Add Product")) {
				if (sessionID != 0) {
					prod = new AddProduct(project, sessionID, Mainframe.this);
					prod.pack();
					prod.setVisible(true);
				} else {
					JFrame j = new JFrame();
					JOptionPane.showMessageDialog(j, "Sorry, please login");
				}
			}
			if (e.getActionCommand().equals("Add Distribution Centre")) {
				if (sessionID != 0) {
					AddDistriCen dataScreen = new AddDistriCen(project, sessionID);
					dataScreen.pack();
					dataScreen.setVisible(true);
				} else {
					JFrame j = new JFrame();
					JOptionPane.showMessageDialog(j, "Sorry, please login");
				}
			}
			if (e.getActionCommand().equals("Add Route")) {
				if (sessionID != 0) {
					AddRoute dataScreen = new AddRoute(project, sessionID);
					dataScreen.pack();
					dataScreen.setVisible(true);
				} else {
					JFrame j = new JFrame();
					JOptionPane.showMessageDialog(j, "Sorry, please login");
				}
			}
			if (e.getActionCommand().equals("See Shopping Cart")) {
				if (sessionID != 0) {
					ShoppingCartDisplay dataScreen = new ShoppingCartDisplay(project, sessionID);
					dataScreen.frmShoppingCart.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
					dataScreen.frmShoppingCart.setVisible(true);
				} else {
					JFrame j = new JFrame();
					JOptionPane.showMessageDialog(j, "Sorry, please login");
				}
			}
			if (e.getActionCommand().equals("Login")) {
				password = new String(passwordField.getPassword());
				userid = textField.getText();
				sessionID = project.login(userid, password);
				JFrame j = new JFrame();
				JOptionPane.showMessageDialog(j, "Your session id is " + sessionID.toString());
			}
			if (e.getActionCommand().equals("Logout")) {
				if (sessionID != 0) {
					project.logout(sessionID);
					sessionID = 0;
				}
			}
			if (e.getActionCommand().equals("See Invoices")) {
				if (sessionID != 0) {
				} else {
					JFrame j = new JFrame();
					JOptionPane.showMessageDialog(j, "Sorry, please login");
				}
			}
			if (e.getActionCommand().equals("Manage Product In Shopping Cart")) {
				if (sessionID != 0) {
					AddProductSC buy = new AddProductSC(project, sessionID);
					buy.pack();
					buy.setVisible(true);
				} else {
					JFrame j = new JFrame();
					JOptionPane.showMessageDialog(j, "Sorry, please login");
				}
			}
			if (e.getActionCommand().equals("Maintain Quantity")) {
				if (sessionID != 0) {
					MQ dataScreen = new MQ(project, sessionID);
					dataScreen.pack();
					dataScreen.setVisible(true);
				} else {
					JFrame j = new JFrame();
					JOptionPane.showMessageDialog(j, "Sorry, please login");
				}
			}
			if (e.getActionCommand().equals("Produce Sales Report")) {
				if (sessionID != 0) {
					SalesReport dataScreen = new SalesReport(project, sessionID);
					dataScreen.frmSalesReport.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
					dataScreen.frmSalesReport.setVisible(true);
				} else {
					JFrame j = new JFrame();
					JOptionPane.showMessageDialog(j, "Sorry, please login");
				}
			}
		}

	}
}
