package org.techfire225.webapp;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import javax.servlet.http.*;

import frc.robot.Constants;

import com.google.gson.JsonObject;

public class ConstantsApplets {
	public static class List extends HttpServlet {
		
		private static final long serialVersionUID = -6977078502800457385L;

		@Override
		protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
			
			try {
				JsonObject out = new JsonObject();
				
				Constants instance = Constants.getConstants();
				
				Field[] fields = instance.getClass().getDeclaredFields();
				for ( Field f : fields ) {
					if ( (f.getModifiers()&Modifier.PUBLIC) == 0 )
						continue;
					
					if ( f.getType() == double.class ) {
						out.addProperty(f.getName(), f.getDouble(instance));
					}
					else if ( f.getType() == int.class ) {
						out.addProperty(f.getName(), f.getInt(instance));
					}
					else if ( f.getType() == float.class ) {
						out.addProperty(f.getName(), f.getFloat(instance));
					}
					else
						out.addProperty(f.getName(), "type unsupported");
				}
				
				
				res.setContentType("application/json");
				res.setStatus(HttpServletResponse.SC_OK);
				res.getWriter().println(out.toString());
			} catch ( Exception e ) {
				e.printStackTrace();
				res.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			}
		}
	}
	
	public static class Update extends HttpServlet {
		private static final long serialVersionUID = -1620038159853455496L;
		
		@Override
		protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
			try {
				JsonObject out = new JsonObject();
				out.addProperty("success", true);
				
				String fieldName = req.getParameter("k");
				String value = req.getParameter("v");
				
				Constants instance = Constants.getConstants();
				
				Field field = instance.getClass().getDeclaredField(fieldName);
				
				if ( field.getType() == double.class ) {
					double val = Double.parseDouble(value);
					field.setDouble(instance, val);
				}
				else if ( field.getType() == int.class ) {
					int val = Integer.parseInt(value);
					field.setInt(instance, val);
				}
				else if ( field.getType() == float.class ) {
					float val = Float.parseFloat(value);
					field.setFloat(instance, val);
				}
				else {
					out.addProperty("success", false);
					out.addProperty("error", "type unsupported");
				}

				res.setContentType("application/json");
				res.setStatus(HttpServletResponse.SC_OK);
				res.getWriter().println(out.toString());
			} catch ( Exception e ) {
				e.printStackTrace();
				res.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			}
		}
	}
}
