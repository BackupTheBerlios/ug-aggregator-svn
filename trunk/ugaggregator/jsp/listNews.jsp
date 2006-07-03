<%@ page import="it.xpug.aggregator.FakeNewsList"%>
<%@ page import="it.xpug.aggregator.News"%>
<%@ page import="java.util.Iterator"%>
<html>
<head></head>
<body>
<h3>XPUG<h3>
<% Iterator newsIterator =FakeNewsList.getIterator();
		while(newsIterator.hasNext())
		{
			News news=(News) newsIterator.next();
			%><p><h2><%=news.title()%></h2>
			<p><%= news.description()%><%
		}
%>

</body>
</html>