var myXmlHttpRequest="";
function getXmlObject(){
	var xmlHttpRequest;
	try {
		xmlHttpRequest = new XMLHttpRequest();
	} catch (e) {
		try {
			xmlHttpRequest = new ActiveObject("Msxml2.XMLHTTP");
		} catch (e) {
			try{
				xmlHttpRequest = new ActiveObject("Microsoft.XMLHTTP");
			}catch(e){
				alert("您的浏览器不支持AJAX！");
				return false; 
			}
		}
	}
	return xmlHttpRequest;
}

//验证AB类学生
function is_AB(is_poor){
	var stu_id=document.getElementById('stu_id').value;
	var stu_name=document.getElementById('stu_name').value;
	myXmlHttpRequest = getXmlObject();
	if(myXmlHttpRequest){
		var url="user.php?act=is_AB&stu_id="+stu_id+"&stu_name="+stu_name+"&time="+new Date().getTime() ;
		myXmlHttpRequest.open("post",url,true);
		myXmlHttpRequest.onreadystatechange = function () {
			if(myXmlHttpRequest.readyState==4){
				var str_1 = myXmlHttpRequest.responseText;
				alert(str_1);
			}
		};
		myXmlHttpRequest.send(null);
	}
}

//根据区id查询学校
function getSchools(area){
	myXmlHttpRequest = getXmlObject();
	if(myXmlHttpRequest){
		var url="user.php?act=school&area="+area+"&time="+new Date().getTime() ;
		myXmlHttpRequest.open("post",url,true);
		myXmlHttpRequest.onreadystatechange = function () {
			if(myXmlHttpRequest.readyState==4){
				var str_1 = myXmlHttpRequest.responseText;
				document.getElementById("school_id").innerHTML = str_1;
			}
		};
		myXmlHttpRequest.send(null);
	}
}
//根据学校查询年级
function getGrade(school){
	myXmlHttpRequest = getXmlObject();
	if(myXmlHttpRequest){
		var url="user.php?act=grade&school="+school+"&time="+new Date().getTime() ;
		myXmlHttpRequest.open("post",url,true);
		myXmlHttpRequest.onreadystatechange = function () {
			if(myXmlHttpRequest.readyState==4){
				var str_2 = myXmlHttpRequest.responseText;
				document.getElementById("grade_id").innerHTML = str_2;
			}
		};
		myXmlHttpRequest.send(null);
	}
}
//根据年级查询班级
function getClasses(grade){
	myXmlHttpRequest = getXmlObject();
	if(myXmlHttpRequest){
		var url="user.php?act=classes&grade="+grade+"&time="+new Date().getTime() ;
		myXmlHttpRequest.open("post",url,true);
		myXmlHttpRequest.onreadystatechange = function () {
			if(myXmlHttpRequest.readyState==4){
				var str_3 = myXmlHttpRequest.responseText;
				document.getElementById("classes_id").innerHTML = str_3;
			}
		};
		myXmlHttpRequest.send(null);
	}
}