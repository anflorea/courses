require 'socket'

server = TCPServer.open(6666)

threads = []

10.times do
	client = server.accept

	threads << Thread.new do
		sleep(5)
		message = client.readline

		puts message

		client.puts(Time.now.ctime)
		client.close
	end
end

threads.each do |thr|
	thr.join
end
