package com.farmily.groupbuy.model;

import java.sql.Timestamp;
import java.util.List;

import com.farmily.product.ProductOptionVO;

public class GroupBuyService {
    private GroupBuyDAO_interface dao;
    public GroupBuyService() {
        dao = new GroupBuyHibernateDAO();
    }
    public GroupBuyVO reviewGroupBuy(Integer groupBuyId, String requestStatus, String rejectReason) {
        GroupBuyVO groupBuyVO = dao.findByPrimaryKey(groupBuyId);
        
        RequestStatus rs = RequestStatus.valueOf(requestStatus);
        
        groupBuyVO.setRequestStatus(rs);
        groupBuyVO.setReplyDatetime(new Timestamp(System.currentTimeMillis()));

        if (rs == RequestStatus.pending) {
            groupBuyVO.setStatus(GroupBuyStatus.pending);
            groupBuyVO.setRejectReason(null);

        } else if (rs == RequestStatus.approved) {
            groupBuyVO.setStatus(GroupBuyStatus.open);
            groupBuyVO.setRejectReason(null);

        } else if (rs == RequestStatus.rejected) {
            groupBuyVO.setStatus(GroupBuyStatus.cancelled);
            groupBuyVO.setRejectReason(rejectReason);
        }
        dao.update(groupBuyVO);
        return groupBuyVO;
    }
    public GroupBuyVO addGroupBuy(
            Integer productId,
            Integer hostUserId,
            Integer targetAmount,
            Integer groupPrice,
            Timestamp openDatetime,
            Timestamp ddlDatetime,
            String pickupAddres) {

        GroupBuyVO groupBuy = new GroupBuyVO();

        groupBuy.setProductId(productId);
        groupBuy.setHostUserId(hostUserId);
        groupBuy.setTargetAmount(targetAmount);
        groupBuy.setGroupPrice(groupPrice);
        groupBuy.setOpenDatetime(openDatetime);
        groupBuy.setDdlDatetime(ddlDatetime);
        groupBuy.setPickupAddress(pickupAddres);
        groupBuy.setStatus(GroupBuyStatus.pending);
        groupBuy.setRequestStatus(RequestStatus.pending);
        groupBuy.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        groupBuy.setRequestDatetime(new Timestamp(System.currentTimeMillis()));
        groupBuy.setReplyDatetime(null);
        groupBuy.setRejectReason(null);
        dao.insert(groupBuy);
        return groupBuy;
    }
    public List<ProductOptionVO> getProductOptions() {
        return dao.getProductOptions();
    }

    public GroupBuyVO updateGroupBuy(Integer groupBuyId,
            Integer productId,
            Integer hostUserId,
            Integer targetAmount,
            Integer groupPrice,
            Timestamp openDatetime,
            Timestamp ddlDatetime,
            String status,
            Timestamp createdAt,
            String requestStatus,
            Timestamp requestDatetime,
            Timestamp replyDatetime,
            String rejectReason) {

        GroupBuyVO groupBuy = new GroupBuyVO();

        groupBuy.setGroupBuyId(groupBuyId);
        groupBuy.setProductId(productId);
        groupBuy.setHostUserId(hostUserId);
        groupBuy.setTargetAmount(targetAmount);
        groupBuy.setGroupPrice(groupPrice);
        groupBuy.setOpenDatetime(openDatetime);
        groupBuy.setDdlDatetime(ddlDatetime);
        groupBuy.setStatus(GroupBuyStatus.valueOf(status));
        groupBuy.setCreatedAt(createdAt);
        groupBuy.setRequestStatus(RequestStatus.valueOf(requestStatus));
        groupBuy.setRequestDatetime(requestDatetime);
        groupBuy.setReplyDatetime(replyDatetime);
        groupBuy.setRejectReason(rejectReason);

        dao.update(groupBuy);

        return groupBuy;
    }

    public void deleteGroupBuy(Integer groupBuyId) {
        dao.delete(groupBuyId);
    }

    public GroupBuyVO getOneGroupBuy(Integer groupBuyId) {
        return dao.findByPrimaryKey(groupBuyId);
    }

    public List<GroupBuyVO> getAll() {
        return dao.getAll();
    }
}