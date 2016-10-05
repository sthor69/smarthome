var hist;
$.ajax({
	url : 'history?num=100',
	success : function(data) {
		$('#temp-sub-title').html(
				"Current temperature: " + data[data.length - 1].temp[0]);
		
		$('#hum-sub-title').html(
				"Current humidity: " + data[data.length - 1].humidity[0] + "%");
		
		var tempCtx = document.getElementById("myTempChart");
		var humCtx = document.getElementById("myHumChart");
		
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