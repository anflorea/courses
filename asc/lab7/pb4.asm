assume CS: code, DS: data
data SEGMENT
	S		db	1, 2, 4, 6, 10, 20, 25
	lenS	equ $ - S
	D		db	7 dup(0)
	k		dw	2
data ENDS

code SEGMENT
start:
	mov AX, data
	mov DS, AX

	repeat:
		mov BX, k
		mov AL, S[BX]
		sub BX, 1
		sub AL, S[BX]
		mov D[BX], AL
		add BX, 2
		cmp BX, lenS
		JB repeat

	mov AX, 4C00h
	int	21h
code ENDS
END start
