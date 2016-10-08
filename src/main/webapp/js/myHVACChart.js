var tempSliderMinIdx = 0;
var tempSliderMaxIdx = 100;
var humSliderMinIdx = 0;
var humSliderMaxIdx = 100;
var totalReadings = 1440;
var readData;
var tempCtx = document.getElementById("myTempChart");
var humCtx = document.getElementById("myHumChart");
var myTempChart, myHumChart;

$(function() {
	$("#temp-slider").slider(
			{
				values : [ 1, 100 ],
				range : true,
				change : function(event, ui) {

					numFromTempSliders(ui.value,
							ui.handleIndex);

					$("#temp-sub-title").html(
							"Number of items: " + totalReadings + ", min: "
									+ tempSliderMinIdx + ", max: "
									+ tempSliderMaxIdx);

					if (typeof myTempChart !== "undefined")
						myTempChart.destroy();
					
					myTempChart = new Chart(tempCtx, {
						type : 'line',
						data : {
							labels : readData.slice(totalReadings/100*tempSliderMinIdx,
									totalReadings/100*tempSliderMaxIdx).map(function(a) {
								return a.time;
							}),
							datasets : [ {
								data : readData.slice(totalReadings/100*tempSliderMinIdx,
										totalReadings/100*tempSliderMaxIdx).map(function(a) {
									return a.temp[0];
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

			numFromHumSliders(ui.value,
					ui.handleIndex);

			$("#hum-sub-title").html(
					"Number of items: " + totalReadings + ", min: "
							+ totalReadings/100*humSliderMinIdx + ", max: "
							+ totalReadings/100*humSliderMaxIdx);

			if (typeof myHumChart !== "undefined")
				myHumChart.destroy();
			
			myHumChart = new Chart(humCtx, {
				type : 'line',
				data : {
					labels : readData.slice(totalReadings/100*humSliderMinIdx,
							totalReadings/100*humSliderMaxIdx).map(function(a) {
						return a.time;
					}),
					datasets : [ {
						data : readData.slice(totalReadings/100*humSliderMinIdx,
								totalReadings/100*humSliderMaxIdx).map(function(a) {
							return a.humidity[0];
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

				

				if (typeof myTempChart !== "undefined")
					myTempChart.destroy();
				
				myTempChart = new Chart(tempCtx, {
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

				if (typeof myHumChart !== "undefined")
					myHumChart.destroy();
				
				myHumChart = new Chart(humCtx, {
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
		});