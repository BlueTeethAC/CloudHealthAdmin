<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="系列id" prop="seriesId">
        <el-input
          v-model="queryParams.seriesId"
          placeholder="请输入系列id"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="系列名称" prop="seriesName">
        <el-input
          v-model="queryParams.seriesName"
          placeholder="请输入系列名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="系列创建者id" prop="seriesCreaterId">
        <el-input
          v-model="queryParams.seriesCreaterId"
          placeholder="请输入系列创建者id"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="系列分类" prop="seriesClassify">
        <el-input
          v-model="queryParams.seriesClassify"
          placeholder="请输入系列分类"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="系列简介" prop="seriesIntroduction">
        <el-input
          v-model="queryParams.seriesIntroduction"
          placeholder="请输入系列简介"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="系列封面路径" prop="seriesPhoto">
        <el-input
          v-model="queryParams.seriesPhoto"
          placeholder="请输入系列封面路径"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="上传时间" prop="seriesUploadDate">
        <el-date-picker clearable
          v-model="queryParams.seriesUploadDate"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择上传时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="是否免费" prop="seriesFree">
        <el-select v-model="queryParams.seriesFree" placeholder="请选择是否免费" clearable>
          <el-option
            v-for="dict in dict.type.is_free"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="订阅价格" prop="seriesPrice">
        <el-input
          v-model="queryParams.seriesPrice"
          placeholder="请输入订阅价格"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="系列状态" prop="status">
        <el-select v-model="queryParams.status" placeholder="请选择系列状态" clearable>
          <el-option
            v-for="dict in dict.type.process_status"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
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
          v-hasPermi="['video:seriesInfo:add']"
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
          v-hasPermi="['video:seriesInfo:edit']"
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
          v-hasPermi="['video:seriesInfo:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['video:seriesInfo:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="seriesInfoList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="系列id" align="center" prop="seriesId" />
      <el-table-column label="系列名称" align="center" prop="seriesName" />
      <el-table-column label="系列创建者id" align="center" prop="seriesCreaterId" />
      <el-table-column label="系列分类" align="center" prop="seriesClassify" />
      <el-table-column label="系列简介" align="center" prop="seriesIntroduction" />
      <el-table-column label="系列封面路径" align="center" prop="seriesPhoto" />
      <el-table-column label="上传时间" align="center" prop="seriesUploadDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.seriesUploadDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="是否免费" align="center" prop="seriesFree">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.is_free" :value="scope.row.seriesFree"/>
        </template>
      </el-table-column>
      <el-table-column label="订阅价格" align="center" prop="seriesPrice" />
      <el-table-column label="系列状态" align="center" prop="status">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.process_status" :value="scope.row.status"/>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['video:seriesInfo:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['video:seriesInfo:remove']"
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

    <!-- 添加或修改视频系列对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="系列名称" prop="seriesName">
          <el-input v-model="form.seriesName" placeholder="请输入系列名称" />
        </el-form-item>
        <el-form-item label="系列创建者id" prop="seriesCreaterId">
          <el-input v-model="form.seriesCreaterId" placeholder="请输入系列创建者id" />
        </el-form-item>
        <el-form-item label="系列分类" prop="seriesClassify">
          <el-input v-model="form.seriesClassify" placeholder="请输入系列分类" />
        </el-form-item>
        <el-form-item label="系列简介" prop="seriesIntroduction">
          <el-input v-model="form.seriesIntroduction" placeholder="请输入系列简介" />
        </el-form-item>
        <el-form-item label="系列封面路径" prop="seriesPhoto">
          <el-input v-model="form.seriesPhoto" placeholder="请输入系列封面路径" />
        </el-form-item>
        <el-form-item label="上传时间" prop="seriesUploadDate">
          <el-date-picker clearable
            v-model="form.seriesUploadDate"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择上传时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="是否免费" prop="seriesFree">
          <el-select v-model="form.seriesFree" placeholder="请选择是否免费">
            <el-option
              v-for="dict in dict.type.is_free"
              :key="dict.value"
              :label="dict.label"
              :value="parseInt(dict.value)"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="订阅价格" prop="seriesPrice">
          <el-input v-model="form.seriesPrice" placeholder="请输入订阅价格" />
        </el-form-item>
        <el-form-item label="系列状态" prop="status">
          <el-select v-model="form.status" placeholder="请选择系列状态">
            <el-option
              v-for="dict in dict.type.process_status"
              :key="dict.value"
              :label="dict.label"
              :value="parseInt(dict.value)"
            ></el-option>
          </el-select>
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
import { listSeriesInfo, getSeriesInfo, delSeriesInfo, addSeriesInfo, updateSeriesInfo } from "@/api/video/seriesInfo";

export default {
  name: "SeriesInfo",
  dicts: ['process_status', 'is_free'],
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
      // 视频系列表格数据
      seriesInfoList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        seriesId: null,
        seriesName: null,
        seriesCreaterId: null,
        seriesClassify: null,
        seriesIntroduction: null,
        seriesPhoto: null,
        seriesUploadDate: null,
        seriesFree: null,
        seriesPrice: null,
        status: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        seriesName: [
          { required: true, message: "系列名称不能为空", trigger: "blur" }
        ],
        seriesCreaterId: [
          { required: true, message: "系列创建者id不能为空", trigger: "blur" }
        ],
        seriesClassify: [
          { required: true, message: "系列分类不能为空", trigger: "blur" }
        ],
        seriesPhoto: [
          { required: true, message: "系列封面路径不能为空", trigger: "blur" }
        ],
        seriesUploadDate: [
          { required: true, message: "上传时间不能为空", trigger: "blur" }
        ],
        seriesFree: [
          { required: true, message: "是否免费不能为空", trigger: "change" }
        ],
        seriesPrice: [
          { required: true, message: "订阅价格不能为空", trigger: "blur" }
        ],
        status: [
          { required: true, message: "系列状态不能为空", trigger: "change" }
        ]
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询视频系列列表 */
    getList() {
      this.loading = true;
      listSeriesInfo(this.queryParams).then(response => {
        this.seriesInfoList = response.rows;
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
        seriesId: null,
        seriesName: null,
        seriesCreaterId: null,
        seriesClassify: null,
        seriesIntroduction: null,
        seriesPhoto: null,
        seriesUploadDate: null,
        seriesFree: null,
        seriesPrice: null,
        status: null
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
      this.ids = selection.map(item => item.seriesId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加视频系列";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const seriesId = row.seriesId || this.ids
      getSeriesInfo(seriesId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改视频系列";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.seriesId != null) {
            updateSeriesInfo(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addSeriesInfo(this.form).then(response => {
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
      const seriesIds = row.seriesId || this.ids;
      this.$modal.confirm('是否确认删除视频系列编号为"' + seriesIds + '"的数据项？').then(function() {
        return delSeriesInfo(seriesIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('video/seriesInfo/export', {
        ...this.queryParams
      }, `seriesInfo_${new Date().getTime()}.xlsx`)
    }
  }
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