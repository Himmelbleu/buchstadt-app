<script setup lang="ts">
const searchValue = ref("");
const menuItems = [
  {
    href: "/trolley",
    label: "购物车",
    isHaveDropMenu: false
  },
  {
    href: "/orders",
    label: "我的订单"
  },
  {
    href: "/collection",
    label: "我的收藏"
  }
];

const user = localStorage.getUserToken();

function logout() {
  localStorage.logoutUser();
}
</script>

<template>
  <div class="f-c-b px-10% my-1%">
    <router-link to="/">
      <div class="f-c-c">
        <img class="w-25 h-15 object-cover" src="/images/logo.png" />
        <div>
          <div>图书网</div>
          <div class="mt-2 text-0.8rem text-gray-5">buchstadt.com</div>
        </div>
      </div>
    </router-link>
    <div class="content">
      <ul>
        <li class="mr-10">
          <span style="display: flex">
            <el-input class="mr-5 w-80" v-model="searchValue" placeholder="搜索图书"></el-input>
            <el-button type="primary" plain @click="$router.push('/search/' + searchValue)">搜索</el-button>
          </span>
        </li>
        <template v-if="user">
          <li class="f-c-c mr-4" v-for="(item, index) in menuItems" :key="index">
            <router-link :to="item.href">
              {{ item.label }}
            </router-link>
          </li>
        </template>
        <li class="f-c-c" v-if="!user">
          <router-link to="/signin">
            <span class="span">登陆 \ 注册 </span>
          </router-link>
        </li>
        <el-dropdown v-else>
          <img class="w-10 h-10 rd-50% object-cover" :src="user.avatar" />
          <template #dropdown>
            <el-dropdown-menu>
              <router-link to="/space">
                <el-dropdown-item>个人中心</el-dropdown-item>
              </router-link>
              <el-dropdown-item>消息通知</el-dropdown-item>
              <el-dropdown-item>系统设置</el-dropdown-item>
              <el-dropdown-item>
                <el-popconfirm title="你是否要退出登录？" @confirm="logout">
                  <template #reference>
                    <span>退出登录</span>
                  </template>
                </el-popconfirm>
              </el-dropdown-item>
            </el-dropdown-menu>
          </template>
        </el-dropdown>
      </ul>
    </div>
  </div>
</template>

<style lang="scss" scoped>
.content {
  ul {
    --uno: f-c-c;
    color: #409eff;
    padding: 0;
    list-style: none;

    li {
      transition: 0.3s;

      a {
        color: #409eff;
      }

      .span {
        font-size: 17px;
      }
    }

    li:hover {
      cursor: pointer;
      transition: 0.3s;
      transform: translate(-1px, 0);
      font-weight: 600;
    }
  }
}
</style>
