package net.Fachpersonal.Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

/**
 * Echo Server
 * @author <a href="https://github.com/Fachpersonal/">Fachpersonal</a>
 * @version 1.0-SNAPSHOT
 * @see <a href="https://github.com/Fachpersonal/jServerClient/blob/master/src/main/java/net/Fachpersonal/Server/jServer.java">jServer</a>
 */
public class EchoServer implements jServer {

    private ServerSocket ss;

    /**
     * Constructor of EchoServer
     * @param port
     */
    public EchoServer(int port) {
        init(port);
        close();
    }

    /**
     * initialization of EchoServer
     * @param port
     * @return
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

    /**
     * Closes EchoServer
     * @return
     */
    @Override
    public int close() {
        return -1; // Something failed
    }

    /**
     * @return TimeStamp in format of HH.mm.ss
     */
    private String timestamp() {
        return "["+new SimpleDateFormat("HH.mm.ss").format(new Timestamp(System.currentTimeMillis()))+"]";
    }
}

/**
 * Echo ClientHandler
 * @author <a href="https://github.com/Fachpersonal/">Fachpersonal</a>
 * @version 1.0-SNAPSHOT
 */
class EchoClient implements Runnable{

    private BufferedReader br;
    private PrintWriter pw;

    private String lastMSG;

    /**
     * Sets input and output streams
     * @param s
     */
    public EchoClient(Socket s){
        try {
            br = new BufferedReader(new InputStreamReader(s.getInputStream()));
            pw = new PrintWriter(s.getOutputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Echo loop
     */
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

    /**
     * @return lastMessage
     */
    public String getLastMSG() {return lastMSG;}
}