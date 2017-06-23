assume CS: code, DS: data
data SEGMENT
	a1	db	0, 1, 2, 'xyz'
		db	2 shl 4, 'F' + 3
		db	'Q', 'Laura'
	a2	db	3 dup (45h)
	a3	db	1 dup(5 dup(3), 11)
	a4	dw	a2 + 1, 'bc'
	a5	db	0ffh
	a6	dw	100h
	a8	dw	a5
	a9	dd	a5
;	a10	db	a5
	a11	db	offset a5
data ENDS

code SEGMENT
start:
	mov AX, data
	mov DS, AX

	mov AX, offset a2
	mov AL, byte ptr a4
	mov AL, -128

	mov AX, 4C00h
	int 21h
code ENDS
END start
