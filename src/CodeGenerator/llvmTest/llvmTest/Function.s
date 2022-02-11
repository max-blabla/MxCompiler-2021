	.text
	.file	"Function.cpp"
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
	sw	s1, 4(sp)
	.cfi_offset ra, -4
	.cfi_offset s0, -8
	.cfi_offset s1, -12
	addi	s0, sp, 16
	.cfi_def_cfa s0, 0
	lui	a0, %hi(_ZStL8__ioinit)
	addi	s1, a0, %lo(_ZStL8__ioinit)
	mv	a0, s1
	call	_ZNSt8ios_base4InitC1Ev
	lui	a0, %hi(_ZNSt8ios_base4InitD1Ev)
	addi	a0, a0, %lo(_ZNSt8ios_base4InitD1Ev)
	lui	a1, %hi(__dso_handle)
	addi	a2, a1, %lo(__dso_handle)
	mv	a1, s1
	call	__cxa_atexit
	lw	s1, 4(sp)
	lw	s0, 8(sp)
	lw	ra, 12(sp)
	addi	sp, sp, 16
	ret
.Lfunc_end0:
	.size	__cxx_global_var_init, .Lfunc_end0-__cxx_global_var_init
	.cfi_endproc
                                        # -- End function
	.text
	.globl	_Z4foo1i                # -- Begin function _Z4foo1i
	.p2align	2
	.type	_Z4foo1i,@function
_Z4foo1i:                               # @_Z4foo1i
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
	lw	a0, -12(s0)
	lw	s0, 8(sp)
	lw	ra, 12(sp)
	addi	sp, sp, 16
	ret
.Lfunc_end1:
	.size	_Z4foo1i, .Lfunc_end1-_Z4foo1i
	.cfi_endproc
                                        # -- End function
	.globl	_Z4foo2i                # -- Begin function _Z4foo2i
	.p2align	2
	.type	_Z4foo2i,@function
_Z4foo2i:                               # @_Z4foo2i
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
	addi	a0, zero, 2
	sw	a0, -12(s0)
	lw	s0, 8(sp)
	lw	ra, 12(sp)
	addi	sp, sp, 16
	ret
.Lfunc_end2:
	.size	_Z4foo2i, .Lfunc_end2-_Z4foo2i
	.cfi_endproc
                                        # -- End function
	.globl	_Z4foo3v                # -- Begin function _Z4foo3v
	.p2align	2
	.type	_Z4foo3v,@function
_Z4foo3v:                               # @_Z4foo3v
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
	lw	s0, 8(sp)
	lw	ra, 12(sp)
	addi	sp, sp, 16
	ret
.Lfunc_end3:
	.size	_Z4foo3v, .Lfunc_end3-_Z4foo3v
	.cfi_endproc
                                        # -- End function
	.globl	_Z4foo4v                # -- Begin function _Z4foo4v
	.p2align	2
	.type	_Z4foo4v,@function
_Z4foo4v:                               # @_Z4foo4v
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
	addi	a0, zero, 32
	lw	s0, 8(sp)
	lw	ra, 12(sp)
	addi	sp, sp, 16
	ret
.Lfunc_end4:
	.size	_Z4foo4v, .Lfunc_end4-_Z4foo4v
	.cfi_endproc
                                        # -- End function
	.globl	_Z4foo5ii               # -- Begin function _Z4foo5ii
	.p2align	2
	.type	_Z4foo5ii,@function
_Z4foo5ii:                              # @_Z4foo5ii
	.cfi_startproc
# %bb.0:
	addi	sp, sp, -32
	.cfi_def_cfa_offset 32
	sw	ra, 28(sp)
	sw	s0, 24(sp)
	sw	s1, 20(sp)
	sw	s2, 16(sp)
	.cfi_offset ra, -4
	.cfi_offset s0, -8
	.cfi_offset s1, -12
	.cfi_offset s2, -16
	addi	s0, sp, 32
	.cfi_def_cfa s0, 0
	sw	a0, -20(s0)
	sw	a1, -24(s0)
	lw	s1, -20(s0)
	lw	s2, -24(s0)
	mv	a0, s1
	mv	a1, s2
	call	__mulsi3
	add	a0, a0, s1
	add	a0, a0, s2
	lw	s2, 16(sp)
	lw	s1, 20(sp)
	lw	s0, 24(sp)
	lw	ra, 28(sp)
	addi	sp, sp, 32
	ret
.Lfunc_end5:
	.size	_Z4foo5ii, .Lfunc_end5-_Z4foo5ii
	.cfi_endproc
                                        # -- End function
	.globl	_Z4foo6iiiiiiiiiiii     # -- Begin function _Z4foo6iiiiiiiiiiii
	.p2align	2
	.type	_Z4foo6iiiiiiiiiiii,@function
_Z4foo6iiiiiiiiiiii:                    # @_Z4foo6iiiiiiiiiiii
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
	lw	t0, 12(s0)
	lw	t0, 8(s0)
	lw	t0, 4(s0)
	lw	t0, 0(s0)
	sw	a0, -12(s0)
	sw	a1, -16(s0)
	sw	a2, -20(s0)
	sw	a3, -24(s0)
	sw	a4, -28(s0)
	sw	a5, -32(s0)
	sw	a6, -36(s0)
	sw	a7, -40(s0)
	lw	a0, -12(s0)
	lw	a1, -16(s0)
	add	a0, a0, a1
	lw	a1, -20(s0)
	add	a0, a0, a1
	lw	a1, -24(s0)
	add	a0, a0, a1
	lw	a1, -28(s0)
	add	a0, a0, a1
	lw	a1, -32(s0)
	add	a0, a0, a1
	lw	a1, -36(s0)
	add	a0, a0, a1
	lw	a1, -40(s0)
	add	a0, a0, a1
	lw	a1, 0(s0)
	add	a0, a0, a1
	lw	a1, 4(s0)
	add	a0, a0, a1
	lw	a1, 8(s0)
	add	a0, a0, a1
	lw	a1, 12(s0)
	add	a0, a0, a1
	lw	s0, 40(sp)
	lw	ra, 44(sp)
	addi	sp, sp, 48
	ret
.Lfunc_end6:
	.size	_Z4foo6iiiiiiiiiiii, .Lfunc_end6-_Z4foo6iiiiiiiiiiii
	.cfi_endproc
                                        # -- End function
	.globl	main                    # -- Begin function main
	.p2align	2
	.type	main,@function
main:                                   # @main
	.cfi_startproc
# %bb.0:
	addi	sp, sp, -32
	.cfi_def_cfa_offset 32
	sw	ra, 28(sp)
	sw	s0, 24(sp)
	sw	s1, 20(sp)
	.cfi_offset ra, -4
	.cfi_offset s0, -8
	.cfi_offset s1, -12
	addi	s0, sp, 32
	.cfi_def_cfa s0, 0
	sw	zero, -16(s0)
	addi	a0, zero, 44
	call	_Z4foo1i
	addi	a0, zero, 44
	call	_Z4foo2i
	call	_Z4foo3v
	call	_Z4foo4v
	addi	s1, zero, 1
	addi	a0, zero, 1
	call	_Z4foo1i
	mv	a1, a0
	addi	a0, zero, 32
	call	_Z4foo5ii
	sw	s1, 12(sp)
	sw	s1, 8(sp)
	sw	s1, 4(sp)
	sw	s1, 0(sp)
	addi	a0, zero, 1
	addi	a1, zero, 1
	addi	a2, zero, 1
	addi	a3, zero, 1
	addi	a4, zero, 1
	addi	a5, zero, 1
	addi	a6, zero, 1
	addi	a7, zero, 1
	call	_Z4foo6iiiiiiiiiiii
	lui	a0, %hi(.L.str)
	addi	a0, a0, %lo(.L.str)
	addi	a1, zero, 1
	call	printf
	mv	a0, zero
	lw	s1, 20(sp)
	lw	s0, 24(sp)
	lw	ra, 28(sp)
	addi	sp, sp, 32
	ret
.Lfunc_end7:
	.size	main, .Lfunc_end7-main
	.cfi_endproc
                                        # -- End function
	.section	.text.startup,"ax",@progbits
	.p2align	2               # -- Begin function _GLOBAL__sub_I_Function.cpp
	.type	_GLOBAL__sub_I_Function.cpp,@function
_GLOBAL__sub_I_Function.cpp:            # @_GLOBAL__sub_I_Function.cpp
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
.Lfunc_end8:
	.size	_GLOBAL__sub_I_Function.cpp, .Lfunc_end8-_GLOBAL__sub_I_Function.cpp
	.cfi_endproc
                                        # -- End function
	.type	_ZStL8__ioinit,@object  # @_ZStL8__ioinit
	.section	.sbss,"aw",@nobits
_ZStL8__ioinit:
	.zero	1
	.size	_ZStL8__ioinit, 1

	.hidden	__dso_handle
	.type	.L.str,@object          # @.str
	.section	.rodata.str1.1,"aMS",@progbits,1
.L.str:
	.asciz	"%d"
	.size	.L.str, 3

	.section	.init_array,"aw",@init_array
	.p2align	2
	.word	_GLOBAL__sub_I_Function.cpp
	.ident	"clang version 10.0.0-4ubuntu1 "
	.section	".note.GNU-stack","",@progbits
