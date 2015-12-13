# JPJson
JPJson is a library to deal with Json data in Java supported by JParsec.


当不知道接口怎么设计,无从下手的时候,可以先写写测试.所有的目标就会变得清晰

TODO: 数值那里为了健壮性应该考虑更大的数字的情况,而不是简单的用long存一下,如果数据是巨大的数值的话,改用大数字类型.
      调整多个可能分支的次序,优化性能.
      更改转义字符匹配时的case语句,改成查表法
      遇到的比较大的困难,泛型擦除导致无法获取Arraylist内元素的信息
      基本功能完成
      Gson就算把构造函数私有 或者没有提供默认构造函数,仍然可以成功.需要研究一下这个黑科技
