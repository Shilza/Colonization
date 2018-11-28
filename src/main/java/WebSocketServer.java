import com.google.gson.Gson;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;


@ServerEndpoint("/endpoint")
public class WebSocketServer {
    public static class Coordinates{
        private int x, y;

        public Coordinates() {}

        public int getX() {
            return x;
        }

        public void setX(int x) {
            this.x = x;
        }

        public int getY() {
            return y;
        }

        public void setY(int y) {
            this.y = y;
        }
    }

    @OnOpen
    public void onOpen(Session session) {
        System.out.println("onOpen::" + session.getId());
    }
    @OnClose
    public void onClose(Session session) {
        System.out.println("onClose::" +  session.getId());
    }

    @OnMessage
    public void onMessage(String message, Session session){
//        System.out.println("onMessage::From=" + session.getId() + " Message=" + message);

        try {
            System.out.println((new Gson().fromJson(message, Coordinates.class)).x);
//            Type type =  new TypeToken<HashMap<String, Object>>(){}.getType();
//            HashMap<String, Object> result = new Gson().fromJson(message, type);
//            System.out.println("s");
//            session.getBasicRemote().sendText("Hello Client " + session.getId() + "!");
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @OnError
    public void onError(Throwable t) {
        System.out.println("onError::" + t.getMessage());
    }



    public static void main(String[] args){
//        System.out.println(((HashMap<String, Object>)(new Gson().fromJson("{\"x\":596,\"y\":224}", ))).get("x"));
    }
}
