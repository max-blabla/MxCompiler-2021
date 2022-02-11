	.text

	.globl	main
main:
	addi sp ,sp ,-72
	sw ra ,68(sp)
	sw s3 ,0(sp)
	sw s4 ,4(sp)
	sw s5 ,8(sp)
	sw s11 ,12(sp)
	sw s6 ,16(sp)
	sw s10 ,20(sp)
	sw s7 ,24(sp)
	sw s8 ,28(sp)
	sw s9 ,32(sp)
	sw s0 ,36(sp)
	sw s1 ,40(sp)
	sw s2 ,44(sp)
Start2:
	call _global._init		#1
	addi s0 ,a0 ,0		#1
	addi s1 ,zero ,10		#5
	addi s2 ,zero ,4		#6
	mul s3 ,s1 ,s2		#6
	addi s4 ,zero ,4		#7
	add s5 ,s3 ,s4		#7
	addi a0 ,s5 ,0		#8
	call malloc		#8
	addi s6 ,a0 ,0		#8
	sw s6 ,52(sp)		#9
	addi s6 ,zero ,2		#11
	addi s7 ,zero ,4		#12
	mul s8 ,s6 ,s7		#12
	addi s9 ,zero ,4		#13
	add s10 ,s8 ,s9		#13
	addi a0 ,s10 ,0		#14
	call malloc		#14
	addi s11 ,a0 ,0		#14
	sw s11 ,48(sp)		#15
	addi s11 ,zero ,0		#16
	sw s11 ,60(sp)		#17
	j End3		#18
End3:
	lw s11 ,60(sp)		#1
	addi a0 ,s11 ,0		#2
	lw s3 ,0(sp)
	lw s4 ,4(sp)
	lw s5 ,8(sp)
	lw s11 ,12(sp)
	lw s6 ,16(sp)
	lw s10 ,20(sp)
	lw s7 ,24(sp)
	lw s8 ,28(sp)
	lw s9 ,32(sp)
	lw s0 ,36(sp)
	lw s1 ,40(sp)
	lw s2 ,44(sp)
	lw ra ,68(sp)
	addi sp ,sp ,72
	ret

	.globl	_global._init
_global._init:
	addi sp ,sp ,-8
	sw ra ,4(sp)
Start0:
	j End1		#1
End1:
	lw ra ,4(sp)
	addi sp ,sp ,8
	ret
