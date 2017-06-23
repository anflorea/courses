%10.
%a. Define a predicate to test if a list of an integer elements has a "valley" aspect (a set has a "valley" aspect if
%elements decreases up to a certain point, and then increases.
%eg: 10 8 6 9 11 13 – has a “valley” aspect
%b. Calculate the alternate sum of list’s elements (l1 - l2 + l3 ...).

increasing([H1,H2]) :- H1 < H2 ,!.
increasing([H1,H2|T]) :- H1<H2 , increasing([H2|T]),!.




valley([H1,H2|T],Cf) :- H1 > H2 , valley([H2|T],1),!.
valley([H1,H2|T],1) :- H1<H2 , increasing([H1,H2|T]).




%--------b--------

alt([],_,S,S):-!.
alt([H|T],0,Sum,SumAdv) :- Sum2 is Sum + H, alt(T,1,Sum2,SumAdv),!.
alt([H|T],1,Sum,SumAdv) :- Sum2 is Sum - H, alt(T,0,Sum2,SumAdv).







