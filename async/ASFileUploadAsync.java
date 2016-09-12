package org.rehab.app.async;

import android.content.Context;
import android.os.AsyncTask;
import android.text.TextUtils;

import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.ContentBody;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.rehab.app.interfaces.IASCommon;
import org.rehab.app.networkhit.ASNetworkCall;
import org.rehab.app.utils.Logger;

import java.io.File;
import java.util.HashMap;
import java.util.Map;



public class ASFileUploadAsync<Req, Resp> extends AsyncTask<Object, Void, Resp> {

    private Context context;
    private IASCommon callbackInterface;
    private Class<Resp> mResponseClass;
    private String mUrl;
    private HashMap<String, String> userData;
    private String type;

    public ASFileUploadAsync(Context context, IASCommon callbackInterface, String url,
                             HashMap<String, String > userData, Class<Resp> responseClass, String type) {
        this.callbackInterface = callbackInterface;
        this.context = context;
        this.mUrl = url;
        this.mResponseClass = responseClass;
        this.userData = userData;
        this.type=type;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Resp doInBackground(Object... params) {
        MultipartEntity reqEntity = new MultipartEntity(HttpMultipartMode.BROWSER_COMPATIBLE);
        try {
            for (Map.Entry<String, String> entry : userData.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue()==null?"":entry.getValue().toString();
                Logger.e("Key & Value","Key:->"+key +"  Value:->"+value);
                // do stuff
                if (!TextUtils.isEmpty(value) && !key.equalsIgnoreCase("user_image") && !key.equalsIgnoreCase("newdeal_image") ) {

                    ContentBody dataKey = new StringBody(value);
                    reqEntity.addPart(key, dataKey);

                }
            }
            if (userData.containsKey("user_image")) {
                String imageFilePath = userData.get("user_image").toString();
                if (imageFilePath != null && !imageFilePath.equalsIgnoreCase("") && !imageFilePath.startsWith("http")) {
                    ContentBody image;
                    if (imageFilePath.contains(".png") || imageFilePath.contains(".PNG"))
                        image = new FileBody(new File(imageFilePath), "image/png");
                    else
                        image = new FileBody(new File(imageFilePath), "image/jpg");
                    reqEntity.addPart("user_image", image);
                }
            }else if(userData.containsKey("newdeal_image")){
                String imageFilePath = userData.get("newdeal_image").toString();
                if (imageFilePath != null && !imageFilePath.equalsIgnoreCase("") && !imageFilePath.startsWith("http")) {
                    ContentBody image;
                    if (imageFilePath.contains(".png") || imageFilePath.contains(".PNG"))
                        image = new FileBody(new File(imageFilePath), "image/png");
                    else
                        image = new FileBody(new File(imageFilePath), "image/jpg");
                    reqEntity.addPart("newdeal_image", image);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        Resp string = ASNetworkCall.getInstance(context).execImage(context, mUrl, reqEntity, mResponseClass);
        return string;
    }


    @Override
    protected void onPostExecute(Resp resp) {
        super.onPostExecute(resp);
        callbackInterface.onResponse(type,resp);
    }


}
