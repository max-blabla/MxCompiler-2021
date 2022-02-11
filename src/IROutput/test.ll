@_str3 = global zeroinitializer
@_str1_contents = global [3,"YES"]
@n = global zeroinitializer
@_str3_contents = global [2,"NO"]
@_str1 = global zeroinitializer

link i32* @malloc(i32 %size);

link void @println(_string** %out);

link void @print(_string** %out);

link _string** @toString(i32 %num);

link void @printInt(i32 %out);

link void @printlnInt(i32 %out);

link _string** @getString();

link i32 @getInt();

link _string** @strcopy(_string** %dst, _string** %src);

link void @append(_string** %dst, _string** %src);

link i32 @_string.length(_string** %this);

link _string** @_string.substring(_string** %this, i32 %l, i32 %r);

link i32 @_string.parseInt(_string** %this);

link i32 @_string.ord(_string** %this, i32 %index);

link i32 @strcomp(_string** %dst, _string** %src);

define i32 @main(){
Start2:
	call void @_global._init()
	%main.1 = alloca i32
	%main.5 = link i32 @getInt()
	store i32 %main.5, i32* @n
	br If-Condition4
If-Condition4:
	%main.10 = load i32, i32* @n
	%main.12 = add, i32 477, i32 0
	%main.13 = rem, i32 %main.10, i32 %main.12
	%main.14 = add, i32 0, i32 0
	%main.15 = icmp eq, i32 %main.13, i32 %main.14
	phi start i32 %main.9
	br i8 %main.9 Succeed9, Phi8
Phi8:
	%main.18 = load i32, i32* @n
	%main.20 = add, i32 74, i32 0
	%main.21 = rem, i32 %main.18, i32 %main.20
	%main.22 = add, i32 0, i32 0
	%main.23 = icmp eq, i32 %main.21, i32 %main.22
	phi start i32 %main.9
	%main.9 = or, i32 %main.15, i32 %main.9
	br i8 %main.9 Succeed11, Phi10
Phi10:
	%main.26 = load i32, i32* @n
	%main.28 = add, i32 47, i32 0
	%main.29 = rem, i32 %main.26, i32 %main.28
	%main.30 = add, i32 0, i32 0
	%main.31 = icmp eq, i32 %main.29, i32 %main.30
	phi start i32 %main.9
	%main.9 = or, i32 %main.23, i32 %main.9
	br i8 %main.9 Succeed13, Phi12
Phi12:
	%main.34 = load i32, i32* @n
	%main.36 = add, i32 4, i32 0
	%main.37 = rem, i32 %main.34, i32 %main.36
	%main.38 = add, i32 0, i32 0
	%main.39 = icmp eq, i32 %main.37, i32 %main.38
	phi start i32 %main.9
	%main.9 = or, i32 %main.31, i32 %main.9
	br i8 %main.9 Succeed15, Phi14
Phi14:
	%main.42 = load i32, i32* @n
	%main.44 = add, i32 7, i32 0
	%main.45 = rem, i32 %main.42, i32 %main.44
	%main.46 = add, i32 0, i32 0
	%main.47 = icmp eq, i32 %main.45, i32 %main.46
	%main.9 = or, i32 %main.39, i32 %main.47
	br Succeed15
Succeed15:
	phi end i32 %main.9
	br Succeed13
Succeed13:
	phi end i32 %main.9
	br Succeed11
Succeed11:
	phi end i32 %main.9
	br Succeed9
Succeed9:
	phi end i32 %main.9
	br i8 %main.9 If-True-Stmt5, If-False-Stmt6
If-True-Stmt5:
	%main.54 = getelementptr i8**, @_str1, i32 0, i32 4
	store i8* @_str1_contents, i8** %main.54
	link void @print(_string* @_str1)
	br Succeed7
If-False-Stmt6:
	%main.61 = getelementptr i8**, @_str3, i32 0, i32 4
	store i8* @_str3_contents, i8** %main.61
	link void @print(_string* @_str3)
	br Succeed7
Succeed7:
	br End3
End3:
	%main.63 = load i32, i32* %main.1
	ret i32 %main.63
}

define void @_global._init(){
Start0:
	br End1
End1:
	ret void
}

link i32 @_string.length(_string** %this);

link _string** @_string.substring(_string** %this, i32 %l, i32 %r);

link i32 @_string.parseInt(_string** %this);

link i32 @_string.ord(_string** %this, i32 %index);
