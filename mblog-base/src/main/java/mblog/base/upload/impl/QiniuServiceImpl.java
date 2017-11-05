package mblog.base.upload.impl;


import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import mblog.base.context.Global;
import mblog.base.upload.QiniuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;


@Service(value = "qiniuSerivce")
public class QiniuServiceImpl implements QiniuService {

    private Logger logger = LoggerFactory.getLogger(QiniuService.class);
    /**
     * 生成客户端上传token
     * @return
     */
    @Override
    public String generateUptoken() {

        String ak = Global.getConfig("qiniu.accesskey");
        String sk = Global.getConfig("qiniu.secretkey");
        String bucket = Global.getConfig("qiniu.bucket");
        Auth auth = Auth.create(ak,sk);
        String upToken = auth.uploadToken(bucket);
        return upToken;
    }

    /**
     * 上传字节数组
     *
     * @param bytes
     * @return
     */
    @Override
    public String uploadBytes(byte[] bytes,String key) {
        String upToken = generateUptoken();
        Configuration configuration = new Configuration(Zone.autoZone());
        UploadManager uploadManager = new UploadManager(configuration);
        try {
            uploadManager.put(bytes, key, upToken);
        } catch (QiniuException e) {
            logger.error("上传字节数组到七牛出错{}{}",bytes,e);
        }
        return key;
    }

    /**
     * 上传文件到七牛
     *
     * @param file
     * @param key
     * @return
     */
    @Override
    public String upload(File file, String key) {
        String upToken = generateUptoken();
        Configuration configuration = new Configuration(Zone.autoZone());
        UploadManager uploadManager = new UploadManager(configuration);
        try {
            uploadManager.put(file,key,upToken);
        } catch (QiniuException e) {
            logger.error("上传字节数组到七牛出错{}{}",file,e);
        }
        return key;
    }



}
