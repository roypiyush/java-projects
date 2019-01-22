namespace java com.personal.thrift
namespace py com.personal.thrift


service MultiplicationService {
    string multiply(1:i32 n1, 2:i32 n2)
}

service FileService {
    string sendFile(1: binary data, 2: string name)
    binary downloadFile(1: string filePath, 2: i32 offset, 3: i32 length)
}