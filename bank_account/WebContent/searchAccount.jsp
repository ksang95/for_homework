<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
	$('#form_submit').click(submit);
	$('input').keyup(function(){
		$('.error').text('');
	});
});

function submit(){
	if($('#search_form')[0].checkValidity()){
	$.ajax({
		url : 'getAccount.do',
		type : 'post',
		dataType : 'json',
		data : $('#search_form').serializeArray(),
		success : ((data)=>{
			display(data);
		}),
		error : ((e)=>{
			alert('error : '+e);
		})
	});}
	else{
		$('.error').text('계좌번호를 정확히 입력하세요. 예)000-000000');
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
	$('.search').hide();
	$('.result').show();
}
</script>
</head>
<body>
<section class='center'>
	<div class='title'>내 통장 정보 검색</div>
	<div class='search'>
		<form class='form1' id='search_form'>
			<div class='form1_input'>
				<label for='accountnum'>계좌번호</label><input type='text' class='input_text'
					id='accountnum' name='accountnum' required='required' pattern='^[0-9]{3}-[0-9]{6}$'><span id='caution'>'-' 포함</span>
			</div>
			<div class='error'></div>
			<button type='button' class='btn' id='form_submit'>검색</button>
			<a href='bankHome.jsp'><button type='button' class='btn'>홈으로</button></a>
		</form>
	</div>
	<div class='result'></div>
	</section>
</body>
</html>