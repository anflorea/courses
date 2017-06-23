require 'socket'

server = TCPServer.new 6666

loop do
	client = server.accept
	message = client.gets
	puts message
	client.puts("Hello!")
	client.close()
end
