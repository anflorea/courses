;;;; Definiti o functie care interclaseaza, fara pastrarea dublurilor, 2 liste liniare sortate cu elemente unice

;;; inter (l1 l2 ... ln, a1 a2 ... am) =	| a1 a2 ... am , if n == 0
;;;											|
;;;											| l1 l2 ... ln , if m == 0
;;;											|
;;;											| l1 U inter(l2 ... ln, a1 ... am) , l1 < a1
;;;											|
;;;											| a1 U inter(l1 ... ln, a2 ... am) , l1 > a1
;;;											|
;;;											| a1 U inter(l2 ... ln, a2 ... am) , l1 == a1

(DEFUN inter (L A)
	(COND
		((NULL L) A)
		((NULL A) L)
		((< (CAR L) (CAR A)) (CONS (CAR L) (inter (CDR L) A)))
		((> (CAR L) (CAR A)) (CONS (CAR A) (inter L (CDR A))))
		((EQUAL (CAR L) (CAR A)) (CONS (CAR L) (inter (CDR L) (CDR A))))
	)
)


