package org.techfire225.webapp;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

public class StateApplets {
  public static class LatestState extends HttpServlet {
    private static final long serialVersionUID = 7888425758718454864L;

    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
      res.setContentType("application/json");
      res.setStatus(HttpServletResponse.SC_OK);
      res.getWriter().println(FireLog.toJsonObject().toString());
    }
  }
}
 
