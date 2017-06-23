%6.
%a. Determine the product of a number represented as digits in a list to a given digit.
%Eg.: [1 9 3 5 9 9] * 2 => [3 8 7 1 9 8]



prod([],0,N,[]):-!.
prod([],Cf,N,[Cf]):-!.
prod([H|T],Cf,N,[Hr|Tr]) :- Hr is ( N * H+ Cf) mod 10 , Cf2 is (N* H + Cf) div 10 , prod(T,Cf2,N,Tr). 


reverse([],R,R):-!.
reverse([H|T],Tr,R) :- reverse(T,[H|Tr],R).

prodMain(L,N,R):- reverse(L,LRev) , prod(LRev,0,N,RRev), reverse(RRev,R).