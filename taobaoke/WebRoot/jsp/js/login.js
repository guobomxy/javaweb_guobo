$(function(){
	$("form").submit(function(){
		if($("#userName").val() == null || "" == $("#userName").val()){
			
			alert("wwwwwwwwwwwwww");
			return false;
		}
		if($("#password").val() == null || "" == $("#password").val()){
			alert("����Ϊ�գ�����");
			return false;
		}
		return true;
	});
});