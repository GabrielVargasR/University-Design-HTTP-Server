package edu.byohttp;

import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.Optional;
import java.util.OptionalInt;

public class ByoHttp {

    private final int port;
    private final File resourcesDirectory;
    private final File mimeConfig;
    private final Config config;

    ByoHttp(int port, File resourcesDirectory, File mimeConfig) {
        this.port = port;
        this.resourcesDirectory = resourcesDirectory;
        this.mimeConfig = mimeConfig;
        this.config = new Config(this.resourcesDirectory, this.mimeConfig);
    }

    void run() {
        try (ServerSocket serverSocket = new ServerSocket(this.port)) {
            while (true) {
                new Thread(this.config.getSocketMessageRunnable(serverSocket.accept())).start();
            }
        } catch (IOException e) {
            System.err.println("Could not listen on port " + this.port);
            System.exit(-1);
        }
    }

    public static void main(String... args) {
        if (!validateArgsCount(args)) {
            System.exit(1);
        }

        OptionalInt port = validatePort(args[0]);
        if (port.isEmpty()) {
            System.exit(1);
        }

        Optional<File> resourcePath = validateResourcePath(args[1]);
        if (resourcePath.isEmpty()) {
            System.exit(1);
        }

        Optional<File> mimeConfigPath = validateMimeConfigPath(args[2]);
        if (mimeConfigPath.isEmpty()) {
            System.exit(1);
        }

        ByoHttp app = new ByoHttp(port.getAsInt(), resourcePath.get(), mimeConfigPath.get());
        app.run();
    }

    private static Optional<File> validateResourcePath(String resourcePathArg) {
        File resourcePath = new File(resourcePathArg);
        if (!resourcePath.exists() || !resourcePath.isDirectory()) {
            System.err.println("Argument <resources path> should point to a directory");
            return Optional.empty();
        }
        return Optional.of(resourcePath);
    }

    private static Optional<File> validateMimeConfigPath(String mimeConfigPathArg) {
        File mimeConfigPath = new File(mimeConfigPathArg);
        if (!mimeConfigPath.exists() || !mimeConfigPath.isFile()) {
            System.err.println("Argument <mimeConfig path> should point to a file");
            return Optional.empty();
        }
        return Optional.of(mimeConfigPath);
    }

    private static OptionalInt validatePort(String portArg) {
        try {
            int port = Integer.parseUnsignedInt(portArg);
            return OptionalInt.of(port);
        } catch (NumberFormatException nfe) {
            System.err.println("Argument <port number> should be a number");
            return OptionalInt.empty();
        }
    }

    private static boolean validateArgsCount(String[] args) {
        if (args.length != 3) {
            printUsage();
            System.err.println("Invalid number of arguments");
            return false;
        }
        return true;
    }

    private static void printUsage() {
        System.out.println("Usage: java ByoHttp <port number> <resources path> <mimeConfig path>");
    }
}
