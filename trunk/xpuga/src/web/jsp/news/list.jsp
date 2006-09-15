<%@ page import="it.xpug.xpuga.*" %>
<html>
<head>
  <title>Italian eXtreme Programming User Group News</title>
  <meta http-equiv="content-type" content="text/html; charset=utf-8" />
  <link href="css/master.css" media="screen, projection" rel="stylesheet" type="text/css" />
</head>
<body>
	
	<div class="navigation">
		<ul class="menu">
			<li class="item">News</li>
		</ul>
	</div>

	<% java.util.List news = (java.util.List) request.getAttribute("news");
	   for (int i=0; i<news.size(); i++) { 
	      NewsPiece piece = (NewsPiece) news.get(i);
	%>
		<div class="content">
			<div id="<%= piece.getInsertionDateAsCode() %>" class="news">
				<p class="info">
					<span class="insertion-date" title="<%= piece.getInsertionDateAsCode() %>"><%= piece.getInsertionDate() %></span>
					<span class="expiration-date" title="<%= piece.getExpirationDateAsCode() %>"><%= piece.getExpirationDate() %></span>
					<span class="user-group" title="<%= piece.getGroupName() %>"><%= piece.getGroupName() %></span>
					<span class="title"><%= piece.getTitle() %></span>
				</p>
				<div class="body">
					<%= piece.getBody() %>
				</div>
				<ul class="actions">
					<li class="action">delete</li>
					<li class="action">edit</li>
				</ul>
			</div>
		</div>
	<% } %>

	<div class="navigation">
		<ul class="menu">
			<li class="item"><a ref="insert-news" href="news/form">Insert</a></li>
		</ul>
	</div>
</body>
</html>
