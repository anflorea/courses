<?php
$s=socket_create(AF_INET,SOCK_STREAM,0);
socket_connect($s,"127.0.0.1", 6666);
$a=readline("Input nr:");
socket_send($s,$a,100,0);
socket_recv($s,$buf,100,0);
echo "Received back:",$buf,"\n";
?>
