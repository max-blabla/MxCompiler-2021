@a = global zeroinitializer
@b = global zeroinitializer
@i = global zeroinitializer
@j = global zeroinitializer

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

define void @_global.printNum(i32 %_global.printNum.0){
Start4:
	%_global.printNum.1 = alloca i32
	store i32 %_global.printNum.0, i32* %_global.printNum.1
	%_global.printNum.10 = load i32, i32* %_global.printNum.1
	%_global.printNum.9 = link _string @toString(i32 %_global.printNum.10)
	link void @println(_string %_global.printNum.9)
	br End5
End5:
	ret void
}

define i32 @main(){
Start6:
	call void @_global._init()
	%main.1 = alloca i32
	%main.2 = add, i32 0, i32 0
	store i32 %main.2, i32* @i
	br For-Condition14
For-Condition14:
	%main.6 = load i32, i32* %i
	%main.8 = add, i32 4, i32 0
	%main.9 = icmp ult, i32 %main.6, i32 %main.8
	br i8 %main.9 For-Body15, Succeed17
For-Body15:
	br Basic18
Basic18:
	%main.11 = add, i32 11, i32 0
	%main.13 = mul, i32 %main.11, i32 4
	%main.15 = add, i32 %main.13, i32 4
	%main.16 = link i32* @malloc(i32 %main.13)
	store i32 %main.11, i32* %main.16
	phi start i32 %main.17
	%main.17 = add, i32 0, i32 0
	br NewArrayCondition20
NewArrayCondition20:
	%main.18 = icmp ult, i32 %main.17, i32 %main.11
	br i8 %main.18 NewArrayBody21, Succeed22
NewArrayBody21:
	%main.17 = add, i32 %main.17, i32 1
	br NewArrayCondition20
Succeed22:
	phi end i32 %main.17
	%main.24 = load i32**, i32*** %a
	%main.26 = load i32, i32* %i
	%main.29 = getelementptr i32**, %main.24, i32 %main.26
	store i32* %main.16, i32** %main.29
	br Succeed19
Succeed19:
	br For-Incr16
For-Incr16:
	%main.31 = load i32, i32* %i
	%main.33 = load i32, i32* %main.31
	%main.34 = add, i32 %main.33, i32 1
	store i32 %main.34, i32* %main.31
	br For-Condition14
Succeed17:
	%main.35 = add, i32 0, i32 0
	store i32 %main.35, i32* @i
	br For-Condition23
For-Condition23:
	%main.39 = load i32, i32* %i
	%main.41 = add, i32 4, i32 0
	%main.42 = icmp ult, i32 %main.39, i32 %main.41
	br i8 %main.42 For-Body24, Succeed26
For-Body24:
	br Basic27
Basic27:
	%main.43 = add, i32 0, i32 0
	store i32 %main.43, i32* @j
	br For-Condition29
For-Condition29:
	%main.47 = load i32, i32* %j
	%main.49 = add, i32 10, i32 0
	%main.50 = icmp ult, i32 %main.47, i32 %main.49
	br i8 %main.50 For-Body30, Succeed32
For-Body30:
	%main.51 = add, i32 888, i32 0
	%main.52 = load i32**, i32*** %a
	%main.54 = load i32, i32* %i
	%main.57 = getelementptr i32**, %main.52, i32 %main.54
	%main.56 = load i32*, i32** %main.57
	%main.58 = load i32, i32* %j
	%main.61 = getelementptr i32*, %main.56, i32 %main.58
	store i32 %main.51, i32* %main.61
	br For-Incr31
For-Incr31:
	%main.63 = load i32, i32* %j
	%main.65 = load i32, i32* %main.63
	%main.66 = add, i32 %main.65, i32 1
	store i32 %main.66, i32* %main.63
	br For-Condition29
Succeed32:
	br Succeed28
Succeed28:
	br For-Incr25
For-Incr25:
	%main.67 = load i32, i32* %i
	%main.69 = load i32, i32* %main.67
	%main.70 = add, i32 %main.69, i32 1
	store i32 %main.70, i32* %main.67
	br For-Condition23
Succeed26:
	%main.71 = add, i32 0, i32 0
	store i32 %main.71, i32* @i
	br For-Condition33
For-Condition33:
	%main.75 = load i32, i32* %i
	%main.77 = add, i32 5, i32 0
	%main.78 = icmp ult, i32 %main.75, i32 %main.77
	br i8 %main.78 For-Body34, Succeed36
For-Body34:
	br Basic37
Basic37:
	%main.81 = add, i32 8, i32 0
	%main.79 = link _rec @malloc(i32 %main.81)
	call void @_rec._init(_rec %main.79)
	%main.83 = load _rec*, _rec** %b
	%main.85 = load i32, i32* %i
	%main.88 = getelementptr _rec*, %main.83, i32 %main.85
	store _rec %main.79, _rec* %main.88
	%main.90 = add, i32 -1, i32 0
	%main.91 = load _rec*, _rec** %b
	%main.93 = load i32, i32* %i
	%main.96 = getelementptr _rec*, %main.91, i32 %main.93
	%main.101 = load _rec, _rec* %main.96
	%main.100 = getelementptr i32*, %main.101, i32 0, i32 0
	store i32 %main.90, i32* %main.100
	br Succeed38
Succeed38:
	br For-Incr35
For-Incr35:
	%main.103 = load i32, i32* %i
	%main.105 = load i32, i32* %main.103
	%main.106 = add, i32 %main.105, i32 1
	store i32 %main.106, i32* %main.103
	br For-Condition33
Succeed36:
	%main.111 = load i32**, i32*** %a
	%main.113 = add, i32 3, i32 0
	%main.116 = getelementptr i32**, %main.111, i32 %main.113
	%main.115 = load i32*, i32** %main.116
	%main.117 = add, i32 9, i32 0
	%main.120 = getelementptr i32*, %main.115, i32 %main.117
	%main.119 = load i32, i32* %main.120
	call void @_global.printNum(i32 %main.119)
	%main.121 = add, i32 0, i32 0
	store i32 %main.121, i32* @i
	br For-Condition39
For-Condition39:
	%main.125 = load i32, i32* %i
	%main.127 = add, i32 3, i32 0
	%main.128 = icmp ule, i32 %main.125, i32 %main.127
	br i8 %main.128 For-Body40, Succeed42
For-Body40:
	%main.129 = add, i32 0, i32 0
	store i32 %main.129, i32* @j
	br For-Condition43
For-Condition43:
	%main.133 = load i32, i32* %j
	%main.135 = add, i32 9, i32 0
	%main.136 = icmp ule, i32 %main.133, i32 %main.135
	br i8 %main.136 For-Body44, Succeed46
For-Body44:
	%main.137 = load i32, i32* %i
	%main.139 = add, i32 10, i32 0
	%main.140 = mul, i32 %main.137, i32 %main.139
	%main.141 = load i32, i32* %j
	%main.143 = add, i32 %main.140, i32 %main.141
	%main.144 = load i32**, i32*** %a
	%main.146 = load i32, i32* %i
	%main.149 = getelementptr i32**, %main.144, i32 %main.146
	%main.148 = load i32*, i32** %main.149
	%main.150 = load i32, i32* %j
	%main.153 = getelementptr i32*, %main.148, i32 %main.150
	store i32 %main.143, i32* %main.153
	br For-Incr45
For-Incr45:
	%main.155 = load i32, i32* %j
	%main.157 = load i32, i32* %main.155
	%main.158 = add, i32 %main.157, i32 1
	store i32 %main.158, i32* %main.155
	br For-Condition43
Succeed46:
	br For-Incr41
For-Incr41:
	%main.159 = load i32, i32* %i
	%main.161 = load i32, i32* %main.159
	%main.162 = add, i32 %main.161, i32 1
	store i32 %main.162, i32* %main.159
	br For-Condition39
Succeed42:
	%main.163 = add, i32 0, i32 0
	store i32 %main.163, i32* @i
	br For-Condition47
For-Condition47:
	%main.167 = load i32, i32* %i
	%main.169 = add, i32 3, i32 0
	%main.170 = icmp ule, i32 %main.167, i32 %main.169
	br i8 %main.170 For-Body48, Succeed50
For-Body48:
	%main.171 = add, i32 0, i32 0
	store i32 %main.171, i32* @j
	br For-Condition51
For-Condition51:
	%main.175 = load i32, i32* %j
	%main.177 = add, i32 9, i32 0
	%main.178 = icmp ule, i32 %main.175, i32 %main.177
	br i8 %main.178 For-Body52, Succeed54
For-Body52:
	%main.183 = load i32**, i32*** %a
	%main.185 = load i32, i32* %i
	%main.188 = getelementptr i32**, %main.183, i32 %main.185
	%main.187 = load i32*, i32** %main.188
	%main.189 = load i32, i32* %j
	%main.192 = getelementptr i32*, %main.187, i32 %main.189
	%main.191 = load i32, i32* %main.192
	call void @_global.printNum(i32 %main.191)
	br For-Incr53
For-Incr53:
	%main.193 = load i32, i32* %j
	%main.195 = load i32, i32* %main.193
	%main.196 = add, i32 %main.195, i32 1
	store i32 %main.196, i32* %main.193
	br For-Condition51
Succeed54:
	br For-Incr49
For-Incr49:
	%main.197 = load i32, i32* %i
	%main.199 = load i32, i32* %main.197
	%main.200 = add, i32 %main.199, i32 1
	store i32 %main.200, i32* %main.197
	br For-Condition47
Succeed50:
	%main.201 = add, i32 0, i32 0
	%main.202 = load i32**, i32*** %a
	%main.204 = add, i32 2, i32 0
	%main.206 = getelementptr i32**, %main.202, i32 %main.204
	%main.205 = load i32*, i32** %main.206
	%main.207 = add, i32 10, i32 0
	%main.209 = getelementptr i32*, %main.205, i32 %main.207
	store i32 %main.201, i32* %main.209
	%main.215 = load i32**, i32*** %a
	%main.217 = add, i32 2, i32 0
	%main.220 = getelementptr i32**, %main.215, i32 %main.217
	%main.219 = load i32*, i32** %main.220
	%main.221 = add, i32 10, i32 0
	%main.224 = getelementptr i32*, %main.219, i32 %main.221
	%main.223 = load i32, i32* %main.224
	call void @_global.printNum(i32 %main.223)
	%main.225 = add, i32 -2, i32 0
	%main.226 = load _rec*, _rec** %b
	%main.228 = add, i32 0, i32 0
	%main.230 = getelementptr _rec*, %main.226, i32 %main.228
	%main.235 = load _rec, _rec* %main.230
	%main.234 = getelementptr i32*, %main.235, i32 0, i32 0
	store i32 %main.225, i32* %main.234
	%main.237 = add, i32 -10, i32 0
	%main.238 = load _rec*, _rec** %b
	%main.240 = load i32**, i32*** %a
	%main.242 = add, i32 2, i32 0
	%main.244 = getelementptr i32**, %main.240, i32 %main.242
	%main.243 = load i32*, i32** %main.244
	%main.245 = add, i32 10, i32 0
	%main.247 = getelementptr i32*, %main.243, i32 %main.245
	%main.246 = load i32, i32* %main.247
	%main.249 = getelementptr _rec*, %main.238, i32 %main.246
	%main.254 = load _rec, _rec* %main.249
	%main.253 = getelementptr i32*, %main.254, i32 0, i32 0
	store i32 %main.237, i32* %main.253
	%main.260 = load _rec*, _rec** %b
	%main.262 = add, i32 0, i32 0
	%main.265 = getelementptr _rec*, %main.260, i32 %main.262
	%main.270 = load _rec, _rec* %main.265
	%main.269 = getelementptr i32*, %main.270, i32 0, i32 0
	%main.268 = load i32, i32* %main.269
	call void @_global.printNum(i32 %main.268)
	%main.275 = load _rec*, _rec** %b
	%main.277 = add, i32 1, i32 0
	%main.280 = getelementptr _rec*, %main.275, i32 %main.277
	%main.285 = load _rec, _rec* %main.280
	%main.284 = getelementptr i32*, %main.285, i32 0, i32 0
	%main.283 = load i32, i32* %main.284
	call void @_global.printNum(i32 %main.283)
	%main.286 = add, i32 0, i32 0
	store i32 %main.286, i32* %main.1
	br End7
End7:
	%main.288 = load i32, i32* %main.1
	ret i32 %main.288
}

define void @_global._init(){
Start0:
	%_global._init.1 = add, i32 4, i32 0
	%_global._init.3 = mul, i32 %_global._init.1, i32 4
	%_global._init.5 = add, i32 %_global._init.3, i32 4
	%_global._init.6 = link i32** @malloc(i32 %_global._init.3)
	store i32 %_global._init.1, i32** %_global._init.6
	phi start i32 %_global._init.7
	%_global._init.7 = add, i32 0, i32 0
	%_global._init.17 = add, i32 5, i32 0
	%_global._init.19 = mul, i32 %_global._init.17, i32 4
	%_global._init.21 = add, i32 %_global._init.19, i32 4
	%_global._init.22 = link _rec* @malloc(i32 %_global._init.19)
	store i32 %_global._init.17, _rec* %_global._init.22
	phi start i32 %_global._init.23
	%_global._init.23 = add, i32 0, i32 0
	br NewArrayCondition11
NewArrayCondition8:
	%_global._init.8 = icmp ult, i32 %_global._init.7, i32 %_global._init.1
	br i8 %_global._init.8 NewArrayBody9, Succeed10
NewArrayBody9:
	%_global._init.7 = add, i32 %_global._init.7, i32 1
	br NewArrayCondition8
Succeed10:
	phi end i32 %_global._init.7
	store i32** %_global._init.6, i32*** @a
	br End1
NewArrayCondition11:
	%_global._init.24 = icmp ult, i32 %_global._init.23, i32 %_global._init.17
	br i8 %_global._init.24 NewArrayBody12, Succeed13
NewArrayBody12:
	%_global._init.20 = getelement _rec*, %_global._init.22, i32 %_global._init.23
	%_global._init.27 = add, i32 8, i32 0
	%_global._init.28 = link _rec @malloc(i32 %_global._init.27)
	call void @_rec._init(_rec %_global._init.28)
	store _rec %_global._init.28, _rec* %_global._init.20
	%_global._init.23 = add, i32 %_global._init.23, i32 1
	br NewArrayCondition11
Succeed13:
	phi end i32 %_global._init.23
	store _rec* %_global._init.22, _rec** @b
	br NewArrayCondition8
End1:
	ret void
}

link i32 @_string.length(_string %this);

link _string @_string.substring(_string %this, i32 %l, i32 %r);

link i32 @_string.parseInt(_string %this);

link i32 @_string.ord(_string %this, i32 %index);
%struct._rec = type { i32, i32 }

define void @_rec._init(_rec %_rec._init.this){
Start2:
	%_rec._init.0 = alloca _rec
	store _rec %_rec._init.this, _rec* %_rec._init.0
	br End3
End3:
	ret void
}
