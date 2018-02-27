CodeReview

[如何用 gitlab 做 code review](http://blog.csdn.net/legend0011/article/details/45585575)
[让 Code Review成为一种习惯](http://www.flickering.cn/uncategorized/2014/08/%E8%AE%A9-code-review%E6%88%90%E4%B8%BA%E4%B8%80%E7%A7%8D%E4%B9%A0%E6%83%AF/)
[用Gitlab进行强制代码Review]http://blog.csdn.net/sinat_36735108/article/details/56004027


如何用 Gitlab 做团队内的 Code Review

基于分支的代码 Review
新建 Issue (无论是 bug 还是 feature), 描述背景或问题,

本地创建分支 issue#123 (123是 issue 的 ID), 围绕关联 issue 进行 program -> commit -> push,

新建 Merge Request 从 issue#123 到 master, 并指派给项目 Owner (或合适 Reviewer) ,

被指派人完成代码审查后， 执行代码合并, 同时删除分支 issue#123 .

多人 Review
提交 Merge Request 后， 被指派人可通过 @someone 邀请一个或多个额外的 Reviewer （它们会收到邮件通知）

被邀请的 Reviewer 看过代码后， 可回复:thumbsup:或+1表示通过， 反之给出修改建议。

Protect Branch
为了避免意外的代码提交或合并， 项目 Owner 或 Master 可以在项目 Settings -> Protected branches 添加受保护的分支，进而引导代码提交是基于 Merge Request 的形式经过 review 之后才合并到目标分支上。


利用分支
从origin/master分支分出来一个dev分支： 
git checkout -b dev origin/master
在dev分支上进行一些更改
add到本地stage 
git add/rm <file / directory>
commit到本地repository 
git commit -m "<notes>"
push到远程分支 
git push origin <远程分支>:<本地分支>

代码push到origin服务器后，可以去网页上发起Merge Request
合并分支
 git checkout master;
 git pull;
 git checkout dev;
 git fetch origin master;
 git rebase master;
删除分支 dev
【git 删除本地分支】
git branch -D br
【git 删除远程分支】
git push origin :br  (origin 后面有空格)

git代码库回滚: 指的是将代码库某分支退回到以前的某个commit id
【本地代码库回滚】：
git reset --hard commit-id :回滚到commit-id，讲commit-id之后提交的commit都去除
git reset --hard HEAD~3：将最近3次的提交回滚

git stash: 备份当前的工作区的内容，从最近的一次提交中读取相关内容，让工作区保证和上次提交的内容一致。同时，将当前的工作区内容保存到Git栈中。
git stash pop: 从Git栈中读取最近一次保存的内容，恢复工作区的相关内容。由于可能存在多个Stash的内容，所以用栈来管理，pop会从最近的一个stash中读取内容并恢复。
git stash list: 显示Git栈内的所有备份，可以利用这个列表来决定从那个地方恢复。
git stash clear: 清空Git栈。此时使用gitg等图形化工具会发现，原来stash的哪些节点都消失了。

2.放弃本地修改，直接覆盖之
git reset --hard
git pull

Already up-to-date. //已经更新到最新。

新建本地分支后将本地分支推送到远程库, 使用git pull 或者 git push 的时候报错
git branch --set-upstream-to=origin/远程分支的名字 本地分支的名字   
git branch --set-upstream-to=origin/master master

.git目录下的config文件：
[remote "origin"]
	url = http://git.up360.com/baoyuwei/up360-service-user.git
	fetch = +refs/heads/*:refs/remotes/origin/*

使用git branch -vv  可以查看本地分支和远程分支的关联关系

查看远程分支日志：
git log origin/master -n 3
-p 选项展开显示每次提交的内容差异，用 -2 则仅显示最近的两次更新：

查看 dev 有，而 master 中没有的：
git log dev ^master 

同理查看 master 中有，而 dev 中没有的内容：
git log master ^dev

查看 dev 中比 master 中多提交了哪些内容：
git log master..dev

不知道谁提交的多谁提交的少，单纯想知道有什么不一样：
git log dev...master

再显示出每个提交是在哪个分支上：
git log --left-right dev...master

git log -p dubbo01...dubbo02

还可以看看远程分支和本地分支的不同：

git log origin/master..HEAD

本地分支与远程分支相关联
git pull origin master


