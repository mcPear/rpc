package com.maciek.util;

public class IdUtil {
    private static final int BASE_PORT = 8080;

    public static Integer toPort(String id) {
        return Integer.parseInt(id) + BASE_PORT;
    }

    public static Integer toPort(int id) {
        return id + BASE_PORT;
    }

    public static int toId(int port) {
        return port - BASE_PORT;
    }

    public static int toId(String port) {
        return Integer.parseInt(port) - BASE_PORT;
    }
}
