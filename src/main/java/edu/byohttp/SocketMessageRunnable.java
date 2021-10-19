package edu.byohttp;

import method.response.Response;
import requests.RequestController;

import java.io.*;
import java.net.Socket;

public class SocketMessageRunnable implements Runnable {

    private final InputStream in;
    private final OutputStream out;
    private final RequestController controller;

    public SocketMessageRunnable(Socket socket, RequestController controller) throws IOException {
        this.in = socket.getInputStream();
        this.out = socket.getOutputStream();
        this.controller = controller;
    }

    @Override
    public void run() {
        try {
            String input = stringifyInput();
            Response response = this.controller.process(input);
            this.writeOutput(response);

            in.close();
            out.flush();
            out.close();
        } catch (IOException e) {
            // TODO: esta es una mala práctica de manejo de errores, mejorar
            e.printStackTrace();
        }
    }

    private String stringifyInput() throws IOException {
        StringBuilder message = new StringBuilder();
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));

        while (reader.ready()) {
            message.append(reader.readLine()).append("\n");
        }

        return message.toString();
    }

    private void writeOutput(Response response) throws IOException {
        out.write(response.toString().getBytes());

        InputStream body = response.getBody();
        if (body!= null) {
            byte[] buffer = new byte[1024];
            int length;
            while ((length = body.read(buffer)) != -1) {
                out.write(buffer, 0, length);
            }
        }
    }
}
