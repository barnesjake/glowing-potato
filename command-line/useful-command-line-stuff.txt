## command line stuff


print base64 encoded string
`echo -n "password" | base64`

print base64 decoded
`echo cGFzc3dvcmQ= | base64 -D`

Parse json response on command line: | jq .

`curl ..... | jq .`
