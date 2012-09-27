<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Home</title>
<link rel="stylesheet"
	href='<c:url value="/resources/css/${skin }/jquery-ui-1.8.23.custom.css"/>' />
<link rel="stylesheet"
	href='<c:url value="/resources/themes/${skin }/jquery.ui.all.css"/>' />
<link rel="stylesheet"
	href='<c:url value="/resources/css/default.css"/>' />
<script type='text/javascript'
	src='<c:url value="/resources/js/jquery-1.8.2.min.js"/>'></script>
<script type='text/javascript'
	src='<c:url value="/resources/js/jquery-ui-1.8.23.custom.min.js"/>'></script>
<script type='text/javascript'
	src='<c:url value="/resources/ui/jquery.ui.core.js"/>'></script>
<script type='text/javascript'
	src='<c:url value="/resources/js/common.js"/>'></script>
</head>
<jsp:useBean id="today" class="java.util.Date" />
<body>
	<div id="allWrapper">
		<div id="tMenu"></div>
		<div id="tContent">
			<div id="currentDate">
				<h1>
					<c:choose>
						<c:when test="${!empty year}">${year }년 <fmt:formatNumber value="${month }" pattern="00"/>월 <fmt:formatNumber value="${day }" pattern="00"/>일</c:when>
						<c:otherwise><fmt:formatDate value="${today }" pattern="yyyy년 MM월 dd일" /></c:otherwise>
					</c:choose>
				</h1>
			</div>
			<table style="width: 80%" id="templateTable">
				<tr>
					<td><label for="tName">이름</label> <input type="text"
						name="tName" id="tName" class="templeteData" /></td>
					<td><label for="tMoney">금액</label> <input type="text"
						name="tMoney" id="tMoney" class="templeteData" /></td>
					<td><label for="tEtc">비고</label> <input type="text"
						name="tEtc" id="tEtc" class="templeteData" /></td>
					<td><button type="button" id="addItemBtn">추가</button></td>
				</tr>
			</table>
			<br />
			<h2>목록</h2>
			<form id="formFinances" name="formFinances" action="" method="post">
				<table>
					<tr>
						<td><label for="customer">거래처</label><select id="customer" name="customer">
						<c:forEach items="${customerList }" var="cl">
							<option value="${cl._idx }" <c:if test="${(!empty param.customer) and (cl._idx eq param.customer)}">selected='selected'</c:if>>${cl.customerName }</option>
						</c:forEach>
						</select></td>
						<td>
							<button class="addCustomer" type="button">거래처 등록</button>
						</td>
						<td>
							<button class="sendMail" type="button">거래처에 메일보내기</button>
						</td>
					</tr>
				</table><br/>
				<c:choose>
					<c:when test="${!empty year }">
						<input type="hidden" name="year" id="year" value='${year}' />
						<input type="hidden" name="month" id="month" value='${month }' />
						<input type="hidden" name="day" id="day" value='${day }' />
					</c:when>
					<c:otherwise>
						<input type="hidden" name="year" id="year" value='<fmt:formatDate value="${today }" pattern="yyyy" />' />
						<input type="hidden" name="month" id="month" value='<fmt:formatDate value="${today }" pattern="MM" />' />
						<input type="hidden" name="day" id="day" value='<fmt:formatDate value="${today }" pattern="dd" />' />
					</c:otherwise>
				</c:choose>
				<table id="finances">
					<tr>
						<th>이름</th>
						<th>금액</th>
						<th>비고</th>
						<th></th>
					</tr>
					<c:if test="${!empty list }">
						<c:forEach items="${list }" var="l">
						<tr>
							<td><input type="text" id="fName" name="fName" value="${l.fName }" /></td>
							<td><input type="text" id="fMoney" name="fMoney" value="${l.fMoney }" /></td>
							<td><input type="text" id="fEtc" name="fEtc" value="${l.fEtc }" /></td>
							<td><button type="button" class='delBtn'>삭제</button></td>
						</tr>
						</c:forEach>
					</c:if>
				</table>
				<button>저장</button>
			</form>
			<div>
				<p>합계 : <span id="summeryMoney">${summeryMoney } 원</span></p>
			</div>
		</div>
		<div id="lContent">
			<div id="ucalendar"></div>
			<br />
			<h2>월간 합계</h2>
			<table style="width: 80%; border: 1px solid;">
				<tr>
					<th>월</th>
					<th>금액</th>
				</tr>
				<c:forEach begin="1" step="1" end="12" var="ca">
				<tr><td><fmt:formatNumber value="${ca }" pattern="00"/>월</td><td><span id='smonth<fmt:formatNumber value="${ca }" pattern="00"/>'>0원</span></td></tr>
				</c:forEach>
			</table>
		</div>
	</div>
<div id="dialog-form" title="거래처등록">
	<p class="validateTips"></p>
	<form name="customerForm" id="customerForm">
	<fieldset>
		<label for="customerName">거래처명</label>
		<input type="text" name="customerName" id="customerName" class="text ui-widget-content ui-corner-all" /><br/>
		<label for="customerEmail">이메일</label>
		<input type="text" name="customerEmail" id="customerEmail" value="" class="text ui-widget-content ui-corner-all" />
	</fieldset>
	</form>
</div>
<script type="text/javascript">
	$(function(){
		$("#ucalendar").datepicker({
			showMonthAfterYear : true,
			regional : 'ko',
			dateFormat : 'yy년 mm월 dd일',
			monthNames : [ '1월', '2월', '3월', '4월','5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월' ],
			monthNamesShort : [ '1월', '2월', '3월',	'4월', '5월', '6월', '7월', '8월','9월', '10월', '11월', '12월' ],
			dayNames : [ '일', '월', '화', '수', '목','금', '토' ],
			dayNamesShort : [ '일', '월', '화', '수','목', '금', '토' ],
			dayNamesMin : [ '일', '월', '화', '수','목', '금', '토' ],
			changeYear : true,
			changeMonth : true,
			onSelect : function(dateText, inst) {
				$("#currentDate").html("<h1>" + dateText+ "</h1>");
				var year = inst.currentYear;
				var month = ((Number(inst.currentMonth)+1)+'').padLeft(2, '0');
				var day = (inst.currentDay+'').padLeft(2,'0');
				$("#year").val(year);
				$("#month").val(month);
				$("#day").val(day);
				document.location.href = '<c:url value="/"/>?year='+year+'&month='+month+'&day='+day;
			}
		});
		// 옵션추가 버튼 클릭시
		$("#addItemBtn").click(function() {
			// item 의 최대번호 구하기
			var newitem = "<tr>";
			newitem += "<td><input type='text' name='fName' id='fName'></td>";
			newitem += "<td><input type='text' name='fMoney' id='fMoney'></td>";
			newitem += "<td><input type='text' name='fEtc' id='fEtc'></td>";
			newitem += "<td><button class='delBtn'>삭제</button></td>";
			newitem += "</tr>";
			$("#finances").append(newitem);
			var lastItemNo = $("#finances tr:last").index();
			var lastItem = $("#finances tr").eq(lastItemNo);
			lastItem.removeClass();
			lastItem.find("td:eq(0)").attr("rowspan", "1");
			lastItem.addClass("item"+ (parseInt(lastItemNo) + 1));
			lastItem.find(":input[name='fName']").val($("#tName").val());
			lastItem.find(":input[name='fMoney']").val($("#tMoney").val());
			lastItem.find(":input[name='fEtc']").val($("#tEtc").val());
			$(".templeteData").val('');
			
			$("button").button();
			$("input[type=text]").addClass("text ui-widget-content ui-corner-all");
		});

		// 삭제버튼 클릭시
		$(".delBtn").live("click",function() {
			var clickedRow = $(this).parent().parent();
			var cls = clickedRow.attr("class");

			// 각 항목의 첫번째 row를 삭제한 경우 다음 row에 td 하나를 추가해 준다.
			if (clickedRow.find("td:eq(0)").attr("rowspan")) {
				if (clickedRow.next().hasClass(cls)) {
					clickedRow.next().prepend(clickedRow.find("td:eq(0)"));
				}
			}
			clickedRow.remove();
			// rowspan 조정
			resizeRowspan(cls);
		});

	// cls : rowspan 을 조정할 class ex) item1, item2, ...
	function resizeRowspan(cls) {
		var rowspan = $("." + cls).length;
		$("." + cls + ":first td:eq(0)").attr("rowspan", rowspan);
	}
	$(":submit").click(function(event) {
		event.preventDefault();
		var params = {
			data : JSON.stringify($("#formFinances").serializeObject())
		};
		$.post('<c:url value="/doSave"/>', params, function() {
		}).complete(function() {
			document.location.reload();
		});
	});
	
	$("button").button();
	$("input[type=text]").addClass("text ui-widget-content ui-corner-all");
	
	$(".addCustomer").click(function() {
		$( "#dialog-form" ).dialog( "open" );
	});
	
	$( "#dialog-form" ).dialog({
		autoOpen: false,
		height: 500,
		width: 450,
		modal: true,
		buttons: {
			"등록": function() {
				var param = {
					data : JSON.stringify($("#customerForm").serializeObject())
				};
				$.post('<c:url value="/registerCustomer"/>', param, function() {
				}).complete(function() {
					$( this ).dialog( "close" );
					document.location.reload();
				});
			},
			"취소": function() {
				$( this ).dialog( "close" );
			}
		},
		close: function() {
			$('form').each(function(){
				if ( this.name = 'customerForm' ) this.reset();
			});
		}
	});
	
	$("#customer").change(function() {
		var year = $("#year").val();
		var month = $("#month").val();
		var day = $("#day").val();
		document.location.href = '<c:url value="/"/>?year='+year+'&month='+month+'&day='+day+'&customer='+$("#customer").val();
	});
	
	$(".sendMail").click(function(){
		var d = {
				customer : $("#customer").val()
				, year : $("#year").val()
				, month : $("#month").val()
				, day : $("#day").val()				
		};
		
		var params = {
			data : JSON.stringify(d)
		};
		$.post('<c:url value="/doSendMail"/>', params, function(){});
	});
});
</script>
<c:if test="${empty customerList }">
<script type="text/javascript">
$(function(){
	$( "#dialog-form" ).dialog("open");
});
</script>
</c:if>
<c:if test="${!empty year }">
<script type="text/javascript">
$(function(){
	$("#ucalendar").datepicker( "setDate" , "${year}년 ${month}월 ${day}일" );
});
</script>
</c:if>
<c:if test="${!empty summeryMonthMoney }">
<c:forEach items="${summeryMonthMoney }" var="smm">
<script type="text/javascript">
$(function(){
	$("#smonth${smm.fmonth}").html("${smm.summoney}원");
});
</script>
</c:forEach>
</c:if>
</body>
</html>
