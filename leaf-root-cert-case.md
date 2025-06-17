b. Combine the cert chain (in wrong order on purpose):

cat server.crt root-ca.crt > cert-chain-without-intermediate.pem

Convert to PKCS#12:

openssl pkcs12 -export \
  -in cert-chain-without-intermediate.pem \
  -inkey server.key \
  -out server-no-intermediate.p12 \
  -name mycert \
  -passout pass:changeit
Import into JKS (if needed):


keytool -importkeystore \
  -deststorepass changeit \
  -destkeypass changeit \
  -destkeystore server.jks \
  -srckeystore server-no-intermediate.p12 \
  -srcstoretype PKCS12 \
  -srcstorepass changeit \
  -alias mycert

2. Use in Java Code

KeyStore ks = KeyStore.getInstance("JKS");
try (FileInputStream fis = new FileInputStream("server.jks")) {
    ks.load(fis, "changeit".toCharArray());
}

KeyManagerFactory kmf = KeyManagerFactory.getInstance("SunX509");
kmf.init(ks, "changeit".toCharArray());

SSLContext sslContext = SSLContext.getInstance("TLS");
sslContext.init(kmf.getKeyManagers(), null, null);



// use sslContext to configure your server (e.g., Jetty, Netty, etc.)
Now this server will send:

Server cert

Root cert

No intermediate
