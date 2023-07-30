package com.dronerecon.ws;

import com.sun.javafx.scene.traversal.Direction;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.net.URL;
import java.util.*;
import java.security.SecureRandom;


/**
 *
 *
 */
public class DroneDataService extends HttpServlet{


    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("application/json;charset=utf-8");
        response.addHeader("Access-Control-Allow-Origin", "*");

        PrintWriter out = response.getWriter();

        String sAreaID = request.getParameter("area_id");

        int iTileX = Integer.parseInt(request.getParameter("tilex"));
        int iTileY = Integer.parseInt(request.getParameter("tiley"));

        int iTotalCols = Integer.parseInt(request.getParameter("totalcols"));
        int iTotalRows = Integer.parseInt(request.getParameter("totalrows"));


        String r = request.getParameter("r");
        String g  = request.getParameter("g");

        String sServiceReturnJson = "";

        String sDirection = "right";


        try {

            URL url = new URL("http://127.0.0.1:8080/dronereconportal/PortalDBService?area_id="
                    +sAreaID+"&tilex="+iTileX+"&tiley="+iTileY+"&r="+r+"&g="+g+"");
            BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
            String strTemp = "";
            while (null != (strTemp = br.readLine())) {
                sServiceReturnJson += strTemp;
            }

        } catch (Exception ex) {

            ex.printStackTrace();
            System.out.println("An error occurred in callDroneService() in DBManager: " + ex.toString());
        }


        if(iTileY % 2 == 0)
        {
            if(iTileX == iTotalCols - 1) {

                iTileY--;
                sDirection = "left";

            }
            else {
                iTileX++;
                sDirection = "right";
            }
        }
        else {
            if(iTileX == 0) {
                iTileY++;
                sDirection = "right";
            }

            else {
                iTileX--;
                sDirection = "left";

            }
        }



        if(iTileY == iTotalRows)
        {
            sDirection = "stop";
        }





        // 4. Format & Return JSON string to caller.
        String sReturnJson = ("{" +
                "\"area_id\":\"" + sAreaID + "\"," +
                "\"nextTileX\":\"" + iTileX + "\"," +
                "\"nextTileY\":\"" + iTileY + "\"," +
                "\"direction\":\"" + sDirection + "\"," +
                "}");


    }
}

