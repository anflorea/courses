%6. 
%a. Write a predicate to test if a list is a set.
%b. Write a predicate to remove the first three occurrences of an element in a list. If the element occurs less
%than three times, all occurrences will be removed.


%---------a---------

noOcc([],El,0).
noOcc([H|T],El,No) :- H=El,  noOcc(T,El,No1), No is No1 +1 ,!.
noOcc([H|T],El,No) :- noOcc(T,El,No).


verifSet([],L):-!.
verifSet([H|T],L) :- noOcc(L,H,No), No = 1, verifSet(T,L).


setVerifSet(L) :- verifSet(L,L). 

%------------b--------


elim([],El,_,[]):-!.
elim([H|T],El,3,[H|Tr]):-elim(T,El,3,Tr),!.
elim([H|T],El,I,[H|Tr]):- H \=El, elim(T,El,I,Tr),!.
elim([H|T],El,I,Tr):- H= El, I<3 , I2  is I+1 , elim(T,El,I2,Tr).


setElim(L,El,Rez) :- elim(L,El,0,Rez).