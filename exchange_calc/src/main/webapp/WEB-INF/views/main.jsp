<%@ page session="false" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html lang="en">
<head>
<title>환율변환</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<!--jquery cdn-->
<script src="https://code.jquery.com/jquery-2.2.4.min.js"></script>
<!--폰트어섬추가 -->
<link rel="stylesheet" type="text/css" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<!--폰트어섬추가 -->
<!--bootstrap js 추가 -->
<script type="text/javascript" src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
<script>

//환율계산
function exchange(){
	var country = $('#country').val();
	var sendPrice = $('#sendPrice').val();
	
	if(country == ""){
		$('#errPrice').show();
		$('#errPrice').text('국가를 선택해주세요');
		return;
	}
	
	if(sendPrice == ""){
		$('#errPrice').show();
		$('#errPrice').text('송금액을 입력해주세요');
		return;
	}else{
		if(parseInt(sendPrice) == 0){
			$('#errPrice').show();
			$('#errPrice').text('0USD 이상은 입력해주세요');
			return;
		}
		
		if(parseInt(sendPrice)>10000){
			$('#errPrice').show();
			$('#errPrice').text('10000USD 이상은 입력이 불가능합니다');
			return;
		}
	}
	
	console.log("sendPrice:::"+sendPrice);
	
	$('#errPrice').hide();
	$.ajax({
        url: "/exchange",
        type: "POST",
        dataType : "JSON",
        data: {
        	country : $('#country').val()
        },
        success: function(data){
       	  if(data != "99"){        
       		 $('#resualArea').show();
	   	  	 
       		 var resultPrice = sendPrice * data;
	   	  	 $('#exchange').text(comma(formatter.format(data)) + " "+ $("#country option:selected").text());
	   	  	 
	   	  	 var tmp = $("#country option:selected").text().split("/");	   	  	
	   	  	 $('#resultPrice').text(comma(formatter.format(resultPrice)) +" "+tmp[0]);
   		  }else{
   			 alert('환율 정보를 불러오는데 실패하였습니다');
   		  }
        },
        error: function(){
        	 alert('환율 정보를 불러오는데 실패하였습니다');
        }
           
     });	
}

var formatter = new Intl.NumberFormat('en-US', {
    style: 'currency',
    currency: 'USD',
    minimumFractionDigits: 2,
 });
 
 //
 
function comma(val){
	
  var tmp = /(^[+-]?\d+)(\d{3})/;
     var result = val + '';
   while (tmp.test(result)) {
	   result = result.replace(tmp, ',');
   }
   //test
   result = result.replace('$','');
   return result;
}


//숫자만 들어오는 경우를 체크
function onlynumber(e) {
	var input = e.target;
	var key = e.key;
	var keyCode = e.keyCode;
	
	if(!inputKeyNumberCheck(key, keyCode)) {
		e.preventDefault();
	}
}

//preventDefault 를 하여도 한글이 입력되는 문제때문에 추가
function removeChar(e) {
	var key = e.keyCode;
	var input = e.target;

	switch (true) {
	case key === 'ArrowRight':
	case key === 'ArrowLeft':
	case key === 'Backspace':
		return;
	default: 
		input.value = input.value.replace(/[^0-9]/g, '');
	}
}


</script>
<div class="container" style="width:50%;">
   <h1>환율 계산</h1>
   <div>송금국가 : 미국(USD)</div>
   <div>수취국가 : 
   		<select name="country" id="country">
   			<option value="">:::선택:::</option>
   			<option value="USDKRW">KRW/USD</option>
   			<option value="USDJPY">JPY/USD</option>
   			<option value="USDJPHP">PHP/USD</option>
   		</select>
   </div>
   <div>
   		환율 : <span id="exchange"></span>
   </div>
   <div>송금액 : <input type="number" onkeydown="onlynumber(event)" onkeyup="removeChar(event)" name="sendPrice" id="sendPrice" value="0" maxlength="5"> USD</div>
   <div style="display:none;font-size:0.9em;color:red;text-align:left;" id="errPrice"></div>
   <br/><div><input type="button" value="submit" onclick="javascript:exchange();"></div>
   
   <br/><br/><h1 style="display:none;" id="resualArea">수취금액은 <span id="resultPrice"></span>입니다 </h1>
</div>
</body>  
</html> 
