const express = require('express');
const cookieParser = require('cookie-parser');
const bodyParser = require('body-parser');

const users = require('./routes/users');
const login = require('./routes/login');
const news = require('./routes/news');

const app = express();

app.use(bodyParser.json());
app.use(bodyParser.urlencoded({extended: false}));
app.use(cookieParser())

app.use('/api/v1/users', users);
app.use('/api/v1/login', login);
app.use('/api/v1/news', news);

module.exports = app;
