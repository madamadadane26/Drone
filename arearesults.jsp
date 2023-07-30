
<html>
	<form>
		<table>
<%@ page import="com.dronerecon.ws.AreaGridTile"%>
<%@ page import="com.dronerecon.ws.DBManager" %>
<%@ page import="java.util.ArrayList" %>

	String sAreaID = request.getParameter("area_id");

	DBManager dbm = new DBManager();
	
	dbm.DBLocation = System.getProperty("catalina.base") + "\\webapps\\dronereconportal\\db\\" + dbm.DBLocation;
	
	dbm.readAreaGridTiles(<%=request.getParameter("area_id")%>);
	
	ArrayList<AreaGridTile> areaTiles = new ArrayList<>();
	
	


	int Maxr = 0;
	int RxHigh = 0;
	int RyHigh = 0;
	
	int GxHigh = 0;
	int GyHigh = 0;
	int Maxg = 0;


	for(AreaGridTile aTile : areaTiles) {
		
		if (areaTiles.get(counter).r > Maxr ){
				XrHigh = aTile.x;
				YrHigh = aTile.y;
				out.println("The highest r values are x:" + RxHigh + " y:" + RyHigh);

			}	
		}
		else {
			if (areaTiles.get(counter).g > Maxg ){
				out.println("The highest g values are x:" + GxHigh + " y:" + GyHigh);
			}
			}
	

	
	
	</table>
	</form>
</html>

	
	