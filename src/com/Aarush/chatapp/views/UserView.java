package com.Aarush.chatapp.views;

import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class UserView extends JFrame {
	int counter;
	public UserView() {
		counter = 0;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//closes the window
		setSize(500,500);//set the size of window
		setResizable(false);//remove resize window option
		setTitle("Login");//set the title of window
		setLocationRelativeTo(null);//to help window place in middle
		//setLocation(500,150);//to help window place with respect to(x,y)axis
		JLabel welcome = new JLabel("Login");
		welcome.setFont(new Font("Arial",Font.BOLD,40));
		Container container = this.getContentPane();
		container.setLayout(null);
		welcome.setBounds(100,70,100,60);
		container.add(welcome);
		JButton button = new JButton("Count");//source
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				counter++;
				welcome.setText("Count"+counter);
			}
		});
		button.setBounds(100,300,200,50);
		container.add(button);
		
		setVisible(true);
		
	}
	public static void main(String[] args) {
		UserView userView = new UserView();
	}

}
