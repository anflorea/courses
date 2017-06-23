;;;doit(l1 l2 ... ln, M, Poz, V) =	| NaN, n == 0
;;;									|
;;;									| V U doit(l1 l2 ... ln, M, Poz + 1, V) , Poz % M == 0
;;;									|
;;;									| l1 U doit(l2 ... ln, M, Poz + 1, V) , otherwise

(DEFUN doIt (L M Poz V)
	(COND
		((NULL L) NIL)
		((EQUAL 0 (MOD P M)) (CONS V (doIt L M (+ 1 Poz) V)))
		(T (CONS (CAR L) (doIt (CDR L) M (+ 1 Poz) V)))
	)
)

(DEFUN main (L, M, V)
	(doIt L M 1 V)
)
