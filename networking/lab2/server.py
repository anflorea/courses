from socket import *

print "Launching server..."
print "Waiting for connections"

awsmSocket = socket(AF_INET, SOCK_DGRAM)
awsmSocket.bind(("0.0.0.0", 7777))
while(1):
    buff, addr = awsmSocket.recvfrom(1000)
    print "Received message: " + buff + ", from: %s" % (addr, ) 
    awsmSocket.sendto(buff[::-1], addr)
