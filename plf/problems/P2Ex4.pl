%4.
%a. Write a predicate to determine the sum of two numbers written in list representation.
 

 
 reverse([],Rez,Rez):-!.
 reverse([H|T],Tr,Rez) :- reverse(T,[H|Tr],Rez).
 

 
 sum([],[],0,[]):-!.
 sum([],[],1,[1]):-!.
 
 sum(L,[],0,L):-!.
 sum([],L,0,L):-!.
 
 sum([H|T],[],1,[Hr|Tr]):- Hr is (H+1) mod 10 , Cf is (H+1) div 10 , sum(T,[],Cf,Tr),!.
 sum([],[H|T],1,[Hr|Tr]):- Hr is (H+1) mod 10 , Cf is (H+1) div 10 , sum([],T,Cf,Tr),!.
 
 sum([H1|T1],[H2|T2],Cf,[Hr|Tr]) :- Hr is (H1 + H2 + Cf ) mod 10 , Cf2 is (H1+H2+Cf) div 10 , sum(T1,T2,Cf2,Tr).


 
 sumMain(L1,L2,Rez) :- reverse(L1,[],L1Re) , reverse(L2,[],L2Re) , sum(L1Re,L2Re,0,SumRe) , reverse(SumRe,[],Rez).