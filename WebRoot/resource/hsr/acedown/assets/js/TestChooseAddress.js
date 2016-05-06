function getAddress(regionId,type) {
  var array = {};
  if(type==0) {//省级列表
	var obj = address['0'];
	var len = obj.length;
	for(var i=0; i<len; i++) {
		var key = obj[i][0];
		var value = obj[i][1];
		array[key] = value;
	}
  } else if(type==1) {//市级列表
	var str = regionId.substring(0,2);
	var obj = address['1'];
	var len = obj.length;
	for(var i=0; i<len; i++) {
		var key = obj[i][0];
		var value = obj[i][1];
		if(key.substring(0,2)==str) {array[key] = value;}
	}
  } else if(type==2) {//区县级列表
	var str = regionId.substring(0,4);
	var obj = address['2'];
	var len = obj.length;
	for(var i=0; i<len; i++) {
		var key = obj[i][0];
		var value = obj[i][1];
		if(key.substring(0,4)==str) {array[key] = value;}
	}
  }
  return array;
}