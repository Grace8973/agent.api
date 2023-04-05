# agent.api
project is mainly used to proxy the HTTP interface of OpenAI, and references and uses the smiley-http-proxy-servlet package.

## application.properties
``````
#port
server.port=8081

# Matching and replacing proxies and the previous section.
proxy.api.prefix=/proxy/*

# Target URL.
proxy.api.targetUrl=https://api.openai.com/
proxy.api.EnableLog=false
``````
## For example
if accessing http://127.0.0.1:8080/proxy/v1/chat/completions
will be intercepted and replaced with https://api.openai.com/v1/chat/completions

## nginx configuration example
``````
http {
include       mime.types;
default_type  application/octet-stream;
sendfile        on;
keepalive_timeout  65;

    gzip  on;
    proxy_headers_hash_bucket_size 1024;
    types_hash_bucket_size 1024;

    server {
        listen       80;
        listen       [::]:80;
        server_name  your.domain.url;
        #root         /usr/share/nginx/html;

        #charset koi8-r;

        #access_log  logs/host.access.log  main;

        location /{
           proxy_pass http://127.0.0.1:yourPort;
           proxy_read_timeout 120;
           proxy_set_header Host $host;
           proxy_set_header X-Forwarded-Proto $scheme;
           proxy_set_header X-Forwarded-Port $server_port;
           proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
           proxy_set_header Upgrade $http_upgrade;
           proxy_set_header Connection "upgrade";
           proxy_set_header Connection '';
           proxy_http_version 1.1;
           chunked_transfer_encoding off;
           proxy_buffering off;
           proxy_cache off;
           proxy_set_header X-Forwarded-For $remote_addr;
           proxy_set_header X-Forwarded-Proto $scheme;
        }

        #error_page  404              /404.html;

        # redirect server error pages to the static page /50x.html
        #
        error_page   500 502 503 504  /50x.html;
        location = /50x.html {
            root   html;
        }
    }
}
``````
## nginx Special attention
following items are to ensure smooth output of EventSource type responses when using the stream parameter request:
``````
proxy_headers_hash_bucket_size 1024;
types_hash_bucket_size 1024;
``````
``````
proxy_set_header Connection '';
proxy_http_version 1.1;
chunked_transfer_encoding off;
proxy_buffering off;
proxy_cache off;
``````