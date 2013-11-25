
//�����ǳ�
function checkNick(){
	//�����ǳ�
		var nick = $("#inpu_userName").val();
		//����Ϊ��
		if(null == nick || "" == nick){
			$("#tip").html("�ǳƲ���Ϊ��").css("color","red");
			flag = false;
			return false;
		}
		//���ܳ���40�ֽ�
		var len = 0;
		for(var i = 0;i < nick.length;i++){
			var str = nick.substr(i,i+1);
			//���������
			if(escape(str).indexOf("%u") != -1){
				len += 2;
			}else{
				len += 1;
			}
		}
		if(len > 40){
			$("#tip").html("�ǳ��е㳤������").css("color","red");
			return false;
		}
		//�����õĻ�
		$("#tip").html(" ");
		return true;
}

//��������
function checkEmail(){
		var email = $("#inpu_email").val();
		//���䲻��Ϊ��
		if(null == email || "" == email){
			$("#tip").html("���䲻��Ϊ��").css("color","red");
			return false;
		}
		//�����ʽҪ��ȷ
		 var filter  = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
		 if (filter.test(email)){
			 
			 $("#tip").html("");
		 }else{
			 $("#tip").html("�����ʽ���԰�").css("color","red");
			 return false;
		 }
		 $("#tip").html("");
		 return true;
}

//��������
function checkPassword(){
	var password = $("#inpu_password").val();
	//����Ϊ��
	if(null == password || "" == password){
		$("#tip").html("���벻��Ϊ�հ�").css("color","red");
		return false;
	}
	//���벻������6λ����16λ
	if(password.length < 6){
		$("#tip").html("����̫����").css("color","red");
		return false;
	}
	if(password.length > 16){
		$("#tip").html("����̫����").css("color","red");
		return false;
	}
	$("#tip").html("");
	return true;
}


$(function(){
	
		//�Ȱ�ע�ᰴť�û�
	if(!$("#inpu_checkbox").is(":checked")){
		$("#submit").prop("disabled", true).css("color","grey");
	}
	//�ǳ�У��
	$("#inpu_userName").blur(function(){
		checkNick();
	});
	
	
	//�����У��
	$("#inpu_email").blur(function(){
		//������벻���򲻽���ajaxУ��
		if(checkEmail()){
			var email = $("#inpu_email").val();
			$.ajax({
			type: "post",
			url: "servlet/CheckServlet",
			data: "email=" + email,
			success: function(data){
				if("1" == data){
					$("#tip").html("");
				}else{
					$("#tip").html(email + "�ѱ�ע�������ֱ�ӵ�¼����").css("color","red");
					return false;
				}
			}
		});
		}

	});
	
	//�������
	$("#inpu_password").blur(function(){
		//������ϸ�����
		if(!checkPassword()){
			$("#inpu_password").val("");
		}
	});
	
	$("#inpu_password1").blur(function(){
		var password = $("#inpu_password").val();
		var password1 = $("#inpu_password1").val();
		if(password != password1){
			$("#tip").html("ȷ�����벻��������").css("color","red");
		}else{
			$("#tip").html("");
		}
	});
	
	//����ѡͬ��Լ��ʱ  ����ע�ᰴť
	$("#inpu_checkbox").click(function(){
		var f = $("#inpu_checkbox").is(":checked");
		if(f){
			$("#submit").prop("disabled", false).addClass("register").css("color","white").css("cursor","hand");
		}else{
			$("#submit").prop("disabled", true).css("color","grey").css("cursor","auto");
		}
	});
	
	//���ύʱ����  ��ע�ᰴť�û�   ǰ̨����һ���ظ��ύ
	$("form").submit(function(){
		alert("woshisjsjsj");
		if(checkNick() && checkEmail() && checkPassword()){
			alert("������");
			//���ύʱ�ٽ�����֤
			var value = $("#validataImg").val();
			value = encodeURI(value);
			$.get("servlet/CheckImagValue", {value:value},
					function(data){
					   if("1" === data){
						   //�����֤����ȷ���ύ
						   alert("�ύ��ע��");
						   $("#submit").prop("disabled", true);
						   return true;
					   }else{
						   $("#tip").html("��֤�벻��").css("color","red");
						   return false;
					   }
			});
		}else{
			return false;
		}		
	});
	
});
//��֤��ͼƬ
$(function(){
	$("#changeValidataImg").css("cursor","hand");

	$("#changeValidataImg").click(function(){
		$(this).attr("src","/taobaoke/servlet/YanzhengmaImag?ddd=" + new Date().getTime());
	});
	
	$("#validataImg").blur(function(){
		//����ajaxУ��
		var value = $("#validataImg").val();
		value = encodeURI(value);
		$.get("servlet/CheckImagValue", {value:value},
				function(data){
				   if("1" != data){
					   $("#tip").html("��֤�벻��").css("color","red");
				   }
				   if("1" === data){
					   $("#tip").html("��֤����ȷ").css("color","green");
				   }
		});
	});
});
