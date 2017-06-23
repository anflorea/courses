%2.
%a. Write a predicate to remove all occurrences of a certain atom from a list.
%b. Define a predicate to produce a list of pairs (atom n) from an initial list of atoms. In this initial list atom has
%n occurrences.
%Eg.: numberatom([1, 2, 1, 2, 1, 3, 1], X) => X = [[1, 4], [2, 2], [3, 1]].


%--------a----------

elimOcc([],_,[]).
elimOcc([H|T],Atom,[H|Tr]) :- H \=Atom , elimOcc(T,Atom,Tr).
elimOcc([H|T],Atom,Tr) :- H = Atom , elimOcc(T,Atom,Tr).


%-----------b---------


numberOcc([],_,0).
numberOcc([H|T],Elem,Rez):- H=Elem, numberOcc(T,Elem,Rez1),Rez is Rez1+1 ,!.
numberOcc([H|T],Elem,Rez) :- numberOcc(T,Elem,Rez).


numberAtom([],Lc,RezList,RezList).
numberAtom([H|T],Lc,RezList,RezC) :- numberOcc(T,H,R) , R = 0 , numberOcc(Lc,H,N), numberAtom(T,Lc,[[H,N]|RezList],RezC),!.
numberAtom([H|T],Lc,RezList,RezC) :- numberOcc(T,H,R) , R \= 0 , numberAtom(T,Lc,RezList,RezC).


setNumberAtom(L,Rez) :- numberAtom(L,L,[],Rez).