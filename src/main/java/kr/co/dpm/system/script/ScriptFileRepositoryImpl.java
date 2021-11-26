package kr.co.dpm.system.script;

import okhttp3.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Component
public class ScriptFileRepositoryImpl implements ScriptFileRepository {
    private static final Logger logger = LogManager.getLogger(ScriptController.class);
    private static final MediaType MULTIPART = MediaType.parse("multipart/form-data");

    @Override
    public boolean distribute(MultipartFile classFile, String encryptResult) {

        try {
            byte[] bytes = classFile.getBytes();
            File convertFile = new File(classFile.getOriginalFilename());
            classFile.transferTo(convertFile);
//            logger.debug("#################111 " + convertFile.getName());
            logger.debug("################# " + bytes.toString());

            OkHttpClient client = new OkHttpClient();

            String url = "http://" + "localhost" + "/script/distribute";

            RequestBody requestBody = new MultipartBody.Builder().
                                      setType(MultipartBody.FORM).
                                      addFormDataPart("encryptId", encryptResult).
                                      addFormDataPart("scriptFile",
                                                      classFile.getOriginalFilename(),
                                                      RequestBody.create(MULTIPART,
                                                             convertFile )).build();

            Request request = new Request.Builder().
                                          url(url).
                                          post(requestBody).
                                          build();

            Response response = client.newCall(request).execute();

            logger.debug("################### " + "실행 성공");
            if (response.isSuccessful()) {
                ResponseBody responseBody = response.body();
                String responseString = responseBody.string();
                System.out.println("responseBody " + responseString);
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        logger.debug("################# return 전");
        return false;
    }
}
