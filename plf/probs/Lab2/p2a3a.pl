                               	%****REMOVING ELEMENTS****%


%2.
%a. Write a predicate to remove all occurrences of a certain atom from a list.

%elimin(E-element,L-initial list,R-resulted list)
	
elimin(E,[],[]).

elimin(E,[E|T],L):-
  	!,
  	elimin(E,T,L).

elimin(E,[H|T],[H|L]):-
  	elimin(E,T,L).

                    
%3.
%a. Define a predicate to remove from a list all repetitive elements.
%Eg.: l=[1,2,1,4,1,3,4] => l=[2,3])

%noOfOcc(L-initial list,E-element,I-nr of occ)
%flow model:(i,i,i),(i,i,o)

%noOfOcc([],E,I):-!.
	
noOfOcc([],_,0).


noOfOcc([H|T],V,I):-
	V = H,
	noOfOcc(T,V,I1),
	I is I1 + 1.
	

noOfOcc([H|T],V,I):-
	V \= H,
	noOfOcc(T,V,I).

eliminRep([],[]).

eliminRep([H|T],Tr):-
	noOfOcc([H|T],H,Res),
	Res >= 2,
	eliminRep(T,Tr),!.

eliminRep([H|T],[H|Tr]):-
	noOfOcc([H|T],H,Res),
	Res < 2,
	eliminRep(T,Tr),!.




   



