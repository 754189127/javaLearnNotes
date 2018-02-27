队列写法：

https://yq.aliyun.com/articles/55621
https://yq.aliyun.com/articles/66110



http://blog.csdn.net/luckyzhoustar/article/details/51286560

3.MQPushConsumer：Consumer的一种，应用通常向Consumer对象注册一个Listener接口，一旦收到消息，Consumer对象立刻回调Listener接口方法

4.MQPullConsumer：Consumer的一种，应用通常主动调用Consumer的拉消息方法从Broker拉消息，主动权由应用控制

http://blog.csdn.net/meilong_whpu/article/details/76950320

http://blog.csdn.net/quhongwei_zhanqiu/article/details/39143213

http://www.uml.org.cn/zjjs/201504021.asp?artid=16162

Producer最佳实践

1.发送消息注意事项

（1） 一个应用尽可能用一个 Topic，消息子类型用 tags 来标识，tags 可以由应用自由设置。只有发送消息设置了tags，消费方在订阅消息时，才可以利用 tags 在 broker 做消息过滤。

（2）每个消息在业务层面的唯一标识码，要设置到 keys 字段，方便将来定位消息丢失问题。服务器会为每个消息创建索引（哈希索引），应用可以通过 topic，key 来查询这条消息内容，以及消息被谁消费。由于是哈希索引，请务必保证 key 尽可能唯一，这样可以避免潜在的哈希冲突。

（3）消息发送成功或者失败，要打印消息日志，务必要打印 sendresult 和 key 字段

（4）send 消息方法，只要不抛异常，就代表发送成功。但是发送成功会有多个状态，在 sendResult 里定义

SEND_OK：消息发送成功

FLUSH_DISK_TIMEOUT：消息发送成功，但是服务器刷盘超时，消息已经进入服务器队列，只有此时服务器宕机，消息才会丢失

FLUSH_SLAVE_TIMEOUT：消息发送成功，但是服务器同步到 Slave 时超时，消息已经进入服务器队列，只有此时服务器宕机，消息才会丢失

SLAVE_NOT_AVAILABLE：消息发送成功，但是此时 slave 不可用，消息已经进入服务器队列，只有此时服务器宕机，消息才会丢失。对于精确发送顺序消息的应用，由于顺序消息的局限性，可能会涉及到主备自动切换问题，所以如果sendresult 中的 status 字段不等于 SEND_OK，就应该尝试重试。对于其他应用，则没有必要这样

（5）对于消息不可丢失应用，务必要有消息重发机制

3.选择 oneway 形式发送

一个 RPC 调用，通常是这样一个过程

（1）客户端发送请求到服务器

（2）服务器处理该请求

（3）服务器向客户端返回应答

所以一个 RPC 的耗时时间是上述三个步骤的总和，而某些场景要求耗时非常短，但是对可靠性要求并不高，例如日志收集类应用，此类应用可以采用 oneway 形式调用，oneway 形式只发送请求不等待应答，而发送请求在客户端实现层面仅仅是一个 os 系统调用的开销，即将数据写入客户端的 socket 缓冲区，此过程耗时通常在微秒级。

顺序队列：
1.把服务端配成单列。（目前默认是8列）
2.客户端，把数据放进单列。通过selector


1. producer发消息,设置一个延迟level值. 

“设置消息延时 10s 消费”的 Producer 端代码如下：

messageDelayLevel=1s 5s 10s 30s 1m 2m 3m 4m 5m 6m 7m 8m 9m 10m 20m 30m 1h 2h
```
Message msg = newMessage(topic, tags, keys, body);  
msg.setDelayTimeLevel(3);  
...  
SendResult sendResult = getMQProducer().send(msg);  
```

RocketMQ为了追求高性能，并不保证此特性，要求在业务上进行去重，也就是说消费消息要做到幂等性。RocketMQ虽然不能严格保证不重复，但是正常情况下很少会出现重复发送、消费情况，只有网络异常，Consumer启停等异常情况下会出现消息重复。

http://blog.csdn.net/zhanglianhai555/article/details/77005663

// 设置为广播消费模式(在默认情况下，就是集群消费（CLUSTERING）)
consumer.setMessageModel(MessageModel.BROADCASTING);

http://blog.csdn.net/a417930422/article/details/50700281


GroupName

分组。一个组代码一个消费队列。两个组就会消费两遍。

tag

单独获取某些tag的消息。一个组如果处理了TagA,则历史的TagB消息将不会被消费。

message.putUserProperty("a", String.valueOf(i));

consumer.subscribe("TopicTest", MessageSelector.bySql("a between 0 and 3");


消费完后的消息去哪里了？

消息的存储是一直存在于CommitLog中的，由于CommitLog是以文件为单位（而非消息）存在的，而且CommitLog的设计是只允许顺序写，且每个消息大小不定长，所以这决定了消息文件几乎不可能按照消息为单位删除（否则性能会极具下降，逻辑也非常复杂）。

所以消息被消费了，消息所占据的物理空间也不会立刻被回收。但消息既然一直没有删除，那RocketMQ怎么知道应该投递过的消息就不再投递？——答案是客户端自身维护——客户端拉取完消息之后，在响应体中，broker会返回下一次应该拉取的位置，PushConsumer通过这一个位置，更新自己下一次的pull请求。这样就保证了正常情况下，消息只会被投递一次。
什么时候清理物理消息文件？

那消息文件到底删不删，什么时候删？

消息存储在CommitLog之后，的确是会被清理的，但是这个清理只会在以下任一条件成立才会批量删除消息文件（CommitLog）：

    消息文件过期（默认72小时），且到达清理时点（默认是凌晨4点），删除过期文件。
    消息文件过期（默认72小时），且磁盘空间达到了水位线（默认75%），删除过期文件。
    磁盘已经达到必须释放的上限（85%水位线）的时候，则开始批量清理文件（无论是否过期），直到空间充足。

注：若磁盘空间达到危险水位线（默认90%），出于保护自身的目的，broker会拒绝写入服务。
