import java.io.*;
import java.net.*;

public class Server {
  private static int PORT = 8100;
  Task task = new Task();

  private Server() {
    try (ServerSocket server = new ServerSocket(PORT);) {
      System.out.println("listening... port:" + PORT);
      while (true) {
        try (Socket socket = server.accept();) {
          accept(socket);
        } catch(Exception e) {
          e.printStackTrace();
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private void accept(Socket socket) throws IOException {
    System.out.println(">>> accepted");

    // BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    InputStreamReader reader = new InputStreamReader(socket.getInputStream());

    int serviceNameLength = reader.read();
    System.out.println("len : " + serviceNameLength);


    char[] serviceName = new char[serviceNameLength];
    reader.read(serviceName, 0, serviceNameLength);

    System.out.println(serviceName);

    // TODO ハッシュから取り出す
    ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
    oos.writeObject(task);


    // flush(reader);
  }

  private void flush(Reader reader) throws IOException {
    System.out.println(">>> flush");
    int c;
    while((c = reader.read()) != -1) {
      System.out.println(c);
    }
  }

  public static void main(String args[]) {
    Server server = new Server();
  }
}
