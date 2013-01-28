function putCat() {
	$("#overlay").show();
	$("#put").hide();
	$("#remove").show();
}

function removeCat() {
	$("#overlay").hide();
	$("#remove").hide();
	$("#put").show();
}

function callScreenshotPlugin() {
	$('.hideOnScreenshot').hide(); // remove on-screen widgets for clean screenshot
	setTimeout(function() { // timeout to give hide() time to complete
		Screenshot.saveScreenshot(screenshotResultHandler, nativePluginErrorHandler);
	}, 1000);
}

function callSharePlugin(fileName) {
	window.plugins.share.show({
	    subject: 'Put A Cat On It',
	    fileName: fileName},
	    function() {}, // Success function
	    function() {alert('Share failed')} // Failure function
	);
}

function screenshotResultHandler(fileName) {
	alert("Successful cat insertion!"
			+ "\r\nFile saved as '" + fileName + "'");
   	callSharePlugin(fileName);
	$('.hideOnScreenshot').fadeIn('fast');   	   	
}

function nativePluginErrorHandler (error) {
	alert("ERROR: \r\n" + error);
	$('.hideOnScreenshot').fadeIn('fast');
} 