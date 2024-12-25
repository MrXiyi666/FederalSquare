# FederalSquare
#
**软件介绍**
#
#
#
**phpfile 里面有服务器端源代码 上传到服务器 federal-square 文件夹内就可以**
#
联邦广场是搭建**私人社区**的开源方案
#
通过最便宜的**虚拟主机** 即可搭建**私人社区** 可以发文章 评论 收藏 还有功能合适的图片管理器
#
当然如果你使用的是云主机 或者 物理主机 vps 等等 **只要域名可以正常访问后台的PHP文件** 也是可以正常使用的
#
此项目完全使用的是**文件读写模式 没有数据库** 就是单纯的把数据归类 保存在文件夹内
#
需要哪个文件就读取和写入哪个文件
#
图片数据也是以图片格式保存在服务器 比如 123.png 这就是一个图片可以下载保存的
#
你发表的文章 保存在自己的服务器里 可以自己备份管理
#
由于数据保存在自己的手里 所以不会有人窃取到你的隐私信息
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
#
#
**使用场景 说明**
#
#
#
我们可以组建社区
#
比如 小说社区 在里面写一些小说内容 发布在自己的社区里面 保存起来 当然你也可以公开你的域名还有密码 让别人查看你的社区动态
#
比如 风景社区 我们可以去旅游拍一些照片 发布在自己的社区里面 保存起来 当然你也可以公开你的域名还有密码 让别人查看你的社区动态
#
比如 二次元社区 我们可以分享二次元 动漫 游戏的内容 发布在自己的社区里面 保存起来 当然你也可以公开你的域名还有密码 让别人查看你的社区动态
#
比如 美食社区 我们可以发布自己制作的美食爱好等等 发布在自己的社区里面 保存起来 当然你也可以公开你的域名还有密码 让别人查看你的社区动态
#
而且 不会泄露你的隐私 也不会受到监视 你的内容并没有问题 是举报你的人它才有问题 不喜欢看 你可以不关注 真的是无语死啦 家人们 谁懂啊
#
你想想 我在我自己家里面 我想干嘛就干嘛 我又没在公共网络发布 凭什么管我
#
#
#
**后端代码说明**
#
#
#
**Access_PassWord.txt** 是社区的密码 直接修改里面的内容就可以改密码 留空 或者没有 这个文件 那么就没有密码
#
**Create_Account.php** 创建账号功能 没有此文件 就无法注册账号 发表文章
#
**Delete_File.php** 删除文件功能 删除文章 删除图片 删除数据的主要功能 
#
**Fun.php** 一个通用函数
#
**PassWord_Data.php** 服务器后端 验证密码 的功能
#
**Publish_Article.php** 发表文章的主要功能 很重要
#
**Read_Discuss_List.php** 读取评论的功能 没有此文件 评论无法正确读取
#
**Read_Folder_List.php** 读取列表的功能 大多数都需要 比如广场读取 收藏夹读取
#
**Read_Hot_List.php** 读取热门的功能 没有此文件 热门就无法获取
#
**Read_Txt.php** 读取文件的功能 每个地方都需要读取文件
#
**Upload_Image.php** 上传图片到服务器功能 上传到 账号内部文件夹 每个账号都独立
#
**Write_Txt.php** 写入文件功能 每个文件都需要经常写入
#
**http://federal-square.top/Create_Account.php**
#
**http://federal-square.top/Delete_File.php**
#
**http://federal-square.top/PassWord_Data.php**
#
**http://federal-square.top/Publish_Article.php**
#
**http://federal-square.top/Read_Discuss_List.php**
#
**http://federal-square.top/Read_Folder_List.php**
#
**http://federal-square.top/Read_Hot_List.php**
#
**http://federal-square.top/Read_Txt.php**
#
**http://federal-square.top/Upload_Image.php**
#
**http://federal-square.top/Write_Txt.php**
#
**http://federal-square.top/Account/xxx.json**
#
**http://federal-square.top/Discuss_Data/xxx.json**
#
**http://federal-square.top/Square_Data/xxx.json**
#
以上格式才正确
#
#
#
**更新说明**
#
#
#
v5.5更新信息<br>
时间函数得到了更新<br>
随机数提高到了999999999<br>
也就是说 999999999 之1的概率 文章会被重复<br>
基本不会重复
#
#
#
**搭建教程**
#
#
#
在虚拟主机根目录 创建**federal-square** 文件夹
#
把php文件上传到federal-square文件夹 并且配置好 域名和虚拟主机可以正常访问
#
创建
#
**Account**       保存账号个人信息
#
**Discuss_Data**  保存文章评论信息
#
**Square_Data**   保存广场文章信息
#
上面的是读取文章列表 php功能 以此举例正常访问即可
#
注意：需要的php文件在 phpfile 文件夹里面 全部上传到**federal-square**即可
#
如何设置访问密码 在**federal-square** 目录下面创建 **Access_PassWord.txt** 文件 填写里面的内容 即可设置密码
#
注意： 空格的话也算有效密码 对方忘记填写空格的话 访问也会失败
#
若没有 **Access_PassWord.txt** 或者 内容为空 都算没有密码
#
#
#
**开发者域名**
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
**结尾总结**
#
#
#
此项目目前在开发阶段 每个新版本可能不兼容
#
但是每个新版本都可以独立使用
#
#
#
#