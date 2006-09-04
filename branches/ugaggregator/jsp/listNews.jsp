<%@ page import="it.xpug.aggregator.*" %>
<%@ page import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
  <title>xpug italia news</title>
  <meta http-equiv="content-type" content="text/html; charset=utf-8" />
  <link href="master.css" media="screen, projection" rel="stylesheet" type="text/css" />
</head>

<body>
	
	<div class="navigation">
		<ul class="menu">
			<li class="item">Home</li>
			<li class="item">Groups</li>
		</ul>
	</div>

	<div class="content">
	
	<%= "dir: " + System.getProperty("NewsFileWriter.IT_XPUG_AGGREGATOR_NEWS_DIR") %>
<% 
	FileFinder finder = new FileFinder("/home/matteo/workspace/ugaggregator/newsdb");
	News[] news = finder.listNews();
	for (int i=0; i<news.length; i++) {
%>
		<div id="20060521144211_milanoxpug" class="news">
			<p class="info">
				<span class="insertion-date" title="20060521144211"><%= news[i].insertionDate().getTime() %></span>
				<span class="expiration-date" title="20060522124211"></span>
				<span class="author"></span>
				<span class="title"><%= news[i].title() %></span>
			</p>
			<div class="body">
				<%= news[i].description() %>
			</div>
			<ul class="actions">
				<li class="action">new</li>
				<li class="action">delete</li>
				<li class="action">edit</li>
			</ul>
		</div>
<%
	}
%>
	</div>
</body>
</html>
