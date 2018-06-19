package devdem.time;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class UpdateRequest extends StringRequest {
    private static final String UPDATE_REQUEST_URL = "http://94.26.174.204/Reminder/update.php";
    private Map<String, String> params;

    UpdateRequest(String username, String password, String zagolovok, String opisanie, Response.Listener<String> listener) {
        super(Method.POST, UPDATE_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("username", username);
        params.put("password", password);
        params.put("zagolovok", zagolovok);
        params.put("opisanie", opisanie);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
