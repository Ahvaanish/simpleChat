package edu.seg2105.edu.server.backend;
// This file contains material supporting section 3.7 of the textbook:
// "Object Oriented Software Engineering" and is issued under the open-source
// license found at www.lloseng.com 


import edu.seg2105.client.common.ChatIF;
import edu.seg2105.edu.server.ui.ServerConsole;
import ocsf.server.*;

import java.io.IOException;

/**
 * This class overrides some of the methods in the abstract 
 * superclass in order to give more functionality to the server.
 *
 * @author Dr Timothy C. Lethbridge
 * @author Dr Robert Lagani&egrave;re
 * @author Fran&ccedil;ois B&eacute;langer
 * @author Paul Holden
 */
public class EchoServer extends AbstractServer 
{

  /**
   * The interface type variable.  It allows the implementation of
   * the display method in the client.
   */
  ChatIF serverUI;

  //Constructors ****************************************************
  
  /**
   * Constructs an instance of the echo server.
   *
   * @param port The port number to connect on.
   */
  public EchoServer(int port, ServerConsole serverConsole)
  {
    super(port);
    serverUI = serverConsole;
  }
  
  //Instance methods ************************************************
  
  /**
   * This method handles any messages received from the client.
   *
   * @param msg The message received from the client.
   * @param client The connection from which the message originated.
   */
  public void handleMessageFromClient
    (Object msg, ConnectionToClient client)
  {
    System.out.println("Message received: " + msg + " from " + client); //Change this?
    this.sendToAllClients(msg);
  }


  // Not sure if this is the way to implement this

  /**
   * Sends message to all clients
   * @param message to be delt with
   */
  public void handleMessageFromServerUI(String message){

      if(message.startsWith("#")){
        handleCommand(message);
      }
      else {
        System.out.println("Message sent: "+message);
        sendToAllClients("SERVER MSG> "+message);
      }
  }


/**
 * Executes a command based on the command string inputed
 * @param command to be executed
 */
private void handleCommand(String command){
}
    
  /**
   * This method overrides the one in the superclass.  Called
   * when the server starts listening for connections.
   */
  protected void serverStarted()
  {
    System.out.println
      ("Server listening for connections on port " + getPort());
  }
  
  /**
   * This method overrides the one in the superclass.  Called
   * when the server stops listening for connections.
   */
  protected void serverStopped()
  {
    System.out.println
      ("Server has stopped listening for connections.");
  }

  /**
   * Hook method called each time a new client connection is
   * accepted. The default implementation does nothing.
   * @param client the connection connected to the client.
   */
  protected void clientConnected(ConnectionToClient client) {
    try{
      client.sendToClient("Connected successfully");
    }
    catch(IOException e){
      System.out.println(e.getMessage());
    }
  }

  /**
   * Hook method called each time a client disconnects.
   * The default implementation does nothing. The method
   * may be overridden by subclasses but should remains synchronized.
   *
   * @param client the connection with the client.
   */
  synchronized protected void clientDisconnected(ConnectionToClient client) {
    try{
      client.sendToClient("Disconnected successfully");
    }
    catch(IOException e){
      System.out.println(e.getMessage());
    }
  }

  //Class methods ***************************************************

}
//End of EchoServer class
