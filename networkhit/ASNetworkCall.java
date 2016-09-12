package org.rehab.app.networkhit;

import android.content.Context;
import android.net.http.AndroidHttpClient;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.rehab.app.utils.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;


/**
 */
public class ASNetworkCall {

    private static final String TAG = "NetworkCall";
    private static ASNetworkCall networkCall;
    private static ObjectMapper objectMapper = null;
    private String mResponseInString;

    public ASNetworkCall(Context context) {
    }

    public static ASNetworkCall getInstance(Context context) {
        if (networkCall == null) {
            networkCall = new ASNetworkCall(context);
            objectMapper = new ObjectMapper();
        }
        return networkCall;
    }
    public <Req,Resp> Resp execKeyValuePost(Context context, String url, Class<Resp> mRespClass, List<NameValuePair> mNameValuePair){

        String responseEntity = null;
        Resp resp = null;
        try {
            final HttpParams httpParameters = new BasicHttpParams();
            HttpConnectionParams.setConnectionTimeout(httpParameters, 15000);
            final DefaultHttpClient client = new DefaultHttpClient(httpParameters);

//            final HttpGet get=new HttpGet(url);

            final HttpPost post = new HttpPost(url);
            Logger.e("Params",mNameValuePair.toString());
            post.setEntity(new UrlEncodedFormEntity(mNameValuePair, "UTF-8"));
            HttpResponse httpResponse = null;
            if (mNameValuePair != null) {
                httpResponse = client.execute(post);
                try {
                    if (httpResponse != null) {
                        int statusCode = httpResponse.getStatusLine().getStatusCode();
                        Logger.i(TAG, statusCode + "");

                        if (statusCode >= 200 && statusCode <= 210) {
                            try {
                /*Changes start*/
                                if (mRespClass != null && !mRespClass.equals(HttpResponse.class)) {
                                    boolean isResponseGZipped = false;
                                    Header[] allHeaders = httpResponse.getAllHeaders();
                                    for (Header header : allHeaders) {
                                        if (header.getName().equals("Content-Encoding") && header.getValue().equals("gzip")) {
                                            isResponseGZipped = true;
                                            break;
                                        }
                                    }
                                    if (isResponseGZipped) {
                                        resp = objectMapper.readValue(AndroidHttpClient.getUngzippedContent(httpResponse.getEntity()), mRespClass);
                                    } else {
                                        HttpEntity entity = httpResponse.getEntity();
//                                Log.e("TAG",convertStreamToString(entity.getContent()));
                                        String responseStr = EntityUtils.toString(entity);
                                        Logger.e("TAG","respose-->" + responseStr);

                                        resp = objectMapper.readValue(responseStr, mRespClass);
                                    }
                                    mResponseInString = objectMapper.writeValueAsString(resp);
                                }

                            } catch (JsonParseException e) {
                                e.printStackTrace();
                            } catch (JsonMappingException e) {
                                e.printStackTrace();
                            } catch (IllegalStateException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }catch (Exception e) {
                                e.printStackTrace();
                            }

                        } else if (statusCode == 401) {

                        }
                    }
                }  catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (final Throwable e) {
            responseEntity = null;
            e.printStackTrace();
        }
        return resp;
    }


    public <Req,Resp> Resp execKeyValueGet(Context context, String url, Class<Resp> mRespClass, List<NameValuePair> mNameValuePair){

        String responseEntity = null;
        Resp resp = null;
        try {
            final HttpParams httpParameters = new BasicHttpParams();
            HttpConnectionParams.setConnectionTimeout(httpParameters, 15000);
            final DefaultHttpClient client = new DefaultHttpClient(httpParameters);
            final HttpGet get=new HttpGet(url);
            HttpResponse httpResponse = null;
            httpResponse = client.execute(get);
            try {
                if (httpResponse != null) {
                    int statusCode = httpResponse.getStatusLine().getStatusCode();
                    Logger.i(TAG, statusCode + "");

                    if (statusCode >= 200 && statusCode <= 210) {
                        try {
                /*Changes start*/
                            if (mRespClass != null && !mRespClass.equals(HttpResponse.class)) {
                                boolean isResponseGZipped = false;
                                Header[] allHeaders = httpResponse.getAllHeaders();
                                for (Header header : allHeaders) {
                                    if (header.getName().equals("Content-Encoding") && header.getValue().equals("gzip")) {
                                        isResponseGZipped = true;
                                        break;
                                    }
                                }
                                if (isResponseGZipped) {
                                    resp = objectMapper.readValue(AndroidHttpClient.getUngzippedContent(httpResponse.getEntity()), mRespClass);
                                } else {
                                    HttpEntity entity = httpResponse.getEntity();
//                                Log.e("TAG",convertStreamToString(entity.getContent()));
                                    String responseStr = EntityUtils.toString(entity);
                                    Logger.e("TAG","respose-->" + responseStr);

                                    resp = objectMapper.readValue(responseStr, mRespClass);
                                }
                                mResponseInString = objectMapper.writeValueAsString(resp);
                            }

                        } catch (JsonParseException e) {
                            e.printStackTrace();
                        } catch (JsonMappingException e) {
                            e.printStackTrace();
                        } catch (IllegalStateException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }catch (Exception e) {
                            e.printStackTrace();
                        }

                    } else if (statusCode == 401) {

                    }
                }
            }  catch (Exception e) {
                e.printStackTrace();
            }
        } catch (final Throwable e) {
            responseEntity = null;
            e.printStackTrace();
        }
        return resp;
    }



    public <Req, Resp> Resp execImage(Context context, String url, MultipartEntity mMultipartEntity, Class<Resp> mRespClass) {
        HttpParams httpParameters = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout(httpParameters, 50000);
        HttpConnectionParams.setSoTimeout(httpParameters, 50000);
        DefaultHttpClient defaultHttpClient = new DefaultHttpClient(httpParameters);
        defaultHttpClient.setParams(httpParameters);
        HttpUriRequest httpUriRequest = new HttpPost(url);
        ((HttpPost) httpUriRequest).setEntity(mMultipartEntity);
        httpUriRequest.setHeader("Accept", "application/json");
//        httpUriRequest.setHeader("content-type","multipart/form-data");
        HttpResponse httpResponse = null;
        Resp resp = null;
        try {
            httpResponse = defaultHttpClient.execute(httpUriRequest);
            if (httpResponse != null) {
                int statusCode = httpResponse.getStatusLine().getStatusCode();
                Logger.i(TAG, statusCode + "");

                if (statusCode >= 200 && statusCode <= 210) {
                    try {
                /*Changes start*/
                        if (mRespClass != null && !mRespClass.equals(HttpResponse.class)) {
                            HttpEntity entity = httpResponse.getEntity();
//                                Logger.error(convertStreamToString(entity.getContent()));
                            String responseStr = EntityUtils.toString(entity);
                            resp = objectMapper.readValue(responseStr, mRespClass);
                            mResponseInString = objectMapper.writeValueAsString(resp);
                            Logger.e(TAG, "respose-->" + mResponseInString);
                        }

                    } catch (JsonParseException e) {
                        e.printStackTrace();
                    } catch (JsonMappingException e) {
                        e.printStackTrace();
                    } catch (IllegalStateException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                } else if (statusCode == 401) {

                } else if (statusCode == 500) {
                    // Logger.error("Internal Server Error");
                }
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return resp;
    }


    /**
     * this method is use to convert input stream to string
     * return String
     */
    private String convertStreamToString(InputStream is) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
        } catch (IOException e) {
            return "IOException";
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                throw new RuntimeException(e.getMessage());
            }
        }
        return sb.toString();
    }
}
