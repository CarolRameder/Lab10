import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 * connects to the server
 */
public class GameClient {
    public static void main(String[] args) throws IOException {
        int PORT = 8100; // The server's port
        String serverAddress = "127.0.0.1"; // The server's IP address

        try {
            Socket socket = new Socket(serverAddress, PORT);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String request;
            String response;
            Scanner scanner = new Scanner(System.in);

            while (true) {
                System.out.print("Type your request: ");
                request = scanner.nextLine();

                if (request.equals("exit")) {
                    // the process of the client finishes
                    // without the command being transmitted
                    socket.close();
                    break;
                }

                out.println(request);// send request to server
                response = in.readLine();//receive response from server
                System.out.println(response);

                // the process of the client finishes
                //the command is transmitted, a certain answer is received
                if (response.equals("Server stopped")) {
                    socket.close();// the process of the client finishes
                    break;
                }
            }
        } catch (UnknownHostException e) {
            System.err.println("Error with server connection " + e);
        }
    }
}