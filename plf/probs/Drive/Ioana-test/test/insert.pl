% Write a predicate to add a value v after 1-st, 2-nd,
%4-th, 8-th, … element in a list.
%
%insert(L,El,Poz,Pc,R)
%L - initial list
%El - the value we add
%Poz - position we have to insert on, initially = 1
%Pc - current pozition, initially = 1
%R - result list
%
insert([],_,_,_,[]).

insert([H|T],El,Poz,Pc,[H,El|RL]) :-
		Poz = Pc,
		Poz2 is Poz*2, Pc2 is Pc +1,
		insert(T,El,Poz2,Pc2,RL).


insert([H|T],El,Poz,Pc,[H|RL]) :-
		Poz \= Pc,
		Pc2 is Pc +1,
		insert(T,El,Poz,Pc2,RL).
