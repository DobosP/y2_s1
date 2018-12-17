
%multipliList(L-List, N-int,C-int, R-List).
%multipliList(i,i,o,o).
multipli([],_,0,[]) :- !.
multipli([H|T], N, C, R) :- multipli(T, N, C2, R2), Nr is (H * N) + C2,
  C is div(Nr,10), NH is mod(Nr,10), R = [NH|R2].


%mull(L,N,R) :-  multipli(L,N,C,R2),(C =:= 0 -> R = R2); R = [C|R2]).
%mull(L,N,R) :- multipli(L,N,C,R2), R = [C|R].

mull(L,N,R) :- multipli(L,N,C,R), C=:= 0,!.
mull(L,N,[C|R]) :- multipli(L,N,C,R).


sortlist([],[]).
sortlist(L,[M|R2]) :- findandremovemax(L,M,L2), sortlist(L2,R2).

findandremovemax(X,X,[]) :- !.
findandremovemax([H|T],M,R) :- findandremovemax(T,M,R2), H < M,!, R = [M|R2],
      M is H.
findandremovemax([H|T],M,R) :- findandremovemax(T,M,R2), R = [H|R2].
