% 13. The list a1, ..., an is given and it consists of distinct integers. 
% Write a predicate to determine all subsets with aspect of "mountain" 
% (a set has a "mountain" aspect if the elements increase to a certain point 
% and then decrease).

%                  |0, n=0
%candidate(l1..ln)=
%				   |l1, otherwise	

% candidate(L:list, E: element)
% flow model :(i,o), (i,i)
% L - initial list
% E - element from list

candidate([H|_],H).

candidate([_|T],E):-
	candidate(T,E).

%					   |c1..cn, D=1
%                      |mountain(L,E U c1..cn,1), E = candidate(L), E not in c1..cn,
%mountain(L,c1..cn,D)= |						  E < c1 and D = 1 
%					   |mountain(L,E U c1..cn,0), E = candidate(L), E not in c1..cn,
%					   |                          E > c1 and D = 0
%					   |mountain(L,E U c1..cn,1), E = candidate(L), E not in c1..cn,
%					   |						  E < c1 and D = 0


% mountain(L:list, C:list, Direction: integer, R: list)
% flow model (i.i,i,o)
% L - initial list from which we take the elements
% C - candidate solution, in this list we construct element with element 
% the solution
% Direction - 1, for the increasing part	
%           - 0, for the decreasing part
% R - result


mountain(_,Cand,1,Cand).


mountain(L,[H|Cand],1,R):-
	candidate(L,E),
	\+(candidate([H|Cand],E)),
	E < H,!,
	mountain(L,[E,H|Cand],1,R).

mountain(L,[H|Cand],0,R):-
	candidate(L,E),
	\+(candidate([H|Cand], E)),
	E > H,
	mountain(L,[E,H|Cand],0,R).

mountain(L,[H|Cand],0,R):-
	candidate(L,E),
	\+(candidate([H|Cand],E)),
	E < H,
	mountain(L,[E,H|Cand],1,R).

mountain(L,[H1,H2],2,R):-
	H1 > H2,
	mountain(L,[H1,H2],0,R).


% find(L: list, Rez: list)
% flow model (i,o) (i,i)
% L - initial list
% Rez - a solution

% find(L) = mountain(L,[E1,E2],2), if E1 = candidate(L), 
%								E2 = candidate(L), E1 < E2


find(L, Rez):-
	candidate(L,E1),
	candidate(L,E2),
	E1 > E2,
	mountain(L,[E1,E2],2,Rez).

% main(l1..ln) = U find(l1l2..ln)

main(L,Rez):-
	findall(R,find(L,R),Rez).





