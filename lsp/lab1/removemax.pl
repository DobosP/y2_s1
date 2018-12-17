
%removeel(X: int , L: List, R: List).
%flowmodel(i,i,o).
removeel(_ , [], []).
removeel(X, [H|T], [H|R]) :- X =\= H, removeel(X, T, R).
removeel(X, [H|T], R) :- X =:= H, removeel(X, T, R).


%maxOfTwo(X: int , Y: int, R: int).
%flowmodel(i,i,o).
maxOfTwo(X, Y, X) :-
    X >= Y.
maxOfTwo(X, Y, Y) :-
    X < Y.


%maxL(L: List, X: int).
%flowmodel(i,o).
maxL([X], X).
maxL([H | T], X) :-
    maxL(T, I),
        maxOfTwo(H, I, X).

%removemax(L: List, R: List).
%flowmodel(i,o).
removemax(L,R) :- maxL(L,M), removeel(M, L, R).
removemax([],[]).
