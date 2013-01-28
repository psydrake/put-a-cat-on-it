/*
 *  This code is adapted from the work of Michael Nachbaur 
 *  by Simon Madine of The Angry Robot Zombie Factory
 *   - Converted to Cordova 1.6.1 by Josemando Sobral.
 *   - Converted to Cordova 2.0.0 by Simon MacDonald
 *  2012-07-03
 *  MIT licensed
 */
var Screenshot = {
	saveScreenshot: function(success, fail) {
		var ts = Math.round((new Date()).getTime());
		var fileName = "putacatonit_" + ts + ".png"
		var retVal = cordova.exec(success, fail, 'Screenshot', 'saveScreenshot', [fileName]);
		//alert('retVal: ' + retVal);
		return retVal;
	}
}
/*
cordova.define("cordova/plugin/screenshot", function(require, exports, module) {
    var exec = require('cordova/exec');
    
	// This class exposes the ability to take a Screenshot to JavaScript
	var Screenshot = function() {};

	// Save the screenshot to the user's Photo Library
	Screenshot.prototype.saveScreenshot = function() {
		exec(null, null, "Screenshot", "saveScreenshot", []);
	};

    var screenshot = new Screenshot();
    module.exports = screenshot;

});

if (!window.plugins) {
    window.plugins = {};
}
if (!window.plugins.screenshot) {
    window.plugins.screenshot = cordova.require("cordova/plugin/screenshot");
}
*/