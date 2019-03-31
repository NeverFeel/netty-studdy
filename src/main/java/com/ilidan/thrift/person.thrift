namespace java com.ilidan.thrift.person

//将thrift数据类型映射成java中的常见类型
typedef i16 short
typedef i32 int
typedef i64 long
typedef string String
typedef bool boolean

//声明结构体数据结构
struct Person{
    1: optional String username;
    2: optional int age;
    3: optional String address;
}

//声明异常信息
exception DataException{
    1: optional String message;
    2: optional String callBack;
    3: optional String date;
}

//声明服务接口信息
service PersonService{
    Person findPersonByUsername(1:required String username) throws(1: DataException dataException);
    void savePerson(1:Person person) throws (1:DataException dataException);
}