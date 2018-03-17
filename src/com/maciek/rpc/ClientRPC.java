package com.maciek.rpc;

import com.maciek.async.DefaultAsyncCallback;
import com.maciek.model.Method;
import com.maciek.util.IdUtil;
import com.maciek.util.ParameterUtil;
import org.apache.xmlrpc.AsyncCallback;
import org.apache.xmlrpc.XmlRpcClient;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Vector;

public class ClientRPC {

    private String serverAddress = "localhost";
    private String serverPort;
    private final List<Integer> serverPortIds;
    private String serverName = "defaultServer";
    private XmlRpcClient server;
    private List<Method> serverMethods;
    private AsyncCallback asyncCallback = new DefaultAsyncCallback();
    private Scanner sc = new Scanner(System.in);

    public ClientRPC(List<Integer> serverPortIds) {
        this.serverPortIds = serverPortIds;
    }

    public void run() {
        try {
            //printAvailableServers();
            //getConfigurationFromUser();
            while (true) {
                System.out.println("\nEnter server id: ");
                int chosenServerPort = IdUtil.toPort(sc.next().trim());
                loadServerMethods(getServerMethods(chosenServerPort));
                printServerMethods();
                System.out.println("Enter ID of method to call it: ");
                int id = Integer.parseInt(sc.next().trim());
                System.out.println("Async call ? y/n: ");
                boolean async = sc.next().trim().equals("y");
                callMethod(id, async);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Klient XML-RPC: " + e);
        }
    }

    private void printAvailableServers() {
        System.out.println("\nAvailable servers: ");
        serverPortIds.forEach(id -> System.out.print(id + " "));
    }

    public Vector getServerMethods(Integer serverPort) throws Exception {
        if (isCenterOfStar()) {
            server = new XmlRpcClient("http://" + serverAddress + ":" + serverPort);
        } else {
            server = new XmlRpcClient("http://" + serverAddress + ":" + IdUtil.toPort(serverPortIds.get(0)));
        }
        Vector<Integer> params = new Vector<>();
        params.add(IdUtil.toId(serverPort));
        return (Vector) server.execute(serverName + ".show", params);
    }

    private void getConfigurationFromUser() {
        System.out.println("Konfiguracja klienta. Użyć domyślnej ? y/n");
//        if (false) {
        if (!sc.next().trim().equals("y")) {
            System.out.println("Podaj IP lub DNS serwera: ");
            serverAddress = sc.next().trim();
            System.out.println("Podaj port serwera: ");
            serverPort = sc.next().trim();
            System.out.println("Podaj nazwę serwera: ");
            serverName = sc.next().trim();
        }
    }

    private void loadServerMethods(Object methods) {
        serverMethods = new ArrayList<>();
        Vector<Vector<Object>> vectorMethods = (Vector) methods;
        for (Vector method : vectorMethods) {
            serverMethods.add(new Method((String) method.get(0), (String) method.get(1), (Vector) method.get(2), (String) method.get(3)));
        }
    }

    private void printServerMethods() {
        for (int i = 0; i < serverMethods.size(); i++) {
            System.out.println("ID: " + i + "  " + serverMethods.get(i).toString());
        }
    }

    private Method getServerMethod(int index) {
        return serverMethods.get(index);
    }

    private void callMethod(int destinationPort, int methodIndex, boolean async) throws Exception {
        Method method = getServerMethod(methodIndex);
        Vector<String> paramTypes = method.paramTypes;
        Vector<Object> callParams = new Vector<>();
        System.out.println("Calling method " + method.fullName);
        for (int i = 0; i < paramTypes.size(); i++) {
            System.out.println("Enter param of type " + paramTypes.get(i) + " :");
            String param = sc.next().trim();
            System.out.println("Param " + param);
            callParams.addElement(ParameterUtil.resolveParam(param, paramTypes.get(i)));
        }
        if (async) {
            System.out.println("Sent async call to thisServer");
            server.executeAsync(serverName + "." + method.name, callParams, asyncCallback);
        } else {
            System.out.println("Server response: " + server.execute(serverName + "." + method.name, callParams));
        }
    }

    private boolean isCenterOfStar() {
        return serverPortIds.size() > 1;
    }

}
