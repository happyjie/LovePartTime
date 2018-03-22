package com.work.happyjie.parttime.http.response;

import com.work.happyjie.parttime.http.response.base.BaseResponse;

import java.util.List;

/**
 * Created by llj on 2018/3/21.
 */

public class GetTaskListResponse extends BaseResponse {

    private int currPage;
    private int pageSize;
    private int totalCount;
    private int totalPage;
    private int startIndex;
    private int endIndex;
    private List<TaskItem> lists;


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

    public List<TaskItem> getLists() {
        return lists;
    }

    public void setLists(List<TaskItem> lists) {
        this.lists = lists;
    }

    public static class TaskItem {
        private String startdate; //2018-03-18 12:03:36//任务开始时间
        private String taskid; //18//任务ID
        private String enddate; //2018-03-22 12:03:40//任务结束时间
        private String money; //88//奖金
        private String count; //88//任务转发个数
        private String finishcount; //0//完成转发个数
        private String tasktype; //线下//任务类型（转发/线下）
        private String taskname; //我叫测试//任务名称
        private String taskdesc; //我叫测试//任务描述
        private String tasklink; //我叫测试//任务链接地址
        private String taskstate; //生效private String //任务状态（生效/失效）

        public String getStartdate() {
            return startdate;
        }

        public void setStartdate(String startdate) {
            this.startdate = startdate;
        }

        public String getTaskid() {
            return taskid;
        }

        public void setTaskid(String taskid) {
            this.taskid = taskid;
        }

        public String getEnddate() {
            return enddate;
        }

        public void setEnddate(String enddate) {
            this.enddate = enddate;
        }

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }

        public String getCount() {
            return count;
        }

        public void setCount(String count) {
            this.count = count;
        }

        public String getFinishcount() {
            return finishcount;
        }

        public void setFinishcount(String finishcount) {
            this.finishcount = finishcount;
        }

        public String getTasktype() {
            return tasktype;
        }

        public void setTasktype(String tasktype) {
            this.tasktype = tasktype;
        }

        public String getTaskname() {
            return taskname;
        }

        public void setTaskname(String taskname) {
            this.taskname = taskname;
        }

        public String getTaskdesc() {
            return taskdesc;
        }

        public void setTaskdesc(String taskdesc) {
            this.taskdesc = taskdesc;
        }

        public String getTasklink() {
            return tasklink;
        }

        public void setTasklink(String tasklink) {
            this.tasklink = tasklink;
        }

        public String getTaskstate() {
            return taskstate;
        }

        public void setTaskstate(String taskstate) {
            this.taskstate = taskstate;
        }
    }
}
