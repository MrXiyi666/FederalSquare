# FederalSquare
#
# 软件介绍
# 利用虚拟主机来搭建社区 然后引用其他的的虚拟主机 进行互动交流 的开源项目
# 可以发布文字 图片 视频 动态图片
# 新增了翻页模式 可以在菜单设置文章数量为10 或者其他数量 然后下一页翻页浏览 文章再多也不怕
# 新增了 游客模式 陌生人获取到你的域名和密码 没有注册账号的话 那么只能在广场时间线查看十个文章 其他功能不可用
# 游客也无法查看 视频 图片 动图 等详细信息
# 网盘模式 专门的界面管理所有的图片视频文件 图片视频文件大小限制在5MB
# 可以在发布页面直接输入 图片 视频 的链接 直接导入 这样就不占用自己的服务器资源了
# 可以收藏别人的文章 可以转发别人的文章到自己的服务器 也就是JSON数据转发到自己的服务器
# 可以在文章下面进行简单的评论交流 目前不支持太丰富的功能
# 此项目与2024年12月17号开发完成 并且不断的修改BUG 由于还在发展期 如有BUG 请体谅
#
#
# 优点
#
# 数据在自己的手里 还可以引用别人的域名 所以说 并不是单机社区 而是 可以互动的社区
# 一个域名30 一个虚拟空间主机70左右 一年100块 极低的价格 稳定的功能 非常适合小圈子使用
# 由于APP模式 所以你的数据也不会被 搜索引擎获取到 这也是很好的特点
#
#
# 缺点
#
#
#
# 由于虚拟主机的性能太小 所以不可以太花哨 虚拟主机的连接数量很少 每次只能有10个左右的连接数量 
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
视频文件不超过10MB都是可以的 毕竟是虚拟主机 空间有限 网速也有限 所以做了限制
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
# 后端代码说明
#
#
#
**Access_PassWord.txt** 是社区的密码 直接修改里面的内容就可以改密码 留空 或者 没有这个文件 那么就没有密码
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
**http://xxxx.com/federal-square/Create_Account.php**
#
**http://xxxx.com/federal-square/Delete_File.php**
#
**http://xxxx.com/federal-square/PassWord_Data.php**
#
**http://xxxx.com/federal-square/Publish_Article.php**
#
**http://xxxx.com/federal-square/Read_Discuss_List.php**
#
**http://xxxx.com/federal-square/Read_Folder_List.php**
#
**http://xxxx.com/federal-square/Read_Hot_List.php**
#
**http://xxxx.com/federal-square/Read_Txt.php**
#
**http://xxxx.com/federal-square/Upload_Image.php**
#
**http://xxxx.com/federal-square/Write_Txt.php**
#
**http://xxxx.com/federal-square/Account/xxx.json**
#
**http://xxxx.com/federal-square/Discuss_Data/xxx.json**
#
**http://xxxx.com/federal-square/Square_Data/xxx.json**
#
以上格式才正确
#
#
#
# 更新说明
#
#
#
# v5.8.0更新信息
#
新增了菜单功能 翻页功能
#
进一步完善了游客模式
#
#
# 搭建教程
#
#
#
在虚拟主机根目录 创建**federal-square** 文件夹
#
把php文件上传到federal-square文件夹 并且配置好 域名和虚拟主机可以正常访问
#
在 federal-square 文件夹内 创建如下三个文件夹
#
**Account**       保存账号个人信息
#
**Discuss_Data**  保存文章评论信息
#
**Square_Data**   保存广场文章信息
#
以上文件夹创建好以后不用管理 程序运行会自动生成 一些内容 比如账号 文章 等等 都会自动写入
#
比如 Account 文件夹内有一个账号 如果你把里面的账号文件夹删除了 那么 就相当于此账号不存在了 
#
比如 Account/XXXX/Data 这个文件夹如果删除的话 那么这个账号就没办法发表文章了 就相当于封禁
#
如何设置访问密码 在**federal-square** 目录下面创建 **Access_PassWord.txt** 文件 填写里面的内容 即可设置密码
#
注意： 空格的话也算有效密码 对方忘记填写空格的话 访问也会失败
#
若没有 **Access_PassWord.txt** 或者 内容为空 都算没有密码
#
# 如何禁用注册功能呢？
**禁用注册功能方法.txt** 这个文件 修改为 Create_Account.php 替换掉原本的Create_Account.php 即可禁用注册
#
#
#
![image](https://img1.lookpic.cn/2025/01/06/jiaocheng.png)
#
#
# 开发者域名
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
# 结尾总结
#
#
#
此项目目前还在开发阶段 遇到问题 很正常
#
如果遇到问题 可以清除数据从新开始
#
#
#
# 感谢赞助
如果您喜欢这个项目 那么你可以少许赞助 我会更有动力 谢谢 我喜欢人民币
#
![image](https://img1.lookpic.cn/2025/01/06/zanzhu.png)

