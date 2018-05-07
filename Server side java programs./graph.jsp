<%@page import="java.io.File"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.io.FileInputStream"%>
<%@page import="java.io.InputStream"%>
<%@page import="java.util.Properties"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    <script type="text/javascript">
      google.charts.load('current', {'packages':['corechart']});
      google.charts.setOnLoadCallback(drawChart);
      
      function drawChart() {
        var data = google.visualization.arrayToDataTable([
        	
<%
String sql="";
      Properties prop = new Properties();
  		try {
  			InputStream input = new FileInputStream("config.properties"); //file contains the username,password,database to be used
  			prop.load(input); //gets the properties from input which is an InputStream object for the properties file
  			Class.forName(prop.getProperty("classname"));
  			Connection con = DriverManager.getConnection(prop.getProperty("databasepath"),
  					prop.getProperty("dbuser"), prop.getProperty("dbpassword")); //creating the connection object
  			Statement stmt=con.createStatement(); 
  			sql="SELECT temperature, stamp FROM monitor;"; //query to get temperature and timestamp from monitor table
  			String type=request.getParameter("type"); //type specifies if the request is from admin or general user
  			String moni=request.getParameter("moni"); //moni specifies the parameter to be monitored
  			
  				if(moni.equals("temp")){
  					sql="SELECT temperature,stamp FROM monitor where location='"+request.getParameter("loc")+"';";
					//gives temperature data on the basis of location(location from the request of app)
  				}else if(moni.equals("smoke")){
  					sql="SELECT smoke,stamp FROM monitor where location='"+request.getParameter("loc")+"';";
					//gives smoke data on the basis of location(location comes from the request of app)
  				}else{
  					sql="SELECT humidity,stamp FROM monitor  where location='"+request.getParameter("loc")+"';";
					//gives humidity data on the basis of location(location comes from the request of app)
  				}
  				out.print("['TimeStamp', 'Sensor'],");
  				ResultSet rs=stmt.executeQuery(sql);//result set contains two fields. (Temperature/Humidity/smoke, Timestamp)
  	  			rs.afterLast();
  	  			int count=0;
  	  			while(rs.previous()&&count<10){count++;}//cursor goes back to 10 positions
  	  			while(rs.next()){
  	  				out.println("['"+rs.getString(2)+"',"+rs.getString(1)+"],");/*cursor comes front, resulting the last 10 documents in chronological order of timestamp. Timestamp, Temperature/smoke/humidity levels*/
  	  			}
  			
  			
  			con.close();
  		}catch(Exception e){
  			out.print(e);
  		}
%>
          [' ',  0 ]
        ]);

        var options = {
          title: 'Sensor graph',
          curveType: 'function',
          legend: { position: 'bottom' }
        };

        var chart = new google.visualization.LineChart(document.getElementById('curve_chart'));

        chart.draw(data, options);
      }
    </script>
    

  </head>
  <body>
  <%//out.print(sql); %>
    <div id="curve_chart" style="width: 400px; height: 600px"></div>
    
    <% 
    //out.print(new File(".").getAbsolutePath());
    %>
    
    
  </body>
</html>
