package kr.co.dpm.system.script;

import kr.co.dpm.system.device.Device;
import okhttp3.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.plugins.convert.TypeConverters;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.NoRouteToHostException;
import java.net.SocketTimeoutException;

@Component
public class ScriptFileRepositoryImpl implements ScriptFileRepository {
    private static final Logger logger = LogManager.getLogger(ScriptController.class);
    private static final MediaType MULTIPART = MediaType.parse("multipart/form-data");

    @Value("${path}")
    private String path;

    @Value("${protocol}")
    private String http;

    @Value("${port}")
    private String port;

    @Value("${url}")
    private String url;

    @Override
    public boolean distribute(MultipartFile classFile, String encryptResult, String ip) {
        File convertFile = null;
        try {
            // 여기서 클래스파일이 경로에 들어감
            convertFile = new File(path + File.separator + classFile.getOriginalFilename());
            //convertFile.createNewFile();
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

            ResponseBody responseBody = response.body();
            JSONObject jsonResponse = new JSONObject(responseBody.string());

            if ("200".equals(jsonResponse.getString("code"))) {
                convertFile.delete();
                logger.debug("-------> 에이전트 배포 성공");

                return true;
            }
            logger.debug("-------> 에이전트 배포 오류 : " + jsonResponse.getString("message"));

        } catch(NoRouteToHostException e) {
            e.printStackTrace();
        } catch(SocketTimeoutException e) {
            logger.debug("-------> " + ip + " 에이전트 연결 시간 초과");
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
           convertFile.delete();
        }
        logger.debug("------> 배포 실패 ");

        return false;
    }
}
