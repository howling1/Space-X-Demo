<template>
  <el-menu
    :default-active="activeIndex"
    class="el-menu-demo"
    mode="horizontal"
    @select="handleSelect"
  >
    <el-menu-item index="1">Home</el-menu-item>
    <el-menu-item index="2">About</el-menu-item>
    
  </el-menu>
  <div class="line"></div>
</template>

<script lang="js">
import { defineComponent, ref } from 'vue'
import { useRoute } from 'vue-router'
import router from '../router'

export default defineComponent({
  setup() {
    const activeIndex = ref('1')
    const handleSelect = (key, keyPath) => {
        if(key == 1) {
            router.push('/')
        } else if (key == 2) {
            router.push('/about')
            }
    }
    
    return {
      activeIndex,
      handleSelect,

    }

  },
})
</script>

<style>

</style>
