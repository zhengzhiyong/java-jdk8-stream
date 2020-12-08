#####学习链接： https://blog.csdn.net/u011240877/article/details/53351188
#一、HashMap的特点
###### 结合平时使用，可以了解到 HashMap 大概具有以下特点：
######  1、底层实现是 链表数组，JDK 8 后又加了 红黑树
######  2、实现了 Map 全部的方法
######  3、key 用 Set 存放，所以想做到 key 不允许重复，key 对应的类需要重写 hashCode 和 equals 方法
######  4、允许空键和空值（但空键只有一个，且放在第一位，下面会介绍）
######  5、元素是无序的，而且顺序会不定时改变
######  6、插入、获取的时间复杂度基本是 O(1)（前提是有适当的哈希函数，让元素分布在均匀的位置）
######  7、遍历整个 Map 需要的时间与 桶(数组) 的长度成正比（因此初始化时 HashMap 的容量不宜太大）
######  8、两个关键因子：初始容量、加载因子
######  9、除了不允许 null 并且同步，Hashtable 几乎和他一样。


#二、HashMap 的 13 个成员变量
###### 1.默认初始容量：16，必须是 2 的整数次方
###### static final int DEFAULT_INITIAL_CAPACITY = 1 << 4; 

######2.默认加载因子的大小：0.75，可不是随便的，结合时间和空间效率考虑得到的
######static final float DEFAULT_LOAD_FACTOR = 0.75f;

######3.最大容量： 2^ 30 次方
######static final int MAXIMUM_CAPACITY = 1 << 30;

######4.当前 HashMap 修改的次数，这个变量用来保证 fail-fast 机制
######transient int modCount;

######5.阈值，下次需要扩容时的值，等于 容量*加载因子
######大于threshold的时候就开始扩容
######int threshold;

######6.树形阈值：JDK 1.8 新增的，当使用 树 而不是列表来作为桶时使用
######    binCount >= TREEIFY_THRESHOLD - 1,其实是>=8，从链表转换为红黑树
######static final int TREEIFY_THRESHOLD = 8;

######7.非树形阈值：也是 1.8 新增的，扩容时分裂一个树形桶的阈值，要比 TREEIFY_THRESHOLD 小
######   lc <= UNTREEIFY_THRESHOLD，其实是<=6的时候从红黑树转换为链表
######static final int UNTREEIFY_THRESHOLD = 6;

######8.树形最小容量：桶可能是树的哈希表的最小容量。至少是 TREEIFY_THRESHOLD 的 4 倍，这样能避免扩容时的冲突
######static final int MIN_TREEIFY_CAPACITY = 64;

######9.缓存的 键值对集合（另外两个视图：keySet 和 values 是在 AbstractMap 中声明的）
######transient Set<Map.Entry<K,V>> entrySet;

######10.哈希表中的链表数组
######transient Node<K,V>[] table;

######11.键值对的数量
######transient int size;

######12.哈希表的加载因子
######final float loadFactor;

######13、序列化  
######private static final long serialVersionUID = 362498820763181265L;

#三、插入逻辑如下:
######1、先调用 hash() 方法计算哈希值
######2、然后调用 putVal() 方法中根据哈希值进行相关操作
######3、如果当前 哈希表内容为空，新建一个哈希表
######4、如果要插入的桶中没有元素，新建个节点并放进去
######5、否则从桶中第一个元素开始查找哈希值对应位置
######6、如果桶中第一个元素的哈希值和要添加的一样，替换，结束查找
######7、如果第一个元素不一样，而且当前采用的还是 JDK 8 以后的树形节点，调用 putTreeVal() 进行插入
######8、否则还是从传统的链表数组中查找、替换，结束查找
######9、当这个桶内链表个数大于等于 8，就要调用 treeifyBin() 方法进行树形化
######10、最后检查是否需要扩容
####插入过程中涉及到几个其他关键的方法 ：

######hash():计算对应的位置
######resize():扩容
######putTreeVal():树形节点的插入
######treeifyBin():树形化容器



#四、HashMap 中的哈希函数 hash()
######HashMap 中通过将传入键的 hashCode 进行无符号右移 16 位，然后进行按位异或，得到这个键的哈希值。
######
######static final int hash(Object key) {
######int h;
######return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
######}



#五、HashMap 中的初始化/扩容方法 resize()
#####扩容过程中几个关键的点：
######1、新初始化哈希表时，容量为默认容量，阈值为 容量*加载因子
######2、已有哈希表扩容时，容量、阈值均翻倍
######3、如果之前这个桶的节点类型是树，需要把新哈希表里当前桶也变成树形结构
######4、复制给新哈希表中需要重新索引（rehash），这里采用的计算方法是
######5、e.hash & (newCap - 1)，等价于 e.hash % newCap
######6、结合扩容源码可以发现扩容的确开销很大，需要迭代所有的元素，rehash、赋值，还得保留原来的数据结构。
######7、所以在使用的时候，最好在初始化的时候就指定好 HashMap 的长度，尽量避免频繁 resize()。


#六、HashMap 的获取方法 get() **
######HashMap 另外一个经常使用的方法就是 get(key)，返回键对应的值:
######如果 HashMap 中包含一个键值对 k-v 满足：
######  (e = getNode(hash(key), key)) == null ? null : e.value;
######查找 方法比较简单:

######1、先计算哈希值;
######2、然后再用 (n - 1) & hash 计算出桶的位置;
######3、在桶里的链表进行遍历查找。
######4、时间复杂度一般跟链表长度有关，因此哈希算法越好，元素分布越均匀，get() 方法就越快，不然遍历一条长链表，太慢了。
######5、不过在 JDK 1.8 以后 HashMap 新增了红黑树节点，优化这种极端情况下的性能问题。


#七、补充
####1、缺点：线程不安全，不同步，可用Map m = Collections.synchronizedMap(new HashMap(...));解决
####2、快速失败机制：HashMap 三个视图返回的迭代器都是 fail-fast 的：如果在迭代时使用非迭代器方法修改了 map 的内容、结构，迭代器就会报 ConcurrentModificationException 的错。
####3、当 HashMap 中有大量的元素都存放到同一个桶中时，这时候哈希表里只有一个桶，这个桶下有一条长长的链表，这个时候 HashMap 就相当于一个单链表，假如单链表有 n 个元素，遍历的时间复杂度就是 O(n)，完全失去了它的优势。
####针对这种情况，JDK 1.8 中引用了 红黑树（时间复杂度为 O(logn)） 优化这个问题
####4、为什么哈希表容量一定要是 2的整数次幂?
######4.1、首先，capacity 为 2的整数次幂的话，计算桶的位置 h&(length-1) 就相当于对 length 取模，提升了计算效率；
######4.2、其次，capacity 为 2 的整数次幂的话，为偶数，这样 capacity-1 为奇数，奇数的最后一位是 1，这样便保证了 h&(capacity-1) 的最后一位可能为 0，也可能为 1（这取决于h的值），即与后的结果可能为偶数，也可能为奇数，这样便可以保证散列的均匀性；
######4.3、而如果 capacity 为奇数的话，很明显 capacity-1 为偶数，它的最后一位是 0，这样 h&(capacity-1) 的最后一位肯定为 0，即只能为偶数，这样任何 hash 值都只会被散列到数组的偶数下标位置上，这便浪费了近一半的空间。
######4.4、摘自： https://github.com/GeniusVJR/LearningNotes/blob/master/Part2/JavaSE/HashMap%E6%BA%90%E7%A0%81%E5%89%96%E6%9E%90.md
######4.5、因此，哈希表容量取 2 的整数次幂，有以下 2 点好处：
######4.6、使用减法替代取模，提升计算效率；
######4.7、为了使不同 hash 值发生碰撞的概率更小，尽可能促使元素在哈希表中均匀地散列。



####5.HashMap 允许 key, value 为 null，同时他们都保存在第一个桶中。
######看代码，添加时先调用 hash()：
######public V put(K key, V value) {
######    //先调用 hash() 方法计算位置
######    return putVal(hash(key), key, value, false, true);
######}
######而在计算哈希值时，如果为 null 就直接返回 0 ，说明了这一点：
######static final int hash(Object key) {
######    int h;
######    return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
######}
####6.HashMap 中 equals() 和 hashCode() 有什么作用？
######HashMap 的添加、获取时需要通过 key 的 hashCode() 进行 hash()，然后计算下标 ( n-1 & hash)，从而获得要找的同的位置。
######当发生冲突（碰撞）时，利用 key.equals() 方法去链表或树中去查找对应的节点。
####7.你知道 hash 的实现吗？为什么要这样实现？
######在 JDK 1.8 的实现中，是通过 hashCode() 的高16位异或低16位实现的：(h = k.hashCode()) ^ (h >>> 16)。
######主要是从速度、功效、质量 来考虑的，这么做可以在桶的 n 比较小的时候，保证高低 bit 都参与到 hash 的计算中，同时位运算不会有太大的开销。
######摘自：http://yikun.github.io/2015/04/01/Java-HashMap%E5%B7%A5%E4%BD%9C%E5%8E%9F%E7%90%86%E5%8F%8A%E5%AE%9E%E7%8E%B0/