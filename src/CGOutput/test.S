	.text

	.globl	main
main:
	addi sp ,sp ,-52
	sw ra ,48(sp)
	sw s0 ,44(sp)
	sw s1 ,40(sp)
Start2:
	call _global._init		#1
	mv s0 ,a0
	sw s0 ,32(sp)		#1
	addi t0 ,sp ,24		#2
	sw t0 ,28(sp)		#2
	addi t0 ,sp ,16		#3
	sw t0 ,20(sp)		#3
	addi t0 ,sp ,8		#4
	sw t0 ,12(sp)		#4
	lw t0 ,12(sp)		#5
	lw s0 ,4(sp)		#5
	sw s0 ,0(t0)		#5
	j End3		#6
End3:
	lw t0 ,28(sp)		#1
	lw s0 ,0(t0)		#1
	sw s0 ,0(sp)		#1
	lw s0 ,0(sp)		#2
	mv a0 ,s0
	lw s1 ,40(sp)
	lw s0 ,44(sp)
	lw ra ,48(sp)
	addi sp ,sp ,52
	ret

	.globl	_global._init
_global._init:
	addi sp ,sp ,-16
	sw ra ,12(sp)
	sw s0 ,8(sp)
	sw s1 ,4(sp)
Start0:
	j End1		#1
End1:
	lw s1 ,4(sp)
	lw s0 ,8(sp)
	lw ra ,12(sp)
	addi sp ,sp ,16
	ret
