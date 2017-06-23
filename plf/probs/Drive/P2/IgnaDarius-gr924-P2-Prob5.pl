%sublist(l-initial list,lr-replaced list,r-resulted list)
%flow model: (i,i,o)
%sublist(l,lr,r)
%      r is empty,   l is empty
%      l1 U sublist(l2..ln,lr,r), l1 is not a list
%      replace(l1,l1.1,lr,s), s U sublist (l2..ln,lr,r);
sublist([],_,[]).
sublist([H|T],L,[HR|TR]):-not(is_list(H)),
	HR is H,
	sublist(T,L,TR).
sublist([[H|T1]|T],L,[HR|TR]):-
	replace([H|T1],H,L,R1),
	HR=R1,
	sublist(T,L,TR).
% replace(l-list,e-element to be replaced,l1-replaced list,r-resulted
% list)
%flow model :(i,i,i,o)
%
%replace(l,e,lr,r)
%       r is empty, l is empty
%       r is l , lr is empty
%       replace(l2..ln,e,lr,r), l1 different of e
%       replace(l2..ln,e,lr,s),conca(lr,s,r), l1 is equal with e
replace([],_,_,[]).
replace(L,_,[],L).
replace(L,E,[E],L).
replace([H|T],E,L,[H|R]):-E=\=H,!,replace(T,E,L,R).
replace([E|T],E,L,R):-replace(T,E,L,S),conca(L,S,R).

%adauga_sf(e-elemet to be appende,l-initial list,r-resulted list)
%flow model:(i,i,o)
%
%adauga_sf(e,l,r)
%	      r = l1, if len(l)=1;
%	      adauga_sf(e,l2..ln,r) otherwise
adauga_sf(E,[],[E]).
adauga_sf(E,[H|T],[H|L]):-adauga_sf(E,T,L).

%conca(l1-list 1,l2-list 2,r-the resulted concatenated list)
%flow model : (i,i,o)
%conca(a,b,r)
%	   r is empty, a is empty and b is empty
%	   r is b, a is empty
%	   r is a, b is empty
%	   adauga_sf(b1,a,s),conca(s,b2..bn,r)  , a,b are not empty
conca([],[],[]).
conca([],L,L).
conca(L,[],L).
conca(L,[H|T],R):-adauga_sf(H,L,S),conca(S,T,R).
