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
