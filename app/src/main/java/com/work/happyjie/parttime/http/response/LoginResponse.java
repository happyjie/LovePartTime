package com.work.happyjie.parttime.http.response;

import com.work.happyjie.parttime.bean.UserInfo;
import com.work.happyjie.parttime.http.response.base.BaseResponse;

/**
 * Created by llj on 2018/3/20.
 */

public class LoginResponse extends BaseResponse{

    private UserInfo userList;
    private AdminInfo adminList;

    public UserInfo getUserList() {
        return userList;
    }

    public void setUserList(UserInfo userList) {
        this.userList = userList;
    }

    public AdminInfo getAdminList() {
        return adminList;
    }

    public void setAdminList(AdminInfo adminList) {
        this.adminList = adminList;
    }


    public static class AdminInfo{

        /**
         * telephone : 123456
         * chartnum : 123456
         * qqnum : 123456
         * address : 北京市昌平区
         */

        private String telephone;
        private String chartnum;
        private String qqnum;
        private String address;

        public String getTelephone() {
            return telephone;
        }

        public void setTelephone(String telephone) {
            this.telephone = telephone;
        }

        public String getChartnum() {
            return chartnum;
        }

        public void setChartnum(String chartnum) {
            this.chartnum = chartnum;
        }

        public String getQqnum() {
            return qqnum;
        }

        public void setQqnum(String qqnum) {
            this.qqnum = qqnum;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

    }



}
