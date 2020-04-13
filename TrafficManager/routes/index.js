var express = require('express');
var router = express.Router();

/* GET home page. */
router.get('/', function(req, res, next) {
  res.render('index', { title: 'Express' });
});
router.post('/TrafficService/:timestamp', function(req,res,next){
  console.log(req.body);
})

module.exports = router;