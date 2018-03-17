package com.maciek.rpc;

public class HybridRPC {

    private static final Integer THIS_SERVER_PORT = 8090;
    private static final Integer LISTEN_SERVER_PORT = 8080;

    public static void main(String[] args) {
        ServerRPC.run(THIS_SERVER_PORT);
        new ClientRPC(LISTEN_SERVER_PORT).run();
    }

    //TRASH
//    public String sendRequest(String message) {
//        System.out.println("I received " + message);
//        if (!message.equals("cut_shot")) {
//            try {
//                Thread.sleep(2000);
//                server = new XmlRpcClient("http://" + SERVER_HOST + ":" + THIS_SERVER_PORT);
//                Vector<String> args = new Vector<>();
//                String response = getResponse(message);
//                args.add(response);
//                System.out.println("I send " + response);
//                server.execute(THIS_SERVER_NAME + ".sendRequest", args);
//            } catch (Exception e) {
//                e.printStackTrace();
//                System.err.println("Klient XML-RPC: " + e);
//            }
//        }
//        return "";
//    }
//
//    public static String getResponse(String trigger) {
//        if (cutShotOccurred()) {
//            return "cut_shot";
//        } else if (trigger.equals("ping")) {
//            return "pong";
//        } else {
//            return "ping";
//        }
//    }
//
//    private static boolean cutShotOccurred() {
//        return random.nextInt(10) > N;
//    }
//
//    public Integer echo(int x, int y) {
//        return x + y;
//    }
//
//    public int asy(int x) {
//        System.out.println("... wywołano asy - odliczam");
//        try {
//            Thread.sleep(x);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//            Thread.currentThread().interrupt();
//        }
//        System.out.println("...asy - koniec odliczania");
//        return 123;
//    }

}
