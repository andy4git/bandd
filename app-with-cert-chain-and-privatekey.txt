# python:

import ssl, socket

context = ssl.SSLContext(ssl.PROTOCOL_TLS_SERVER)
context.load_cert_chain(certfile="fullchain.pem", keyfile="privkey.pem")

with socket.socket(socket.AF_INET, socket.SOCK_STREAM, 0) as sock:
    sock.bind(("0.0.0.0", 4433))
    sock.listen(5)
    with context.wrap_socket(sock, server_side=True) as ssock:
        conn, addr = ssock.accept()


# node JS
const https = require('https');
const fs = require('fs');

const options = {
  cert: fs.readFileSync('fullchain.pem'),
  key: fs.readFileSync('privkey.pem'),
};

https.createServer(options, (req, res) => {
  res.writeHead(200);
  res.end('hello world\n');
}).listen(4433);
