	.text
	.file	"builtin.c"
	.globl	toString                # -- Begin function toString
	.p2align	2
	.type	toString,@function
toString:                               # @toString
	.cfi_startproc
# %bb.0:
	addi	sp, sp, -16
	.cfi_def_cfa_offset 16
	sw	ra, 12(sp)
	sw	s0, 8(sp)
	.cfi_offset ra, -4
	.cfi_offset s0, -8
	addi	s0, sp, 16
	.cfi_def_cfa s0, 0
	sw	a0, -12(s0)
	addi	a0, zero, 14
	mv	a1, zero
	call	malloc
	sw	a0, -16(s0)
	lw	a0, -16(s0)
	lw	a2, -12(s0)
	lui	a1, %hi(.L.str)
	addi	a1, a1, %lo(.L.str)
	call	sprintf
	lw	a0, -16(s0)
	sb	zero, 13(a0)
	lw	a0, -16(s0)
	lw	s0, 8(sp)
	lw	ra, 12(sp)
	addi	sp, sp, 16
	ret
.Lfunc_end0:
	.size	toString, .Lfunc_end0-toString
	.cfi_endproc
                                        # -- End function
	.globl	print                   # -- Begin function print
	.p2align	2
	.type	print,@function
print:                                  # @print
	.cfi_startproc
# %bb.0:
	addi	sp, sp, -16
	.cfi_def_cfa_offset 16
	sw	ra, 12(sp)
	sw	s0, 8(sp)
	.cfi_offset ra, -4
	.cfi_offset s0, -8
	addi	s0, sp, 16
	.cfi_def_cfa s0, 0
	sw	a0, -16(s0)
	lw	a1, -16(s0)
	lui	a0, %hi(.L.str.1)
	addi	a0, a0, %lo(.L.str.1)
	call	printf
	lw	s0, 8(sp)
	lw	ra, 12(sp)
	addi	sp, sp, 16
	ret
.Lfunc_end1:
	.size	print, .Lfunc_end1-print
	.cfi_endproc
                                        # -- End function
	.globl	println                 # -- Begin function println
	.p2align	2
	.type	println,@function
println:                                # @println
	.cfi_startproc
# %bb.0:
	addi	sp, sp, -16
	.cfi_def_cfa_offset 16
	sw	ra, 12(sp)
	sw	s0, 8(sp)
	.cfi_offset ra, -4
	.cfi_offset s0, -8
	addi	s0, sp, 16
	.cfi_def_cfa s0, 0
	sw	a0, -16(s0)
	lw	a1, -16(s0)
	lui	a0, %hi(.L.str.2)
	addi	a0, a0, %lo(.L.str.2)
	call	printf
	lw	s0, 8(sp)
	lw	ra, 12(sp)
	addi	sp, sp, 16
	ret
.Lfunc_end2:
	.size	println, .Lfunc_end2-println
	.cfi_endproc
                                        # -- End function
	.globl	printInt                # -- Begin function printInt
	.p2align	2
	.type	printInt,@function
printInt:                               # @printInt
	.cfi_startproc
# %bb.0:
	addi	sp, sp, -16
	.cfi_def_cfa_offset 16
	sw	ra, 12(sp)
	sw	s0, 8(sp)
	.cfi_offset ra, -4
	.cfi_offset s0, -8
	addi	s0, sp, 16
	.cfi_def_cfa s0, 0
	sw	a0, -12(s0)
	lw	a1, -12(s0)
	lui	a0, %hi(.L.str)
	addi	a0, a0, %lo(.L.str)
	call	printf
	lw	s0, 8(sp)
	lw	ra, 12(sp)
	addi	sp, sp, 16
	ret
.Lfunc_end3:
	.size	printInt, .Lfunc_end3-printInt
	.cfi_endproc
                                        # -- End function
	.globl	printlnInt              # -- Begin function printlnInt
	.p2align	2
	.type	printlnInt,@function
printlnInt:                             # @printlnInt
	.cfi_startproc
# %bb.0:
	addi	sp, sp, -16
	.cfi_def_cfa_offset 16
	sw	ra, 12(sp)
	sw	s0, 8(sp)
	.cfi_offset ra, -4
	.cfi_offset s0, -8
	addi	s0, sp, 16
	.cfi_def_cfa s0, 0
	sw	a0, -12(s0)
	lw	a1, -12(s0)
	lui	a0, %hi(.L.str.3)
	addi	a0, a0, %lo(.L.str.3)
	call	printf
	lw	s0, 8(sp)
	lw	ra, 12(sp)
	addi	sp, sp, 16
	ret
.Lfunc_end4:
	.size	printlnInt, .Lfunc_end4-printlnInt
	.cfi_endproc
                                        # -- End function
	.globl	getString               # -- Begin function getString
	.p2align	2
	.type	getString,@function
getString:                              # @getString
	.cfi_startproc
# %bb.0:
	addi	sp, sp, -16
	.cfi_def_cfa_offset 16
	sw	ra, 12(sp)
	sw	s0, 8(sp)
	.cfi_offset ra, -4
	.cfi_offset s0, -8
	addi	s0, sp, 16
	.cfi_def_cfa s0, 0
	addi	a0, zero, 257
	mv	a1, zero
	call	malloc
	sw	a0, -16(s0)
	lw	a1, -16(s0)
	lui	a0, %hi(.L.str.1)
	addi	a0, a0, %lo(.L.str.1)
	call	__isoc99_scanf
	lw	a0, -16(s0)
	sb	zero, 256(a0)
	lw	a0, -16(s0)
	lw	s0, 8(sp)
	lw	ra, 12(sp)
	addi	sp, sp, 16
	ret
.Lfunc_end5:
	.size	getString, .Lfunc_end5-getString
	.cfi_endproc
                                        # -- End function
	.globl	getInt                  # -- Begin function getInt
	.p2align	2
	.type	getInt,@function
getInt:                                 # @getInt
	.cfi_startproc
# %bb.0:
	addi	sp, sp, -16
	.cfi_def_cfa_offset 16
	sw	ra, 12(sp)
	sw	s0, 8(sp)
	.cfi_offset ra, -4
	.cfi_offset s0, -8
	addi	s0, sp, 16
	.cfi_def_cfa s0, 0
	addi	a0, zero, 4
	mv	a1, zero
	call	malloc
	sw	a0, -16(s0)
	lw	a1, -16(s0)
	lui	a0, %hi(.L.str)
	addi	a0, a0, %lo(.L.str)
	call	__isoc99_scanf
	lw	a0, -16(s0)
	lw	a0, 0(a0)
	lw	s0, 8(sp)
	lw	ra, 12(sp)
	addi	sp, sp, 16
	ret
.Lfunc_end6:
	.size	getInt, .Lfunc_end6-getInt
	.cfi_endproc
                                        # -- End function
	.globl	_string.parseInt                # -- Begin function _string.parseInt
	.p2align	2
	.type	_string.parseInt,@function
_string.parseInt:                               # @_string.parseInt
	.cfi_startproc
# %bb.0:
	addi	sp, sp, -32
	.cfi_def_cfa_offset 32
	sw	ra, 28(sp)
	sw	s0, 24(sp)
	.cfi_offset ra, -4
	.cfi_offset s0, -8
	addi	s0, sp, 32
	.cfi_def_cfa s0, 0
	sw	a0, -16(s0)
	lw	a0, -16(s0)
	call	strlen
	sw	a0, -20(s0)
	sw	zero, -24(s0)
	sw	zero, -32(s0)
	j	.LBB7_1
.LBB7_1:                                # =>This Inner Loop Header: Depth=1
	lw	a0, -32(s0)
	lw	a1, -20(s0)
	bge	a0, a1, .LBB7_7
	j	.LBB7_2
.LBB7_2:                                #   in Loop: Header=BB7_1 Depth=1
	lw	a0, -16(s0)
	lw	a1, -32(s0)
	add	a0, a0, a1
	lb	a0, 0(a0)
	addi	a0, a0, -48
	sw	a0, -28(s0)
	lw	a0, -28(s0)
	addi	a1, zero, 9
	blt	a1, a0, .LBB7_4
	j	.LBB7_3
.LBB7_3:                                #   in Loop: Header=BB7_1 Depth=1
	lw	a0, -28(s0)
	addi	a1, zero, -1
	blt	a1, a0, .LBB7_5
	j	.LBB7_4
.LBB7_4:
	j	.LBB7_8
.LBB7_5:                                #   in Loop: Header=BB7_1 Depth=1
	lw	a0, -24(s0)
	addi	a1, zero, 10
	mul	a0, a0, a1
	lw	a1, -28(s0)
	add	a0, a0, a1
	sw	a0, -24(s0)
	j	.LBB7_6
.LBB7_6:                                #   in Loop: Header=BB7_1 Depth=1
	lw	a0, -32(s0)
	addi	a0, a0, 1
	sw	a0, -32(s0)
	j	.LBB7_1
.LBB7_7:                                # %.loopexit
	j	.LBB7_8
.LBB7_8:
	lw	a0, -24(s0)
	lw	s0, 24(sp)
	lw	ra, 28(sp)
	addi	sp, sp, 32
	ret
.Lfunc_end7:
	.size	_string.parseInt, .Lfunc_end7-_string.parseInt
	.cfi_endproc
                                        # -- End function
	.globl	_string.length                  # -- Begin function _string.length
	.p2align	2
	.type	_string.length,@function
length:                                 # @_string.length
	.cfi_startproc
# %bb.0:
	addi	sp, sp, -16
	.cfi_def_cfa_offset 16
	sw	ra, 12(sp)
	sw	s0, 8(sp)
	.cfi_offset ra, -4
	.cfi_offset s0, -8
	addi	s0, sp, 16
	.cfi_def_cfa s0, 0
	sw	a0, -16(s0)
	lw	a0, -16(s0)
	call	strlen
	lw	s0, 8(sp)
	lw	ra, 12(sp)
	addi	sp, sp, 16
	ret
.Lfunc_end8:
	.size	_string.length, .Lfunc_end8-_string.length
	.cfi_endproc
                                        # -- End function
	.globl	_string.substring               # -- Begin function _string.substring
	.p2align	2
	.type	_string.substring,@function
_string.substring:                              # @_string.substring
	.cfi_startproc
# %bb.0:
	addi	sp, sp, -32
	.cfi_def_cfa_offset 32
	sw	ra, 28(sp)
	sw	s0, 24(sp)
	.cfi_offset ra, -4
	.cfi_offset s0, -8
	addi	s0, sp, 32
	.cfi_def_cfa s0, 0
	sw	a0, -16(s0)
	sw	a1, -20(s0)
	sw	a2, -24(s0)
	lw	a0, -24(s0)
	lw	a1, -20(s0)
	sub	a0, a0, a1
	addi	a0, a0, 1
	srai	a1, a0, 31
	call	malloc
	sw	a0, -32(s0)
	lw	a0, -16(s0)
	lw	a1, -20(s0)
	add	a0, a0, a1
	lw	a1, -32(s0)
	call	strcpy
	lw	a0, -32(s0)
	lw	a1, -24(s0)
	lw	a2, -20(s0)
	sub	a1, a1, a2
	add	a0, a0, a1
	sb	zero, 0(a0)
	lw	a0, -32(s0)
	lw	s0, 24(sp)
	lw	ra, 28(sp)
	addi	sp, sp, 32
	ret
.Lfunc_end9:
	.size	_string.substring, .Lfunc_end9-_string.substring
	.cfi_endproc
                                        # -- End function
	.globl	_string.ord                     # -- Begin function _string.ord
	.p2align	2
	.type	_string.ord,@function
_string.ord:                                    # @_string.ord
	.cfi_startproc
# %bb.0:
	addi	sp, sp, -32
	.cfi_def_cfa_offset 32
	sw	ra, 28(sp)
	sw	s0, 24(sp)
	.cfi_offset ra, -4
	.cfi_offset s0, -8
	addi	s0, sp, 32
	.cfi_def_cfa s0, 0
	sw	a0, -16(s0)
	sw	a1, -20(s0)
	lw	a0, -16(s0)
	lw	a1, -20(s0)
	add	a0, a0, a1
	lb	a0, 0(a0)
	lw	s0, 24(sp)
	lw	ra, 28(sp)
	addi	sp, sp, 32
	ret
.Lfunc_end10:
	.size	_string.ord, .Lfunc_end10-_string.ord
	.cfi_endproc
                                        # -- End function
	.globl	strcopy                 # -- Begin function strcopy
	.p2align	2
	.type	strcopy,@function
strcopy:                                # @strcopy
	.cfi_startproc
# %bb.0:
	addi	sp, sp, -32
	.cfi_def_cfa_offset 32
	sw	ra, 28(sp)
	sw	s0, 24(sp)
	.cfi_offset ra, -4
	.cfi_offset s0, -8
	addi	s0, sp, 32
	.cfi_def_cfa s0, 0
	sw	a0, -16(s0)
	lw	a0, -16(s0)
	call	strlen
	sw	a0, -20(s0)
	lw	a0, -20(s0)
	addi	a0, a0, 1
	srai	a1, a0, 31
	call	malloc
	sw	a0, -24(s0)
	lw	a0, -16(s0)
	lw	a1, -24(s0)
	call	strcpy
	lw	a0, -24(s0)
	lw	a1, -20(s0)
	add	a0, a0, a1
	sb	zero, 0(a0)
	lw	a0, -24(s0)
	lw	s0, 24(sp)
	lw	ra, 28(sp)
	addi	sp, sp, 32
	ret
.Lfunc_end11:
	.size	strcopy, .Lfunc_end11-strcopy
	.cfi_endproc
                                        # -- End function
	.globl	append                  # -- Begin function append
	.p2align	2
	.type	append,@function
append:                                 # @append
	.cfi_startproc
# %bb.0:
	addi	sp, sp, -48
	.cfi_def_cfa_offset 48
	sw	ra, 44(sp)
	sw	s0, 40(sp)
	.cfi_offset ra, -4
	.cfi_offset s0, -8
	addi	s0, sp, 48
	.cfi_def_cfa s0, 0
	sw	a0, -16(s0)
	sw	a1, -24(s0)
	lw	a0, -16(s0)
	call	strlen
	sw	a0, -28(s0)
	lw	a0, -24(s0)
	call	strlen
	sw	a0, -32(s0)
	lw	a0, -32(s0)
	lw	a1, -28(s0)
	add	a0, a0, a1
	addi	a0, a0, 1
	srai	a1, a0, 31
	call	malloc
	sw	a0, -40(s0)
	lw	a0, -16(s0)
	lw	a1, -40(s0)
	call	strcpy
	lw	a0, -24(s0)
	lw	a1, -40(s0)
	lw	a2, -28(s0)
	add	a1, a1, a2
	call	strcpy
	lw	a0, -40(s0)
	lw	a1, -32(s0)
	lw	a2, -28(s0)
	add	a1, a1, a2
	add	a0, a0, a1
	sb	zero, 0(a0)
	lw	a0, -40(s0)
	lw	s0, 40(sp)
	lw	ra, 44(sp)
	addi	sp, sp, 48
	ret
.Lfunc_end12:
	.size	append, .Lfunc_end12-append
	.cfi_endproc
                                        # -- End function
	.globl	strcomp                 # -- Begin function strcomp
	.p2align	2
	.type	strcomp,@function
strcomp:                                # @strcomp
	.cfi_startproc
# %bb.0:
	addi	sp, sp, -32
	.cfi_def_cfa_offset 32
	sw	ra, 28(sp)
	sw	s0, 24(sp)
	.cfi_offset ra, -4
	.cfi_offset s0, -8
	addi	s0, sp, 32
	.cfi_def_cfa s0, 0
	sw	a0, -16(s0)
	sw	a1, -24(s0)
	lw	a0, -16(s0)
	lw	a1, -24(s0)
	call	strcmp
	lw	s0, 24(sp)
	lw	ra, 28(sp)
	addi	sp, sp, 32
	ret
.Lfunc_end13:
	.size	strcomp, .Lfunc_end13-strcomp
	.cfi_endproc
                                        # -- End function
	.type	.L.str,@object          # @.str
	.section	.rodata.str1.1,"aMS",@progbits,1
.L.str:
	.asciz	"%d"
	.size	.L.str, 3

	.type	.L.str.1,@object        # @.str.1
.L.str.1:
	.asciz	"%s"
	.size	.L.str.1, 3

	.type	.L.str.2,@object        # @.str.2
.L.str.2:
	.asciz	"%s\n"
	.size	.L.str.2, 4

	.type	.L.str.3,@object        # @.str.3
.L.str.3:
	.asciz	"%d\n"
	.size	.L.str.3, 4

	.ident	"clang version 10.0.0-4ubuntu1 "
	.section	".note.GNU-stack","",@progbits
