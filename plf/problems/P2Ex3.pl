%3.
%a. Merge two sorted lists with removing the doubles

member([El|T],El) :-!.
member([H|T],El) :- member(T,El).


merge(A,[],A):-!.
merge([],B,B):-!.


merge([H1|T1],[H2|T2],[H1|Tr]) :- H1<H2 ,not(member(T1,H1)), merge(T1,[H2|T2],Tr),!.
merge([H1|T1],[H2|T2],Tr) :- H1<H2 , merge(T1,[H2|T2],Tr),!.

merge([H1|T1],[H2|T2],[H2|Tr]) :- H1>H2 ,not(member(T2,H2)), merge([H1|T1],T2,Tr),!.
merge([H1|T1],[H2|T2],Tr) :- H1>H2 , merge([H1|T1],T2,Tr),!.


merge([H1|T1],[H2|T2],[H1|Tr]) :- H1=H2 , not(member(T2,H1)),not(member(T1,H2)),  merge(T1,T2,Tr),!.
merge([H1|T1],[H2|T2],Tr) :- H1=H2 , merge(T1,T2,Tr).
 

