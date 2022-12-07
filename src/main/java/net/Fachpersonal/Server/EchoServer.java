package net.Fachpersonal.Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class EchoServer implements jServer {

    private ServerSocket ss;

    public EchoServer(int port) {
        init(port);
        close();
    }

    /**
     * Starts Echo Server
     * @return -1 = nothing
     */
    @Override
    public int init(int port) {
        try {
            ss = new ServerSocket(port);
            System.out.println(timestamp()+" EchoServer started!");
            while(true) {
                Socket s = ss.accept();
                EchoClient ec = new EchoClient(s);
                new Thread(ec).start();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int close() {
        return -1; // Something failed
    }

    private String timestamp() {
        return "["+new SimpleDateFormat("HH.mm.ss").format(new Timestamp(System.currentTimeMillis()))+"]";
    }
}
class EchoClient implements Runnable{

    private BufferedReader br;
    private PrintWriter pw;

    private String lastMSG;

    public EchoClient(Socket s){
        try {
            br = new BufferedReader(new InputStreamReader(s.getInputStream()));
            pw = new PrintWriter(s.getOutputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void run() {
        try {
            while ((lastMSG = br.readLine()) != null) {
                pw.println("{ECHO} " + lastMSG);
                pw.flush();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String getLastMSG() {return lastMSG;}
}