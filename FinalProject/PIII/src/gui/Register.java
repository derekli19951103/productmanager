package gui;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.ChangeListener;

import api.Project;

public class Register extends JFrame{
	private JPanel panel;
	private JTextField[] textFields;
	private GridLayout gLayout;
	private ButtonHandler handler;
	private JButton save, cancel;
	private JCheckBox chckbxAdmin;

	
	private Project project;
	public Register(Project p) {
		super("Registration");
        this.project=p;
		
		
		gLayout = new GridLayout(4, 5);
		panel = new JPanel();
		panel.setLayout(gLayout);
		textFields = new JTextField[2];
		textFields[0] = new JTextField(20);
		textFields[1] = new JTextField(20);

		addLabel("User ID", panel);
		addBox(textFields[0], panel);
		addLabel("Enter Password", panel);
		addBox(textFields[1], panel);
		chckbxAdmin = new JCheckBox("Are you an Admin?",false);
		addCheckbox(chckbxAdmin,panel);
		addLabel("", panel);
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
	private void addCheckbox(JCheckBox b, JPanel panel) {
		JPanel p = new JPanel();
		p.add(b);
		panel.add(p);
	}
	
	
	
	
	
	
	private class ButtonHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==save){
				if(chckbxAdmin.isSelected()){
				project.addUser(textFields[0].getText(), textFields[1].getText(),true);}
				else{
					project.addUser(textFields[0].getText(), textFields[1].getText(),false);}
			}if(e.getSource()==cancel){
				textFields[0].setText("");
				textFields[1].setText("");
			}
			
		}
	}
	


}
