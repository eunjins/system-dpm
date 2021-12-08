package kr.co.dpm.system.script;

import okhttp3.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.net.ConnectException;

@Repository
public class ScriptFileRepositoryImpl implements ScriptFileRepository {
    private static final Logger logger = LogManager.getLogger(ScriptController.class);
    private static final MediaType MULTIPART = MediaType.parse("multipart/form-data");

    @Value("${protocol}")
    private String http;

    @Value("${port}")
    private String port;

    @Value("${url}")
    private String url;

    @Override
    public boolean distribute(File classFile, String encryptResult, String ip) {
        try {
            String requestUrl = http + ip + port + url;

            OkHttpClient client = new OkHttpClient();

            RequestBody requestBody = new MultipartBody.Builder()
                    .setType(MultipartBody.FORM)
                    .addFormDataPart("encryptId", encryptResult)
                    .addFormDataPart("scriptFile", classFile.getName()
                            , RequestBody.create(MULTIPART, classFile)).build();

            Request request = new Request.Builder()
                    .url(requestUrl)
                    .post(requestBody)
                    .build();

            Response response = client.newCall(request).execute();

            ResponseBody responseBody = response.body();
            JSONObject jsonResponse = new JSONObject(responseBody.string());
            if ("200".equals(jsonResponse.getString("code"))) {
                logger.info("-------> 에이전트 배포 성공");

                return true;
            }
            logger.info("-------> 에이전트 배포 오류 : " + jsonResponse.getString("message"));
            logger.info("------> 배포 실패 ");
        } catch (ConnectException e) {
            logger.info("------> 연결 되지 않은 디바이스");
        } catch (Exception e) {
            logger.info("------> 연결 되지 않은 디바이스");
        }

        return false;
    }
}
