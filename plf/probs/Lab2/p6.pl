%6.
%a. Write a predicate to test if a list is a set.
%b. Write a predicate to remove the first three occurrences of 
%an element in a list. If the element occurs less than three times, 
%all occurrences will be removed.

%a

notin(A,[]).
notin(A,[B|C]) :- 
	A\=B, 
	notin(A,C).

isSet([]).
isSet([_]).
isSet([A|B]) :- 
	notin(A,B), 
	isSet(B).

%b

count([],_,0).

count([H|T],E,I):-
	H=E,
	count(T,E,I2),
	I is I2+1.

count([H|T],E,I):-
	H\=E,
	count(T,E,I).



elim([],[],[]).
elim([H|T],Lc,Lr):-
	count(Lc,H,N),
	N=1,
	elim(T,Lc,Lr).
elim([H|T],Lc,Lr):-
	count(Lc,H,N),
	N\=1,
	elim(T,Lc,Lr).


deleteall([],A,[]).
deleteall([H|T],A,Result) :- H=A, deleteall(T,A,Result).
deleteall([H|T],A,[H|Result]) :- deleteall(T,A,Result).

delete_one(_, [], []).
delete_one(Term, [Term|Tail], Tail).
delete_one(Term, [Head|Tail], [Head|Result]) :-
  delete_one(Term, Tail, Result).
  

rez(L,E,Lf):-
	count(L,E,N),
	N=<3,
	%deleteall(L,E,Lf).
	deleteall(L,E,Lf).

rez(L,E,Lf):-
	count(L,E,N),
	N>3,
	delete_one(E,L,Lf).


  
%eliminate(el,list,list).
%eliminare(el,integer,list,list).
  

  
  
eliminate(E,L1,L2):-
	eliminare(E,3,L1,L2).
  
eliminare(_,_,[],[]). 
eliminare(E,Ap,[E|T],L):-
	Ap=0,
	!.
eliminare(E,Ap,[E|T],L):-
	Ap>0,
	Ap2=Ap-1,
    eliminare(E,Ap2,T,L).


  eliminare(E,Ap,[H|T],[H|L]):-
  	eliminare(E,Ap,T,L).                                                                  




	