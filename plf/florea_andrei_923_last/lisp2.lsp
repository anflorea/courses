(DEFUN leftTree (tree)

	(COND

		((EQ (CADR tree) 0) nil)
		(T (CDDR tree))

	)

)

(DEFUN remLeftTree (tree counter)

	(COND

		((NULL tree) nil) 
		((EQ counter 0) tree)
		(T (remLeftTree (CDDR tree) (+ (CADR tree) (- counter 1))))

	)

)

(DEFUN rightTree(tree)

	(COND

		((< (CADR tree) 2) nil)
		(T (remLeftTree (CDDR tree) 1))

	)

)
#||
7. Return the level of a node X in a tree of type (1). The level of the root element is 0.

depth(T-tree N-searchedNode L-level)
depth(tree, node, level) = 	{ null ; tree is empty
							{ level ; top of tree = node
							{ depth(leftTree(tree), node, level + 1) ; depth(leftTree(tree), node, level + 1) != null
							{ depth(rightTree(tree), node, level + 1) ; depth(rightTree(tree), node, level + 1) != null
							{ null ; otherwise

||#

(DEFUN depth (tree node level)

	(COND

		((NULL tree) nil)
		((STRING= (CAR tree) node) level)
		((NOT (NULL (depth (leftTree tree) node (+ 1 level)))) (depth (leftTree tree) node (+ 1 level)))
		((NOT (NULL (depth (rightTree tree) node (+ 1 level)))) (depth (rightTree tree) node (+ 1 level)))
		(T nil)

	)

)

(DEFUN depthMain (tree node)

	(depth tree node 0)

)
