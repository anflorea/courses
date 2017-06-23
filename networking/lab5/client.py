import socket
import sys
import time

sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)

server_address = ('localhost', 6666)

print >>sys.stderr, 'connecting to %s port %s' % server_address
sock.connect(server_address)

sock.send("Message\n")

data = sock.recv(10)
print 'received "%s"' % data
