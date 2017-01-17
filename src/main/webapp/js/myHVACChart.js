var places = [ "room", "chld" ];
var types = [ "temp", "humidity" ];
var handles = [ "min", "max" ];

var totalReadings = 1440;
var readData = [];

var sliderIndex = [];
var myCtx = [];
var myChart = [];

for (var place = 0; place < places.length; place++) {

	sliderIndex[place] = [];
	myCtx[place] = [];
	myChart[place] = [];

	for (var type = 0; type < types.length; type++) {

		sliderIndex[place][type] = [];
		sliderIndex[place][type][0] = 0;
		sliderIndex[place][type][1] = 100;

//		myCtx[place][type] = $("#my" + places[place] + types[type] + "Chart");
	    myCtx[place][type] = "my" + places[place] + types[type] + "Chart";

    }
}

initializeMeasureChart();

function initializeMeasureChart() {

	$
			.ajax({

				url : 'history?type=measure&num=' + totalReadings,

				success : function(data) {
					readData = data;

					for (var place = 0; place < places.length; place++) {

						for (var type = 0; type < types.length; type++) {

							$(
									'#' + places[place] + '-' + types[type]
											+ '-subtitle')
									.html(
											"Current " + types[type] + ": "
													+ readData[readData.length - 1][types[type]][0]);
							
//							if (typeof myChart[place][type] != "undefined"
//									&& myChart[place][type] != null)
//								myChart[place][type].destroy();
//
//							myChart[place][type] = createChart(
//									myCtx[place][type], types[type],
//									sliderIndex[place][type][0],
//									sliderIndex[place][type][1], 0);
							createChart(myCtx[place][type], types[type], 0);

						}

					}
				}
			});
}

//for (var place = 0; place < places.length; place++) {
//	
//	for (var type = 0; type < types.length; type++) {
//		
//		$("#" + places[place] + "-" + types[type] + "-slider").slider(
//				{
//					values : [ 0, 100 ],
//					range : true,
//					change : function(event, ui) {
//
//						var id = event.target.id.split("-");
//						var idPlace = places.indexOf(id[0]);
//						var idType = types.indexOf(id[1]);
//												
//						if (ui.handleIndex == 0)
//							sliderIndex[idPlace][idType][0] = ui.value;
//						else
//							sliderIndex[idPlace][idType][1] = ui.value;
//
//						if (typeof myChart[idPlace][idType] !== "undefined"
//								&& myChart[idPlace][idType] !== null)
//							myChart[idPlace][idType].destroy();
//
//						myChart[idPlace][idType] = createChart(myCtx[idPlace][idType],
//								types[idType], totalReadings / 100
//										* sliderIndex[idPlace][idType][0],
//								totalReadings / 100
//										* sliderIndex[idPlace][idType][1], 0);
//                        createChart(myCtx[idPlace][idType], types[idType], 0);
//					}
//				});
//	}
//}

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
	initializeMeasureChart();
};

function oldcreateChart(ctx, dataType, min, max, sensorIdx) {
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
};

function createChart(ctx, dataType, sensorIdx) {
    console.log(ctx);
    Highcharts.chart(ctx , {
        chart: {
            zoomType: 'x'
        },
        title: {
            text: dataType + ' in ' + places[sensorIdx]
        },
        subtitle: {
            text: document.ontouchstart === undefined ?
                'Click and drag in the plot area to zoom in' : 'Pinch the chart to zoom in'
        },
        xAxis: {
            type: 'datetime'
        },
        yAxis: {
            title: {
                text: dataType
            }
        },
        legend: {
            enabled: false
        },
        plotOptions: {
            area: {
                fillColor: {
                    linearGradient: {
                        x1: 0,
                        y1: 0,
                        x2: 0,
                        y2: 1
                    },
                    stops: [
                        [0, Highcharts.getOptions().colors[0]],
                        [1, Highcharts.Color(Highcharts.getOptions().colors[0]).setOpacity(0).get('rgba')]
                    ]
                },
                marker: {
                    radius: 2
                },
                lineWidth: 1,
                states: {
                    hover: {
                        lineWidth: 1
                    }
                },
                threshold: null
            }
        },

        series: [{
            type: 'area',
            name: dataType,
            data: readData.map(function(a) {
					return a[dataType][sensorIdx];
            })
        }]
    });
};
