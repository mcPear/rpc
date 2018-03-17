package com.maciek.rpc;

import com.maciek.util.IdUtil;
import org.apache.xmlrpc.WebServer;

import java.util.Collections;
import java.util.Vector;

public class ServerRPC {

    private static final String NAME = "defaultServer";
    private static WebServer webServer;
    private final ClientRPC client;
    private final Integer id;

    public void run(int port) {
        try {
            System.out.println("Startuje serwer XML-RPC...");
            webServer = new WebServer(port);
            webServer.addHandler(NAME, this);
            webServer.start();
            System.out.println(NAME + " wystartował pomyślnie.");
            System.out.println("Nasłuchuje na porcie: " + port);
            System.out.println("Aby zatrzymać serwer naciśnij Ctrl+C");
        } catch (Exception e) {
            System.err.println("Serwer XML-RPC: " + e);
        }
    }

    public ServerRPC(ClientRPC client, Integer id) {
        this.client = client;
        this.id = id;
    }

    public Vector show(int serverId) {
        System.out.println(this.id+" "+serverId);
        if (this.id == serverId) {
            return show();
        } else {
            try {
                return client.getServerMethods(IdUtil.toPort(serverId));
            } catch (Exception e) {
                return null;
            }
        }
    }

    private Vector show() {
        Vector allMethods = new Vector();
        allMethods.add(getConcatenateIntegerDto());
        allMethods.add(getRepeatDto());
        allMethods.add(getPowDto());
        allMethods.add(getSortDoublesDto());
        return allMethods;
    }

    public String concatenateInteger(String expression, int value) {
        return expression + value;
    }

    private Object[] getConcatenateIntegerDto() {
        String[] types = {"String", "Integer"};
        Object[] methodDto = {"concatenateInteger", "concatenateInteger(String expression, int value)", types,
                "Appends integer at the end of expression"};
        return methodDto;
    }

    public Double pow(double base, int power) {
        return Math.pow(base, power);
    }

    private Object[] getPowDto() {
        String[] types = {"Double", "Integer"};
        Object[] methodDto = {"pow", "pow(Double base, Integer power)", types,
                "Math operation power"};
        return methodDto;
    }

    public String repeat(String expression, int times) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < times; i++) {
            builder.append(expression + " ");
        }
        return builder.toString();
    }

    private Object[] getRepeatDto() {
        String[] types = {"String", "Integer"};
        Object[] methodDto = {"repeat", "repeat(String expression, int times)", types, "Repeats expression many times"};
        return methodDto;
    }

    public Vector<Double> sortDoubles(Vector<Double> values) {
        Collections.sort(values);
        return values;
    }

    private Object[] getSortDoublesDto() {
        String[] types = {"Vector<Double>"};
        Object[] methodDto = {"sortDoubles", "sortDoubles(Vector<Double> values)", types, "Returns sorted vector of Double"};
        return methodDto;
    }

    public Object[] delegateMethod(int destinationPort, int methodIndex){
        return
    }

}
