%concat(A: List,B: List, R: L List).
%flowmodel(i, i, o).
concat([],[],[]).
concat([],[H|T],[H|R]) :- concat([],T,R).
concat([H|T],B,[H|R]) :- concat(T,B,R).

%substitute(A: List, E: int, B: List, R: List).
%flowmodel(i,i,i,o).
substitute([],_,_,[]).
substitute([H|T],E,B,R) :- H =:= E,
    substitute(T,E,B,RR), concat(B,RR,R).
substitute([H|T],E,B,R) :- H =\= E,
    substitute(T,E,B,RR), R = [H|RR].

%getfirst(L: List, E: int).
%flowmodel(i,o).
getfirst([H|T], H).

%bsubstitute(A: List, B: List, R: List).
%flowmodel(i,i,o).
bsubstitute([],_,[]).
bsubstitute([H|T],B,R) :- is_list(H),
  getfirst(H,E), substitute(H,E,B,SR),
  bsubstitute(T,B,RR), R = [SR|RR].
bsubstitute([H|T],B,R) :- \+ is_list(H),
  bsubstitute(T,B,RR), R = [H|RR].
