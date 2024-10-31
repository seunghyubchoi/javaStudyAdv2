package chat.client;

import chat.util.SocketUtil;

import java.io.*;
import java.net.ConnectException;
import java.net.Socket;
import java.util.Scanner;

import static util.MyLogger.log;

public class Client {
    public static void main(String[] args) throws IOException {

        Socket socket = null;
        DataInputStream input = null;
        DataOutputStream output = null;

        try {
            socket = new Socket("localhost", 12345);
            input = new DataInputStream(socket.getInputStream());
            output = new DataOutputStream(socket.getOutputStream());
            log("서버와 연결");

            Scanner scanner = new Scanner(System.in);
            while (true) {
                System.out.print("전송 문자 : ");
                String msgToServer = scanner.nextLine();

                if(msgToServer.equals("exit")){
                    break;
                }

                output.writeUTF(msgToServer);


            }
        } catch (IOException e) {
            log("Client Exception Occurred...........");
            log(e);
        } finally {
            log("클라이언트 자원 정리");
            SocketUtil.closeResources(output, input, socket);
            log("클라이언트 자원 정리 완료");
        }




    }
}
