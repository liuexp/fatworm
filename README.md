Fatworm
=======================

meow

Warning
=====================
* Always push to git before git rebase trunk and git svn dcommit

TODO
====================
* Be careful with alias in Env.
* FetchTable Scan.
* Finish data manipulation language.
* Redesign Logic plan Group procedure, must expand the table first and do Project in 2 stages.
* select (a + b) as t, max(c) from A group by t;  // this makes sense
* select max(a + b) as t from A group by t; // Error: can not group on 't'
* Before Order expand the table first.
* If resolve alias simply by renaming, then those in having must also be renamed.

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
Reference
====================

[gitsvn](http://stackoverflow.com/questions/661018/pushing-an-existing-git-repository-to-svn)

[SQL-2003 BNF](http://savage.net.au/SQL/sql-2003-2.bnf.html)

[MySQL Internals](https://dev.mysql.com/doc/internals/en/index.html)
