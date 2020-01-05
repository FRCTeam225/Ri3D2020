package org.techfire225.webapp;

import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.WebSocketListener;
import org.eclipse.jetty.websocket.servlet.WebSocketServlet;
import org.eclipse.jetty.websocket.servlet.WebSocketServletFactory;

import java.util.Timer;
import java.util.TimerTask;

public class StateSocket extends WebSocketServlet  {
  private static final long serialVersionUID = -6042298400375548987L;

  @Override
  public void configure(WebSocketServletFactory factory) {
    factory.register(StateSocketAdapter.class);
  }
  
  public static class StateSocketAdapter extends TimerTask implements WebSocketListener {
    Session session;
    boolean invalid = false;
    Timer timer = new Timer();
 
    public StateSocketAdapter() {
      
    }
    
    @Override
    public void onWebSocketConnect(Session session) {
      this.session = session;
      timer.schedule(this, 0, 100); 
    }
    
    @Override
    public void onWebSocketClose(int statusCode, String reason) {
      timer.cancel();
      invalid = true;
    }
    
    @Override
    public void onWebSocketError(Throwable cause) {
      timer.cancel();
      invalid = true;
    }
  
    @Override
    public void run() {
      if ( invalid || !session.isOpen() )
        return;
 
      try {
        session.getRemote().sendStringByFuture(FireLog.toJsonObject().toString());
      } catch (Exception e) {
        invalid = true;
      }
    }

    @Override
    public void onWebSocketBinary(byte[] arg0, int arg1, int arg2) {
    }

    @Override
    public void onWebSocketText(String arg0) {
    }
  }


}
