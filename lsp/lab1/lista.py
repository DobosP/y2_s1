from copy import deepcopy

class Nod:
    def __init__(self, e):
        self.e = e
        self.urm = None

class Lista:
    def __init__(self):
        self.prim = None


'''
crearea unei liste din valori citite pana la 0
'''
def creareLista():
    lista = Lista()
    lista.prim = creareLista_rec()
    return lista

def creareLista_rec():
    x = int(input("x="))
    if x == 0:
        return None
    else:
        nod = Nod(x)
        nod.urm = creareLista_rec()
        return nod

'''
tiparirea elementelor unei liste
'''
def tipar(lista):
    tipar_rec(lista.prim)

def tipar_rec(nod):
    if nod != None:
        print (nod.e)
        tipar_rec(nod.urm)


'''
The list is empty
'''
def emptylist(list):
    return emptynode(list.prim)

def emptynode(nod):
    if not nod:
        return True
    return False

def gcmd(a, b):
    if a % b == 0:
        return b
    return gcmd(b, a % b)

def lcmList(list):
    if emptylist(list):
        return 1
    return get_head(list) * lcmList(
        get_tail(deepcopy(list)))/ gcmd(get_head(list),
        lcmList(get_tail(deepcopy(list))))


def substituite(val, newvalm, list):
    if emptylist(list):
        return Lista()
    if get_head(list) == val:
        next_list = substituite(val, newvalm, deepcopy(get_tail(list)))
        nod = Nod(newvalm)
        nod.urm = next_list.prim
        next_list.prim = nod
        return next_list
    if get_head(list) != val:
        nod = Nod(get_head(list))
        next_list = substituite(val, newvalm, deepcopy(get_tail(list)))
        nod.urm = next_list.prim
        next_list.prim = nod
        return next_list

def get_head(list):
    return list.prim.e

def get_tail(list):
    list.prim = list.prim.urm
    return list


'''
program pentru test
'''
def main():
    list = creareLista()
    tipar(list)
    print("lowest common multiple of the elements is:" ,lcmList(list))
    val = int(input("e="))
    new_val = int(input("e1="))
    new_list = substituite(val, new_val, list)
    print("The new list is:")
    tipar(new_list)

main()
