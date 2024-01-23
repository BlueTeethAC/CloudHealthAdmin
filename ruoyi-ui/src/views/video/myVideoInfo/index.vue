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
      <el-form-item label="视频id" prop="videoId">
        <el-input
          v-model="queryParams.videoId"
          placeholder="请输入视频id"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>

      <el-form-item label="视频类型" prop="videoModel">
        <el-select
          v-model="queryParams.videoModel"
          placeholder="请选择视频类型"
          clearable
        >
          <el-option
            v-for="dict in dict.type.video_model"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          ></el-option>
        </el-select>
      </el-form-item>

      <!-- 视频分类 -->
      <el-form-item label="视频分类" prop="videoCalssify">
        <el-select
          v-model="queryParams.videoCalssify"
          placeholder="请选择视频分类"
          clearable
        >
          <el-option
            v-for="dict in dict.type.video_classify"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          ></el-option>
        </el-select>
      </el-form-item>

      <el-form-item label="所属系列" prop="videoSeries">
        <el-input
          v-model="queryParams.videoSeries"
          placeholder="请输入视频所属系列"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="视频名称" prop="videoName">
        <el-input
          v-model="queryParams.videoName"
          placeholder="请输入视频名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="上传时间" prop="videoUploadDate">
        <el-date-picker
          clearable
          v-model="queryParams.videoUploadDate"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择上传时间"
        >
        </el-date-picker>
      </el-form-item>

      <el-form-item label="视频状态" prop="status">
        <el-select
          v-model="queryParams.status"
          placeholder="请选择视频状态"
          clearable
        >
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
          v-hasPermi="['video:videoInfo:add']"
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
          v-hasPermi="['video:videoInfo:edit']"
          >修改</el-button
        >
      </el-col> -->

      <!-- <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['video:videoInfo:remove']"
        >删除</el-button>
      </el-col> -->

      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['video:videoInfo:export']"
          >导出</el-button
        >
      </el-col>
      <right-toolbar
        :showSearch.sync="showSearch"
        @queryTable="getList"
      ></right-toolbar>
    </el-row>

    <el-table
      v-loading="loading"
      :data="myVideoInfoList"
      @selection-change="handleSelectionChange"
    >
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="视频id" align="center" prop="videoId" />

      <el-table-column label="视频类型" align="center" prop="videoModel">
        <template slot-scope="scope">
          <dict-tag
            :options="dict.type.video_model"
            :value="scope.row.videoModel"
          />
        </template>
      </el-table-column>

      <el-table-column label="视频分类" align="center" prop="videoCalssify">
        <template slot-scope="scope">
          <dict-tag
            :options="dict.type.video_classify"
            :value="scope.row.videoCalssify"
          />
        </template>
      </el-table-column>

      <el-table-column label="视频所属系列" align="center" prop="videoSeries" />
      <el-table-column label="视频名称" align="center" prop="videoName" />
      <el-table-column
        label="上传时间"
        align="center"
        prop="videoUploadDate"
        width="180"
      >
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.videoUploadDate, "{y}-{m}-{d}") }}</span>
        </template>
      </el-table-column>

      <el-table-column label="视频状态" align="center" prop="status">
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
            :disabled="scope.row.status != 0 && scope.row.status != 3"
            v-hasPermi="['video:videoInfo:edit']"
            >修改</el-button
          >

          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            :disabled="scope.row.status != 2"
            v-hasPermi="['video:videoInfo:edit']"
            >下架</el-button
          >

          <!-- <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['video:videoInfo:remove']"
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

    <!-- 添加或修改我的课程视频对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="视频类型" prop="videoModel">
          <el-select
            v-model="form.videoModel"
            placeholder="请选择视频类型"
            :disabled="form.status === 1 || form.status === 2"
          >
            <el-option
              v-for="dict in dict.type.video_model"
              :key="dict.value"
              :label="dict.label"
              :value="parseInt(dict.value)"
            ></el-option>
          </el-select>
        </el-form-item>

        <el-form-item label="视频分类" prop="videoCalssify">
          <el-select
            v-model="form.videoCalssify"
            placeholder="请选择视频分类"
            :disabled="form.status === 1 || form.status === 2"
          >
            <el-option
              v-for="dict in dict.type.video_classify"
              :key="dict.value"
              :label="dict.label"
              :value="parseInt(dict.value)"
            ></el-option>
          </el-select>
        </el-form-item>

        <el-form-item
          label="视频封面"
          prop="videoPhoto"
          :disabled="form.status === 1 || form.status === 2"
        >
          <!-- <el-input v-model="form.videoPhoto" placeholder="请上传视频封面" /> -->
          <el-upload
            class="avatar-uploader"
            :action="uploadFileUrl"
            :show-file-list="false"
            :on-success="photoHandleAvatarSuccess"
            :before-upload="photoBeforeAvatarUpload"
            :disabled="form.status === 1 || form.status === 2"
          >
            <img v-if="form.videoPhoto" :src="form.videoPhoto" class="avatar" />
            <i v-else class="el-icon-plus avatar-uploader-icon"></i>
          </el-upload>
        </el-form-item>

        <el-form-item
          label="上传视频"
          prop="videoPath"
          :disabled="form.status != 0 || form.status != 3"
        >
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
            <el-button
              size="small"
              type="primary"
              :disabled="form.status === 1 || form.status === 2"
              v-show="form.status === 0 || form.status === 3"
              >点击上传</el-button
            >

            <el-button
              size="small"
              type="primary"
              @click="reviewVideo(form.videoPath)"
              v-show="form.status != 0"
              >点击查看视频</el-button
            >

            <div
              slot="tip"
              class="el-upload__tip"
              v-show="form.status === 0 || form.status === 3"
            >
              只能上传mp4文件，且不超过1GB
            </div>
          </el-upload>
        </el-form-item>

        <el-form-item label="所属系列" prop="videoSeries">
          <el-input
            v-model="form.videoSeries"
            placeholder="请输入视频所属系列"
            :disabled="form.status === 1 || form.status === 2"
          />
        </el-form-item>

        <el-form-item label="视频名称" prop="videoName">
          <el-input
            v-model="form.videoName"
            placeholder="请输入视频名称"
            :disabled="form.status === 1 || form.status === 2"
          />
        </el-form-item>

        <!-- <el-form-item label="视频状态" prop="status">
          <el-select v-model="form.status" placeholder="请选择视频状态">
            <el-option
              v-for="dict in dict.type.video_status"
              :key="dict.value"
              :label="dict.label"
              :value="parseInt(dict.value)"
            ></el-option>
          </el-select>
        </el-form-item> -->
      </el-form>

      <div slot="footer" class="dialog-footer">
        <el-button
          type="primary"
          @click="submitForm"
          v-show="form.status === 0 || form.status === 3"
          >确 定</el-button
        >
        <el-button
          type="danger"
          @click="tankeOffForm"
          v-show="form.status === 2"
          >下 架</el-button
        >
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import {
  listMyVideoInfo,
  getMyVideoInfo,
  delMyVideoInfo,
  addMyVideoInfo,
  updateMyVideoInfo,
  isDisableButton,
} from "@/api/video/myVideoInfo";

export default {
  name: "MyVideoInfo",
  dicts: ["video_status", "video_model", "video_classify"],
  data() {
    return {
      // 文件上传地址
      uploadFileUrl: process.env.VUE_APP_BASE_API + "/minio/file/upload", // 上传文件服务器地址

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
      // 我的课程视频表格数据
      myVideoInfoList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        videoId: null,
        videoModel: null,
        videoCalssify: null,
        videoSeries: null,
        videoName: null,
        videoUploadDate: null,
        status: null,
        userId: this.$store.state.user.id,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        videoModel: [
          { required: true, message: "视频类型不能为空", trigger: "blur" },
        ],
        videoCalssify: [
          { required: true, message: "视频分类不能为空", trigger: "blur" },
        ],
        videoPath: [
          { required: true, message: "访问路径不能为空", trigger: "blur" },
        ],
        videoName: [
          { required: true, message: "视频名称不能为空", trigger: "blur" },
        ],
        videoPhoto: [
          { required: true, message: "视频封面路径不能为空", trigger: "blur" },
        ],
        videoUploadUser: [
          { required: true, message: "视频上传者id不能为空", trigger: "blur" },
        ],
        videoUploadDate: [
          { required: true, message: "上传时间不能为空", trigger: "blur" },
        ],
        status: [
          { required: true, message: "视频状态不能为空", trigger: "change" },
        ],
      },
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询我的课程视频列表 */
    getList() {
      this.loading = true;

      // 添加自己的用户信息id
      this.queryParams.videoUploadUser = this.$store.state.user.id;

      listMyVideoInfo(this.queryParams).then((response) => {
        this.myVideoInfoList = response.rows;
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
        videoId: null,
        videoModel: null,
        videoCalssify: null,
        videoPath: null,
        videoSeries: null,
        videoName: null,
        videoPhoto: null,
        videoUploadUser: null,
        videoUploadDate: null,
        status: 0,
        // status: null
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
      this.ids = selection.map((item) => item.videoId);
      this.single = selection.length !== 1;
      this.multiple = !selection.length;
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.form.status = 0; // 将状态设置为0待审核
      this.open = true;
      this.title = "添加我的课程视频";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const videoId = row.videoId || this.ids;
      getMyVideoInfo(videoId).then((response) => {
        this.form = response.data;
        this.open = true;
        this.title = "修改我的课程视频";
      });
    },
    /** 提交按钮 */
    submitForm() {
      // 将上传者id填入
      // this.form.videoUploadUser = this.$store.state.user.id;

      this.$refs["form"].validate((valid) => {
        // 将上传者id填入
        this.form.videoUploadUser = this.$store.state.user.id;

        // 将表单状态都改为待审核
        this.form.status = 0;

        if (valid) {
          if (this.form.videoId != null) {
            updateMyVideoInfo(this.form).then((response) => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addMyVideoInfo(this.form).then((response) => {
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
      const videoIds = row.videoId || this.ids;
      this.$modal
        .confirm('是否确认删除我的课程视频编号为"' + videoIds + '"的数据项？')
        .then(function () {
          return delMyVideoInfo(videoIds);
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
        "video/myVideoInfo/export",
        {
          ...this.queryParams,
        },
        `myVideoInfo_${new Date().getTime()}.xlsx`
      );
    },

    // 视频封面上传成功的方法
    photoHandleAvatarSuccess(res) {
      // res 就是上传的文件路径
      this.form.videoPhoto = res; // 将封面路径赋予 表单的 videoPhoto 就可以实现图片回显
      this.$loading().close();
    },

    // 视频封面上传前的方法
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
      // return new Promise((resolve, reject) => {
      //   this.$nextTick(() => {
      //     // this.currentFileType = file.name
      //     resolve()
      //   })
      // })
    },

    // 超出文件数量上限的钩子
    videoHandleExceed(files, fileList) {
      this.$message.warning(
        `当前限制选择 1 个文件，本次选择了 ${files.length} 个文件，共选择了 ${
          files.length + fileList.length
        } 个文件`
      );
    },
    // 点击删除文件时的钩子
    videoHandleRemove(file) {
      console.log(file);
    },
    // 点击文件时的钩子
    videoHandlePreview(file) {
      console.log(file);
    },
    // 移除文件时的钩子
    videoBeforeRemove(file) {
      // return this.$confirm(`确定移除 ${file.name}？`);
      return true;
    },

    // 上传视频文件成功时的钩子
    uploadViewSucess(res) {
      // res 就是上传的文件路径
      this.form.videoPath = res; // 将封面路径赋予 表单的 videoPhoto 就可以实现图片回显
      this.$loading().close();
    },

    // 视频文件上传前的方法
    videoBeforeUpload(file) {
      this.$loading();
      const isMP4 = file.type === "video/mp4";
      if (!isMP4) {
        this.$message.error("上传文件只能是 JPG 格式!");
        this.$loading().close();
        return isMP4;
      }

      const isLt10G = file.size / 1024 / 1024 < 1024;
      if (!isLt10G) {
        this.$message.error("上传文件大小不能超过 1GB!");
        this.$loading().close();
        return isLt10G;
      }
    },

    isDisable(status) {
      return isDisableButton(status);
    },

    // 新开一个页面跳转（查看上传的视频用的）
    reviewVideo(url) {
      window.open(url);
    },

    // 下架按钮
    tankeOffForm() {

      this.$refs["form"].validate((valid) => {
        // 将上传者id填入
        this.form.videoUploadUser = this.$store.state.user.id;

        // 将表单状态都改为下架
        this.form.status = 3;

        if (valid) {
          if (this.form.videoId != null) {
            updateMyVideoInfo(this.form).then((response) => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addMyVideoInfo(this.form).then((response) => {
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