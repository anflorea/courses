;unsigned representation

assume CS:code, DS:data
data SEGMENT
	a DB 10
	b DB 15
	c DW 200
	e DD 150
data ENDS

code SEGMENT
start:
]mov AX, data
	mov DS, AX
	;(a + b * c + 2 / c) / (2 + a) + e

	mov AL, b
	mov AH, 0 ; AX contains b
	mul c ; DX:AX = b * c

	mov BL, a
	mov BH, 0 ; BX = a
	mov CX, 0 ; CX:BX = a

	add BX, AX
	add CX, DX ; CX:BX = a + b * c

	mov AX, 2
	mov DX, 0
	DIV c ; AX = 2 / c
	mov DX, 0 ; DX:AX = 2 / c

	add AX, BX
	add DX, CX ; DX:AX = a + b * c + 2 / c

	mov BL, a
	mov BH, 0 ; BX = a
	add BX, 2 ; BX = a + 2

	div BX ; AX = DX:AX / BX

	mov DX, 0 ; DX:AX = (a + b * c + 2 / c) / (2 + a)

	mov BX, word ptr e
	mov CX, word ptr e+2 ; CX:BX = e

	add AX, BX
	add DX, CX ; final result is DX:AX

	mov AX, 4C00h
	int 21h
code ENDS
END start
