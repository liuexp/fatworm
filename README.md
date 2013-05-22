Fatworm
=======================

Warning
=====================
* Always push to git before git rebase trunk and git svn dcommit

TODO
====================
* so strange that fake flush within transaction didn't work so well as expected.
* index, index, index. select pusher.
* watch for BCursor, after deletion there might be empty pages.
* Btree, every node fills up a page. for int/float/long key it's okay just use a fixed length page. for var-length page, should I use extra page or var-length fanout?
* comparisons with Mysql
* comparisons with NULL: store as NULL, only convert it on getObject; see MySQL's result on 

                select * from InfraTest where k <= 2 or 2 <= k

* Extract the expanding table procedure(on those expressions without aggregate) to run it before GROUP and ORDER
* If resolve alias simply by renaming, then those in having must also be renamed.
* Figure out general expressions renaming so that Select plan can be pushed beyond Rename.

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
