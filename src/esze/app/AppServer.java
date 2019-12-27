package esze.app;

import java.io.IOException;
import java.util.ArrayList;

public class AppServer {
	
	public boolean isServerStarted = false;
	private int serverPort = 11111;
	public Runnable serverThread = null;
	private java.net.ServerSocket serverSocket;
	
	public ArrayList<AppClientSocket> clientSockets = new ArrayList<AppClientSocket>();
	
	public void startServer() {
		
		serverThread = new Runnable() {
			
			@Override
			public void run() {
				//CREATE SERVER SOCKET
				try {
					serverSocket = new java.net.ServerSocket(serverPort);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				
				//LET ANY CLIENT CONNECT AT ANY TIME
				while(true) {
					try {
						java.net.Socket client = serverSocket.accept();
					 	AppClientSocket clientSocket = new AppClientSocket(client, AppServer.this);
					 	clientSockets.add(clientSocket);
					} catch(Exception e) {
						e.printStackTrace();
					}
				}
			}
		};
		serverThread.run();
		
	}

}
