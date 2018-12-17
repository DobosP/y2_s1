%appendlast(L-List,X-int,R-List).
%appendlast(i,i,o).
appendlast([],X,[X]).
appendlast([H|T],X,[H|R]) :- appendlast(T,X,R).


%allsequence(N- int, M-int, C-int, L-List, R-List).
%allsequence(i,i,i,i,o).
allsequence(N,_,C,L,L) :- C > N,!.
allsequence(N,M,C,L,R) :- C2 is C + M, appendlast(L,C,L2), allsequence(N,M,C2,L2,R).
allsequence(N,M,C,L,R) :- C2 is C + 1, allsequence(N,M,C2,L,R).

%sequence(N-int,M-int,R-List).
%sequence(i,i,o).
sequences(N,M,R) :- allsequence(N,M,1,[],R).


absolute(X,Y) :- X > 0, Y is X; Y is X * (-1).


isokey([X],N,1).
isokey([X,Y|T],N,R) :- D is X - Y, absolute(D,AD), AD >= N, isokey([Y|T],N,R); R is 0.


getlist(N,X,[]):- X > N, !.
getlist(N,X,[X|R]):- X2 is X + 1, getlist(N,X2,R).


permut(List,[H|Perm]):-deleteel(H,List,Rest),permut(Rest,Perm).
permut([],[]).

deleteel(X,[X|T],T).
deleteel(X,[H|T],[H|NT]):-deleteel(X,T,NT).


newseq(N,M,R) :- getlist(N,1,L),permut(L,P), isokey(P,M,O), O =:= 1, R = P.
