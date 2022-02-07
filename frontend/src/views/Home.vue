<template>
  <el-main>
    <el-row> <h1>Welcome to the Space-X ship searcher</h1></el-row>
    <el-row
      ><p>
        We now have {{ amount }} Space-X ships. You can search for the ship you
        are interested in by name.
      </p>
      <!-- Form -->
      <el-button type="text" @click="dialogFormVisible = true"
        >Add a new ship</el-button
      >

      <el-dialog v-model="dialogFormVisible" title="Ship information">
        <el-form :model="form">
          <el-form-item label="ID" label-width="140px">
            <el-input v-model="form.id" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="Name" label-width="140px">
            <el-input v-model="form.name" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="Link" label-width="140px">
            <el-input v-model="form.link" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="Build year" label-width="140px">
            <el-input v-model="form.year_built" autocomplete="off"></el-input>
          </el-form-item>
        </el-form>
        <template #footer>
          <span class="dialog-footer">
            <el-button @click="dialogFormVisible = false">Cancel</el-button>
            <el-button type="primary" @click="addNewShip">Confirm</el-button>
          </span>
        </template>
      </el-dialog>
    </el-row>
    <el-row>
      <el-input
        placeholder="Type something"
        v-model="searchName"
        v-on:keyup.enter="searchShips"
      >
        <template #prefix>
          <el-icon class="el-input__icon" @click="searchShips"
            ><search
          /></el-icon>
        </template>
      </el-input>
    </el-row>
    <div :key="ship.id" v-for="ship in ships">
      <Ship :ship="ship" />
    </div>
  </el-main>
</template>

<script lang="js">
import Ship from '@/components/Ship.vue';
import { reactive, ref } from 'vue';
import { Search } from '@element-plus/icons-vue';
import { ElMessageBox, ElMessage } from "element-plus";
import axios from "axios";

export default{
  name: 'Home',
  components: {
      Search,
      Ship,
      ElMessage,
      ElMessageBox,
  },

  data() {
    const dialogFormVisible = ref(false)

    const form = reactive({
      id: '',
      name: '',
      link: '',
      year_built: '',
    });

    return {
      ships: [],
      amount: 0,
      searchName: '',
      dialogFormVisible,
      form,
    }
  },

  methods:{
    async searchShips() {
      let searchResult;
      if (this.searchName == "") {
        searchResult = await axios.get("https://api.spacexdata.com/v4/ships");
        this.ships = searchResult.data;
      } else{
        searchResult = await axios.get("http://localhost:8080/ship/fuzzy/"+this.searchName);
        this.ships = searchResult.data;
      }
    },

    async addNewShip() {
      await axios.post("http://localhost:8080/ship/add", this.form).then(resp =>
      {
        this.ships = resp.data;
        this.amount = resp.data.length;

        this.dialogFormVisible = false;

        ElMessage({
            type: "success",
            message: "Add succeeded",
          });
      }).catch(err => {
        console.log(err);
        ElMessage({
            type: "info",
            message: "Add failed",
          });
      });
    }
  },

  async created(){
    let localData = await axios.get("http://localhost:8080/ship");
    if (localData.data.length > 0) {
      this.ships = localData.data;
      this.amount = localData.data.length;
    } else{
      let remoteResource = await axios.get("https://api.spacexdata.com/v4/ships");
      this.ships = remoteResource.data;
      this.amount = remoteResource.data.length;

      let shipsToPost = [];

      for (let i = 0; i < remoteResource.data.length; i++) {
          shipsToPost.push(
          {
              id: remoteResource.data[i].id,
              name: remoteResource.data[i].name,
              link: remoteResource.data[i].link,
              year_built: remoteResource.data[i].year_built
          });
      }

      await axios.post("http://localhost:8080/ship/save", shipsToPost);
    }
  }
}
</script>

<style scoped lang="css">
p {
  margin-right: 10px;
}

.el-row {
  margin: 10px;
  justify-content: space-between;
}
.el-col {
  border-radius: 4px;
  display: flex;
  flex-wrap: wrap;
}

.dialog-footer button:first-child {
  margin-right: 10px;
}
</style>
