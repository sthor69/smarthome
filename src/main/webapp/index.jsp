<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon" href="../../favicon.ico">

<title>Smarthome control centre</title>

<!-- Bootstrap core CSS -->
<link href="css/bootstrap.min.css" rel="stylesheet">

<!-- Custom styles for this template -->
<link href="css/justified-nav.css" rel="stylesheet">

<!-- Google Analytics -->
<script>
      (function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
      (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
      m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
      })(window,document,'script','https://www.google-analytics.com/analytics.js','ga');

      ga('create', 'UA-80479854-1', 'auto');
      ga('send', 'pageview');

    </script>
</head>

<body>

	<div class="container">

		<!-- The justified navigation menu is meant for single line per list item.
           Multiple lines will require custom code not provided by Bootstrap. -->
		<div class="masthead">
			<h3 class="text-muted">SmartHome</h3>
			<nav>
				<ul class="nav nav-justified">
					<li class="active"><a href="#">Home</a></li>
					<li><a href="dashboard">Dashboard</a></li>
					<li><a href="pages/location.jsp">Illumination</a></li>
					<li><a href="#">Air conditioning</a></li>
					<li><a href="#">Energy saving</a></li>
					<li><a href="#">Help</a></li>
				</ul>
			</nav>
		</div>

		<!-- Jumbotron -->
		<div class="jumbotron">
			<h1>SmartHome!</h1>
			<p class="lead">Make your home the first and most flexible smart
				environment on Earth.</p>
			<p>
				<a class="btn btn-lg btn-success" href="starthistorizer" role="button">Start the monitor!!</a>
			</p>
		</div>

		<!-- Example row of columns -->
		<div class="row">
			<div class="col-lg-4">
				<h2>Illumination</h2>
				<p>You can decide where buttons are placed, irrespective of
					current electrical wiring is designed. Don't bother anymore with
					the number, types and location of light switches. You can now
					reconfigure your lighting controls with a simple click.</p>
				<p>
					<a class="btn btn-primary" href="#" role="button">View details
						&raquo;</a>
				</p>
			</div>
			<div class="col-lg-4">
				<h2>Energy saving</h2>
				<p>Measure how much energy you are using, in order to improve
					energy sustainability and save CO2 emissions. Make the world a
					better world (saving money...)</p>
				<p>
					<a class="btn btn-primary" href="#" role="button">View details
						&raquo;</a>
				</p>
			</div>
			<div class="col-lg-4">
				<h2>Air conditioning</h2>
				<p>Control you air conditioning system from remote locations and
					check current temperature.</p>
				<p>
					<a class="btn btn-primary" href="#" role="button">View details
						&raquo;</a>
				</p>
			</div>
		</div>
		
		<footer class="footer">
			<p>&copy; Company 2014</p>
		</footer>

	</div>
	<!-- /container -->
</body>
</html>

