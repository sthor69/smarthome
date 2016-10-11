var roomTempSliderMinIdx = 0;
var roomTempSliderMaxIdx = 100;
var roomHumSliderMinIdx = 0;
var roomHumSliderMaxIdx = 100;
var totalReadings = 1440;
var readData = [];
var myRoomTempCtx = document.getElementById("myRoomTempChart");
var myRoomHumCtx = document.getElementById("myRoomHumChart");
var myRoomTempChart, myRoomHumChart;

initializeChart();

function initializeChart() {
	$.ajax({
		url : 'history?num=' + totalReadings,
		success : function(data) {
			readData = data;
			$('#room-temp-subtitle').html(
					"Current temperature: " + data[data.length - 1].temp[0]);

			$('#room-hum-subtitle').html(
					"Current humidity: " + data[data.length - 1].humidity[0]
							+ "%");

			if (typeof myRoomTempChart !== "undefined" && myRoomTempChart !== null)
				myRoomTempChart.destroy();

			myRoomTempChart = createChart(myRoomTempCtx, "temp", 
					roomTempSliderMinIdx, roomTempSliderMaxIdx)

			if (typeof myRoomHumChart !== "undefined" && myRoomHumChart !== null)
				myRoomHumChart.destroy();

			myRoomHumChart = createChart(myRoomHumCtx, "humidity", 
					roomHumSliderMinIdx, roomHumSliderMinIdx)

		}
	});
}

$(function() {
	$("#room-temp-slider").slider(
			{
				values : [ 0, 100 ],
				range : true,
				change : function(event, ui) {

					numFromRoomTempSliders(ui.value, ui.handleIndex);

					if (typeof myRoomTempChart !== "undefined"
							&& myRoomTempChart !== null)
						myRoomTempChart.destroy();

					myRoomTempChart = createChart(myRoomTempCtx, "temp", 
							totalReadings / 100 * roomTempSliderMinIdx, 
							totalReadings / 100	* roomTempSliderMaxIdx);

				}
			});
});

$(function() {
	$("#room-humidity-slider").slider(
			{
				values : [ 0, 100 ],
				range : true,
				change : function(event, ui) {

					numFromRoomHumSliders(ui.value, ui.handleIndex);

					if (typeof myRoomHumChart !== "undefined" && myRoomHumChart !== null)
						myRoomHumChart.destroy();

					myRoomHumChart = createChart(myRoomHumCtx, "humidity",
							totalReadings / 100 * roomHumSliderMinIdx,
							totalReadings / 100 * roomHumSliderMaxIdx);
				}
			});
});

var numFromRoomTempSliders = function(value, index) {
	if (index == 0)
		roomTempSliderMinIdx = value;
	else
		roomTempSliderMaxIdx = value;

	return 1440 / 100 * (roomTempSliderMaxIdx - roomTempSliderMinIdx);

}

var numFromRoomHumSliders = function(value, index) {
	if (index == 0)
		roomHumSliderMinIdx = value;
	else
		roomHumSliderMaxIdx = value;

	return 1440 / 100 * (roomHumSliderMaxIdx - roomHumSliderMinIdx);

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