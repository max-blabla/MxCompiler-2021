	.text

	.globl	_global.getcount
_global.getcount:
	addi sp ,sp ,-36
	sw ra ,32(sp)
	sw s3 ,0(sp)
	sw s4 ,4(sp)
	sw s0 ,8(sp)
	sw s1 ,12(sp)
	sw s2 ,16(sp)
Start2:
	sw a0 ,20(sp)		#3
	lw s0 ,20(sp)		#4
	addi s1 ,zero ,0		#5
	addi s3 ,zero ,4		#6
	mul s1 ,s1 ,s3		#6
	addi s1 ,s1 ,4		#6
	addi s2 ,s1 ,0		#6
	add s1 ,s0 ,s2		#6
	lw s2 ,0(s1)		#7
	addi s3 ,zero ,1		#8
	add s4 ,s2 ,s3		#8
	sw s4 ,0(s1)		#9
	lw s4 ,0(s1)		#10
	sw s4 ,24(sp)		#10
	j End3		#11
End3:
	lw s4 ,24(sp)		#1
	addi a0 ,s4 ,0		#2
	lw s3 ,0(sp)
	lw s4 ,4(sp)
	lw s0 ,8(sp)
	lw s1 ,12(sp)
	lw s2 ,16(sp)
	lw ra ,32(sp)
	addi sp ,sp ,36
	ret

	.globl	main
main:
	addi sp ,sp ,-2120
	sw ra ,2116(sp)
	sw s3 ,0(sp)
	sw s4 ,4(sp)
	sw s5 ,8(sp)
	sw s11 ,12(sp)
	sw s6 ,16(sp)
	sw s10 ,20(sp)
	sw s7 ,24(sp)
	sw s8 ,28(sp)
	sw s9 ,32(sp)
	sw s0 ,36(sp)
	sw s1 ,40(sp)
	sw s2 ,44(sp)
Start4:
	call _global._init		#1
	addi s0 ,a0 ,0		#1
	addi s1 ,zero ,1		#259
	addi s2 ,zero ,4		#260
	mul s3 ,s1 ,s2		#260
	addi s4 ,zero ,4		#261
	add s5 ,s3 ,s4		#261
	addi a0 ,s5 ,0		#262
	call malloc		#262
	addi s6 ,a0 ,0		#262
	sw s1 ,0(s6)		#263
	lui s1 ,%hi(count)		#264
	addi s1 ,s1 ,%lo(count)		#264
	sw s6 ,0(s1)		#264
	addi s6 ,zero ,0		#265
	lui s7 ,%hi(count)		#266
	addi s7 ,s7 ,%lo(count)		#266
	addi s8 ,zero ,0		#267
	addi s10 ,zero ,4		#268
	mul s8 ,s8 ,s10		#268
	addi s8 ,s8 ,4		#268
	addi s9 ,s8 ,0		#268
	add s8 ,s7 ,s9		#268
	sw s6 ,0(s8)		#269
	lui s6 ,%hi(count)		#270
	addi s6 ,s6 ,%lo(count)		#270
	addi a0 ,s6 ,0		#271
	call _global.getcount		#271
	addi s9 ,a0 ,0		#271
	sw s9 ,2104(sp)		#272
	lui s9 ,%hi(count)		#273
	addi s9 ,s9 ,%lo(count)		#273
	addi a0 ,s9 ,0		#274
	call _global.getcount		#274
	addi s10 ,a0 ,0		#274
	sw s10 ,2100(sp)		#275
	lui s10 ,%hi(count)		#276
	addi s10 ,s10 ,%lo(count)		#276
	addi a0 ,s10 ,0		#277
	call _global.getcount		#277
	addi s11 ,a0 ,0		#277
	sw s11 ,2096(sp)		#278
	lui s11 ,%hi(count)		#279
	addi s11 ,s11 ,%lo(count)		#279
	addi a0 ,s11 ,0		#280
	call _global.getcount		#280
	sw s11 ,1080(sp)
	addi s11 ,a0 ,0		#280
	sw s11 ,2092(sp)		#281
	lui s11 ,%hi(count)		#282
	addi s11 ,s11 ,%lo(count)		#282
	addi a0 ,s11 ,0		#283
	call _global.getcount		#283
	sw s10 ,1076(sp)
	addi s10 ,a0 ,0		#283
	sw s10 ,2088(sp)		#284
	lui s10 ,%hi(count)		#285
	addi s10 ,s10 ,%lo(count)		#285
	addi a0 ,s10 ,0		#286
	call _global.getcount		#286
	sw s9 ,1072(sp)
	addi s9 ,a0 ,0		#286
	sw s9 ,2084(sp)		#287
	lui s9 ,%hi(count)		#288
	addi s9 ,s9 ,%lo(count)		#288
	addi a0 ,s9 ,0		#289
	call _global.getcount		#289
	sw s8 ,1068(sp)
	addi s8 ,a0 ,0		#289
	sw s8 ,2080(sp)		#290
	lui s8 ,%hi(count)		#291
	addi s8 ,s8 ,%lo(count)		#291
	addi a0 ,s8 ,0		#292
	call _global.getcount		#292
	sw s7 ,1064(sp)
	addi s7 ,a0 ,0		#292
	sw s7 ,2076(sp)		#293
	lui s7 ,%hi(count)		#294
	addi s7 ,s7 ,%lo(count)		#294
	addi a0 ,s7 ,0		#295
	call _global.getcount		#295
	sw s6 ,1060(sp)
	addi s6 ,a0 ,0		#295
	sw s6 ,2072(sp)		#296
	lui s6 ,%hi(count)		#297
	addi s6 ,s6 ,%lo(count)		#297
	addi a0 ,s6 ,0		#298
	call _global.getcount		#298
	sw s5 ,1056(sp)
	addi s5 ,a0 ,0		#298
	sw s5 ,2068(sp)		#299
	lui s5 ,%hi(count)		#300
	addi s5 ,s5 ,%lo(count)		#300
	addi a0 ,s5 ,0		#301
	call _global.getcount		#301
	sw s4 ,1052(sp)
	addi s4 ,a0 ,0		#301
	sw s4 ,2064(sp)		#302
	lui s4 ,%hi(count)		#303
	addi s4 ,s4 ,%lo(count)		#303
	addi a0 ,s4 ,0		#304
	call _global.getcount		#304
	sw s3 ,1048(sp)
	addi s3 ,a0 ,0		#304
	sw s3 ,2060(sp)		#305
	lui s3 ,%hi(count)		#306
	addi s3 ,s3 ,%lo(count)		#306
	addi a0 ,s3 ,0		#307
	call _global.getcount		#307
	sw s2 ,1044(sp)
	addi s2 ,a0 ,0		#307
	sw s2 ,2056(sp)		#308
	lui s2 ,%hi(count)		#309
	addi s2 ,s2 ,%lo(count)		#309
	addi a0 ,s2 ,0		#310
	call _global.getcount		#310
	sw s11 ,1040(sp)
	addi s11 ,a0 ,0		#310
	sw s11 ,2052(sp)		#311
	lui s11 ,%hi(count)		#312
	addi s11 ,s11 ,%lo(count)		#312
	addi a0 ,s11 ,0		#313
	call _global.getcount		#313
	sw s10 ,1036(sp)
	addi s10 ,a0 ,0		#313
	sw s10 ,2048(sp)		#314
	lui s10 ,%hi(count)		#315
	addi s10 ,s10 ,%lo(count)		#315
	addi a0 ,s10 ,0		#316
	call _global.getcount		#316
	sw s9 ,1032(sp)
	addi s9 ,a0 ,0		#316
	sw s9 ,2044(sp)		#317
	lui s9 ,%hi(count)		#318
	addi s9 ,s9 ,%lo(count)		#318
	addi a0 ,s9 ,0		#319
	call _global.getcount		#319
	sw s8 ,1028(sp)
	addi s8 ,a0 ,0		#319
	sw s8 ,2040(sp)		#320
	lui s8 ,%hi(count)		#321
	addi s8 ,s8 ,%lo(count)		#321
	addi a0 ,s8 ,0		#322
	call _global.getcount		#322
	sw s7 ,1024(sp)
	addi s7 ,a0 ,0		#322
	sw s7 ,2036(sp)		#323
	lui s7 ,%hi(count)		#324
	addi s7 ,s7 ,%lo(count)		#324
	addi a0 ,s7 ,0		#325
	call _global.getcount		#325
	sw s6 ,1020(sp)
	addi s6 ,a0 ,0		#325
	sw s6 ,2032(sp)		#326
	lui s6 ,%hi(count)		#327
	addi s6 ,s6 ,%lo(count)		#327
	addi a0 ,s6 ,0		#328
	call _global.getcount		#328
	sw s5 ,1016(sp)
	addi s5 ,a0 ,0		#328
	sw s5 ,2028(sp)		#329
	lui s5 ,%hi(count)		#330
	addi s5 ,s5 ,%lo(count)		#330
	addi a0 ,s5 ,0		#331
	call _global.getcount		#331
	sw s4 ,1012(sp)
	addi s4 ,a0 ,0		#331
	sw s4 ,2024(sp)		#332
	lui s4 ,%hi(count)		#333
	addi s4 ,s4 ,%lo(count)		#333
	addi a0 ,s4 ,0		#334
	call _global.getcount		#334
	sw s3 ,1008(sp)
	addi s3 ,a0 ,0		#334
	sw s3 ,2020(sp)		#335
	lui s3 ,%hi(count)		#336
	addi s3 ,s3 ,%lo(count)		#336
	addi a0 ,s3 ,0		#337
	call _global.getcount		#337
	sw s2 ,1004(sp)
	addi s2 ,a0 ,0		#337
	sw s2 ,2016(sp)		#338
	lui s2 ,%hi(count)		#339
	addi s2 ,s2 ,%lo(count)		#339
	addi a0 ,s2 ,0		#340
	call _global.getcount		#340
	sw s11 ,1000(sp)
	addi s11 ,a0 ,0		#340
	sw s11 ,2012(sp)		#341
	lui s11 ,%hi(count)		#342
	addi s11 ,s11 ,%lo(count)		#342
	addi a0 ,s11 ,0		#343
	call _global.getcount		#343
	sw s10 ,996(sp)
	addi s10 ,a0 ,0		#343
	sw s10 ,2008(sp)		#344
	lui s10 ,%hi(count)		#345
	addi s10 ,s10 ,%lo(count)		#345
	addi a0 ,s10 ,0		#346
	call _global.getcount		#346
	sw s9 ,992(sp)
	addi s9 ,a0 ,0		#346
	sw s9 ,2004(sp)		#347
	lui s9 ,%hi(count)		#348
	addi s9 ,s9 ,%lo(count)		#348
	addi a0 ,s9 ,0		#349
	call _global.getcount		#349
	sw s8 ,988(sp)
	addi s8 ,a0 ,0		#349
	sw s8 ,2000(sp)		#350
	lui s8 ,%hi(count)		#351
	addi s8 ,s8 ,%lo(count)		#351
	addi a0 ,s8 ,0		#352
	call _global.getcount		#352
	sw s7 ,984(sp)
	addi s7 ,a0 ,0		#352
	sw s7 ,1996(sp)		#353
	lui s7 ,%hi(count)		#354
	addi s7 ,s7 ,%lo(count)		#354
	addi a0 ,s7 ,0		#355
	call _global.getcount		#355
	sw s6 ,980(sp)
	addi s6 ,a0 ,0		#355
	sw s6 ,1992(sp)		#356
	lui s6 ,%hi(count)		#357
	addi s6 ,s6 ,%lo(count)		#357
	addi a0 ,s6 ,0		#358
	call _global.getcount		#358
	sw s5 ,976(sp)
	addi s5 ,a0 ,0		#358
	sw s5 ,1988(sp)		#359
	lui s5 ,%hi(count)		#360
	addi s5 ,s5 ,%lo(count)		#360
	addi a0 ,s5 ,0		#361
	call _global.getcount		#361
	sw s4 ,972(sp)
	addi s4 ,a0 ,0		#361
	sw s4 ,1984(sp)		#362
	lui s4 ,%hi(count)		#363
	addi s4 ,s4 ,%lo(count)		#363
	addi a0 ,s4 ,0		#364
	call _global.getcount		#364
	sw s3 ,968(sp)
	addi s3 ,a0 ,0		#364
	sw s3 ,1980(sp)		#365
	lui s3 ,%hi(count)		#366
	addi s3 ,s3 ,%lo(count)		#366
	addi a0 ,s3 ,0		#367
	call _global.getcount		#367
	sw s2 ,964(sp)
	addi s2 ,a0 ,0		#367
	sw s2 ,1976(sp)		#368
	lui s2 ,%hi(count)		#369
	addi s2 ,s2 ,%lo(count)		#369
	addi a0 ,s2 ,0		#370
	call _global.getcount		#370
	sw s11 ,960(sp)
	addi s11 ,a0 ,0		#370
	sw s11 ,1972(sp)		#371
	lui s11 ,%hi(count)		#372
	addi s11 ,s11 ,%lo(count)		#372
	addi a0 ,s11 ,0		#373
	call _global.getcount		#373
	sw s10 ,956(sp)
	addi s10 ,a0 ,0		#373
	sw s10 ,1968(sp)		#374
	lui s10 ,%hi(count)		#375
	addi s10 ,s10 ,%lo(count)		#375
	addi a0 ,s10 ,0		#376
	call _global.getcount		#376
	sw s9 ,952(sp)
	addi s9 ,a0 ,0		#376
	sw s9 ,1964(sp)		#377
	lui s9 ,%hi(count)		#378
	addi s9 ,s9 ,%lo(count)		#378
	addi a0 ,s9 ,0		#379
	call _global.getcount		#379
	sw s8 ,948(sp)
	addi s8 ,a0 ,0		#379
	sw s8 ,1960(sp)		#380
	lui s8 ,%hi(count)		#381
	addi s8 ,s8 ,%lo(count)		#381
	addi a0 ,s8 ,0		#382
	call _global.getcount		#382
	sw s7 ,944(sp)
	addi s7 ,a0 ,0		#382
	sw s7 ,1956(sp)		#383
	lui s7 ,%hi(count)		#384
	addi s7 ,s7 ,%lo(count)		#384
	addi a0 ,s7 ,0		#385
	call _global.getcount		#385
	sw s6 ,940(sp)
	addi s6 ,a0 ,0		#385
	sw s6 ,1952(sp)		#386
	lui s6 ,%hi(count)		#387
	addi s6 ,s6 ,%lo(count)		#387
	addi a0 ,s6 ,0		#388
	call _global.getcount		#388
	sw s5 ,936(sp)
	addi s5 ,a0 ,0		#388
	sw s5 ,1948(sp)		#389
	lui s5 ,%hi(count)		#390
	addi s5 ,s5 ,%lo(count)		#390
	addi a0 ,s5 ,0		#391
	call _global.getcount		#391
	sw s4 ,932(sp)
	addi s4 ,a0 ,0		#391
	sw s4 ,1944(sp)		#392
	lui s4 ,%hi(count)		#393
	addi s4 ,s4 ,%lo(count)		#393
	addi a0 ,s4 ,0		#394
	call _global.getcount		#394
	sw s3 ,928(sp)
	addi s3 ,a0 ,0		#394
	sw s3 ,1940(sp)		#395
	lui s3 ,%hi(count)		#396
	addi s3 ,s3 ,%lo(count)		#396
	addi a0 ,s3 ,0		#397
	call _global.getcount		#397
	sw s2 ,924(sp)
	addi s2 ,a0 ,0		#397
	sw s2 ,1936(sp)		#398
	lui s2 ,%hi(count)		#399
	addi s2 ,s2 ,%lo(count)		#399
	addi a0 ,s2 ,0		#400
	call _global.getcount		#400
	sw s11 ,920(sp)
	addi s11 ,a0 ,0		#400
	sw s11 ,1932(sp)		#401
	lui s11 ,%hi(count)		#402
	addi s11 ,s11 ,%lo(count)		#402
	addi a0 ,s11 ,0		#403
	call _global.getcount		#403
	sw s10 ,916(sp)
	addi s10 ,a0 ,0		#403
	sw s10 ,1928(sp)		#404
	lui s10 ,%hi(count)		#405
	addi s10 ,s10 ,%lo(count)		#405
	addi a0 ,s10 ,0		#406
	call _global.getcount		#406
	sw s9 ,912(sp)
	addi s9 ,a0 ,0		#406
	sw s9 ,1924(sp)		#407
	lui s9 ,%hi(count)		#408
	addi s9 ,s9 ,%lo(count)		#408
	addi a0 ,s9 ,0		#409
	call _global.getcount		#409
	sw s8 ,908(sp)
	addi s8 ,a0 ,0		#409
	sw s8 ,1920(sp)		#410
	lui s8 ,%hi(count)		#411
	addi s8 ,s8 ,%lo(count)		#411
	addi a0 ,s8 ,0		#412
	call _global.getcount		#412
	sw s7 ,904(sp)
	addi s7 ,a0 ,0		#412
	sw s7 ,1916(sp)		#413
	lui s7 ,%hi(count)		#414
	addi s7 ,s7 ,%lo(count)		#414
	addi a0 ,s7 ,0		#415
	call _global.getcount		#415
	sw s6 ,900(sp)
	addi s6 ,a0 ,0		#415
	sw s6 ,1912(sp)		#416
	lui s6 ,%hi(count)		#417
	addi s6 ,s6 ,%lo(count)		#417
	addi a0 ,s6 ,0		#418
	call _global.getcount		#418
	sw s5 ,896(sp)
	addi s5 ,a0 ,0		#418
	sw s5 ,1908(sp)		#419
	lui s5 ,%hi(count)		#420
	addi s5 ,s5 ,%lo(count)		#420
	addi a0 ,s5 ,0		#421
	call _global.getcount		#421
	sw s4 ,892(sp)
	addi s4 ,a0 ,0		#421
	sw s4 ,1904(sp)		#422
	lui s4 ,%hi(count)		#423
	addi s4 ,s4 ,%lo(count)		#423
	addi a0 ,s4 ,0		#424
	call _global.getcount		#424
	sw s3 ,888(sp)
	addi s3 ,a0 ,0		#424
	sw s3 ,1900(sp)		#425
	lui s3 ,%hi(count)		#426
	addi s3 ,s3 ,%lo(count)		#426
	addi a0 ,s3 ,0		#427
	call _global.getcount		#427
	sw s2 ,884(sp)
	addi s2 ,a0 ,0		#427
	sw s2 ,1896(sp)		#428
	lui s2 ,%hi(count)		#429
	addi s2 ,s2 ,%lo(count)		#429
	addi a0 ,s2 ,0		#430
	call _global.getcount		#430
	sw s11 ,880(sp)
	addi s11 ,a0 ,0		#430
	sw s11 ,1892(sp)		#431
	lui s11 ,%hi(count)		#432
	addi s11 ,s11 ,%lo(count)		#432
	addi a0 ,s11 ,0		#433
	call _global.getcount		#433
	sw s10 ,876(sp)
	addi s10 ,a0 ,0		#433
	sw s10 ,1888(sp)		#434
	lui s10 ,%hi(count)		#435
	addi s10 ,s10 ,%lo(count)		#435
	addi a0 ,s10 ,0		#436
	call _global.getcount		#436
	sw s9 ,872(sp)
	addi s9 ,a0 ,0		#436
	sw s9 ,1884(sp)		#437
	lui s9 ,%hi(count)		#438
	addi s9 ,s9 ,%lo(count)		#438
	addi a0 ,s9 ,0		#439
	call _global.getcount		#439
	sw s8 ,868(sp)
	addi s8 ,a0 ,0		#439
	sw s8 ,1880(sp)		#440
	lui s8 ,%hi(count)		#441
	addi s8 ,s8 ,%lo(count)		#441
	addi a0 ,s8 ,0		#442
	call _global.getcount		#442
	sw s7 ,864(sp)
	addi s7 ,a0 ,0		#442
	sw s7 ,1876(sp)		#443
	lui s7 ,%hi(count)		#444
	addi s7 ,s7 ,%lo(count)		#444
	addi a0 ,s7 ,0		#445
	call _global.getcount		#445
	sw s6 ,860(sp)
	addi s6 ,a0 ,0		#445
	sw s6 ,1872(sp)		#446
	lui s6 ,%hi(count)		#447
	addi s6 ,s6 ,%lo(count)		#447
	addi a0 ,s6 ,0		#448
	call _global.getcount		#448
	sw s5 ,856(sp)
	addi s5 ,a0 ,0		#448
	sw s5 ,1868(sp)		#449
	lui s5 ,%hi(count)		#450
	addi s5 ,s5 ,%lo(count)		#450
	addi a0 ,s5 ,0		#451
	call _global.getcount		#451
	sw s4 ,852(sp)
	addi s4 ,a0 ,0		#451
	sw s4 ,1864(sp)		#452
	lui s4 ,%hi(count)		#453
	addi s4 ,s4 ,%lo(count)		#453
	addi a0 ,s4 ,0		#454
	call _global.getcount		#454
	sw s3 ,848(sp)
	addi s3 ,a0 ,0		#454
	sw s3 ,1860(sp)		#455
	lui s3 ,%hi(count)		#456
	addi s3 ,s3 ,%lo(count)		#456
	addi a0 ,s3 ,0		#457
	call _global.getcount		#457
	sw s2 ,844(sp)
	addi s2 ,a0 ,0		#457
	sw s2 ,1856(sp)		#458
	lui s2 ,%hi(count)		#459
	addi s2 ,s2 ,%lo(count)		#459
	addi a0 ,s2 ,0		#460
	call _global.getcount		#460
	sw s11 ,840(sp)
	addi s11 ,a0 ,0		#460
	sw s11 ,1852(sp)		#461
	lui s11 ,%hi(count)		#462
	addi s11 ,s11 ,%lo(count)		#462
	addi a0 ,s11 ,0		#463
	call _global.getcount		#463
	sw s10 ,836(sp)
	addi s10 ,a0 ,0		#463
	sw s10 ,1848(sp)		#464
	lui s10 ,%hi(count)		#465
	addi s10 ,s10 ,%lo(count)		#465
	addi a0 ,s10 ,0		#466
	call _global.getcount		#466
	sw s9 ,832(sp)
	addi s9 ,a0 ,0		#466
	sw s9 ,1844(sp)		#467
	lui s9 ,%hi(count)		#468
	addi s9 ,s9 ,%lo(count)		#468
	addi a0 ,s9 ,0		#469
	call _global.getcount		#469
	sw s8 ,828(sp)
	addi s8 ,a0 ,0		#469
	sw s8 ,1840(sp)		#470
	lui s8 ,%hi(count)		#471
	addi s8 ,s8 ,%lo(count)		#471
	addi a0 ,s8 ,0		#472
	call _global.getcount		#472
	sw s7 ,824(sp)
	addi s7 ,a0 ,0		#472
	sw s7 ,1836(sp)		#473
	lui s7 ,%hi(count)		#474
	addi s7 ,s7 ,%lo(count)		#474
	addi a0 ,s7 ,0		#475
	call _global.getcount		#475
	sw s6 ,820(sp)
	addi s6 ,a0 ,0		#475
	sw s6 ,1832(sp)		#476
	lui s6 ,%hi(count)		#477
	addi s6 ,s6 ,%lo(count)		#477
	addi a0 ,s6 ,0		#478
	call _global.getcount		#478
	sw s5 ,816(sp)
	addi s5 ,a0 ,0		#478
	sw s5 ,1828(sp)		#479
	lui s5 ,%hi(count)		#480
	addi s5 ,s5 ,%lo(count)		#480
	addi a0 ,s5 ,0		#481
	call _global.getcount		#481
	sw s4 ,812(sp)
	addi s4 ,a0 ,0		#481
	sw s4 ,1824(sp)		#482
	lui s4 ,%hi(count)		#483
	addi s4 ,s4 ,%lo(count)		#483
	addi a0 ,s4 ,0		#484
	call _global.getcount		#484
	sw s3 ,808(sp)
	addi s3 ,a0 ,0		#484
	sw s3 ,1820(sp)		#485
	lui s3 ,%hi(count)		#486
	addi s3 ,s3 ,%lo(count)		#486
	addi a0 ,s3 ,0		#487
	call _global.getcount		#487
	sw s2 ,804(sp)
	addi s2 ,a0 ,0		#487
	sw s2 ,1816(sp)		#488
	lui s2 ,%hi(count)		#489
	addi s2 ,s2 ,%lo(count)		#489
	addi a0 ,s2 ,0		#490
	call _global.getcount		#490
	sw s11 ,800(sp)
	addi s11 ,a0 ,0		#490
	sw s11 ,1812(sp)		#491
	lui s11 ,%hi(count)		#492
	addi s11 ,s11 ,%lo(count)		#492
	addi a0 ,s11 ,0		#493
	call _global.getcount		#493
	sw s10 ,796(sp)
	addi s10 ,a0 ,0		#493
	sw s10 ,1808(sp)		#494
	lui s10 ,%hi(count)		#495
	addi s10 ,s10 ,%lo(count)		#495
	addi a0 ,s10 ,0		#496
	call _global.getcount		#496
	sw s9 ,792(sp)
	addi s9 ,a0 ,0		#496
	sw s9 ,1804(sp)		#497
	lui s9 ,%hi(count)		#498
	addi s9 ,s9 ,%lo(count)		#498
	addi a0 ,s9 ,0		#499
	call _global.getcount		#499
	sw s8 ,788(sp)
	addi s8 ,a0 ,0		#499
	sw s8 ,1800(sp)		#500
	lui s8 ,%hi(count)		#501
	addi s8 ,s8 ,%lo(count)		#501
	addi a0 ,s8 ,0		#502
	call _global.getcount		#502
	sw s7 ,784(sp)
	addi s7 ,a0 ,0		#502
	sw s7 ,1796(sp)		#503
	lui s7 ,%hi(count)		#504
	addi s7 ,s7 ,%lo(count)		#504
	addi a0 ,s7 ,0		#505
	call _global.getcount		#505
	sw s6 ,780(sp)
	addi s6 ,a0 ,0		#505
	sw s6 ,1792(sp)		#506
	lui s6 ,%hi(count)		#507
	addi s6 ,s6 ,%lo(count)		#507
	addi a0 ,s6 ,0		#508
	call _global.getcount		#508
	sw s5 ,776(sp)
	addi s5 ,a0 ,0		#508
	sw s5 ,1788(sp)		#509
	lui s5 ,%hi(count)		#510
	addi s5 ,s5 ,%lo(count)		#510
	addi a0 ,s5 ,0		#511
	call _global.getcount		#511
	sw s4 ,772(sp)
	addi s4 ,a0 ,0		#511
	sw s4 ,1784(sp)		#512
	lui s4 ,%hi(count)		#513
	addi s4 ,s4 ,%lo(count)		#513
	addi a0 ,s4 ,0		#514
	call _global.getcount		#514
	sw s3 ,768(sp)
	addi s3 ,a0 ,0		#514
	sw s3 ,1780(sp)		#515
	lui s3 ,%hi(count)		#516
	addi s3 ,s3 ,%lo(count)		#516
	addi a0 ,s3 ,0		#517
	call _global.getcount		#517
	sw s2 ,764(sp)
	addi s2 ,a0 ,0		#517
	sw s2 ,1776(sp)		#518
	lui s2 ,%hi(count)		#519
	addi s2 ,s2 ,%lo(count)		#519
	addi a0 ,s2 ,0		#520
	call _global.getcount		#520
	sw s11 ,760(sp)
	addi s11 ,a0 ,0		#520
	sw s11 ,1772(sp)		#521
	lui s11 ,%hi(count)		#522
	addi s11 ,s11 ,%lo(count)		#522
	addi a0 ,s11 ,0		#523
	call _global.getcount		#523
	sw s10 ,756(sp)
	addi s10 ,a0 ,0		#523
	sw s10 ,1768(sp)		#524
	lui s10 ,%hi(count)		#525
	addi s10 ,s10 ,%lo(count)		#525
	addi a0 ,s10 ,0		#526
	call _global.getcount		#526
	sw s9 ,752(sp)
	addi s9 ,a0 ,0		#526
	sw s9 ,1764(sp)		#527
	lui s9 ,%hi(count)		#528
	addi s9 ,s9 ,%lo(count)		#528
	addi a0 ,s9 ,0		#529
	call _global.getcount		#529
	sw s8 ,748(sp)
	addi s8 ,a0 ,0		#529
	sw s8 ,1760(sp)		#530
	lui s8 ,%hi(count)		#531
	addi s8 ,s8 ,%lo(count)		#531
	addi a0 ,s8 ,0		#532
	call _global.getcount		#532
	sw s7 ,744(sp)
	addi s7 ,a0 ,0		#532
	sw s7 ,1756(sp)		#533
	lui s7 ,%hi(count)		#534
	addi s7 ,s7 ,%lo(count)		#534
	addi a0 ,s7 ,0		#535
	call _global.getcount		#535
	sw s6 ,740(sp)
	addi s6 ,a0 ,0		#535
	sw s6 ,1752(sp)		#536
	lui s6 ,%hi(count)		#537
	addi s6 ,s6 ,%lo(count)		#537
	addi a0 ,s6 ,0		#538
	call _global.getcount		#538
	sw s5 ,736(sp)
	addi s5 ,a0 ,0		#538
	sw s5 ,1748(sp)		#539
	lui s5 ,%hi(count)		#540
	addi s5 ,s5 ,%lo(count)		#540
	addi a0 ,s5 ,0		#541
	call _global.getcount		#541
	sw s4 ,732(sp)
	addi s4 ,a0 ,0		#541
	sw s4 ,1744(sp)		#542
	lui s4 ,%hi(count)		#543
	addi s4 ,s4 ,%lo(count)		#543
	addi a0 ,s4 ,0		#544
	call _global.getcount		#544
	sw s3 ,728(sp)
	addi s3 ,a0 ,0		#544
	sw s3 ,1740(sp)		#545
	lui s3 ,%hi(count)		#546
	addi s3 ,s3 ,%lo(count)		#546
	addi a0 ,s3 ,0		#547
	call _global.getcount		#547
	sw s2 ,724(sp)
	addi s2 ,a0 ,0		#547
	sw s2 ,1736(sp)		#548
	lui s2 ,%hi(count)		#549
	addi s2 ,s2 ,%lo(count)		#549
	addi a0 ,s2 ,0		#550
	call _global.getcount		#550
	sw s11 ,720(sp)
	addi s11 ,a0 ,0		#550
	sw s11 ,1732(sp)		#551
	lui s11 ,%hi(count)		#552
	addi s11 ,s11 ,%lo(count)		#552
	addi a0 ,s11 ,0		#553
	call _global.getcount		#553
	sw s10 ,716(sp)
	addi s10 ,a0 ,0		#553
	sw s10 ,1728(sp)		#554
	lui s10 ,%hi(count)		#555
	addi s10 ,s10 ,%lo(count)		#555
	addi a0 ,s10 ,0		#556
	call _global.getcount		#556
	sw s9 ,712(sp)
	addi s9 ,a0 ,0		#556
	sw s9 ,1724(sp)		#557
	lui s9 ,%hi(count)		#558
	addi s9 ,s9 ,%lo(count)		#558
	addi a0 ,s9 ,0		#559
	call _global.getcount		#559
	sw s8 ,708(sp)
	addi s8 ,a0 ,0		#559
	sw s8 ,1720(sp)		#560
	lui s8 ,%hi(count)		#561
	addi s8 ,s8 ,%lo(count)		#561
	addi a0 ,s8 ,0		#562
	call _global.getcount		#562
	sw s7 ,704(sp)
	addi s7 ,a0 ,0		#562
	sw s7 ,1716(sp)		#563
	lui s7 ,%hi(count)		#564
	addi s7 ,s7 ,%lo(count)		#564
	addi a0 ,s7 ,0		#565
	call _global.getcount		#565
	sw s6 ,700(sp)
	addi s6 ,a0 ,0		#565
	sw s6 ,1712(sp)		#566
	lui s6 ,%hi(count)		#567
	addi s6 ,s6 ,%lo(count)		#567
	addi a0 ,s6 ,0		#568
	call _global.getcount		#568
	sw s5 ,696(sp)
	addi s5 ,a0 ,0		#568
	sw s5 ,1708(sp)		#569
	lui s5 ,%hi(count)		#570
	addi s5 ,s5 ,%lo(count)		#570
	addi a0 ,s5 ,0		#571
	call _global.getcount		#571
	sw s4 ,692(sp)
	addi s4 ,a0 ,0		#571
	sw s4 ,1704(sp)		#572
	lui s4 ,%hi(count)		#573
	addi s4 ,s4 ,%lo(count)		#573
	addi a0 ,s4 ,0		#574
	call _global.getcount		#574
	sw s3 ,688(sp)
	addi s3 ,a0 ,0		#574
	sw s3 ,1700(sp)		#575
	lui s3 ,%hi(count)		#576
	addi s3 ,s3 ,%lo(count)		#576
	addi a0 ,s3 ,0		#577
	call _global.getcount		#577
	sw s2 ,684(sp)
	addi s2 ,a0 ,0		#577
	sw s2 ,1696(sp)		#578
	lui s2 ,%hi(count)		#579
	addi s2 ,s2 ,%lo(count)		#579
	addi a0 ,s2 ,0		#580
	call _global.getcount		#580
	sw s11 ,680(sp)
	addi s11 ,a0 ,0		#580
	sw s11 ,1692(sp)		#581
	lui s11 ,%hi(count)		#582
	addi s11 ,s11 ,%lo(count)		#582
	addi a0 ,s11 ,0		#583
	call _global.getcount		#583
	sw s10 ,676(sp)
	addi s10 ,a0 ,0		#583
	sw s10 ,1688(sp)		#584
	lui s10 ,%hi(count)		#585
	addi s10 ,s10 ,%lo(count)		#585
	addi a0 ,s10 ,0		#586
	call _global.getcount		#586
	sw s9 ,672(sp)
	addi s9 ,a0 ,0		#586
	sw s9 ,1684(sp)		#587
	lui s9 ,%hi(count)		#588
	addi s9 ,s9 ,%lo(count)		#588
	addi a0 ,s9 ,0		#589
	call _global.getcount		#589
	sw s8 ,668(sp)
	addi s8 ,a0 ,0		#589
	sw s8 ,1680(sp)		#590
	lui s8 ,%hi(count)		#591
	addi s8 ,s8 ,%lo(count)		#591
	addi a0 ,s8 ,0		#592
	call _global.getcount		#592
	sw s7 ,664(sp)
	addi s7 ,a0 ,0		#592
	sw s7 ,1676(sp)		#593
	lui s7 ,%hi(count)		#594
	addi s7 ,s7 ,%lo(count)		#594
	addi a0 ,s7 ,0		#595
	call _global.getcount		#595
	sw s6 ,660(sp)
	addi s6 ,a0 ,0		#595
	sw s6 ,1672(sp)		#596
	lui s6 ,%hi(count)		#597
	addi s6 ,s6 ,%lo(count)		#597
	addi a0 ,s6 ,0		#598
	call _global.getcount		#598
	sw s5 ,656(sp)
	addi s5 ,a0 ,0		#598
	sw s5 ,1668(sp)		#599
	lui s5 ,%hi(count)		#600
	addi s5 ,s5 ,%lo(count)		#600
	addi a0 ,s5 ,0		#601
	call _global.getcount		#601
	sw s4 ,652(sp)
	addi s4 ,a0 ,0		#601
	sw s4 ,1664(sp)		#602
	lui s4 ,%hi(count)		#603
	addi s4 ,s4 ,%lo(count)		#603
	addi a0 ,s4 ,0		#604
	call _global.getcount		#604
	sw s3 ,648(sp)
	addi s3 ,a0 ,0		#604
	sw s3 ,1660(sp)		#605
	lui s3 ,%hi(count)		#606
	addi s3 ,s3 ,%lo(count)		#606
	addi a0 ,s3 ,0		#607
	call _global.getcount		#607
	sw s2 ,644(sp)
	addi s2 ,a0 ,0		#607
	sw s2 ,1656(sp)		#608
	lui s2 ,%hi(count)		#609
	addi s2 ,s2 ,%lo(count)		#609
	addi a0 ,s2 ,0		#610
	call _global.getcount		#610
	sw s11 ,640(sp)
	addi s11 ,a0 ,0		#610
	sw s11 ,1652(sp)		#611
	lui s11 ,%hi(count)		#612
	addi s11 ,s11 ,%lo(count)		#612
	addi a0 ,s11 ,0		#613
	call _global.getcount		#613
	sw s10 ,636(sp)
	addi s10 ,a0 ,0		#613
	sw s10 ,1648(sp)		#614
	lui s10 ,%hi(count)		#615
	addi s10 ,s10 ,%lo(count)		#615
	addi a0 ,s10 ,0		#616
	call _global.getcount		#616
	sw s9 ,632(sp)
	addi s9 ,a0 ,0		#616
	sw s9 ,1644(sp)		#617
	lui s9 ,%hi(count)		#618
	addi s9 ,s9 ,%lo(count)		#618
	addi a0 ,s9 ,0		#619
	call _global.getcount		#619
	sw s8 ,628(sp)
	addi s8 ,a0 ,0		#619
	sw s8 ,1640(sp)		#620
	lui s8 ,%hi(count)		#621
	addi s8 ,s8 ,%lo(count)		#621
	addi a0 ,s8 ,0		#622
	call _global.getcount		#622
	sw s7 ,624(sp)
	addi s7 ,a0 ,0		#622
	sw s7 ,1636(sp)		#623
	lui s7 ,%hi(count)		#624
	addi s7 ,s7 ,%lo(count)		#624
	addi a0 ,s7 ,0		#625
	call _global.getcount		#625
	sw s6 ,620(sp)
	addi s6 ,a0 ,0		#625
	sw s6 ,1632(sp)		#626
	lui s6 ,%hi(count)		#627
	addi s6 ,s6 ,%lo(count)		#627
	addi a0 ,s6 ,0		#628
	call _global.getcount		#628
	sw s5 ,616(sp)
	addi s5 ,a0 ,0		#628
	sw s5 ,1628(sp)		#629
	lui s5 ,%hi(count)		#630
	addi s5 ,s5 ,%lo(count)		#630
	addi a0 ,s5 ,0		#631
	call _global.getcount		#631
	sw s4 ,612(sp)
	addi s4 ,a0 ,0		#631
	sw s4 ,1624(sp)		#632
	lui s4 ,%hi(count)		#633
	addi s4 ,s4 ,%lo(count)		#633
	addi a0 ,s4 ,0		#634
	call _global.getcount		#634
	sw s3 ,608(sp)
	addi s3 ,a0 ,0		#634
	sw s3 ,1620(sp)		#635
	lui s3 ,%hi(count)		#636
	addi s3 ,s3 ,%lo(count)		#636
	addi a0 ,s3 ,0		#637
	call _global.getcount		#637
	sw s2 ,604(sp)
	addi s2 ,a0 ,0		#637
	sw s2 ,1616(sp)		#638
	lui s2 ,%hi(count)		#639
	addi s2 ,s2 ,%lo(count)		#639
	addi a0 ,s2 ,0		#640
	call _global.getcount		#640
	sw s11 ,600(sp)
	addi s11 ,a0 ,0		#640
	sw s11 ,1612(sp)		#641
	lui s11 ,%hi(count)		#642
	addi s11 ,s11 ,%lo(count)		#642
	addi a0 ,s11 ,0		#643
	call _global.getcount		#643
	sw s10 ,596(sp)
	addi s10 ,a0 ,0		#643
	sw s10 ,1608(sp)		#644
	lui s10 ,%hi(count)		#645
	addi s10 ,s10 ,%lo(count)		#645
	addi a0 ,s10 ,0		#646
	call _global.getcount		#646
	sw s9 ,592(sp)
	addi s9 ,a0 ,0		#646
	sw s9 ,1604(sp)		#647
	lui s9 ,%hi(count)		#648
	addi s9 ,s9 ,%lo(count)		#648
	addi a0 ,s9 ,0		#649
	call _global.getcount		#649
	sw s8 ,588(sp)
	addi s8 ,a0 ,0		#649
	sw s8 ,1600(sp)		#650
	lui s8 ,%hi(count)		#651
	addi s8 ,s8 ,%lo(count)		#651
	addi a0 ,s8 ,0		#652
	call _global.getcount		#652
	sw s7 ,584(sp)
	addi s7 ,a0 ,0		#652
	sw s7 ,1596(sp)		#653
	lui s7 ,%hi(count)		#654
	addi s7 ,s7 ,%lo(count)		#654
	addi a0 ,s7 ,0		#655
	call _global.getcount		#655
	sw s6 ,580(sp)
	addi s6 ,a0 ,0		#655
	sw s6 ,1592(sp)		#656
	lui s6 ,%hi(count)		#657
	addi s6 ,s6 ,%lo(count)		#657
	addi a0 ,s6 ,0		#658
	call _global.getcount		#658
	sw s5 ,576(sp)
	addi s5 ,a0 ,0		#658
	sw s5 ,1588(sp)		#659
	lui s5 ,%hi(count)		#660
	addi s5 ,s5 ,%lo(count)		#660
	addi a0 ,s5 ,0		#661
	call _global.getcount		#661
	sw s4 ,572(sp)
	addi s4 ,a0 ,0		#661
	sw s4 ,1584(sp)		#662
	lui s4 ,%hi(count)		#663
	addi s4 ,s4 ,%lo(count)		#663
	addi a0 ,s4 ,0		#664
	call _global.getcount		#664
	sw s3 ,568(sp)
	addi s3 ,a0 ,0		#664
	sw s3 ,1580(sp)		#665
	lui s3 ,%hi(count)		#666
	addi s3 ,s3 ,%lo(count)		#666
	addi a0 ,s3 ,0		#667
	call _global.getcount		#667
	sw s2 ,564(sp)
	addi s2 ,a0 ,0		#667
	sw s2 ,1576(sp)		#668
	lui s2 ,%hi(count)		#669
	addi s2 ,s2 ,%lo(count)		#669
	addi a0 ,s2 ,0		#670
	call _global.getcount		#670
	sw s11 ,560(sp)
	addi s11 ,a0 ,0		#670
	sw s11 ,1572(sp)		#671
	lui s11 ,%hi(count)		#672
	addi s11 ,s11 ,%lo(count)		#672
	addi a0 ,s11 ,0		#673
	call _global.getcount		#673
	sw s10 ,556(sp)
	addi s10 ,a0 ,0		#673
	sw s10 ,1568(sp)		#674
	lui s10 ,%hi(count)		#675
	addi s10 ,s10 ,%lo(count)		#675
	addi a0 ,s10 ,0		#676
	call _global.getcount		#676
	sw s9 ,552(sp)
	addi s9 ,a0 ,0		#676
	sw s9 ,1564(sp)		#677
	lui s9 ,%hi(count)		#678
	addi s9 ,s9 ,%lo(count)		#678
	addi a0 ,s9 ,0		#679
	call _global.getcount		#679
	sw s8 ,548(sp)
	addi s8 ,a0 ,0		#679
	sw s8 ,1560(sp)		#680
	lui s8 ,%hi(count)		#681
	addi s8 ,s8 ,%lo(count)		#681
	addi a0 ,s8 ,0		#682
	call _global.getcount		#682
	sw s7 ,544(sp)
	addi s7 ,a0 ,0		#682
	sw s7 ,1556(sp)		#683
	lui s7 ,%hi(count)		#684
	addi s7 ,s7 ,%lo(count)		#684
	addi a0 ,s7 ,0		#685
	call _global.getcount		#685
	sw s6 ,540(sp)
	addi s6 ,a0 ,0		#685
	sw s6 ,1552(sp)		#686
	lui s6 ,%hi(count)		#687
	addi s6 ,s6 ,%lo(count)		#687
	addi a0 ,s6 ,0		#688
	call _global.getcount		#688
	sw s5 ,536(sp)
	addi s5 ,a0 ,0		#688
	sw s5 ,1548(sp)		#689
	lui s5 ,%hi(count)		#690
	addi s5 ,s5 ,%lo(count)		#690
	addi a0 ,s5 ,0		#691
	call _global.getcount		#691
	sw s4 ,532(sp)
	addi s4 ,a0 ,0		#691
	sw s4 ,1544(sp)		#692
	lui s4 ,%hi(count)		#693
	addi s4 ,s4 ,%lo(count)		#693
	addi a0 ,s4 ,0		#694
	call _global.getcount		#694
	sw s3 ,528(sp)
	addi s3 ,a0 ,0		#694
	sw s3 ,1540(sp)		#695
	lui s3 ,%hi(count)		#696
	addi s3 ,s3 ,%lo(count)		#696
	addi a0 ,s3 ,0		#697
	call _global.getcount		#697
	sw s2 ,524(sp)
	addi s2 ,a0 ,0		#697
	sw s2 ,1536(sp)		#698
	lui s2 ,%hi(count)		#699
	addi s2 ,s2 ,%lo(count)		#699
	addi a0 ,s2 ,0		#700
	call _global.getcount		#700
	sw s11 ,520(sp)
	addi s11 ,a0 ,0		#700
	sw s11 ,1532(sp)		#701
	lui s11 ,%hi(count)		#702
	addi s11 ,s11 ,%lo(count)		#702
	addi a0 ,s11 ,0		#703
	call _global.getcount		#703
	sw s10 ,516(sp)
	addi s10 ,a0 ,0		#703
	sw s10 ,1528(sp)		#704
	lui s10 ,%hi(count)		#705
	addi s10 ,s10 ,%lo(count)		#705
	addi a0 ,s10 ,0		#706
	call _global.getcount		#706
	sw s9 ,512(sp)
	addi s9 ,a0 ,0		#706
	sw s9 ,1524(sp)		#707
	lui s9 ,%hi(count)		#708
	addi s9 ,s9 ,%lo(count)		#708
	addi a0 ,s9 ,0		#709
	call _global.getcount		#709
	sw s8 ,508(sp)
	addi s8 ,a0 ,0		#709
	sw s8 ,1520(sp)		#710
	lui s8 ,%hi(count)		#711
	addi s8 ,s8 ,%lo(count)		#711
	addi a0 ,s8 ,0		#712
	call _global.getcount		#712
	sw s7 ,504(sp)
	addi s7 ,a0 ,0		#712
	sw s7 ,1516(sp)		#713
	lui s7 ,%hi(count)		#714
	addi s7 ,s7 ,%lo(count)		#714
	addi a0 ,s7 ,0		#715
	call _global.getcount		#715
	sw s6 ,500(sp)
	addi s6 ,a0 ,0		#715
	sw s6 ,1512(sp)		#716
	lui s6 ,%hi(count)		#717
	addi s6 ,s6 ,%lo(count)		#717
	addi a0 ,s6 ,0		#718
	call _global.getcount		#718
	sw s5 ,496(sp)
	addi s5 ,a0 ,0		#718
	sw s5 ,1508(sp)		#719
	lui s5 ,%hi(count)		#720
	addi s5 ,s5 ,%lo(count)		#720
	addi a0 ,s5 ,0		#721
	call _global.getcount		#721
	sw s4 ,492(sp)
	addi s4 ,a0 ,0		#721
	sw s4 ,1504(sp)		#722
	lui s4 ,%hi(count)		#723
	addi s4 ,s4 ,%lo(count)		#723
	addi a0 ,s4 ,0		#724
	call _global.getcount		#724
	sw s3 ,488(sp)
	addi s3 ,a0 ,0		#724
	sw s3 ,1500(sp)		#725
	lui s3 ,%hi(count)		#726
	addi s3 ,s3 ,%lo(count)		#726
	addi a0 ,s3 ,0		#727
	call _global.getcount		#727
	sw s2 ,484(sp)
	addi s2 ,a0 ,0		#727
	sw s2 ,1496(sp)		#728
	lui s2 ,%hi(count)		#729
	addi s2 ,s2 ,%lo(count)		#729
	addi a0 ,s2 ,0		#730
	call _global.getcount		#730
	sw s11 ,480(sp)
	addi s11 ,a0 ,0		#730
	sw s11 ,1492(sp)		#731
	lui s11 ,%hi(count)		#732
	addi s11 ,s11 ,%lo(count)		#732
	addi a0 ,s11 ,0		#733
	call _global.getcount		#733
	sw s10 ,476(sp)
	addi s10 ,a0 ,0		#733
	sw s10 ,1488(sp)		#734
	lui s10 ,%hi(count)		#735
	addi s10 ,s10 ,%lo(count)		#735
	addi a0 ,s10 ,0		#736
	call _global.getcount		#736
	sw s9 ,472(sp)
	addi s9 ,a0 ,0		#736
	sw s9 ,1484(sp)		#737
	lui s9 ,%hi(count)		#738
	addi s9 ,s9 ,%lo(count)		#738
	addi a0 ,s9 ,0		#739
	call _global.getcount		#739
	sw s8 ,468(sp)
	addi s8 ,a0 ,0		#739
	sw s8 ,1480(sp)		#740
	lui s8 ,%hi(count)		#741
	addi s8 ,s8 ,%lo(count)		#741
	addi a0 ,s8 ,0		#742
	call _global.getcount		#742
	sw s7 ,464(sp)
	addi s7 ,a0 ,0		#742
	sw s7 ,1476(sp)		#743
	lui s7 ,%hi(count)		#744
	addi s7 ,s7 ,%lo(count)		#744
	addi a0 ,s7 ,0		#745
	call _global.getcount		#745
	sw s6 ,460(sp)
	addi s6 ,a0 ,0		#745
	sw s6 ,1472(sp)		#746
	lui s6 ,%hi(count)		#747
	addi s6 ,s6 ,%lo(count)		#747
	addi a0 ,s6 ,0		#748
	call _global.getcount		#748
	sw s5 ,456(sp)
	addi s5 ,a0 ,0		#748
	sw s5 ,1468(sp)		#749
	lui s5 ,%hi(count)		#750
	addi s5 ,s5 ,%lo(count)		#750
	addi a0 ,s5 ,0		#751
	call _global.getcount		#751
	sw s4 ,452(sp)
	addi s4 ,a0 ,0		#751
	sw s4 ,1464(sp)		#752
	lui s4 ,%hi(count)		#753
	addi s4 ,s4 ,%lo(count)		#753
	addi a0 ,s4 ,0		#754
	call _global.getcount		#754
	sw s3 ,448(sp)
	addi s3 ,a0 ,0		#754
	sw s3 ,1460(sp)		#755
	lui s3 ,%hi(count)		#756
	addi s3 ,s3 ,%lo(count)		#756
	addi a0 ,s3 ,0		#757
	call _global.getcount		#757
	sw s2 ,444(sp)
	addi s2 ,a0 ,0		#757
	sw s2 ,1456(sp)		#758
	lui s2 ,%hi(count)		#759
	addi s2 ,s2 ,%lo(count)		#759
	addi a0 ,s2 ,0		#760
	call _global.getcount		#760
	sw s11 ,440(sp)
	addi s11 ,a0 ,0		#760
	sw s11 ,1452(sp)		#761
	lui s11 ,%hi(count)		#762
	addi s11 ,s11 ,%lo(count)		#762
	addi a0 ,s11 ,0		#763
	call _global.getcount		#763
	sw s10 ,436(sp)
	addi s10 ,a0 ,0		#763
	sw s10 ,1448(sp)		#764
	lui s10 ,%hi(count)		#765
	addi s10 ,s10 ,%lo(count)		#765
	addi a0 ,s10 ,0		#766
	call _global.getcount		#766
	sw s9 ,432(sp)
	addi s9 ,a0 ,0		#766
	sw s9 ,1444(sp)		#767
	lui s9 ,%hi(count)		#768
	addi s9 ,s9 ,%lo(count)		#768
	addi a0 ,s9 ,0		#769
	call _global.getcount		#769
	sw s8 ,428(sp)
	addi s8 ,a0 ,0		#769
	sw s8 ,1440(sp)		#770
	lui s8 ,%hi(count)		#771
	addi s8 ,s8 ,%lo(count)		#771
	addi a0 ,s8 ,0		#772
	call _global.getcount		#772
	sw s7 ,424(sp)
	addi s7 ,a0 ,0		#772
	sw s7 ,1436(sp)		#773
	lui s7 ,%hi(count)		#774
	addi s7 ,s7 ,%lo(count)		#774
	addi a0 ,s7 ,0		#775
	call _global.getcount		#775
	sw s6 ,420(sp)
	addi s6 ,a0 ,0		#775
	sw s6 ,1432(sp)		#776
	lui s6 ,%hi(count)		#777
	addi s6 ,s6 ,%lo(count)		#777
	addi a0 ,s6 ,0		#778
	call _global.getcount		#778
	sw s5 ,416(sp)
	addi s5 ,a0 ,0		#778
	sw s5 ,1428(sp)		#779
	lui s5 ,%hi(count)		#780
	addi s5 ,s5 ,%lo(count)		#780
	addi a0 ,s5 ,0		#781
	call _global.getcount		#781
	sw s4 ,412(sp)
	addi s4 ,a0 ,0		#781
	sw s4 ,1424(sp)		#782
	lui s4 ,%hi(count)		#783
	addi s4 ,s4 ,%lo(count)		#783
	addi a0 ,s4 ,0		#784
	call _global.getcount		#784
	sw s3 ,408(sp)
	addi s3 ,a0 ,0		#784
	sw s3 ,1420(sp)		#785
	lui s3 ,%hi(count)		#786
	addi s3 ,s3 ,%lo(count)		#786
	addi a0 ,s3 ,0		#787
	call _global.getcount		#787
	sw s2 ,404(sp)
	addi s2 ,a0 ,0		#787
	sw s2 ,1416(sp)		#788
	lui s2 ,%hi(count)		#789
	addi s2 ,s2 ,%lo(count)		#789
	addi a0 ,s2 ,0		#790
	call _global.getcount		#790
	sw s11 ,400(sp)
	addi s11 ,a0 ,0		#790
	sw s11 ,1412(sp)		#791
	lui s11 ,%hi(count)		#792
	addi s11 ,s11 ,%lo(count)		#792
	addi a0 ,s11 ,0		#793
	call _global.getcount		#793
	sw s10 ,396(sp)
	addi s10 ,a0 ,0		#793
	sw s10 ,1408(sp)		#794
	lui s10 ,%hi(count)		#795
	addi s10 ,s10 ,%lo(count)		#795
	addi a0 ,s10 ,0		#796
	call _global.getcount		#796
	sw s9 ,392(sp)
	addi s9 ,a0 ,0		#796
	sw s9 ,1404(sp)		#797
	lui s9 ,%hi(count)		#798
	addi s9 ,s9 ,%lo(count)		#798
	addi a0 ,s9 ,0		#799
	call _global.getcount		#799
	sw s8 ,388(sp)
	addi s8 ,a0 ,0		#799
	sw s8 ,1400(sp)		#800
	lui s8 ,%hi(count)		#801
	addi s8 ,s8 ,%lo(count)		#801
	addi a0 ,s8 ,0		#802
	call _global.getcount		#802
	sw s7 ,384(sp)
	addi s7 ,a0 ,0		#802
	sw s7 ,1396(sp)		#803
	lui s7 ,%hi(count)		#804
	addi s7 ,s7 ,%lo(count)		#804
	addi a0 ,s7 ,0		#805
	call _global.getcount		#805
	sw s6 ,380(sp)
	addi s6 ,a0 ,0		#805
	sw s6 ,1392(sp)		#806
	lui s6 ,%hi(count)		#807
	addi s6 ,s6 ,%lo(count)		#807
	addi a0 ,s6 ,0		#808
	call _global.getcount		#808
	sw s5 ,376(sp)
	addi s5 ,a0 ,0		#808
	sw s5 ,1388(sp)		#809
	lui s5 ,%hi(count)		#810
	addi s5 ,s5 ,%lo(count)		#810
	addi a0 ,s5 ,0		#811
	call _global.getcount		#811
	sw s4 ,372(sp)
	addi s4 ,a0 ,0		#811
	sw s4 ,1384(sp)		#812
	lui s4 ,%hi(count)		#813
	addi s4 ,s4 ,%lo(count)		#813
	addi a0 ,s4 ,0		#814
	call _global.getcount		#814
	sw s3 ,368(sp)
	addi s3 ,a0 ,0		#814
	sw s3 ,1380(sp)		#815
	lui s3 ,%hi(count)		#816
	addi s3 ,s3 ,%lo(count)		#816
	addi a0 ,s3 ,0		#817
	call _global.getcount		#817
	sw s2 ,364(sp)
	addi s2 ,a0 ,0		#817
	sw s2 ,1376(sp)		#818
	lui s2 ,%hi(count)		#819
	addi s2 ,s2 ,%lo(count)		#819
	addi a0 ,s2 ,0		#820
	call _global.getcount		#820
	sw s11 ,360(sp)
	addi s11 ,a0 ,0		#820
	sw s11 ,1372(sp)		#821
	lui s11 ,%hi(count)		#822
	addi s11 ,s11 ,%lo(count)		#822
	addi a0 ,s11 ,0		#823
	call _global.getcount		#823
	sw s10 ,356(sp)
	addi s10 ,a0 ,0		#823
	sw s10 ,1368(sp)		#824
	lui s10 ,%hi(count)		#825
	addi s10 ,s10 ,%lo(count)		#825
	addi a0 ,s10 ,0		#826
	call _global.getcount		#826
	sw s9 ,352(sp)
	addi s9 ,a0 ,0		#826
	sw s9 ,1364(sp)		#827
	lui s9 ,%hi(count)		#828
	addi s9 ,s9 ,%lo(count)		#828
	addi a0 ,s9 ,0		#829
	call _global.getcount		#829
	sw s8 ,348(sp)
	addi s8 ,a0 ,0		#829
	sw s8 ,1360(sp)		#830
	lui s8 ,%hi(count)		#831
	addi s8 ,s8 ,%lo(count)		#831
	addi a0 ,s8 ,0		#832
	call _global.getcount		#832
	sw s7 ,344(sp)
	addi s7 ,a0 ,0		#832
	sw s7 ,1356(sp)		#833
	lui s7 ,%hi(count)		#834
	addi s7 ,s7 ,%lo(count)		#834
	addi a0 ,s7 ,0		#835
	call _global.getcount		#835
	sw s6 ,340(sp)
	addi s6 ,a0 ,0		#835
	sw s6 ,1352(sp)		#836
	lui s6 ,%hi(count)		#837
	addi s6 ,s6 ,%lo(count)		#837
	addi a0 ,s6 ,0		#838
	call _global.getcount		#838
	sw s5 ,336(sp)
	addi s5 ,a0 ,0		#838
	sw s5 ,1348(sp)		#839
	lui s5 ,%hi(count)		#840
	addi s5 ,s5 ,%lo(count)		#840
	addi a0 ,s5 ,0		#841
	call _global.getcount		#841
	sw s4 ,332(sp)
	addi s4 ,a0 ,0		#841
	sw s4 ,1344(sp)		#842
	lui s4 ,%hi(count)		#843
	addi s4 ,s4 ,%lo(count)		#843
	addi a0 ,s4 ,0		#844
	call _global.getcount		#844
	sw s3 ,328(sp)
	addi s3 ,a0 ,0		#844
	sw s3 ,1340(sp)		#845
	lui s3 ,%hi(count)		#846
	addi s3 ,s3 ,%lo(count)		#846
	addi a0 ,s3 ,0		#847
	call _global.getcount		#847
	sw s2 ,324(sp)
	addi s2 ,a0 ,0		#847
	sw s2 ,1336(sp)		#848
	lui s2 ,%hi(count)		#849
	addi s2 ,s2 ,%lo(count)		#849
	addi a0 ,s2 ,0		#850
	call _global.getcount		#850
	sw s11 ,320(sp)
	addi s11 ,a0 ,0		#850
	sw s11 ,1332(sp)		#851
	lui s11 ,%hi(count)		#852
	addi s11 ,s11 ,%lo(count)		#852
	addi a0 ,s11 ,0		#853
	call _global.getcount		#853
	sw s10 ,316(sp)
	addi s10 ,a0 ,0		#853
	sw s10 ,1328(sp)		#854
	lui s10 ,%hi(count)		#855
	addi s10 ,s10 ,%lo(count)		#855
	addi a0 ,s10 ,0		#856
	call _global.getcount		#856
	sw s9 ,312(sp)
	addi s9 ,a0 ,0		#856
	sw s9 ,1324(sp)		#857
	lui s9 ,%hi(count)		#858
	addi s9 ,s9 ,%lo(count)		#858
	addi a0 ,s9 ,0		#859
	call _global.getcount		#859
	sw s8 ,308(sp)
	addi s8 ,a0 ,0		#859
	sw s8 ,1320(sp)		#860
	lui s8 ,%hi(count)		#861
	addi s8 ,s8 ,%lo(count)		#861
	addi a0 ,s8 ,0		#862
	call _global.getcount		#862
	sw s7 ,304(sp)
	addi s7 ,a0 ,0		#862
	sw s7 ,1316(sp)		#863
	lui s7 ,%hi(count)		#864
	addi s7 ,s7 ,%lo(count)		#864
	addi a0 ,s7 ,0		#865
	call _global.getcount		#865
	sw s6 ,300(sp)
	addi s6 ,a0 ,0		#865
	sw s6 ,1312(sp)		#866
	lui s6 ,%hi(count)		#867
	addi s6 ,s6 ,%lo(count)		#867
	addi a0 ,s6 ,0		#868
	call _global.getcount		#868
	sw s5 ,296(sp)
	addi s5 ,a0 ,0		#868
	sw s5 ,1308(sp)		#869
	lui s5 ,%hi(count)		#870
	addi s5 ,s5 ,%lo(count)		#870
	addi a0 ,s5 ,0		#871
	call _global.getcount		#871
	sw s4 ,292(sp)
	addi s4 ,a0 ,0		#871
	sw s4 ,1304(sp)		#872
	lui s4 ,%hi(count)		#873
	addi s4 ,s4 ,%lo(count)		#873
	addi a0 ,s4 ,0		#874
	call _global.getcount		#874
	sw s3 ,288(sp)
	addi s3 ,a0 ,0		#874
	sw s3 ,1300(sp)		#875
	lui s3 ,%hi(count)		#876
	addi s3 ,s3 ,%lo(count)		#876
	addi a0 ,s3 ,0		#877
	call _global.getcount		#877
	sw s2 ,284(sp)
	addi s2 ,a0 ,0		#877
	sw s2 ,1296(sp)		#878
	lui s2 ,%hi(count)		#879
	addi s2 ,s2 ,%lo(count)		#879
	addi a0 ,s2 ,0		#880
	call _global.getcount		#880
	sw s11 ,280(sp)
	addi s11 ,a0 ,0		#880
	sw s11 ,1292(sp)		#881
	lui s11 ,%hi(count)		#882
	addi s11 ,s11 ,%lo(count)		#882
	addi a0 ,s11 ,0		#883
	call _global.getcount		#883
	sw s10 ,276(sp)
	addi s10 ,a0 ,0		#883
	sw s10 ,1288(sp)		#884
	lui s10 ,%hi(count)		#885
	addi s10 ,s10 ,%lo(count)		#885
	addi a0 ,s10 ,0		#886
	call _global.getcount		#886
	sw s9 ,272(sp)
	addi s9 ,a0 ,0		#886
	sw s9 ,1284(sp)		#887
	lui s9 ,%hi(count)		#888
	addi s9 ,s9 ,%lo(count)		#888
	addi a0 ,s9 ,0		#889
	call _global.getcount		#889
	sw s8 ,268(sp)
	addi s8 ,a0 ,0		#889
	sw s8 ,1280(sp)		#890
	lui s8 ,%hi(count)		#891
	addi s8 ,s8 ,%lo(count)		#891
	addi a0 ,s8 ,0		#892
	call _global.getcount		#892
	sw s7 ,264(sp)
	addi s7 ,a0 ,0		#892
	sw s7 ,1276(sp)		#893
	lui s7 ,%hi(count)		#894
	addi s7 ,s7 ,%lo(count)		#894
	addi a0 ,s7 ,0		#895
	call _global.getcount		#895
	sw s6 ,260(sp)
	addi s6 ,a0 ,0		#895
	sw s6 ,1272(sp)		#896
	lui s6 ,%hi(count)		#897
	addi s6 ,s6 ,%lo(count)		#897
	addi a0 ,s6 ,0		#898
	call _global.getcount		#898
	sw s5 ,256(sp)
	addi s5 ,a0 ,0		#898
	sw s5 ,1268(sp)		#899
	lui s5 ,%hi(count)		#900
	addi s5 ,s5 ,%lo(count)		#900
	addi a0 ,s5 ,0		#901
	call _global.getcount		#901
	sw s4 ,252(sp)
	addi s4 ,a0 ,0		#901
	sw s4 ,1264(sp)		#902
	lui s4 ,%hi(count)		#903
	addi s4 ,s4 ,%lo(count)		#903
	addi a0 ,s4 ,0		#904
	call _global.getcount		#904
	sw s3 ,248(sp)
	addi s3 ,a0 ,0		#904
	sw s3 ,1260(sp)		#905
	lui s3 ,%hi(count)		#906
	addi s3 ,s3 ,%lo(count)		#906
	addi a0 ,s3 ,0		#907
	call _global.getcount		#907
	sw s2 ,244(sp)
	addi s2 ,a0 ,0		#907
	sw s2 ,1256(sp)		#908
	lui s2 ,%hi(count)		#909
	addi s2 ,s2 ,%lo(count)		#909
	addi a0 ,s2 ,0		#910
	call _global.getcount		#910
	sw s11 ,240(sp)
	addi s11 ,a0 ,0		#910
	sw s11 ,1252(sp)		#911
	lui s11 ,%hi(count)		#912
	addi s11 ,s11 ,%lo(count)		#912
	addi a0 ,s11 ,0		#913
	call _global.getcount		#913
	sw s10 ,236(sp)
	addi s10 ,a0 ,0		#913
	sw s10 ,1248(sp)		#914
	lui s10 ,%hi(count)		#915
	addi s10 ,s10 ,%lo(count)		#915
	addi a0 ,s10 ,0		#916
	call _global.getcount		#916
	sw s9 ,232(sp)
	addi s9 ,a0 ,0		#916
	sw s9 ,1244(sp)		#917
	lui s9 ,%hi(count)		#918
	addi s9 ,s9 ,%lo(count)		#918
	addi a0 ,s9 ,0		#919
	call _global.getcount		#919
	sw s8 ,228(sp)
	addi s8 ,a0 ,0		#919
	sw s8 ,1240(sp)		#920
	lui s8 ,%hi(count)		#921
	addi s8 ,s8 ,%lo(count)		#921
	addi a0 ,s8 ,0		#922
	call _global.getcount		#922
	sw s7 ,224(sp)
	addi s7 ,a0 ,0		#922
	sw s7 ,1236(sp)		#923
	lui s7 ,%hi(count)		#924
	addi s7 ,s7 ,%lo(count)		#924
	addi a0 ,s7 ,0		#925
	call _global.getcount		#925
	sw s6 ,220(sp)
	addi s6 ,a0 ,0		#925
	sw s6 ,1232(sp)		#926
	lui s6 ,%hi(count)		#927
	addi s6 ,s6 ,%lo(count)		#927
	addi a0 ,s6 ,0		#928
	call _global.getcount		#928
	sw s5 ,216(sp)
	addi s5 ,a0 ,0		#928
	sw s5 ,1228(sp)		#929
	lui s5 ,%hi(count)		#930
	addi s5 ,s5 ,%lo(count)		#930
	addi a0 ,s5 ,0		#931
	call _global.getcount		#931
	sw s4 ,212(sp)
	addi s4 ,a0 ,0		#931
	sw s4 ,1224(sp)		#932
	lui s4 ,%hi(count)		#933
	addi s4 ,s4 ,%lo(count)		#933
	addi a0 ,s4 ,0		#934
	call _global.getcount		#934
	sw s3 ,208(sp)
	addi s3 ,a0 ,0		#934
	sw s3 ,1220(sp)		#935
	lui s3 ,%hi(count)		#936
	addi s3 ,s3 ,%lo(count)		#936
	addi a0 ,s3 ,0		#937
	call _global.getcount		#937
	sw s2 ,204(sp)
	addi s2 ,a0 ,0		#937
	sw s2 ,1216(sp)		#938
	lui s2 ,%hi(count)		#939
	addi s2 ,s2 ,%lo(count)		#939
	addi a0 ,s2 ,0		#940
	call _global.getcount		#940
	sw s11 ,200(sp)
	addi s11 ,a0 ,0		#940
	sw s11 ,1212(sp)		#941
	lui s11 ,%hi(count)		#942
	addi s11 ,s11 ,%lo(count)		#942
	addi a0 ,s11 ,0		#943
	call _global.getcount		#943
	sw s10 ,196(sp)
	addi s10 ,a0 ,0		#943
	sw s10 ,1208(sp)		#944
	lui s10 ,%hi(count)		#945
	addi s10 ,s10 ,%lo(count)		#945
	addi a0 ,s10 ,0		#946
	call _global.getcount		#946
	sw s9 ,192(sp)
	addi s9 ,a0 ,0		#946
	sw s9 ,1204(sp)		#947
	lui s9 ,%hi(count)		#948
	addi s9 ,s9 ,%lo(count)		#948
	addi a0 ,s9 ,0		#949
	call _global.getcount		#949
	sw s8 ,188(sp)
	addi s8 ,a0 ,0		#949
	sw s8 ,1200(sp)		#950
	lui s8 ,%hi(count)		#951
	addi s8 ,s8 ,%lo(count)		#951
	addi a0 ,s8 ,0		#952
	call _global.getcount		#952
	sw s7 ,184(sp)
	addi s7 ,a0 ,0		#952
	sw s7 ,1196(sp)		#953
	lui s7 ,%hi(count)		#954
	addi s7 ,s7 ,%lo(count)		#954
	addi a0 ,s7 ,0		#955
	call _global.getcount		#955
	sw s6 ,180(sp)
	addi s6 ,a0 ,0		#955
	sw s6 ,1192(sp)		#956
	lui s6 ,%hi(count)		#957
	addi s6 ,s6 ,%lo(count)		#957
	addi a0 ,s6 ,0		#958
	call _global.getcount		#958
	sw s5 ,176(sp)
	addi s5 ,a0 ,0		#958
	sw s5 ,1188(sp)		#959
	lui s5 ,%hi(count)		#960
	addi s5 ,s5 ,%lo(count)		#960
	addi a0 ,s5 ,0		#961
	call _global.getcount		#961
	sw s4 ,172(sp)
	addi s4 ,a0 ,0		#961
	sw s4 ,1184(sp)		#962
	lui s4 ,%hi(count)		#963
	addi s4 ,s4 ,%lo(count)		#963
	addi a0 ,s4 ,0		#964
	call _global.getcount		#964
	sw s3 ,168(sp)
	addi s3 ,a0 ,0		#964
	sw s3 ,1180(sp)		#965
	lui s3 ,%hi(count)		#966
	addi s3 ,s3 ,%lo(count)		#966
	addi a0 ,s3 ,0		#967
	call _global.getcount		#967
	sw s2 ,164(sp)
	addi s2 ,a0 ,0		#967
	sw s2 ,1176(sp)		#968
	lui s2 ,%hi(count)		#969
	addi s2 ,s2 ,%lo(count)		#969
	addi a0 ,s2 ,0		#970
	call _global.getcount		#970
	sw s11 ,160(sp)
	addi s11 ,a0 ,0		#970
	sw s11 ,1172(sp)		#971
	lui s11 ,%hi(count)		#972
	addi s11 ,s11 ,%lo(count)		#972
	addi a0 ,s11 ,0		#973
	call _global.getcount		#973
	sw s10 ,156(sp)
	addi s10 ,a0 ,0		#973
	sw s10 ,1168(sp)		#974
	lui s10 ,%hi(count)		#975
	addi s10 ,s10 ,%lo(count)		#975
	addi a0 ,s10 ,0		#976
	call _global.getcount		#976
	sw s9 ,152(sp)
	addi s9 ,a0 ,0		#976
	sw s9 ,1164(sp)		#977
	lui s9 ,%hi(count)		#978
	addi s9 ,s9 ,%lo(count)		#978
	addi a0 ,s9 ,0		#979
	call _global.getcount		#979
	sw s8 ,148(sp)
	addi s8 ,a0 ,0		#979
	sw s8 ,1160(sp)		#980
	lui s8 ,%hi(count)		#981
	addi s8 ,s8 ,%lo(count)		#981
	addi a0 ,s8 ,0		#982
	call _global.getcount		#982
	sw s7 ,144(sp)
	addi s7 ,a0 ,0		#982
	sw s7 ,1156(sp)		#983
	lui s7 ,%hi(count)		#984
	addi s7 ,s7 ,%lo(count)		#984
	addi a0 ,s7 ,0		#985
	call _global.getcount		#985
	sw s6 ,140(sp)
	addi s6 ,a0 ,0		#985
	sw s6 ,1152(sp)		#986
	lui s6 ,%hi(count)		#987
	addi s6 ,s6 ,%lo(count)		#987
	addi a0 ,s6 ,0		#988
	call _global.getcount		#988
	sw s5 ,136(sp)
	addi s5 ,a0 ,0		#988
	sw s5 ,1148(sp)		#989
	lui s5 ,%hi(count)		#990
	addi s5 ,s5 ,%lo(count)		#990
	addi a0 ,s5 ,0		#991
	call _global.getcount		#991
	sw s4 ,132(sp)
	addi s4 ,a0 ,0		#991
	sw s4 ,1144(sp)		#992
	lui s4 ,%hi(count)		#993
	addi s4 ,s4 ,%lo(count)		#993
	addi a0 ,s4 ,0		#994
	call _global.getcount		#994
	sw s3 ,128(sp)
	addi s3 ,a0 ,0		#994
	sw s3 ,1140(sp)		#995
	lui s3 ,%hi(count)		#996
	addi s3 ,s3 ,%lo(count)		#996
	addi a0 ,s3 ,0		#997
	call _global.getcount		#997
	sw s2 ,124(sp)
	addi s2 ,a0 ,0		#997
	sw s2 ,1136(sp)		#998
	lui s2 ,%hi(count)		#999
	addi s2 ,s2 ,%lo(count)		#999
	addi a0 ,s2 ,0		#1000
	call _global.getcount		#1000
	sw s11 ,120(sp)
	addi s11 ,a0 ,0		#1000
	sw s11 ,1132(sp)		#1001
	lui s11 ,%hi(count)		#1002
	addi s11 ,s11 ,%lo(count)		#1002
	addi a0 ,s11 ,0		#1003
	call _global.getcount		#1003
	sw s10 ,116(sp)
	addi s10 ,a0 ,0		#1003
	sw s10 ,1128(sp)		#1004
	lui s10 ,%hi(count)		#1005
	addi s10 ,s10 ,%lo(count)		#1005
	addi a0 ,s10 ,0		#1006
	call _global.getcount		#1006
	sw s9 ,112(sp)
	addi s9 ,a0 ,0		#1006
	sw s9 ,1124(sp)		#1007
	lui s9 ,%hi(count)		#1008
	addi s9 ,s9 ,%lo(count)		#1008
	addi a0 ,s9 ,0		#1009
	call _global.getcount		#1009
	sw s8 ,108(sp)
	addi s8 ,a0 ,0		#1009
	sw s8 ,1120(sp)		#1010
	lui s8 ,%hi(count)		#1011
	addi s8 ,s8 ,%lo(count)		#1011
	addi a0 ,s8 ,0		#1012
	call _global.getcount		#1012
	sw s7 ,104(sp)
	addi s7 ,a0 ,0		#1012
	sw s7 ,1116(sp)		#1013
	lui s7 ,%hi(count)		#1014
	addi s7 ,s7 ,%lo(count)		#1014
	addi a0 ,s7 ,0		#1015
	call _global.getcount		#1015
	sw s6 ,100(sp)
	addi s6 ,a0 ,0		#1015
	sw s6 ,1112(sp)		#1016
	lui s6 ,%hi(count)		#1017
	addi s6 ,s6 ,%lo(count)		#1017
	addi a0 ,s6 ,0		#1018
	call _global.getcount		#1018
	sw s5 ,96(sp)
	addi s5 ,a0 ,0		#1018
	sw s5 ,1108(sp)		#1019
	lui s5 ,%hi(count)		#1020
	addi s5 ,s5 ,%lo(count)		#1020
	addi a0 ,s5 ,0		#1021
	call _global.getcount		#1021
	sw s4 ,92(sp)
	addi s4 ,a0 ,0		#1021
	sw s4 ,1104(sp)		#1022
	lui s4 ,%hi(count)		#1023
	addi s4 ,s4 ,%lo(count)		#1023
	addi a0 ,s4 ,0		#1024
	call _global.getcount		#1024
	sw s3 ,88(sp)
	addi s3 ,a0 ,0		#1024
	sw s3 ,1100(sp)		#1025
	lui s3 ,%hi(count)		#1026
	addi s3 ,s3 ,%lo(count)		#1026
	addi a0 ,s3 ,0		#1027
	call _global.getcount		#1027
	sw s2 ,84(sp)
	addi s2 ,a0 ,0		#1027
	sw s2 ,1096(sp)		#1028
	lui s2 ,%hi(count)		#1029
	addi s2 ,s2 ,%lo(count)		#1029
	addi a0 ,s2 ,0		#1030
	call _global.getcount		#1030
	sw s11 ,80(sp)
	addi s11 ,a0 ,0		#1030
	sw s11 ,1092(sp)		#1031
	lui s11 ,%hi(count)		#1032
	addi s11 ,s11 ,%lo(count)		#1032
	addi a0 ,s11 ,0		#1033
	call _global.getcount		#1033
	sw s10 ,76(sp)
	addi s10 ,a0 ,0		#1033
	sw s10 ,1088(sp)		#1034
	lui s10 ,%hi(count)		#1035
	addi s10 ,s10 ,%lo(count)		#1035
	addi a0 ,s10 ,0		#1036
	call _global.getcount		#1036
	sw s9 ,72(sp)
	addi s9 ,a0 ,0		#1036
	sw s9 ,1084(sp)		#1037
	lw s9 ,2104(sp)		#1038
	addi a0 ,s9 ,0		#1039
	call toString		#1039
	sw s8 ,68(sp)
	addi s8 ,a0 ,0		#1039
	sw s7 ,64(sp)
	lui s7 ,%hi(_str1)		#1040
	addi s7 ,s7 ,%lo(_str1)		#1040
	sw s6 ,60(sp)
	addi s6 ,zero ,4		#1040
	add s6 ,s7 ,s6		#1040
	sw s5 ,56(sp)
	lui s5 ,%hi(_str1_contents)		#1041
	addi s5 ,s5 ,%lo(_str1_contents)		#1041
	sw s5 ,0(s6)		#1041
	addi a0 ,s8 ,0		#1042
	lui s7 ,%hi(_str1)		#1042
	addi s7 ,s7 ,%lo(_str1)		#1042
	addi a1 ,s7 ,0		#1042
	call append		#1042
	addi s5 ,a0 ,0		#1042
	addi a0 ,s5 ,0		#1043
	call print		#1043
	sw s4 ,52(sp)
	addi s4 ,a0 ,0		#1043
	sw s3 ,48(sp)
	addi s3 ,zero ,0		#1044
	sw s3 ,2108(sp)		#1045
	j End5		#1046
End5:
	lw s3 ,2108(sp)		#1
	addi a0 ,s3 ,0		#2
	lw s3 ,0(sp)
	lw s4 ,4(sp)
	lw s5 ,8(sp)
	lw s11 ,12(sp)
	lw s6 ,16(sp)
	lw s10 ,20(sp)
	lw s7 ,24(sp)
	lw s8 ,28(sp)
	lw s9 ,32(sp)
	lw s0 ,36(sp)
	lw s1 ,40(sp)
	lw s2 ,44(sp)
	lw ra ,2116(sp)
	addi sp ,sp ,2120
	ret

	.globl	_global._init
_global._init:
	addi sp ,sp ,-8
	sw ra ,4(sp)
Start0:
	j End1		#1
End1:
	lw ra ,4(sp)
	addi sp ,sp ,8
	ret

	.data
count:
	.word	0
	.size	8

	.data
_str1_contents:
	.asciz	" "
	.size	1

	.data
_str1:
	.word	1
	.word	0
	.size	4
