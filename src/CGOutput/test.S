	.text

	.globl	_global._init
_global._init:
	addi sp ,sp ,-4
	sw ra ,0(sp)
.LBB0_1:			 #Start0
	sw ra ,0(sp)
	addi sp ,sp ,4
	ret

	.globl	_global.main
_global.main:
	addi sp ,sp ,-56
	sw ra ,52(sp)
	sw s0 ,48(sp)
	sw s1 ,44(sp)
	sw s2 ,40(sp)
	sw s3 ,36(sp)
	sw s4 ,32(sp)
	sw s5 ,28(sp)
	sw s6 ,24(sp)
	sw s7 ,20(sp)
	sw s8 ,16(sp)
.LBB0_2:			 #Start3
	call _global._init		#2
	li t0 ,10		#4
	addi s7 ,t0 ,0		#4
	lw s6 ,8(sp)		#5
	sw s7 ,8(sp)		#6
	lw s6 ,8(sp)		#7
	lw s6 ,8(sp)		#9
	lw s5 ,4(sp)		#10
	sw s6 ,4(sp)		#11
	lw s5 ,4(sp)		#12
	lw s5 ,8(sp)		#13
	li t0 ,10		#14
	addi s4 ,t0 ,0		#14
	sub s5 ,s5 ,s4		#15
	seqz s3 ,s5		#15
	beqz s3 ,.LBB0_5		#16
	j .LBB0_3		#16
.LBB0_3:			 #If-True-Stmt5
	li t0 ,20		#2
	addi s2 ,t0 ,0		#2
	lw s1 ,0(sp)		#3
	sw s2 ,0(sp)		#4
	lw s1 ,0(sp)		#5
	lw s1 ,0(sp)		#6
	lw s0 ,4(sp)		#7
	sw s1 ,4(sp)		#8
	lw s0 ,4(sp)		#9
	j .LBB0_4		#10
.LBB0_5:			 #If-False-Stmt6
	j .LBB0_4		#1
.LBB0_4:			 #Succeed7
	lw s0 ,4(sp)		#1
	sw s0 ,12(sp)		#2
	lw a0 ,12(sp)		#3
	mv a0 ,a0		#4
	sw s0 ,48(sp)
	sw s1 ,44(sp)
	sw s2 ,40(sp)
	sw s3 ,36(sp)
	sw s4 ,32(sp)
	sw s5 ,28(sp)
	sw s6 ,24(sp)
	sw s7 ,20(sp)
	sw s8 ,16(sp)
	sw ra ,52(sp)
	addi sp ,sp ,56
	ret
