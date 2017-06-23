; LISP 32: Dandu-se o lista liniara, sa se stearga toate secventele de valori numerice consecutive
; exemplu: (1 2 c 4 6 7 8 i 10 j) --> (c 4 i 10 j)

;aux(l1..ln, theLast) =	| nil, if the list is empty
;						| l1..aux(l2..ln, 0), if l1 is not a number
;						| aux(l2..ln, 1), if l2 = l1 + 1
;						| aux(l2..ln, 0), if theLast = 1
;						| l1..aux(l2..ln, 0), otherwise


(DEFUN aux (L theLast)
	(COND
		((NULL L) nil)
		((NOT (NUMBERP (CAR L))) (CONS (CAR L) (aux (CDR L) 0)))
		((AND (NUMBERP (CADR L)) (= (CADR L) (+ (CAR L) 1))) (aux (CDR L) 1))
		((= theLast 1) (aux (CDR L) 0))
		(T (CONS (CAR L) (aux (CDR L) 0)))
	)
)


(DEFUN rem_sublist (L)
	(aux L 0)
)
