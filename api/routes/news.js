const express = require('express');
const router = express.Router();
const News = require('../models/news');

/* POST create News */
router.post('/', function (req, res, next) {

    const {title, content} = req.body;

    const news = new News({title, content});

    news.save()
        .then((result) => res.status(201).send())
        .catch((error) => res.status(500).json(error));

});

/* GET all News */
router.get('/', function (req, res, next) {

    News.find({})
        .then((result) => {
            if (result) {
                const mapped = result.map((item) => {
                    return {_id: item._id, title: item.title}
                })
                res.json(mapped)
            } else {
                res.status(500).send()
            }
        }).catch((error) => res.status(500).json(error))
});

/* GET specific News */
router.get('/:id', function (req, res, next) {

    News.findOne({"_id": req.params.id})
        .then((result) => {
            if (result) {
                res.json(result)
            } else {
                res.status(500).send()
            }
        }).catch((error) => res.status(500).json(error))
});

/* PUT update specific News */
router.put('/:id', function (req, res, next) {

    const {title, content} = req.body;

    News.findOne({"_id": req.params.id})
        .then((result) => {
            if (result) {

                if (title) {
                    result.title = title
                }

                if (content) {
                    result.content = content
                }

                result.save().then((saved) => res.status(200).send());

            } else {
                res.status(500).send()
            }
        }).catch((error) => res.status(500).json(error))
});

/* DELETE remove specific News */
router.delete('/:id', function (req, res, next) {

    News.findOneAndRemove({"_id": req.params.id})
        .then((result) => {
            if (result) {

                res.status(200).send()

            } else {
                res.status(500).send()
            }
        }).catch((error) => res.status(500).json(error))
});

module.exports = router;
