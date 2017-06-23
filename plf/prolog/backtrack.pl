mountain([H,H1],R):-
	H>H1 ,
	((R=:=1);(R=:=2)),!.

	mountain([H,H1|T],R):-
		((R=:=1);(R=:=0)),
		H<H1,
		mountain([H1|T],1),!.

		mountain([H,H1|T],R):-
			((R=:=1);(R=:=2)),
			H>H1,
			mountain([H1|T],2),!.




	% Function checks if its only parameter, a list , is a mountain (acts as
			% main() for function mountain )

	is_mountain([H|T]):-
		mountain([H|T],0),!.


		% Function returns the number of sublists in a list(received as a
				% parameter )which have a mountain aspect

		noOfM([],0):-!.
			noOfM([H|T],Res):-
				is_list(H),
				is_mountain(H),
				noOfM(T,R),
				Res is R+1,!.
				noOfM([_|T],Res):-
					noOfM(T,Res),!.
