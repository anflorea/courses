

; a)

(DEFUN nth-elem (L N)
	(COND
		((< N 1) NIL)
		((= N 1) (CAR L))
		(t (nth-elem (CDR L) (- N 1)))
	)
)

; b)

(DEFUN check (L e)
	(COND
		((NULL L) NIL)
		((LISTP (CAR L)) (check (CAR L) e))
		((= (CAR L) e) T)
		(T (check (CDR L) e))
	)
)

; c)

(DEFUN sublists (L Sub)
	(COND
		((NULL L) Sub)
		((LISTP (CAR L)) 
			(APPEND (sublists (CAR L) NIL) (sublists (CDR L) (CONS (CAR L) Sub)))
			)
		(T (sublists (CDR L) Sub))
	)
)

(DEFUN gen_sublists(L)
	(sublists L L)
)

; d)

(DEFUN isMember(L elem)
	(COND
		((NULL L) NIL)
		((= (CAR L) elem) T)
		(T (isMember (CDR L) elem))
	)
)

(DEFUN setCreate (L)
	(COND
		((NULL L) NIL)
		((isMember (CDR L) (CAR L)) (setCreate (CDR L)))
		(T (CONS (CAR L) (setCreate (CDR L))))
	)
)
