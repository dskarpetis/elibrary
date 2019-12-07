/*
 * Javascript code required for the library project.
 */
function blockPage() {
	$
			.blockUI({
				message : '<div id="please-wait-div"><h2><span class="glyphicon glyphicon-refresh glyphicon-refresh-animate" /> Please wait...</h2></div>',
				css : {
					border : '0px none',
					top : '0',
					left : '0',
					width : '100%',
					opacity : '0.9',
					cursor : 'auto'
				},
				overlayCSS : {
					backgroundColor : '#60A19D',
					opacity : '0.6',
					cursor : 'auto'
				}
			});
}

function unblockPage() {
	$.unblockUI();
}

function dateOfBirthDatePicker() {
	// console.log("asd");
	$('[data-toggle="tooltip"]').tooltip();
	$('[data-toggle="popover"]').popover();
	$('.datepicker2').datepicker({
		format : 'dd/mm/yyyy',
		startView : 2,
		minViewMode : 0,
		maxViewMode : 2,
		todayHighlight : true,
		language : 'el',
		autoclose : true,
		endDate : new Date()
	}).datepicker("show");
}

function yearPublishDatePicker() {
	// console.log("asd");
	$('[data-toggle="tooltip"]').tooltip();
	$('[data-toggle="popover"]').popover();
	$('.datepicker3').datepicker({
		format : 'yyyy',
		startView : 2,
		minViewMode : 2,
		maxViewMode : 2,
		language : 'el',
		autoclose : true,
		endDate : new Date()
	}).datepicker("show");
}

$(document).ready(function() {
	$(document).on('click', ".datepicker2", function() {
		dateOfBirthDatePicker();
	});
	$(document).on('click', ".datepicker3", function() {
		yearPublishDatePicker();
	});
});

$(function() {

	// We can attach the `fileselect` event to all file inputs on the page
	$(document).on(
			'change',
			':file',
			function() {
				var input = $(this), numFiles = input.get(0).files ? input
						.get(0).files.length : 1, label = input.val().replace(
						/\\/g, '/').replace(/.*\//, '');
				input.trigger('fileselect', [ numFiles, label ]);
			});

	// We can watch for our custom `fileselect` event like this
	$(document).ready(
			function() {
				$(':file').on(
						'fileselect',
						function(event, numFiles, label) {
							var input = $(this).parents('.input-group').find(
									':text'), log = numFiles > 1 ? numFiles
									+ ' files selected' : label;

							if (input.length) {
								input.val(log);
							} else {
								if (log)
									alert(log);
							}

						});
			});

});
