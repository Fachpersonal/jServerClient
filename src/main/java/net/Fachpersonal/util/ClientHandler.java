package net.Fachpersonal.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public abstract class ClientHandler implements Runnable {

    private Socket socket;

    private BufferedReader br;
    private PrintWriter pw;

    public ClientHandler(Socket socket) {
        this.socket = socket;
        init();
    }

    private void init(){
        try {
            br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            pw = new PrintWriter(socket.getOutputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public abstract void run();

    public String std_readLine(){
        try {
            return br.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void std_write(String str) {
        pw.println(str);
        pw.flush();
    }

    public abstract String readLine();
    public abstract void write(String str);
}
