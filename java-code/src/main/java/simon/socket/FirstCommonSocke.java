package simon.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by sim on 2017/3/20.
 * 测试Socket的demo代码，服务端
 */
public class FirstCommonSocke {
    public static void main(String[] args0){
        try {
            ServerSocket serverSocket = new ServerSocket(8080);
            Socket socket = serverSocket.accept();
            BufferedReader is = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String info = null;
//            while ((info = is.readLine()) != null){
//                System.out.print(info);
//            }
            System.out.print(is.readLine());
            PrintWriter writer = new PrintWriter(socket.getOutputStream());
            writer.print("你好，我是服务端");
            writer.flush();
            is.close();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
