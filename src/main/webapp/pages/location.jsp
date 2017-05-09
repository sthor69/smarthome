<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<script type="text/javascript"
	src="http://maps.google.com/maps/api/js?sensor=false"></script>

<script type="text/javascript">
	var initialize = function() {

		var lat = $("#lat").html();
		var lng = $("#long").html();
		// fornisce latitudine e longitudine
		var latlng = new google.maps.LatLng(lat, lng);

		// imposta le opzioni di visualizzazione
		var options = {
			zoom : 12,
			center : latlng,
			mapTypeId : google.maps.MapTypeId.ROADMAP
		};

		// crea l'oggetto mappa
		var map = new google.maps.Map(document.getElementById('map'), options);
	}

	window.onload = initialize;

	var marker = new google.maps.Marker({
		position : latlng,
		map : map,
		title : 'Giulia'
	});
</script>

</head>

<body style="margin: 0; padding: 0;">

<div id="lat"><% application.getAttribute("lat"); %></div>
<div id="long"><% application.getAttribute("long"); %></div>

	<div id="map" style="width: 100%; height: 100%"></div>

</body>


</html>