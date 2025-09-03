
podman pull hashicorp/consul:latest

podman run --name=consul-server -d -p 8500:8500 -p 8600:8600/udp hashicorp/consul consul agent -server -ui -node=server-1 -bootstrap-expect=1 -client=0.0.0.0 -data-dir=/consul/data
	
	