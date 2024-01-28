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
      <!-- <el-form-item label="活动id" prop="activityId">
        <el-input
          v-model="queryParams.activityId"
          placeholder="请输入活动id"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item> -->
      <el-form-item label="活动名" prop="activityName">
        <el-input
          v-model="queryParams.activityName"
          placeholder="请输入活动名"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="活动时间" prop="activityDate">
        <el-date-picker
          clearable
          v-model="queryParams.activityDate"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择活动时间"
        >
        </el-date-picker>
      </el-form-item>
      <el-form-item label="活动分类" prop="activityClassify">
        <!-- <el-input
          v-model="queryParams.activityClassify"
          placeholder="请输入系列分类"
          clearable
          @keyup.enter.native="handleQuery"
        /> -->
        <el-select
          v-model="queryParams.activityClassify"
          placeholder="请选择活动分类"
        >
          <el-option
            v-for="item in selectActivityClassify"
            :key="item.classifyNum"
            :label="item.classifyName"
            :value="item.classifyNum"
          >
          </el-option>
        </el-select>
      </el-form-item>

      <el-form-item label="是否免费 " prop="isFree">
        <el-select
          v-model="queryParams.isFree"
          placeholder="请选择是否免费 "
          clearable
        >
          <el-option
            v-for="dict in dict.type.is_free"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <!-- <el-form-item label="价格" prop="activityPrice">
        <el-input
          v-model="queryParams.activityPrice"
          placeholder="请输入价格"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item> -->
      <el-form-item label="活动地点" prop="activityPlace">
        <el-input
          v-model="queryParams.activityPlace"
          placeholder="请输入活动地点"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <!-- <el-form-item label="发布人id" prop="publishId">
        <el-input
          v-model="queryParams.publishId"
          placeholder="请输入发布人id"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item> -->
      <el-form-item label="审核状态" prop="status">
        <el-select v-model="queryParams.status" placeholder="请选择审核状态" clearable>
          <el-option
            v-for="dict in dict.type.video_status"
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
          v-hasPermi="['activity:activityInfo:add']"
          >新增</el-button
        >
      </el-col>
      <!-- <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['activity:activityInfo:edit']"
        >修改</el-button>
      </el-col> -->
      <!-- <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['activity:activityInfo:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['activity:activityInfo:export']"
        >导出</el-button>
      </el-col> -->
      <right-toolbar
        :showSearch.sync="showSearch"
        @queryTable="getList"
      ></right-toolbar>
    </el-row>

    <el-table
      v-loading="loading"
      :data="myActivityInfoList"
      @selection-change="handleSelectionChange"
    >
      <el-table-column type="selection" width="55" align="center" />
      <!-- <el-table-column label="活动id" align="center" prop="activityId" /> -->
      <el-table-column label="活动名" align="center" prop="activityName" />
      <el-table-column
        label="活动时间"
        align="center"
        prop="activityDate"
        width="180"
      >
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.activityDate, "{y}-{m}-{d}") }}</span>
        </template>
      </el-table-column>

      <el-table-column label="活动分类" align="center" prop="activityClassify">
        <template slot-scope="scope">
          <span>{{getActivityClassify(scope.row.activityClassify)}}</span>
        </template>
      </el-table-column>

      <el-table-column label="是否免费 " align="center" prop="isFree">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.is_free" :value="scope.row.isFree" />
        </template>
      </el-table-column>
      <el-table-column label="价格" align="center" prop="activityPrice" />
      <el-table-column label="活动地点" align="center" prop="activityPlace" />
      <el-table-column label="发布人id" align="center" prop="publishId" />
      <el-table-column label="审核状态" align="center" prop="status">
        <template slot-scope="scope">
          <dict-tag
            :options="dict.type.video_status"
            :value="scope.row.status"
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
            v-hasPermi="['activity:activityInfo:edit']"
            :disabled = "scope.row.status != 0 && scope.row.status != 3"
            >修改</el-button
          >

          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['activity:activityInfo:edit']"
            :disabled = "scope.row.status != 2"
            >下架</el-button
          >
          <!-- <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['activity:activityInfo:remove']"
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

    <!-- 添加或修改发布活动对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px" :disabled="form.status != 0 && form.status != 3">
        <el-form-item label="活动名" prop="activityName">
          <el-input v-model="form.activityName" placeholder="请输入活动名" />
        </el-form-item>
        <el-form-item label="活动时间" prop="activityDate">
          <el-date-picker
            clearable
            v-model="form.activityDate"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择活动时间"
          >
          </el-date-picker>
        </el-form-item>

        <!-- 活动封面 -->
        <el-form-item label="活动封面" prop="activityPhoto">
          <!-- <el-input v-model="form.videoPhoto" placeholder="请上传视频封面" /> -->
          <el-upload
            class="avatar-uploader"
            :action="uploadFileUrl"
            :show-file-list="false"
            :on-success="photoHandleAvatarSuccess"
            :before-upload="photoBeforeAvatarUpload"
            :disabled="form.status === 1 || form.status === 2"
          >
            <img
              v-if="form.activityPhoto"
              :src="form.activityPhoto"
              class="avatar"
            />
            <i v-else class="el-icon-plus avatar-uploader-icon"></i>
          </el-upload>
        </el-form-item>

        <el-form-item label="活动分类" prop="activityClassify">
          <el-select
            v-model="form.activityClassify"
            placeholder="请选择活动分类"
          >
            <el-option
              v-for="item in selectActivityClassify"
              :key="item.classifyNum"
              :label="item.classifyName"
              :value="item.classifyNum"
            >
            </el-option>
          </el-select>
        </el-form-item>

        <el-form-item label="是否免费 " prop="isFree">
          <el-select
            v-model="form.isFree"
            placeholder="请选择是否免费 "
            @change="(value) => ifFree(value)"
          >
            <el-option
              v-for="dict in dict.type.is_free"
              :key="dict.value"
              :label="dict.label"
              :value="parseInt(dict.value)"
            ></el-option>
          </el-select>
        </el-form-item>

        <el-form-item label="价格" prop="activityPrice">
          <el-input
            v-model="form.activityPrice"
            placeholder="请输入价格"
            style="width: 150px"
            :disabled="isIfFree"
          />
          元
        </el-form-item>

        <el-form-item label="活动地点" prop="activityPlace">
          <el-input v-model="form.activityPlace" placeholder="请输入活动地点" />
        </el-form-item>

        <!-- <el-form-item label="发布人id" prop="publishId">
          <el-input v-model="form.publishId" placeholder="请输入发布人id" />
        </el-form-item> -->
        <!-- <el-form-item label="审核状态   0：待审核   1：审核通过    2：审核不通过" prop="status">
          <el-select v-model="form.status" placeholder="请选择审核状态   0：待审核   1：审核通过    2：审核不通过">
            <el-option
              v-for="dict in dict.type.process_status"
              :key="dict.value"
              :label="dict.label"
              :value="parseInt(dict.value)"
            ></el-option>
          </el-select>
        </el-form-item> -->
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm" v-show="form.status == 0 || form.status == 3">确 定</el-button>
        <el-button type="danger" @click="updateFormStatus" v-show="form.status == 2">下 架</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import {
  listMyActivityInfo,
  getMyActivityInfo,
  delMyActivityInfo,
  addMyActivityInfo,
  updateMyActivityInfo,
} from "@/api/activity/myActivityInfo";

import { listActivityClassify } from "@/api/activity/activityClassify";

export default {
  name: "MyActivityInfo",
  dicts: ["process_status", "is_free","video_status"],
  data() {
    return {

      // 当前选择的是否是免费
      isIfFree: false,

      // 文件上传地址
      uploadFileUrl: process.env.VUE_APP_BASE_API + "/minio/file/upload", // 上传文件服务器地址

      // 用于选择的活动分类
      selectActivityClassify: null,

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
      // 发布活动表格数据
      myActivityInfoList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        activityId: null,
        activityName: null,
        activityPhoto: null,
        activityDate: null,
        activityClassify: null,
        isFree: null,
        activityPrice: null,
        activityPlace: null,
        publishId: null,
        status: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        activityName: [
          { required: true, message: "活动名不能为空", trigger: "blur" },
        ],
        activityDate: [
          { required: true, message: "活动时间不能为空", trigger: "blur" },
        ],
        activityClassify: [
          { required: true, message: "系列分类不能为空", trigger: "blur" },
        ],
        isFree: [
          { required: true, message: "是否免费 不能为空", trigger: "change" },
        ],
        activityPlace: [
          { required: true, message: "活动地点不能为空", trigger: "blur" },
        ],

        activityPrice: [
          { required: true, message: "请输入有效金额", trigger: "blur" },
          {
            pattern: /^(0|[1-9]\d*)(\.\d{1,2})?$/,
            message: "请输入两位小数",
            trigger: "blur",
          },
        ],

        publishId: [
          { required: true, message: "发布人id不能为空", trigger: "blur" },
        ],
        status: [
          {
            required: true,
            message: "审核状态不能为空",
            trigger: "change",
          },
        ],
      },
    };
  },
  created() {
    // 获得活动分类选项
    listActivityClassify().then((res) => {
      this.selectActivityClassify = res.rows;
    });

    this.getList();
  },
  methods: {
    /** 查询发布活动列表 */
    getList() {
      // 添加自己的用户信息id
      this.queryParams.publishId = this.$store.state.user.id;

      this.loading = true;
      listMyActivityInfo(this.queryParams).then((response) => {
        this.myActivityInfoList = response.rows;
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
        activityId: null,
        activityName: null,
        activityPhoto: null,
        activityDate: null,
        activityClassify: null,
        isFree: null,
        activityPrice: null,
        activityPlace: null,
        publishId: null,
        status: 0,
      };
      this.resetForm("form");
    },

    // 活动封面上传前的方法
    photoBeforeAvatarUpload(file) {
      this.$loading();
      const isJPG = file.type === "image/jpeg";
      if (!isJPG) {
        this.$message.error("上传文件只能是 JPG 格式!");
        this.$loading().close();
        return isJPG;
      }
      const isLt10M = file.size / 1024 / 1024 < 10;
      if (!isLt10M) {
        this.$message.error("上传文件大小不能超过 10MB!");
        this.$loading().close();
        return isLt10M;
      }
    },

    // 活动封面上传成功的方法
    photoHandleAvatarSuccess(res) {
      // res 就是上传的文件路径
      this.form.activityPhoto = res; // 将封面路径赋予 表单的 videoPhoto 就可以实现图片回显
      this.$loading().close();
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
      this.ids = selection.map((item) => item.activityId);
      this.single = selection.length !== 1;
      this.multiple = !selection.length;
    },
    /** 新增按钮操作 */
    handleAdd() {

      this.form.status = 0;// 打开时状态设置为待审核

      this.reset();
      this.open = true;
      this.title = "添加发布活动";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {

      this.form.status = 0;// 打开弹窗时状态改为待审核

      this.ifFree(row.isFree);

      this.reset();
      const activityId = row.activityId || this.ids;
      getMyActivityInfo(activityId).then((response) => {
        this.form = response.data;
        this.open = true;
        this.title = "修改发布活动";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate((valid) => {
        // 将上传者id填入
        this.form.publishId = this.$store.state.user.id;

        // 将表单状态都改为待审核
        this.form.status = 0;

        if (valid) {
          if (this.form.activityId != null) {
            updateMyActivityInfo(this.form).then((response) => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addMyActivityInfo(this.form).then((response) => {
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
      const activityIds = row.activityId || this.ids;
      this.$modal
        .confirm('是否确认删除发布活动编号为"' + activityIds + '"的数据项？')
        .then(function () {
          return delMyActivityInfo(activityIds);
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
        "activity/myActivityInfo/export",
        {
          ...this.queryParams,
        },
        `myActivityInfo_${new Date().getTime()}.xlsx`
      );
    },

    // 用于判断勾选的是否是免费，免费的话价格就自动变为0，且价格输入框无法填写
    ifFree(value) {
      if (value === 1) {
        this.form.activityPrice = 0;
        this.isIfFree = true;
      } else {
        this.isIfFree = false;
      }
    },

    // 从 activityClassify 中获取对应的值
    getActivityClassify(keyValue) {
      let resultName = "未分类";

      this.selectActivityClassify.forEach((element) => {
        if (element.classifyNum == keyValue) {
          resultName = element.classifyName;
        }
      });

      return resultName;
    },


    /** 下架按钮 */
    updateFormStatus() {
      this.$refs["form"].validate((valid) => {
        // 将上传者id填入
        this.form.publishId = this.$store.state.user.id;

        // 将表单状态都改为待审核
        this.form.status = 3;

        if (valid) {
          if (this.form.activityId != null) {
            updateMyActivityInfo(this.form).then((response) => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addMyActivityInfo(this.form).then((response) => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
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

