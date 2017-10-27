const express = require('express');
const router = express.Router();
const User = require('../models/user');

/* POST create User */
router.post('/', function (req, res, next) {

    const {email, password} = req.body;

    const user = new User({email, password});

    user.save()
        .then((result) => res.json(result))
        .catch((error) => res.status(500).json(error));

});

module.exports = router;
