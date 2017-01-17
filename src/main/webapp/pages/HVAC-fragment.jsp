<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<label for="timeframe">Select the time range of measures</label>
	<select name="timeframe" id="timeframe" onchange="changePeriod()">
		<option selected="selected">24 hrs</option>
		<option>48 hr</option>
		<option>1 week</option>
		<option>2 weeks</option>
		<option>1 month</option>
	</select>
	<br />

   <h2 id=log>Dining room</h2>

	<h3 id="room-temp-subtitle" class="sub-header">Temperature</h3>
	<div id="myroomtempChart" width="400" height="100"></div>
	<br />
	<div id="room-temp-slider"></div>

	<h3 id="room-humidity-subtitle" class="sub-header">Humidity</h3>
	<div id="myroomhumidityChart" width="400" height="100"></div>
	<br />
	<div id="room-humidity-slider"></div>

	<h2>Children room</h2>

   <h3 id="chld-temp-subtitle" class="sub-header">Temperature</h3>
   <div id="mychldtempChart" width="400" height="100"></div>
   <br />
   <div id="chld-temp-slider"></div>

   <h3 id="chld-humidity-subtitle" class="sub-header">Humidity</h3>
   <div id="mychldhumidityChart" width="400" height="100"></div>
   <br />
   <div id="chld-humidity-slider"></div>
	
	<script src="js/myHVACChart.js"></script>
</body>
</html>
