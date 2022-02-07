<template>
  <el-card class="box-card">
    <el-col>
      <el-row>
        <header>
          <a :href="ship.link" style="text-decoration: none">{{ ship.name }}</a>
        </header>
      </el-row>
      <el-row>
        <body>
          <p
            :innerText="
              ship.year_built != null
                ? 'Built in ' + ship.year_built
                : 'Build year unknown'
            "
          ></p>
        </body>
      </el-row>
    </el-col>
    <el-col style="float: right">
      <el-button type="danger" @click="deleteShip">Delete</el-button>
    </el-col>
  </el-card>
</template>

<script>
import axios from "axios";
import { ElMessageBox, ElMessage } from "element-plus";

export default {
  name: "Ship",
  props: {
    ship: Object,
  },

  components: {
    ElMessageBox,
    ElMessage,
  },

  methods: {
    async deleteTest() {
      await axios
        .get("http://localhost:8080/ship/delete/" + this.ship.id)
        .then((resp) => {
          alert("Successful delete!");
          window.location.reload();
        })
        .catch((err) => {
          alert("Delete failed!");
        });
    },

    async deleteShip() {
      ElMessageBox.confirm(
        "Ship will be permanently deleted. Continue?",
        "Warning",
        {
          confirmButtonText: "OK",
          cancelButtonText: "Cancel",
          type: "warning",
        }
      )
        .then(() => {
          return axios.get("http://localhost:8080/ship/delete/" + this.ship.id);
        })
        .then((resp) => {
          window.location.reload();

          alert("Delete succeeded");
        })
        .catch(() => {
          ElMessage({
            type: "info",
            message: "Delete failed",
          });
        });
    },
  },
};
</script>

<style scoped>
.el-card {
  margin: 10px;
}
</style>
