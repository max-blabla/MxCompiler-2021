	.text
	.file	"test.cpp"
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
	lui	a0, %hi(BBB)
	addi	a0, a0, %lo(BBB)
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
	lw	a0, -16(s0)
	addi	a1, zero, 1
	sw	a1, 0(a0)
	addi	a1, zero, 2
	sw	a1, 4(a0)
	addi	a1, zero, 3
	sw	a1, 8(a0)
	addi	a0, a0, 60
	call	_ZN2AAC2Ev
	lw	s0, 8(sp)
	lw	ra, 12(sp)
	addi	sp, sp, 16
	ret
.Lfunc_end1:
	.size	_ZN1AC2Ev, .Lfunc_end1-_ZN1AC2Ev
	.cfi_endproc
                                        # -- End function
	.section	.text.startup,"ax",@progbits
	.p2align	2               # -- Begin function __cxx_global_var_init.1
	.type	__cxx_global_var_init.1,@function
__cxx_global_var_init.1:                # @__cxx_global_var_init.1
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
	lui	a0, %hi(BBBB)
	addi	a0, a0, %lo(BBBB)
	call	_ZN2AAC2Ev
	lw	s0, 8(sp)
	lw	ra, 12(sp)
	addi	sp, sp, 16
	ret
.Lfunc_end2:
	.size	__cxx_global_var_init.1, .Lfunc_end2-__cxx_global_var_init.1
	.cfi_endproc
                                        # -- End function
	.section	.text._ZN2AAC2Ev,"axG",@progbits,_ZN2AAC2Ev,comdat
	.weak	_ZN2AAC2Ev              # -- Begin function _ZN2AAC2Ev
	.p2align	2
	.type	_ZN2AAC2Ev,@function
_ZN2AAC2Ev:                             # @_ZN2AAC2Ev
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
	addi	a1, zero, 44
	sw	a1, 0(a0)
	addi	a1, zero, 2
	sw	a1, 4(a0)
	addi	a1, zero, 3
	sw	a1, 8(a0)
	lw	s0, 8(sp)
	lw	ra, 12(sp)
	addi	sp, sp, 16
	ret
.Lfunc_end3:
	.size	_ZN2AAC2Ev, .Lfunc_end3-_ZN2AAC2Ev
	.cfi_endproc
                                        # -- End function
	.text
	.globl	_Z1fiib                 # -- Begin function _Z1fiib
	.p2align	2
	.type	_Z1fiib,@function
_Z1fiib:                                # @_Z1fiib
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
	sw	a0, -12(s0)
	sw	a1, -16(s0)
	sb	a2, -17(s0)
	addi	a0, zero, 2
	sw	a0, -12(s0)
	lw	s0, 24(sp)
	lw	ra, 28(sp)
	addi	sp, sp, 32
	ret
.Lfunc_end4:
	.size	_Z1fiib, .Lfunc_end4-_Z1fiib
	.cfi_endproc
                                        # -- End function
	.globl	main                    # -- Begin function main
	.p2align	2
	.type	main,@function
main:                                   # @main
	.cfi_startproc
# %bb.0:
	addi	sp, sp, -144
	.cfi_def_cfa_offset 144
	sw	ra, 140(sp)
	sw	s0, 136(sp)
	.cfi_offset ra, -4
	.cfi_offset s0, -8
	addi	s0, sp, 144
	.cfi_def_cfa s0, 0
	addi	a0, s0, -128
	call	_ZN1AC2Ev
	lui	a0, %hi(aaaaaaaaa)
	addi	a1, zero, 3
	sw	a1, %lo(aaaaaaaaa)(a0)
	lw	a0, %lo(aaaaaaaaa)(a0)
	lui	a1, %hi(ss)
	sw	a0, %lo(ss)(a1)
	lw	a0, %lo(ss)(a1)
	sw	a0, -132(s0)
	lw	a0, %lo(ss)(a1)
	lui	a1, %hi(dsd)
	sw	a0, %lo(dsd)(a1)
	mv	a0, zero
	lw	s0, 136(sp)
	lw	ra, 140(sp)
	addi	sp, sp, 144
	ret
.Lfunc_end5:
	.size	main, .Lfunc_end5-main
	.cfi_endproc
                                        # -- End function
	.section	.text.startup,"ax",@progbits
	.p2align	2               # -- Begin function _GLOBAL__sub_I_test.cpp
	.type	_GLOBAL__sub_I_test.cpp,@function
_GLOBAL__sub_I_test.cpp:                # @_GLOBAL__sub_I_test.cpp
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
	call	__cxx_global_var_init.1
	lw	s0, 8(sp)
	lw	ra, 12(sp)
	addi	sp, sp, 16
	ret
.Lfunc_end6:
	.size	_GLOBAL__sub_I_test.cpp, .Lfunc_end6-_GLOBAL__sub_I_test.cpp
	.cfi_endproc
                                        # -- End function
	.type	aaaaaaaaa,@object       # @aaaaaaaaa
	.section	.sbss,"aw",@nobits
	.globl	aaaaaaaaa
	.p2align	2
aaaaaaaaa:
	.word	0                       # 0x0
	.size	aaaaaaaaa, 4

	.type	ss,@object              # @ss
	.globl	ss
	.p2align	2
ss:
	.word	0                       # 0x0
	.size	ss, 4

	.type	dsd,@object             # @dsd
	.bss
	.globl	dsd
	.p2align	4
dsd:
	.zero	40
	.size	dsd, 40

	.type	BBB,@object             # @BBB
	.globl	BBB
	.p2align	2
BBB:
	.zero	120
	.size	BBB, 120

	.type	BBBB,@object            # @BBBB
	.globl	BBBB
	.p2align	2
BBBB:
	.zero	60
	.size	BBBB, 60

	.section	.init_array,"aw",@init_array
	.p2align	2
	.word	_GLOBAL__sub_I_test.cpp
	.ident	"clang version 10.0.0-4ubuntu1 "
	.section	".note.GNU-stack","",@progbits
