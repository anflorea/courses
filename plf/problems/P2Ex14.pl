%14.
%a. Define a predicate to determine the longest sequences of consecutive even numbers (if exist more maximal
%sequences one of them).

%11:41

%--------------seq(L,Asize,Msize,Aseq,Mseq)-------------

seq([],Asize,Msize,Aseq,_,Aseq) :- Asize>Msize,!.
seq([],_,_,Aseq,Mseq,Mseq):-!.

seq([H1|T],Asize,Msize,Tr,Mseq,Rez) :- H1 mod 2 =:=0 ,  Asize2 is Asize + 1, seq(T,Asize2,Msize,[H1|Tr],Mseq,Rez),!.

seq([H1|T],Asize,Msize,Aseq,Mseq,Rez) :- H1 mod 2 =:= 1 ,Asize>Msize, seq(T,0,Asize,[],Aseq,Rez),!.
seq([H1|T],Asize,Msize,Aseq,Mseq,Rez) :- H1 mod 2 =:= 1 ,Asize=<Msize, seq(T,0,Msize,[],Mseq,Rez).
 
 
 
setSeq(L,R) :- seq(L,0,0,[],[],Rez),reverse(Rez,R).



reverse([],R,R):-!.
reverse([H|T],Tr,R) :- reverse(T,[H|Tr],R).



