package mblog.base.upload;

import java.io.File;
import java.io.OutputStream;

public interface QiniuService {

    /**
     * 生成上传token
     */
    String generateUptoken();

    /**
     * 上传字节数组
     * @param bytes
     * @return
     */
    String uploadBytes(byte[] bytes, String key);

    /**
     * 上传文件到七牛
     * @param file
     * @param key
     * @return
     */
    String upload(File file, String key);

}
