; ModuleID = 'Class.cpp'
source_filename = "Class.cpp"
target datalayout = "e-m:e-p270:32:32-p271:32:32-p272:64:64-i64:64-f80:128-n8:16:32:64-S128"
target triple = "x86_64-pc-linux-gnu"

%class.A = type { i32, i32, [100 x i32], i32* }

$_ZN1AC2Ev = comdat any

$_ZN1A4foo1Ev = comdat any

$_ZN1A4foo2Ei = comdat any

@a = dso_local global %class.A zeroinitializer, align 8
@llvm.global_ctors = appending global [1 x { i32, void ()*, i8* }] [{ i32, void ()*, i8* } { i32 65535, void ()* @_GLOBAL__sub_I_Class.cpp, i8* null }]

; Function Attrs: noinline uwtable
define internal void @__cxx_global_var_init() #0 section ".text.startup" {
  call void @_ZN1AC2Ev(%class.A* @a) #6
  ret void
}

; Function Attrs: noinline nounwind optnone uwtable
define linkonce_odr dso_local void @_ZN1AC2Ev(%class.A* %0) unnamed_addr #1 comdat align 2 {
  %2 = alloca %class.A*, align 8
  store %class.A* %0, %class.A** %2, align 8
  %3 = load %class.A*, %class.A** %2, align 8
  %4 = getelementptr inbounds %class.A, %class.A* %3, i32 0, i32 1
  store i32 0, i32* %4, align 4
  %5 = getelementptr inbounds %class.A, %class.A* %3, i32 0, i32 2
  %6 = getelementptr inbounds [100 x i32], [100 x i32]* %5, i64 0, i64 0
  store i32 0, i32* %6, align 4
  %7 = getelementptr inbounds i32, i32* %6, i64 1
  %8 = getelementptr inbounds i32, i32* %6, i64 100
  br label %9

9:                                                ; preds = %9, %1
  %10 = phi i32* [ %7, %1 ], [ %11, %9 ]
  store i32 0, i32* %10, align 4
  %11 = getelementptr inbounds i32, i32* %10, i64 1
  %12 = icmp eq i32* %11, %8
  br i1 %12, label %13, label %9

13:                                               ; preds = %9
  ret void
}

; Function Attrs: noinline norecurse optnone uwtable
define dso_local i32 @main() #2 {
  %1 = alloca i32, align 4
  %2 = alloca %class.A, align 8
  %3 = alloca %class.A*, align 8
  store i32 0, i32* %1, align 4
  call void @_ZN1AC2Ev(%class.A* %2) #6
  %4 = bitcast %class.A* %2 to i8*
  call void @llvm.memcpy.p0i8.p0i8.i64(i8* align 8 %4, i8* align 8 bitcast (%class.A* @a to i8*), i64 416, i1 false)
  call void @_ZN1A4foo1Ev(%class.A* %2)
  %5 = call i32 @_ZN1A4foo2Ei(%class.A* %2, i32 2)
  %6 = call i8* @_Znam(i64 51168) #7
  %7 = bitcast i8* %6 to %class.A*
  %8 = getelementptr inbounds %class.A, %class.A* %7, i64 123
  br label %9

9:                                                ; preds = %9, %0
  %10 = phi %class.A* [ %7, %0 ], [ %11, %9 ]
  call void @_ZN1AC2Ev(%class.A* %10) #6
  %11 = getelementptr inbounds %class.A, %class.A* %10, i64 1
  %12 = icmp eq %class.A* %11, %8
  br i1 %12, label %13, label %9

13:                                               ; preds = %9
  store %class.A* %7, %class.A** %3, align 8
  %14 = load i32, i32* %1, align 4
  ret i32 %14
}

; Function Attrs: argmemonly nounwind willreturn
declare void @llvm.memcpy.p0i8.p0i8.i64(i8* noalias nocapture writeonly, i8* noalias nocapture readonly, i64, i1 immarg) #3

; Function Attrs: noinline optnone uwtable
define linkonce_odr dso_local void @_ZN1A4foo1Ev(%class.A* %0) #4 comdat align 2 {
  %2 = alloca %class.A*, align 8
  store %class.A* %0, %class.A** %2, align 8
  %3 = load %class.A*, %class.A** %2, align 8
  %4 = call i8* @_Znam(i64 48) #7
  %5 = bitcast i8* %4 to i32*
  %6 = getelementptr inbounds %class.A, %class.A* %3, i32 0, i32 3
  store i32* %5, i32** %6, align 8
  ret void
}

; Function Attrs: noinline nounwind optnone uwtable
define linkonce_odr dso_local i32 @_ZN1A4foo2Ei(%class.A* %0, i32 %1) #1 comdat align 2 {
  %3 = alloca %class.A*, align 8
  %4 = alloca i32, align 4
  store %class.A* %0, %class.A** %3, align 8
  store i32 %1, i32* %4, align 4
  %5 = load %class.A*, %class.A** %3, align 8
  %6 = getelementptr inbounds %class.A, %class.A* %5, i32 0, i32 0
  %7 = load i32, i32* %6, align 8
  %8 = load i32, i32* %4, align 4
  %9 = add nsw i32 %7, %8
  ret i32 %9
}

; Function Attrs: nobuiltin
declare dso_local noalias i8* @_Znam(i64) #5

; Function Attrs: noinline uwtable
define internal void @_GLOBAL__sub_I_Class.cpp() #0 section ".text.startup" {
  call void @__cxx_global_var_init()
  ret void
}

attributes #0 = { noinline uwtable "correctly-rounded-divide-sqrt-fp-math"="false" "disable-tail-calls"="false" "frame-pointer"="all" "less-precise-fpmad"="false" "min-legal-vector-width"="0" "no-infs-fp-math"="false" "no-jump-tables"="false" "no-nans-fp-math"="false" "no-signed-zeros-fp-math"="false" "no-trapping-math"="false" "stack-protector-buffer-size"="8" "target-cpu"="x86-64" "target-features"="+cx8,+fxsr,+mmx,+sse,+sse2,+x87" "unsafe-fp-math"="false" "use-soft-float"="false" }
attributes #1 = { noinline nounwind optnone uwtable "correctly-rounded-divide-sqrt-fp-math"="false" "disable-tail-calls"="false" "frame-pointer"="all" "less-precise-fpmad"="false" "min-legal-vector-width"="0" "no-infs-fp-math"="false" "no-jump-tables"="false" "no-nans-fp-math"="false" "no-signed-zeros-fp-math"="false" "no-trapping-math"="false" "stack-protector-buffer-size"="8" "target-cpu"="x86-64" "target-features"="+cx8,+fxsr,+mmx,+sse,+sse2,+x87" "unsafe-fp-math"="false" "use-soft-float"="false" }
attributes #2 = { noinline norecurse optnone uwtable "correctly-rounded-divide-sqrt-fp-math"="false" "disable-tail-calls"="false" "frame-pointer"="all" "less-precise-fpmad"="false" "min-legal-vector-width"="0" "no-infs-fp-math"="false" "no-jump-tables"="false" "no-nans-fp-math"="false" "no-signed-zeros-fp-math"="false" "no-trapping-math"="false" "stack-protector-buffer-size"="8" "target-cpu"="x86-64" "target-features"="+cx8,+fxsr,+mmx,+sse,+sse2,+x87" "unsafe-fp-math"="false" "use-soft-float"="false" }
attributes #3 = { argmemonly nounwind willreturn }
attributes #4 = { noinline optnone uwtable "correctly-rounded-divide-sqrt-fp-math"="false" "disable-tail-calls"="false" "frame-pointer"="all" "less-precise-fpmad"="false" "min-legal-vector-width"="0" "no-infs-fp-math"="false" "no-jump-tables"="false" "no-nans-fp-math"="false" "no-signed-zeros-fp-math"="false" "no-trapping-math"="false" "stack-protector-buffer-size"="8" "target-cpu"="x86-64" "target-features"="+cx8,+fxsr,+mmx,+sse,+sse2,+x87" "unsafe-fp-math"="false" "use-soft-float"="false" }
attributes #5 = { nobuiltin "correctly-rounded-divide-sqrt-fp-math"="false" "disable-tail-calls"="false" "frame-pointer"="all" "less-precise-fpmad"="false" "no-infs-fp-math"="false" "no-nans-fp-math"="false" "no-signed-zeros-fp-math"="false" "no-trapping-math"="false" "stack-protector-buffer-size"="8" "target-cpu"="x86-64" "target-features"="+cx8,+fxsr,+mmx,+sse,+sse2,+x87" "unsafe-fp-math"="false" "use-soft-float"="false" }
attributes #6 = { nounwind }
attributes #7 = { builtin }

!llvm.module.flags = !{!0}
!llvm.ident = !{!1}

!0 = !{i32 1, !"wchar_size", i32 4}
!1 = !{!"clang version 10.0.0-4ubuntu1 "}
