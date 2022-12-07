package net.Fachpersonal.Server;

/**
 * interface for ClientHandlers
 * @author <a href="https://github.com/Fachpersonal/">Fachpersonal</a>
 * @version 1.0-SNAPSHOT
 */
public interface jClientHandler {
    String readLine();
    void write(String str);

    int run();
    int run(String[] args);
    void init();
    int close();


}
