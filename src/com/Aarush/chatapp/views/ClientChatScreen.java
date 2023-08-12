package com.Aarush.chatapp.views;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.Aarush.chatapp.network.Client;
import com.Aarush.chatapp.utils.UserInfo;

public class ClientChatScreen extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextArea textArea;
	private Client client;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
					try {
						ClientChatScreen frame = new ClientChatScreen();
						frame.setVisible(true);
					} catch (UnknownHostException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
	}
	
	private void sendIt() {
		String message= textField.getText();
		try {
			client.sendMessage(UserInfo.USER_NAME+":-"+message);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Create the frame.
	 * @throws IOException 
	 * @throws UnknownHostException 
	 */
	public ClientChatScreen() throws UnknownHostException, IOException {
		textArea  = new JTextArea();
		client = new Client(textArea);
		setTitle(UserInfo.USER_NAME+" chat window");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 799, 425);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 10, 765, 298);
		contentPane.add(scrollPane);
		
		
		textArea.setFont(new Font("Monospaced", Font.PLAIN, 16));
		textArea.setBounds(21, 27, 738, 267);
		scrollPane.setViewportView(textArea);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textField.setBounds(10, 327, 629, 36);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton sendit = new JButton("Send Message");
		sendit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sendIt();
			}
		});
		sendit.setBounds(659, 327, 116, 36);
		contentPane.add(sendit);
		setVisible(true);
	}
}
