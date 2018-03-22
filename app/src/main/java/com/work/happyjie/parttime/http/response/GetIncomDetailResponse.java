package com.work.happyjie.parttime.http.response;

import com.work.happyjie.parttime.http.response.base.BaseResponse;

import java.util.List;

/**
 * Created by asus on 2018-03-20 .
 */

public class GetIncomDetailResponse extends BaseResponse {

    private int currPage;
    private int pageSize; //": 5,// 每页显示的记录数
    private int totalCount;//": 278,// 总记录数
    private int totalPage;//": 56,// 总页数
    private List<IncomeItem> lists;
    private List<TotalRow> totalrow;


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

    public List<IncomeItem> getLists() {
        return lists;
    }

    public void setLists(List<IncomeItem> lists) {
        this.lists = lists;
    }

    public List<TotalRow> getTotalrow() {
        return totalrow;
    }

    public void setTotalrow(List<TotalRow> totalrow) {
        this.totalrow = totalrow;
    }

    public static class TotalRow{
        private String balance;//": "999900.00",//余额
        private String todayincome;//": "0.00",//今日收入
        private String incomenum;//": "10141.50",//累计收益
        private String sumdate;//": "2018年3月份"//合计时间
    }

    public static class IncomeItem{
        private String incomedate;
        private String incomenum;

        public String getIncomedate() {
            return incomedate;
        }

        public void setIncomedate(String incomedate) {
            this.incomedate = incomedate;
        }

        public String getIncomenum() {
            return incomenum;
        }

        public void setIncomenum(String incomenum) {
            this.incomenum = incomenum;
        }
    }
}
