<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="反馈id" prop="feedbackinfoId">
        <el-input
          v-model="queryParams.feedbackinfoId"
          placeholder="请输入反馈id"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="咨询表id" prop="consultationFormId">
        <el-input
          v-model="queryParams.consultationFormId"
          placeholder="请输入咨询表id"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="回复者id" prop="replyPersonId">
        <el-input
          v-model="queryParams.replyPersonId"
          placeholder="请输入回复者id"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <!-- <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['healthConsult:feedBackInfo:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['healthConsult:feedBackInfo:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['healthConsult:feedBackInfo:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['healthConsult:feedBackInfo:export']"
        >导出</el-button>
      </el-col> -->
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="feedBackInfoList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="反馈id" align="center" prop="feedbackinfoId" />
      <el-table-column label="咨询表id" align="center" prop="consultationFormId" />
      <el-table-column label="回复者id" align="center" prop="replyPersonId" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['healthConsult:feedBackInfo:edit']"
          >修改</el-button>
          <!-- <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['healthConsult:feedBackInfo:remove']"
          >删除</el-button> -->
        </template>
      </el-table-column>
    </el-table>
    
    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改资讯信息反馈对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">

        <el-form-item label="">
          <el-button type="primary">查看历史信息</el-button>
        </el-form-item>

        <el-form-item label="咨询表id" prop="consultationFormId">
          <el-input v-model="form.consultationFormId" placeholder="请输入咨询表id" />
        </el-form-item>
        <el-form-item label="回复者id" prop="replyPersonId">
          <el-input v-model="form.replyPersonId" placeholder="请输入回复者id" />
        </el-form-item>
        <el-form-item label="回复内容" prop="replyInfo">
          <el-input v-model="form.replyInfo" type="textarea" placeholder="请输入内容" />
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
import { listFeedBackInfo, getFeedBackInfo, delFeedBackInfo, addFeedBackInfo, updateFeedBackInfo } from "@/api/healthConsult/feedBackInfo";

export default {
  name: "FeedBackInfo",
  data() {
    return {
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
      // 资讯信息反馈表格数据
      feedBackInfoList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        feedbackinfoId: null,
        consultationFormId: null,
        replyPersonId: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        consultationFormId: [
          { required: true, message: "咨询表id不能为空", trigger: "blur" }
        ],
        replyPersonId: [
          { required: true, message: "回复者id不能为空", trigger: "blur" }
        ],
        replyInfo: [
          { required: true, message: "回复内容不能为空", trigger: "blur" }
        ]
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询资讯信息反馈列表 */
    getList() {
      this.loading = true;
      listFeedBackInfo(this.queryParams).then(response => {
        this.feedBackInfoList = response.rows;
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
        feedbackinfoId: null,
        consultationFormId: null,
        replyPersonId: null,
        replyInfo: null
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
      this.ids = selection.map(item => item.feedbackinfoId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加资讯信息反馈";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const feedbackinfoId = row.feedbackinfoId || this.ids
      getFeedBackInfo(feedbackinfoId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改资讯信息反馈";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.feedbackinfoId != null) {
            updateFeedBackInfo(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addFeedBackInfo(this.form).then(response => {
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
      const feedbackinfoIds = row.feedbackinfoId || this.ids;
      this.$modal.confirm('是否确认删除资讯信息反馈编号为"' + feedbackinfoIds + '"的数据项？').then(function() {
        return delFeedBackInfo(feedbackinfoIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('healthConsult/feedBackInfo/export', {
        ...this.queryParams
      }, `feedBackInfo_${new Date().getTime()}.xlsx`)
    },

    
  }
};
</script>
