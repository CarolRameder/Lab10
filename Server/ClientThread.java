import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * manages client commands and sends answers back
 */
public class ClientThread extends Thread {
    private Socket socket = null;

    public ClientThread(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream());
            String raspuns;
            String request;

           //the server receives commands until "stop"/"exit"
            while (true) {
                // the client is now connected
                System.out.println("Waiting for a new request... ");
                request = in.readLine();

                if (request == null) {
                    // client typed exit -> connection is lost
                    System.out.println("Client exited");
                    break;
                }

                else if (request.equals("stop")) {
                    // the client is disconected after receiving this specific answer
                    System.out.println("Server stopped");
                    raspuns = "Server stopped";
                    out.println(raspuns);
                    out.flush();
                    break;
                }

                else {
                    // sending the given command back to client after it is displayed
                    raspuns = "the given command is :  " + request;
                    System.out.println(raspuns);
                    out.println(raspuns);
                    out.flush();
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}