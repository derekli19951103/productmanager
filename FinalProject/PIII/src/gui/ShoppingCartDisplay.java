package gui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextPane;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import api.Project;
import javax.swing.JTextArea;

/**
 * ShoppingCartDisplay displays the status of shopping cart. It's information
 * includes:
 * <ul>
 * <li>project the project
 * <li>sessionid the session id of the shopper
 * </ul>
 * 
 * @author Derek
 * @version 1.0
 *
 */

public class ShoppingCartDisplay {

	protected JFrame frmShoppingCart;
	private final ActionListener action = new ButtonHandler();
	private Project project;
	private int sessionid;

	/**
	 * Create the application of the shopping cart.
	 */
	public ShoppingCartDisplay(Project p, int sessionid) {
		this.project = p;

		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmShoppingCart = new JFrame();
		frmShoppingCart.setTitle("Shopping Cart");
		frmShoppingCart.setBounds(100, 100, 450, 300);
		frmShoppingCart.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmShoppingCart.getContentPane().setLayout(null);

		JLabel lblInCart = new JLabel("In Cart");
		lblInCart.setBounds(20, 20, 60, 20);
		frmShoppingCart.getContentPane().add(lblInCart);

		JButton btnCheckout = new JButton("Checkout");
		btnCheckout.addActionListener(action);
		btnCheckout.setBounds(300, 220, 120, 30);
		frmShoppingCart.getContentPane().add(btnCheckout);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(30, 43, 390, 165);
		frmShoppingCart.getContentPane().add(scrollPane);

		JTextArea textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
	}

	/**
	 * The ActionListener of this button of the frame.
	 */
	private class ButtonHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getActionCommand().equals("Checkout")) {

			} else {

			}

		}

	}
}
