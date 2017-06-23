;;;a) Write a function to return the n-th element of a list, or NIL if such an element does not exist.
;;;b) Write a function to check whether an atom E is a member of a list which is not necessarily linear.
;;;c) Write a function to determine the list of all sublists of a given list, on any level.
;;; A sublist is either the list itself, or any element that is a list, at any level. Example:
;;;  (1 2 (3 (4 5) (6 7)) 8 (9 10)) => 5 sublists :
;;;   ( (1 2 (3 (4 5) (6 7)) 8 (9 10)) (3 (4 5) (6 7)) (4 5) (6 7) (9 10) )
;;;   d) Write a function to transform a linear list into a set.


; a)

#||

nth-elem(L, N) =	| NULL, if N < 1
					| L1, if N == 1
					| nth-elem( L2..Ln, N - 1)

||#

(DEFUN nth-elem (L N)
	(COND
		((< N 1) NIL)
		((= N 1) (CAR L))
		(t (nth-elem (CDR L) (- N 1)))
	)
)

; b)

#||

check(L, e) =	| NULL, if L's length is 0
				| check(L1, e), if L1 is a list
				| true, if L1 == e
				| check( L2..Ln, e), otherwise

||#

(DEFUN check (L e)
	(COND
		((NULL L) NIL)
		((LISTP (CAR L)) (check (CAR L) e))
		((= (CAR L) e) T)
		(T (check (CDR L) e))
	)
)

; c)

#||

sublists(L) =	| L, if L's length is 0
				| sublists(L1) + sublists(L2..Ln), if L1 is a list
				| sublists(L2..Ln), otherwise

||#

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

#||

isMember(L, elem) =	| false, if L's legth is 0
					| true, if L1 == elem
					| isMember(L2..Ln, elem)

setCreate(L) =	| NULL, if L's length is 0
				| setCreate(L2..Ln), if isMember(L2..Ln, L1)
				| L1 + setCreate(L2..Ln), otherwise

||#

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
