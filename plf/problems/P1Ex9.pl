%9.
%a. Insert an element on the position n in a list.
%b. Define a predicate to determine the greatest common divisor of all numbers from a list.


%--------a----


insert(L,El,N,Poz,L) :- Poz is N+1,!.
insert([H|T],El,N,N,[El,H|Tr]) :- Poz is N+1 , insert(T,El,N,Poz,Tr),!.
insert([H|T],El,N,Poz,[H|Tr]) :- Poz < N , Poz2 is Poz + 1, insert(T,El,N,Poz2,Tr).


setInsert(L,El,N,Rez) :- insert(L,El,N,1,Rez).

%-------b-----


cmmdc(A,0,A):-!.
cmmdc(0,B,B):-!.
cmmdc(A,A,A):-!.
cmmdc(A,B,D) :- A<B ,B1 is B-A ,cmmdc(A,B1,D),!.
cmmdc(A,B,D) :- B<A ,A1 is A-B, cmmdc(A1,B,D). 


cmmdcList([],Cmmdc,Cmmdc):-!.
cmmdcList([H|T],Cmmdc,CmmdcR) :- cmmdc(H,Cmmdc,R) , cmmdcList(T,R,CmmdcR).

setCmmdcList([H|T],CmmdcR) :- cmmdcList(T,H,CmmdcR). 