package network.exception.connect;

import java.io.IOException;
import java.net.ConnectException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class ConnectTimeoutMainV2 {
    public static void main(String[] args) throws IOException {
        long start = System.currentTimeMillis();

        try {
            Socket socket = new Socket();
            socket.connect(new InetSocketAddress("192.168.1.250", 46578), 3000);
        } catch (SocketTimeoutException e) {
            e.printStackTrace();
        }

        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }
}
