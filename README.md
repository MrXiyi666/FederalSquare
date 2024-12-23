# FederalSquare
#======================软件介绍======================
#
#
#
phpfile 是服务器后端代码 复制上传就可以直接用
#
联邦广场是搭建私人社区的开源方案
#
通过最便宜的虚拟主机 即可搭建私人社区 可以发文章 评论 收藏 还有功能合适的图片管理器
#
当然如果你使用的是云主机 或者 物理主机 vps 等等 只要域名可以正常访问后台的PHP文件 也是可以正常使用的
#
此项目完全使用的是文件读写模式 没有数据库 就是单纯的把数据归类 保存在文件夹内
#
需要哪个文件就读取和写入哪个文件
#
图片数据也是以图片格式保存在服务器 比如 123.png 这就是一个图片可以下载保存的
#
你发表的文章 保存在自己的服务器里 可以自己备份管理
#
由于数据保存在自己的手里 所以不会有人窃取到你的隐私信息 
#
非常适合 同学 家人 朋友 小群体使用
#
不要向陌生人泄露你的域名以及密码 就可以保证绝对安全
#
非常适合那些喜欢日常上网不受陌生人监控骚扰的群体
#
小群体使用 不会再有人给你打电话 也不会再有人顺着网线找到你家里 对你进行真实
#
联邦广场可以通过引用对方的域名 获取到对方的文章数据 还可以在对方的文章下面评论
#
如果有两个同样的服务器都配置好了联邦广场的后台PHP文件的话
#
那么他们是可以相互引用 然后相互看到对方的文章 还可以评论 还可以收藏对方的文章到自己的服务器
#
联邦广场可以收藏别人的文章内容 文字会保存在自己的服务器 但是图片依然保存在对方的服务器 我们只是引用了对方的图片链接罢了
#
也就是说单纯的访问对方的文章 是使用了对方的服务器资源
#
大家一起分担服务器资源。
#
此项目 完全开源免费
#
我不对你服务器内的任何数据内容负责
#
#
#
#======================更新说明======================
#
#
#
注意： 5.4版本以后php后台代码修改了变量名 Read_PassWord 修改为 PassWord
#
#
#
#======================搭建教程======================
#
#
#
在虚拟主机根目录 创建federal-square 文件夹
#
把php文件上传到federal-square文件夹 并且配置好 域名和虚拟主机可以正常访问
#
创建
#
Account       保存账号个人信息
#
Discuss_Data  保存文章评论信息
#
Square_Data   保存广场文章信息
#
例如 http://federal-square.top/federal-square/Read_Folder_List.php
#
上面的是读取文章列表 php功能 以此举例正常访问即可
#
注意：需要的php文件在 phpfile 文件夹里面 全部上传到federal-square即可
#
如何设置访问密码 在federal-square 目录下面创建 Access_PassWord.txt 文件 填写里面的内容 即可设置密码
#
注意： 空格的话也算有效密码 对方忘记填写空格的话 访问也会失败
#
若没有 Access_PassWord.txt 或者 内容为空 都算没有密码
#
#
#
#======================开发者域名======================
#
#
#
我的-引用列表 添加下面的域名和密码
#
网址： http://federal-square.top/
#
密码： federal-square
#
可以获取开发者的更新信息 以及最新版本
#
#
#
#======================结尾总结======================
#
#
#
此项目目前在开发阶段 每个新版本可能不兼容
#
但是每个新版本都可以独立使用
#
#
#
#=================================软件介绍=================================
#
#