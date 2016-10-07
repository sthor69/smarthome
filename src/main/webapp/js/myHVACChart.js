var tempSliderMinIdx = 0;
var tempSliderMaxIdx = 100;
var humSliderMinIdx = 0;
var humSliderMaxIdx = 100;
var totalReadings = 1440;
var readData;
var tempCtx = document.getElementById("myTempChart");
var humCtx = document.getElementById("myHumChart");

$(function() {
	$("#temp-slider").slider(
			{
				values : [ 1, 100 ],
				range : true,
				change : function(event, ui) {

					var numberOfReadings = numFromTempSliders(ui.value,
							ui.handleIndex);

					$("#temp-sub-title").html(
							"Number of items: " + numberOfReadings + ", min: "
									+ tempSliderMinIdx + ", max: "
									+ tempSliderMaxIdx);

					var myTempChart = new Chart(tempCtx, {
						type : 'line',
						data : {
							labels : readData.slice(numberOfReadings/100*tempSliderMinIdx,
									numberOfReadings/100*tempSliderMaxIdx).map(function(a) {
								return a.time;
							}),
							datasets : [ {
								data : readData.slice(numberOfReadings/100*tempSliderMinIdx,
										numberOfReadings/100*tempSliderMaxIdx).map(function(a) {
									return a.temp[0];
								})
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

$(function() {
	$("#humidity-slider").slider({
		values : [ 1, 100 ],
		range : true,
		change : function(event, ui) {

			var numberOfReadings = numFromHumSliders(ui.value,
					ui.handleIndex);

			$("#hum-sub-title").html(
					"Number of items: " + numberOfReadings + ", min: "
							+ tempSliderMinIdx + ", max: "
							+ tempSliderMaxIdx);

			var myHumChart = new Chart(humCtx, {
				type : 'line',
				data : {
					labels : readData.slice(numberOfReadings/100*humSliderMinIdx,
							numberOfReadings/100*humSliderMaxIdx).map(function(a) {
						return a.time;
					}),
					datasets : [ {
						data : readData.slice(numberOfReadings/100*humSliderMinIdx,
								numberOfReadings/100*humSliderMaxIdx).map(function(a) {
							return a.temp[0];
						})
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
});

$
		.ajax({
			url : 'history?num=' + totalReadings,
			success : function(data) {
				readData = data;
				$('#temp-sub-title')
						.html(
								"Current temperature: "
										+ data[data.length - 1].temp[0]);

				$('#hum-sub-title').html(
						"Current humidity: "
								+ data[data.length - 1].humidity[0] + "%");

				

				var myTempChart = new Chart(tempCtx, {
					type : 'line',
					data : {
						labels : data.map(function(a) {
							return a.time;
						}),
						datasets : [ {
							data : data.map(function(a) {
								return a.temp[0];
							})
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

				var myHumChart = new Chart(humCtx, {
					type : 'line',
					data : {
						labels : data.map(function(a) {
							return a.time;
						}),
						datasets : [ {
							data : data.map(function(a) {
								return a.humidity[0];
							})
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