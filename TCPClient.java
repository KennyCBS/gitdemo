package client;
//I'm going to tell you a TCP joke until you get it.

import java.net.*;
import java.util.Scanner;
import java.io.*;

import shared.SerializableData;

public class TCPClient {
	public static void main(String argv[]) throws Exception
	{
		while(true){
			String username;
			double amount;
			
			
			
			BufferedReader inFromUser = new BufferedReader( new InputStreamReader(System.in));
			Scanner input = new Scanner(System.in);
			
			System.out.println("Client is ON 1");
			
			
			Socket clientSocket = new Socket("localhost", 6789);
			SerializableData sdClient = new SerializableData();
			
			System.out.println("Client is ON 2");
			
			
			ObjectOutputStream outToServer = 
				new ObjectOutputStream(clientSocket.getOutputStream());
			
			System.out.println("Client is ON 3");
			
			ObjectInputStream inFromServer = 
				new ObjectInputStream(clientSocket.getInputStream());   
			
			
			
			System.out.println("Please type in your username");
			sdClient.username = inFromUser.readLine();
			
			System.out.println("What do you want to deposit?");
			sdClient.amount = input.nextDouble();
			
			outToServer.writeObject(sdClient);   
   
			sdClient = (SerializableData) inFromServer.readObject();
			
			System.out.println("FROM SERVER: " + sdClient.toString());   
			clientSocket.close();
		}
	}
}