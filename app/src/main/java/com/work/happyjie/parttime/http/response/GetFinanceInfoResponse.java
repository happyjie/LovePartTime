package com.work.happyjie.parttime.http.response;

import com.work.happyjie.parttime.http.response.base.BaseResponse;

import java.util.List;

/**
 * Created by asus on 2018-03-20 .
 */

public class GetFinanceInfoResponse extends BaseResponse {

    /**
     * currPage : 1
     * pageSize : 5
     * totalCount : 2
     * totalPage : 1
     * totalrow : [{"balance":"999900.00","totalincome":"19000000.00","sumdate":"2018年3月份"}]
     * lists : [{"drawcashdate":"2018-03-08 05:03:32","drawcashnum":"200.00","status":"已通过"}]
     * startIndex : 1
     * endIndex : 1
     */

    private int currPage;
    private int pageSize;
    private int totalCount;
    private int totalPage;
    private int startIndex;
    private int endIndex;
    private List<TotalrowBean> totalrow;
    private List<ListsBean> lists;

    public int getCurrPage() {
        return currPage;
    }

    public void setCurrPage(int currPage) {
        this.currPage = currPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(int startIndex) {
        this.startIndex = startIndex;
    }

    public int getEndIndex() {
        return endIndex;
    }

    public void setEndIndex(int endIndex) {
        this.endIndex = endIndex;
    }

    public List<TotalrowBean> getTotalrow() {
        return totalrow;
    }

    public void setTotalrow(List<TotalrowBean> totalrow) {
        this.totalrow = totalrow;
    }

    public List<ListsBean> getLists() {
        return lists;
    }

    public void setLists(List<ListsBean> lists) {
        this.lists = lists;
    }

    public static class TotalrowBean {
        /**
         * balance : 999900.00
         * totalincome : 19000000.00
         * sumdate : 2018年3月份
         */

        private String balance;
        private String totalincome;
        private String sumdate;

        public String getBalance() {
            return balance;
        }

        public void setBalance(String balance) {
            this.balance = balance;
        }

        public String getTotalincome() {
            return totalincome;
        }

        public void setTotalincome(String totalincome) {
            this.totalincome = totalincome;
        }

        public String getSumdate() {
            return sumdate;
        }

        public void setSumdate(String sumdate) {
            this.sumdate = sumdate;
        }
    }

    public static class ListsBean {
        /**
         * drawcashdate : 2018-03-08 05:03:32
         * drawcashnum : 200.00
         * status : 已通过
         */

        private String drawcashdate;
        private String drawcashnum;
        private String status;

        public String getDrawcashdate() {
            return drawcashdate;
        }

        public void setDrawcashdate(String drawcashdate) {
            this.drawcashdate = drawcashdate;
        }

        public String getDrawcashnum() {
            return drawcashnum;
        }

        public void setDrawcashnum(String drawcashnum) {
            this.drawcashnum = drawcashnum;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }
}

