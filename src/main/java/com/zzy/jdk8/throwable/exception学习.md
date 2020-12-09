#Throwable是Java中处理异常的超级类，Throwable可以分为Error和Exception两类。
##错误：错误是一种严重的情况，应用程序不能尝试处理该错误。错误主要是由应用程序运行的环境引起的。在编译时不检查错误，也不必（但可以）捕捉或处理错误。
###在java中，程序员根本不必担心错误，因为错误表明系统或环境有问题。以下是一些错误：
###OutOfMemory错误、堆栈溢出错误、链接错误
##异常：异常是由应用程序引起的，异常是从Throwable类派生的。例外情况可分为两类：
###检查的异常也称为编译时异常
###未检查的异常也称为运行时异常

###throws和throw区别
#####throws：用来声明一个方法可能产生的所有异常，不做任何处理而是将异常往上传，谁调用我我就抛给谁。
######用在方法声明后面，跟的是异常类名
######可以跟多个异常类名，用逗号隔开
######表示抛出异常，由该方法的调用者来处理
######throws表示出现异常的一种可能性，并不一定会发生这些异常
#####throw：则是用来抛出一个具体的异常类型。
######用在方法体内，跟的是异常对象名
######只能抛出一个异常对、象名
######表示抛出异常，由方法体内的语句处理
######throw则是抛出了异常，执行throw则一定抛出了某种异常

###Java 异常处理关键字
######try、catch、finally、throw、throws
######常见异常：ArrayIndexOutOfBoundsException、NumberFormatException、NullPointerException、ArithmeticException
######Java异常学习地址： https://chercher.tech/java-programming/exceptions-java
######try块中的代码可能引发异常，也可能不引发异常
######try block或catch或finally blocks不能单独退出
######catch或finally或两者都应遵循try块
######当finally块存在时，catch块是可选的
######当catch块存在时，finally块是可选的
######当java中没有发生异常（但在python语言中存在）时，没有执行块
######没有try语句就不能有catch或finally block。
######我们不能在try..catch..finally block之间编写任何代码。
######try..catch块可以嵌套（在另一个try或catch或finally中尝试）
######我们只能有一个最后一个finally和一个try 