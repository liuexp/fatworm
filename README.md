Fatworm
=======================

meow

Warning
=====================
* Always push to git before git rebase trunk and git svn dcommit

TODO
====================
* Redesign Logic plan Group procedure.
* select (a + b) as t, max(c) from A group by t;  // this makes sense
* select max(a + b) as t from A group by t; // Error: can not group on 't'
* Call src.eval(env) everytime before next().
* FetchTable Scan.
* Finish data manipulation language.
* Before Order expand the table first.
* If resolve alias simply by renaming, then those in having must also be renamed.

Reference
====================

[gitsvn](http://stackoverflow.com/questions/661018/pushing-an-existing-git-repository-to-svn)

[SQL-2003 BNF](http://savage.net.au/SQL/sql-2003-2.bnf.html)

[MySQL Internals](https://dev.mysql.com/doc/internals/en/index.html)
