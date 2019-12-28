package esze.app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class AppClientSocket {
	
	java.net.Socket clientSocket;
	AppServer server;
	Thread listeningThread;
	AppCommunicator communicator;
	AppClientIdentity identity;
	
	public AppClientSocket(java.net.Socket clientSocket, AppServer server) {
		this.clientSocket = clientSocket;
		this.server = server;
		communicator = new AppCommunicator(this);
		startListeningForMessages();
	}

	public java.net.Socket getClientSocket() {
		return clientSocket;
	}

	public AppServer getServer() {
		return server;
	}

	public void setClientSocket(java.net.Socket clientSocket) {
		this.clientSocket = clientSocket;
	}

	public void setServer(AppServer server) {
		this.server = server;
	}
	
	public void startListeningForMessages() {
		listeningThread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				while(true) {
				 	String nachricht = null;
					try {
						nachricht = readMessage(clientSocket);
					} catch (IOException e) {
						e.printStackTrace();
					}
					if(nachricht != null) {
						communicator.receivedMessage(nachricht);
						System.out.println(nachricht);
					}
				}
			}
		});
		listeningThread.start();
	}
	
	private String readMessage(java.net.Socket socket) throws IOException {
		BufferedReader bufferedReader =
			new BufferedReader(
				new InputStreamReader(
					socket.getInputStream()));
		char[] buffer = new char[200];
		int anzahlZeichen = bufferedReader.read(buffer, 0, 200); // blockiert bis Nachricht empfangen
		String nachricht = new String(buffer, 0, anzahlZeichen);
		return nachricht;
	}
	
	public void sendMessage(java.net.Socket socket, String nachricht) throws IOException {
		PrintWriter printWriter =
			new PrintWriter(
				new OutputStreamWriter(
					socket.getOutputStream()));
		printWriter.print(nachricht);
 		printWriter.flush();
	}
	
	public AppClientIdentity getIdentity() {
		return identity;
	}

}
