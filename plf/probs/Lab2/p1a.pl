%1.
%a.Write a predicate to determine the lowest common multiple of a list formed from integer numbers.

cmmdc(A,A,D):-
    %A=:=B,
    D=A,
    !.

cmmdc(A,B,D):-
  	A>B,
  	A1 is A-B,
  	cmmdc(A1,B,D).

cmmdc(A,B,D):-
    B>A,
  	B1 is B-A,
  	cmmdc(A,B1,D).
  
%Pt a descoperii cmmdc putem afisa una din valorile A si B
%Dar pt cmmmc vom folosi formula: a*b=cmmdc(a, b) * cmmmc[a, b]


cmmmc(A,B,M):-
  	P is A*B,
  	cmmdc(A,B,D),
  	M is div(P,D).
  
cmmmcL([],1).
cmmmcL([H|T],M):-
    cmmmcL(T,M1),
    cmmmc(H,M1,M).  







