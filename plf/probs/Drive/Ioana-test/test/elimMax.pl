  %Elimina toate aparitiile elem maxim dintr-o lista

  maxim([],-3200).
  maxim([H|T],M):-maxim(T,M),H<M,!.
  maxim([H|_],H).

  elimin(E,[],[]).
  elimin(E,[E|T],L):-!,elimin(E,T,L).
  elimin(E,[H|T],[H|L]):-elimin(E,T,L).

  eliminMax([],[]).
  eliminMax(L1,L2):-maxim(L1,M),
                    elimin(M,L1,L2).
