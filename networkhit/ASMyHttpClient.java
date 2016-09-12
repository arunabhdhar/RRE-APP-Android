
package org.rehab.app.networkhit;

import android.content.Context;

import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.SingleClientConnManager;
import org.apache.http.params.HttpParams;


public class ASMyHttpClient extends DefaultHttpClient {

    final Context context;
    boolean isSSLSecurity;

    public ASMyHttpClient(Context context, HttpParams params, boolean isSSLSecurity) {
        super(params);
        this.context = context;
        this.isSSLSecurity = isSSLSecurity;
    }

    public ASMyHttpClient(Context context, boolean isSSLSecurity) {
        this.context = context;
        this.isSSLSecurity = isSSLSecurity;
    }

    @Override
    protected ClientConnectionManager createClientConnectionManager() {
        SchemeRegistry registry = new SchemeRegistry();
        registry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
        return new SingleClientConnManager(getParams(), registry);
    }


}


