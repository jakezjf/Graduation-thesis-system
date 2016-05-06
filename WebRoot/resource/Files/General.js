var arrCSS=[
    ["<img src='../resource/Skins/Skin_11001/Skin.gif' alt='blue'>","../resource/Skins/Skin_11001/Application.css"],
    ["<img src='../resource/Skins/Skin_11002/Skin.gif' alt='olive green'>","../resource/Skins/Skin_11002/Application.css"],
    ["<img src='../resource/Skins/Skin_11003/Skin.gif' alt='sapphire blue'>","../resource/Skins/Skin_11003/Application.css"],
    ""
];

// *** function to replace href="#" ***
function v(){
	return;
}

// *** Cookies ***
function writeCookie(name, value){ 
	exp = new Date(); 
	exp.setTime(exp.getTime() + (86400 * 1000 * 30));
	document.cookie = name + "=" + escape(value) + "; expires=" + exp.toGMTString() + "; path=/"; 
} 

function readCookie(name){ 
	var search; 
	search = name + "="; 
	offset = document.cookie.indexOf(search); 
	if (offset != -1) { 
		offset += search.length; 
		end = document.cookie.indexOf(";", offset); 
		if (end == -1){
			end = document.cookie.length;
		}
		return unescape(document.cookie.substring(offset, end)); 
	}else{
		return "";
	}
}

////////////////////////////////////
// StyleSheet
////////////////////////////////////
function writeCSS(){
  for(var i=0;i<arrCSS.length;i++){
    document.write('<link title="css'+i+'" href="'+arrCSS[i][1]+'" rel="stylesheet" disabled="true" type="text/css" />');
  }
    setStyleSheet(readCookie("stylesheet"));
}

function writeCSSLinks(){
  for(var i=0;i<arrCSS.length-1;i++){
    if(i>0) document.write('  ');
    document.write('<a href="javascript:v()" onclick="setStyleSheet(\'css'+i+'\')">'+arrCSS[i][0]+'</a>');
  }
}

function setStyleSheet(strCSS){
  var objs=document.getElementsByTagName("link");
  var intFound=0;
  for(var i=0;i<objs.length;i++){
    if(objs[i].type.indexOf("css")>-1&&objs[i].title){
      objs[i].disabled = true;
      if(objs[i].title==strCSS) intFound=i;
    }
  }
  objs[intFound].disabled = false;
  writeCookie("stylesheet",objs[intFound].title);
}

writeCSS();
setStyleSheet(readCookie("stylesheet"));

// ������ʾ������
function ShowHideDiv(init) {
	if(document.getElementById("script").style.display == "block"){
		 document.getElementById("script").style.display = "none";
  }
  else{
  	document.getElementById("script").style.display = "block";
  }
}
