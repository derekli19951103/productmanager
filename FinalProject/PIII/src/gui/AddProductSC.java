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
import api.Shopper;


public class AddProductSC extends JFrame{
	private JPanel panel;
	private JTextField[] textFields;
	private GridLayout gLayout;
	private ButtonHandler handler;
	private JButton save, cancel,newb,remove;
    private int sessionid;
	private Project project;

	private int custid;
	private boolean flag;
	
	public AddProductSC(Project p,int sessionid) {
		super("Manage a Product in the Shopping Cart");
        this.project=p;
        this.sessionid=sessionid;
        
		
		
		gLayout = new GridLayout(4, 6);
		panel = new JPanel();
		panel.setLayout(gLayout);
		textFields = new JTextField[2];
		textFields[0] = new JTextField(20);
		textFields[1] = new JTextField(20);

		addLabel("Product ID", panel);
		addBox(textFields[0], panel);
		addLabel("Quantity Demanded", panel);
		addBox(textFields[1], panel);
		save = new JButton("Buy!!!");
		cancel = new JButton("Cancel");
		remove=new JButton("Remove Product");
		handler = new ButtonHandler();
		addButton(save, panel, handler);
		addButton(cancel, panel, handler);
		newb = new JButton("Register As A New Customer");
		addButton(newb, panel, handler);
		Shopper shop = (Shopper)project.active.get(sessionid);
		if(shop.cusrecord!=null){
			flag=true;
			custid=shop.cusrecord.ID;
			newb.setEnabled(false);
		}else{
			flag=false;
		}
	  

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
				if(flag){
				
				}
			}if(e.getSource()==newb){
				AddCustomer dataScreen=new AddCustomer(project,sessionid);
				dataScreen.pack();
			    dataScreen.setVisible(true);
			}
			
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
