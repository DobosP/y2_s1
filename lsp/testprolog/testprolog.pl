

%addwithcarry(L-list,N-int,C-int,R-list).
%addwithcarry(i,i,o,o).
addwithcarry([],N,N,[]).
addwithcarry([H|T],N,C,[NH|R]) :- addwithcarry(T,N,C2,R),
  Nr is H + C2, C is Nr div 10, NH is Nr mod 10.


%addnumbers(L-list,N-int,R-list).
%addnumbers(i,i,o).
addnumbers(L,N,R) :- addwithcarry(L,N,C,R), C =:= 0,!.
addnumbers(L,N,[C|R]) :- addwithcarry(L,N,C,R).
