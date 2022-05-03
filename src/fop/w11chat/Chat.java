package fop.w11chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.PortUnreachableException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class Chat extends MiniJava {

    public static void main(String[] args) {

        Socket socket = null;

        while (true) {
            try {
                String input = readString(
                        "Enter <port> in order to start the chat server "
                                + "or <host>:<port> in order to connect to a running server. "
                                + "Enter exit for exiting the chat.\n");
                if (input.equals("exit")) {
                    break;
                }
                else if (!input.contains(":")) {
                    ServerSocket server = new ServerSocket(Integer.parseInt(input));
                    System.out.println("Server is started on port " + input + ", accepting connections...");
                    socket = server.accept();
                    server.close();
                    break;
                }
                else {
                    int port = Integer.parseInt(input.substring(input.indexOf(":") + 1));
                    String host = input.substring(0, input.indexOf(":"));
                    socket = new Socket(host, port);
                    break;
                }
            }
            catch (UnknownHostException e) {
                System.out.println("Unknown host input, please try again!");
            }
            catch (PortUnreachableException e) {
                System.out.println("Unreachable port input, please try again!");
            }
            catch (IOException e) {
                System.out.println("I/O Error, please try again!");
            }
            catch (IllegalArgumentException e) {
                System.out.println("Wrong input!");
            }
        }

        if (socket != null) {
            try {
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(socket.getOutputStream());

                Thread reader = new Thread() {
                    @Override
                    public void run() {
                        try {
                            while (true) {
                                String output = in.readLine();
                                if (output.equals("exit")) {
                                    System.out.println("Connection interrupted!");
                                    break;
                                }
                                System.out.println("\n" + output);
                                System.out.print("> ");
                            }
                        }
                        catch (IOException e) {
                            System.out.println("\nReader target disconnected!");
                            interrupt();
                        }
                    }
                };

                Thread writer = new Thread() {
                    @Override
                    public void run() {
                        while (true) {
                            if (reader.isInterrupted()) {
                                interrupt();
                                break;
                            }
                            String input = readString("> ");
                            if (input.equals("exit")) return;
                            out.println("< " + input);
                            out.flush();
                        }
                    }
                };

                reader.start();
                writer.start();

                try {
                    reader.join();
                    writer.join();
                }
                catch (InterruptedException e) {
                    System.out.println("Connection interrupted!");
                }

                System.out.println("Exiting...");

            }
            catch (IOException e) {
                System.out.println("I/O exception occurred!");
            }
        }

    }

}
