package networks;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client  {

	private Socket socket;
	private DataInputStream input;
	private DataOutputStream output;

	public Client() throws UnknownHostException, IOException {
		socket = new Socket("localhost", 5000);
		input = new DataInputStream(socket.getInputStream());
		output = new DataOutputStream(socket.getOutputStream());
		System.out.println(input.readUTF());
		System.out.println("Ingrese numero de registro que quiere ver:");
		Scanner scanner = new Scanner(System.in);
		output.writeInt(Integer.parseInt(scanner.nextLine()));
		System.out.println(input.readUTF());
	}

	public static void main(String[] args) {
		try { 
			
			new Client();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
