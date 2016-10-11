<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<label for="timeframe">Select the time range of monitors</label>
	<select name="timeframe" id="timeframe" onchange="changePeriod()">
		<option selected="selected">24 hrs</option>
		<option>48 hr</option>
		<option>1 week</option>
		<option>2 weeks</option>
		<option>1 month</option>
	</select>
	<br />

	<h2 id="water-sub-title" class="sub-header">Water levels</h2>
	<canvas id="myWaterChart" width="400" height="100"></canvas>
	<br />
	<div id="water-slider"></div>

	<script src="js/myWaterChart.js"></script>
</body>

</body>
</html>