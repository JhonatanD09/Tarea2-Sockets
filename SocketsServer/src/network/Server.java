package network;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Server implements Runnable {

	private ServerSocket serverSocket;
	private Thread thread;
	private boolean isConnectionOn;

	public Server() throws IOException {
		serverSocket = new ServerSocket(5000);
		isConnectionOn = true;
		thread = new Thread(this);
		thread.start();
		Logger.getGlobal().log(Level.INFO, "new server init in port 5000");
	}

	@Override
	public void run() {
		while (isConnectionOn) {
			try {
				Socket connection = serverSocket.accept();
				Logger.getGlobal().log(Level.INFO, "New cliente connected");
				DataInputStream input = new DataInputStream(connection.getInputStream());
				DataOutputStream output = new DataOutputStream(connection.getOutputStream());
				output.writeUTF("Cantidad de nombres regiatradaos:  " + names().size());
				int index = input.readInt();
				Logger.getGlobal().log(Level.INFO, index + " index");
				output.writeUTF(searchName(names(), index-1));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	private String searchName(ArrayList<String> names, int index) {
		if (index < names.size()) {
			return "El dato que solicito : " + names.get(index);
		}else {
			return "No existe dato resistrado en esa posicion";
		}
	}

	private ArrayList<String> names(){
		ArrayList<String> names = new ArrayList<String>();
		names.add("Jose");
		names.add("Daniel");
		names.add("Maria");
		names.add("Juana");
		names.add("Maleja");
		return names;
	}
	
	
	public static void main(String[] args) {
		try {
			new Server();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
