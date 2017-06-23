;;;; Definiti o functie care elimina toate aparitiile unui element dintr-o lista neliniara
;;;; eg: (2 3 (2 4 2) 2 10) => e = 2 => (3 (4) 10)

;;; elim (l1 ... ln, e) = 	| NaN , n == 0
;;;							|
;;;							| elim(l2 ... ln, e) , l1 == e
;;;							|
;;;							| elim(l1, e) U elim(l2 .. ln, e) , l1 = list
;;;							|
;;;							| l1 U elim

(DEFUN elim (L e)
	(COND
		((NULL L) NIL)
		((LISTP (CAR L)) (CONS (elim (CAR L) e) (elim (CDR L) e)))
		((EQUAL (CAR L) e) (elim (CDR L) e))
		(t (CONS (CAR L) (elim (CDR L) e)))
	)
)

(DEFUN main (L e)
	(elim L e)
)
