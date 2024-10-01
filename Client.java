import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        Socket client = null;
        BufferedReader bufferedReader = null;
        BufferedWriter bufferedWriter = null;
        Scanner scanner = null;

        try{
            client = new Socket("localhost", 8888);

            bufferedReader = new BufferedReader(new InputStreamReader(client.getInputStream()));
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));

            System.out.println("Server: " + bufferedReader.readLine());
            scanner = new Scanner(System.in);
            while(true){

                String msgToServer = scanner.nextLine();
                bufferedWriter.write(msgToServer);
                bufferedWriter.newLine();
                bufferedWriter.flush();
                System.out.println("Server: " + bufferedReader.readLine());

                if(msgToServer.equalsIgnoreCase("bye")){
                    break;
                }
            }

        }catch(IOException io){
            io.printStackTrace();
        }finally{
            try {
                if (client != null) {
                    client.close();
                }
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                if (bufferedWriter != null) {
                    bufferedWriter.close();
                }
                if(scanner != null){
                    scanner.close();
                }
            }catch(IOException io){
                io.printStackTrace();
            }
        }
    }
}
