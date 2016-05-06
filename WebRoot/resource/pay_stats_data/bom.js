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
//根据区id查询学校
function getSchools(act,area){
	myXmlHttpRequest = getXmlObject();
	if(myXmlHttpRequest){
		var url=act+"?act=school&area="+area+"&time="+new Date().getTime() ;
		myXmlHttpRequest.open("post",url,true);
		myXmlHttpRequest.onreadystatechange = function () {
			if(myXmlHttpRequest.readyState==4){
				var str = myXmlHttpRequest.responseText;
				document.getElementById("school_id").innerHTML = str;
			}
		};
		myXmlHttpRequest.send(null);
	}
}
//根据学校查询年级
function getGrade(act,school){
	myXmlHttpRequest = getXmlObject();
	if(myXmlHttpRequest){
		var url=act+"?act=grade&school="+school+"&time="+new Date().getTime() ;
		myXmlHttpRequest.open("post",url,true);
		myXmlHttpRequest.onreadystatechange = function () {
			if(myXmlHttpRequest.readyState==4){
				var str = myXmlHttpRequest.responseText;
				document.getElementById("grade_id").innerHTML = str;
			}
		};
		myXmlHttpRequest.send(null);
	}
}
//根据年级查询班级
function getClasses(act,grade){
	myXmlHttpRequest = getXmlObject();
	if(myXmlHttpRequest){
		var url=act+"?act=classes&grade="+grade+"&time="+new Date().getTime() ;
		myXmlHttpRequest.open("post",url,true);
		myXmlHttpRequest.onreadystatechange = function () {
			if(myXmlHttpRequest.readyState==4){
				var str = myXmlHttpRequest.responseText;
				document.getElementById("classes_id").innerHTML = str;
			}
		};
		myXmlHttpRequest.send(null);
	}
}

function getPayType(act,pay_id){
	var use = document.getElementById("user_id").value;
	if(use==''){
		alert("请选择缴费的用户!");
		return false;
	}
	if(pay_id==''){
		alert("请选择缴费的月份!");
		return false;
	}
	myXmlHttpRequest = getXmlObject();
	if(myXmlHttpRequest){
		var url=act+"?act=payType&pay_id="+pay_id+"&time="+new Date().getTime() ;
		myXmlHttpRequest.open("post",url,true);
		myXmlHttpRequest.onreadystatechange = function () {
			if(myXmlHttpRequest.readyState==4 && myXmlHttpRequest.status == 200){
				var result = myXmlHttpRequest.responseText;
				var json = eval("("+ result +")");
				document.getElementById("money").value = json['money'];
				document.getElementById("disc_value").value = json['disc_value'];
				document.getElementById("price").value = json['price'];
				document.getElementById("pay_start_time").value = json['pay_start_time'];
				document.getElementById("pay_finish_time").value = json['pay_finish_time'];
			}
		};
		myXmlHttpRequest.send(null);
	}
}

function getUserPay(){
	var user_name = document.getElementById("user_name_0").value;
	var stu_name = document.getElementById("stu_name_0").value;
	var true_name = document.getElementById("true_name_0").value;
	var mobile_phone = document.getElementById("mobile_phone_0").value;
	myXmlHttpRequest = getXmlObject();
	if(myXmlHttpRequest){
		var url="charge.php?act=seaUser&user_name="+user_name+"&stu_name="+stu_name +"&truename="+true_name+"&mobile_phone="+mobile_phone;
		myXmlHttpRequest.open("post",url,true);
		myXmlHttpRequest.onreadystatechange = function () {
			if(myXmlHttpRequest.readyState==4 && myXmlHttpRequest.status == 200){
				document.getElementById("user_list_foo").innerHTML  = myXmlHttpRequest.responseText;
			}
		};
		myXmlHttpRequest.send(null);
	}
}