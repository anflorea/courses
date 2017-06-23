%8.
%a. Determine the successor of a number represented as digits in a list.
%Eg.: [1 9 3 5 9 9] --> [1 9 3 6 0 0]

succ([],0,[]):-!.
succ([],1,[1]):-!.
succ(L,0,L) :-!.
succ([H|T],1,[Hr|Tr]) :- Hr is (H + 1) mod 10 , Cf2 is (H+1) div 10, succ(T,Cf2,Tr).


reverse([],R,R).
reverse([H|T],Tr,R) :- reverse(T,[H|T],R).

setSucc(L,S) :- reverse(L,LR) , succ(LR,1,SR), reverse(SR,S).