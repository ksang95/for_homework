<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상희 은행</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
<link rel="stylesheet" href="css/account.css">
<style type="text/css">

</style>
<script type="text/javascript">
$(function(){
	$('.result').hide();
	if(${param.type eq 'saving'})
		$('.title').text('적금 통장 개설');
	else if(${param.type eq 'loan'})
		$('.title').text('대출 통장 개설');
	$('#form_submit').click(submit);
	$('input').keyup(function(){
		$('.error').text('');
	});
});

function submit(url1){
	if($('#create_form')[0].checkValidity()){
	$.ajax({
		url : 'createAccount.do',
		type : 'post',
		dataType : 'json',
		data : $('#create_form').serializeArray(),
		success : ((data)=>{
			display(data);
		}),
		error : ((e)=>{
			alert('error : '+e);
		})
	});}
	else{
		$('.error').text('정보를 정확히 입력하세요.');
	}
}
function display(account){
	let tag="";
	if(account.type=='saving'){
		tag+="<h2 class='account_type'>적금 통장</h2>";
		tag+="<div class='account_info'><h3>이름</h3>"+account.name+"</div>";
		tag+="<div class='account_info'><h3>기간</h3>"+account.term+" 개월</div>";
		tag+="<div class='account_info'><h3>월 입금액</h3>"+account.monthly+" 원</div>";
		tag+="<div class='account_info'><h3>이자액</h3>"+account.interest+" 원</div>";
		tag+="<div class='account_info'><h3>이율</h3>"+account.rate+" %</div>";
		tag+="<div class='account_info'><h3>계좌번호</h3>"+account.accountnum+"</div>";
		tag+="<div class='account_info'><h3>총납입금액</h3>"+account.total+" 원</div>";
		tag+="<div class='account_info'><h3>만기시 환급액</h3>"+account.refund+" 원</div>";
	}
	else if(account.type=='loan'){
		tag+="<h2 class='account_type'>대출 통장</h2>";
		tag+="<div class='account_info'><h3>이름</h3>"+account.name+"</div>";
		tag+="<div class='account_info'><h3>기간</h3>"+account.term+" 개월</div>";
		tag+="<div class='account_info'><h3>대출금액</h3>"+account.loan+" 원</div>";
		tag+="<div class='account_info'><h3>이자액</h3>"+account.interest+" 원</div>";
		tag+="<div class='account_info'><h3>이율</h3>"+account.rate+" %</div>";
		tag+="<div class='account_info'><h3>계좌번호</h3>"+account.accountnum+"</div>";
		tag+="<div class='account_info'><h3>월 상환액</h3>"+account.monthly+" 원</div>";
	}
	tag+="<div class='link'><a href='bankHome.jsp'><button class='btn'>홈으로</button></a></div>";
	$('.result').html(tag);
	$('.create').hide();
	$('.result').show();
}
</script>
</head>
<body>
<section class='center'>
<div class='title'>통장 개설</div>
<div class='create'>
<form class='form2' id='create_form'>
<c:if test="${param.type eq 'saving'}">
<input type='hidden' name='type' value='saving'/>
<div class='form2_input'>
<div><label for='name1'>이름</label><span><input type='text' class='input_text' name='name' id='name1' required="required"></span></div>
<div><label for='term1'>기간</label><span><input type='text' class='input_text' name='term' id='term1' required="required" pattern='^[0-9]*$'>개월</span></div>
<div><label for='monthly1'>월 입금액</label><span><input type='text' class='input_text' name='monthly' id='monthly1' required="required" pattern='^[0-9]*$'>원</span></div>
</div>
</c:if>
<c:if test="${param.type eq 'loan'}">
<input type='hidden' name='type' value='loan'/>
<div class='form2_input'>
<div><label for='name2'>이름</label><span><input type='text' class='input_text' name='name' id='name2' required="required"></span></div>
<div><label for='term2'>기간</label><span><input type='text' class='input_text' name='term' id='term2' required="required" pattern='^[0-9]*$'>개월</span></div>
<div><label for='loan'>대출금액</label><span><input type='text' class='input_text' name='loan' id='loan' required="required" pattern='^[0-9]*$'>원</span></div>
</div>
</c:if>
<div class='error'></div>
<button type='button' class='btn' id='form_submit'>개설하기</button>
<a href='bankHome.jsp'><button type='button' class='btn'>홈으로</button></a>
</form>
</div>
<div class='result'>
</div>
</section>
</body>
</html>