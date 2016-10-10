var tempSliderMinIdx = 0;
var tempSliderMaxIdx = 100;
var humSliderMinIdx = 0;
var humSliderMaxIdx = 100;
var totalReadings = 1440;
var readData = [];
var myTempCtx = document.getElementById("myTempChart");
var myHumCtx = document.getElementById("myHumChart");
var myTempChart, myHumChart;

$(function() {
	$("#temp-slider").slider(
			{
				values : [ 0, 100 ],
				range : true,
				change : function(event, ui) {

					numFromTempSliders(ui.value, ui.handleIndex);

//					$("#temp-sub-title").html(
//							"Current temperature: " + readData[readData.length - 1].temp[0]);

					if (typeof myTempChart !== "undefined" && myTempChart !== null)
						myTempChart.destroy();

					myTempChart = createChart(myTempCtx, "temp", 
							totalReadings / 100 * tempSliderMinIdx,
							totalReadings / 100 * tempSliderMaxIdx);
					
//					myTempChart = new Chart(myTempCtx, {
//						type : 'line',
//						data : {
//							labels : readData.slice(
//									totalReadings / 100 * tempSliderMinIdx,
//									totalReadings / 100 * tempSliderMaxIdx)
//									.map(function(a) {
//										return a.time;
//									}),
//							datasets : [ {
//								data : readData.slice(
//										totalReadings / 100 * tempSliderMinIdx,
//										totalReadings / 100 * tempSliderMaxIdx)
//										.map(function(a) {
//											return a.temp[0];
//										})
//							} ]
//						},
//						options : {
//							elements : {
//								point : {
//									radius : 0
//								}
//							},
//							scales : {
//								yAxes : [ {
//									ticks : {
//										beginAtZero : false
//									}
//								} ]
//							}
//						}
//					});
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
	$("#humidity-slider").slider(
			{
				values : [ 0, 100 ],
				range : true,
				change : function(event, ui) {

					numFromHumSliders(ui.value, ui.handleIndex);

//					$("#hum-sub-title").html(
//							"Number of items: " + totalReadings + ", min: "
//									+ totalReadings / 100 * humSliderMinIdx
//									+ ", max: " + totalReadings / 100
//									* humSliderMaxIdx);

					if (typeof myHumChart !== "undefined" && myHumChart !== null)
						myHumChart.destroy();

					myHumChart = createChart(myHumCtx, "humidity", 
							totalReadings / 100 * humSliderMinIdx, 
							totalReadings / 100 * humSliderMaxIdx);
					
//					myHumChart = new Chart(myHumCtx, {
//						type : 'line',
//						data : {
//							labels : readData.slice(
//									totalReadings / 100 * humSliderMinIdx,
//									totalReadings / 100 * humSliderMaxIdx).map(
//									function(a) {
//										return a.time;
//									}),
//							datasets : [ {
//								data : readData.slice(
//										totalReadings / 100 * humSliderMinIdx,
//										totalReadings / 100 * humSliderMaxIdx)
//										.map(function(a) {
//											return a.humidity[0];
//										})
//							} ]
//						},
//						options : {
//							elements : {
//								point : {
//									radius : 0
//								}
//							},
//							scales : {
//								yAxes : [ {
//									ticks : {
//										beginAtZero : false
//									}
//								} ]
//							}
//						}
//					});
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

				if (typeof myTempChart !== "undefined" && myTempChart !== null)
					myTempChart.destroy();

				myTempChart = createChart(myTempCtx, "temp", 
						tempSliderMinIdx, tempSliderMaxIdx)
				
//				myTempChart = new Chart(myTempCtx, {
//					type : 'line',
//					data : {
//						labels : readData.map(function(a) {
//							return a.time;
//						}),
//						datasets : [ {
//							data : readData.map(function(a) {
//								return a.temp[0];
//							})
//						} ]
//					},
//					options : {
//						elements : {
//							point : {
//								radius : 0
//							}
//						},
//						scales : {
//							yAxes : [ {
//								ticks : {
//									beginAtZero : false
//								}
//							} ]
//						}
//					}
//				});

				if (typeof myHumChart !== "undefined" && myHumChart !== null)
					myHumChart.destroy();
				
				myHumChart = createChart(myHumCtx, "humidity", 
						humSliderMinIdx, humSliderMinIdx)

//				myHumChart = new Chart(myHumCtx, {
//					type : 'line',
//					data : {
//						labels : readData.map(function(a) {
//							return a.time;
//						}),
//						datasets : [ {
//							data : readData.map(function(a) {
//								return a.humidity[0];
//							})
//						} ]
//					},
//					options : {
//						elements : {
//							point : {
//								radius : 0
//							}
//						},
//						scales : {
//							yAxes : [ {
//								ticks : {
//									beginAtZero : false
//								}
//							} ]
//						}
//					}
//				});
			}
		});

$(function() {
	$("#timeframe").onchange = function() {
		var sel = $("#timeframe").selectedIndex;

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

	};
});

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