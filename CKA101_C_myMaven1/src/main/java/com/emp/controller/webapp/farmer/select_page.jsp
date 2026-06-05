<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>團購</title>

<style>
  table#table-1 {
	width: 450px;
	background-color: #CCCCFF;
	margin-top: 5px;
	margin-bottom: 10px;
    border: 3px ridge Gray;
    height: 80px;
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
   <tr><td><h3>IBM Group_buy: Home</h3><h4>( MVC )</h4></td></tr>
</table>

<p>This is the Home page for IBM  Group_buy: Home</p>

<h3>資料查詢:</h3>
	
<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<ul>
  <li><a href='listAllGroupBuy.jsp'>List</a> all  GroupBuy    <h4>(byDAO).         </h4></li>
  <li><a href="<%=request.getContextPath()%>/GroupBuyServlet?action=getAll">List</a> all  GroupBuy    <h4>(getFromSession).</h4> <br><br><br></li>
  
  <li>
    <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/GroupBuyServlet" >
        <b>輸入團購編號:</b>
        <input type="text" name="groupBuyId">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="送出">                   <h4>(資料格式驗證  by Controller ).</h4> 
    </FORM>
  </li>
  
  <li>
    <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/GroupBuyServlet" name="form1" >
        <b>輸入團購編號:</b>
        <input type="text" name="groupBuyId">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="button" value="送出" onclick="fun1()">  <h4>(資料格式驗證  by Java Script).</h4> 
    </FORM>
  </li>
  <% com.emp.model.GroupBuyHibernateDAO dao = new com.emp.model.GroupBuyHibernateDAO(); %> <% pageContext.setAttribute("dao", dao) ; %> <!-- p204 , p337 -->

<%--   <jsp:useBean id="dao" scope="page" class="com.emp.model.EmpDAO" /> --%>
   
  <li>
     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/GroupBuyServlet">
       <b>選擇團購編號:</b>
       <select size="1" name="groupBuyId">
         <c:forEach var="groupBuyVO" items="${dao.all}" > 
          <option value="${groupBuyVO.groupBuyId}">
          ${groupBuyVO.groupBuyId}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
    </FORM>
  </li>
  
  <li>
     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/GroupBuyServlet" >
       <b>選擇農產品編號:</b>
       <select size="1" name="groupBuyId">
         <c:forEach var="groupBuyVO" items="${dao.all}" > 
          <option value="${groupBuyVO.groupBuyId}">${groupBuyVO.productId}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
     </FORM>
  </li>
</ul>

<ul>
  <li>
    <a href="<%=request.getContextPath()%>/GroupBuyServlet?action=add_page">
      新增團購
    </a>
  </li>
</ul>

<script>    
   function fun1(){
      with(document.form1){
         if (groupBuyId.value=="") 
             alert("請輸入團購編號!");
         else if (isNaN(groupBuyId.value)) 
             alert("團購編號格式不正確!");
         else if ((groupBuyId.value <= 0)) 
             alert("請填寫0以上數字!");
         else
             submit();
      }
   }
</script>

</body>
</html>