%b. Write a predicate to add a value v after 1-st, 2-nd, 4-th, 8-th, ... element in a list.


insert([],_,_,_,[]).

insert([H|T],El,Poz,Pc,[H,El|RL]) :-
		Poz = Pc,
		Poz2 is Poz*2, 
		Pc2 is Pc +1,
		insert(T,El,Poz2,Pc2,RL).


insert([H|T],El,Poz,Pc,[H|RL]) :-
		Poz \= Pc,
		Pc2 is Pc +1,
		insert(T,El,Poz,Pc2,RL).

