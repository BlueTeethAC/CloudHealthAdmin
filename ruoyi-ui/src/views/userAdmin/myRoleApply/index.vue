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
      <el-form-item label="信息id" prop="id">
        <el-input
          v-model="queryParams.id"
          placeholder="请输入信息id"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <!-- <el-form-item label="申请审核人id" prop="userId">
        <el-input
          v-model="queryParams.userId"
          placeholder="请输入申请审核人id"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item> -->
      <el-form-item label="申请角色" prop="role">
        <!-- <el-input
          v-model="queryParams.role"
          placeholder="请输入申请角色"
          clearable
          @keyup.enter.native="handleQuery"
        /> -->
        <el-select v-model="queryParams.role" placeholder="请选择申报角色">
          <el-option
            v-for="item in selectRoleInfo"
            :key="item.roleId"
            :label="item.roleName"
            :value="item.roleId"
          >
          </el-option>
        </el-select>
      </el-form-item>
      <!-- <el-form-item label="申请的资料地址" prop="infoUrl">
        <el-input
          v-model="queryParams.infoUrl"
          placeholder="请输入申请的资料地址"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item> -->
      <el-form-item label="审核状态" prop="status">
        <el-select
          v-model="queryParams.status"
          placeholder="请选择审核状态"
          clearable
        >
          <el-option
            v-for="dict in dict.type.process_status"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="审核员id" prop="adminId">
        <el-input
          v-model="queryParams.adminId"
          placeholder="请输入审核员id"
          clearable
          @keyup.enter.native="handleQuery"
        />
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
          v-hasPermi="['userAdmin:roleProcess:add']"
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
          v-hasPermi="['userAdmin:roleProcess:edit']"
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
          v-hasPermi="['userAdmin:roleProcess:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['userAdmin:roleProcess:export']"
        >导出</el-button>
      </el-col>-->
      <right-toolbar
        :showSearch.sync="showSearch"
        @queryTable="getList"
      ></right-toolbar>
    </el-row>

    <el-table
      v-loading="loading"
      :data="myRoleApplyList"
      @selection-change="handleSelectionChange"
    >
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="信息id" align="center" prop="id" />
      <el-table-column label="申请审核人id" align="center" prop="userId" />

      <el-table-column label="申请角色" align="center" prop="role">
        <template slot-scope="scope">
          <!-- <dict-tag :options="dict.type.ch_user_role" :value="scope.row.role" /> -->
          <span>{{getRowRole(scope.row.role)}}</span>
        </template>
      </el-table-column>

      <!-- <el-table-column label="申请的资料地址" align="center" prop="infoUrl" /> -->

      <el-table-column label="审核状态" align="center" prop="status">
        <template slot-scope="scope">
          <dict-tag
            :options="dict.type.process_status"
            :value="scope.row.status"
          />
        </template>
      </el-table-column>
      <el-table-column label="审核员id" align="center" prop="adminId" />
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
            v-hasPermi="['userAdmin:roleProcess:edit']"
            :disabled = "scope.row.status != 0"
            >修改</el-button
          >
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['userAdmin:roleProcess:remove']"
            :disabled = "scope.row.status != 0"
            >删除</el-button
          >
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

    <!-- 添加或修改角色申报对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <!-- <el-form-item label="申请人id" prop="userId">
          <el-input v-model="form.userId" placeholder="请输入申请审核人id" />
        </el-form-item> -->
        <el-form-item label="申请角色" prop="role">
          <!-- <el-input v-model="form.role" placeholder="请输入申请角色" /> -->
          <el-select v-model="form.role" placeholder="请输入申请角色">
            <el-option
              v-for="item in selectRoleInfo"
              :key="item.roleId"
              :label="item.roleName"
              :value="item.roleId"
            >
            </el-option>
          </el-select>
        </el-form-item>

        <el-form-item label="申请资料" prop="infoUrl">
          <!-- <el-input v-model="form.infoUrl" placeholder="请输入申请的资料地址" /> -->
          <!-- <el-input v-model="form.videoPath" placeholder="请输入访问路径" /> -->
          <el-upload
            class="upload-demo"
            :action="uploadFileUrl"
            :on-preview="videoHandlePreview"
            :on-remove="videoHandleRemove"
            :before-remove="videoBeforeRemove"
            :on-success="uploadViewSucess"
            :before-upload="videoBeforeUpload"
            multiple
            :limit="1"
            :on-exceed="videoHandleExceed"
            :file-list="fileList"
          >
            <el-button size="small" type="primary">点击上传</el-button>

            <div slot="tip" class="el-upload__tip">
              只能上传pdf文件，且不超过1GB
            </div>
          </el-upload>
        </el-form-item>

        <!-- <el-form-item label="审核状态   0：待审核   1：审核通过   2：审核不通过" prop="status">
          <el-radio-group v-model="form.status">
            <el-radio
              v-for="dict in dict.type.process_status"
              :key="dict.value"
              :label="parseInt(dict.value)"
            >{{dict.label}}</el-radio>
          </el-radio-group>
        </el-form-item> -->
        <!-- <el-form-item label="审核员id" prop="adminId">
          <el-input v-model="form.adminId" placeholder="请输入审核员id" />
        </el-form-item> -->
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
  listMyRoleApply,
  getMyRoleApply,
  delMyRoleApply,
  addMyRoleApply,
  updateMyRoleApply,
} from "@/api/userAdmin/myRoleApply";

import { listRoleinfo } from "@/api/userAdmin/roleinfo";

export default {
  name: "MyRoleApply",
  dicts: ["process_status"],
  data() {
    return {

      fileList: [],

      // 文件上传地址
      uploadFileUrl: process.env.VUE_APP_BASE_API + "/minio/file/upload", // 上传文件服务器地址

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
      // 角色申报表格数据
      myRoleApplyList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        id: null,
        userId: null,
        role: null,
        infoUrl: null,
        status: null,
        adminId: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        userId: [
          { required: true, message: "申请审核人id不能为空", trigger: "blur" },
        ],
        role: [
          { required: true, message: "申请角色不能为空", trigger: "blur" },
        ],
        infoUrl: [
          {
            required: true,
            message: "申请的资料地址不能为空",
            trigger: "blur",
          },
        ],
        status: [
          {
            required: true,
            message:
              "审核状态   0：待审核   1：审核通过   2：审核不通过不能为空",
            trigger: "change",
          },
        ],
        adminId: [
          { required: true, message: "审核员id不能为空", trigger: "blur" },
        ],
      },
    };
  },
  created() {
    // 执行查询角色信息方法
    listRoleinfo().then((res) => {
      this.selectRoleInfo = res.rows; // 获得可选择的角色

      // 筛选角色
      this.selectRoleInfo = this.selectRoleInfo.filter((item) => {
        return item.roleId == 100 || item.roleId == 101;
      });

    });

    this.getList();
  },
  methods: {
    /** 查询角色申报列表 */
    getList() {
      this.loading = true;
      this.queryParams.userId = this.$store.state.user.id;// 设置当前用户
      listMyRoleApply(this.queryParams).then((response) => {
        this.myRoleApplyList = response.rows;
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
        id: null,
        userId: null,
        role: null,
        infoUrl: null,
        status: null,
        adminId: null,
      };
      this.resetForm("form");

      this.fileList = [];
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
      this.ids = selection.map((item) => item.id);
      this.single = selection.length !== 1;
      this.multiple = !selection.length;
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.fileList = [];
      // console.log(this.fileList)
      this.open = true;
      this.title = "添加角色申报";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids;
      getMyRoleApply(id).then((response) => {
        this.form = response.data;

        this.fileList = [{name:"申报材料",url:this.form.infoUrl}];

        this.open = true;
        this.title = "修改角色申报";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate((valid) => {

        // 将上传者id填入
        this.form.userId = this.$store.state.user.id;

        if (valid) {
          if (this.form.id != null) {
            updateMyRoleApply(this.form).then((response) => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addMyRoleApply(this.form).then((response) => {
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
      const ids = row.id || this.ids;
      this.$modal
        .confirm('是否确认删除角色申报编号为"' + ids + '"的数据项？')
        .then(function () {
          return delMyRoleApply(ids);
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
        "userAdmin/myRoleApply/export",
        {
          ...this.queryParams,
        },
        `myRoleApply_${new Date().getTime()}.xlsx`
      );
    },

    // 点击删除文件时的钩子
    videoHandleRemove(file) {
      this.fileList = [];
      console.log(file);
    },
    // 点击文件时的钩子
    videoHandlePreview(file) {
      // console.log(file);
      window.open(this.form.infoUrl);
    },
    // 移除文件时的钩子
    videoBeforeRemove(file) {
      // return this.$confirm(`确定移除 ${file.name}？`);
      return true;
    },

    // 上传视频文件成功时的钩子
    uploadViewSucess(res) {
      // res 就是上传的文件路径
      this.form.infoUrl = res; // 将封面路径赋予 表单的 videoPhoto 就可以实现图片回显
      this.$loading().close();
    },

    // 视频文件上传前的方法
    videoBeforeUpload(file) {
      this.$loading();
      const isPDF = file.type === "application/pdf";
      if (!isPDF) {
        this.$message.error("上传文件只能是 pdf 格式!");
        this.$loading().close();
        return isPDF;
      }

      const isLt10G = file.size / 1024 / 1024 < 1024;
      if (!isLt10G) {
        this.$message.error("上传文件大小不能超过 1GB!");
        this.$loading().close();
        return isLt10G;
      }
    },

    // 超出文件数量上限的钩子
    videoHandleExceed(files, fileList) {
      this.$message.warning(
        `当前限制选择 1 个文件，本次选择了 ${files.length} 个文件，共选择了 ${
          files.length + fileList.length
        } 个文件`
      );
    },

    // 用于判断申请的角色并显示的方法
    getRowRole(roleId){
      console.log(roleId)
      let roleName = "未分配";
      this.selectRoleInfo.forEach(item=>{
        if(item.roleId == roleId){
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