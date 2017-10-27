const express = require('express');
const router = express.Router();
const User = require('../models/user');

/* POST login User */
router.post('/', function (req, res, next) {

    const {email, password} = req.body;

    User.findOne({email})
        .then((result) => {
            if (result) {
                result.comparePassword(password)
                    .then((isMatch) => {
                        if (isMatch) {
                            res.status(200).json({"logged": true})
                        } else {
                            res.status(403).json({"logged": false})
                        }
                    })

            } else {
                res.status(403).json({"logged": false})
            }
        })
        .catch((error) => res.status(500).json(error));

});

module.exports = router;
