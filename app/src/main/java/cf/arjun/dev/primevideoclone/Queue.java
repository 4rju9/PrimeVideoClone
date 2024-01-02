package cf.arjun.dev.primevideoclone;

import android.annotation.SuppressLint;
import android.content.Context;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class Queue {

    public static final String TAG = "REQUEST";
    @SuppressLint("StaticFieldLeak")
    private static Queue instance;
    private RequestQueue requestQueue;
    @SuppressLint("StaticFieldLeak")
    private static Context ctx;

    private Queue(Context context) {
        ctx = context;
        requestQueue = getRequestQueue();
    }

    public static synchronized Queue getInstance(Context context) {
        if (instance == null) {
            instance = new Queue(context);
        }
        return instance;
    }

    private RequestQueue getRequestQueue() {
        if (requestQueue == null) {
            // getApplicationContext() is key, it keeps you from leaking the
            // Activity or BroadcastReceiver if someone passes one in.
            requestQueue = Volley.newRequestQueue(ctx.getApplicationContext());
        }
        return requestQueue;
    }

    public void makeRequest (int method, String url, Response.Listener<String> response, boolean shouldCache) {

        StringRequest request = new StringRequest(
                method,
                url,
                response,
                error -> Toast.makeText(ctx, "Something went wrong, Come back later!", Toast.LENGTH_LONG).show()
        );
        request.setShouldCache(shouldCache);
        request.setTag(TAG);
        getRequestQueue().add(request);

    }

    public void onStop () {

        if (requestQueue != null) {
            requestQueue.cancelAll(TAG);
            requestQueue.stop();
        }
        requestQueue = null;
        instance = null;
        ctx = null;

    }

}
