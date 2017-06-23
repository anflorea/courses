assume CS: code, DS: data
data SEGMENT
	A dw 0110011011001010b
	B dw 1101011100001011b
	C dw ?
	aux dw ?
	aux2 dw ?
data ENDS

code SEGMENT
start:
	mov AX, data
	mov DS, AX

	mov AX, B
	mov CL, 5
	shr AX, CL ; AX 5-8 become AX 0-3
	mov BL, 00001111b
	mov BH, 0
	and AX, BX ; AX has only 0-3
	
	mov aux, AX ; aux 0-3 are 5-8 of B
	
	mov AX, A
	mov BL, 00011111b
	mov BH, 0
	and AX, BX ; AX has only 0-4 of A

	mov CL, 4
	shl AX, CL ; AX has on 4-8, 0-4 of A

	mov aux2, AX ; aux2 has on 4-8, 0-4 of A

	mov AX, A
	mov CL, 6
	shr AX, CL
	mov BL, 01111111b
	mov BH, 0
	and AX, BX
	mov CL, 9
	shl AX, CL ; AX has on 9-15, 6-12 of A

	or AX, aux
	or AX, aux2
	
	mov C, AX ; C stores the result
	

	mov AX, 4C00h
	int	21h
END start
code ENDS
