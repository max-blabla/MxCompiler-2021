; ModuleID = 'test.cpp'
source_filename = "test.cpp"
target datalayout = "e-m:e-p270:32:32-p271:32:32-p272:64:64-i64:64-f80:128-n8:16:32:64-S128"
target triple = "x86_64-pc-linux-gnu"

%class.A = type { i32, i32, i32, [12 x i32], %class.AA }
%class.AA = type { i32, i32, i32, [12 x i32] }

$_ZN1AC2Ev = comdat any

$_ZN2AAC2Ev = comdat any

@aaaaaaaaa = dso_local global i32 0, align 4
@ss = dso_local global i32 0, align 4
@dsd = dso_local global [10 x i32] zeroinitializer, align 16
@BBB = dso_local global %class.A zeroinitializer, align 4
@BBBB = dso_local global %class.AA zeroinitializer, align 4
@llvm.global_ctors = appending global [1 x { i32, void ()*, i8* }] [{ i32, void ()*, i8* } { i32 65535, void ()* @_GLOBAL__sub_I_test.cpp, i8* null }]

; Function Attrs: noinline uwtable
define internal void @__cxx_global_var_init() #0 section ".text.startup" {
  call void @_ZN1AC2Ev(%class.A* @BBB) #3
  ret void
}

; Function Attrs: noinline nounwind optnone uwtable
define linkonce_odr dso_local void @_ZN1AC2Ev(%class.A* %0) unnamed_addr #1 comdat align 2 {
  %2 = alloca %class.A*, align 8
  store %class.A* %0, %class.A** %2, align 8
  %3 = load %class.A*, %class.A** %2, align 8
  %4 = getelementptr inbounds %class.A, %class.A* %3, i32 0, i32 0
  store i32 1, i32* %4, align 4
  %5 = getelementptr inbounds %class.A, %class.A* %3, i32 0, i32 1
  store i32 2, i32* %5, align 4
  %6 = getelementptr inbounds %class.A, %class.A* %3, i32 0, i32 2
  store i32 3, i32* %6, align 4
  %7 = getelementptr inbounds %class.A, %class.A* %3, i32 0, i32 4
  call void @_ZN2AAC2Ev(%class.AA* %7) #3
  ret void
}

; Function Attrs: noinline uwtable
define internal void @__cxx_global_var_init.1() #0 section ".text.startup" {
  call void @_ZN2AAC2Ev(%class.AA* @BBBB) #3
  ret void
}

; Function Attrs: noinline nounwind optnone uwtable
define linkonce_odr dso_local void @_ZN2AAC2Ev(%class.AA* %0) unnamed_addr #1 comdat align 2 {
  %2 = alloca %class.AA*, align 8
  store %class.AA* %0, %class.AA** %2, align 8
  %3 = load %class.AA*, %class.AA** %2, align 8
  %4 = getelementptr inbounds %class.AA, %class.AA* %3, i32 0, i32 0
  store i32 44, i32* %4, align 4
  %5 = getelementptr inbounds %class.AA, %class.AA* %3, i32 0, i32 1
  store i32 2, i32* %5, align 4
  %6 = getelementptr inbounds %class.AA, %class.AA* %3, i32 0, i32 2
  store i32 3, i32* %6, align 4
  ret void
}

; Function Attrs: noinline nounwind optnone uwtable
define dso_local void @_Z1fiib(i32 %0, i32 %1, i1 zeroext %2) #1 {
  %4 = alloca i32, align 4
  %5 = alloca i32, align 4
  %6 = alloca i8, align 1
  store i32 %0, i32* %4, align 4
  store i32 %1, i32* %5, align 4
  %7 = zext i1 %2 to i8
  store i8 %7, i8* %6, align 1
  store i32 2, i32* %4, align 4
  ret void
}

; Function Attrs: noinline norecurse nounwind optnone uwtable
define dso_local i32 @main() #2 {
  %1 = alloca %class.A, align 4
  %2 = alloca i32, align 4
  call void @_ZN1AC2Ev(%class.A* %1) #3
  store i32 3, i32* @aaaaaaaaa, align 4
  %3 = load i32, i32* @aaaaaaaaa, align 4
  store i32 %3, i32* @ss, align 4
  %4 = load i32, i32* @ss, align 4
  store i32 %4, i32* %2, align 4
  %5 = load i32, i32* @ss, align 4
  store i32 %5, i32* getelementptr inbounds ([10 x i32], [10 x i32]* @dsd, i64 0, i64 0), align 16
  ret i32 0
}

; Function Attrs: noinline uwtable
define internal void @_GLOBAL__sub_I_test.cpp() #0 section ".text.startup" {
  call void @__cxx_global_var_init()
  call void @__cxx_global_var_init.1()
  ret void
}

attributes #0 = { noinline uwtable "correctly-rounded-divide-sqrt-fp-math"="false" "disable-tail-calls"="false" "frame-pointer"="all" "less-precise-fpmad"="false" "min-legal-vector-width"="0" "no-infs-fp-math"="false" "no-jump-tables"="false" "no-nans-fp-math"="false" "no-signed-zeros-fp-math"="false" "no-trapping-math"="false" "stack-protector-buffer-size"="8" "target-cpu"="x86-64" "target-features"="+cx8,+fxsr,+mmx,+sse,+sse2,+x87" "unsafe-fp-math"="false" "use-soft-float"="false" }
attributes #1 = { noinline nounwind optnone uwtable "correctly-rounded-divide-sqrt-fp-math"="false" "disable-tail-calls"="false" "frame-pointer"="all" "less-precise-fpmad"="false" "min-legal-vector-width"="0" "no-infs-fp-math"="false" "no-jump-tables"="false" "no-nans-fp-math"="false" "no-signed-zeros-fp-math"="false" "no-trapping-math"="false" "stack-protector-buffer-size"="8" "target-cpu"="x86-64" "target-features"="+cx8,+fxsr,+mmx,+sse,+sse2,+x87" "unsafe-fp-math"="false" "use-soft-float"="false" }
attributes #2 = { noinline norecurse nounwind optnone uwtable "correctly-rounded-divide-sqrt-fp-math"="false" "disable-tail-calls"="false" "frame-pointer"="all" "less-precise-fpmad"="false" "min-legal-vector-width"="0" "no-infs-fp-math"="false" "no-jump-tables"="false" "no-nans-fp-math"="false" "no-signed-zeros-fp-math"="false" "no-trapping-math"="false" "stack-protector-buffer-size"="8" "target-cpu"="x86-64" "target-features"="+cx8,+fxsr,+mmx,+sse,+sse2,+x87" "unsafe-fp-math"="false" "use-soft-float"="false" }
attributes #3 = { nounwind }

!llvm.module.flags = !{!0}
!llvm.ident = !{!1}

!0 = !{i32 1, !"wchar_size", i32 4}
!1 = !{!"clang version 10.0.0-4ubuntu1 "}
