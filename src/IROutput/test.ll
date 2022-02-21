
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

define i32 @main(){
Start2:
	call void @_global._init()
	%main.1 = alloca i32
	%main.2 = alloca i32
	%main.3 = alloca i32
	store null %main.5, i32* %main.3
	br End3
End3:
	%main.8 = load i32, i32* %main.1
	ret i32 %main.8
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
