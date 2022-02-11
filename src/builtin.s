	.text
	.globl	toString                # -- Begin function toString
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
	addi	a0, zero, 16
	mv	a1, zero
	call	malloc
	sw	a0, -16(s0)
	lw	a0, -16(s0)
	lw	a0, 4(a0)
	lw	a2, -12(s0)
	lui	a1, %hi(.L.str)
	addi	a1, a1, %lo(.L.str)
	call	sprintf
	lw	a0, -16(s0)
	lw	a0, 4(a0)
	sb	zero, 14(a0)
	lw	a0, -16(s0)
	lw	s0, 8(sp)
	lw	ra, 12(sp)
	addi	sp, sp, 16
	ret


	.globl	print                   # -- Begin function print
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
	lw	a0, -16(s0)
	lw	a1, 4(a0)
	lui	a0, %hi(.L.str.1)
	addi	a0, a0, %lo(.L.str.1)
	call	printf
	lw	s0, 8(sp)
	lw	ra, 12(sp)
	addi	sp, sp, 16
	ret


	.globl	println                 # -- Begin function println
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
	lw	a0, -16(s0)
	lw	a1, 4(a0)
	lui	a0, %hi(.L.str.2)
	addi	a0, a0, %lo(.L.str.2)
	call	printf
	lw	s0, 8(sp)
	lw	ra, 12(sp)
	addi	sp, sp, 16
	ret


	.globl	printInt                # -- Begin function printInt
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


	.globl	printlnInt              # -- Begin function printlnInt
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


	.globl	getString               # -- Begin function getString
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
	addi	a0, zero, 261
	mv	a1, zero
	call	malloc
	sw	a0, -16(s0)
	lw	a0, -16(s0)
	lw	a1, 4(a0)
	lui	a0, %hi(.L.str.2)
	addi	a0, a0, %lo(.L.str.2)
	call	scanf
	lw	a0, -16(s0)
	lw	a0, 4(a0)
	call	strlen
	lw	a1, -16(s0)
	sw	a0, 0(a1)
	lw	a0, -16(s0)
	lw	s0, 8(sp)
	lw	ra, 12(sp)
	addi	sp, sp, 16
	ret


	.globl	getInt                  # -- Begin function getInt
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
	lw	a1, -16(s0)
	lui	a0, %hi(.L.str.3)
	addi	a0, a0, %lo(.L.str.3)
	call	scanf
	lw	a0, -16(s0)
	lw	a0, 0(a0)
	lw	s0, 8(sp)
	lw	ra, 12(sp)
	addi	sp, sp, 16
	ret


	.globl	_string.parseInt                # -- Begin function parseInt
_string.parseInt:                               # @parseInt
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
	sw	zero, -20(s0)
	lw	a0, -16(s0)
	lw	a0, 0(a0)
	sw	a0, -24(s0)
	sw	zero, -32(s0)
	j	.LBB7_1
.LBB7_1:                                # =>This Inner Loop Header: Depth=1
	lw	a0, -32(s0)
	lw	a1, -24(s0)
	bge	a0, a1, .LBB7_7
	j	.LBB7_2
.LBB7_2:                                #   in Loop: Header=BB7_1 Depth=1
	lw	a0, -16(s0)
	lw	a0, 4(a0)
	lw	a1, -32(s0)
	add	a0, a0, a1
	lb	a0, 0(a0)
	addi	a0, a0, -48
	sw	a0, -28(s0)
	lw	a0, -28(s0)
	bltz	a0, .LBB7_4
	j	.LBB7_3
.LBB7_3:                                #   in Loop: Header=BB7_1 Depth=1
	lw	a0, -28(s0)
	addi	a1, zero, 10
	blt	a0, a1, .LBB7_5
	j	.LBB7_4
.LBB7_4:
	lw	a0, -20(s0)
	sw	a0, -12(s0)
	j	.LBB7_8
.LBB7_5:                                #   in Loop: Header=BB7_1 Depth=1
	lw	a0, -20(s0)
	addi	a1, zero, 10
	mul	a0, a0, a1
	lw	a1, -28(s0)
	add	a0, a0, a1
	sw	a0, -20(s0)
	j	.LBB7_6
.LBB7_6:                                #   in Loop: Header=BB7_1 Depth=1
	lw	a0, -32(s0)
	addi	a0, a0, 1
	sw	a0, -32(s0)
	j	.LBB7_1
.LBB7_7:                                # %.loopexit
	j	.LBB7_8
.LBB7_8:
	lw	a0, -12(s0)
	lw	s0, 24(sp)
	lw	ra, 28(sp)
	addi	sp, sp, 32
	ret


	.globl	_string.substring               # -- Begin function substring
_string.substring:                              # @substring
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
	sw	a0, -12(s0)
	sw	a1, -16(s0)
	sw	a2, -24(s0)
	lw	a0, -16(s0)
	lw	a1, -12(s0)
	sub	a0, a0, a1
	addi	a0, a0, 5
	srai	a1, a0, 31
	call	malloc
	sw	a0, -32(s0)
	lw	a0, -16(s0)
	lw	a1, -12(s0)
	sub	a0, a0, a1
	lw	a1, -32(s0)
	sw	a0, 0(a1)
	sw	zero, -36(s0)
	j	.LBB8_1
.LBB8_1:                                # =>This Inner Loop Header: Depth=1
	lw	a0, -36(s0)
	lw	a1, -32(s0)
	lw	a1, 0(a1)
	bge	a0, a1, .LBB8_4
	j	.LBB8_2
.LBB8_2:                                #   in Loop: Header=BB8_1 Depth=1
	lw	a0, -24(s0)
	lw	a0, 4(a0)
	lw	a1, -36(s0)
	lw	a2, -12(s0)
	add	a2, a1, a2
	add	a0, a0, a2
	lb	a0, 0(a0)
	lw	a2, -32(s0)
	lw	a2, 4(a2)
	add	a1, a2, a1
	sb	a0, 0(a1)
	j	.LBB8_3
.LBB8_3:                                #   in Loop: Header=BB8_1 Depth=1
	lw	a0, -36(s0)
	addi	a0, a0, 1
	sw	a0, -36(s0)
	j	.LBB8_1
.LBB8_4:
	lw	a0, -32(s0)
	lw	a0, 4(a0)
	lw	a1, -16(s0)
	lw	a2, -12(s0)
	sub	a1, a1, a2
	add	a0, a1, a0
	sb	zero, 4(a0)
	lw	a0, -32(s0)
	lw	s0, 40(sp)
	lw	ra, 44(sp)
	addi	sp, sp, 48
	ret

	.globl	_string.ord                     # -- Begin function ord
_string.ord:                                    # @ord
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
	sw	a1, -16(s0)
	lw	a0, -16(s0)
	lw	a0, 4(a0)
	lw	a1, -12(s0)
	add	a0, a0, a1
	lb	a0, 0(a0)
	lw	s0, 8(sp)
	lw	ra, 12(sp)
	addi	sp, sp, 16
	ret


	.globl	_string.length                  # -- Begin function length
_string.length:                                 # @length
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
	lw	a0, 0(a0)
	lw	s0, 8(sp)
	lw	ra, 12(sp)
	addi	sp, sp, 16
	ret


	.globl	append                  # -- Begin function append
append:                                 # @append
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
	lw	a0, 0(a0)
	lw	a1, -24(s0)
	lw	a1, 0(a1)
	add	a0, a0, a1
	addi	a0, a0, 5
	srai	a1, a0, 31
	call	malloc
	sw	a0, -32(s0)
	lw	a0, -16(s0)
	lw	a0, 0(a0)
	lw	a1, -24(s0)
	lw	a1, 0(a1)
	add	a0, a0, a1
	lw	a1, -32(s0)
	sw	a0, 0(a1)
	lw	a0, -32(s0)
	lw	a0, 4(a0)
	lw	a1, -16(s0)
	lw	a1, 4(a1)
	call	strcpy
	lw	a0, -32(s0)
	lw	a0, 4(a0)
	lw	a1, -16(s0)
	lw	a1, 0(a1)
	add	a0, a0, a1
	lw	a1, -24(s0)
	lw	a1, 4(a1)
	call	strcpy
	lw	a0, -32(s0)
	lw	a0, 4(a0)
	lw	a1, -16(s0)
	lw	a1, 0(a1)
	lw	a2, -24(s0)
	lw	a2, 0(a2)
	add	a1, a1, a2
	add	a0, a1, a0
	sb	zero, 4(a0)
	lw	a0, -32(s0)
	lw	s0, 24(sp)
	lw	ra, 28(sp)
	addi	sp, sp, 32
	ret

	.globl	strcopy                 # -- Begin function strcopy
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
	sw	a1, -24(s0)
	lw	a0, -24(s0)
	lw	a0, 0(a0)
	addi	a0, a0, 5
	srai	a1, a0, 31
	call	malloc
	sw	a0, -16(s0)
	lw	a0, -24(s0)
	lw	a0, 0(a0)
	lw	a1, -16(s0)
	sw	a0, 0(a1)
	lw	a0, -16(s0)
	lw	a0, 4(a0)
	lw	a1, -24(s0)
	lw	a1, 4(a1)
	call	strcpy
	lw	a0, -16(s0)
	lw	a0, 4(a0)
	lw	a1, -24(s0)
	lw	a1, 0(a1)
	add	a0, a1, a0
	sb	zero, 4(a0)
	lw	a0, -16(s0)
	lw	s0, 24(sp)
	lw	ra, 28(sp)
	addi	sp, sp, 32
	ret

	.globl	strcomp                 # -- Begin function strcomp
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
	lw	a0, 4(a0)
	lw	a1, -24(s0)
	lw	a1, 4(a1)
	call	strcmp
	lw	s0, 24(sp)
	lw	ra, 28(sp)
	addi	sp, sp, 32
	ret

	.data
.L.str:
	.asciz	"%d"
	.size	.L.str, 3

	.data
.L.str.1:
	.asciz	"%s"
	.size	.L.str.1, 3

	.data
.L.str.2:
	.asciz	"%s\n"
	.size	.L.str.2, 4

	.data
.L.str.3:
	.asciz	"%d\n"
	.size	.L.str.3, 4

