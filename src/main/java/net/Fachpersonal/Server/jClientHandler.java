package net.Fachpersonal.Server;
public interface jClientHandler {
    String readLine();
    void write(String str);

    int run();
    int run(String[] args);
    void init();
    int close();


}
