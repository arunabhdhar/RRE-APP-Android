package org.rehab.app.async;


import android.content.Context;
import android.os.AsyncTask;

import org.apache.http.NameValuePair;
import org.rehab.app.interfaces.IASCommon;
import org.rehab.app.networkhit.ASNetworkCall;

import java.util.List;


public class ReqRespAsync<Req, Resp> extends AsyncTask<Object, Void, Resp> {

    private Context context;
    private IASCommon callbackInterface;
    private Class<Resp> mResponseClass;
    private String mUrl;
    private String mMethod;
    private List<NameValuePair> mList;
    private String type;

    public ReqRespAsync(Context context, IASCommon callbackInterface, String url, String method,
                        List<NameValuePair> mList, Class<Resp> responseClass, String type) {
        this.callbackInterface = callbackInterface;
        this.context = context;
        this.mUrl = url;
        this.mMethod = method;
        this.mList = mList;
        this.mResponseClass = responseClass;
        this.type=type;
    }



    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Resp doInBackground(Object... params) {
        Resp string;
        if (mMethod.equalsIgnoreCase("post")) {
            string = ASNetworkCall.getInstance(context).execKeyValuePost(context, mUrl, mResponseClass, mList);
        } else {
            string = ASNetworkCall.getInstance(context).execKeyValueGet(context, mUrl, mResponseClass, mList);
        }
        return string;
    }


    @Override
    protected void onPostExecute(Resp resp) {
        super.onPostExecute(resp);
        callbackInterface.onResponse(type,resp);

    }


}