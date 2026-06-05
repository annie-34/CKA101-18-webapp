<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.emp.model.*"%>

<%
//見com.emp.controller.EmpServlet.java第163行存入req的empVO物件 (此為從資料庫取出的empVO, 也可以是輸入格式有錯誤時的empVO物件)
   GroupBuyVO groupBuyVO = (GroupBuyVO) request.getAttribute("groupBuyVO");
%>
<%-- debug: groupBuyVO null/groupBuyId output hidden for cleaner page --%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>團購資料修改 - update_groupBuy_input.jsp</title>

<style>
  table#table-1 {
	background-color: #CCCCFF;
    border: 2px solid black;
    text-align: center;
  }
  table#table-1 h4 {
    color: red;
    display: block;
    margin-bottom: 1px;
  }
  h4 {
    color: blue;
    display: inline;
  }
</style>

<style>
  table {
	width: 450px;
	background-color: white;
	margin-top: 1px;
	margin-bottom: 1px;
  }
  table, th, td {
    border: 0px solid #CCCCFF;
  }
  th, td {
    padding: 1px;
  }
</style>


<meta name="viewport" content="width=device-width, initial-scale=1.0">
<style id="warm-literary-groupbuy-theme">
  :root {
    --paper: #fffaf0;
    --paper-deep: #f4ead8;
    --ink: #3f3428;
    --muted: #817162;
    --line: #dfd0bd;
    --sage: #7f9676;
    --sage-deep: #5f7558;
    --rose: #c98274;
    --wheat: #efd9ad;
    --white: rgba(255, 255, 255, .86);
    --shadow: 0 18px 45px rgba(89, 67, 45, .14);
  }

  * { box-sizing: border-box; }

  html { min-height: 100%; }

  body {
    min-height: 100vh;
    margin: 0;
    padding: 42px clamp(18px, 4vw, 56px);
    color: var(--ink);
    font-family: "Noto Sans TC", "Microsoft JhengHei", "PingFang TC", Arial, sans-serif;
    line-height: 1.65;
    background:
      radial-gradient(circle at 12% 8%, rgba(239, 217, 173, .48), transparent 28%),
      linear-gradient(135deg, #fffaf0 0%, #f7efe2 48%, #eef3e9 100%);
    overflow-x: auto;
  }

  body::before {
    content: "";
    position: fixed;
    inset: 0;
    pointer-events: none;
    opacity: .38;
    background-image: linear-gradient(rgba(63, 52, 40, .035) 1px, transparent 1px);
    background-size: 100% 30px;
  }

  h3, h4, p, ul, form, table { position: relative; z-index: 1; }

  h3 {
    margin: 22px auto 14px;
    max-width: 1120px;
    color: var(--ink);
    font-size: clamp(22px, 3vw, 34px);
    font-weight: 700;
    letter-spacing: 0;
  }

  h4 {
    color: var(--sage-deep) !important;
    font-weight: 600;
  }

  p, ul {
    max-width: 1120px;
    margin-left: auto;
    margin-right: auto;
  }

  a {
    color: var(--sage-deep);
    font-weight: 700;
    text-decoration: none;
    border-bottom: 1px solid rgba(95, 117, 88, .35);
    transition: color .2s ease, border-color .2s ease, background .2s ease;
  }

  a:hover {
    color: #8f5f54;
    border-color: rgba(201, 130, 116, .65);
  }

  table#table-1 {
    width: min(100%, 1120px) !important;
    min-height: 96px;
    margin: 0 auto 26px !important;
    border: 1px solid rgba(223, 208, 189, .9) !important;
    border-radius: 8px;
    border-collapse: separate !important;
    border-spacing: 0;
    overflow: hidden;
    background: linear-gradient(135deg, rgba(255,255,255,.9), rgba(244,234,216,.9)) !important;
    box-shadow: var(--shadow);
    text-align: left !important;
  }

  table#table-1 td {
    padding: 24px 28px !important;
    border: 0 !important;
    vertical-align: middle;
  }

  table#table-1 h3 {
    margin: 0 0 8px;
    max-width: none;
  }

  table#table-1 img {
    width: auto;
    max-width: 110px;
    height: auto;
    vertical-align: middle;
    border-radius: 6px;
  }

  body > table:not(#table-1), form > table, body > form > table {
    width: min(100%, 1120px) !important;
    margin: 16px auto 24px !important;
    border-collapse: separate !important;
    border-spacing: 0;
    overflow: hidden;
    background: var(--white) !important;
    border: 1px solid var(--line) !important;
    border-radius: 8px;
    box-shadow: 0 10px 28px rgba(89, 67, 45, .10);
  }

  th, td {
    border-color: var(--line) !important;
    padding: 12px 14px !important;
    color: var(--ink);
    vertical-align: middle;
  }

  th {
    background: #ecf1e6;
    color: #42533f;
    font-weight: 700;
    white-space: nowrap;
  }

  tr:nth-child(even) td { background: rgba(255, 250, 240, .68); }
  tr:hover td { background: rgba(239, 217, 173, .32); }

  form {
    max-width: 1120px;
    margin: 0 auto 18px;
  }

  input[type="text"], input[type="datetime-local"], select, textarea {
    width: min(100%, 360px);
    min-height: 42px;
    padding: 9px 12px;
    color: var(--ink);
    background: rgba(255, 255, 255, .94);
    border: 1px solid var(--line);
    border-radius: 8px;
    outline: none;
    font: inherit;
    transition: border-color .2s ease, box-shadow .2s ease, background .2s ease;
  }

  textarea {
    min-height: 110px;
    resize: vertical;
  }

  input:focus, select:focus, textarea:focus {
    border-color: var(--sage);
    box-shadow: 0 0 0 4px rgba(127, 150, 118, .16);
    background: #fff;
  }

  input[type="submit"], input[type="button"] {
    min-height: 40px;
    padding: 9px 18px;
    border: 0;
    border-radius: 999px;
    color: #fff;
    background: linear-gradient(135deg, var(--sage), var(--sage-deep));
    box-shadow: 0 8px 18px rgba(95, 117, 88, .22);
    cursor: pointer;
    font: inherit;
    font-weight: 700;
    transition: transform .18s ease, box-shadow .18s ease, filter .18s ease;
  }

  input[type="submit"]:hover, input[type="button"]:hover {
    transform: translateY(-1px);
    box-shadow: 0 12px 24px rgba(95, 117, 88, .28);
    filter: brightness(1.04);
  }

  font[style*="red"], li[style*="red"] {
    color: #a94c43 !important;
  }

  c\:if + ul, body > ul {
    padding: 18px 22px 18px 42px;
    border-radius: 8px;
    background: rgba(255, 255, 255, .62);
    border: 1px solid rgba(223, 208, 189, .65);
  }

  li { margin: 10px 0; }

  @media (max-width: 720px) {
    body { padding: 24px 12px; }
    table#table-1 td, th, td { padding: 10px !important; }
    input[type="submit"], input[type="button"] { width: 100%; margin-top: 8px; }
    input[type="text"], input[type="datetime-local"], select, textarea { width: 100%; }
  }
</style>
</head>
<body bgcolor='white'>

<table id="table-1">
	<tr><td>
		 <h3>團購資料修改 - update_groupBuy_input.jsp</h3>
		 <h4><a href="<%=request.getContextPath()%>/farmer/select_page.jsp"><img src="images/back.png" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<h3>資料修改:</h3>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<form method="post" action="<%=request.getContextPath()%>/GroupBuyServlet">

<table>
    <tr>
        <td>團購編號:</td>
        <td>${groupBuyVO.groupBuyId}</td>
    </tr>

    <tr>
        <td>農產品編號:</td>
        <td>${groupBuyVO.productId}</td>
    </tr>

    <tr>
        <td>團購主編號:</td>
        <td>${groupBuyVO.hostUserId}</td>
    </tr>

    <tr>
        <td>達標金額:</td>
        <td>${groupBuyVO.targetAmount}</td>
    </tr>

    <tr>
        <td>團購單價:</td>
        <td>${groupBuyVO.groupPrice}</td>
    </tr>

    <tr>
        <td>開團時間:</td>
        <td>${groupBuyVO.openDatetime}</td>
    </tr>

    <tr>
        <td>截止時間:</td>
        <td>${groupBuyVO.ddlDatetime}</td>
    </tr>

    <tr>
        <td>團購狀態:</td>
        <td>${groupBuyVO.status.displayName}</td>
    </tr>

    <tr>
        <td>發起請求時間:</td>
        <td>${groupBuyVO.requestDatetime}</td>
    </tr>

    <tr>
        <td>收件地址:</td>
        <td>${groupBuyVO.pickupAddress}</td>
    </tr>

    <tr>
        <td>請求狀態:</td>
        <td>
            <select name="requestStatus">
                <option value="pending" ${groupBuyVO.requestStatus == 'pending' ? 'selected' : ''}>待審核</option>
                <option value="approved" ${groupBuyVO.requestStatus == 'approved' ? 'selected' : ''}>通過</option>
                <option value="rejected" ${groupBuyVO.requestStatus == 'rejected' ? 'selected' : ''}>拒絕</option>
            </select>
        </td>
    </tr>

    <tr>
        <td>拒絕原因:</td>
        <td>
            <textarea name="rejectReason">${groupBuyVO.rejectReason}</textarea>
        </td>
    </tr>
</table>

<input type="hidden" name="groupBuyId" value="${groupBuyVO.groupBuyId}">
<input type="hidden" name="action" value="update">
<input type="submit" value="送出修改">

</form>

	<jsp:useBean id="groupBuySvc" scope="page" class="com.emp.model.GroupBuyService" />
<!-- 	<tr> -->
<!-- 		<td>部門:<font color=red><b>*</b></font></td> -->
<!-- 		<td><select size="1" name="deptno"> -->
<%-- 			<c:forEach var="deptVO" items="${deptSvc.all}"> --%>
<%-- 				<option value="${deptVO.deptno}" ${(empVO.deptno==deptVO.deptno)?'selected':'' } >${deptVO.dname} --%>
<%-- 			</c:forEach> --%>
<!-- 		</select></td> -->
<!-- 	</tr> -->

</table>




<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->

<%-- <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" /> --%>
<%-- <script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script> --%>
<%-- <script src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script> --%>

<style>
  .xdsoft_datetimepicker .xdsoft_datepicker {
           width:  300px;   /* width:  300px; */
  }
  .xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
           height: 151px;   /* height:  151px; */
  }
</style>

<script>
//         $.datetimepicker.setLocale('zh');
//         $('#f_date1').datetimepicker({
//            theme: '',              //theme: 'dark',
//  	       timepicker:false,       //timepicker:true,
//  	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
//  	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
<%--  		   value: '<%=group_buy_VO.getOpen_datetime()%>', // value:   new Date(), --%>
//            //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
//            startDate:	            '2025/07/10',  // 起始日
//            //minDate:               '-2026-01-01', // 去除今日(不含)之前
//            //maxDate:               '+1970-01-01'  // 去除今日(不含)之後
//         });
        
        
   
        // ----------------------------------------------------------以下用來排定無法選擇的日期-----------------------------------------------------------

        //      1.以下為某一天之前的日期無法選擇
        //      var somedate1 = new Date('2017-06-15');
        //      $('#f_date1').datetimepicker({
        //          beforeShowDay: function(date) {
        //        	  if (  date.getYear() <  somedate1.getYear() || 
        //		           (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
        //		           (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
        //              ) {
        //                   return [false, ""]
        //              }
        //              return [true, ""];
        //      }});

        
        //      2.以下為某一天之後的日期無法選擇
        //      var somedate2 = new Date('2017-06-15');
        //      $('#f_date1').datetimepicker({
        //          beforeShowDay: function(date) {
        //        	  if (  date.getYear() >  somedate2.getYear() || 
        //		           (date.getYear() == somedate2.getYear() && date.getMonth() >  somedate2.getMonth()) || 
        //		           (date.getYear() == somedate2.getYear() && date.getMonth() == somedate2.getMonth() && date.getDate() > somedate2.getDate())
        //              ) {
        //                   return [false, ""]
        //              }
        //              return [true, ""];
        //      }});


        //      3.以下為兩個日期之外的日期無法選擇 (也可按需要換成其他日期)
        //      var somedate1 = new Date('2017-06-15');
        //      var somedate2 = new Date('2017-06-25');
        //      $('#f_date1').datetimepicker({
        //          beforeShowDay: function(date) {
        //        	  if (  date.getYear() <  somedate1.getYear() || 
        //		           (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
        //		           (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
        //		             ||
        //		            date.getYear() >  somedate2.getYear() || 
        //		           (date.getYear() == somedate2.getYear() && date.getMonth() >  somedate2.getMonth()) || 
        //		           (date.getYear() == somedate2.getYear() && date.getMonth() == somedate2.getMonth() && date.getDate() > somedate2.getDate())
        //              ) {
        //                   return [false, ""]
        //              }
        //              return [true, ""];
        //      }});
        
</script>
</html>