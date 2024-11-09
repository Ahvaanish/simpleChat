package edu.seg2105.edu.server.ui;

import edu.seg2105.client.backend.ChatClient;
import edu.seg2105.client.common.ChatIF;
import edu.seg2105.edu.server.backend.EchoServer;

import java.io.IOException;
import java.util.Scanner;

/**
 * This class constructs the UI for a Server Admin.  It implements the
 * chat interface in order to activate the display() method.
 *
 * @author Ahvaanish Kunaratnam
 */


public class ServerConsole implements ChatIF {

    /**
     * The default port to connect on, when no port is provided in terminal
     */
    final public static int DEFAULT_PORT = 5555;

    //Instance variables **********************************************

    /**
     * The instance of the server that created this ServerConsole.
     */
    EchoServer server;

    /**
     * Scanner to read from the console
     */
    Scanner fromConsole;


    public ServerConsole(int port)
    {
        server = new EchoServer(port, this);
        // Create scanner object to read from console
        fromConsole = new Scanner(System.in);
    }

    /**
     * Displays a message from the server Admin
     * @param message to be displayed
     */
    public void display(String message)
    {
        System.out.println("> " + message);
    }

    /**
     * This method waits for input from the console.  Once it is
     * received, it sends it to the server's message handler
     */

    public void accept()
    {
        try
        {
            String message;
            while (true)
            {
                message = fromConsole.nextLine();
                server.handleMessageFromServerUI(message);
            }
        }
        catch (Exception ex)
        {
            System.out.println("Unexpected error while reading from console!");
        }
    }

    /**
     * This method is responsible for the creation of
     * the server instance
     *
     * @param args[0] The port number to listen on.  Defaults to 5555
     *
     */
    public static void main(String[] args)
    {
        int port = 0; //Port to listen on

        try
        {
            port = Integer.parseInt(args[0]);
        }
        catch(Throwable t)
        {
            port = DEFAULT_PORT;
        }

        ServerConsole chat = new ServerConsole(port);

        try
        {
            chat.server.listen(); //Start listening for connections
        }
        catch (Exception ex)
        {
            System.out.println("ERROR - Could not listen for clients!");
        }
        chat.accept();
    }


}
