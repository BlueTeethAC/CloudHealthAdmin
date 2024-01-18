<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="信息id" prop="infoId">
        <el-input
          v-model="queryParams.infoId"
          placeholder="请输入信息id"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="输入日期" prop="inputDate">
        <el-date-picker clearable
          v-model="queryParams.inputDate"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择输入日期">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="用户id" prop="userId">
        <el-input
          v-model="queryParams.userId"
          placeholder="请输入用户id"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>




      <!-- <el-form-item label="身高" prop="height">
        <el-input
          v-model="queryParams.height"
          placeholder="请输入身高"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="体重" prop="weight">
        <el-input
          v-model="queryParams.weight"
          placeholder="请输入体重"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="心率" prop="heartRule">
        <el-input
          v-model="queryParams.heartRule"
          placeholder="请输入心率"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="血压(上压)" prop="elevatedBloodPressure">
        <el-input
          v-model="queryParams.elevatedBloodPressure"
          placeholder="请输入血压(上压)"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="血压(下压)" prop="lowerBloodPressure">
        <el-input
          v-model="queryParams.lowerBloodPressure"
          placeholder="请输入血压(下压)"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="血糖" prop="bloodSugar">
        <el-input
          v-model="queryParams.bloodSugar"
          placeholder="请输入血糖"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="性别" prop="sex">
        <el-select v-model="queryParams.sex" placeholder="请选择性别" clearable>
          <el-option
            v-for="dict in dict.type.user_sex"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="腰围" prop="waist">
        <el-input
          v-model="queryParams.waist"
          placeholder="请输入腰围"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="臀围" prop="hip">
        <el-input
          v-model="queryParams.hip"
          placeholder="请输入臀围"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="上臂臂围" prop="upperArmCircumference">
        <el-input
          v-model="queryParams.upperArmCircumference"
          placeholder="请输入上臂臂围"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="体脂" prop="bodyFat">
        <el-input
          v-model="queryParams.bodyFat"
          placeholder="请输入体脂"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="内脂" prop="lactone">
        <el-input
          v-model="queryParams.lactone"
          placeholder="请输入内脂"
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
          v-hasPermi="['healthConsult:bodyinfo:add']"
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
          v-hasPermi="['healthConsult:bodyinfo:edit']"
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
          v-hasPermi="['healthConsult:bodyinfo:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['healthConsult:bodyinfo:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="bodyinfoList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="信息id" align="center" prop="infoId" />
      <el-table-column label="输入日期" align="center" prop="inputDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.inputDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="用户id" align="center" prop="userId" />
      <el-table-column label="身高" align="center" prop="height" />
      <el-table-column label="体重" align="center" prop="weight" />
      <el-table-column label="心率" align="center" prop="heartRule" />
      <el-table-column label="血压(上压)" align="center" prop="elevatedBloodPressure" />
      <el-table-column label="血压(下压)" align="center" prop="lowerBloodPressure" />
      <el-table-column label="血糖" align="center" prop="bloodSugar" />
      <el-table-column label="性别" align="center" prop="sex">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.user_sex" :value="scope.row.sex"/>
        </template>
      </el-table-column>
      <el-table-column label="腰围" align="center" prop="waist" />
      <el-table-column label="臀围" align="center" prop="hip" />
      <el-table-column label="上臂臂围" align="center" prop="upperArmCircumference" />
      <el-table-column label="体脂" align="center" prop="bodyFat" />
      <el-table-column label="内脂" align="center" prop="lactone" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['healthConsult:bodyinfo:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['healthConsult:bodyinfo:remove']"
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

    <!-- 添加或修改用户个人身体信息对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="输入日期" prop="inputDate">
          <el-date-picker clearable
            v-model="form.inputDate"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择输入日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="用户id" prop="userId">
          <el-input v-model="form.userId" placeholder="请输入用户id" />
        </el-form-item>

        
        <el-form-item label="身高" prop="height">
          <el-input v-model="form.height" placeholder="请输入身高" />
        </el-form-item>
        <el-form-item label="体重" prop="weight">
          <el-input v-model="form.weight" placeholder="请输入体重" />
        </el-form-item>
        <el-form-item label="心率" prop="heartRule">
          <el-input v-model="form.heartRule" placeholder="请输入心率" />
        </el-form-item>
        <el-form-item label="血压" prop="elevatedBloodPressure">
          <el-input v-model="form.elevatedBloodPressure" placeholder="请输入血压" />
        </el-form-item>
        <el-form-item label="血压" prop="lowerBloodPressure">
          <el-input v-model="form.lowerBloodPressure" placeholder="请输入血压" />
        </el-form-item>
        <el-form-item label="血糖" prop="bloodSugar">
          <el-input v-model="form.bloodSugar" placeholder="请输入血糖" />
        </el-form-item>



        <el-form-item label="性别" prop="sex">
          <el-select v-model="form.sex" placeholder="请选择性别">
            <el-option
              v-for="dict in dict.type.user_sex"
              :key="dict.value"
              :label="dict.label"
              :value="parseInt(dict.value)"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="腰围" prop="waist">
          <el-input v-model="form.waist" placeholder="请输入腰围" />
        </el-form-item>
        <el-form-item label="臀围" prop="hip">
          <el-input v-model="form.hip" placeholder="请输入臀围" />
        </el-form-item>
        <el-form-item label="上臂臂围" prop="upperArmCircumference">
          <el-input v-model="form.upperArmCircumference" placeholder="请输入上臂臂围" />
        </el-form-item>
        <el-form-item label="体脂" prop="bodyFat">
          <el-input v-model="form.bodyFat" placeholder="请输入体脂" />
        </el-form-item>
        <el-form-item label="内脂" prop="lactone">
          <el-input v-model="form.lactone" placeholder="请输入内脂" />
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
import { listBodyinfo, getBodyinfo, delBodyinfo, addBodyinfo, updateBodyinfo } from "@/api/healthConsult/bodyinfo";

export default {
  name: "Bodyinfo",
  dicts: ['user_sex'],
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
      // 用户个人身体信息表格数据
      bodyinfoList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        infoId: null,
        inputDate: null,
        userId: null,
        height: null,
        weight: null,
        heartRule: null,
        elevatedBloodPressure: null,
        lowerBloodPressure: null,
        bloodSugar: null,
        sex: null,
        waist: null,
        hip: null,
        upperArmCircumference: null,
        bodyFat: null,
        lactone: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        inputDate: [
          { required: true, message: "输入日期不能为空", trigger: "blur" }
        ],
        userId: [
          { required: true, message: "用户id不能为空", trigger: "blur" }
        ],
        height: [
          { required: true, message: "身高不能为空", trigger: "blur" }
        ],
        weight: [
          { required: true, message: "体重不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询用户个人身体信息列表 */
    getList() {
      this.loading = true;
      listBodyinfo(this.queryParams).then(response => {
        this.bodyinfoList = response.rows;
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
        inputDate: null,
        userId: null,
        height: null,
        weight: null,
        heartRule: null,
        elevatedBloodPressure: null,
        lowerBloodPressure: null,
        bloodSugar: null,
        sex: null,
        waist: null,
        hip: null,
        upperArmCircumference: null,
        bodyFat: null,
        lactone: null
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
      this.title = "添加用户个人身体信息";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const infoId = row.infoId || this.ids
      getBodyinfo(infoId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改用户个人身体信息";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.infoId != null) {
            updateBodyinfo(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addBodyinfo(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除用户个人身体信息编号为"' + infoIds + '"的数据项？').then(function() {
        return delBodyinfo(infoIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('healthConsult/bodyinfo/export', {
        ...this.queryParams
      }, `bodyinfo_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
