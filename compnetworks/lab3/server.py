import socket
import threading
import subprocess
from time import sleep

_HOST = '127.0.0.1'  # Standard loopback interface address (localhost)
_PORT = 8080        # Port to listen on (non-privileged ports are > 1023)
_BUCKET_SIZE = 1024


class ThreadedServer(object):
    def __init__(self, host, port):
        self.__host = host
        self.__port = port
        self.__socket = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
        self.__socket.setsockopt(socket.SOL_SOCKET, socket.SO_REUSEADDR, 1)
        self.__socket.bind((self.__host, self.__port))


    def start(self):
        while True:
            data, cli_addr = self.__socket.recvfrom(_BUCKET_SIZE)
            threading.Thread(target = self.execute_command_job,args = (data, cli_addr)).start()


    def execute_command_job(self, data, cli_addr):
        data = data.decode("utf-8").split(',')
        x = int(data[0])
        y = int(data[1])
        direction = str(data[2]).strip()

        if direction == "N":
            x += 1
        if direction == "S":
            x -= 1
        if direction == "E":
            y += 1
        if direction == "V":
            y -= 1

        new_position = (str(x) + "," + str(y)).encode("utf-8")
        self.__socket.sendto(new_position, cli_addr)


server = ThreadedServer(_HOST, _PORT)
server.start()
