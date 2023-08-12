package com.Aarush.chatapp.network;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import com.Aarush.chatapp.utils.ConfigReader;


public class Server {
	ServerSocket serverSocket;
	ArrayList<ServerWorker> workers = new ArrayList<>(); // contains all the client sockets
	
	public Server() throws IOException {
		int PORT = Integer.parseInt(ConfigReader.getValue("PORTNO"));
		serverSocket =new ServerSocket(PORT);
		System.out.println("Server is started ,now waiting for the clients to join.");
		handledClientRequest();
	}
	// Multiple Client HandShaking
	public void handledClientRequest() throws IOException {
		while(true) {
			Socket clientSocket = serverSocket.accept(); // HandShaking
			// Per Client Per Thread
			ServerWorker serverWorker = new ServerWorker(clientSocket, this); // Creating a new Worker/ Thread
			workers.add(serverWorker);
			serverWorker.start();
			}
	}
	
	// Single Client
	/* 
	public Server() throws IOException{
		int PORT = Integer.parseInt(ConfigReader.getValue("PORTNO"));
		serverSocket =new ServerSocket(PORT);
		System.out.println("Server Started and waiting for the client connection....");
		Socket socket = serverSocket.accept();//Handshaking
		System.out.println("Client Joins the Server...");
		InputStream in =socket.getInputStream();// read bytes from network
		byte arr[] = in.readAllBytes();
		String str= new String(arr);//Bytes convert into String
		System.out.println("Message Rec From The Client "+str);
		in.close();
		socket.close();
	}*/

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Server server= new Server();

		
	}

}
