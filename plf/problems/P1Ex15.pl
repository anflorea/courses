%15.
%a. Write a predicate to transform a list in a set, considering the first occurrence.
%Eg: [1,2,3,1,2] is transform in [1,2,3].
%b. Write a predicate to decompose a list in a list respecting the following: [list of even numbers list of odd
%numbers] and also return the number of even numbers and the numbers of odd numbers.

%-----a-----

member([El|T],El):-!.
member([H|T],El):- member(T,El).


addLast([],El,[El]):-!.
addLast([H|T],El,[H|Tr]) :- addLast(T,El,Tr). 


construct([],Rez,Rez):-!.
construct([H|T],Tr,Rez) :- not(member(Tr,H)), addLast(Tr,H,Rezz),  construct(T,Rezz,Rez),!.
construct([H|T],Tr,Rez) :- construct(T,Tr,Rez).


setConstruct(L,Rez) :- construct(L,[],Rez). 


%--------b--------




even([],[],No,No):-!.
even([H|T],[H|Tr],Act,No) :- H mod 2 =:=0, Act1 is Act+1, even(T,Tr,Act1,No),!.
even([H|T],Tr,Act,No) :- even(T,Tr,Act,No),!.


odd([],[],No,No):-!.
odd([H|T],[H|Tr],Act,No) :- H mod 2 =:=1, Act1 is Act+1, odd(T,Tr,Act1,No),!.
odd([H|T],Tr,Act,No) :- odd(T,Tr,Act,No),!.

concat(L,[],L):-!.
concat(L,[H|T],[H|Tr]) :- concat(L,T,Tr).


tadam(L,Rez,NoEven,NoOdd) :- even(L,Even,0,NoEven) , odd(L,Odd,0,NoOdd) , concat(Odd,Even,Rez).





