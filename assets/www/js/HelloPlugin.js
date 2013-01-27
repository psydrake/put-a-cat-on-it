var HelloPlugin = {
	callNativeFunction: function(success, fail, resultType) {
		return cordova.exec(success, fail, 'net.edrake.putacatonit.HelloPlugin', 'nativeAction', [resultType]);		
	}
}