package gui;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


import api.Project;


public class AddDistriCen extends JFrame{
	private JPanel panel;
	private JTextField[] textFields;
	private GridLayout gLayout;
	private ButtonHandler handler;
	private JButton save, cancel;
	protected int sessionid;
	private Project project;
	
	public AddDistriCen(Project p,int sessionid) {
		super("Add a Distribution Centre");
        this.project=p;
		this.sessionid=sessionid;
		
		gLayout = new GridLayout(2, 2);
		panel = new JPanel();
		panel.setLayout(gLayout);
		textFields = new JTextField[1];
		textFields[0] = new JTextField(20);
		
		addLabel("City Located", panel);
		addBox(textFields[0], panel);
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
	
	
	
	
	
	
	private class ButtonHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==save){
				project.addDistributionCenter(textFields[0].getText(), sessionid);
			}else{
				textFields[0].setText("");
			}
			
		}
	}


}
