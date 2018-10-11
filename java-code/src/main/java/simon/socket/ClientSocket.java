package simon.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by sim on 2017/3/20.
 * Socket链接客户端
 */


public class ClientSocket {
    public static void main(String[] args0){
        try {
            Socket socket = new Socket("127.0.0.1",8080);
            PrintWriter writer = new PrintWriter(socket.getOutputStream());
            writer.println("你好啊，我是客户端");
            writer.flush();
            BufferedReader is = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            System.out.println("冒号后面是服务端发回的信息:"+is.readLine());
            writer.close();
            is.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
