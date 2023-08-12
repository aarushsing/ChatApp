package com.Aarush.chatapp.views;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.Aarush.chatapp.dao.UserDAO;
import com.Aarush.chatapp.dto.UserDTO;
import com.Aarush.chatapp.utils.UserInfo;

public class UserScreen extends JFrame{
	private JTextField useridtxt;

	private JPasswordField passwordField;

	
	public static void main(String[] args) {
		
					UserScreen window = new UserScreen();
					
	}
	UserDAO userDAO = new UserDAO();
	private void doLogin() {
		String userid  = useridtxt.getText();
		char[]  password= passwordField.getPassword();
		UserDTO userDTO =new UserDTO(userid , password);
		try {
			String message ="";
			if(userDAO.isLogin(userDTO)) {
			 	message ="Welcome "+userid;
			 	UserInfo.USER_NAME = userid;
			 	JOptionPane.showMessageDialog(this, message);
			 	setVisible(false);
			 	dispose();
			 	DashBoard dashBoard = new DashBoard(message);
			 	dashBoard.setVisible(true);
			}
			else {
				message ="Invalid userid or password";
				JOptionPane.showMessageDialog(this, message);
			}
			//JOptionPane.showMessageDialog(this, message);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void register() {
		String userid  = useridtxt.getText();
		char[]  password= passwordField.getPassword();
		//UserDAO userDAO = new UserDAO();
		UserDTO userDTO =new UserDTO(userid , password);
		try {
		int result = userDAO.add(userDTO);
		if(result>0) {
			JOptionPane.showMessageDialog(this ,"Register Successfully");
			//System.out.println("Record Added....");
		}
		else {
			JOptionPane.showMessageDialog(this ,"Register Fail");
			//System.out.println("Record Not Added....");
		}
		}
		catch(ClassNotFoundException | SQLException ex) {
			System.out.println("DB Issue....");
			ex.printStackTrace();
		}
		catch(Exception ex){
			System.out.println("Some Generic Exception raised...");
			ex.printStackTrace();//Where is the exception
		}
		System.out.println(" userid "+userid+" Password "+password+" "+password.toString());//ClassName@HashCode(Hexadecimal)
	}
	

	/**
	 * Create the application.
	 */
	public UserScreen() {
		setResizable(false);
		setBackground(new Color(255, 255, 255));
		setTitle("Login");
		getContentPane().setBackground(new Color(255, 255, 255));
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("LOGIN");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 40));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(301, 64, 159, 93);
		getContentPane().add(lblNewLabel);
		
		useridtxt = new JTextField();
		useridtxt.setBounds(373, 178, 249, 42);
		getContentPane().add(useridtxt);
		useridtxt.setColumns(10);
		
		JLabel useridlbl = new JLabel("USER ID.");
		useridlbl.setHorizontalAlignment(SwingConstants.CENTER);
		useridlbl.setFont(new Font("Tahoma", Font.BOLD, 18));
		useridlbl.setBounds(182, 176, 113, 39);
		getContentPane().add(useridlbl);
		
		JLabel pwdlbl = new JLabel("Password");
		pwdlbl.setHorizontalAlignment(SwingConstants.CENTER);
		pwdlbl.setFont(new Font("Tahoma", Font.BOLD, 18));
		pwdlbl.setBounds(182, 250, 113, 39);
		getContentPane().add(pwdlbl);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(373, 253, 249, 39);
		getContentPane().add(passwordField);
		
		JButton Loginbt = new JButton("Login");
		Loginbt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doLogin();
			}
		});
		Loginbt.setFont(new Font("Tahoma", Font.BOLD, 16));
		Loginbt.setBounds(247, 339, 106, 29);
		getContentPane().add(Loginbt);
		
		JButton Registerbt = new JButton("Register");
		Registerbt.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				register();
			}
		});
		Registerbt.setFont(new Font("Tahoma", Font.BOLD, 16));
		Registerbt.setBounds(424, 339, 106, 29);
		getContentPane().add(Registerbt);
		setSize(833, 533);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
	}
}



