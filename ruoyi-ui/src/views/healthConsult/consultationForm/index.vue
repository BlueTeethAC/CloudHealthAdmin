<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="咨询表id" prop="consultationFormId">
        <el-input
          v-model="queryParams.consultationFormId"
          placeholder="请输入咨询表id"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <!-- <el-form-item label="健康信息id" prop="bodyinfoId">
        <el-input
          v-model="queryParams.bodyinfoId"
          placeholder="请输入身体健康信息id"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item> -->
      <!-- <el-form-item label="健康问卷得分" prop="score">
        <el-input
          v-model="queryParams.score"
          placeholder="请输入健康问卷得分"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item> -->
      <el-form-item label="输入日期" prop="inputDate">
        <el-date-picker clearable
          v-model="queryParams.inputDate"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择输入日期">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="咨询人id" prop="userId">
        <el-input
          v-model="queryParams.userId"
          placeholder="请输入输入咨询人id"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="咨询状态" prop="state">
        <el-select v-model="queryParams.state" placeholder="请选择咨询单状态" clearable>
          <el-option
            v-for="dict in dict.type.consultation_status"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <!-- <el-form-item label="问卷答题记录" prop="questionnaireRecordings">
        <el-input
          v-model="queryParams.questionnaireRecordings"
          placeholder="请输入问卷答题记录"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item> -->
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
          v-hasPermi="['healthConsult:consultationForm:add']"
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
          v-hasPermi="['healthConsult:consultationForm:edit']"
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
          v-hasPermi="['healthConsult:consultationForm:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['healthConsult:consultationForm:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="consultationFormList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="咨询表id" align="center" prop="consultationFormId" />
      <el-table-column label="身体健康信息id" align="center" prop="bodyinfoId" />
      <el-table-column label="健康问卷得分" align="center" prop="score" />
      <el-table-column label="输入日期" align="center" prop="inputDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.inputDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="咨询人id" align="center" prop="userId" />
      <el-table-column label="咨询单状态" align="center" prop="state">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.consultation_status" :value="scope.row.state"/>
        </template>
      </el-table-column>
      <!-- <el-table-column label="问卷答题记录" align="center" prop="questionnaireRecordings" /> -->
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['healthConsult:consultationForm:edit']"
          >处理</el-button>
          <!-- <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['healthConsult:consultationForm:remove']"
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

    <!-- 添加或修改咨询信息对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <!-- <el-form-item label="身体健康信息id" prop="bodyinfoId"> -->
        <el-form-item label="信息id" prop="bodyinfoId">
          <!-- <el-input v-model="form.bodyinfoId" placeholder="请输入身体健康信息id" /> -->
          <el-button type="primary" @click="bodyInfoOpen=true">查看健康信息</el-button>
        </el-form-item>
        <el-form-item label="问卷得分" prop="score">
          <el-input v-model="form.score" placeholder="请输入健康问卷得分" disabled />
        </el-form-item>
        <el-form-item label="输入日期" prop="inputDate">
          <el-date-picker clearable
            v-model="form.inputDate"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择输入日期"
            disabled>
          </el-date-picker>
        </el-form-item>
        <el-form-item label="咨询人id" prop="userId">
          <el-input v-model="form.userId" placeholder="请输入输入人 id" disabled />
        </el-form-item>
        <el-form-item label="咨询状态" prop="state">
          <el-select v-model="form.state" placeholder="请选择咨询单状态" disabled>
            <el-option
              v-for="dict in dict.type.consultation_status"
              :key="dict.value"
              :label="dict.label"
              :value="parseInt(dict.value)"
            ></el-option>
          </el-select>
        </el-form-item>

        <el-form-item label="健康建议" prop="questionnaireRecordings">
          <el-input type="textarea" rows="6" v-model="form.questionnaireRecordings" placeholder="请输入建议"/>
        </el-form-item>

        <!-- 历史咨询记录 -->
        <hr/>
        <div v-for="(item,index) in feedBackInfo" :key="index">

          <!-- 咨询人信息 -->
          <el-row :gutter="16" v-show="item.role!=6">
            <!-- 回复者 -->
            <el-col :span="5">
              咨询人{{item.replyPersonId}}
            </el-col>
            <!-- 回复信息 -->
            <el-col :span="12">
              {{item.replyInfo}}
            </el-col>
          </el-row>

           <!-- 营养师 -->
           <el-row :gutter="16" v-show="item.role==6">
            <!-- 回复者 -->
            <el-col :span="5">
              营养师{{item.replyPersonId}}
            </el-col>
            <!-- 回复信息 -->
            <el-col :span="12">
              {{item.replyInfo}}
            </el-col>
          </el-row>

        </div>

        <br/>
        <br/>

        <el-form-item label="回复" prop="">
          <el-input type="textarea" rows="6" placeholder="请输入建议"/>
        </el-form-item>
      
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm" v-show="form.state == 0 || form.state == 1">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>


    <body-info-dialog @closeDialog="closeDialog" :infoid = "dialogBodyInfoId" :open="bodyInfoOpen"></body-info-dialog>

  </div>
</template>

<script>
import { listConsultationForm, getConsultationForm, delConsultationForm, addConsultationForm, updateConsultationForm } from "@/api/healthConsult/consultationForm";

import { listFeedBackInfo, getListInfoWithRole } from "@/api/healthConsult/feedBackInfo"

// 引入健康信息详情组件
import bodyInfoDialog from './components/bodyInfoDialog.vue';

export default {
  name: "ConsultationForm",
  dicts: ['consultation_status'],

  // 声明组件
  components:{
    bodyInfoDialog
  },

  data() {
    return {

      // 咨询回复信息
      feedBackInfo: null,

      // 用户信息弹窗状态
      bodyInfoOpen: false,

      // 身体信息id
      dialogBodyInfoId: null,

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
      // 咨询信息表格数据
      consultationFormList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        consultationFormId: null,
        bodyinfoId: null,
        score: null,
        inputDate: null,
        userId: null,
        state: null,
        questionnaireRecordings: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        bodyinfoId: [
          { required: true, message: "身体健康信息id不能为空", trigger: "blur" }
        ],
        score: [
          { required: true, message: "健康问卷得分不能为空", trigger: "blur" }
        ],
        inputDate: [
          { required: true, message: "输入日期不能为空", trigger: "blur" }
        ],
        userId: [
          { required: true, message: "输入人 id不能为空", trigger: "blur" }
        ],
        state: [
          { required: true, message: "咨询单状态不能为空", trigger: "change" }
        ],
        questionnaireRecordings: [
          { required: true, message:"咨询建议不能为空", trigger: "blur" }
        ]

      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询咨询信息列表 */
    getList() {
      this.loading = true;
      listConsultationForm(this.queryParams).then(response => {
        this.consultationFormList = response.rows;
        // console.log(this.consultationFormList);
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
        consultationFormId: null,
        bodyinfoId: null,
        score: null,
        inputDate: null,
        userId: null,
        state: null,
        questionnaireRecordings: null
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
      this.ids = selection.map(item => item.consultationFormId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加咨询信息";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();

      const consultationFormId = row.consultationFormId || this.ids
      getConsultationForm(consultationFormId).then(response => {
        this.form = response.data;
        this.open = true;

        // 查询追问信息
        this.getHistoryInfo(this.form.consultationFormId);

        this.dialogBodyInfoId = this.form.bodyinfoId;

        // this.title = "修改咨询信息";
        this.title = "处理咨询信息";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {

        this.form.state = 1;// 表单状态修改为已回复

        if (valid) {
          if (this.form.consultationFormId != null) {
            updateConsultationForm(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addConsultationForm(this.form).then(response => {
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
      const consultationFormId = row.consultationFormId || this.ids;
      this.$modal.confirm('是否确认删除咨询信息编号为"' + consultationFormId + '"的数据项？').then(function() {
        return delConsultationForm(consultationFormId);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('healthConsult/consultationForm/export', {
        ...this.queryParams
      }, `consultationForm_${new Date().getTime()}.xlsx`)
    },

    // 子组件修改父组件属性的方法
    closeDialog(){
      this.bodyInfoOpen = false;
    },

    // 根据咨询表id 查询咨询历史信息
    getHistoryInfo(formId){

      getListInfoWithRole(formId).then(res=>{
        this.feedBackInfo = res.rows;// 赋值
        console.log(this.feedBackInfo)
      });
    }
  }
};
</script>
