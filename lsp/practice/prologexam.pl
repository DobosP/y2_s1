%reverselist(i,o).
reverselist([],_,[]).
reverselist([X], R, [RL|R]) :- is_list(X),reverselist(X,[],RL).
reverselist([X], R, [X|R]) :- not(is_list(X)).
reverselist([H|T],R, RT) :- is_list(H),
    reverselist(H,[],RH), reverselist(T,[RH|R],RT), !.
reverselist([H|T], R ,RT) :-
    reverselist(T,[H|R], RT).


%finde(i,e,o)
finde([],_,false):-!.
finde([H|_], E, true) :- H =:= E,!.
finde([_|T], E, R) :- finde(T, E, R).

%transformset(i,o)
transformset([],[]) :- !.
transformset([H|T], R) :- finde(T, H, E), E,
              transformset(T, R), !.
transformset([H|T], [H|R]) :- transformset(T, R).


%removen(i,i,i,o).
removenn([],_,_,[]).
removenn([_|T],NR,N,R) :- NR =:= N, NNR is NR + 1, NN is 2 * N,
    removenn(T,NNR,NN,R),!.
removenn([H|T],NR,N,[H|R]) :- NNR is NR + 1,
      removenn(T,NNR,N,R).


deleteel([X|T],X,T).
deleteel([H|T],X,[H|R]) :- deleteel(T,X,R).

permut([],[]).
permut(L,[X|R]) :- deleteel(L,X,Rest), permut(Rest,R).


combinari(_,0,[]):-!.
combinari([H|T],K,[H|R]) :- Ks is K - 1, combinari(T,Ks,R).
combinari([_|T],K,R) :- combinari(T,K,R).

arr([],_,[]):-!.
arr(L,K,R) :- combinari(L,K,RC), permut(RC,R).


submultim([],[]):-!.
submultim([H|T],[H|R]) :- submultim(T,R).
submultim([H|T],R) :- submultim(T,R).



%listisarith(L,D,K,R).
listisarith([X],_,K, K):-!.
listisarith([H,HS|T],D,K,K) :- Dif is HS - H, D =\= Dif,!.
listisarith([H,HS|T],D,K,R) :- Ks is K + 1, listisarith([HS|T],D,Ks,R).

diff([],0):-!.
diff([X],0):-!.
diff([H,Hs|T],Diff):- Diff is Hs - H.


submultimkaritm(L,K,RS) :- submultim(L,RS),
      diff(RS,D), listisarith(RS,D,1,KD), KD =:= K.


listsums([],0):-!.
listsums([H|T],S) :- listsums(T,SR), S is SR + H.

combinaris(L,K,S,R) :- combinari(L,K,R), listsums(R,Ss), Ss =:= S.
