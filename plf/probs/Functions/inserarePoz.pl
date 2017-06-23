%Sa se insereze un element pe o anumita pozitie 

insertion([],El,_,1,[El]):-
	!.

insertion([],El,_,_,[]).

insertion([H|T],El,Poz,Poz,[El,H|Lr]):-
	Poz1 is Poz + 1,
	insertion(T,El,Poz,Poz1,Lr).

insertion([H|T],El,Poz,I,[H|Tr]):-
	I \= Poz,
	Ir is I+1,
	insertion(T,El,Poz,Ir,Tr),!.