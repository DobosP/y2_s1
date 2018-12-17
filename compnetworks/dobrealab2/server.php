<?php
$s=socket_create(AF_INET,SOCK_STREAM,0);
socket_bind($s,"127.0.0.1",9999);
socket_listen($s);
while(TRUE) {
$cs=socket_accept($s);
$pid = pcntl_fork();
if ($pid == -1) {
die('could not fork');
} else if ($pid) {
pcntl_wait($status);
} else {
socket_recv($cs,$path,30,0);
$size=filesize($path);
if ($size!=FALSE)
        socket_send($cs,$size,10,0);
else
        socket_send($cs,"-1",10,0);
}
}
?>
