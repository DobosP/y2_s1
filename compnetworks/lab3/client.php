<?php

$HOST = '127.0.0.1';
$PORT = 8080;
$BUCKET_SIZE = 1024;


// $server_host = readline("Server: ");
// $server_port = intval(readline("Port: "));
$server_host = $HOST;
$server_port = $PORT;

$socket_client=socket_create(AF_INET,SOCK_DGRAM,0);

while(1)
{
    $local_file = readline("Path to file: ");
    $x = 0;
    $y = 0;
    if($file = fopen($local_file, "r")){
      while(!feof($file)) {
        $line = fgets($file);
        //Send the message to the server
        $data = "{$x},{$y},{$line}";
        if( ! socket_sendto($socket_client, $data , strlen($data) , 0 , $server_host , $server_port))
        {
           $errorcode = socket_last_error();
           $errormsg = socket_strerror($errorcode);

           die("Could not send data: [$errorcode] $errormsg \n");
         }

         //Now receive reply from server and print it
         if(socket_recv ( $socket_client , $reply , 2045 , MSG_WAITALL ) === FALSE)
         {
           $errorcode = socket_last_error();
           $errormsg = socket_strerror($errorcode);

           die("Could not receive data: [$errorcode] $errormsg \n");
         }
        else{
           echo "Reply : {$reply}\n";
           $data = explode(",", $reply);
           $x = intval($data[0]);
           $y = intval($data[1]);
           sleep(1);
        }
      }
    fclose($file);
    }
    break;
}
exit()
?>
