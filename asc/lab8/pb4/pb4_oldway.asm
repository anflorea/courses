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
	currP	dw	0
data ENDS

code SEGMENT
start:
	mov AX, data
	mov DS, AX

	mov CX, lenS
	mov BX, itS
	repeat:
		mov	currP, BX
		mov	BP, 0
		mov	DL, 1
		mov ok, DL
		repeat2:
			mov DL, D[BP]
			cmp S[BX], DL
			je ifne
				mov DL, 0
				mov ok, DL
			ifne:
			inc BX
			inc BP
			cmp ok, 0
			je outside
			cmp BX, lenS
			je outside
			cmp BP, lenD
			jb repeat2
		outside:
		cmp ok, 0
		je iff2
			mov BX, itsol
			mov DL, byte ptr currP
			mov sol[BX], DL
			inc BX
			mov itsol, BX
		iff2:
		mov BX, currP
		inc BX
		;cmp BX, lenS
		;jb repeat  
		loop repeat

	mov AX, 4C00h
	int 21h
code ENDS
END start
