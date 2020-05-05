import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * waits for new connections and creates a thread for every new client
 */
public class GameServer {
    public static final int PORT = 8100; // Define the port on which the server is listening

    public GameServer() throws IOException {
        ServerSocket serverSocket = null;
        try {
            System.out.println ("Waiting for a the client to connect...");
            serverSocket = new ServerSocket(PORT);//the information is transmitted using sockets

            // a new thread is created for the client's commands
            while (true) {
                Socket socket = serverSocket.accept();
                new ClientThread(socket).start();

            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            serverSocket.close();
        }

    }

    public static void main(String[] args) throws IOException {
        GameServer gameServer = new GameServer();
    }
}