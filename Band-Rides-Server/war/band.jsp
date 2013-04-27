<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.catglo.bandridesserver.*" %>
<%@	page import="java.util.List" %>
<%@ page import="javax.jdo.Query" %>
<%@ page import="javax.jdo.PersistenceManager" %>
<%



Band band;
if (request.getParameter("bandID") != null) {
    band = Band.BandWithId(Long.parseLong(request.getParameter("bandID")));
}
else {
	band = new Band();
	band.makePersistant();
	out.println(band.bandID);
}
%>
<!doctype html>
<html>
  <head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <title>marcs_test</title>
  
  </head>

  <body>
<%
PersistenceManager pm = PMF.get().getPersistenceManager();
Query q = pm.newQuery(Band.class);
List<Band> results = (List<Band>) q.execute();
for (Band b : results ){
	
	out.println("<a href='band.jsp?bandID="+b.bandID+"'>");
	
	if (b.name.length()<1) out.println("unnamed");
	else                   out.println(b.name);
	
	out.println("</a><br/>");
}

%>  
  
  
<a href='shows.php?bandID<%= band.bandID %>'>View Schedule</a>
	
<form action='index.php' method='get'>

<input type='hidden' name='bandID' value='<%= band.bandID %>'/>

<p>Name:<br/>
<input type='text' name='name' value='<%= band.name %>'/>
</p>

<p>Description:<br/>
<textarea name='description'><%= band.description %></textarea>
</p>
	
<p>Image URL:<br/>
<input type='text' name='imageURL' value='<%= band.imageUrl %>'/>
</p>
	
	
<p><a href='https://soundcloud.com/'>Soundcloud</a> URL:<br/>
<input type='text' name='soundcloud' value='<%= band.soundcloud %>'/>
</p>

<p><a href='http://www.youtube.com/'>YouTube URL:</a><br/>
<input type='text' name='YouTube' value='<%= band.YouTube %>'/>
</p>

<input type='submit' name='submit' value='submit'/>

</form>

</body>

</html>

	