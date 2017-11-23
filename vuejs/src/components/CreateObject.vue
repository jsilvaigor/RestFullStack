<template>
  <div class="hello">

    <h2>Nova Notícia</h2>
    <input v-model="title">
    <input v-model="content">
    <button v-on:click="save">Salvar</button>
    <button v-on:click="cancel">Cancelar</button>
    <p>{{error}}</p>
  </div>
</template>

<script>
import axios from "axios";
export default {
  name: "CreateObject",
  data: {
    title: "",
    content: "",
    error: ""
  },
  methods: {
    cancel: function() {
      this.$router.push("/");
    },
    save: function() {
      var news = {
        title: this.title,
        content: this.content
      };
      console.log(news)
      axios
        .post('http://localhost:3000/api/v1/news', {title: this.title, content: this.content})
        .then(response => {
          this.cancel()
        })
        .catch(e => {
          this.errors.push(e);
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
