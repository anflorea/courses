assume CS: code, DS: data
data SEGMENT
	error1	db	'Invalid drive letter! $'
	msg		db	'Please insert the Drive letter: $'
	msg2	db	'The default drive has been changed. $'
	max_l	db	2
	len		db	?
	drive	db	4 dup (?)
	curr_d	db	2 dup (?)
	path	db	'D:'
	path_l	equ $ - path
data ENDS

code SEGMENT
start:
	mov AX, data
	mov DS, AX

	new_ln MACRO
		mov dl, 10
		mov dh, 0
		mov ah, 02h
		int 21h

		mov dl, 13
		mov dh, 0
		mov ah, 02h
		int 21h	
	ENDM

	lbl:

	
	mov ah, 09h
	mov DX, offset msg
	int 21h

	mov ah, 0Ah
	mov DX, offset max_l
	int 21h

	mov al, len
	mov ah, 0
	mov si, ax
	mov drive[si], ':'

	mov Ah, drive[0]
	cmp Ah, 'A'
	jl error_msg
	cmp Ah, drive[0]
	cmp Ah, 'Z'
	jg error_msg

	jmp next
	error_msg:
		new_ln
		mov ah, 09h
		mov DX, offset error1
		int 21h
		new_ln
	jmp lbl
	next:

	new_ln

	mov ah, 19h
	int 21h

	ADD al, 'A'
	cmp al, drive[0]
	je is_current_drive

	jmp is_not_current_drive

	is_current_drive:
		mov Ah, 0Eh
		mov DL, drive[0]
		sub DL, 'A'
		int 21h
		
		mov ah, 09h
		mov DX, offset msg2
		int 21h


	is_not_current_drive:
		mov Ah, 3Bh
		;mov drive[1], ':'
		;mov drive[2], '\'
		;mov drive[3], '$'
		mov DX, offset path
		int 21h
	mov AX, 4C00h
	int 21h
code ENDS
END start
