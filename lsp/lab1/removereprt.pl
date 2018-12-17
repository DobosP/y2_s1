
%ocurenceel(E: int, L: List, A: int).
%flowmodel(i,i,o).
ocurenceel(_,[], 0).
ocurenceel(E,[H|T], A) :- E =:= H, ocurenceel(E, T, AR), A is AR + 1.
ocurenceel(E,[H|T], A) :- E =\= H, ocurenceel(E, T, A).

%removeel(X: int ,L: List, R: List).
%flowmodel(i,i,o).
removeel(_ , [], []).
removeel(X, [H|T], [H|R]) :- X =\= H, removeel(X, T, R).
removeel(X, [H|T], R) :- X =:= H, removeel(X, T, R).

%removerep(L: List, R: List).
%flowmodel(i,o).
removerep([],[]).
removerep([H|T], R) :-ocurenceel(H, [H|T], A), A > 1, removeel(H, T, RE), removerep(RE, R).
removerep([H|T], [H|R]) :-ocurenceel(H, [H|T], A), A =:= 1, removerep(T, R).
