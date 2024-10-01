import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        Socket client = null;
        BufferedReader bufferedReader = null;
        BufferedWriter bufferedWriter = null;
        try{
            serverSocket = new ServerSocket(8888);
            System.out.println("Server is listening on Port 8888");

        }catch(IOException io){
            io.printStackTrace();
        }

        while(true) {
            try {
                client = serverSocket.accept();
                System.out.println("Connection Established");
                bufferedReader = new BufferedReader(new InputStreamReader(client.getInputStream()));
                bufferedWriter = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
                bufferedWriter.write("Connection Established");
                bufferedWriter.newLine();
                bufferedWriter.flush();
                while (true) {
                    String msgFromClient = bufferedReader.readLine();
                    System.out.println("Client: " + msgFromClient);
                    bufferedWriter.write("Message Received");
                    bufferedWriter.newLine();
                    bufferedWriter.flush();
                    if(msgFromClient.equalsIgnoreCase("bye")){
                        break;
                    }
                }
                client.close();
                bufferedReader.close();
                bufferedWriter.close();
            }catch(IOException io){
                io.printStackTrace();
            }
        }
    }
}
