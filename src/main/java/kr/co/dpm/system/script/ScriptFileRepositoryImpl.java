package kr.co.dpm.system.script;

import okhttp3.*;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.net.NoRouteToHostException;
import java.net.SocketTimeoutException;

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

            RequestBody requestBody = new MultipartBody.Builder().
                                      setType(MultipartBody.FORM).
                                      addFormDataPart("encryptId", encryptResult).
                                      addFormDataPart("scriptFile",
                                              classFile.getName(),
                                                      RequestBody.create(MULTIPART,
                                                              classFile)).build();

            Request request = new Request.Builder().
                                          url(requestUrl).
                                          post(requestBody).
                                          build();

            Response response = client.newCall(request).execute();

            ResponseBody responseBody = response.body();
            JSONObject jsonResponse = new JSONObject(responseBody.string());

            if ("200".equals(jsonResponse.getString("code"))) {
                //convertFile.delete();
                logger.debug("-------> 에이전트 배포 성공");

                return true;
            }

            logger.debug("-------> 에이전트 배포 오류 : " + jsonResponse.getString("message"));
        } catch (NoRouteToHostException e) {
            e.printStackTrace();

        } catch (SocketTimeoutException e) {
            logger.debug("-------> " + ip + " 에이전트 연결 시간 초과");

        } catch (Exception e) {
            e.printStackTrace();

        } finally {
           //convertFile.delete();
        }

        logger.debug("------> 배포 실패 ");

        return false;
    }
}
