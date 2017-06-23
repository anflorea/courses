;;; 11. Write a function to determine the depth of a list.

;;
;; depth(X) = 	| 0 , if X is not a list
;;			  	| 
;;			   	| 1 + max( depth(X1)..depth(Xn) ) , if X is a list
;;


; method 1
(DEFUN depth2 (X)
	(COND
		((atom X) 0)
		(T (+ 1 (apply `max (mapcar `depth2 X))))
	)
)

; method 2 -> using a variable
(DEFUN depth (X cur)
	(COND
		((atom X) cur)
		(T (apply `max (mapcar (lambda (a) (depth a (+ cur 1))) X)))
	)
)

(DEFUN getDepth (X)
	(depth X 0)
)
