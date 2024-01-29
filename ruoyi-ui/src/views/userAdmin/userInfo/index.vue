<template>
  <div class="app-container">
    <el-form
      :model="queryParams"
      ref="queryForm"
      size="small"
      :inline="true"
      v-show="showSearch"
      label-width="68px"
    >
      <el-form-item label="用户id" prop="userId">
        <el-input
          v-model="queryParams.userId"
          placeholder="请输入用户id"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <!-- <el-form-item label="预留字段 微信用户唯一识别码" prop="openId"> -->
      <el-form-item label="识别码" prop="openId">
        <el-input
          v-model="queryParams.openId"
          placeholder="请输入微信识别码"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="用户名" prop="username">
        <el-input
          v-model="queryParams.username"
          placeholder="请输入用户名"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <!-- <el-form-item label="用户密码" prop="password">
        <el-input
          v-model="queryParams.password"
          placeholder="请输入用户密码"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item> -->
      <el-form-item label="手机号码" prop="phoneNumber">
        <el-input
          v-model="queryParams.phoneNumber"
          placeholder="请输入用户手机号码"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="常用地址" prop="userPlace">
        <el-input
          v-model="queryParams.userPlace"
          placeholder="请输入常用地址"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="用户角色" prop="userRole">
        <el-select
          v-model="queryParams.userRole"
          placeholder="请选择用户角色"
          clearable
        >
          <el-option
            v-for="item in selectRoleInfo"
            :key="item.roleId"
            :label="item.roleName"
            :value="item.roleId"
          >
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="账号状态" prop="userStatus">
        <el-select
          v-model="queryParams.userStatus"
          placeholder="请选择账号状态"
          clearable
        >
          <el-option
            v-for="dict in dict.type.ch_user_status"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button
          type="primary"
          icon="el-icon-search"
          size="mini"
          @click="handleQuery"
          >搜索</el-button
        >
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery"
          >重置</el-button
        >
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['userAdmin:userInfo:add']"
          >新增</el-button
        >
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['userAdmin:userInfo:edit']"
          >修改</el-button
        >
      </el-col>
      <!-- <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['userAdmin:userInfo:remove']"
        >删除</el-button>
      </el-col> -->
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['userAdmin:userInfo:export']"
          >导出</el-button
        >
      </el-col>
      <right-toolbar
        :showSearch.sync="showSearch"
        @queryTable="getList"
      ></right-toolbar>
    </el-row>

    <el-table
      v-loading="loading"
      :data="userInfoList"
      @selection-change="handleSelectionChange"
    >
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="用户id" align="center" prop="userId" />
      <el-table-column label="微信唯一识别码" align="center" prop="openId" />
      <el-table-column label="用户名" align="center" prop="username" />
      <!-- <el-table-column label="用户密码" align="center" prop="password" /> -->
      <el-table-column label="手机号码" align="center" prop="phoneNumber" />
      <el-table-column label="常用地址" align="center" prop="userPlace" />
      <el-table-column label="用户角色" align="center" prop="userRole">
        <template slot-scope="scope">
          <!-- <dict-tag :options="dict.type.ch_user_role" :value="scope.row.userRole"/> -->
          <span>{{ getRowRole(scope.row.userRole) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="账号状态" align="center" prop="userStatus">
        <template slot-scope="scope">
          <dict-tag
            :options="dict.type.ch_user_status"
            :value="scope.row.userStatus"
          />
        </template>
      </el-table-column>
      <el-table-column
        label="操作"
        align="center"
        class-name="small-padding fixed-width"
      >
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['userAdmin:userInfo:edit']"
            >修改</el-button
          >
          <!-- <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['userAdmin:userInfo:remove']"
            >删除</el-button
          > -->
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total > 0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改用户信息对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <!-- <el-form-item label="微信唯一识别码" prop="openId"> -->
        <el-form-item label="识别码" prop="openId">
          <el-input v-model="form.openId" placeholder="请输入微信唯一识别码" />
        </el-form-item>
        <el-form-item label="用户名" prop="username">
          <el-input v-model="form.username" placeholder="请输入用户名" />
        </el-form-item>
        <el-form-item label="用户密码" prop="password">
          <el-input
            v-model="form.password"
            placeholder="请输入用户密码"
            show-password
          />
        </el-form-item>
        <el-form-item label="手机号码" prop="phoneNumber">
          <el-input
            v-model="form.phoneNumber"
            placeholder="请输入用户手机号码"
          />
        </el-form-item>
        <el-form-item label="常用地址" prop="userPlace">
          <el-input v-model="form.userPlace" placeholder="请输入常用地址" />
        </el-form-item>
        <el-form-item label="用户角色" prop="userRole">
          <el-select v-model="form.userRole" placeholder="请选择用户角色">
            <el-option
              v-for="item in selectRoleInfo"
              :key="item.roleId"
              :label="item.roleName"
              :value="item.roleId"
            >
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="账号状态" prop="userStatus">
          <el-radio-group v-model="form.userStatus">
            <el-radio
              v-for="dict in dict.type.ch_user_status"
              :key="dict.value"
              :label="parseInt(dict.value)"
              >{{ dict.label }}</el-radio
            >
          </el-radio-group>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import {
  listUserInfo,
  getUserInfo,
  delUserInfo,
  addUserInfo,
  updateUserInfo,
} from "@/api/userAdmin/userInfo";

import { listRoleinfo } from "@/api/userAdmin/roleinfo";

export default {
  name: "UserInfo",
  dicts: ["ch_user_role", "ch_user_status"],
  data() {
    return {
      // 用于选择的角色信息
      selectRoleInfo: null,

      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 用户信息表格数据
      userInfoList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        userId: null,
        openId: null,
        username: null,
        password: null,
        phoneNumber: null,
        userPlace: null,
        userRole: null,
        userStatus: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        username: [
          { required: true, message: "用户名不能为空", trigger: "blur" },
        ],
        password: [
          { required: true, message: "用户密码不能为空", trigger: "blur" },
        ],
        phoneNumber: [
          { required: true, message: "用户手机号码不能为空", trigger: "blur" },
        ],
        userRole: [
          { required: true, message: "用户角色不能为空", trigger: "change" },
        ],
        userStatus: [
          { required: true, message: "账号状态不能为空", trigger: "change" },
        ],
      },
    };
  },
  created() {
    // 执行查询角色信息方法
    listRoleinfo().then((res) => {
      this.selectRoleInfo = res.rows; // 获得可选择的角色

      // 筛选角色
      // this.selectRoleInfo = this.selectRoleInfo.filter((item) => {
      //   return item.roleId == 100 || item.roleId == 101;
      // });
    });

    this.getList();
  },
  methods: {
    /** 查询用户信息列表 */
    getList() {
      this.loading = true;
      listUserInfo(this.queryParams).then((response) => {
        this.userInfoList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        userId: null,
        openId: null,
        username: null,
        password: null,
        phoneNumber: null,
        userIntroduce: null,
        accountPicture: null,
        userPlace: null,
        userRole: null,
        userStatus: null,
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map((item) => item.userId);
      this.single = selection.length !== 1;
      this.multiple = !selection.length;
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加用户信息";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const userId = row.userId || this.ids;
      getUserInfo(userId).then((response) => {
        this.form = response.data;
        this.open = true;
        this.title = "修改用户信息";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate((valid) => {
        if (valid) {
          if (this.form.userId != null) {
            updateUserInfo(this.form).then((response) => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addUserInfo(this.form).then((response) => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const userIds = row.userId || this.ids;
      this.$modal
        .confirm('是否确认删除用户信息编号为"' + userIds + '"的数据项？')
        .then(function () {
          return delUserInfo(userIds);
        })
        .then(() => {
          this.getList();
          this.$modal.msgSuccess("删除成功");
        })
        .catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download(
        "userAdmin/userInfo/export",
        {
          ...this.queryParams,
        },
        `userInfo_${new Date().getTime()}.xlsx`
      );
    },

    // 用于判断角色并显示的方法
    getRowRole(roleId) {
      console.log(roleId);
      let roleName = "未分配";
      this.selectRoleInfo.forEach((item) => {
        if (item.roleId == roleId) {
          roleName = item.roleName;
        }
      });

      return roleName;
    },
  },
};
</script>


<style>
.avatar-uploader .el-upload {
  border: 1px dashed #d9d9d9;
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
}
.avatar-uploader .el-upload:hover {
  border-color: #409eff;
}
.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 178px;
  height: 178px;
  line-height: 178px;
  text-align: center;
}
.avatar {
  width: 178px;
  height: 178px;
  display: block;
  object-fit: cover;
}
</style>