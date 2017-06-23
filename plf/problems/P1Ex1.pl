%1.
%a. Write a predicate to determine the lowest common multiple of a list formed from integer numbers.
%b. Write a predicate to add a value v after 1-st, 2-nd, 4-th, 8-th, … element in a list



%------a------

cmmdc(0,A,A).
cmmdc(A,0,A).
cmmdc(A,A,A):- !.
cmmdc(A,B,R) :- A<B ,B1 is B-A,cmmdc(A,B1,R).
cmmdc(A,B,R) :-  A>B ,A1 is A-B, cmmdc(A1,B,R).


cmmmc(A,B,Rf) :- Mult= A*B ,cmmdc(A,B,R), Rf is Mult div R .



cmmmcList([],Rez,Rez).
cmmmcList([H|T],B,R) :- cmmmc(H,B,Rez),cmmmcList(T,Rez,R).


setCmmmcList(L,R) :- cmmmcList(L,1,R).

%-------b---------


multiple(1).
multiple(El) :- 0=:=El mod 2 ,El1 is El/2, multiple(El1).


addValue([],Value,_,[]).
addValue([H|T],Value,I1,[H|Tr]):- not(multiple(I1)),I is I1+1, addValue(T,Value,I,Tr).
addValue([H|T],Value,I1,[H,Value|Tr]) :- multiple(I1) ,I is I1+1, addValue(T,Value,I,Tr),!.


setAddValue(L,Value,Rez) :- addValue(L,Value,1,Rez).
