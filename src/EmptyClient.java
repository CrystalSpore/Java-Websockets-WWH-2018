import java.net.URI;
import java.net.URISyntaxException;
import java.nio.ByteBuffer;
import java.util.Scanner;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft;
import org.java_websocket.drafts.Draft_6455;
import org.java_websocket.handshake.ServerHandshake;

public class EmptyClient extends WebSocketClient {

    public EmptyClient(URI serverUri, Draft draft) {
        super(serverUri, draft);
    }

    public EmptyClient(URI serverURI) {
        super(serverURI);
    }

    @Override
    public void onOpen(ServerHandshake handshakedata) {
        send("Hello, it is me. Mario :)");
        System.out.println("new connection opened");
    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
        System.out.println("closed with exit code " + code + " additional info: " + reason);
    }

    @Override
    public void onMessage(String message) {
        System.out.println("received message: " + message);
    }

    @Override
    public void onMessage(ByteBuffer message) {
        System.out.println("received ByteBuffer");
    }

    @Override
    public void onError(Exception ex) {
        System.err.println("an error occurred:" + ex);
    }

    public static void main(String[] args) throws URISyntaxException {

        try {
            WebSocketClient client = new EmptyClient(new URI("ws://localhost:8887"));
            client.connectBlocking();

            System.out.flush();

            Scanner kin = new Scanner(System.in);

            boolean flag = true;

            System.out.println("Enter \"exit\" to end the client connect and exit the program...\n");

            while (flag) {
                System.out.print("Enter: ");

                String str = kin.nextLine();

                if (str.equals("exit")) {
                    client.send("Exit command sent");
                    flag = false;
                } else {
                    client.send(str);
                }

            }
            System.out.println("End Prog \\\\");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
