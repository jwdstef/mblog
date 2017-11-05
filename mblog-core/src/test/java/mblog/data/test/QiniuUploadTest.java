package mblog.data.test;

import mblog.base.upload.QiniuService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@ActiveProfiles("dev")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:/conf/spring-test.xml"})
public class QiniuUploadTest {

    @Resource(name = "qiniuSerivce")
    private QiniuService qiniuService;

    @Test
    public void getToken(){
        System.out.print(qiniuService.generateUptoken());
    }
}
