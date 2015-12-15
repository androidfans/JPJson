#JPJSon
JPJson is an open source json data parse library. It's parse function implemented based on the [JParsec library](https://github.com/androidfans/JParsec).It is also a largish demo in a fully functional project.
There are some interesting episode i hava met when i develop the library , you can refer the blog[开发中遇到的小问题](http://blog.csdn.net/u012990751/article/details/50307079).
The interfaces JPJson offered imitate the Google's Gson library, the user just need to follow these steps:

* define a JavaBean
* get the json String
* call the fromJson() method which need the Class object and json String
* define a JavaBean's reference to accept the result

next is the code example:
```Java
//define the JavaBean
class CHS{
    //set the authority arbitrary
    //use the generic type to indicate what are the List stored
    private List<CH> ch;
}
class CH{
    public String names;
    public List<Integer> datas;
}

//code for using the interface
String json = "{\"ch\":{\"names\":\"怡美家园\",\"datas\":[1,2,3,4,5,6,7,8]}}"
JPJson jpJson = new JPJson();
CHS chs = jpJson.fromJson(json,CHS.class);
//now you will get a parsed CHS object
```
#JPJSon
JPJson是一个开源的json解析库,解析部分主要基于[JParsec](https://github.com/androidfans/JParsec)实现.同时也作为JParsec解析工具的一个在稍大型项目中的完整演示.
在开发JPJson的过程中遇到很多有意思的小问题,具体可以参见我的这篇博文[开发中遇到的小问题](http://blog.csdn.net/u012990751/article/details/50307079)
其中,JPJson的接口设计模仿了Google的Gson库,使用者只需要做到如下几步:

* 定义一个JavaBean
* 获取json字符串
* 调用fromJson()方法,传入JavaBean的Class对象和json字符串
* 定义一个JavaBean的引用接收返回结果即可

下面是使用的完整代码示例:
```Java
//首先是JavaBean的定义
class CHS{
    //权限修饰符可以任意设置
    //使用泛型指定list里面所存放的具体类型
    private List<CH> ch;
}
class CH{
    public String names;
    public List<Integer> datas;
}
//函数调用的代码
String json = "{\"ch\":{\"names\":\"怡美家园\",\"datas\":[1,2,3,4,5,6,7,8]}}"
JPJson jpJson = new JPJson();
CHS chs = jpJson.fromJson(json,CHS.class);
//这样就可以获得一个被解析好的CHS对象啦
```
