package kr.co.dpm.system.script;

import okhttp3.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.plugins.convert.TypeConverters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

@Component
public class ScriptFileRepositoryImpl implements ScriptFileRepository {
    private static final Logger logger = LogManager.getLogger(ScriptController.class);
    private static final MediaType MULTIPART = MediaType.parse("multipart/form-data");

    @Value("${tempPath}")
    private String tempPath;

    @Value("${agentUrl}")
    private String url;

    @Override
    public boolean distribute(MultipartFile classFile, String encryptResult) {
        logger.debug("### sciprt OKHTTP 배포합니다.");
        try {
            File convertFile = new File(classFile.getOriginalFilename());
            convertFile.createNewFile();
            FileOutputStream fileOutputStream = new FileOutputStream(convertFile);
            fileOutputStream.write(classFile.getBytes());
            fileOutputStream.close();
            logger.debug("### sciprt 파일 변환 완료");

//                File tempFile = new File(tempPath);
//                if (!tempFile.isDirectory()) {
//                    tempFile.mkdir();
//                }
//                classFile.transferTo(new File(
//                        tempPath + File.separator + classFile.getOriginalFilename()));

            OkHttpClient client = new OkHttpClient();

            RequestBody requestBody = new MultipartBody.Builder().
                                      setType(MultipartBody.FORM).
                                      addFormDataPart("encryptId", encryptResult).
                                      addFormDataPart("scriptFile",
                                                      classFile.getOriginalFilename(),
                                                      RequestBody.create(MULTIPART,
                                                              convertFile)).build();

            Request request = new Request.Builder().
                                          url(url).
                                          post(requestBody).
                                          build();

            Response response = client.newCall(request).execute();
            logger.debug("### sciprt 배포 실행");

            if (response.isSuccessful()) {
                logger.debug("### sciprt 배포 응답");

                ResponseBody responseBody = response.body();
                String responseString = responseBody.string();

                logger.debug(" 응답 문자열 : " + responseString);

                return true;
            }
        } catch(Exception e) {
            e.printStackTrace();
        }

        return true;
    }
}
