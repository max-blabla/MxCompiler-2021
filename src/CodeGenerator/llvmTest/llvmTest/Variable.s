	.text
	.file	"Variable.cpp"
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
	lui	a0, %hi(ss)
	lw	a0, %lo(ss)(a0)
	lui	a1, %hi(s)
	lw	a1, %lo(s)(a1)
	add	a0, a0, a1
	lui	a1, %hi(ssss)
	sw	a0, %lo(ssss)(a1)
	lw	s0, 8(sp)
	lw	ra, 12(sp)
	addi	sp, sp, 16
	ret
.Lfunc_end0:
	.size	__cxx_global_var_init, .Lfunc_end0-__cxx_global_var_init
	.cfi_endproc
                                        # -- End function
	.text
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
	.cfi_offset ra, -4
	.cfi_offset s0, -8
	addi	s0, sp, 32
	.cfi_def_cfa s0, 0
	sw	zero, -12(s0)
	sw	zero, -20(s0)
	lw	a0, -16(s0)
	lw	a1, -20(s0)
	add	a0, a0, a1
	sw	a0, -24(s0)
	lui	a0, %hi(ss)
	lw	a0, %lo(ss)(a0)
	lw	a1, -24(s0)
	add	a0, a0, a1
	sw	a0, -28(s0)
	lui	a0, %hi(ssss)
	lw	a0, %lo(ssss)(a0)
	lw	a1, -28(s0)
	add	a0, a0, a1
	lw	s0, 24(sp)
	lw	ra, 28(sp)
	addi	sp, sp, 32
	ret
.Lfunc_end1:
	.size	main, .Lfunc_end1-main
	.cfi_endproc
                                        # -- End function
	.section	.text.startup,"ax",@progbits
	.p2align	2               # -- Begin function _GLOBAL__sub_I_Variable.cpp
	.type	_GLOBAL__sub_I_Variable.cpp,@function
_GLOBAL__sub_I_Variable.cpp:            # @_GLOBAL__sub_I_Variable.cpp
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
.Lfunc_end2:
	.size	_GLOBAL__sub_I_Variable.cpp, .Lfunc_end2-_GLOBAL__sub_I_Variable.cpp
	.cfi_endproc
                                        # -- End function
	.type	s,@object               # @s
	.section	.sbss,"aw",@nobits
	.globl	s
	.p2align	2
s:
	.word	0                       # 0x0
	.size	s, 4

	.type	ss,@object              # @ss
	.globl	ss
	.p2align	2
ss:
	.word	0                       # 0x0
	.size	ss, 4

	.type	sss,@object             # @sss
	.section	.sdata,"aw",@progbits
	.globl	sss
	.p2align	2
sss:
	.word	1                       # 0x1
	.size	sss, 4

	.type	ssss,@object            # @ssss
	.section	.sbss,"aw",@nobits
	.globl	ssss
	.p2align	2
ssss:
	.word	0                       # 0x0
	.size	ssss, 4

	.section	.init_array,"aw",@init_array
	.p2align	2
	.word	_GLOBAL__sub_I_Variable.cpp
	.ident	"clang version 10.0.0-4ubuntu1 "
	.section	".note.GNU-stack","",@progbits
