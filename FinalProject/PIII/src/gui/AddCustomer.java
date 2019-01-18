package gui;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import api.Project;

/**
 * AddCustomer displays when a customer is added. It's information includes:
 * <ul>
 * <li>p the Project
 * <li>sessionid the session id of the customer
 * </ul>
 * 
 * @author Sunny
 * @version 1.0
 *
 */
public class AddCustomer extends JFrame {
	private JPanel panel;
	private JTextField[] textFields;
	private GridLayout gLayout;
	private ButtonHandler handler;
	private JButton save, cancel;
	private Project project;

	protected int sessionid;

	/**
	 * Create the application of AddCustomer.
	 */

	public AddCustomer(Project p, int sessionid) {
		super("Add a customer");
		this.project = p;

		this.sessionid = sessionid;

		gLayout = new GridLayout(4, 4);
		panel = new JPanel();
		panel.setLayout(gLayout);
		textFields = new JTextField[3];
		textFields[0] = new JTextField(20);
		textFields[1] = new JTextField(20);
		textFields[2] = new JTextField(20);

		addLabel("Customer Name", panel);
		addBox(textFields[0], panel);
		addLabel("City", panel);
		addBox(textFields[1], panel);
		addLabel("Street", panel);
		addBox(textFields[2], panel);
		save = new JButton("Save");
		cancel = new JButton("Cancel");
		handler = new ButtonHandler();
		addButton(save, panel, handler);
		addButton(cancel, panel, handler);

		this.add(panel);
	}

	private void addLabel(String labelText, JPanel panel) {
		JLabel label = new JLabel(labelText);
		JPanel p = new JPanel(new FlowLayout(FlowLayout.LEFT));
		p.add(label);
		panel.add(p);
	}

	private void addBox(JTextField tField, JPanel panel) {
		JPanel p = new JPanel();
		p.add(tField);
		panel.add(p);
	}

	private void addButton(JButton b, JPanel panel, ButtonHandler h) {
		JPanel p = new JPanel();
		b.addActionListener(h);
		p.add(b);
		panel.add(p);
	}

	/**
	 * The ActionListener of this button of the frame.
	 */

	private class ButtonHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == save) {
				project.addCustomer(textFields[0].getText(), textFields[1].getText(), textFields[2].getText(),
						sessionid);
			}
			if (e.getSource() == cancel) {
				textFields[0].setText("");
				textFields[1].setText("");
				textFields[2].setText("");

			}

		}

	}

}
