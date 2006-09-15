<%@ page import="it.xpug.xpuga.*" %>
<html>
<head>
  <title>Italian eXtreme Programming User Group News</title>
  <meta http-equiv="content-type" content="text/html; charset=utf-8" />
  <link href="../css/master.css" media="screen, projection" rel="stylesheet" type="text/css" />
</head>
<body>
	
	<div class="navigation">
		<ul class="menu">
			<li class="item">News</li>
		</ul>
	</div>

	<div class="content">
		<form id="insert-news" action="../news" method="post">
			<p>
				<div class="error" title="title">Error for this field</div>
				<input name="title" id="title" size="22" tabindex="1" type="text">
					<label for="title"><small>Title (required)</small></label>
				</input>
			</p>
			<p>
				<div class="error" title="body">Error for this field</div>
				<input name="body" id="body" size="22" tabindex="2" type="text">
					<label for="body"><small>Body (required)</small></label>
				</input>
			</p>
			<p>
				<div class="error" title="user-group">Error for this field</div>
				<input name="user-group" id="user-group" size="22" tabindex="3" type="text">
					<label for="user-group"><small>User Group (required)</small></label>
				</input>
			</p>
			<p>
				<div class="error" title="expiration-date">Error for this field</div>
				<input name="expiration-date" id="expiration-date" size="22" tabindex="4" type="text">
					<label for="expiration-date"><small>Expiration Date (required, format: YYYYMMDDHHMMSS)</small></label>
				</input>
			</p>
		</form>
	</div>

	<div class="navigation">
		<ul class="menu">
			<li class="item"><a ref="insert" href="../news" onclick="document.getElementById('insert-news').submit();">Submit</a></li>
			<li class="item"><a ref="list" href="../news">Cancel</a></li>
		</ul>
	</div>

</body>
</html>
