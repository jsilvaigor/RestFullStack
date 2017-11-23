<template>
  <div class="hello">
    <h2>Lista de Notícias</h2>
      <button v-on:click="createObject">Novo</button>
      <table>
        <thead>
          <tr>
            <th>Título</th>
            <th>Ações</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="(item, index) in items" :key="index">
            <td>{{item.title}}</td>
            <td>
              <button v-on:click="editObject(item)">Editar</button>
              <button v-on:click="removeObject(item)">Remover</button>
            </td>
          </tr>
        </tbody>
      </table>
  </div>
</template>

<script>
import axios from "axios";
export default {
  name: "HelloWorld",
  data: function() {
    return { items: [] };
  },
  created: function() {
    this.fechNews();
  },
  methods: {
    fechNews: function() {
      axios
        .get(`http://localhost:3000/api/v1/news`)
        .then(response => {
          // JSON responses are automatically parsed.
          this.items = response.data;
        })
        .catch(e => {
          this.errors.push(e);
        });
    },

    createObject: function() {
      this.$router.push("/Create");
    },
    editObject: function(item) {
      this.$router.push({ name: "Edit", params: { id: item._id}})
    },
    removeObject: function(item) {
      axios
        .delete(`http://localhost:3000/api/v1/news/` + item._id)
        .then(response => {
          this.$router.push("/");
        });
    }
  }
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
h1,
h2 {
  font-weight: normal;
}
ul {
  list-style-type: none;
  padding: 0;
}
li {
  display: inline-block;
  margin: 0 10px;
}
a {
  color: #42b983;
}
</style>
