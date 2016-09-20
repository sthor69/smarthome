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

<title>Dashboard</title>

<!-- Bootstrap core CSS -->
<link href="css/bootstrap.min.css" rel="stylesheet">

<!-- Custom styles for this template -->
<link href="css/dashboard.css" rel="stylesheet">

<script src="js/Chart.bundle.js"></script>

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>



</head>

<body>

	<nav class="navbar navbar-inverse navbar-fixed-top">
		<div class="container-fluid">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#navbar" aria-expanded="false"
					aria-controls="navbar">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="index.jsp">Home</a>
			</div>
			<div id="navbar" class="navbar-collapse collapse">
				<ul class="nav navbar-nav navbar-right">
					<li><a href="#">Dashboard</a></li>
					<li><a href="#">Settings</a></li>
					<li><a href="#">Profile</a></li>
					<li><a href="#">Help</a></li>
				</ul>
				<form class="navbar-form navbar-right">
					<input type="text" class="form-control" placeholder="Search...">
				</form>
			</div>
		</div>
	</nav>

	<div class="container-fluid">
		<div class="row">
			<div class="col-sm-3 col-md-2 sidebar">
				<ul class="nav nav-sidebar">
					<li class="active"><a href="#">Overview <span
							class="sr-only">(current)</span></a></li>
					<li><a href="#">Reports</a></li>
					<li><a href="#">Analytics</a></li>
					<li><a href="#">Export</a></li>
				</ul>
				<ul class="nav nav-sidebar">
					<li><a href="">Nav item</a></li>
					<li><a href="">Nav item again</a></li>
					<li><a href="">One more nav</a></li>
					<li><a href="">Another nav item</a></li>
					<li><a href="">More navigation</a></li>
				</ul>
				<ul class="nav nav-sidebar">
					<li><a href="">Nav item again</a></li>
					<li><a href="">One more nav</a></li>
					<li><a href="">Another nav item</a></li>
				</ul>
			</div>
			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
				<h1 class="page-header">Dashboard</h1>

				<div class="row placeholders">
					<div class="col-xs-6 col-sm-3 placeholder">
						<img src="images/illumination_thumbnail.png"
							class="img-responsive" alt="Generic placeholder thumbnail">
						<h4>Illumination</h4>
						<span class="text-muted">Temp: ${temp}</span>
					</div>
					<div id="energy" class="col-xs-6 col-sm-3 placeholder">
						<img src="images/energy_saving_thumbnail.jpg"
							class="img-responsive" alt="Generic placeholder thumbnail">
						<h4>Energy saving</h4>
						<span class="text-muted">Something else</span>
					</div>
					<div class="col-xs-6 col-sm-3 placeholder">
						<img src="images/air_conditioning_thumbnail.jpg"
							class="img-responsive" alt="Generic placeholder thumbnail">
						<h4>Air conditioning</h4>
						<span class="text-muted">Something else</span>
					</div>
					<div class="col-xs-6 col-sm-3 placeholder">
						<img src="images/monitoring_thumbnail.jpeg" class="img-responsive"
							alt="Generic placeholder thumbnail">
						<h4>Monitoring</h4>
						<span class="text-muted">Something else</span>
					</div>
				</div>

				<h2 id="sub-title" class="sub-header">Section title</h2>
				<canvas id="myChart" width="400" height="400"></canvas>
				<script>
					var hist;
					$.ajax({
						url: 'history',
						success: function(data) {
							$('#sub-title').html("Current temperature: " + data[data.length - 1].temp[0]);
							var ctx = document.getElementById("myChart");
							var myChart = new Chart(ctx, {
								type : 'line',
								data : {
									labels: data.map(function(a) {return a.time;}),
									datasets : [ {
										data : data.map(function(a) {return a.temp[0];})
									} ]
								},
								options : {
									scales : {
										yAxes : [ {
											ticks : {
												beginAtZero : false
											}
										} ]
									}
								}
							});
						}
					});
					
					
				</script>
			</div>
		</div>
	</div>

	<!-- Bootstrap core JavaScript
    ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	<script src="js/bootstrap.min.js"></script>

</body>
</html>

