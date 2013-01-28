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

// Camera functions
function takePicture() {
	navigator.camera.getPicture(onCameraSuccess, onCameraFail, {
			quality: 60,
	        sourceType: navigator.camera.PictureSourceType.CAMERA,
	        mediaType: navigator.camera.MediaType.PICTURE,			
			destinationType: Camera.DestinationType.FILE_URI,
			targetWidth: 1024,
			targetHeight: 768,
			correctOrientation: true
		}
	); 
}

function onCameraSuccess(imageURI) {
    var image = document.getElementById('main');
    image.src = imageURI;
}

function onCameraFail(message) {
    alert('Failed because: ' + message);
}

// Screenshot native plugin call
function callScreenshotPlugin() {
	$('.hideOnScreenshot').hide(); // remove on-screen widgets for clean screenshot
	setTimeout(function() { // timeout to give hide() time to complete
		Screenshot.saveScreenshot(screenshotResultHandler, nativePluginErrorHandler);
	}, 1000);
}

// Share native plugin call
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