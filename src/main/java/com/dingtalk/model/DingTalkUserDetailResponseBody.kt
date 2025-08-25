package com.dingtalk.model

import com.google.gson.annotations.SerializedName

class DingTalkUserDetailResponseBody : DingTalkResponse() {
    @SerializedName("result")
    var result: DingTalkUserDetailResult? = null

    class DingTalkUserDetailResult {
        @SerializedName("extension")
        var extension: String? = null

        @SerializedName("unionid")
        var unionid: String? = null

        @SerializedName("boss")
        var boss: String? = null

        @SerializedName("role_list")
        var roleList: MutableList<RoleListDTO>? = null

        @SerializedName("exclusive_account")
        var exclusiveAccount: Boolean? = null

        @SerializedName("manager_userid")
        var managerUserid: String? = null

        @SerializedName("admin")
        var admin: String? = null

        @SerializedName("remark")
        var remark: String? = null

        @SerializedName("title")
        var title: String? = null

        @SerializedName("hired_date")
        var hiredDate: String? = null

        @SerializedName("userid")
        var userid: String? = null

        @SerializedName("work_place")
        var workPlace: String? = null

        @SerializedName("dept_order_list")
        var deptOrderList: MutableList<DeptOrderListDTO>? = null

        @SerializedName("real_authed")
        var realAuthed: String? = null

        @SerializedName("dept_id_list")
        var deptIdList: MutableList<Int>? = null

        @SerializedName("job_number")
        var jobNumber: String? = null

        @SerializedName("email")
        var email: String? = null

        @SerializedName("leader_in_dept")
        var leaderInDept: MutableList<LeaderInDeptDTO>? = null

        @SerializedName("mobile")
        var mobile: String? = null

        @SerializedName("active")
        var active: String? = null

        @SerializedName("org_email")
        var orgEmail: String? = null

        @SerializedName("telephone")
        var telephone: String? = null

        @SerializedName("avatar")
        var avatar: String? = null

        @SerializedName("hide_mobile")
        var hideMobile: String? = null

        @SerializedName("senior")
        var senior: String? = null

        @SerializedName("name")
        var name: String? = null

        @SerializedName("union_emp_ext")
        var unionEmpExt: UnionEmpExtDTO? = null

        @SerializedName("state_code")
        var stateCode: String? = null

        class RoleListDTO {
            @SerializedName("group_name")
            var groupName: String? = null

            @SerializedName("name")
            var name: String? = null

            @SerializedName("id")
            var id: String? = null
        }


        class DeptOrderListDTO {
            @SerializedName("dept_id")
            var deptId: String? = null

            @SerializedName("order")
            var order: String? = null
        }


        class LeaderInDeptDTO {
            @SerializedName("leader")
            var leader: String? = null

            @SerializedName("dept_id")
            var deptId: String? = null
        }


        class UnionEmpExtDTO {
            @SerializedName("union_emp_map_list")
            var unionEmpMapList: MutableList<UnionEmpMapListDTO>? = null

            @SerializedName("userid")
            var userid: String? = null

            @SerializedName("corp_id")
            var corpId: String? = null

            class UnionEmpMapListDTO {
                @SerializedName("userid")
                var userid: String? = null

                @SerializedName("corp_id")
                var corpId: String? = null
            }
        }
    }
}
