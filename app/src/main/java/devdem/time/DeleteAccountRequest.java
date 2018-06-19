package devdem.time;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class DeleteAccountRequest extends StringRequest {
    private static final String UPDATE_REQUEST_URL = "http://94.26.174.204/Reminder/delacc.php";
    private Map<String, String> params;

    DeleteAccountRequest(String username, String password, Response.Listener<String> listener) {
        super(Method.POST, UPDATE_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("username", username);
        params.put("password", password);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}


