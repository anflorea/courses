%13.
%a. Given a linear numerical list write a predicate to remove all sequences of consecutive values.
%Eg.: remove([1, 2, 4, 6, 7, 8, 10], L) will produce L=[4, 10].


remove([H],0,[H]):-!.
remove([H],1,[]):-!.
remove([H1,H2|T],Cf,Tr) :-H2 - H1 =:= 1,  remove([H2|T],1,Tr),!.
remove([H1,H2|T],1,Tr) :- not(H2 - H1 =:= 1),  remove([H2|T],0,Tr),!.
remove([H1,H2|T],0,[H1|Tr]) :- not(H2 - H1 =:= 1),  remove([H2|T],0,Tr).

setRemove(L,Rez) :- remove(L,0,Rez).