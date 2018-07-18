const NodeRSA = require('../src/NodeRSA');

var key = new NodeRSA({b: 1024}); // different option objects still yield same size
var publicPEM = key.exportKey("pkcs1-public-pem");

console.log(publicPEM)