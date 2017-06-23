assume CS:code, DS:data
data segment
	a DB 5
	b DW 7
	c DB 2
	d DD 4
data ends
code segment
start:
	mov AX, data
	mov DS, AX

	mov AL, a
	mul b ; dx:ax=a*b
	mov BX, 7
	sub BX, AX
	mov AX, BX
	mov BL, c 
	mov BH, 0 ; bx=c
	add ax,bx ; dx:ax=7-a*b+c
	
	mov bl, a
	mov bh,0 ; bx = a
	div BX ; ax - citul, dx - restul 

	mov BL, 6
	mov BH, 0
	add AX, BX ; add ax, 6
	mov dx,0 
	
	mov CX, word ptr d
	mov BX, word ptr d+2 ; bx:cx = d
	sub CX, AX
	sbb BX, DX ;rezultatul in  bx:cx 
	mov AX, 4C00h
code ends
end start
