<?php
$s=socket_create(AF_INET,SOCK_STREAM,0);
socket_connect($s,"127.0.0.1",9999);
$path=readline("Path: ");
socket_send($s,$path,30,0);
socket_recv($s,$size,10,0);
$pos = strpos($path, "/");
$file=substr($path, $pos);
while (($pos = strpos($file, "/")) !== FALSE) {
    $file = substr($file, $pos+1);
    $pos = strpos($file, "/");
}
$myfile = fopen("$file-copy", "w");
fwrite($myfile,$size);
?>
