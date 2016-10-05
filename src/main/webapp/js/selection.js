$('#light-img').hover(function() {
        $(this).css('cursor','pointer');
    });

$('#energy-img').hover(function() {
    $(this).css('cursor','pointer');
});

$('#hvac-img').hover(function() {
    $(this).css('cursor','pointer');
});

$('#monitor-img').hover(function() {
    $(this).css('cursor','pointer');
});

$("#hvac-img").click(function() {
	$("#detail-fragment").load("pages/HVAC-fragment.jsp");
});