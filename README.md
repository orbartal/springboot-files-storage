# springboot-files-storage

A demo of a springboot universal file storage service to upload and download files with uid and metadata.

### Description

​A demo service to manage files. It contain 2 main API:
1. Upload a file to the service and get uid in response.
2. Download file using a uid that belong to the file.

The service store 2 different types of data:
1. The actual content of the file.
2. A meta data about the file including its uid and a key that enable to find it in the storage.

Both the metadata and the storgae can used different DBs in a why that is transparent to the service user.
So, the service API is the same regardless of how and where the files data is actually stores.

Options for file content storage:
1. A local dir on the server host machine. It can be Linux, Windows, Mac or any other OS. Physical, Virtual or Container.
2. A Redis DB. With UId as Key and File bytes as Value.
3. Hadoop cluster, with one or many data nodes. The Key is the file path in Hadoop and Value is Hadoop file.

Options for file content storage:
1. In memory Java Map between Key and Value.
2. Redis server

The selection between different DBs/implementation is done in Spring config application.properties properties:
file.metadata.service=
file.storage.service=

Each DB/Service might require additional config properies specefic to it such as DB host and port or storage dir path.

### Test
1. The project as inside of it in dir [test](https://github.com/orbartal/springboot-files-storage/tree/main/backend/src/test/java/orbartal/demo/springboot/files/storage) many tests that can be run from IDE or maven. They test connectiviy to DBs, server config or e2e upload and download of files.
2. An independent E2E tester: [springboot-files-storage-tester](https://github.com/orbartal/springboot-files-storage-tester).
It can be use to test the service and its indepent from any code of the service, only relay on its api.

​
### Related projects
1. A tester that enable to run e2e test of upload and download a file (using same UID for both actions): [springboot-files-storage-tester](https://github.com/orbartal/springboot-files-storage-tester)
2. A docker-compose that enable to run this serivce with different config properties and supporting DBS: [docker-tester-spring-files](https://github.com/orbartal/docker-tester-spring-files)
### Sources

​- [Spring Boot upload file to Redis](https://frontbackend.com/spring-boot/spring-boot-upload-file-to-redis) and [code](https://github.com/martinwojtus/tutorials/tree/master/spring-boot/upload-file-to-redis)
- [Set Up Containerize and Test a Single Hadoop Cluster using Docker and Docker compose](https://www.section.io/engineering-education/set-up-containerize-and-test-a-single-hadoop-cluster-using-docker-and-docker-compose/) and [code](https://github.com/big-data-europe/docker-hadoop)
- [Large files in REST API](https://dba-presents.com/jvm/java/278-large-files-in-rest-api)
- [webhdfs api: 2 steps to upload files](https://hadoop.apache.org/docs/r1.0.4/webhdfs.html#FsURIvsHTTP_URL), [Hadoop Creating a File](https://hevodata.com