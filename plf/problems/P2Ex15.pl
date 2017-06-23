%15.
%a. Define a predicate to determine the predecessor of a number represented as digits in a list.
%Eg.: [1 9 3 6 0 0] => [1 9 3 5 9 9]

reverse([],R,R):-!.
reverse([H|T],Tr,R):- reverse(T,[H|Tr],R).


pred([1],1,[]):-!.
pred([],_,[]):-!.
pred([H|T],Cf,[Hr|Tr]) :- Hr is (10+ H - Cf) mod 10, Cf2 is ((10+ H - Cf) div 10 +1 ) mod 2 , pred(T,Cf2,Tr).




setPred(L,P) :- reverse(L,LR), pred(LR,1,PR) , reverse(PR,P).