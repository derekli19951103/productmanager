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
import javax.swing.text.BadLocationException;

import api.Project;



public class AddProduct extends JFrame{
	private JPanel panel;
	private JTextField[] textFields;
	private GridLayout gLayout;
	private ButtonHandler handler;
	private JButton save, cancel;
	private Project project;
	protected int sessionid;
	protected Mainframe m;
	
	public AddProduct(Project p,int sessionid,Mainframe m) {
		super("Add a Product");
        this.project=p;
      
        this.sessionid=sessionid;
        this.m=m;
		
		gLayout = new GridLayout(6, 6);
		panel = new JPanel();
		panel.setLayout(gLayout);
		textFields = new JTextField[4];
		textFields[0] = new JTextField(20);
		textFields[1] = new JTextField(20);
		textFields[2] = new JTextField(20);
		textFields[3] = new JTextField(20);

		addLabel("Product Name", panel);
		addBox(textFields[0], panel);
		addLabel("Category ID", panel);
		addBox(textFields[1], panel);
		addLabel("Price", panel);
		addBox(textFields[2], panel);
		addLabel("Image", panel);
		addBox(textFields[3], panel);
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
				Integer prodid=project.addProduct(textFields[0].getText(), Integer.parseInt(textFields[1].getText()), Double.parseDouble(textFields[2].getText()),sessionid);
				project.back.getProduct(prodid).setImagePath(textFields[3].getText());;
				
				try {
					m.updateprod();
				} catch (BadLocationException e1) {
					e1.printStackTrace();
				}
			}else{
				textFields[0].setText("");
				textFields[1].setText("");
				textFields[2].setText("");
			}
			
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
