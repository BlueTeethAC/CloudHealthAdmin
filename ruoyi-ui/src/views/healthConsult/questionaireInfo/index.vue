<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="问卷信息id" prop="infoId">
        <el-input
          v-model="queryParams.infoId"
          placeholder="请输入问卷信息id"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="问卷型号" prop="model">
        <el-input
          v-model="queryParams.model"
          placeholder="请输入问卷型号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="题号" prop="questionNumber">
        <el-input
          v-model="queryParams.questionNumber"
          placeholder="请输入题号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="正确答案" prop="answer">
        <el-input
          v-model="queryParams.answer"
          placeholder="请输入正确答案"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="考题分数 " prop="fraction">
        <el-input
          v-model="queryParams.fraction"
          placeholder="请输入考题分数 "
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
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['healthConsult:questionaireInfo:add']"
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
          v-hasPermi="['healthConsult:questionaireInfo:edit']"
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
          v-hasPermi="['healthConsult:questionaireInfo:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['healthConsult:questionaireInfo:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="questionaireInfoList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="问卷信息id" align="center" prop="infoId" />
      <el-table-column label="问卷型号" align="center" prop="model" />
      <el-table-column label="题号" align="center" prop="questionNumber" />
      <el-table-column label="正确答案" align="center" prop="answer" />
      <el-table-column label="考题分数 " align="center" prop="fraction" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['healthConsult:questionaireInfo:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['healthConsult:questionaireInfo:remove']"
          >删除</el-button>
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

    <!-- 添加或修改问卷信息对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="问卷型号" prop="model">
          <el-input v-model="form.model" placeholder="请输入问卷型号" />
        </el-form-item>
        <el-form-item label="题号" prop="questionNumber">
          <el-input v-model="form.questionNumber" placeholder="请输入题号" />
        </el-form-item>
        <el-form-item label="正确答案" prop="answer">
          <el-input v-model="form.answer" placeholder="请输入正确答案" />
        </el-form-item>
        <el-form-item label="考题分数 " prop="fraction">
          <el-input v-model="form.fraction" placeholder="请输入考题分数 " />
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
import { listQuestionaireInfo, getQuestionaireInfo, delQuestionaireInfo, addQuestionaireInfo, updateQuestionaireInfo } from "@/api/healthConsult/questionaireInfo";

export default {
  name: "QuestionaireInfo",
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
      // 问卷信息表格数据
      questionaireInfoList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        infoId: null,
        model: null,
        questionNumber: null,
        answer: null,
        fraction: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        model: [
          { required: true, message: "问卷型号不能为空", trigger: "blur" }
        ],
        questionNumber: [
          { required: true, message: "题号不能为空", trigger: "blur" }
        ],
        answer: [
          { required: true, message: "正确答案不能为空", trigger: "blur" }
        ],
        fraction: [
          { required: true, message: "考题分数 不能为空", trigger: "blur" }
        ]
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询问卷信息列表 */
    getList() {
      this.loading = true;
      listQuestionaireInfo(this.queryParams).then(response => {
        this.questionaireInfoList = response.rows;
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
        infoId: null,
        model: null,
        questionNumber: null,
        answer: null,
        fraction: null
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
      this.ids = selection.map(item => item.infoId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加问卷信息";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const infoId = row.infoId || this.ids
      getQuestionaireInfo(infoId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改问卷信息";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.infoId != null) {
            updateQuestionaireInfo(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addQuestionaireInfo(this.form).then(response => {
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
      const infoIds = row.infoId || this.ids;
      this.$modal.confirm('是否确认删除问卷信息编号为"' + infoIds + '"的数据项？').then(function() {
        return delQuestionaireInfo(infoIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('healthConsult/questionaireInfo/export', {
        ...this.queryParams
      }, `questionaireInfo_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
