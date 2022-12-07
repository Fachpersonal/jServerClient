package net.Fachpersonal.Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class EchoClient {

    public EchoClient(String host, int port) throws IOException {
        Socket s = new Socket(host, port);
        BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
        PrintWriter pw = new PrintWriter(s.getOutputStream());
        Scanner sc = new Scanner(System.in);
        String line;
        while (true) {
            line = sc.nextLine();
            pw.println(line);
            pw.flush();
            System.out.println(br.readLine());
        }
    }
}
