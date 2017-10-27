const mongoose = require('mongoose');
const bcrypt = require('bcrypt');

const SALT_WORK_FACTOR = 10;

const UserSchema = new mongoose.Schema({
    email: {
        type: String,
        unique: true
    },
    password: {
        type: String,
        unique: true
    }
}, {timestamps: true});

UserSchema.pre('save', function (next) {

    if (!this.isModified('password')) {
        return next();
    }

    const salt = bcrypt.genSaltSync(SALT_WORK_FACTOR);
    this.password = bcrypt.hashSync(this.password, salt);

    next();
});

UserSchema.methods.comparePassword = function (rcvPass) {

    const user = this;

    return new Promise(function (resolve, reject) {
        bcrypt.compare(rcvPass, user.password, (err, isMatch) => {
            if (err) {
                reject(err)
            } else {
                resolve(isMatch)
            }
        })
    })
};

const user = mongoose.model('user', UserSchema);

module.exports = user;