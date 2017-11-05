package client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

import server.ChatMessage;
import server.MessageType;

/**
 * This class is responsible for the communication with the server
 * 
 * @author Alex and Bogdan
 *
 */
public class Client {

	public static Client clientInstance;

	private ObjectInputStream streamInput; // the incoming stream for messages
	private ObjectOutputStream streamOutput; // the outgoing stream for messages
	private Socket socket; // the socket from which we establish connection

	private static GUI gui;
	private String server, username;
	private int port; // port on which communication is established

	Client(String server, int port, String username) {
		this.server = server;
		this.port = port;
		this.username = username;

	}

	/**
	 * Try to establish the connection. If the connection is successful then the
	 * function returns true, otherwise false
	 * 
	 * @return if successful or not
	 */
	public boolean start() {

		try {
			socket = new Socket(server, port); // try to connect
		} catch (Exception ec) {
			display("Error connectiong to server:" + ec);
			return false;
		}

		String msg = "Connection accepted " + socket.getInetAddress() + ":" + socket.getPort();
		display(msg);

		try {
			streamInput = new ObjectInputStream(socket.getInputStream()); // first
																			// create
																			// the
																			// input
																			// stream
			streamOutput = new ObjectOutputStream(socket.getOutputStream()); // create
																				// output
																				// stream
		} catch (IOException eIO) {
			display("Exception creating new Input/output Streams: " + eIO);
			return false;
		}

		new ListenFromServer().start(); // extinde thread
		try {
			streamOutput.writeObject(username);
		} catch (IOException eIO) {
			display("Exception doing login : " + eIO);
			disconnect();
			return false;
		}
		return true;
	}

	/**
	 * Display the message into the console
	 * 
	 * @param msg
	 *            the message to be despalyed
	 */
	private void display(String msg) {

		if (gui == null)
			System.out.println(msg);

	}

	/**
	 * Scrie pe stream comanda care urmeaza sa fie citita de server
	 * 
	 * @param msg
	 *            = mesajul de tip ChatMessage
	 */
	public void sendMessage(ChatMessage msg) {
		try {
			streamOutput.writeObject(msg);
		} catch (IOException e) {
			display("Exception writing to server: " + e);
		}
	}

	/**
	 * Disconnects the client from the server. Closes the streams.
	 */
	private void disconnect() {
		try {
			if (streamInput != null)
				streamInput.close();
		} catch (Exception e) {
		}
		try {
			if (streamOutput != null)
				streamOutput.close();
		} catch (Exception e) {
		}
		try {
			if (socket != null)
				socket.close();
		} catch (Exception e) {
		}

		if (gui != null)
			gui.connectionFailed();

	}

	/**
	 * Aici ruleaza comenzile primite de la consola sau din interfata
	 * 
	 */
	private static void runClient(Client client) {

		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		while (true) {
			String msg = scan.nextLine(); // citeste linia

			if (msg.equalsIgnoreCase("LOGOUT")) {
				client.sendMessage(new ChatMessage(MessageType.LOGOUT, ""));
				break;
			} else {
				client.sendMessage(new ChatMessage(MessageType.MESSAGE, msg));
			}
		}
	}

	public static void main(String[] args) {
		int portNumber = 6789; // port on which communicate
		String serverAddress = "seproject.hopto.org"; // the server address
		String userName = "user"; // symbolic user

		switch (args.length) {
		case 3:
			serverAddress = args[2];
		case 2:
			try {
				portNumber = Integer.parseInt(args[1]);
			} catch (Exception e) {
				System.out.println("Invalid port number.");
				System.out.println("Usage is: > java Client [username] [portNumber] [serverAddress]");
				return;
			}
		case 1:
			userName = args[0];
		case 0:
			break;
		default:
			System.out.println("Usage is: > java Client [username] [portNumber] {serverAddress]");
			return;
		}
		clientInstance = new Client(serverAddress, portNumber, userName);
		gui = GUI.getInstance();
		gui.draw();

		if (!clientInstance.start())
			return;
		runClient(clientInstance); // when client stops connection is disconnected from the server
		clientInstance.disconnect();

	}

	/**
	 * Class responsible for the thread of the client
	 * 
	 * @author Alex and Bogdan
	 *
	 */
	class ListenFromServer extends Thread {
		/**
		 * reads the information from the server
		 * 
		 */
		public void run() {
			while (true) {
				try {
					ChatMessage aux = (ChatMessage) streamInput.readObject(); // reads
																				// the
																				// message
					gui.readMsg(aux);
				} catch (IOException e) {
					display("Server has close the connection: " + e);
					if (gui != null)
						gui.connectionFailed();
					break;
				} catch (ClassNotFoundException e2) {
				}
			}
		}
	}

	/**
	 * 
	 * @return the instance of the client
	 */
	public static Client getInstance() {
		return clientInstance;
	}
}
