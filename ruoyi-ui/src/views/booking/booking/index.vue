<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="预约信息id" prop="infoId">
        <el-input
          v-model="queryParams.infoId"
          placeholder="请输入预约信息id"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="预约人id" prop="userInfoId">
        <el-input
          v-model="queryParams.userInfoId"
          placeholder="请输入预约人id"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="信息生成日期" prop="infoTime">
        <el-date-picker clearable
          v-model="queryParams.infoTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择信息生成日期">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="预约日期" prop="bookingTime">
        <el-date-picker clearable
          v-model="queryParams.bookingTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择预约日期">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="预约目标 id" prop="targetUserId">
        <el-input
          v-model="queryParams.targetUserId"
          placeholder="请输入预约目标 id"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="预约状态" prop="status">
        <el-select v-model="queryParams.status" placeholder="请选择预约状态" clearable>
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
          v-hasPermi="['booking:booking:add']"
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
          v-hasPermi="['booking:booking:edit']"
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
          v-hasPermi="['booking:booking:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['booking:booking:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="bookingList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="预约信息id" align="center" prop="infoId" />
      <el-table-column label="预约人id" align="center" prop="userInfoId" />
      <el-table-column label="信息生成日期" align="center" prop="infoTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.infoTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="预约日期" align="center" prop="bookingTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.bookingTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="预约目标 id" align="center" prop="targetUserId" />
      <el-table-column label="预约状态" align="center" prop="status">
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
            v-hasPermi="['booking:booking:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['booking:booking:remove']"
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

    <!-- 添加或修改私教，健身房预约对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="预约人id" prop="userInfoId">
          <el-input v-model="form.userInfoId" placeholder="请输入预约人id" />
        </el-form-item>
        <el-form-item label="信息生成日期" prop="infoTime">
          <el-date-picker clearable
            v-model="form.infoTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择信息生成日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="预约日期" prop="bookingTime">
          <el-date-picker clearable
            v-model="form.bookingTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择预约日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="预约目标 id" prop="targetUserId">
          <el-input v-model="form.targetUserId" placeholder="请输入预约目标 id" />
        </el-form-item>
        <el-form-item label="预约状态" prop="status">
          <el-radio-group v-model="form.status">
            <el-radio
              v-for="dict in dict.type.process_status"
              :key="dict.value"
              :label="parseInt(dict.value)"
            >{{dict.label}}</el-radio>
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
import { listBooking, getBooking, delBooking, addBooking, updateBooking } from "@/api/booking/booking";

export default {
  name: "Booking",
  dicts: ['process_status'],
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
      // 私教，健身房预约表格数据
      bookingList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        infoId: null,
        userInfoId: null,
        infoTime: null,
        bookingTime: null,
        targetUserId: null,
        status: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        userInfoId: [
          { required: true, message: "预约人id不能为空", trigger: "blur" }
        ],
        infoTime: [
          { required: true, message: "信息生成日期不能为空", trigger: "blur" }
        ],
        bookingTime: [
          { required: true, message: "预约日期不能为空", trigger: "blur" }
        ],
        targetUserId: [
          { required: true, message: "预约目标 id不能为空", trigger: "blur" }
        ],
        status: [
          { required: true, message: "预约状态不能为空", trigger: "change" }
        ]
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询私教，健身房预约列表 */
    getList() {
      this.loading = true;
      listBooking(this.queryParams).then(response => {
        this.bookingList = response.rows;
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
        userInfoId: null,
        infoTime: null,
        bookingTime: null,
        targetUserId: null,
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
      this.ids = selection.map(item => item.infoId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加私教，健身房预约";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const infoId = row.infoId || this.ids
      getBooking(infoId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改私教，健身房预约";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.infoId != null) {
            updateBooking(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addBooking(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除私教，健身房预约编号为"' + infoIds + '"的数据项？').then(function() {
        return delBooking(infoIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('booking/booking/export', {
        ...this.queryParams
      }, `booking_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
