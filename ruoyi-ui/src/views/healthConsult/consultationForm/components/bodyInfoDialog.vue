<template>
  <div>
    <el-dialog
      :title="title"
      :visible.sync="open"
      width="1000px"
      @open="() => {getInfo()}"
      @close="()=>{closeDialog()}"
      append-to-body
    >
      <el-form
        ref="bodyInfoForm"
        :model="bodyInfoFormData"
        :rules="bodyInfoRules"
        size="medium"
        label-width="100px"
        disabled
      >
        <el-row gutter="15">
          <el-row gutter="15">
            <el-col :span="12">
              <el-form-item label="健康信息ID" prop="infoId">
                <el-input
                  v-model="bodyInfoFormData.infoId"
                  placeholder="请输入健康信息ID"
                  clearable
                  :style="{ width: '100%' }"
                ></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="用户ID" prop="userId">
                <el-input
                  v-model="bodyInfoFormData.userId"
                  placeholder="请输入用户ID"
                  clearable
                  :style="{ width: '100%' }"
                ></el-input>
              </el-form-item>
            </el-col>
          </el-row>

          <el-row>
            <el-col :span="12">
              <el-form-item label="咨询日期" prop="inputDate">
                <el-date-picker
                  v-model="bodyInfoFormData.inputDate"
                  format="HH:mm:ss"
                  value-format="HH:mm:ss"
                  :style="{ width: '100%' }"
                  placeholder="请输入咨询日期"
                  clearable
                ></el-date-picker>
              </el-form-item>
            </el-col>
          </el-row>

          <el-row gutter="15">
            <el-col :span="12">
              <el-form-item label="身高" prop="height">
                <el-input
                  v-model="bodyInfoFormData.height"
                  placeholder="请输入身高"
                  clearable
                  :style="{ width: '100%' }"
                ></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="体重" prop="weight">
                <el-input
                  v-model="bodyInfoFormData.weight"
                  placeholder="请输入体重"
                  clearable
                  :style="{ width: '100%' }"
                ></el-input>
              </el-form-item>
            </el-col>
          </el-row>

          <el-row gutter="15">
            <el-col :span="12">
              <el-form-item label="性别" prop="sex">
                <el-select
                  v-model="bodyInfoFormData.sex"
                  placeholder="请输入性别"
                  :style="{ width: '100%' }"
                >
                  <el-option
                    v-for="(item, index) in sexOptions"
                    :key="index"
                    :label="item.label"
                    :value="item.value"
                    :disabled="item.disabled"
                  ></el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="心率" prop="heartRule">
                <el-input
                  v-model="bodyInfoFormData.heartRule"
                  placeholder="请输入心率"
                  clearable
                  :style="{ width: '100%' }"
                ></el-input>
              </el-form-item>
            </el-col>
          </el-row>

          <el-row gutter="15">
            <el-col :span="12">
              <el-form-item label="血压(上压)" prop="elevatedBloodPressure">
                <el-input
                  v-model="bodyInfoFormData.elevatedBloodPressure"
                  placeholder="请输入血压(上压)"
                  clearable
                  :style="{ width: '100%' }"
                ></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="血压(下压)" prop="lowerBloodPressure">
                <el-input
                  v-model="bodyInfoFormData.lowerBloodPressure"
                  placeholder="请输入血压(下压)"
                  clearable
                  :style="{ width: '100%' }"
                ></el-input>
              </el-form-item>
            </el-col>
          </el-row>

          <el-row gutter="15">
            <el-col :span="12">
              <el-form-item label="腰围" prop="waist">
                <el-input
                  v-model="bodyInfoFormData.waist"
                  placeholder="请输入腰围"
                  clearable
                  :style="{ width: '100%' }"
                ></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="臂围" prop="hip">
                <el-input
                  v-model="bodyInfoFormData.hip"
                  placeholder="请输入臂围"
                  clearable
                  :style="{ width: '100%' }"
                >
                </el-input>
              </el-form-item>
            </el-col>
          </el-row>

          <el-row gutter="15">
            <el-col :span="12">
              <el-form-item label="上臂臂围" prop="upperArmCircumference">
                <el-input
                  v-model="bodyInfoFormData.upperArmCircumference"
                  placeholder="请输入上臂臂围"
                  clearable
                  :style="{ width: '100%' }"
                ></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="体脂" prop="bodyFat">
                <el-input
                  v-model="bodyInfoFormData.bodyFat"
                  placeholder="请输入体脂"
                  clearable
                  :style="{ width: '100%' }"
                ></el-input>
              </el-form-item>
            </el-col>
          </el-row>

          <el-row gutter="15">
            <el-col :span="12">
              <el-form-item label="内脂" prop="lactone">
                <el-input
                  v-model="bodyInfoFormData.lactone"
                  placeholder="请输入内脂"
                  clearable
                  :style="{ width: '100%' }"
                ></el-input>
              </el-form-item>
            </el-col>

            <el-col :span="12">
              <el-form-item label="血糖" prop="bloodSugar">
                <el-input
                  v-model="bodyInfoFormData.bloodSugar"
                  placeholder="请输入"
                  clearable
                  :style="{ width: '100%' }"
                ></el-input>
              </el-form-item>
            </el-col>
          </el-row>
        </el-row>
        <el-form-item size="large">
          <!-- <el-button type="primary" @click="submitForm">提交</el-button>
          <el-button @click="resetForm">重置</el-button> -->
        </el-form-item>
      </el-form>
    </el-dialog>
  </div>
</template>
<script>
import { getBodyinfo } from "@/api/healthConsult/bodyinfo";

export default {
  components: {},
  props: ["infoid", "open"],
  data() {
    return {
      title: "用户健康信息",

      // 是否显示弹出层
      // open: true,

      bodyInfoFormData: {
        infoId: undefined,
        userId: undefined,
        inputDate: null,
        height: undefined,
        weight: undefined,
        sex: 0,
        heartRule: "",
        elevatedBloodPressure: undefined,
        lowerBloodPressure: undefined,
        waist: undefined,
        hip: undefined,
        upperArmCircumference: undefined,
        bodyFat: undefined,
        lactone: undefined,
        bloodSugar: undefined,
      },
      bodyInfoRules: {
        infoId: [
          {
            required: true,
            message: "请输入健康信息ID",
            trigger: "blur",
          },
        ],
        userId: [
          {
            required: true,
            message: "请输入用户ID",
            trigger: "blur",
          },
        ],
        inputDate: [
          {
            required: true,
            message: "请输入咨询日期",
            trigger: "change",
          },
        ],
        height: [
          {
            required: true,
            message: "请输入身高",
            trigger: "blur",
          },
        ],
        weight: [
          {
            required: true,
            message: "请输入体重",
            trigger: "blur",
          },
        ],
        sex: [
          {
            required: true,
            message: "请输入性别",
            trigger: "change",
          },
        ],
        heartRule: [
          {
            required: true,
            message: "请输入心率",
            trigger: "blur",
          },
        ],
        elevatedBloodPressure: [
          {
            required: true,
            message: "请输入血压(上压)",
            trigger: "blur",
          },
        ],
        lowerBloodPressure: [
          {
            required: true,
            message: "请输入血压(下压)",
            trigger: "blur",
          },
        ],
        waist: [
          {
            required: true,
            message: "请输入腰围",
            trigger: "blur",
          },
        ],
        hip: [
          {
            required: true,
            message: "请输入臂围",
            trigger: "blur",
          },
        ],
        upperArmCircumference: [
          {
            required: true,
            message: "请输入上臂臂围",
            trigger: "blur",
          },
        ],
        bodyFat: [
          {
            required: true,
            message: "请输入体脂",
            trigger: "blur",
          },
        ],
        lactone: [
          {
            required: true,
            message: "请输入内脂",
            trigger: "blur",
          },
        ],
        bloodSugar: [
          {
            required: true,
            message: "请输入血糖",
            trigger: "blur",
          },
        ],
      },
      sexOptions: [
        {
          label: "男",
          value: 0,
        },
        {
          label: "女",
          value: 1,
        },
      ],
    };
  },
  computed: {},
  watch: {},

  created() {

  },

  mounted() {

  },

  methods: {
    submitForm() {
      this.$refs["bodyInfoForm"].validate((valid) => {
        if (!valid) return;
        // TODO 提交表单
      });
    },
    resetForm() {
      this.$refs["bodyInfoForm"].resetFields();
    },

    // 查询数据
    getInfo() {
      // 查询数据
      getBodyinfo(this.infoid).then((res) => {
        this.bodyInfoFormData = res.data;
      });
    },

    // 关闭弹窗的回调
    closeDialog(){
      this.open = false;
      this.$emit('closeDialog');
    }
  },
};
</script>
<style>
</style>
