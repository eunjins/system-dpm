package kr.co.dpm.system.script;

import kr.co.dpm.system.device.Device;
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

    @Value("${protocol}")
    private String http;

    @Value("${port}")
    private String port;

    @Value("${url}")
    private String url;

    @Override
    public boolean distribute(MultipartFile classFile, String encryptResult, String ip) {
        try {
            File convertFile = new File(classFile.getOriginalFilename());
            convertFile.createNewFile();
            FileOutputStream fileOutputStream = new FileOutputStream(convertFile);
            fileOutputStream.write(classFile.getBytes());
            fileOutputStream.close();

            String requestUrl = http + ip + port + url;

            OkHttpClient client = new OkHttpClient();

            RequestBody requestBody = new MultipartBody.Builder().
                                      setType(MultipartBody.FORM).
                                      addFormDataPart("encryptId", encryptResult).
                                      addFormDataPart("scriptFile",
                                                      classFile.getOriginalFilename(),
                                                      RequestBody.create(MULTIPART,
                                                              convertFile)).build();

            Request request = new Request.Builder().
                                          url(requestUrl).
                                          post(requestBody).
                                          build();

            Response response = client.newCall(request).execute();

            if (response.isSuccessful()) {
                ResponseBody responseBody = response.body();
                String responseString = responseBody.string();

                return true;
            }
        } catch(Exception e) {
            e.printStackTrace();
        }

        return false;
    }
}
