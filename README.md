Fatworm
=======================
* Recall the Honor Code, and use at your own RISK!!!
* A database engine with BTree for index and tuned buffer manager for I/O.
* OPT: select pusher.
* OPT: order cleanup.
* OPT: precise env construction.
* OPT: precise index utilization: with certain greedy heuristics, this BTree index is comparably fast (difference<2%) as some classmate using purely in-memory TreeMap for indexing, and 2x~3x faster than others using BTree index.
* OPT: equivalent expressions propagation and constant propagation.

TODO
====================

Testing
======================
* select a,b from (select 1+2 as a, 2+3 as b, 3+4 as c) as d,(select 1+2 as e, 2+3 as f, 3+4 as g) as h
* select a from (select 1+2 as a) as b
* select a from (select 1+2 as a) as b where a = all(select (a+3)/2) order by a
        

        create database meow
        use meow
        create table a(a int, b int default 0)
        insert into a values(1)
        insert into a values(0)
        insert into a values(1,2)
        insert into a values(2,2)
        SELECT * FROM a WHERE a < ALL(SELECT b)
        create table p(c int)
        insert into p values(1)
        insert into p values(2)
        select * from p having max(c)=2
        drop table p
        drop table a
        

        create table t1(a int, b int)
        create table t2(a int, b int)
        create table t3(c int, d int)
        insert into t1 values (1,2)
        insert into t1 values (2,2)
        insert into t1 values (3,2)
        insert into t2 values (9,9)
        insert into t3 values (9,9)
        select * from t1 group by b having exists(select * from t3 group by c having sum(a) = 6)
        select * from t1 group by b having exists(select * from t2 group by b having sum(a) = 9)
        select 5-(a+b) as c from t1 order by c
        select 5-(a+b) as c from t1 order by a
        drop table t1
        drop table t2
        drop table t3

        create table x(a int, b int)
        insert into x values (1,2)
        insert into x values (2,3)
        insert into x values (3,3)
        select distinct b from x having b = count(b)-1
        drop table x

some meows and eees
=========
* It would be so much nicer to rewrite the parser in antlr4, so that logic plan is simultaneously generated.
* Translate to binary codes is much better than just leaving it with plan/scan trees, there could be so much more low-level optimizations for binary codes.
* For variable length records, it would be much better to use different page structure for variable length records and fixed length records, anyway I just treat almost everything as variable length.


Reference
====================

[gitsvn](http://stackoverflow.com/questions/661018/pushing-an-existing-git-repository-to-svn)

[SQL-2003 BNF](http://savage.net.au/SQL/sql-2003-2.bnf.html)

[MySQL Internals](https://dev.mysql.com/doc/internals/en/index.html)
