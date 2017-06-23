import socket

s = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
s.bind(("0.0.0.0", 7777))
while(1):
    buff, addr = s.recvfrom(1000)
    print "Received message: " + buff + ", from: %s" % (addr, ) 
    s.sendto(buff[::-1], addr)
