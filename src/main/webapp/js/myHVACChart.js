var tempSliderMinIdx = 0;
var tempSliderMaxIdx = 100;
var humSliderMinIdx = 0;
var humSliderMaxIdx = 100;
var totalReadings = 1440;
var readData = [];
var myTempCtx = document.getElementById("myTempChart");
var myHumCtx = document.getElementById("myHumChart");
var myTempChart, myHumChart;

initializeChart();

function initializeChart() {
	$.ajax({
		url : 'history?num=' + totalReadings,
		success : function(data) {
			readData = data;
			$('#temp-sub-title').html(
					"Current temperature: " + data[data.length - 1].temp[0]);

			$('#hum-sub-title').html(
					"Current humidity: " + data[data.length - 1].humidity[0]
							+ "%");

			if (typeof myTempChart !== "undefined" && myTempChart !== null)
				myTempChart.destroy();

			myTempChart = createChart(myTempCtx, "temp", tempSliderMinIdx,
					tempSliderMaxIdx)

			if (typeof myHumChart !== "undefined" && myHumChart !== null)
				myHumChart.destroy();

			myHumChart = createChart(myHumCtx, "humidity", humSliderMinIdx,
					humSliderMinIdx)

		}
	});
}

$(function() {
	$("#temp-slider").slider(
			{
				values : [ 0, 100 ],
				range : true,
				change : function(event, ui) {

					numFromTempSliders(ui.value, ui.handleIndex);

					if (typeof myTempChart !== "undefined"
							&& myTempChart !== null)
						myTempChart.destroy();

					myTempChart = createChart(myTempCtx, "temp", totalReadings
							/ 100 * tempSliderMinIdx, totalReadings / 100
							* tempSliderMaxIdx);

				}
			});
});

$(function() {
	$("#humidity-slider").slider(
			{
				values : [ 0, 100 ],
				range : true,
				change : function(event, ui) {

					numFromHumSliders(ui.value, ui.handleIndex);

					if (typeof myHumChart !== "undefined"
							&& myHumChart !== null)
						myHumChart.destroy();

					myHumChart = createChart(myHumCtx, "humidity",
							totalReadings / 100 * humSliderMinIdx,
							totalReadings / 100 * humSliderMaxIdx);
				}
			});
});

var numFromTempSliders = function(value, index) {
	if (index == 0)
		tempSliderMinIdx = value;
	else
		tempSliderMaxIdx = value;

	return 1440 / 100 * (tempSliderMaxIdx - tempSliderMinIdx);

}

var numFromHumSliders = function(value, index) {
	if (index == 0)
		humSliderMinIdx = value;
	else
		humSliderMaxIdx = value;

	return 1440 / 100 * (humSliderMaxIdx - humSliderMinIdx);

}

function changePeriod() {
	var sel = $("#timeframe").prop("selectedIndex");

	switch (sel) {

	case 0:
		totalReadings = 1440;
		break;
	case 1:
		totalReadings = 2880;
		break;
	case 2:
		totalReadings = 10080;
		break;
	case 3:
		totalReadings = 20160;
		break;
	case 4:
		totalReadings = 43200;
		break;

	}
	initializeChart();
};

function createChart(ctx, dataType, min, max) {
	return new Chart(ctx, {
		type : 'line',
		data : {
			labels : readData.slice(min, max).map(function(a) {
				return a.time;
			}),
			datasets : [ {
				data : readData.slice(min, max).map(function(a) {
					return a[dataType][0];
				})
			} ]
		},
		options : {
			elements : {
				point : {
					radius : 0
				}
			},
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