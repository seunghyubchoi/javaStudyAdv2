package chat.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import static util.MyLogger.log;

public class SocketUtil {
    public static void closeResources(OutputStream output, InputStream input, Socket socket) {
        closeOutput(output);
        closeInput(input);
        closeSocket(socket);
    }

    public static void closeOutput(OutputStream output) {
        if (output != null) {
            try {
                log("output Close..................");
                output.close();
            } catch (IOException e) {
                log(e.getMessage());
            }
        }
    }

    public static void closeInput(InputStream input) {
        if (input != null) {
            try {
                log("input Close..................");
                input.close();
            } catch (IOException e) {
                log(e.getMessage());
            }
        }
    }

    public static void closeSocket(Socket socket) {
        if (socket != null) {
            try {
                log("socket Close..................");
                socket.close();
            } catch (IOException e) {
                log(e.getMessage());
            }
        }
    }
}
