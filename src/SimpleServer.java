import java.net.InetSocketAddress;
import java.nio.ByteBuffer;

import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;


/**
 * @author: Crystal Fletcher
 * @git: CrystalSpore
 * @date: 2/24/18
 *
 * @library: https://github.com/TooTallNate/Java-WebSocket
 *              (used maven to install)
 *
 */

public class SimpleServer extends WebSocketServer {
    public SimpleServer(InetSocketAddress address) {
        super(address);
    }

    private static String myString = null;

    public void onOpen(WebSocket conn, ClientHandshake handshake) {
        conn.send("Welcome to the server!"); //sends message to new client
        broadcast("new connection: " + handshake.getResourceDescriptor()); //this method sends a message to all clients connected
        System.out.println("new connection to " + conn.getRemoteSocketAddress());
    }

    public void onClose(WebSocket conn, int code, String reason, boolean remote) {
        System.out.println("closed " + conn.getRemoteSocketAddress() + " with exit code " + code + " additional info: " + reason);
    }

    public void onMessage(WebSocket conn, String message) {
        System.out.println("received message from " + conn.getRemoteSocketAddress() + ": " + message);

        //*** VelociTap code will need to go here ***//

        //myString = message;
    }

    public void onMessage(WebSocket conn, ByteBuffer message) {
        System.out.println("received ByteBuffer from " + conn.getRemoteSocketAddress());
    }

    public void onError(WebSocket conn, Exception ex) {
        System.err.println("an error occured on connection " + conn.getRemoteSocketAddress() + ":" + ex);
    }

    public void onStart() {
        System.out.println("server started successfully");
    }


    public static void main(String[] args) {
        String host = "localhost";
        int port = 8887;

        WebSocketServer server = new SimpleServer(new InetSocketAddress(host, port));
        server.run();

        //*** Below code is inaccessible due to the loop in server.run() method ***//
        /*while(true) {
            if ( myString != null ) {
                System.out.println("myString is: " + myString);
                myString = null;
            }
        }*/
    }

}