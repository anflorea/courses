assume CS: code, DS: data
data SEGMENT
	S		db	1, 2, 3, 4, 1, 2, 3, 1, 4, 1, 2, 3, 1, 1, 2
	lenS	equ	$ - S
	D		db	1, 2
	lenD	equ	$ - D
	sol		db	lenS	dup(?)
	itS		dw	0
	itD		dw	0
	itsol	dw	0
	ok		db	0
	var		dw	0
data ENDS

code SEGMENT
start:
	mov AX, data
	mov DS, AX
	mov ES, AX

	mov CX, lenS
	mov BX, 0
	mov BP, 0
	repeat:
		mov SI, offset S
		mov DI, offset D
		mov	DL, 0
		mov ok, DL
		mov var, SI
		mov CX, lenD
		repeat2:
			cmpsb
			je overpass
				mov DL, 1
				mov ok, DL
			overpass:
			loop repeat2
		cmp ok, 1
		je iff2
			mov AL, byte ptr var
			mov sol[BP], AL
		iff2:
		inc BX
		cmp BX, lenS
		jb repeat  

	mov AX, 4C00h
	int 21h
code ENDS
END start
