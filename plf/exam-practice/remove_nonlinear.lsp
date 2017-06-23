(DEFUN fmm (L e)
	(COND
		((NULL L) nil)
		((ATOM (CAR L)) (IF (EQ (CAR L) e) (fmm (CDR L) e) (CONS (CAR L) (fmm (CDR L) e))))
		(T (CONS (fmm (CAR L) e) (fmm (CDR L) e)))
	)
)

(DEFUN check (L lastt)
	(COND
		((AND (AND (NULL L) (ATOM lastt)) (NOT (NUMBERP lastt))) T)
		((NULL L) nil)
)

(DEFUN fmm2 (L)
	(COND
		((NULL L) 0)
		((ATOM L) 0)
		(T (+ (check L nil) (apply `+ (mapcar `fmm2 L))))
	)
)
