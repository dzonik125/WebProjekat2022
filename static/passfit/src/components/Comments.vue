<!-- eslint-disable vue/require-v-for-key -->
<template>
    <div id="comms">
        <link href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <div class="container">
            <div class="row">
                <div class="panel panel-default widget">
                    <div class="panel-heading">
                        <span class="glyphicon glyphicon-comment"></span>
                        <h3 class="panel-title">
                            Svi komentari</h3>
                    </div>
                    <div class="panel-body">
                        <ul class="list-group">
                            <li class="list-group-item" v-for="comment in commments">
                                <div class="row">
                                    <div class="col-xs-2 col-md-1">
                                    </div>
                                    <div class="col-xs-10 col-md-11">
                                        <div>
                                            <div class="mic-info">
                                                By: <a>{{comment.buyer}}</a> {{comment.created}}
                                            </div>
                                        </div>
                                        <div class="comment-text">
                                            {{comment.text}}
                                        </div>
                                        <div class="action">
                                            <button type="button" class="btn btn-success btn-xs" title="Approved" v-on:click="approveComment(comment.id)">
                                                <span class="glyphicon glyphicon-ok"></span>
                                            </button>
                                            <button type="button" class="btn btn-danger btn-xs" title="Delete" v-on:click="disapproveComment(comment.id)">
                                                <span class="glyphicon glyphicon-trash"></span>
                                            </button>
                                        </div>
                                    </div>
                                </div>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>

    </div>
</template>

<script>
import axios from 'axios'

export default {
  data () {
    return {
      commments: []
    }
  },
  mounted () {
    const axios = require('axios')
    axios.get('http://localhost:8082/rest/getAllComments/').then(response => {
      this.commments = response.data
      console.log(response.data)
    })
  },
  methods: {
    approveComment: function (id) {
      axios.post('http://localhost:8082/rest/approveComment/', {id: id}).then(response => {
        window.alert(response.data)
        this.$router.push({name: 'Index'})
      })
    },
    disapproveComment: function (id) {
      axios.post('http://localhost:8082/rest/disapproveComment/', {id: id}).then(response => {
        window.alert(response.data)
        this.$router.push({name: 'Index'})
      })
    }
  }
}
</script>

  <!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
body {
    padding-top: 30px;
}

.widget .panel-body {
    padding: 0px;
}

.widget .list-group {
    margin-bottom: 0;
}

.widget .panel-title {
    display: inline
}

.widget .label-info {
    float: right;
}

.widget li.list-group-item {
    border-radius: 0;
    border: 0;
    border-top: 1px solid #ddd;
}

.widget li.list-group-item:hover {
    background-color: rgba(86, 61, 124, .1);
}

.widget .mic-info {
    color: #666666;
    font-size: 11px;
}

.widget .action {
    margin-top: 5px;
}

.widget .comment-text {
    font-size: 12px;
}

.widget .btn-block {
    border-top-left-radius: 0px;
    border-top-right-radius: 0px;
}
</style>
