package com.Aarush.chatapp.network;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

/*
 Thread == worker
 Worker need a job to perform
 For a job , u give a runnable 
 Once job is created via Runnable so write the job logic inside a run function
 Assign the job to a Thread/worker
  
*/
	public class ServerWorker extends Thread{
		private Socket clientSocket;
		private InputStream in;
		private OutputStream out;
		private Server server;
		public ServerWorker(Socket clientSocket, Server server) throws IOException {
		this.server = server;	
			this.clientSocket = clientSocket;
			in = clientSocket.getInputStream(); //Client Data Read
			out = clientSocket.getOutputStream(); // Client Data Write
			System.out.println("New Client Comes....");
		}
		
		@Override
		public void run() {
			// Read Data from the client and broadcast the data to all.
			BufferedReader br = new BufferedReader (new InputStreamReader(in));
		 String line;
		 try {
		 while(true) {
			 
				line = br.readLine();
				System.out.println("line read.. "+line);
				if(line.equalsIgnoreCase("quit")) {
					break; // Client chat End
				}
				//out.write(line.getBytes()); // Client Send
				// Broadcast to all client
				for(ServerWorker serverWorker :server.workers) {
					line = line + "\n";
					serverWorker.out.write(line.getBytes());
				}
		
		 }	
		 } catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
			}
		 finally {
			 try {
			 if (br!=null) {
				 br.close();
			 }
			 if(in!=null) {
				 in.close();
			 }
			 if(out!=null) {
				 out.close();
			 }
			 if(clientSocket!=null) {
				 clientSocket.close();
			 }
			 }
			 catch(Exception ex) {
				 ex.printStackTrace();
			 }
		 }
		 
		}


}
