;1.
;a) Write a function to return the n-th element of a list, 
;or NIL if such an element does not exist.

;					|NIL, N=0
;element_n(l1..ln)=
;

(DEFUN element_n(L In N)
	(COND 
		((NULL L) NIL)
		((= In N)(CAR L))
		(T(element_n (CDR L) (+ 1 In) N ))
	)
)

;b) Write a function to check whether an atom E is a member of a
;list which is not necessarily linear.
;ex : (1 2 3 (4 9)) 

;
;
;

(DEFUN be_member(L E)
	(COND
		((NULL L) nil)
		((AND (ATOM (CAR L))( = (CAR L) E)) t)
		((ATOM (CAR L)) (be_member (CDR L) E))
		(T (or(be_member (CAR L) E) (be_member (CDR L) E)))
	)
)

;c) Write a function to determine the list of all sublists of a given list, on any level.
;A sublist is either the list itself, or any element that is a list, at any level. Example:
;(1 2 (3 (4 5) (6 7)) 8 (9 10)) => 5 sublists :
;( (1 2 (3 (4 5) (6 7)) 8 (9 10)) (3 (4 5) (6 7)) (4 5) (6 7) (9 10) )

(DEFUN sublists(L Sub)
       (COND
               ((NULL L) Sub)
               ((LISTP (CAR L))(APPEND (sublists (CAR L) NIL) (sublists (CDR L) (CONS (CAR L) Sub))))
               (T(sublists (CDR L) Sub))
       )
) 

(DEFUN gen_sublists(L)
          (sublists L L)
)

;d) Write a function to transform a linear list into a set.

(DEFUN is_member(L E)
      (COND
              ((NULL L) NIL)
              ((= (CAR L) E) T)
              (T (is_member (CDR L) E))
      )
)

(DEFUN to_set(L)
      (COND
              ((NULL L) NIL)
              ((is_member (CDR L) (CAR L)) (to_set (CDR L)))
              (T(CONS (CAR L) (to_set (CDR L))))
      )
)



  
