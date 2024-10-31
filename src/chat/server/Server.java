package chat.server;

import chat.session.Session;
import chat.session.SessionManager;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import static util.MyLogger.log;

public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(12345);

        while(true) {
            Socket socket = serverSocket.accept();
            log("소켓 연결 : " + socket);

            Session session = new Session(socket);
            Thread thread = new Thread(session);
            thread.start();
        }
    }
}
