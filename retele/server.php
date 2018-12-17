<?php
$s=socket_create(AF_INET,SOCK_STREAM,0);
socket_bind($s,"172.30.119.58",9999);
socket_listen($s);
$cs=socket_accept($s);
socket_send($cs,"Hello, this is dobo",30,0);
?>
