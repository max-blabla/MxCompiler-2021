
link i32* @malloc(i32 %size);

link void @println(_string %out);

link void @print(_string %out);

link _string @toString(i32 %num);

link void @printInt(i32 %out);

link void @printlnInt(i32 %out);

link _string @getString();

link i32 @getInt();

link _string @strcopy(_string %dst);

link void @append(_string %dst, _string %src);

link i32 @_string.length(_string %this);

link _string @_string.substring(_string %this, i32 %l, i32 %r);

link i32 @_string.parseInt(_string %this);

link i32 @_string.ord(_string %this, i32 %index);

link i32 @strcomp(_string %dst, _string %src);

define i32 @_global.tak(i32 %_global.tak.0, i32 %_global.tak.1, i32 %_global.tak.2){
Start2:
	%_global.tak.3 = alloca i32
	%_global.tak.4 = alloca i32
	store i32 %_global.tak.0, i32* %_global.tak.4
	%_global.tak.5 = alloca i32
	store i32 %_global.tak.1, i32* %_global.tak.5
	%_global.tak.6 = alloca i32
	store i32 %_global.tak.2, i32* %_global.tak.6
	br If-Condition8
If-Condition8:
	%_global.tak.7 = load i32, i32* %_global.tak.5
	%_global.tak.9 = load i32, i32* %_global.tak.4
	%_global.tak.11 = icmp ult, i32 %_global.tak.7, i32 %_global.tak.9
	br i8 %_global.tak.11 If-True-Stmt9, If-False-Stmt10
If-True-Stmt9:
	%_global.tak.12 = add, i32 1, i32 0
	%_global.tak.21 = load i32, i32* %_global.tak.4
	%_global.tak.23 = add, i32 1, i32 0
	%_global.tak.25 = sub, i32 %_global.tak.21, i32 %_global.tak.23
	%_global.tak.26 = load i32, i32* %_global.tak.5
	%_global.tak.28 = load i32, i32* %_global.tak.6
	%_global.tak.20 = call i32 @_global.tak(i32 %_global.tak.25, i32 %_global.tak.26, i32 %_global.tak.28)
	%_global.tak.34 = load i32, i32* %_global.tak.5
	%_global.tak.36 = add, i32 1, i32 0
	%_global.tak.38 = sub, i32 %_global.tak.34, i32 %_global.tak.36
	%_global.tak.39 = load i32, i32* %_global.tak.6
	%_global.tak.41 = load i32, i32* %_global.tak.4
	%_global.tak.33 = call i32 @_global.tak(i32 %_global.tak.38, i32 %_global.tak.39, i32 %_global.tak.41)
	%_global.tak.47 = load i32, i32* %_global.tak.6
	%_global.tak.49 = add, i32 1, i32 0
	%_global.tak.51 = sub, i32 %_global.tak.47, i32 %_global.tak.49
	%_global.tak.52 = load i32, i32* %_global.tak.4
	%_global.tak.54 = load i32, i32* %_global.tak.5
	%_global.tak.46 = call i32 @_global.tak(i32 %_global.tak.51, i32 %_global.tak.52, i32 %_global.tak.54)
	%_global.tak.16 = call i32 @_global.tak(i32 %_global.tak.20, i32 %_global.tak.33, i32 %_global.tak.46)
	%_global.tak.56 = add, i32 %_global.tak.12, i32 %_global.tak.16
	store i32 %_global.tak.56, i32* %_global.tak.3
	br End3
If-False-Stmt10:
	%_global.tak.57 = load i32, i32* %_global.tak.6
	store i32 %_global.tak.57, i32* %_global.tak.3
	br End3
Succeed11:
	br End3
End3:
	%_global.tak.59 = load i32, i32* %_global.tak.3
	ret i32 %_global.tak.59
}

define i32 @main(){
Start6:
	call void @_global._init()
	%main.1 = alloca i32
	%main.2 = alloca _A**
	%main.4 = add, i32 10, i32 0
	%main.6 = add, i32 9, i32 0
	%main.8 = mul, i32 %main.4, i32 4
	%main.10 = add, i32 %main.8, i32 4
	%main.11 = link _A** @malloc(i32 %main.10)
	store i32 %main.4, _A** %main.11
	phi start i32 %main.12
	%main.12 = add, i32 0, i32 0
	br NewArrayCondition12
NewArrayCondition12:
	%main.13 = icmp ult, i32 %main.12, i32 %main.4
	br i8 %main.13 NewArrayBody13, Succeed14
NewArrayBody13:
	%main.9 = getelement _A**, %main.11, i32 %main.12
	%main.14 = mul, i32 %main.6, i32 4
	%main.16 = add, i32 %main.14, i32 4
	%main.17 = link _A* @malloc(i32 %main.16)
	store i32 %main.6, _A* %main.17
	store _A* %main.17, _A** %main.9
	phi start i32 %main.18
	%main.18 = add, i32 0, i32 0
	%main.12 = add, i32 %main.12, i32 1
	br NewArrayCondition15
NewArrayCondition15:
	%main.19 = icmp ult, i32 %main.18, i32 %main.6
	br i8 %main.19 NewArrayBody16, Succeed17
NewArrayBody16:
	%main.18 = add, i32 %main.18, i32 1
	br NewArrayCondition15
Succeed17:
	phi end i32 %main.18
	br NewArrayCondition12
Succeed14:
	phi end i32 %main.12
	store _A** %main.11, _A*** %main.2
	%main.27 = add, i32 0, i32 0
	store i32 %main.27, i32* %main.1
	br End7
End7:
	%main.29 = load i32, i32* %main.1
	ret i32 %main.29
}

define void @_global._init(){
Start0:
	br End1
End1:
	ret void
}

link i32 @_string.length(_string %this);

link _string @_string.substring(_string %this, i32 %l, i32 %r);

link i32 @_string.parseInt(_string %this);

link i32 @_string.ord(_string %this, i32 %index);
%struct._A = type {  }

define void @_A._init(_A %_A._init.this){
Start4:
	%_A._init.0 = alloca _A
	store _A %_A._init.this, _A* %_A._init.0
	br End5
End5:
	ret void
}
