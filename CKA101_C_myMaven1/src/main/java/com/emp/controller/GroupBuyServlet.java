package com.emp.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

import com.emp.model.GroupBuyService;
import com.emp.model.GroupBuyVO;
import com.emp.model.ProductOptionVO;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.emp.model.GroupBuyStatus;
import com.emp.model.RequestStatus;
@WebServlet("/GroupBuyServlet")//對應到html的action，代表當接收到html的action，從html接收後才可以找得到對應的servlet
public class GroupBuyServlet extends HttpServlet {
	public void doGet(HttpServletRequest req,HttpServletResponse res) throws ServletException,IOException {
		doPost(req,res);
	}
	public void doPost(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException{
		req.setCharacterEncoding("UTF-8");
		String action=req.getParameter("action");
		if ("getAll".equals(action)) {
		    GroupBuyService groupBuySvc = new GroupBuyService();
		    List<GroupBuyVO> list = groupBuySvc.getAll();

		    req.setAttribute("list", list);

		    String url = "/farmer/listAllGroupBuy.jsp";
		    RequestDispatcher successView = req.getRequestDispatcher(url);
		    successView.forward(req, res);
		    return;
		}
		
		if("getOne_For_Display".equals(action)) {
			List<String> errorMsgs=new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			String str=req.getParameter("groupBuyId"); //這邊的group_buy_id對應到html的input text name
			if(str==null||(str.trim()).length()==0) {
				errorMsgs.add("請輸入團購編號");
			}
			if(!errorMsgs.isEmpty()) {//如果錯誤不是空的話代表有錯會進入這個if
				RequestDispatcher failureView=req.getRequestDispatcher("/farmer/select_page.jsp");
				failureView.forward(req,res);//使用forward會保留剛剛的request來秀出error訊息，如果使用redirect的話會是重新發送一個req
				return;
			}
			Integer groupBuyId=null;
			try {
				groupBuyId=Integer.valueOf(str);
			}catch(Exception e) {
				errorMsgs.add("團購編號格式不正確");
			}
			if(!errorMsgs.isEmpty()) {
				RequestDispatcher failureView=req.getRequestDispatcher("/farmer/select_page.jsp");
				failureView.forward(req, res);
				return;
			}
			GroupBuyService groupBuySvc =new GroupBuyService();
			GroupBuyVO groupBuyVO =groupBuySvc.getOneGroupBuy(groupBuyId);
			if(groupBuyVO==null) {
				errorMsgs.add("查無資料");
			}
			if(!errorMsgs.isEmpty()) {
				RequestDispatcher failureView=req.getRequestDispatcher("/farmer/select_page.jsp");
				failureView.forward(req, res);
				return;
			}
			req.setAttribute("groupBuyVO", groupBuyVO);// 資料庫取出的empVO物件,存入req
			String url="/farmer/listOneGroupBuy.jsp";
			RequestDispatcher successView=req.getRequestDispatcher(url);// 成功轉交 listOneEmp.jsp
			successView.forward(req, res);
			return;
		}
		if("getOne_For_Update".equals(action)) {
		    List<String> errorMsgs = new LinkedList<>();
		    req.setAttribute("errorMsgs", errorMsgs);
		    Integer groupBuyId = null;
		    try {
		        String groupBuyIdStr = req.getParameter("groupBuyId");
		        if (groupBuyIdStr == null || groupBuyIdStr.trim().length() == 0) {
		            errorMsgs.add("團購編號不可空白");
		        } else {
		            groupBuyId = Integer.valueOf(groupBuyIdStr.trim());
		        }
		    } catch (NumberFormatException e) {
		        errorMsgs.add("團購編號格式不正確");
		    }
		    if (!errorMsgs.isEmpty()) {
		        RequestDispatcher failureView = req.getRequestDispatcher("/farmer/select_page.jsp");
		        failureView.forward(req, res);
		        return;
		    }
		    GroupBuyService groupBuySvc = new GroupBuyService();
		    GroupBuyVO groupBuyVO = groupBuySvc.getOneGroupBuy(groupBuyId);
		    if (groupBuyVO == null) {
		        errorMsgs.add("查無資料");
		        RequestDispatcher failureView = req.getRequestDispatcher("/farmer/select_page.jsp");
		        failureView.forward(req, res);
		        return;
		    }
		    req.setAttribute("groupBuyVO", groupBuyVO);
		    String url = "/farmer/update_groupBuy_input.jsp";
		    RequestDispatcher successView = req.getRequestDispatcher(url);
		    successView.forward(req, res);
		    return;
		}
	
		
		if ("update".equals(action)) {
		    List<String> errorMsgs = new LinkedList<>();
		    req.setAttribute("errorMsgs", errorMsgs);
		    Integer groupBuyId = Integer.valueOf(req.getParameter("groupBuyId").trim());

		    String requestStatus = req.getParameter("requestStatus");

		    if (requestStatus == null ||
		        !(requestStatus.equals("pending") ||
		          requestStatus.equals("approved") ||
		          requestStatus.equals("rejected"))) {

		        errorMsgs.add("請求狀態錯誤，請重新輸入");
		    }

		    String rejectReason = req.getParameter("rejectReason");

		    if ("rejected".equals(requestStatus)) {
		        if (rejectReason == null || rejectReason.trim().length() == 0) {
		            errorMsgs.add("拒絕原因不可為空白");
		        }
		    } else {
		        rejectReason = null;
		    }

		    GroupBuyService groupBuySvc = new GroupBuyService();
		    GroupBuyVO groupBuyVO = groupBuySvc.getOneGroupBuy(groupBuyId);
		    

		    if (requestStatus != null &&
		        (requestStatus.equals("pending") ||
		         requestStatus.equals("approved") ||
		         requestStatus.equals("rejected"))) {
		        groupBuyVO.setRequestStatus(RequestStatus.valueOf(requestStatus));
		    }
		    groupBuyVO.setRejectReason(rejectReason == null ? null : rejectReason.trim());
		    if (!errorMsgs.isEmpty()) {
		        req.setAttribute("groupBuyVO", groupBuyVO);
		        RequestDispatcher failureView = req.getRequestDispatcher("/farmer/update_groupBuy_input.jsp");
		        failureView.forward(req, res);
		        return;
		    }
		    groupBuyVO = groupBuySvc.reviewGroupBuy(groupBuyId, requestStatus, rejectReason);

		    req.setAttribute("groupBuyVO", groupBuyVO);

		    String url = "/farmer/listOneGroupBuy.jsp";
		    RequestDispatcher successView = req.getRequestDispatcher(url);
		    successView.forward(req, res);
		    return;
		}
		if ("add_page".equals(action)) {
		    GroupBuyService groupBuySvc = new GroupBuyService();
		    List<ProductOptionVO> productList = groupBuySvc.getProductOptions();
		    req.setAttribute("productList", productList);
		    RequestDispatcher successView =
		    req.getRequestDispatcher("/farmer/addGroupBuy.jsp");
		    successView.forward(req, res);
		    return;
		}
		if("insert".equals(action)) {
			List<String>errorMsgs=new LinkedList<>();
			req.setAttribute("errorMsgs", errorMsgs);
			Integer productIdInt=null;
			String productId=req.getParameter("productId");
			if(productId==null||productId.trim().length()==0) {
				errorMsgs.add("請選擇農產品");
			}else {
				try {
					productIdInt = Integer.valueOf(productId.trim());
				} catch(NumberFormatException e) {
					errorMsgs.add("農產品編號格式錯誤");
				}
			}
			// TODO: 之後會員登入功能整合後，改成從 session 取得登入者 ID
			Integer hostUserIdInt = 1;

			Integer targetAmountInt=null;
			String targetAmount=req.getParameter("targetAmount");
			String targetAmountReg="^[0-9]+$";
			if(targetAmount==null||targetAmount.trim().length()==0) {
				errorMsgs.add("達標金額請勿空白");
			}else if(!targetAmount.trim().matches(targetAmountReg)) {
				errorMsgs.add("達標金額只能是數字");
			}else {
			    targetAmountInt = Integer.valueOf(targetAmount.trim());
			}
			String groupPrice=req.getParameter("groupPrice");
			String groupPriceReg="^[0-9]+$";
			Integer groupPriceInt=null;
			if(groupPrice==null||groupPrice.trim().length()==0) {
				errorMsgs.add("團購單價請勿空白");
			}else if(!groupPrice.trim().matches(groupPriceReg)) {
				errorMsgs.add("團購單價只能輸入數字");
			}else {
				groupPriceInt=Integer.valueOf(groupPrice.trim());
			}
			java.sql.Timestamp openDatetime = null;
			String openDatetimeStr = req.getParameter("openDatetime");
			try {
			    if (openDatetimeStr == null || openDatetimeStr.trim().length() == 0) {
			        errorMsgs.add("請輸入開團日期與時間");
			    } else {
			        openDatetime = java.sql.Timestamp.valueOf(
			            openDatetimeStr.replace("T", " ") + ":00"
			        );
			    }
			} catch (IllegalArgumentException e) {
			    errorMsgs.add("請輸入正確的開團日期與時間");
			}
			java.sql.Timestamp ddlDatetime = null;
			String ddlDatetimeStr = req.getParameter("ddlDatetime");

			try {
			    if (ddlDatetimeStr == null || ddlDatetimeStr.trim().length() == 0) {
			        errorMsgs.add("請輸入截止日期與時間");
			    } else {
			        ddlDatetime = java.sql.Timestamp.valueOf(
			            ddlDatetimeStr.replace("T", " ") + ":00"
			        );
			    }
			} catch (IllegalArgumentException e) {
			    errorMsgs.add("請輸入正確的截止日期與時間");
			}
			String pickupAddress=req.getParameter("pickupAddress");
			if(pickupAddress==null||pickupAddress.trim().length()==0) {
				errorMsgs.add("收件地址請勿空白");
			}
			Timestamp requestDatetime = new Timestamp(System.currentTimeMillis());

			GroupBuyVO groupBuyVO = new GroupBuyVO();
			groupBuyVO.setProductId(productIdInt);
			groupBuyVO.setHostUserId(hostUserIdInt);
			groupBuyVO.setTargetAmount(targetAmountInt);
			groupBuyVO.setGroupPrice(groupPriceInt);
			groupBuyVO.setOpenDatetime(openDatetime);
			groupBuyVO.setDdlDatetime(ddlDatetime);
			groupBuyVO.setRequestDatetime(requestDatetime);
			groupBuyVO.setStatus(GroupBuyStatus.pending);
			groupBuyVO.setRequestStatus(RequestStatus.pending);
			groupBuyVO.setPickupAddress(pickupAddress);
			
			if (!errorMsgs.isEmpty()) {
			    GroupBuyService groupBuySvc = new GroupBuyService();
			    List<ProductOptionVO> productList = groupBuySvc.getProductOptions();
			    req.setAttribute("productList", productList);
			    req.setAttribute("groupBuyVO", groupBuyVO);
			    RequestDispatcher failureView =
			        req.getRequestDispatcher("/farmer/addGroupBuy.jsp");
			    failureView.forward(req, res);
			    return;
			}
			
			GroupBuyService groupBuySvc=new GroupBuyService();
			groupBuyVO=groupBuySvc.addGroupBuy(productIdInt, hostUserIdInt, targetAmountInt, groupPriceInt, openDatetime, ddlDatetime,pickupAddress);
			String url="/farmer/listAllGroupBuy.jsp";
			RequestDispatcher successView=req.getRequestDispatcher(url);
			successView.forward(req, res);
			return;
		}
		if("delete".equals(action)) {
		    List<String> errorMsgs = new LinkedList<>();
		    req.setAttribute("errorMsgs", errorMsgs);
		    Integer groupBuyId = null;
		    String groupBuyIdStr = req.getParameter("groupBuyId");
		    try {
		        if (groupBuyIdStr == null || groupBuyIdStr.trim().length() == 0) {
		            errorMsgs.add("團購編號不可空白");
		        } else {
		            groupBuyId = Integer.valueOf(groupBuyIdStr.trim());
		        }
		    } catch (NumberFormatException e) {
		        errorMsgs.add("團購編號格式不正確");
		    }

		    if (!errorMsgs.isEmpty()) {
		        RequestDispatcher failureView = req.getRequestDispatcher("/farmer/listAllGroupBuy.jsp");
		        failureView.forward(req, res);
		        return;
		    }
		    GroupBuyService groupBuySvc = new GroupBuyService();
		    groupBuySvc.deleteGroupBuy(groupBuyId);
		    String url = "/farmer/listAllGroupBuy.jsp";
		    RequestDispatcher successView = req.getRequestDispatcher(url);
		    successView.forward(req, res);
		    return;
		}
	}
}