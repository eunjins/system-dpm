package kr.co.dpm.system.script;

import okhttp3.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.io.File;

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
    public boolean distribute(File classFile, String encryptResult, String ip) throws Exception {
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
                return true;
            }

            logger.error("                           DISTRIBUTE ERROR   :   " + jsonResponse.getString("message")                );
        } catch (Exception e) {
            logger.error("                           DISCONNECT                                 ");
        }

        return false;
    }
}
