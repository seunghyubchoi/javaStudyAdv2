package chat.session;

import chat.util.SocketUtil;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import static util.MyLogger.log;

public class Session implements Runnable {
    private final Socket socket;

    public Session(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        DataInputStream input = null;
        DataOutputStream output = null;
        try {
            input = new DataInputStream(socket.getInputStream());
            output = new DataOutputStream(socket.getOutputStream());

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
            SocketUtil.closeResources(output, input, socket);
            log("서버 자원 정리 완료 및 클라이언트와 연결 종료, " + socket);
        }



    }
}
