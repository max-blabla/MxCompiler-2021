	.text
	.file	"Class.cpp"
	.section	.text.startup,"ax",@progbits
	.p2align	2               # -- Begin function __cxx_global_var_init
	.type	__cxx_global_var_init,@function
__cxx_global_var_init:                  # @__cxx_global_var_init
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
	lui	a0, %hi(a)
	addi	a0, a0, %lo(a)
	call	_ZN1AC2Ev
	lw	s0, 8(sp)
	lw	ra, 12(sp)
	addi	sp, sp, 16
	ret
.Lfunc_end0:
	.size	__cxx_global_var_init, .Lfunc_end0-__cxx_global_var_init
	.cfi_endproc
                                        # -- End function
	.section	.text._ZN1AC2Ev,"axG",@progbits,_ZN1AC2Ev,comdat
	.weak	_ZN1AC2Ev               # -- Begin function _ZN1AC2Ev
	.p2align	2
	.type	_ZN1AC2Ev,@function
_ZN1AC2Ev:                              # @_ZN1AC2Ev
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
	sw	zero, 4(a1)
	sw	zero, 8(a1)
	addi	a0, a1, 12
	addi	a1, a1, 408
	j	.LBB1_1
.LBB1_1:                                # =>This Inner Loop Header: Depth=1
	sw	zero, 0(a0)
	addi	a0, a0, 4
	bne	a0, a1, .LBB1_1
	j	.LBB1_2
.LBB1_2:
	lw	s0, 8(sp)
	lw	ra, 12(sp)
	addi	sp, sp, 16
	ret
.Lfunc_end1:
	.size	_ZN1AC2Ev, .Lfunc_end1-_ZN1AC2Ev
	.cfi_endproc
                                        # -- End function
	.text
	.globl	main                    # -- Begin function main
	.p2align	2
	.type	main,@function
main:                                   # @main
	.cfi_startproc
# %bb.0:
	addi	sp, sp, -448
	.cfi_def_cfa_offset 448
	sw	ra, 444(sp)
	sw	s0, 440(sp)
	sw	s1, 436(sp)
	sw	s2, 432(sp)
	sw	s3, 428(sp)
	.cfi_offset ra, -4
	.cfi_offset s0, -8
	.cfi_offset s1, -12
	.cfi_offset s2, -16
	.cfi_offset s3, -20
	addi	s0, sp, 448
	.cfi_def_cfa s0, 0
	sw	zero, -24(s0)
	addi	s1, s0, -440
	mv	a0, s1
	call	_ZN1AC2Ev
	lui	a0, %hi(a)
	addi	a1, a0, %lo(a)
	mv	a0, s1
	addi	a2, zero, 416
	call	memcpy
	mv	a0, s1
	call	_ZN1A4foo1Ev
	mv	a0, s1
	addi	a1, zero, 2
	call	_ZN1A4foo2Ei
	lui	s1, 12
	addi	a0, s1, 2016
	mv	a1, zero
	call	_Znam
	mv	s2, a0
	addi	a0, s1, 1524
	add	s3, s2, a0
	mv	s1, s2
	j	.LBB2_1
.LBB2_1:                                # =>This Inner Loop Header: Depth=1
	mv	a0, s1
	call	_ZN1AC2Ev
	addi	s1, s1, 412
	bne	s1, s3, .LBB2_1
	j	.LBB2_2
.LBB2_2:
	sw	s2, -448(s0)
	lw	a0, -24(s0)
	lw	s3, 428(sp)
	lw	s2, 432(sp)
	lw	s1, 436(sp)
	lw	s0, 440(sp)
	lw	ra, 444(sp)
	addi	sp, sp, 448
	ret
.Lfunc_end2:
	.size	main, .Lfunc_end2-main
	.cfi_endproc
                                        # -- End function
	.section	.text._ZN1A4foo1Ev,"axG",@progbits,_ZN1A4foo1Ev,comdat
	.weak	_ZN1A4foo1Ev            # -- Begin function _ZN1A4foo1Ev
	.p2align	2
	.type	_ZN1A4foo1Ev,@function
_ZN1A4foo1Ev:                           # @_ZN1A4foo1Ev
	.cfi_startproc
# %bb.0:
	addi	sp, sp, -16
	.cfi_def_cfa_offset 16
	sw	ra, 12(sp)
	sw	s0, 8(sp)
	sw	s1, 4(sp)
	.cfi_offset ra, -4
	.cfi_offset s0, -8
	.cfi_offset s1, -12
	addi	s0, sp, 16
	.cfi_def_cfa s0, 0
	sw	a0, -16(s0)
	lw	s1, -16(s0)
	addi	a0, zero, 48
	mv	a1, zero
	call	_Znam
	sw	a0, 408(s1)
	lw	s1, 4(sp)
	lw	s0, 8(sp)
	lw	ra, 12(sp)
	addi	sp, sp, 16
	ret
.Lfunc_end3:
	.size	_ZN1A4foo1Ev, .Lfunc_end3-_ZN1A4foo1Ev
	.cfi_endproc
                                        # -- End function
	.section	.text._ZN1A4foo2Ei,"axG",@progbits,_ZN1A4foo2Ei,comdat
	.weak	_ZN1A4foo2Ei            # -- Begin function _ZN1A4foo2Ei
	.p2align	2
	.type	_ZN1A4foo2Ei,@function
_ZN1A4foo2Ei:                           # @_ZN1A4foo2Ei
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
	lw	a0, 0(a0)
	lw	a1, -20(s0)
	add	a0, a0, a1
	lw	s0, 24(sp)
	lw	ra, 28(sp)
	addi	sp, sp, 32
	ret
.Lfunc_end4:
	.size	_ZN1A4foo2Ei, .Lfunc_end4-_ZN1A4foo2Ei
	.cfi_endproc
                                        # -- End function
	.section	.text.startup,"ax",@progbits
	.p2align	2               # -- Begin function _GLOBAL__sub_I_Class.cpp
	.type	_GLOBAL__sub_I_Class.cpp,@function
_GLOBAL__sub_I_Class.cpp:               # @_GLOBAL__sub_I_Class.cpp
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
	call	__cxx_global_var_init
	lw	s0, 8(sp)
	lw	ra, 12(sp)
	addi	sp, sp, 16
	ret
.Lfunc_end5:
	.size	_GLOBAL__sub_I_Class.cpp, .Lfunc_end5-_GLOBAL__sub_I_Class.cpp
	.cfi_endproc
                                        # -- End function
	.type	a,@object               # @a
	.bss
	.globl	a
	.p2align	3
a:
	.zero	412
	.size	a, 412

	.section	.init_array,"aw",@init_array
	.p2align	2
	.word	_GLOBAL__sub_I_Class.cpp
	.ident	"clang version 10.0.0-4ubuntu1 "
	.section	".note.GNU-stack","",@progbits
