# 物料项目
采用SSH架构（Struts1-Spring-Hibrenate），供小白学习Java框架集成做参考

项目简介：
    这个项目是我在学习玩struts1和spring以及hibernate后做的一次练习，所以实际意义不大。主要的作用是可以用来练练手，把自己学习的技术，进行一次实战开发。
    其中碰到了不少的坑，框架版本依赖，缺少架包什么的，都很令人头疼。

项目功能：

    登录界面：
    
        1.国际化，这里采用的是静态国际化。在登录界面点击选择语言，这里实现了中文和英文两种。原理见代码
        
        2.登录功能----没有写注册功能，也没啥难度。就省掉了。
        
    使用界面：
    
        1.隐式地提供了拦截请求（拦截没有登录成功，想直接进行使用界面的请求），通过继承action类，做了一个代理，把验证操作切入到action中。
        
        2.添加物料功能
        
        3.删除物料
        
        4.查询了物料信息
        
        5.修改物料信息
        
        6.根据物料代码查询物料信息
        
        7.查询全部物料
        
        8.更新页面信息
        
        9.上传物料图片信息
        
        
        
如有不懂的地方请联系我：
 
                邮箱：if.chaos.else.zz@gmail.com
                
                QQ:773670863

        
     
        
        
        
        
