from socket import *
s = socket(AF_INET, SOCK_DGRAM)
message = input('Text to send:')
s.sendto(str.encode(message), ("172.30.113.232", 10001))
print (s.recvfrom(1024))
