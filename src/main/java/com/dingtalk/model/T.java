package com.dingtalk.model;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class T {

    @SerializedName("extension")
    private String extension;
    @SerializedName("unionid")
    private String unionid;
    @SerializedName("boss")
    private String boss;
    @SerializedName("role_list")
    private RoleListDTO roleList;
    @SerializedName("exclusive_account")
    private Boolean exclusiveAccount;
    @SerializedName("manager_userid")
    private String managerUserid;
    @SerializedName("admin")
    private String admin;
    @SerializedName("remark")
    private String remark;
    @SerializedName("title")
    private String title;
    @SerializedName("hired_date")
    private String hiredDate;
    @SerializedName("userid")
    private String userid;
    @SerializedName("work_place")
    private String workPlace;
    @SerializedName("dept_order_list")
    private DeptOrderListDTO deptOrderList;
    @SerializedName("real_authed")
    private String realAuthed;
    @SerializedName("dept_id_list")
    private String deptIdList;
    @SerializedName("job_number")
    private String jobNumber;
    @SerializedName("email")
    private String email;
    @SerializedName("leader_in_dept")
    private LeaderInDeptDTO leaderInDept;
    @SerializedName("mobile")
    private String mobile;
    @SerializedName("active")
    private String active;
    @SerializedName("org_email")
    private String orgEmail;
    @SerializedName("telephone")
    private String telephone;
    @SerializedName("avatar")
    private String avatar;
    @SerializedName("hide_mobile")
    private String hideMobile;
    @SerializedName("senior")
    private String senior;
    @SerializedName("name")
    private String name;
    @SerializedName("union_emp_ext")
    private UnionEmpExtDTO unionEmpExt;
    @SerializedName("state_code")
    private String stateCode;

    @NoArgsConstructor
    @Data
    public static class RoleListDTO {
        @SerializedName("group_name")
        private String groupName;
        @SerializedName("name")
        private String name;
        @SerializedName("id")
        private String id;
    }

    @NoArgsConstructor
    @Data
    public static class DeptOrderListDTO {
        @SerializedName("dept_id")
        private String deptId;
        @SerializedName("order")
        private String order;
    }

    @NoArgsConstructor
    @Data
    public static class LeaderInDeptDTO {
        @SerializedName("leader")
        private String leader;
        @SerializedName("dept_id")
        private String deptId;
    }

    @NoArgsConstructor
    @Data
    public static class UnionEmpExtDTO {
        @SerializedName("union_emp_map_list")
        private UnionEmpMapListDTO unionEmpMapList;
        @SerializedName("userid")
        private String userid;
        @SerializedName("corp_id")
        private String corpId;

        @NoArgsConstructor
        @Data
        public static class UnionEmpMapListDTO {
            @SerializedName("userid")
            private String userid;
            @SerializedName("corp_id")
            private String corpId;
        }
    }
}
