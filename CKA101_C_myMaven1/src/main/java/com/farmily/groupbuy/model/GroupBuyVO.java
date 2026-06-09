package com.farmily.groupbuy.model;
import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
@Entity
@Table(name = "group_buy")
public class GroupBuyVO implements java.io.Serializable{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="group_buy_id",updatable=false)
	private Integer groupBuyId;
	
	@Column(name="product_id")
	private Integer productId;
	
	@Column(name="host_user_id")
	private Integer hostUserId ;
	
	@Column(name="target_amount")
	private Integer targetAmount;
	
	@Column(name="group_price")
	private Integer groupPrice ;
	

	@Column(name="open_datetime")
	private Timestamp openDatetime ;

	@Column(name="ddl_datetime")
	private Timestamp ddlDatetime ;

	@Enumerated(EnumType.STRING)
	@Column(name = "status",
	    columnDefinition = "ENUM('open','success','failed','cancelled','pending')")
	private GroupBuyStatus status;

	@Column(name="created_at")
	private Timestamp createdAt;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "request_status",columnDefinition = "ENUM('pending','approved','rejected')")
	private RequestStatus requestStatus;
	
	@Column(name="request_datetime")
	private Timestamp requestDatetime;
	
	@Column(name="reply_datetime")
	private Timestamp replyDatetime;
	
	@Column(name="reject_reason")
	private String rejectReason;
	@Column(name="pickup_address")
	private String pickupAddress;

	public GroupBuyVO() {
		super();
	}

	public GroupBuyVO(Integer groupBuyId, Integer productId, Integer hostUserId, Integer targetAmount,
	        Integer groupPrice, Timestamp openDatetime, Timestamp ddlDatetime, GroupBuyStatus status,
	        Timestamp createdAt, RequestStatus requestStatus, Timestamp requestDatetime,
	        Timestamp replyDatetime, String rejectReason, String pickupAddress) {
	    super();
	    this.groupBuyId = groupBuyId;
	    this.productId = productId;
	    this.hostUserId = hostUserId;
	    this.targetAmount = targetAmount;
	    this.groupPrice = groupPrice;
	    this.openDatetime = openDatetime;
	    this.ddlDatetime = ddlDatetime;
	    this.status = status;
	    this.createdAt = createdAt;
	    this.requestStatus = requestStatus;
	    this.requestDatetime = requestDatetime;
	    this.replyDatetime = replyDatetime;
	    this.rejectReason = rejectReason;
	    this.pickupAddress = pickupAddress;
	}

	public String getPickupAddress() {
		return pickupAddress;
	}

	public void setPickupAddress(String pickupAddress) {
		this.pickupAddress = pickupAddress;
	}

	public Integer getGroupBuyId() {
		return groupBuyId;
	}

	public void setGroupBuyId(Integer groupBuyId) {
		this.groupBuyId = groupBuyId;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public Integer getHostUserId() {
		return hostUserId;
	}

	public void setHostUserId(Integer hostUserId) {
		this.hostUserId = hostUserId;
	}

	public Integer getTargetAmount() {
		return targetAmount;
	}

	public void setTargetAmount(Integer targetAmount) {
		this.targetAmount = targetAmount;
	}

	public Integer getGroupPrice() {
		return groupPrice;
	}

	public void setGroupPrice(Integer groupPrice) {
		this.groupPrice = groupPrice;
	}

	public Timestamp getOpenDatetime() {
		return openDatetime;
	}

	public void setOpenDatetime(Timestamp openDatetime) {
		this.openDatetime = openDatetime;
	}

	public Timestamp getDdlDatetime() {
		return ddlDatetime;
	}

	public void setDdlDatetime(Timestamp ddlDatetime) {
		this.ddlDatetime = ddlDatetime;
	}



	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public GroupBuyStatus getStatus() {
	    return status;
	}

	public void setStatus(GroupBuyStatus status) {
	    this.status = status;
	}

	public RequestStatus getRequestStatus() {
	    return requestStatus;
	}

	public void setRequestStatus(RequestStatus requestStatus) {
	    this.requestStatus = requestStatus;
	}

	public Timestamp getRequestDatetime() {
		return requestDatetime;
	}

	public void setRequestDatetime(Timestamp requestDatetime) {
		this.requestDatetime = requestDatetime;
	}

	public Timestamp getReplyDatetime() {
		return replyDatetime;
	}

	public void setReplyDatetime(Timestamp replyDatetime) {
		this.replyDatetime = replyDatetime;
	}

	public String getRejectReason() {
		return rejectReason;
	}

	public void setRejectReason(String rejectReason) {
		this.rejectReason = rejectReason;
	}

	
	
	

}
