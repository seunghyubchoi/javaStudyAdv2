package chat.session;

import chat.util.SocketUtil;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import static util.MyLogger.log;

public class Session implements Runnable {
    private final Socket socket;
    private final DataInputStream input;
    private final DataOutputStream output;
    private final SessionManager sessionManager;
    private boolean closed = false;

    public Session(Socket socket, SessionManager sessionManager) throws IOException {
        this.socket = socket;
        this.input  = new DataInputStream(socket.getInputStream());
        this.output = new DataOutputStream(socket.getOutputStream());
        this.sessionManager = sessionManager;
        this.sessionManager.add(this);
    }

    @Override
    public void run() {
        try {
            while (true) {
                String message = input.readUTF();
                if(message.equals("exit")) {
                    break;
                }
                log(message);
                output.writeUTF(socket + " : " + message);
            }
        } catch (IOException e) {
            log("Session Error Occurred................");
            log(e.getMessage());
        } finally {
            log("서버 자원 정리");
            //SocketUtil.closeResources(output, input, socket);
            sessionManager.remove(this);
            close();
            log("서버 자원 정리 완료 및 클라이언트와 연결 종료, " + socket);
        }
    }
    public synchronized void close () {
        if(closed) {
            return;
        }
        SocketUtil.closeResources(output, input, socket);
        closed = true;
        log("연결 종료 : " + socket);
    }
}
