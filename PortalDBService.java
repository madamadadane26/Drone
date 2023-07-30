package com.dronerecon.ws;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class PortalDBService extends HttpServlet{

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json;charset=utf-8");
        response.addHeader("Access-Control-Allow-Origin", "*");

        PrintWriter out = response.getWriter();



        String sAreaID = request.getParameter("area_id");
        int iTileX = Integer.parseInt(request.getParameter("tilex"));
        int iTileY = Integer.parseInt(request.getParameter("tiley"));
        int r = Integer.parseInt(request.getParameter("r"));
        int g = Integer.parseInt(request.getParameter("g"));



        DBManager oDBManager = new DBManager();


        oDBManager.DBLocation = System.getProperty("catalina.base") + "\\webapps\\dronereconportal\\db\\" + oDBManager.DBLocation;



        oDBManager.insertAreaGridTile(
                sAreaID,
                iTileX,
                iTileY,
                r,
                g);

        out.println("{\"success\":true}");
    }
}

