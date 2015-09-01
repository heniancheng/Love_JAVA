设计模式原则
1、单一职责原则（SRP）
一个原因引起变化，通常用于设计接口。
例：将UserInfo抽象出UserBO（业务对象），UserBL（业务逻辑）

2、里氏替换原则（LSP）
父类出现地方可以用子类替换，通常父类是接口或抽象类
＝：子类完全实现父类方法，子类可以有自己个性，
        覆盖或实现父类方法时参数可以放大，结果可以缩小（＝契约设计：前置条件，后置结果）
例：战士Soldier类拥有武器AbstractGun类，实现子类有步枪Rifle类、手枪Headgun类
       客户端战士可以用不同的实现子类代替武器类
        
3、依赖倒置原则（DIP）
面向拌接口编程
＝：父类不依赖子类，子类依赖父类
   高级模块不依赖低级模块，反之依赖
例：高级模块（抽象层）：IDriver接口、ICar接口
       低级模块（实现层）：Driver类、Benz类、BMW类
   
4、接口隔离原则（IIP）
建立单一接口
例：将IPettyGirl接口拆分成IGreatTemperamentGirl接口、IGoodBodyGirl接口

5、知识最少原则（KLP）：迪米特法则
对其他对象最少了解
＝：只与朋友交流；朋友也是有距离的
例：Teacher只与GroupLeader交流，GroupLeader与Girl交流

6、开闭原则（OCP）
对拓展开放，对修改关闭
例：在NovelBook在基础上拓展出OffNovelBook，模拟书故事书的打折