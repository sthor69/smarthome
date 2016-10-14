var roomWaterSliderMinIdx = 0;
var roomWaterSliderMaxIdx = 100;
var corrWaterSliderMinIdx = 0;
var corrWaterSliderMaxIdx = 100;
var myCorrWaterCtx = $("#myCorrWaterChart");
var myCorrWaterChart;
var readData = [];
var totalReadings = 1440;


initializeWaterChart();

function initializeWaterChart() {
	$.ajax({
		url : 'history?type=monitor&num=' + totalReadings,
		success : function(data) {
			readData = data;
			$('corr-water-subtitle').html(
					"Current water level: " + readData[readData.length - 1].water[0]);
			
			if (typeof myCorrWaterChart != "undefined"
				&& myCorrWaterChart != null)
			myCorrWaterChart.destroy();

		myCorrWaterChart = createChart(myCorrWaterCtx, "water",
				corrWaterSliderMinIdx, corrWaterSliderMaxIdx, 0);
		}
	});
}

$(function() {
	$("#corr-water-slider").slider(
			{
				values : [ 0, 100 ],
				range : true,
				change : function(event, ui) {

					numFromCorrWaterSliders(ui.value, ui.handleIndex);

					if (typeof myCorrWaterChart != "undefined"
							&& myCorrWaterChart != null)
						myCorrWaterChart.destroy();

					myCorrWaterChart = createChart(myCorrWaterCtx, "water",
							totalReadings / 100 * corrWaterSliderMinIdx,
							totalReadings / 100 * corrWaterSliderMaxIdx, 0);

				}
			});
});

var numFromCorrWaterSliders = function(value, index) {
	if (index == 0)
		corrWaterSliderMinIdx = value;
	else
		corrWaterSliderMaxIdx = value;

	return 1440 / 100 * (corrWaterSliderMaxIdx - corrWaterSliderMinIdx);

}

function createChart(ctx, dataType, min, max, sensorIdx) {
	return new Chart(ctx, {
		type : 'line',
		data : {
			labels : readData.slice(min, max).map(function(a) {
				return a.time;
			}),
			datasets : [ {
				data : readData.slice(min, max).map(function(a) {
					return a[dataType][sensorIdx];
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