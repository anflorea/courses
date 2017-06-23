assume CS: code, DS: data
data SEGMENT
	msg		db	'Vineee Craciunuuuul!!!! (Salut Vlad) $'
	max_n	db	20
	len		db	?
	read	db	20 dup (?)
data ENDS

code SEGMENT
start:
	mov AX, data
	mov DS, AX

	mov ah, 09h
	mov DX, offset msg
	int 21h

	mov ah, 0Ah
	mov DX, offset max_n
	int 21h

	mov al, len
	mov ah, 0
	mov si, ax
	mov read[si], '$'

	mov dl, 10
	mov dh, 0
	mov ah, 02h
	int 21h

	mov dl, 13
	mov dh, 0
	mov ah, 02h
	int 21h

	mov ah, 09h
	mov DX, offset read
	int 21h

	mov AX, 4C00h
	int 21h
code ENDS
END start
