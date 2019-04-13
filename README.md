# netty-study

### 一、netty使用

    1.http服务器
    2.RPC框架
    3.长连接服务器


### 二、protocol buffers使用

netty对Google protocol buffers的原生支持

    使用protobuf：
    1.定义proto消息体文件
    2.通过proto编译器对消息体文件进行编译，生成对于的java类文件
    3.使用protobuf的API进行操作
    4.netty使用protobuf
    5.protobuf最佳实践

### 三、thrift使用
    1.定义thrift文件信息，添加thrift依赖
    
### 四、grpc的使用
    1.添加插件依赖
    2.添加grpc demo
    3.服务端流式数据通信
    4.客户端流式数据通信
    5.双向流式数据通信
    
### 五java nio
    1.IntBuffer和ByteBuffer的使用
    2.文件通道用法
    3.分片ByteBuffer使用
    4.堆外内存DirectByteBuffer的使用
    5.readOnly buffer的使用
    6.MappedByteBuffer使用
    7.scattering和gathering