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
        SessionManager sessionManager = new SessionManager();

        ShutdownHook shutdownHook = new ShutdownHook(serverSocket, sessionManager);
        Runtime.getRuntime().addShutdownHook(new Thread(shutdownHook, "shutdownHook"));

        try {
            while(true) {
                Socket socket = serverSocket.accept();
                log("소켓 연결 : " + socket);

                Session session = new Session(socket, sessionManager);
                Thread thread = new Thread(session);
                thread.start();
            }
        } catch (IOException e ) {
            log("서버 소켓 종료 : " + e.getMessage());
        }



    }

    static class ShutdownHook implements Runnable {

        private final ServerSocket serverSocket;
        private final SessionManager sessionManager;

        public ShutdownHook(ServerSocket serverSocket, SessionManager sessionManager) {

            this.serverSocket = serverSocket;
            this.sessionManager = sessionManager;
        }

        @Override
        public void run() {
            log("셧 다운 훅 실행");
            try {
                sessionManager.closeAll();
                serverSocket.close();

                Thread.sleep(1000); // 셧다운 훅 실행 끝날 때까지 기다려 주는 용도
            } catch (Exception e) {
                log(e);
            }
        }
    }
}
