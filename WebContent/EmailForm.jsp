<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
<title>Email with Spring MVC</title>
</head>
<body>
	<div class="container">
		<h1>Sending e-mail with Spring MVC</h1>

		<form method="post" action="sendEmail" class="form-horizontal">
			<div class="form-group">
				<label for="inputEmail1">To:</label> <input name="recipient"
					type="email" class="form-control" id="inputEmail1"
					placeholder="Email">
			</div>
			<div class="form-group">
				<label for="inputSubject">Subject:</label> <input name="subject"
					type="text" class="form-control" id="inputSubject"
					placeholder="Subject">
			</div>
			<div class="form-group">
				<label for="inputMessage">HTML Content:</label>
				<textarea name="message" class="form-control" rows="5"
					id="inputMessage"></textarea>
			</div>
			<button type="submit" class="btn btn-default">Send E-mail</button>
		</form>


		<hr>

		<form method="post" action="sendEmailTemplate">
			<button type="submit" class="btn btn-warning">Send E-mail
				with template</button>
		</form>

	</div>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
</body>
</html>