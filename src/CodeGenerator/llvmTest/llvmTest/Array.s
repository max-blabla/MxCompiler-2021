	.text
	.file	"Array.c"
	.globl	main                    # -- Begin function main
	.p2align	2
	.type	main,@function
main:                                   # @main
	.cfi_startproc
# %bb.0:
	addi	sp, sp, -944
	.cfi_def_cfa_offset 944
	sw	ra, 940(sp)
	sw	s0, 936(sp)
	sw	s1, 932(sp)
	.cfi_offset ra, -4
	.cfi_offset s0, -8
	.cfi_offset s1, -12
	addi	s0, sp, 944
	.cfi_def_cfa s0, 0
	lui	a0, %hi(arr1)
	addi	a0, a0, %lo(arr1)
	sw	zero, 8(a0)
	lui	a1, %hi(arr2)
	addi	s1, a1, %lo(arr2)
	addi	a1, zero, 1
	sw	a1, 12(s1)
	lw	a0, 4(a0)
	sw	a0, 12(s1)
	addi	a0, zero, 52
	mv	a1, zero
	call	malloc
	lui	a1, %hi(arr3)
	sw	a0, %lo(arr3)(a1)
	addi	a0, zero, 92
	mv	a1, zero
	call	malloc
	sw	a0, -16(s0)
	sw	s1, -16(s0)
	mv	a0, zero
	lw	s1, 932(sp)
	lw	s0, 936(sp)
	lw	ra, 940(sp)
	addi	sp, sp, 944
	ret
.Lfunc_end0:
	.size	main, .Lfunc_end0-main
	.cfi_endproc
                                        # -- End function
	.type	arr1,@object            # @arr1
	.bss
	.globl	arr1
	.p2align	4
arr1:
	.zero	400
	.size	arr1, 400

	.type	arr2,@object            # @arr2
	.globl	arr2
	.p2align	4
arr2:
	.zero	492
	.size	arr2, 492

	.type	arr3,@object            # @arr3
	.comm	arr3,4,8
	.ident	"clang version 10.0.0-4ubuntu1 "
	.section	".note.GNU-stack","",@progbits
