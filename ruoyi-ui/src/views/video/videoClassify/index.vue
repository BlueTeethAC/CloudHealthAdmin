<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      
      <!-- <el-form-item label="分类信息id" prop="classifyId">
        <el-input
          v-model="queryParams.classifyId"
          placeholder="请输入分类信息id"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item> -->
      
      <el-form-item label="分类编号" prop="classifyNum">
        <el-input
          v-model="queryParams.classifyNum"
          placeholder="请输入分类编号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      
      <el-form-item label="分类名称" prop="classifyName">
        <el-input
          v-model="queryParams.classifyName"
          placeholder="请输入分类名称"
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
          v-hasPermi="['video:videoClassify:add']"
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
          v-hasPermi="['video:videoClassify:edit']"
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
          v-hasPermi="['video:videoClassify:remove']"
        >删除</el-button>
      </el-col>

      <!-- <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['video:videoClassify:export']"
        >导出</el-button>
      </el-col> -->

      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="videoClassifyList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />

      <el-table-column label="分类信息id" align="center" prop="classifyId" />
      
      <el-table-column label="分类编号" align="center" prop="classifyNum" />
      
      <el-table-column label="分类名称" align="center" prop="classifyName" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['video:videoClassify:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['video:videoClassify:remove']"
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

    <!-- 添加或修改视频课程分类对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="分类编号" prop="classifyNum">
          <el-input v-model="form.classifyNum" placeholder="请输入分类编号" />
        </el-form-item>
        <el-form-item label="分类名称" prop="classifyName">
          <el-input v-model="form.classifyName" placeholder="请输入分类名称" />
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
import { listVideoClassify, getVideoClassify, delVideoClassify, addVideoClassify, updateVideoClassify } from "@/api/video/videoClassify";

export default {
  name: "VideoClassify",
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
      // 视频课程分类表格数据
      videoClassifyList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        classifyId: null,
        classifyNum: null,
        classifyName: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        classifyNum: [
          { required: true, message: "分类编号不能为空", trigger: "blur" }
        ],
        classifyName: [
          { required: true, message: "分类名称不能为空", trigger: "blur" }
        ]
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询视频课程分类列表 */
    getList() {
      this.loading = true;
      listVideoClassify(this.queryParams).then(response => {
        this.videoClassifyList = response.rows;
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
        classifyId: null,
        classifyNum: null,
        classifyName: null
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
      this.ids = selection.map(item => item.classifyId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加视频课程分类";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const classifyId = row.classifyId || this.ids
      getVideoClassify(classifyId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改视频课程分类";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.classifyId != null) {
            updateVideoClassify(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addVideoClassify(this.form).then(response => {
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
      const classifyIds = row.classifyId || this.ids;
      this.$modal.confirm('是否确认删除视频课程分类编号为"' + classifyIds + '"的数据项？').then(function() {
        return delVideoClassify(classifyIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('video/videoClassify/export', {
        ...this.queryParams
      }, `videoClassify_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
