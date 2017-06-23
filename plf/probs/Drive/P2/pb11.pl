%11.
% a. Replace all occurrences of an element from a list with
%another element e.

%%push_back(L,E,R)
%L-list , E- element we append, R- result list
%flow model : (i,i,o)
push_back([],E,[E]).
push_back([H|T],E,[H|Tr]):-
	push_back(T,E,Tr).


%replace(L,E1,E2,C,R)
%L -initial list
%E1, element we search for
%E2- element we replace E1 with
%C- list we construct
%R- result list
%flow model :(i,i,i,i,o)

replace1([],_,_,C,R):-
	R=C.
replace1([H|T],E1,E2,C,R):-
	H=E1,
	push_back(C,E2,NewC),
	replace1(T,E1,E2,NewC,R).
replace1([H|T],E1,E2,C,R):-
	H\=E1,
	push_back(C,H,NewC),
	replace1(T,E1,E2,NewC,R).


replace(L,E1,E2,R):-
	replace1(L,E1,E2,[],Res),
	R=Res.
replace([],_,_,R):-
	R=[].

% b. For a heterogeneous list, formed from integer numbers and
% list of numbers, define a predicate to determine the maximum
% number of the list, and then to replace this value in
% sublists with the maximum value of sublist.
% Eg.: [1, [2, 5, 7], 4, 5, [1, 4], 3, [1, 3, 5, 8, 5, 4], 5, [5,%9, 1], 2] =>
%[1, [2, 7, 7], 4, 5, [1, 4], 3, [1, 3, 8, 8, 8, 4], 5, [9, 9, 1]%, 2]
%

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


%is list
is_list1([]).
is_list1([_|T]):-
	is_list1(T).


%replace_sub_max(L, Max,C, R)
%L- list
%Max- value we replace with the maximum from the sublist
%R- result list

replace_sub_max([],_,C,R):-
	C=R.

%cazul in care e sublista primul elem
replace_sub_max([H|T],Max,C,R):-
	is_list(H),
	max_list_main(H,Res), %determine max nr from sublist
	replace(H,Max,Res,HR),
	push_back(C,HR,CR),
	%replace all occ with max from sublist
	replace_sub_max(T,Max,CR,R).


%cazul in care e numar primul elem
replace_sub_max([H|T],Max,C,R):-
	push_back(C,H,CR),
	replace_sub_max(T,Max,CR,R).


replace_sub(L,R):-
	max_list_main(L,Max),
	replace_sub_max(L,Max,[],Res),
	R=Res.
replace_sub([],R):-
	R=[].










