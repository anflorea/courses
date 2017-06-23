;signed representation

assume CS: code, DS: data
data SEGMENT
	a	DB	10
	b	DB	15
	c	DW	200
	e	DD	150
	aux	DW	?
data ENDS

code SEGMENT
start:
	mov AX, data
	mov DS, AX
	; (a + b * c + 2 / c) / (2 + a) + e

	mov AL, b
	cbw ; AX = b
	imul c ; DX:AX = b * c

	mov CX, DX
	mov BX, AX ; CX:BX = b * c

	mov AL, a
	cbw
	mov aux, AX ; aux = a
	cwd ; DX:AX = a

	adc BX, AX
	adc CX, DX ; CX:BX = a + b * c

	mov AX, 2
	cwd	; DX:AX = 2

	idiv c ; AX = 2 / c
	cwd ; DX:AX = 2 / c

	adc aux, 2 ; aux = 2 + a
	adc AX, BX
	adc DX, CX ; DX:AX = a + b * c + 2 / c

	idiv aux ; AX = DX:AX / (2 + a)
	cwd

	mov BX, word ptr e
	mov CX, word ptr e+2; CX:BX = e

	adc AX, BX
	adc DX, CX ; final result is stored in DX:AX

	mov AX, 4C00h
	int 21h
code ENDS
END start

