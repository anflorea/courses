%interclasare 2 liste sortate
%
inter([],[],[]).
inter([],L2,L2):-!.
inter(L1,[],L1):-!.
inter([H1|L1],[H2|L2],[H1|L3]):-H1<H2,!,
                                L=[H2|L2],
                                inter(L1,L,L3).
inter([H1|L1],[H2|L2],[H2|L3]):-H1>=H2,!,
                               L=[H1|L1],
                               inter(L,L2,L3).
