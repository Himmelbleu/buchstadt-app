<script setup lang="ts">
import { submitForm } from "@common/elemplus/el-form-validation";
import { RouterPaths } from "@subapp-admin/constants/router-path";
import { queryAllByPage, updateOne, deleteOne } from "@subapp-admin/apis/api-buch";
import { buchFormRules, buchTypeOps, buchPrimeOps, dateShortcuts, disabledDate } from "@subapp-admin/common/el-form";

const pageSize = ref(5);
const currPage = ref(1);
const pageTotal = ref(100);

const formEl = ref();
const data = shallowRef();

async function fetchData() {
  const pageRes = await queryAllByPage({
    pageSize: pageSize.value,
    currPage: currPage.value
  });
  pageTotal.value = pageRes.total;
  data.value = pageRes.list;
}

await fetchData();

async function deleteBuch(row: BuchPoJo, index: number) {
  await deleteOne(row.id);
  data.value.splice(index, 1);
}

async function handleCurrentPageChange() {
  await fetchData();
}
</script>

<template>
  <div>
    <div class="f-c-e mb-10">
      <el-pagination
        background
        layout="prev, pager, next"
        @current-change="handleCurrentPageChange"
        :total="pageTotal"
        v-model:page-size="pageSize"
        v-model:current-page="currPage" />
    </div>
    <el-table border :data="data" stripe style="width: 100%">
      <el-table-column type="expand" width="100" fixed label="操作" v-slot="{ row }">
        <div class="px-10 my-5">
          <el-form ref="formEl" :model="row" :rules="buchFormRules" label-position="left" label-width="100px">
            <FormTitle title="主表数据" sub-title="书籍的主要内容"></FormTitle>
            <el-form-item label="书名" prop="name">
              <el-input v-model="row.name" clearable placeholder="请输入书籍名称" />
            </el-form-item>
            <el-form-item label="出版日期" prop="postDate">
              <el-date-picker
                value-format="YYYY-MM-DD"
                v-model="row.postDate"
                type="date"
                placeholder="选择出版日期"
                :disabled-date="disabledDate"
                :shortcuts="dateShortcuts" />
            </el-form-item>
            <el-form-item label="单价" prop="price">
              <el-input type="number" clearable placeholder="请输入书籍单价" v-model="row.price" />
            </el-form-item>
            <el-form-item label="折扣" prop="discount">
              <el-input type="number" clearable placeholder="请输入书籍折扣" v-model="row.discount" />
            </el-form-item>
            <el-form-item label="简介" prop="profile">
              <el-input type="textarea" v-model="row.profile" autosize placeholder="请输入书籍简介" />
            </el-form-item>
            <el-form-item label="类型" prop="type">
              <el-select v-model="row.type" placeholder="选择书籍类型">
                <el-option v-for="item in buchTypeOps" :key="item.value" :label="item.label" :value="item.value" />
              </el-select>
            </el-form-item>
            <el-form-item label="首推" prop="isPrime">
              <el-select v-model="row.isPrime" placeholder="是否首推到首页">
                <el-option v-for="item in buchPrimeOps" :key="item.value" :label="item.label" :value="item.value" />
              </el-select>
            </el-form-item>
            <el-form-item label="封面" prop="cover">
              <el-input v-model="row.cover" clearable placeholder="仅支持网络图片" />
            </el-form-item>
          </el-form>
          <div f-c-c mt-5>
            <el-button type="primary" @click="submitForm(formEl, async () => await updateOne(row))">保存表单</el-button>
          </div>
        </div>
      </el-table-column>
      <el-table-column fixed prop="name" label="书名" show-overflow-tooltip width="200" />
      <el-table-column prop="postDate" sortable show-overflow-tooltip label="出版日期" />
      <el-table-column prop="price" sortable label="单价" />
      <el-table-column prop="discount" sortable label="折扣" />
      <!-- <el-table-column prop="profile" label="简介" show-overflow-tooltip width="200" /> -->
      <el-table-column prop="cover" label="封面" show-overflow-tooltip width="200" v-slot="{ row }">
        <img class="w-15 h-20 object-cover" :src="row.cover" />
      </el-table-column>
      <el-table-column prop="type" label="类型" />
      <el-table-column prop="isPrime" label="首推" sortable v-slot="{ row }">
        <span v-if="row.isPrime">是</span>
        <span v-else-if="!row.isPrime">否</span>
      </el-table-column>
      <el-table-column label="更多" width="150" v-slot="scope">
        <el-button size="small" type="success" plain @click="$router.push(RouterPaths.buchs.update + scope.row.id)">详细</el-button>
        <el-popconfirm title="你确定要删除该书籍？" @confirm="deleteBuch(scope.row, scope.$index)">
          <template #reference>
            <el-button size="small" type="danger" plain>删除</el-button>
          </template>
        </el-popconfirm>
      </el-table-column>
    </el-table>
  </div>
</template>

<style scoped lang="scss"></style>
