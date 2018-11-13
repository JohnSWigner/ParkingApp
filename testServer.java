import java.net.*;
import java.io.*;
import java.util.Scanner;

public class testServer extends Thread {
   private ServerSocket serverSocket;
   private Scanner userInput = new Scanner(System.in);
   public testServer(int port) throws IOException {
      serverSocket = new ServerSocket(port);
      serverSocket.setSoTimeout(100000);
   }
   
   public void run(){
      while(true){
         try {
            System.out.println("Waiting for client on port " +
            serverSocket.getLocalPort() + "....");
            Socket server = serverSocket.accept();
            
            System.out.println("Just connected to " + server.getRemoteSocketAddress());
            
            //If we want to receive data from the client at all, uncomment these bad boys
            //DataInputStream in = new DataInputStream(server.getInputStream());
            //System.out.println(in.readUTF());
            DataOutputStream out = new DataOutputStream(server.getOutputStream());
            try {
               out.writeInt(1);
               Thread.sleep(1000);
               out.writeInt(2);
               Thread.sleep(1000);                           
               out.writeInt(3);
               Thread.sleep(1000);
               out.writeInt(4);
               Thread.sleep(1000);
               out.writeInt(-1);
               Thread.sleep(1000);                              
               out.writeInt(3);
               Thread.sleep(1000);
               out.writeInt(6);
               Thread.sleep(1000);
            } catch(InterruptedException e) {}
            server.close();
         } catch (SocketTimeoutException s) {
            System.out.println("Socket timed out!");
            break;
         } catch (IOException e) {
            break;
         }
      }
   }
   
   public static void main(String[] args) {
      int port = 2345;
      try {
         Thread t = new testServer(port);
         t.start();
      } catch (IOException e) {
         e.printStackTrace();
      }
   }
}
         