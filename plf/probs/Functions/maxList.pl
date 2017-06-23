%max_list (L, Max, R)
%L- initial list
%Max- initial maximum value
%R- result, final max value
%flow model: (i,i,o)
max_list([],Max,R):-
	R is Max.
max_list([H|T],Max,R):-
	is_list(H),
	max_list(T,Max,R).
max_list([H|T],Max,R):-
	H@>Max,
	max_list(T,H,R).
max_list([H|T],Max,R):-
	H@=<Max,
	max_list(T,Max,R).

max_list_main([H|T],R):-
	max_list([H|T],H,Res),
	R is Res.
max_list_main([],R):-
	R is 0.