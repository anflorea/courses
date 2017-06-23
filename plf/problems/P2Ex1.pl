%1.
%a. Sort a list with removing the double values. E.g.: [4 2 6 2 3 4] --> [2 3 4 6]


member([El|T],El):-!.
member([H|T],El):- member(T,El).

insertInSorted([],El,0,[El]):-!.
insertInSorted(L,El,1,L):-!.
insertInSorted([H|T],El,0,[H|Tr]) :- H<El ,insertInSorted(T,El,0,Tr),!.
insertInSorted([H|T],El,0,[El,H|Tr]) :- H>=El, insertInSorted(T,El,1,Tr).

sortList([],[]).
sortList([H|T],Rez):-  not(member(T,H)), sortList(T,Rezz), insertInSorted(Rezz,H,0,Rez),!.
sortList([H|T],Rez):-  sortList(T,Rez).



