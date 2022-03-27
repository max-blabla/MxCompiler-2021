
define void @_global._init(){
Start0:
	br End1
End1:
	ret void
}

link void @_global.print(_string %str);

link void @_global.println(_string %str);

link _string @_global.strcopy(_string %str);

link _string @_global.toString(i32 %num);

link void @_global.printInt(i32 %num);

link void @_global.printlnInt(i32 %num);

link _string @_global.getString();

link i32 @_global.getInt();

link _string @_global.append(_string %dst, _string %src);

link void @_global.strcomp(_string %dst, _string %src);

link i32 @_global.length(_string %this);

link i32 @_string.parseInt(_string %this);

link _string @_string.substring(_string %this, i32 %l, i32 %r);

link i32 @_string.ord(_string %this, i32 %index);

define i32 @_global.main(){
Start3:
	%_global.main.0 = alloca i32
	call void @_global._init()
	%_global.main.1 = alloca i32
	%_global.main.2 = add, i32 10, i32 0
	%_global.main.3 = load i32, i32* %_global.main.1
	store i32 %_global.main.2, i32* %_global.main.1
	%_global.main.4 = load i32, i32* %_global.main.1
	%_global.main.5 = alloca i32
	%_global.main.6 = add, i32 0, i32 0
	%_global.main.7 = load i32, i32* %_global.main.5
	store i32 %_global.main.6, i32* %_global.main.5
	%_global.main.8 = load i32, i32* %_global.main.5
	%_global.main.9 = alloca i32
	%_global.main.10 = add, i32 1, i32 0
	%_global.main.11 = load i32, i32* %_global.main.9
	store i32 %_global.main.10, i32* %_global.main.9
	%_global.main.12 = load i32, i32* %_global.main.9
	br For-Condition4
For-Condition4:
	%_global.main.13 = load i32, i32* %_global.main.9
	%_global.main.14 = load i32, i32* %_global.main.1
	%_global.main.15 = icmp ule, i32 %_global.main.13, i32 %_global.main.14
	br i8 %_global.main.15 For-Body5, Succeed7
For-Body5:
	%_global.main.16 = load i32, i32* %_global.main.5
	%_global.main.17 = load i32, i32* %_global.main.9
	%_global.main.18 = add, i32 %_global.main.16, i32 %_global.main.17
	%_global.main.19 = load i32, i32* %_global.main.5
	store i32 %_global.main.18, i32* %_global.main.5
	%_global.main.20 = load i32, i32* %_global.main.5
	br For-Incr6
For-Incr6:
	%_global.main.21 = load i32, i32* %_global.main.9
	%_global.main.22 = load i32, i32* %_global.main.9
	%_global.main.23 = add, i32 %_global.main.22, i32 1
	store i32 %_global.main.23, i32* %_global.main.9
	br For-Condition4
Succeed7:
	%_global.main.24 = alloca i32
	%_global.main.25 = add, i32 1, i32 0
	%_global.main.26 = load i32, i32* %_global.main.24
	store i32 %_global.main.25, i32* %_global.main.24
	%_global.main.27 = load i32, i32* %_global.main.24
	br For-Condition8
For-Condition8:
	%_global.main.28 = load i32, i32* %_global.main.24
	%_global.main.29 = load i32, i32* %_global.main.1
	%_global.main.30 = icmp ule, i32 %_global.main.28, i32 %_global.main.29
	br i8 %_global.main.30 For-Body9, Succeed11
For-Body9:
	%_global.main.31 = load i32, i32* %_global.main.5
	%_global.main.32 = add, i32 10, i32 0
	%_global.main.33 = add, i32 %_global.main.31, i32 %_global.main.32
	%_global.main.34 = load i32, i32* %_global.main.24
	%_global.main.35 = add, i32 %_global.main.33, i32 %_global.main.34
	%_global.main.36 = load i32, i32* %_global.main.5
	store i32 %_global.main.35, i32* %_global.main.5
	%_global.main.37 = load i32, i32* %_global.main.5
	br For-Incr10
For-Incr10:
	%_global.main.38 = load i32, i32* %_global.main.24
	%_global.main.39 = load i32, i32* %_global.main.24
	%_global.main.40 = add, i32 %_global.main.39, i32 1
	store i32 %_global.main.40, i32* %_global.main.24
	br For-Condition8
Succeed11:
	%_global.main.41 = load i32, i32* %_global.main.5
	store i32 %_global.main.41, i32* %_global.main.0
	br End4
End4:
	%_global.main.42 = load i32, i32* %_global.main.0
	ret i32 %_global.main.42
}

link i32 @_global.length(_string %this);

link i32 @_string.parseInt(_string %this);

link _string @_string.substring(_string %this, i32 %l, i32 %r);

link i32 @_string.ord(_string %this, i32 %index);
