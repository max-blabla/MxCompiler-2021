@a = global zeroinitializer
@b = global zeroinitializer
@i = global zeroinitializer
@j = global zeroinitializer

define void @_global._init(){
Start0:
	%_global._init.1 = add, i32 4, i32 0
	%_global._init.3 = mul, i32 %_global._init.1, i32 4
	%_global._init.5 = add, i32 %_global._init.3, i32 4
	%_global._init.6 = link i32** @malloc(i32 %_global._init.5)
	store i32 %_global._init.1, i32** %_global._init.6
	%_global._init.7 = load i32**, i32*** @a
	store i32** %_global._init.6, i32*** @a
	%_global._init.8 = load i32**, i32*** @a
	%_global._init.12 = add, i32 5, i32 0
	%_global._init.14 = mul, i32 %_global._init.12, i32 4
	%_global._init.16 = add, i32 %_global._init.14, i32 4
	%_global._init.17 = link _rec* @malloc(i32 %_global._init.16)
	store i32 %_global._init.12, _rec* %_global._init.17
	%_global._init.18 = load _rec*, _rec** @b
	store _rec* %_global._init.17, _rec** @b
	%_global._init.19 = load _rec*, _rec** @b
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

link i32 @_string.length(_string %this);

link i32 @_string.parseInt(_string %this);

link _string @_string.substring(_string %this, i32 %l, i32 %r);

link i32 @_string.ord(_string %this, i32 %index);

define void @_global.printNum(i32 %_global.printNum.0){
Start2:
	%_global.printNum.3 = add, i32 %_global.printNum.0, i32 0
	%_global.printNum.4 = link _string @_global.toString(i32 %_global.printNum.3)
	link void @_global.println(_string %_global.printNum.4)
	ret void
}

define i32 @_global.main(){
Start3:
	call void @_global._init()
	%_global.main.1 = add, i32 0, i32 0
	%_global.main.2 = load i32, i32* @i
	store i32 %_global.main.1, i32* @i
	%_global.main.3 = load i32, i32* @i
	br For-Condition4
For-Condition4:
	%_global.main.4 = load i32, i32* @i
	%_global.main.5 = add, i32 4, i32 0
	%_global.main.6 = icmp ult, i32 %_global.main.4, i32 %_global.main.5
	br i8 %_global.main.6 For-Body4, Succeed4
For-Body4:
	%_global.main.7 = add, i32 11, i32 0
	%_global.main.9 = mul, i32 %_global.main.7, i32 4
	%_global.main.11 = add, i32 %_global.main.9, i32 4
	%_global.main.12 = link i32* @malloc(i32 %_global.main.11)
	store i32 %_global.main.7, i32* %_global.main.12
	%_global.main.13 = load i32**, i32*** @a
	%_global.main.14 = load i32, i32* @i
	%_global.main.16 = getelemenptr i32**, %_global.main.13, i32 %_global.main.14
	%_global.main.15 = load i32*, i32** %_global.main.16
	store i32* %_global.main.12, i32** %_global.main.16
	%_global.main.17 = load i32*, i32** %_global.main.16
	%_global.main.18 = load i32, i32* @i
	%_global.main.19 = load i32, i32* @i
	%_global.main.20 = add, i32 %_global.main.19, i32 1
	store i32 %_global.main.20, i32* @i
	br For-Condition4
Succeed4:
	%_global.main.21 = add, i32 0, i32 0
	%_global.main.22 = load i32, i32* @i
	store i32 %_global.main.21, i32* @i
	%_global.main.23 = load i32, i32* @i
	br For-Condition6
For-Condition6:
	%_global.main.24 = load i32, i32* @i
	%_global.main.25 = add, i32 4, i32 0
	%_global.main.26 = icmp ult, i32 %_global.main.24, i32 %_global.main.25
	br i8 %_global.main.26 For-Body6, Succeed6
For-Body6:
	%_global.main.27 = add, i32 0, i32 0
	%_global.main.28 = load i32, i32* @j
	store i32 %_global.main.27, i32* @j
	%_global.main.29 = load i32, i32* @j
	br For-Condition8
Succeed6:
	%_global.main.48 = add, i32 0, i32 0
	%_global.main.49 = load i32, i32* @i
	store i32 %_global.main.48, i32* @i
	%_global.main.50 = load i32, i32* @i
	br For-Condition9
For-Condition8:
	%_global.main.30 = load i32, i32* @j
	%_global.main.31 = add, i32 10, i32 0
	%_global.main.32 = icmp ult, i32 %_global.main.30, i32 %_global.main.31
	br i8 %_global.main.32 For-Body8, Succeed8
For-Condition9:
	%_global.main.51 = load i32, i32* @i
	%_global.main.52 = add, i32 5, i32 0
	%_global.main.53 = icmp ult, i32 %_global.main.51, i32 %_global.main.52
	br i8 %_global.main.53 For-Body9, Succeed9
For-Body8:
	%_global.main.33 = add, i32 888, i32 0
	%_global.main.34 = load i32**, i32*** @a
	%_global.main.35 = load i32, i32* @i
	%_global.main.37 = getelemenptr i32**, %_global.main.34, i32 %_global.main.35
	%_global.main.36 = load i32*, i32** %_global.main.37
	%_global.main.38 = load i32, i32* @j
	%_global.main.40 = getelemenptr i32*, %_global.main.36, i32 %_global.main.38
	%_global.main.39 = load i32, i32* %_global.main.40
	store i32 %_global.main.33, i32* %_global.main.40
	%_global.main.41 = load i32, i32* %_global.main.40
	%_global.main.42 = load i32, i32* @j
	%_global.main.43 = load i32, i32* @j
	%_global.main.44 = add, i32 %_global.main.43, i32 1
	store i32 %_global.main.44, i32* @j
	br For-Condition8
Succeed8:
	%_global.main.45 = load i32, i32* @i
	%_global.main.46 = load i32, i32* @i
	%_global.main.47 = add, i32 %_global.main.46, i32 1
	store i32 %_global.main.47, i32* @i
	br For-Condition6
For-Body9:
	%_global.main.55 = add, i32 8, i32 0
	%_global.main.54 = link _rec @malloc(i32 %_global.main.55)
	call void @_rec._init(_rec %_global.main.54)
	%_global.main.57 = load _rec*, _rec** @b
	%_global.main.58 = load i32, i32* @i
	%_global.main.60 = getelemenptr _rec*, %_global.main.57, i32 %_global.main.58
	%_global.main.59 = load _rec, _rec* %_global.main.60
	store _rec %_global.main.56, _rec* %_global.main.60
	%_global.main.61 = load _rec, _rec* %_global.main.60
	%_global.main.62 = add, i32 -1, i32 0
	%_global.main.63 = load _rec*, _rec** @b
	%_global.main.64 = load i32, i32* @i
	%_global.main.66 = getelemenptr _rec*, %_global.main.63, i32 %_global.main.64
	%_global.main.65 = load _rec, _rec* %_global.main.66
	%_global.main.67 = getelemenptr i32*, %_global.main.65, i32 0, i32 0
	%_global.main.68 = load i32, i32* %_global.main.67
	store i32 %_global.main.62, i32* %_global.main.67
	%_global.main.69 = load i32, i32* %_global.main.67
	%_global.main.70 = load i32, i32* @i
	%_global.main.71 = load i32, i32* @i
	%_global.main.72 = add, i32 %_global.main.71, i32 1
	store i32 %_global.main.72, i32* @i
	br For-Condition9
Succeed9:
	%_global.main.73 = load i32**, i32*** @a
	%_global.main.74 = add, i32 3, i32 0
	%_global.main.76 = getelemenptr i32**, %_global.main.73, i32 %_global.main.74
	%_global.main.75 = load i32*, i32** %_global.main.76
	%_global.main.77 = add, i32 9, i32 0
	%_global.main.79 = getelemenptr i32*, %_global.main.75, i32 %_global.main.77
	%_global.main.78 = load i32, i32* %_global.main.79
	call void @_global.printNum(i32 %_global.main.78)
	%_global.main.81 = add, i32 0, i32 0
	%_global.main.82 = load i32, i32* @i
	store i32 %_global.main.81, i32* @i
	%_global.main.83 = load i32, i32* @i
	br For-Condition11
For-Condition11:
	%_global.main.84 = load i32, i32* @i
	%_global.main.85 = add, i32 3, i32 0
	%_global.main.86 = icmp ule, i32 %_global.main.84, i32 %_global.main.85
	br i8 %_global.main.86 For-Body11, Succeed11
For-Body11:
	%_global.main.87 = add, i32 0, i32 0
	%_global.main.88 = load i32, i32* @j
	store i32 %_global.main.87, i32* @j
	%_global.main.89 = load i32, i32* @j
	br For-Condition12
Succeed11:
	%_global.main.112 = add, i32 0, i32 0
	%_global.main.113 = load i32, i32* @i
	store i32 %_global.main.112, i32* @i
	%_global.main.114 = load i32, i32* @i
	br For-Condition13
For-Condition12:
	%_global.main.90 = load i32, i32* @j
	%_global.main.91 = add, i32 9, i32 0
	%_global.main.92 = icmp ule, i32 %_global.main.90, i32 %_global.main.91
	br i8 %_global.main.92 For-Body12, Succeed12
For-Condition13:
	%_global.main.115 = load i32, i32* @i
	%_global.main.116 = add, i32 3, i32 0
	%_global.main.117 = icmp ule, i32 %_global.main.115, i32 %_global.main.116
	br i8 %_global.main.117 For-Body13, Succeed13
For-Body12:
	%_global.main.93 = load i32, i32* @i
	%_global.main.94 = add, i32 10, i32 0
	%_global.main.95 = mul, i32 %_global.main.93, i32 %_global.main.94
	%_global.main.96 = load i32, i32* @j
	%_global.main.97 = add, i32 %_global.main.95, i32 %_global.main.96
	%_global.main.98 = load i32**, i32*** @a
	%_global.main.99 = load i32, i32* @i
	%_global.main.101 = getelemenptr i32**, %_global.main.98, i32 %_global.main.99
	%_global.main.100 = load i32*, i32** %_global.main.101
	%_global.main.102 = load i32, i32* @j
	%_global.main.104 = getelemenptr i32*, %_global.main.100, i32 %_global.main.102
	%_global.main.103 = load i32, i32* %_global.main.104
	store i32 %_global.main.97, i32* %_global.main.104
	%_global.main.105 = load i32, i32* %_global.main.104
	%_global.main.106 = load i32, i32* @j
	%_global.main.107 = load i32, i32* @j
	%_global.main.108 = add, i32 %_global.main.107, i32 1
	store i32 %_global.main.108, i32* @j
	br For-Condition12
Succeed12:
	%_global.main.109 = load i32, i32* @i
	%_global.main.110 = load i32, i32* @i
	%_global.main.111 = add, i32 %_global.main.110, i32 1
	store i32 %_global.main.111, i32* @i
	br For-Condition11
For-Body13:
	%_global.main.118 = add, i32 0, i32 0
	%_global.main.119 = load i32, i32* @j
	store i32 %_global.main.118, i32* @j
	%_global.main.120 = load i32, i32* @j
	br For-Condition14
Succeed13:
	%_global.main.138 = add, i32 0, i32 0
	%_global.main.139 = load i32**, i32*** @a
	%_global.main.140 = add, i32 2, i32 0
	%_global.main.142 = getelemenptr i32**, %_global.main.139, i32 %_global.main.140
	%_global.main.141 = load i32*, i32** %_global.main.142
	%_global.main.143 = add, i32 10, i32 0
	%_global.main.145 = getelemenptr i32*, %_global.main.141, i32 %_global.main.143
	%_global.main.144 = load i32, i32* %_global.main.145
	store i32 %_global.main.138, i32* %_global.main.145
	%_global.main.146 = load i32, i32* %_global.main.145
	%_global.main.147 = load i32**, i32*** @a
	%_global.main.148 = add, i32 2, i32 0
	%_global.main.150 = getelemenptr i32**, %_global.main.147, i32 %_global.main.148
	%_global.main.149 = load i32*, i32** %_global.main.150
	%_global.main.151 = add, i32 10, i32 0
	%_global.main.153 = getelemenptr i32*, %_global.main.149, i32 %_global.main.151
	%_global.main.152 = load i32, i32* %_global.main.153
	call void @_global.printNum(i32 %_global.main.152)
	%_global.main.155 = add, i32 -2, i32 0
	%_global.main.156 = load _rec*, _rec** @b
	%_global.main.157 = add, i32 0, i32 0
	%_global.main.159 = getelemenptr _rec*, %_global.main.156, i32 %_global.main.157
	%_global.main.158 = load _rec, _rec* %_global.main.159
	%_global.main.160 = getelemenptr i32*, %_global.main.158, i32 0, i32 0
	%_global.main.161 = load i32, i32* %_global.main.160
	store i32 %_global.main.155, i32* %_global.main.160
	%_global.main.162 = load i32, i32* %_global.main.160
	%_global.main.163 = add, i32 -10, i32 0
	%_global.main.164 = load _rec*, _rec** @b
	%_global.main.165 = load i32**, i32*** @a
	%_global.main.166 = add, i32 2, i32 0
	%_global.main.168 = getelemenptr i32**, %_global.main.165, i32 %_global.main.166
	%_global.main.167 = load i32*, i32** %_global.main.168
	%_global.main.169 = add, i32 10, i32 0
	%_global.main.171 = getelemenptr i32*, %_global.main.167, i32 %_global.main.169
	%_global.main.170 = load i32, i32* %_global.main.171
	%_global.main.173 = getelemenptr _rec*, %_global.main.164, i32 %_global.main.170
	%_global.main.172 = load _rec, _rec* %_global.main.173
	%_global.main.174 = getelemenptr i32*, %_global.main.172, i32 0, i32 0
	%_global.main.175 = load i32, i32* %_global.main.174
	store i32 %_global.main.163, i32* %_global.main.174
	%_global.main.176 = load i32, i32* %_global.main.174
	%_global.main.177 = load _rec*, _rec** @b
	%_global.main.178 = add, i32 0, i32 0
	%_global.main.180 = getelemenptr _rec*, %_global.main.177, i32 %_global.main.178
	%_global.main.179 = load _rec, _rec* %_global.main.180
	%_global.main.181 = getelemenptr i32*, %_global.main.179, i32 0, i32 0
	%_global.main.182 = load i32, i32* %_global.main.181
	call void @_global.printNum(i32 %_global.main.182)
	%_global.main.184 = load _rec*, _rec** @b
	%_global.main.185 = add, i32 1, i32 0
	%_global.main.187 = getelemenptr _rec*, %_global.main.184, i32 %_global.main.185
	%_global.main.186 = load _rec, _rec* %_global.main.187
	%_global.main.188 = getelemenptr i32*, %_global.main.186, i32 0, i32 0
	%_global.main.189 = load i32, i32* %_global.main.188
	call void @_global.printNum(i32 %_global.main.189)
	%_global.main.191 = add, i32 0, i32 0
	%_global.main.192 = add, i32 %_global.main.191, i32 0
	ret i32 %_global.main.192
For-Condition14:
	%_global.main.121 = load i32, i32* @j
	%_global.main.122 = add, i32 9, i32 0
	%_global.main.123 = icmp ule, i32 %_global.main.121, i32 %_global.main.122
	br i8 %_global.main.123 For-Body14, Succeed14
For-Body14:
	%_global.main.124 = load i32**, i32*** @a
	%_global.main.125 = load i32, i32* @i
	%_global.main.127 = getelemenptr i32**, %_global.main.124, i32 %_global.main.125
	%_global.main.126 = load i32*, i32** %_global.main.127
	%_global.main.128 = load i32, i32* @j
	%_global.main.130 = getelemenptr i32*, %_global.main.126, i32 %_global.main.128
	%_global.main.129 = load i32, i32* %_global.main.130
	call void @_global.printNum(i32 %_global.main.129)
	%_global.main.132 = load i32, i32* @j
	%_global.main.133 = load i32, i32* @j
	%_global.main.134 = add, i32 %_global.main.133, i32 1
	store i32 %_global.main.134, i32* @j
	br For-Condition14
Succeed14:
	%_global.main.135 = load i32, i32* @i
	%_global.main.136 = load i32, i32* @i
	%_global.main.137 = add, i32 %_global.main.136, i32 1
	store i32 %_global.main.137, i32* @i
	br For-Condition13
}

link i32 @_string.length(_string %this);

link i32 @_string.parseInt(_string %this);

link _string @_string.substring(_string %this, i32 %l, i32 %r);

link i32 @_string.ord(_string %this, i32 %index);
%struct._rec = type { i32*, i32* }

define void @_rec._init(_rec %_rec._init.0){
Start1:
	ret void
}
