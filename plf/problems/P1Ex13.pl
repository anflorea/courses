%13.
%a. Transform a list in a set, in the order of the last occurrences of elements. Eg.: [1,2,3,1,2] is transformed in
%[3,1,2].
%b. Define a predicate to determine the greatest common divisor of all numbers in a list.


%-------a-----


member([El|T],El) :-!.
member([H|T],El) :- member(T,El).

transform([],[]).
transform([H|T],Rez):- member(T,H), transform(T,Rez),!.
transform([H|T],[H|Tr]) :- transform(T,Tr).


%--------b--------


cmmdc(A,0,A):-!.
cmmdc(0,B,B):-!.
cmmdc(A,A,A):-!.
cmmdc(A,B,D) :- A<B ,B1 is B-A ,cmmdc(A,B1,D),!.
cmmdc(A,B,D) :- B<A ,A1 is A-B, cmmdc(A1,B,D). 


cmmdcList([],Cmmdc,Cmmdc):-!.
cmmdcList([H|T],Cmmdc,CmmdcR) :- cmmdc(H,Cmmdc,R) , cmmdcList(T,R,CmmdcR).

setCmmdcList([H|T],CmmdcR) :- cmmdcList(T,H,CmmdcR). 