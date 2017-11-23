<template>
  <div class="hello">

    <h2>Editar Notícia</h2>
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
  data: function(){
  return {
    title: "",
    content: "",
    error: "",
    id : ""
  }},
  created : function() {
        this.id = this.$route.params.id
        axios
        .get(`http://localhost:3000/api/v1/news/` + this.id)
        .then(response => {
          // JSON responses are automatically parsed.
          console.log(response)
          this.title = response.data.title
          this.content = response.data.content
        })
        .catch(e => {
          this.errors.push(e);
        });
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
        .put('http://localhost:3000/api/v1/news/' + this.id, {title: this.title, content: this.content})
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
